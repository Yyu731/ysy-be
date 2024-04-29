package com.ruoyi.system.service.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.service.IUserIMServece;
import com.tencentyun.TLSSigAPIv2;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * ClassName: IMServiceImpl
 * Package: com.ruoyi.system.service.impl
 * Description:
 *
 * @Author yy
 * @Create 2024-04-24 9:54
 * @Version 1.0
 */
@Slf4j
@Service
public class UserIMServiceImpl implements IUserIMServece {

    @Value("${tencent.im.sdkAppId}")
    private Long sdkAppId;

    @Value("${tencent.im.secretKey}")
    private String secretKey;

    @Value("${tencent.im.managerId}")
    private String managerId;

    @Value("${tencent.im.userServiceId}")
    private String userServiceId;

    @Resource
    private SysUserMapper sysUserMapper;

    private static final String BASE_URL = "https://console.tim.qq.com";

    @Override
    public Map createAccount(Long userId) {
        SysUser sysUser = sysUserMapper.selectOne(Wrappers.lambdaQuery(SysUser.class).eq(SysUser::getUserId, userId));
        String account = "user_" + userId;
        String nickname = sysUser.getNickName();
        String userSig = new TLSSigAPIv2(sdkAppId, secretKey).genUserSig(account, 180 * 86400);

        Map result = MapUtil.builder()
                .put("sdkAppId", sdkAppId)
                .put("account", account)
                .put("userSig", userSig)
                .build();

        String adminSig = new TLSSigAPIv2(sdkAppId, secretKey).genUserSig(managerId, 180 * 86400);
        String url = BASE_URL + "/v4/im_open_login_svc/account_check?" +
                "sdkappid=" + sdkAppId +
                "&identifier=" + managerId +
                "&usersig=" + adminSig +
                "&random=" + RandomUtil.randomInt(1, 999999999) +
                "&contenttype=json";

        JSONObject json = JSONUtil.createObj()
                .set("CheckItem", ListUtil.of(
                        MapUtil.of("UserID", account)
                ));

        String response = HttpUtil.post(url, json.toString());
        JSONObject entries = JSONUtil.parseObj(response);
        Integer errorCode = entries.getInt("ErrorCode");
        String errorInfo = entries.getStr("ErrorInfo");
        if (errorCode != 0) {
            log.error("查询客户IM账号失败", errorInfo);
            throw new BaseException("客服系统异常");
        }

        JSONArray list = JSONUtil.parseArray(entries.getStr("ResultItem"));
        String accountStatus = list.getJSONObject(0).getStr("AccountStatus");
        if ("Imported".equals(accountStatus)) {
            int rows = sysUserMapper.updateById(sysUser);
            if (rows == 0) {
                log.error("无法更新客户账号登录时间，账户ID：", userId);
                throw new BaseException("客服系统异常");
            }
            this.sendWelcomeMessage(account);
            return result;
        }

        url = BASE_URL + "/v4/im_open_login_svc/account_import?" +
                "sdkappid=" + sdkAppId +
                "&identifier=" + managerId +
                "&usersig=" + adminSig +
                "&random=" + RandomUtil.randomInt(1, 999999999) +
                "&contenttype=json";

        json = JSONUtil.createObj()
                .set("UserID", account)
                .set("Nick", nickname);

        if (StringUtils.isNotEmpty(sysUser.getAvatar())) {
            json.set("FaceUrl", sysUser.getAvatar());
        }

        response = HttpUtil.post(url, json.toString());
        entries = JSONUtil.parseObj(response);
        errorCode = entries.getInt("ErrorCode");
        errorInfo = entries.getStr("ErrorInfo");
        if (errorCode != 0) {
            log.error("创建客户IM账号失败", errorInfo);
            throw new BaseException("客服系统异常");
        }

        url = BASE_URL + "/v4/sns/friend_add?" +
                "sdkappid=" + sdkAppId +
                "&identifier=" + managerId +
                "&usersig=" + adminSig +
                "&random=99999999" + RandomUtil.randomInt(1, 999999999) +
                "&contenttype=json";

        json = JSONUtil.createObj()
                .set("From_Account", account)
                .set("AddFriendItem", ListUtil.of(
                        Map.of("To_Account", userServiceId,
                                "AddSource", "AddSource_Type_web")
                ));

        response = HttpUtil.post(url, json.toString());
        entries = JSONUtil.parseObj(response);
        errorCode = entries.getInt("ErrorCode");
        errorInfo = entries.getStr("ErrorInfo");
        if (errorCode != 0) {
            log.error("添加客服IM好友失败", errorInfo);
            throw new BaseException("客服系统异常");
        }

        list = JSONUtil.parseArray(entries.getStr("ResultItem"));
        JSONObject object = list.getJSONObject(0);
        Integer resultCode = object.getInt("ResultCode");
        String resultInfo = object.getStr("ResultInfo");
        if (resultCode != 0) {
            log.error("添加客服IM好友失败", resultInfo);
            throw new BaseException("客服系统异常");
        }

        int rows = sysUserMapper.updateById(sysUser);
        if (rows == 0) {
            log.error("无法更新客户账号登录时间，账户ID：", userId);
            throw new BaseException("客服系统异常");
        }

        this.sendWelcomeMessage(account);
        return result;
    }

    public void sendWelcomeMessage(String account) {
        String userSig = new TLSSigAPIv2(sdkAppId, secretKey).genUserSig(userServiceId, 180 * 86400);
        String url = BASE_URL + "/v4/openim/sendmsg?" +
                "sdkappid=" + sdkAppId +
                "&identifier=" + userServiceId +
                "&usersig=" + userSig +
                "&random=" + RandomUtil.randomInt(1, 999999999) +
                "&contenttype=json";
        JSONObject json = JSONUtil.createObj()
                .set("SyncOtherMachine", 2)
                .set("To_Account", account)
                .set("MsgLifeTime", 120)
                .set("MsgRandom", RandomUtil.randomInt(1, 999999999))
                .set("MsgBody", ListUtil.of(
                        MapUtil.builder()
                                .put("MsgType", "TIMTextElem")
                                .put("MsgContent",
                                        MapUtil.builder().put("Text", "欢迎！")
                                                .build())
                                .build()
                ));
        String response = HttpUtil.post(url, json.toString());
        JSONObject entries = JSONUtil.parseObj(response);
        Integer errorCode = entries.getInt("ErrorCode");
        String errorInfo = entries.getStr("ErrorInfo");
        if (errorCode != 0) {
            log.error("发送欢迎词失败：" + errorInfo);
            throw new BaseException("客服系统异常");
        }
    }
}

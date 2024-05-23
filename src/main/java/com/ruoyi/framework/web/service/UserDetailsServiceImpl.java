package com.ruoyi.framework.web.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.UserStatus;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.WechatService;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

/**
 * 用户验证处理
 *
 * @author ruoyi
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private ISysUserService userService;

    @Autowired
    private WechatService wechatService;

    @Autowired
    private SysPermissionService sysPermissionService;

    private static ArrayList DEFAULT_NICKNAME_PREFIX = Lists.newArrayList(
            "生活更美好",
            "大桔大利",
            "日富一日",
            "好柿开花",
            "柿柿如意",
            "一椰暴富",
            "大柚所为",
            "杨梅吐气",
            "天生荔枝"
    );

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userService.selectUserByUserName(username);
        if (StringUtils.isNull(user)) {
            log.info("登录用户：{} 不存在.", username);
            throw new ServiceException(MessageUtils.message("user.not.exists"));
        } else if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            log.info("登录用户：{} 已被删除.", username);
            throw new ServiceException(MessageUtils.message("user.password.delete"));
        } else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            log.info("登录用户：{} 已被停用.", username);
            throw new ServiceException(MessageUtils.message("user.blocked"));
        }
        return createLoginUser(user);
    }

    public UserDetails loginByOpenId(String code) {

        JSONObject jsonObject = wechatService.getOpenid(code);

        if (ObjectUtil.isNotEmpty(jsonObject.getInt("errcode"))) {
            throw new BadCredentialsException(jsonObject.getStr("errmsg"));
        }
        String openId = jsonObject.getStr("openid");

        SysUser sysUser = userService.getByOpenid(openId);

        sysUser = ObjectUtil.isNotEmpty(sysUser) ? sysUser : SysUser.builder().openId(openId).build();

        if (sysUser.getUserId() != null) {
            userService.updateById(sysUser);
        } else {
            String nickName = (String) DEFAULT_NICKNAME_PREFIX.get((int) (Math.random() * DEFAULT_NICKNAME_PREFIX.size()));
            sysUser.setNickName(nickName);
            userService.save(sysUser);
        }

        return createLoginUser(sysUser);
    }

    public UserDetails createLoginUser(SysUser user) {

        Set<String> rolePermission = sysPermissionService.getMenuPermission(user);
        return new LoginUser(user.getUserId(), user, rolePermission);
//        return new LoginUser(user.getUserId(), user.getDeptId(), user, permissionService.getMenuPermission(user));
    }
}

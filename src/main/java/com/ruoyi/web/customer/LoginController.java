package com.ruoyi.web.customer;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginBody;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.service.SysLoginService;
import com.ruoyi.system.service.IUserIMServece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * ClassName: LoginController
 * Package: com.ruoyi.web.yxy
 * Description:
 *
 * @Author yy
 * @Create 2024-05-23 17:42
 * @Version 1.0
 */
@RestController
@RequestMapping("/customer")
public class LoginController {

    @Autowired
    private SysLoginService loginService;

    @Autowired
    private IUserIMServece userIMServece;

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("login")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);

        LoginUser loginUser = SecurityUtils.getLoginUser();
        Map<String, Object> account = userIMServece.createAccount(loginUser.getUsername());
        ajax.put("mi", account);
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("wx-login")
    public AjaxResult wxLogin(@RequestBody LoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();

        String token = loginService.wxLogin(loginBody.getWxCode());
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Map<String, Object> account = userIMServece.createAccount(loginUser.getUsername());
        ajax.put("mi", account);
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo() {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        return ajax;
    }
}

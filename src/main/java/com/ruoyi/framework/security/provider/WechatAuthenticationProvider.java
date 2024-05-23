package com.ruoyi.framework.security.provider;



import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.framework.security.token.WechatAuthenticationToken;
import com.ruoyi.framework.web.service.UserDetailsServiceImpl;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.WechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

/**
 * ClassName: WechatAuthenticationProvider
 * Package: com.ruoyi.framework.security.provider
 * Description:
 *
 * @Author yy
 * @Create 2024-04-09 0:08
 * @Version 1.0
 */
public class WechatAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private WechatService wechatService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //获取openId
        WechatAuthenticationToken token = (WechatAuthenticationToken) authentication;
        String code = token.getOpenId();

        UserDetails userDetails = userDetailsService.loginByOpenId(code);
        LoginUser loginUser = (LoginUser) userDetails;

        WechatAuthenticationToken newToken = WechatAuthenticationToken.authenticated(loginUser.getOpenId(), userDetails, null);
        newToken.setDetails(token.getDetails());
        return newToken;
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return WechatAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

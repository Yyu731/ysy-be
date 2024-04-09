package com.ruoyi.framework.security.token;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.Collection;

/**
 * ClassName: WechatAuthenticationToken
 * Package: com.ruoyi.framework.security.token
 * Description:
 *
 * @Author yy
 * @Create 2024-04-09 0:00
 * @Version 1.0
 */
@Getter
public class WechatAuthenticationToken extends AbstractAuthenticationToken {
    private final String openId;
    private final Object principal;

    /**
     * 未认证的Token
     *
     * @param openId openId
     */
    public WechatAuthenticationToken(String openId, Object principal) {
        super(null);
        this.openId = openId;
        this.principal = principal;
        setAuthenticated(false);
    }

    /**
     * 已认证的token
     *
     * @param openId      openId
     * @param authorities 权限
     */
    public WechatAuthenticationToken(String openId, Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.openId = openId;
        this.principal = principal;
        setAuthenticated(true);
    }

    /**
     * 未认证的Token
     *
     * @param openId openId
     */
    public static WechatAuthenticationToken unauthenticated(String openId, Object principal) {
        return new WechatAuthenticationToken(openId, principal);
    }

    /**
     * 已认证的token
     *
     * @param openId      openId
     * @param authorities 权限
     */
    public static WechatAuthenticationToken authenticated(String openId, Object principal, Collection<? extends GrantedAuthority> authorities) {
        return new WechatAuthenticationToken(openId, principal, authorities);
    }


    @Override
    public Object getCredentials() {
        return null;
    }

}

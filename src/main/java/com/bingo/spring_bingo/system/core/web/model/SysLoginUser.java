package com.bingo.spring_bingo.system.core.web.model;

import com.bingo.spring_bingo.common.constant.SysModelEnum;
import com.bingo.spring_bingo.system.model.SysOrgUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * @author bingo
 * @date 2022-04-29 15:31
 */
public class SysLoginUser implements UserDetails {

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipAddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String osName;

    /**
     * 权限列表
     */
    private Set<String> authmarks;

    /**
     * 用户信息
     */
    @JsonIgnoreProperties(clazz = SysOrgUser.class, isShow = true, value = {"fdName"})
    private SysOrgUser user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public SysOrgUser getUser() {
        return user;
    }

    public void setUser(SysOrgUser user) {
        this.user = user;
    }

    public String getLoginLocation() {
        return loginLocation;
    }

    public void setLoginLocation(String loginLocation) {
        this.loginLocation = loginLocation;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public Set<String> getAuthmarks() {
        return authmarks;
    }

    public void setAuthmarks(Set<String> authmarks) {
        this.authmarks = authmarks;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getFdPassword();
    }

    @Override
    public String getUsername() {
        return user.getFdLoginName();
    }

    /**
     * 账户是否未过期,过期无法验证
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     */
    @Override
    public boolean isAccountNonLocked() {
        return user.getFdIsLock().equals(SysModelEnum.BOOLEAN_NO.getCode());
    }

    /**
     * 指示是否已未过期的用户的凭据(密码),过期的凭据防止认证
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用 ,禁用的用户不能身份验证
     */
    @Override
    public boolean isEnabled() {
        return user.getFdIsAvailable().equals(SysModelEnum.BOOLEAN_YES.getCode());
    }
}

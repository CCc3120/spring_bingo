package com.bingo.spring_bingo.system.core.security;

import com.bingo.spring_bingo.system.core.constant.SysConstant;
import com.bingo.spring_bingo.system.core.util.RedisUtil;
import com.bingo.spring_bingo.system.core.util.ServletUtil;
import com.bingo.spring_bingo.system.core.web.model.SysLoginUser;
import com.bingo.spring_bingo.util.AddressUtil;
import com.bingo.spring_bingo.util.DateUtil;
import com.bingo.spring_bingo.util.IDGenerator;
import com.bingo.spring_bingo.util.IPUtil;
import com.bingo.spring_bingo.util.ObjectUtil;
import com.bingo.spring_bingo.util.StringUtil;
import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author h-bingo
 * @date 2022/05/02 16:00
 **/
@Component
public class UserTokenService {
    // 令牌自定义标识
    @Value("${token.header}")
    private String header;

    // 令牌秘钥
    @Value("${token.secret}")
    private String secret;

    // 令牌有效期（默认20分钟）
    @Value("${token.expireTime}")
    private int expireTime;

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public SysLoginUser getLoginUser(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = getToken(request);
        if (StringUtil.isNotNull(token)) {
            try {
                Claims claims = parseToken(token);
                // 解析对应的权限以及用户信息
                String uuid = (String) claims.get(SysConstant.LOGIN_USER_KEY);
                String userKey = getTokenKey(uuid);
                SysLoginUser user = RedisUtil.getCacheObject(userKey);
                return user;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 设置用户身份信息
     */
    public void setLoginUser(SysLoginUser loginUser) {
        if (!ObjectUtil.isNull(loginUser) && StringUtil.isNotNull(loginUser.getToken())) {
            refreshToken(loginUser);
        }
    }

    /**
     * 删除用户身份信息
     */
    public void delLoginUser(String token) {
        if (StringUtil.isNotNull(token)) {
            String userKey = getTokenKey(token);
            RedisUtil.deleteObject(userKey);
        }
    }

    /**
     * 创建令牌
     *
     * @param loginUser 用户信息
     * @return 令牌
     */
    public String createToken(SysLoginUser loginUser) {
        String token = IDGenerator.generateID();
        loginUser.setToken(token);
        setUserAgent(loginUser);
        refreshToken(loginUser);

        Map<String, Object> claims = new HashMap<>();
        claims.put(SysConstant.LOGIN_USER_KEY, token);
        return createToken(claims);
    }

    /**
     * 验证令牌有效期，相差不足20分钟，自动刷新缓存
     *
     * @param loginUser
     * @return 令牌
     */
    public void verifyToken(SysLoginUser loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= expireTime * DateUtil.MINUTE) {
            refreshToken(loginUser);
        }
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginUser 登录信息
     */
    public void refreshToken(SysLoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * DateUtil.MINUTE);
        // 根据uuid将loginUser缓存
        String userKey = getTokenKey(loginUser.getToken());
        RedisUtil.setCacheObject(userKey, loginUser, expireTime, TimeUnit.MINUTES);
    }

    /**
     * 设置用户代理信息
     *
     * @param loginUser 登录信息
     */
    public void setUserAgent(SysLoginUser loginUser) {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtil.getRequest().getHeader("User-Agent"));
        String ip = IPUtil.getIpAddr(ServletUtil.getRequest());
        loginUser.setIpAddr(ip);
        loginUser.setLoginLocation(AddressUtil.getAddressByIP(ip));
        loginUser.setBrowser(userAgent.getBrowser().getName());
        loginUser.setOsName(userAgent.getOperatingSystem().getName());
    }


    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (StringUtil.isNotNull(token) && token.startsWith(SysConstant.TOKEN_PREFIX)) {
            token = token.replace(SysConstant.TOKEN_PREFIX, "");
        }
        return token;
    }

    private String getTokenKey(String uuid) {
        return RedisUtil.LOGIN_TOKEN_KEY + uuid;
    }
}

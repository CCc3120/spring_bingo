package com.bingo.spring_bingo.system.core.web.service.spring;

import com.bingo.spring_bingo.system.core.security.UserTokenService;
import com.bingo.spring_bingo.system.core.util.RedisUtil;
import com.bingo.spring_bingo.system.core.web.model.SysLoginUser;
import com.bingo.spring_bingo.system.core.web.service.ISysUserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * 系统登录服务
 *
 * @author bingo
 * @date 2022-04-09 17:31
 */
@Service
public class SysUserLoginServiceImpl implements ISysUserLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserTokenService userTokenService;

    @Override
    public String doLogin(String username, String password, String code, String uuid) {

        // validateCode(username, code, uuid);

        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                System.out.println("账号密码错误");
                throw new RuntimeException("账号密码错误");
            } else {
                System.out.println("其他服务异常");
                throw new RuntimeException("其他服务异常");
            }
        }

        // 登录验证通过的信息
        SysLoginUser loginUser = (SysLoginUser) authentication.getPrincipal();
        return userTokenService.createToken(loginUser);
    }

    // 验证登录验证码
    private void validateCode(String username, String code, String uuid) {
        String verifyKey = RedisUtil.CAPTCHA_CODE_KEY + uuid;
        String validateValue = RedisUtil.getCacheObject(verifyKey);
        RedisUtil.deleteObject(verifyKey);
        if (validateValue == null) {
            System.out.println("验证码过期");
            throw new RuntimeException("验证码过期");
        }

        if (!validateValue.equalsIgnoreCase(code)) {
            System.out.println("验证码错误");
            throw new RuntimeException("验证码错误");
        }
    }
}

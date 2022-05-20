package com.bingo.spring_bingo.system.core.security;

import com.bingo.spring_bingo.system.core.web.model.SysLoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证成功处理
 *
 * @author bingo
 * @date 2022-04-29 10:58
 */
@Component
public class JwtLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserTokenService userTokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        SysLoginUser loginUser = (SysLoginUser) authentication.getPrincipal();
        String jwtToken = userTokenService.createToken(loginUser);
        System.out.println("jwtToken：" + jwtToken);
    }
}

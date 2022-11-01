package com.bingo.spring_bingo.system.core.security;

import com.bingo.spring_bingo.system.core.response.AjaxResultFactory;
import com.bingo.spring_bingo.system.core.util.JsonMapper;
import com.bingo.spring_bingo.system.core.util.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author bingo
 * @date 2022-05-10 16:34
 */
@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        // 用户未通过认证访问受保护的资源时
        log.info("未认证用户访问受保护资源");
        ServletUtil.renderString(response, JsonMapper.getInstance().toJsonString(AjaxResultFactory.fail("未登陆系统")));
    }
}

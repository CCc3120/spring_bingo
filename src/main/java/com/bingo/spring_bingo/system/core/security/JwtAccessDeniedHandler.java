package com.bingo.spring_bingo.system.core.security;

import com.bingo.spring_bingo.system.core.response.AjaxResultFactory;
import com.bingo.spring_bingo.system.core.util.JsonMapper;
import com.bingo.spring_bingo.system.core.util.ServletUtil;
import com.bingo.spring_bingo.system.core.web.model.SysLoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
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
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Autowired
    private UserTokenService userTokenService;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 认证成功的用户访问受保护的资源，但是权限不够
        SysLoginUser loginUser = userTokenService.getLoginUser(request);
        log.info("「{}」认证的用户访问受保护资源，权限不够", loginUser.getFdUsername());
        ServletUtil.renderString(response, JsonMapper.getInstance().toJsonString(AjaxResultFactory.fail(
                "很抱歉，您无权访问此页面")));
    }
}

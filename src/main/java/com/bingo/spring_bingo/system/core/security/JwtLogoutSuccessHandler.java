package com.bingo.spring_bingo.system.core.security;

import com.bingo.spring_bingo.system.core.web.model.SysLoginUser;
import com.bingo.spring_bingo.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 退出成功处理
 *
 * @author bingo
 * @date 2022-04-29 10:59
 */
@Slf4j
@Component
public class JwtLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private UserTokenService userTokenService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        SysLoginUser loginUser = userTokenService.getLoginUser(request);
        if (!ObjectUtil.isNull(loginUser)) {
            String fdUserName = loginUser.getUsername();
            // 删除用户缓存记录
            userTokenService.delLoginUser(loginUser.getToken());
            log.info("「{}」退出成功", fdUserName);
            // 退出成功，重定向到指定链接
        }
    }
}

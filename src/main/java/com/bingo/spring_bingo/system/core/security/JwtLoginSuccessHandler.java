package com.bingo.spring_bingo.system.core.security;

import com.bingo.spring_bingo.system.core.response.AjaxResultFactory;
import com.bingo.spring_bingo.system.core.util.JsonMapper;
import com.bingo.spring_bingo.system.core.util.ServletUtil;
import com.bingo.spring_bingo.system.core.web.model.SysLoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证成功处理
 *
 * @author bingo
 * @date 2022-04-29 10:58
 */
@Slf4j
@Component
public class JwtLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserTokenService userTokenService;

    /**
     * 此方法适用springsecurity的登陆验证
     * <p>
     * 无需配置忽略登陆接口，
     * <p>
     * securityconfig中 formLogin.loginProcessingUrl("/sys/login")这项一定不能配
     *
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        SysLoginUser loginUser = (SysLoginUser) authentication.getPrincipal();
        String jwtToken = userTokenService.createToken(loginUser);
        log.info("登陆成功jwtToken：{}", jwtToken);
        Map<String, String> map = new HashMap<>();
        map.put("token", jwtToken);
        ServletUtil.renderString(response, JsonMapper.getInstance().toJsonString(AjaxResultFactory.success(map)));
    }
}

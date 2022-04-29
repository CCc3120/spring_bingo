package com.bingo.spring_bingo.common.security;

import com.bingo.spring_bingo.system.core.security.LoginFailureHandlerImpl;
import com.bingo.spring_bingo.system.core.security.LoginSuccessHandlerImpl;
import com.bingo.spring_bingo.system.core.security.LogoutSuccessHandlerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

import java.util.ArrayList;
import java.util.List;

/**
 * SpringSecurity 配置
 *
 * @author bingo
 * @date 2022-03-25 11:27
 */
@EnableWebSecurity(debug = false)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) //开启方法权限注解
public class SysSpringSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Spring Security默认是禁用注解的，要想开启注解，要在继承WebSecurityConfigurerAdapter的类加@EnableGlobalMethodSecurity注解，
     * 并在该类中将AuthenticationManager定义为Bean。
     * <p>
     * 解决 无法直接注入 AuthenticationManager
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        List<String> url = new ArrayList<>();

        http
                .cors((cors) -> {

                })
                .authorizeRequests((authorizeRequests) ->
                        authorizeRequests
                                // 放行的url
                                .antMatchers(url.toArray(new String[]{})).permitAll()
                                // 放行,跨域请求会先进行一次options请求
                                .antMatchers(HttpMethod.OPTIONS).permitAll()
                                // 其他url都要认证
                                .anyRequest().authenticated()
                )
                .formLogin((formLogin) ->
                        formLogin
                                // .authenticationDetailsSource(authenticationDetailsSource)
                                // .loginPage("/intologin")
                                .loginProcessingUrl("/login")
                                .permitAll()
                                // 登录成功处理
                                .successHandler(new LoginSuccessHandlerImpl())
                                // 登录失败处理
                                .failureHandler(new LoginFailureHandlerImpl())

                )
                .logout((logout) ->
                        logout
                                .logoutUrl("/logout")
                                // 退出成功处理
                                .logoutSuccessHandler(new LogoutSuccessHandlerImpl())
                )
                .exceptionHandling((exceptionHandling) ->
                                // 异常处理
                                exceptionHandling
                                        // .authenticationEntryPoint(null)
                                        .accessDeniedPage("/")
                                        // .accessDeniedHandler(null)
                )
                // 禁用请求头
                .headers(AbstractHttpConfigurer::disable)
                // 不使用session
                .sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 禁用csrf
                .csrf(AbstractHttpConfigurer::disable)
        ;
    }
}

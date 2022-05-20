package com.bingo.spring_bingo.common.security;

import com.bingo.spring_bingo.system.core.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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


    @Autowired
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    private JwtLoginFailureHandler jwtLoginFailureHandler;
    @Autowired
    private JwtLoginSuccessHandler jwtLoginSuccessHandler;
    @Autowired
    private JwtLogoutSuccessHandler jwtLogoutSuccessHandler;

    /**
     * 跨域过滤器
     */
    // @Autowired
    // private CorsFilter corsFilter;

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

    /**
     * 强散列哈希加密实现
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        List<String> ignoreUrl = new ArrayList<>();

        List<String> anonymousUrl = new ArrayList<>();

        http
                .cors((cors) -> {

                })
                .authorizeRequests((authorizeRequests) ->
                        authorizeRequests
                                // 放行的url
                                .antMatchers(ignoreUrl.toArray(new String[]{})).permitAll()
                                // 放行,跨域请求会先进行一次options请求
                                .antMatchers(HttpMethod.OPTIONS).permitAll()
                                .antMatchers(anonymousUrl.toArray(new String[]{})).anonymous()
                                // 其他url都要认证
                                .anyRequest().authenticated()
                )
                .formLogin((formLogin) ->
                        formLogin
                                // .authenticationDetailsSource(authenticationDetailsSource)
                                // .loginPage("/intologin")
                                .loginProcessingUrl("/sys/login")
                                .permitAll()
                                // 登录成功处理
                                .successHandler(jwtLoginSuccessHandler)
                                // 登录失败处理
                                .failureHandler(jwtLoginFailureHandler)

                )
                .logout((logout) ->
                        logout
                                .logoutUrl("/sys/logout")
                                // 退出成功处理
                                .logoutSuccessHandler(jwtLogoutSuccessHandler)
                )
                .exceptionHandling((exceptionHandling) ->
                                // 异常处理
                                exceptionHandling
                                        .accessDeniedHandler(jwtAccessDeniedHandler)
                                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                        // .accessDeniedPage("/")
                )
                // 禁用请求头
                .headers(AbstractHttpConfigurer::disable)
                // 不使用session
                .sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 禁用csrf
                .csrf(AbstractHttpConfigurer::disable)
        ;
        // 添加JWT filter
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        // 添加CORS filter
        // http.addFilterBefore(corsFilter, JwtAuthenticationTokenFilter.class);
        // http.addFilterBefore(corsFilter, LogoutFilter.class);
    }
}
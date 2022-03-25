package com.bingo.spring_bingo.common.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bingo
 * @date 2022-03-25 11:27
 */

@EnableWebSecurity()
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) //开启方法权限注解
public class SysSpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        List<String> url = new ArrayList<>();

        http
                .cors((cors) -> {

                })
                .authorizeRequests((authorizeRequests) ->
                        authorizeRequests
                                .antMatchers(url.toArray(new String[]{})).permitAll()
                                //跨域请求会先进行一次options请求
                                .antMatchers(HttpMethod.OPTIONS).permitAll()
                                .anyRequest().permitAll()
                )
                .csrf(AbstractHttpConfigurer::disable)
        ;
    }
}

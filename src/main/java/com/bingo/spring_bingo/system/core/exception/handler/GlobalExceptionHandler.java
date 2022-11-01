package com.bingo.spring_bingo.system.core.exception.handler;

import com.bingo.spring_bingo.system.core.exception.AuthenticationLoginException;
import com.bingo.spring_bingo.system.core.response.AjaxResult;
import com.bingo.spring_bingo.system.core.response.AjaxResultFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统异常处理类
 *
 * @author bingo
 * @date 2022-04-09 17:17
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationLoginException.class)
    public AjaxResult authenticationLoginException(AuthenticationLoginException e, HttpServletRequest request) {
        log.error("认证异常", e);
        return AjaxResultFactory.fail(e.getMessage());
    }

    /**
     * 其他异常
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public AjaxResult exception(Exception e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error(String.format("请求地址'%s'，发生系统异常。", requestURI), e);
        return AjaxResultFactory.error();
    }
}

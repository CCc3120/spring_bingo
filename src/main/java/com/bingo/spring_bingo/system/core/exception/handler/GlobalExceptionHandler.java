package com.bingo.spring_bingo.system.core.exception.handler;

import com.bingo.spring_bingo.system.core.response.AjaxResult;
import com.bingo.spring_bingo.system.core.response.AjaxResultFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统异常处理类
 *
 * @author bingo
 * @date 2022-04-09 17:17
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

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
        logger.error(String.format("请求地址'%s'，发生系统异常。", requestURI), e);
        return AjaxResultFactory.error();
    }
}

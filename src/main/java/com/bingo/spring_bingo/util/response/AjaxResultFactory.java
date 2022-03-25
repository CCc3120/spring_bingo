package com.bingo.spring_bingo.util.response;

import com.bingo.spring_bingo.common.constant.HttpStatusEnum;

/**
 * @author bingo
 * @date 2022-03-24 13:45
 */
public class AjaxResultFactory {

    public static AjaxResult success() {
        return new AjaxResult(HttpStatusEnum.SUCCESS.getCode(), HttpStatusEnum.SUCCESS.getDesc(), null);
    }

    public static AjaxResult success(Object data) {
        return new AjaxResult(HttpStatusEnum.SUCCESS.getCode(), HttpStatusEnum.SUCCESS.getDesc(), data);
    }

    public static AjaxResult error() {
        return new AjaxResult(HttpStatusEnum.ERROR.getCode(), HttpStatusEnum.ERROR.getDesc(), null);
    }

    public static AjaxResultProxy build() {
        return new AjaxResultProxy();
    }

    public static AjaxResultProxy build(HttpStatusEnum httpStatusEnum) {
        return new AjaxResultProxy(httpStatusEnum.getCode(), httpStatusEnum.getDesc(), null);
    }

    public static AjaxResultProxy build(HttpStatusEnum httpStatusEnum, String message) {
        return new AjaxResultProxy(httpStatusEnum.getCode(), message, null);
    }

    public static AjaxResultProxy build(HttpStatusEnum httpStatusEnum, Object data) {
        return new AjaxResultProxy(httpStatusEnum.getCode(), httpStatusEnum.getDesc(), data);
    }

    public static AjaxResultProxy build(HttpStatusEnum httpStatusEnum, String message, Object data) {
        return new AjaxResultProxy(httpStatusEnum.getCode(), message, data);
    }
}

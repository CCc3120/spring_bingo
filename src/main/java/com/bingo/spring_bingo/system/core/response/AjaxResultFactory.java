package com.bingo.spring_bingo.system.core.response;

import com.bingo.spring_bingo.common.constant.HttpStatusEnum;

/**
 * @author bingo
 * @date 2022-03-24 13:45
 */
public class AjaxResultFactory {

    public static AjaxResult success() {
        return success(null);
    }

    public static AjaxResult success(Object data) {
        return new AjaxResultProxy(HttpStatusEnum.SUCCESS.getCode(), HttpStatusEnum.SUCCESS.getDesc(), data).result();
    }

    public static AjaxResult fail() {
        return new AjaxResultProxy(HttpStatusEnum.FAIL.getCode(), HttpStatusEnum.FAIL.getDesc(), null).result();
    }

    public static AjaxResult error() {
        return new AjaxResultProxy(HttpStatusEnum.ERROR.getCode(), HttpStatusEnum.ERROR.getDesc(), null).result();
    }

    public static AjaxResult build(HttpStatusEnum httpStatusEnum) {
        return new AjaxResultProxy(httpStatusEnum.getCode(), httpStatusEnum.getDesc(), null).result();
    }

    public static AjaxResult build(HttpStatusEnum httpStatusEnum, Object data) {
        return new AjaxResultProxy(httpStatusEnum.getCode(), httpStatusEnum.getDesc(), data).result();
    }

    public static AjaxResultProxy build() {
        return new AjaxResultProxy();
    }
}

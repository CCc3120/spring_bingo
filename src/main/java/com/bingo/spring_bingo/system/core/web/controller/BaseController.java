package com.bingo.spring_bingo.system.core.web.controller;

import com.bingo.spring_bingo.system.core.response.AjaxResult;
import com.bingo.spring_bingo.system.core.response.AjaxResultFactory;

/**
 * @author bingo
 * @date 2022-04-08 16:38
 */
public abstract class BaseController {

    protected AjaxResult success() {
        return AjaxResultFactory.success();
    }

    protected AjaxResult success(Object data) {
        return AjaxResultFactory.success(data);
    }

    protected AjaxResult fail() {
        return AjaxResultFactory.fail();
    }

    protected AjaxResult fail(String message) {
        return AjaxResultFactory.fail(message);
    }

    protected AjaxResult error() {
        return AjaxResultFactory.error();
    }

    protected AjaxResult error(String message) {
        return AjaxResultFactory.error(message);
    }
}

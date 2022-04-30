package com.bingo.spring_bingo.system.core.response;

/**
 * @author bingo
 * @date 2022-03-24 11:57
 */
public class AjaxResultProxy {

    private String code;

    private String message;

    private Object data;

    protected AjaxResultProxy() {
    }

    protected AjaxResultProxy(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public AjaxResultProxy code(String code) {
        this.code = code;
        return this;
    }

    public AjaxResultProxy message(String message) {
        this.message = message;
        return this;
    }

    public AjaxResultProxy data(Object data) {
        this.data = data;
        return this;
    }

    public AjaxResult result() {
        return new AjaxResult(this.code, this.message, this.data);
    }
}

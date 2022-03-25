package com.bingo.spring_bingo.common.constant;

/**
 * @author bingo
 * @date 2022-03-24 11:36
 */
public enum HttpStatusEnum {
    SUCCESS("200", "操作成功"),
    ERROR("500","系统错误");

    private String code;
    private String desc;

    HttpStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}

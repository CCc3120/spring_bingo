package com.bingo.spring_bingo.common.constant;

/**
 * @author bingo
 * @date 2022-03-24 20:00
 */
public enum SysModelEnum {
    BOOLEAN_YES("Y", "是"),
    BOOLEAN_NO("N", "否"),

    SEX_MAN("M", "男"),
    SEX_WOMAN("W", "女"),
    SEX_NO("NO", "未知");


    private String code;
    private String desc;

    SysModelEnum(String code, String desc) {
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

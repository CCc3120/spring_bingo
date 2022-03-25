package com.bingo.spring_bingo.system.interfaces;

/**
 * @author bingo
 * @date 2022-03-25 14:32
 */
public interface ISysOrgUser extends ISysOrgElement{
    /**
     * @return 手机号
     */
    String getFdMobileNo();

    /**
     * @return Email地址
     */
    String getFdEmail();

    /**
     * @return 登录名
     */
    String getFdLoginName();

}

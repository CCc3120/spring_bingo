package com.bingo.spring_bingo.system.core.constant;

/**
 * 系统常量
 *
 * @author h-bingo
 * @date 2022/05/02 15:51
 **/
public interface SysConstant {
    /**
     * 分号 分隔符
     */
    String SEPAR_SEMICOLON = ";";
    /**
     * 管理员权限
     */
    String ADMIN_AUTH = "*:*:*";
    /**
     * 管理员账号
     */
    String ADMIN_ACCT = "admin";


    /**
     * 令牌
     */
    String TOKEN = "token";

    /**
     * 令牌前缀
     */
    String TOKEN_PREFIX = "Bearer ";
    /**
     * 令牌前缀
     */
    String LOGIN_USER_KEY = "login_user_key";
}

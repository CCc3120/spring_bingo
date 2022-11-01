package com.bingo.spring_bingo.system.core.exception;

public class AuthenticationLoginException extends RuntimeException {

    public static final String VALIDATE_CODE_LOSE = "验证码失效";
    public static final String VALIDATE_CODE_ERROR = "验证码错误";
    public static final String NO_ACCOUNT = "账户不存在";
    public static final String NO_AVAILABLE = "账户不可用";
    public static final String LOCK = "账户已锁定";

    public AuthenticationLoginException(String message) {
        super(message);
    }
}

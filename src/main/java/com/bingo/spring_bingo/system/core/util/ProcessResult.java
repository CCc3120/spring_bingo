package com.bingo.spring_bingo.system.core.util;

public class ProcessResult<T> {

    private Boolean isSuccess;

    private T result;

    private String message;

    public ProcessResult(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public ProcessResult(Boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public ProcessResult(Boolean isSuccess, T result) {
        this.isSuccess = isSuccess;
        this.result = result;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean isSuccess() {
        return isSuccess;
    }

    public static <T> ProcessResult<T> success() {
        return new ProcessResult<>(true);
    }

    public static <T> ProcessResult<T> success(T t) {
        return new ProcessResult<>(true, t);
    }

    public static <T> ProcessResult<T> fail() {
        return new ProcessResult<>(false);
    }

    public static <T> ProcessResult<T> fail(String message) {
        return new ProcessResult<>(false, message);
    }
}

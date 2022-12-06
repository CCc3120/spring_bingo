package com.bingo.spring_bingo.system.core.util.retry;

public interface RetryService {

    Object exec() throws Throwable;
}

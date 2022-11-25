package com.bingo.spring_bingo.system.core.cache;

public interface Cache {

    <T> T addObject(String key, T data);

    <T> T getObject(String key);

    void removeObject(String key);
}

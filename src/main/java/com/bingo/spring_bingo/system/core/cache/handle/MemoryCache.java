package com.bingo.spring_bingo.system.core.cache.handle;

import com.bingo.spring_bingo.system.core.cache.Cache;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class MemoryCache implements Cache {

    public static final String CACHE_TYPE = "MemoryCache";

    private static Map<String, Object> memoryCache = new HashMap<>();

    private Type type;

    public MemoryCache(Type type) {
        this.type = type;
    }

    @Override
    public <T> T addObject(String key, T data) {
        memoryCache.put(key, data);
        return data;
    }

    @Override
    public <T> T getObject(String key) {
        return (T) memoryCache.get(key);
    }

    @Override
    public void removeObject(String key) {
        memoryCache.remove(key);
    }
}

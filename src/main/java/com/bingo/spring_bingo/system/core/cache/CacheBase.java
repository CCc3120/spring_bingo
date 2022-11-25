package com.bingo.spring_bingo.system.core.cache;

import com.bingo.spring_bingo.system.core.cache.handle.MemoryCache;
import com.bingo.spring_bingo.system.core.cache.handle.MemoryRedisCache;
import com.bingo.spring_bingo.system.core.cache.handle.RedisCache;
import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.Type;

public abstract class CacheBase implements InitializingBean {

    protected CacheManager cacheManager;

    public abstract String cacheType();

    public abstract Type type();

    @Override
    public void afterPropertiesSet() throws Exception {
        cacheManager = CacheManager.getDefaultCacheManager(cacheType());
        cacheManager.addCache(MemoryCache.CACHE_TYPE, new MemoryCache(type()));
        cacheManager.addCache(RedisCache.CACHE_TYPE, new RedisCache(type()));
        cacheManager.addCache(MemoryRedisCache.CACHE_TYPE, new MemoryRedisCache(type()));
    }
}

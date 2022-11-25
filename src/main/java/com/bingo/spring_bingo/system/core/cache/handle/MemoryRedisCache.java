package com.bingo.spring_bingo.system.core.cache.handle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bingo.spring_bingo.system.core.cache.Cache;
import com.bingo.spring_bingo.system.core.util.RedisUtil;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class MemoryRedisCache implements Cache {

    public static final String CACHE_TYPE = "MemoryRedisCache";

    private String namespace = "MemoryRedisCache";

    private static Map<String, Object> memoryCache = new HashMap<>();
    private Type type;

    public MemoryRedisCache(Type type) {
        this.type = type;
    }

    @Override
    public <T> T addObject(String key, T data) {
        if ("java.lang.String".equals(type.getTypeName())) {
            JSONObject object = new JSONObject();
            object.put(namespace, data);
            RedisUtil.setCacheObject(key, object);
        } else {
            RedisUtil.setCacheObject(key, JSON.parse(JSON.toJSONString(data)));
        }
        memoryCache.put(key, data);
        return data;
    }

    @Override
    public <T> T getObject(String key) {
        T data = (T) memoryCache.get(key);
        if (data == null) {
            JSON json = RedisUtil.getCacheObject(key);
            if (json == null) {
                return null;
            }
            if ("java.lang.String".equals(type.getTypeName())) {
                return (T) ((JSONObject) json).getString(namespace);
            } else if (JSON.class.getTypeName().equals(type.getTypeName())) {
                return (T) json;
            } else if (json instanceof JSONObject) {
                return JSON.parseObject(json.toJSONString(), type);
            } else if (json instanceof JSONArray) {
                return (T) JSON.parseArray(json.toJSONString(), new Type[]{type});
            }
        }
        return null;
    }

    @Override
    public void removeObject(String key) {
        memoryCache.remove(key);
        RedisUtil.deleteObject(key);
    }
}

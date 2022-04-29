package com.bingo.spring_bingo.system.core.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * redis 操作工具类
 *
 * @author bingo
 * @date 2022-04-27 16:18
 */
@Component
public class RedisUtil {

    /**
     * 分割符
     */
    public static final String DECOLLATOR = ":";

    /**
     * 应用前缀
     */
    public static final String APP_PREFIX = "AppName";

    /**
     * 缓存名前缀
     */
    public static final String CACHE_NAMES_PREFIX = APP_PREFIX + DECOLLATOR + "cacheNames";

    /**
     * 永不过期的缓存名
     */
    public static final String CACHE_NAME_FOREVER = CACHE_NAMES_PREFIX + DECOLLATOR + "forever";

    /**
     * 验证码 redis key 前缀
     */
    public static final String CAPTCHA_CODE_KEY = APP_PREFIX + DECOLLATOR + "user_captcha_code" + DECOLLATOR;

    /**
     * 登录用户 redis key 前缀
     */
    public static final String LOGIN_TOKEN_KEY = APP_PREFIX + DECOLLATOR + "user_login_token" + DECOLLATOR;


    private static RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setDatastore(RedisTemplate<String, Object> redisTemplate) {
        RedisUtil.redisTemplate = redisTemplate;
    }

    /**
     * 判断 key 是否存在
     *
     * @param key 键
     * @return 如果存在 key 则返回 true，否则返回 false
     */
    public static Boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     */
    public static <T> void setCacheObject(String key, T value) {
        opsForValue().set(key, value);
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key     缓存的键值
     * @param value   缓存的值
     * @param timeout 时间
     */
    public static <T> void setCacheObject(String key, T value, Integer timeout) {
        opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key      缓存的键值
     * @param value    缓存的值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     */
    public static <T> void setCacheObject(String key, T value, Integer timeout, TimeUnit timeUnit) {
        opsForValue().set(key, value, timeout, timeUnit);
    }


    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @return true=设置成功；false=设置失败
     */
    public static boolean expire(String key, long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return true=设置成功；false=设置失败
     */
    public static boolean expire(String key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public static <T> T getCacheObject(String key) {
        return (T) opsForValue().get(key);
    }

    /**
     * 删除单个对象
     *
     * @param key
     */
    public static boolean deleteObject(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除集合对象
     *
     * @param collection 多个对象
     * @return
     */
    public static long deleteObject(Collection collection) {
        return redisTemplate.delete(collection);
    }

    public static String getCacheKey(String cacheName) {
        return CACHE_NAMES_PREFIX.replace("cacheNames", cacheName);
    }

    public static String getCacheForeverKey(String cacheName) {
        return CACHE_NAME_FOREVER.replace("cacheNames", cacheName);
    }

    public static ValueOperations<String, Object> opsForValue() {
        return redisTemplate.opsForValue();
    }

    public static HashOperations<String, String, Object> opsForHash() {
        return redisTemplate.opsForHash();
    }

    public static ListOperations<String, Object> opsForList() {
        return redisTemplate.opsForList();
    }

    public static SetOperations<String, Object> opsForSet() {
        return redisTemplate.opsForSet();
    }

    public static ZSetOperations<String, Object> opsForZSet() {
        return redisTemplate.opsForZSet();
    }
}

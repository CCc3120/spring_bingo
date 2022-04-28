package com.bingo.spring_bingo.system.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 线程工具类
 *
 * @author bingo
 * @date 2022-04-28 16:06
 */
public class ThreadUtil {

    private static final Logger logger = LoggerFactory.getLogger(ThreadUtil.class);

    /**
     * sleep等待,单位为毫秒
     */
    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
        }
    }

    /**
     * 数据分组处理
     *
     * @param list
     * @param handle
     * @param <T>
     */
    public static <T> boolean groupHandle(List<List<T>> list, ThreadGroupHandle<T> handle) {
        CountDownLatch downLatch = new CountDownLatch(list.size());
        for (List<T> ts : list) {
            ThreadPoolUtil.singleton().getThreadPoolExecutor().execute(() -> {
                handle.groupHandle(ts);
                downLatch.countDown();
            });
        }
        try {
            downLatch.await();
            return true;
        } catch (InterruptedException e) {
            logger.error("数据分组处理异常");
            return false;
        }
    }
}

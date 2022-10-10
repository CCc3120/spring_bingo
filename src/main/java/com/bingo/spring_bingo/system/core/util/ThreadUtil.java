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
     * 数据并行处理
     *
     * @param list
     * @param handle
     * @param <T>
     */
    public static <T> boolean groupHandle(List<T> list, ThreadHandle handle) {
        CountDownLatch downLatch = new CountDownLatch(list.size());
        list.forEach(t -> new Thread(() -> {
            handle.handle(t);
            downLatch.countDown();
        }).start());
        try {
            downLatch.await();
            return true;
        } catch (InterruptedException e) {
            logger.error("数据分组处理异常");
            return false;
        }
    }
}

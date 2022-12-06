package com.bingo.spring_bingo.system.core.util.retry;

import com.bingo.spring_bingo.system.core.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RetryFactory {

    /**
     * 任务重试
     *
     * @param retryService
     * @param times
     * @return
     * @throws Throwable
     */
    public static Object retry(RetryService retryService, int times) throws Throwable {
        try {
            log.info("任务重试剩余次数：{}", times);
            Object exec = retryService.exec();
            log.info("任务执行结果：{}", JsonMapper.getInstance().toJsonString(exec));
            return exec;
        } catch (Throwable e) {
            if (times > 1) {
                return retry(retryService, --times);
            } else {
                log.info("任务重试执行失败");
                throw e;
            }
        }
    }
}

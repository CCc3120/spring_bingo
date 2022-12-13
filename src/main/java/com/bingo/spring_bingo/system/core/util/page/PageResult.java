package com.bingo.spring_bingo.system.core.util.page;

import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName Page
 * @Description 分页数据对象
 * @Author h-bingo
 * @Date 2022-12-13 14:06
 * @Version 1.0
 */
@Data
public class PageResult<T> implements Serializable {

    private List<T> dataList = Collections.emptyList();

    private long count = 0L;

    private long pageSize = BaseSearchModel.PAGE_SIZE;

    private long pageNum = BaseSearchModel.PAGE_NUM;

    private PageResult() {
    }

    private PageResult(List<T> dataList, long count, long pageSize, long pageNum) {
        this.dataList = dataList;
        this.count = count;
        this.pageSize = pageSize;
        this.pageNum = pageNum;
    }

    private PageResult(long pageSize, long pageNum) {
        this.pageSize = pageSize;
        this.pageNum = pageNum;
    }

    public static <T> PageResult<T> of(List<T> dataList, long count, BaseSearchModel model) {
        return new PageResult<>(dataList, count, model.getPageSize(), model.getPageNum());
    }

    public static <T> PageResult<T> of(BaseSearchModel model) {
        return empty(model);
    }

    public static <T> PageResult<T> empty(BaseSearchModel model) {
        return new PageResult<>(model.getPageSize(), model.getPageNum());
    }
}

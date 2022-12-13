package com.bingo.spring_bingo.system.core.util.page;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName BaseSearchModel
 * @Description 查询model基类
 * @Author h-bingo
 * @Date 2022-12-13 14:32
 * @Version 1.0
 */

@Data
public class BaseSearchModel implements Serializable {

    public static final String ASC = "asc";
    public static final String DESC = "desc";
    public static final long PAGE_SIZE = 10;
    public static final long PAGE_NUM = 1;

    /**
     * 搜索内容
     */
    private String searchContent;

    /**
     * 搜索字段
     */
    private String searchProp;

    /**
     * 排序类型 升序 降序
     */
    private String orderType = ASC;

    /**
     * 排序字段
     */
    private String orderProp;

    /**
     * 页大小
     */
    private long pageSize = PAGE_SIZE;

    /**
     * 页码
     */
    private long pageNum = PAGE_NUM;
}

package com.bingo.spring_bingo.system.interfaces;

/**
 * @author bingo
 * @date 2022-03-25 14:13
 */
public interface ISysOrgElement {

    /**
     * @return 名称
     */
    String getFdName();

    /**
     * @return 编号
     */
    String getFdNo();

    /**
     * @return 排序号
     */
    Integer getFdOrder();

    /**
     * @return 是否有效
     */
    String getFdIsAvailable();

    /**
     * @return 备注
     */
    String getFdMemo();
}

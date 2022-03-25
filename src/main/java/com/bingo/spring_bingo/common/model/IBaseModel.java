package com.bingo.spring_bingo.common.model;

/**
 * @author bingo
 * @date 2022-03-25 13:19
 */
public interface IBaseModel {

    String getFdId();

    void setFdId(String fdId);

    Class<?> getModelFromClass();
}

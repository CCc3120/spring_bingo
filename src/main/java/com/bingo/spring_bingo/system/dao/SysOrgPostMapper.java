package com.bingo.spring_bingo.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bingo.spring_bingo.system.model.SysOrgPost;

/**
 * @author bingo
 * @date 2022-03-31 20:10
 */
public interface SysOrgPostMapper extends BaseMapper<SysOrgPost> {

    /**
     * 主键查询
     *
     * @param primaryKey
     * @return
     */
    SysOrgPost findByPrimaryKey(String primaryKey);
}

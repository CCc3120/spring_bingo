package com.bingo.spring_bingo.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bingo.spring_bingo.system.model.SysOrgOrg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author bingo
 * @date 2022-03-31 19:49
 */
@Mapper
public interface SysOrgOrgMapper extends BaseMapper<SysOrgOrg> {

    /**
     * 主键查询
     *
     * @param primaryKey
     * @return
     */
    SysOrgOrg findByPrimaryKey(String primaryKey);

    /**
     * 查询子机构列表
     *
     * @param primaryKey
     * @return
     */
    List<SysOrgOrg> findChildren(String primaryKey);
}

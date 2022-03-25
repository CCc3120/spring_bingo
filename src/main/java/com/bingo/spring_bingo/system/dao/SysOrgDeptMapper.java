package com.bingo.spring_bingo.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bingo.spring_bingo.system.model.SysOrgDept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author bingo
 * @date 2022-03-25 15:46
 */
@Mapper
public interface SysOrgDeptMapper extends BaseMapper<SysOrgDept> {

    SysOrgDept findByPrimaryKey(String primaryKey);

    List<SysOrgDept> findChildren(String primaryKey);
}

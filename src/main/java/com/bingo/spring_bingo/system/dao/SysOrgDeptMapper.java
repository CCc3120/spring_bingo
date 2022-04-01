package com.bingo.spring_bingo.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bingo.spring_bingo.system.model.SysOrgDept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author bingo
 * @date 2022-03-25 15:46
 */
@Mapper
public interface SysOrgDeptMapper extends BaseMapper<SysOrgDept> {
    /**
     * 主键查询
     *
     * @param primaryKey
     * @return
     */
    SysOrgDept findByPrimaryKey(String primaryKey);

    /**
     * 查询子部门列表
     *
     * @param primaryKey
     * @return
     */
    List<SysOrgDept> findChildren(String primaryKey);

    /**
     * 查询机构下部门
     *
     * @param fdOrgId
     * @return
     */
    List<SysOrgDept> findByOrgId(String fdOrgId);

    IPage<SysOrgDept> findPageList(IPage<SysOrgDept> page);
}

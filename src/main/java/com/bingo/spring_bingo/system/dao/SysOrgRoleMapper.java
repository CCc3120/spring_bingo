package com.bingo.spring_bingo.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bingo.spring_bingo.system.model.SysOrgRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author bingo
 * @date 2022-04-02 17:37
 */
@Mapper
public interface SysOrgRoleMapper extends BaseMapper<SysOrgRole> {

    /**
     * 主键查询
     *
     * @param primaryKey
     * @return
     */
    SysOrgRole findByPrimaryKey(String primaryKey);

    /**
     * 查询用户拥有的所有角色
     *
     * @param fdUserId
     * @return
     */
    List<SysOrgRole> findUserRole(String fdUserId);
}

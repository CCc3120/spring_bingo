package com.bingo.spring_bingo.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bingo.spring_bingo.system.model.SysOrgMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author bingo
 * @date 2022-03-31 19:59
 */
@Mapper
public interface SysOrgMenuMapper extends BaseMapper<SysOrgMenu> {

    /**
     * 主键查询
     *
     * @param primaryKey
     * @return
     */
    SysOrgMenu findByPrimaryKey(String primaryKey);

    /**
     * 查询子级权限
     *
     * @param primaryKey
     * @return
     */
    List<SysOrgMenu> findChildren(String primaryKey);

    /**
     * 查询角色下的所有权限
     *
     * @param fdRoleId
     * @return
     */
    List<SysOrgMenu> findRoleMenu(String fdRoleId);

    /**
     * 查询用户所有权限标识
     *
     * @param fdUserId
     * @return
     */
    List<String> findMenuAuthMarkByUserId(String fdUserId);
}

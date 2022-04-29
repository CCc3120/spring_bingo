package com.bingo.spring_bingo.system.service;

import com.bingo.spring_bingo.system.model.SysOrgUser;

import java.util.Set;

/**
 * @author bingo
 * @date 2022-04-29 14:43
 */
public interface ISysOrgMenuService {

    /**
     * 查询用户所有权限
     *
     * @param fdId
     * @return
     */
    Set<String> findMenuAuthMarkByUser(SysOrgUser sysOrgUser);
}

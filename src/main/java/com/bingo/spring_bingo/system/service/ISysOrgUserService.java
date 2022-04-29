package com.bingo.spring_bingo.system.service;

import com.bingo.spring_bingo.system.model.SysOrgUser;

/**
 * @author bingo
 * @date 2022-03-25 10:08
 */
public interface ISysOrgUserService {

    /**
     * 根据用户名查询
     *
     * @param loginName
     * @return
     */
    SysOrgUser findByLoginName(String loginName);

}

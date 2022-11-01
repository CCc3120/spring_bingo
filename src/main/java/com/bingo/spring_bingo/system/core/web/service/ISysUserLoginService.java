package com.bingo.spring_bingo.system.core.web.service;

import com.bingo.spring_bingo.system.core.util.ProcessResult;

/**
 * @author bingo
 * @date 2022-04-09 17:29
 */
public interface ISysUserLoginService {

    /**
     * 系统登录
     *
     * @return
     */
    ProcessResult<String> doLogin(String username, String password, String code, String uuid);
}

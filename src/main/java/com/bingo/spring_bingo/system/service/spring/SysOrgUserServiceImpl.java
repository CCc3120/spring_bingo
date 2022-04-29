package com.bingo.spring_bingo.system.service.spring;

import com.bingo.spring_bingo.system.dao.SysOrgUserMapper;
import com.bingo.spring_bingo.system.model.SysOrgUser;
import com.bingo.spring_bingo.system.service.ISysOrgUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author bingo
 * @date 2022-03-25 10:09
 */
@Service
public class SysOrgUserServiceImpl implements ISysOrgUserService {

    @Autowired
    private SysOrgUserMapper sysOrgUserMapper;

    @Override
    public SysOrgUser findByLoginName(String loginName) {
        return sysOrgUserMapper.findByLoginName(loginName);
    }
}

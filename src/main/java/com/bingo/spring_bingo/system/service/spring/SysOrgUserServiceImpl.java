package com.bingo.spring_bingo.system.service.spring;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.spring_bingo.system.dao.SysOrgUserMapper;
import com.bingo.spring_bingo.system.model.SysOrgUser;
import com.bingo.spring_bingo.system.service.ISysOrgUserService;
import org.springframework.stereotype.Service;

/**
 * @author bingo
 * @date 2022-03-25 10:09
 */
@Service
public class SysOrgUserServiceImpl extends ServiceImpl<SysOrgUserMapper, SysOrgUser> implements ISysOrgUserService {

}

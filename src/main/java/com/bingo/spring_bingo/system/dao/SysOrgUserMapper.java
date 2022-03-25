package com.bingo.spring_bingo.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bingo.spring_bingo.system.model.SysOrgUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author bingo
 * @date 2022-03-25 10:07
 */
@Mapper
public interface SysOrgUserMapper extends BaseMapper<SysOrgUser> {

    SysOrgUser findByPrimaryKey(String primaryKey);

}

package com.bingo.spring_bingo.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bingo.spring_bingo.system.model.SysOrgUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author bingo
 * @date 2022-03-25 10:07
 */
@Mapper
public interface SysOrgUserMapper extends BaseMapper<SysOrgUser> {

    /**
     * 主键查询
     *
     * @param primaryKey
     * @return
     */
    SysOrgUser findByPrimaryKey(String primaryKey);

    /**
     * 查询部门下的员工
     *
     * @param fdDeptId
     * @return
     */
    List<SysOrgUser> findDeptUser(String fdDeptId);

    /**
     * 根据用户名查询
     *
     * @param loginName
     * @return
     */
    SysOrgUser findByLoginName(String loginName);
}

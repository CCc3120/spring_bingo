package com.bingo.spring_bingo.system.model;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author bingo
 * @date 2022-03-25 15:04
 */
@TableName(value = "sys_org_role")
public class SysOrgRole extends SysOrgElement{

    public SysOrgRole() {
        super();
        setFdOrgType(ORG_TYPE_ROLE);
    }

    @Override
    public Class<?> getModelFromClass() {
        return SysOrgRole.class;
    }
}

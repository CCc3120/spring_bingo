package com.bingo.spring_bingo.system.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bingo.spring_bingo.system.interfaces.ISysOrgOrg;

/**
 * 机构
 *
 * @author bingo
 * @date 2022-03-25 15:04
 */
@TableName(value = "sys_org_org")
public class SysOrgOrg extends SysOrgElement implements ISysOrgOrg {

    public SysOrgOrg() {
        super();
        setFdOrgType(ORG_TYPE_ORG);
    }

    @Override
    public Class<?> getModelFromClass() {
        return SysOrgOrg.class;
    }
}

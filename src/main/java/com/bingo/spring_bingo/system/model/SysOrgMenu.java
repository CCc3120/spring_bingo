package com.bingo.spring_bingo.system.model;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 功能菜单
 *
 * @author bingo
 * @date 2022-03-25 15:05
 */
@TableName(value = "sys_org_menu")
public class SysOrgMenu extends SysOrgElement {

    public SysOrgMenu() {
        super();
        setFdOrgType(ORG_TYPE_MENU);
    }

    @Override
    public Class<?> getModelFromClass() {
        return SysOrgMenu.class;
    }
}

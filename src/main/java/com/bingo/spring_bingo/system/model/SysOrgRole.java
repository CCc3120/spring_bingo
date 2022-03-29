package com.bingo.spring_bingo.system.model;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

/**
 * @author bingo
 * @date 2022-03-25 15:04
 */
@TableName(value = "sys_org_role")
public class SysOrgRole extends SysOrgElement {

    public SysOrgRole() {
        super();
    }

    @Override
    public Class<?> getModelFromClass() {
        return SysOrgRole.class;
    }

    /**
     * 角色下的权限菜单
     */
    private List<SysOrgMenu> fdMenu;

    public List<SysOrgMenu> getFdMenu() {
        return fdMenu;
    }

    public void setFdMenu(List<SysOrgMenu> fdMenu) {
        this.fdMenu = fdMenu;
    }
}

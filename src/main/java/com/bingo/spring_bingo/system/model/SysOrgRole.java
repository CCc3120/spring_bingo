package com.bingo.spring_bingo.system.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
     * 角色权限标识
     */
    private String fdAuthMark;

    public String getFdAuthMark() {
        return fdAuthMark;
    }

    public void setFdAuthMark(String fdAuthMark) {
        this.fdAuthMark = fdAuthMark;
    }

    /**
     * 角色下的权限菜单
     */
    @JsonIgnoreProperties(clazz = SysOrgMenu.class, isShow = true, value = {"fdName"})
    private List<SysOrgMenu> fdRoleMenu;

    public List<SysOrgMenu> getFdRoleMenu() {
        return fdRoleMenu;
    }

    public void setFdRoleMenu(List<SysOrgMenu> fdRoleMenu) {
        this.fdRoleMenu = fdRoleMenu;
    }
}

package com.bingo.spring_bingo.system.model;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

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
    }

    @Override
    public Class<?> getModelFromClass() {
        return SysOrgMenu.class;
    }

    /**
     * 菜单类型  按钮/菜单
     */
    private String fdMenuType;

    public String getFdMenuType() {
        return fdMenuType;
    }

    public void setFdMenuType(String fdMenuType) {
        this.fdMenuType = fdMenuType;
    }

    /**
     * 菜单权限标识
     */
    private String fdAuthMark;

    public String getFdAuthMark() {
        return fdAuthMark;
    }

    public void setFdAuthMark(String fdAuthMark) {
        this.fdAuthMark = fdAuthMark;
    }

    /**
     * 菜单父级
     */
    private SysOrgMenu fdParent;

    public SysOrgMenu getFdParent() {
        return fdParent;
    }

    public void setFdParent(SysOrgMenu fdParent) {
        this.fdParent = fdParent;
    }

    /**
     * 菜单子集
     */
    private List<SysOrgMenu> fdChildren;

    public List<SysOrgMenu> getFdChildren() {
        return fdChildren;
    }

    public void setFdChildren(List<SysOrgMenu> fdChildren) {
        this.fdChildren = fdChildren;
    }
}

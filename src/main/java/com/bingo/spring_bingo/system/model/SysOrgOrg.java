package com.bingo.spring_bingo.system.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bingo.spring_bingo.system.interfaces.ISysOrgOrg;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

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
    }

    @Override
    public Class<?> getModelFromClass() {
        return SysOrgOrg.class;
    }

    /**
     * 机构领导
     */
    @JsonIgnoreProperties(clazz = SysOrgUser.class, isShow = true, value = {"fdName"})
    private SysOrgUser fdThisLeader;

    public SysOrgUser getFdThisLeader() {
        return fdThisLeader;
    }

    public void setFdThisLeader(SysOrgUser fdThisLeader) {
        this.fdThisLeader = fdThisLeader;
    }

    /**
     * 父级机构
     */
    @JsonIgnoreProperties(clazz = SysOrgOrg.class, isShow = true, value = {"fdName"})
    private SysOrgOrg fdParent;

    public SysOrgOrg getFdParent() {
        return fdParent;
    }

    public void setFdParent(SysOrgOrg fdParent) {
        this.fdParent = fdParent;
    }

    /**
     * 子机构列表
     */
    @JsonIgnoreProperties(clazz = SysOrgOrg.class, isShow = true, value = {"fdName"})
    private List<SysOrgOrg> fdChildren;

    public List<SysOrgOrg> getFdChildren() {
        return fdChildren;
    }

    public void setFdChildren(List<SysOrgOrg> fdChildren) {
        this.fdChildren = fdChildren;
    }

    /**
     * 机构部门
     */
    @JsonIgnoreProperties(clazz = SysOrgDept.class, isShow = true, value = {"fdName"})
    private List<SysOrgDept> fdDept;

    public List<SysOrgDept> getFdDept() {
        return fdDept;
    }

    public void setFdDept(List<SysOrgDept> fdDept) {
        this.fdDept = fdDept;
    }
}

package com.bingo.spring_bingo.system.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bingo.spring_bingo.system.interfaces.ISysOrgDept;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * 部门
 *
 * @author bingo
 * @date 2022-03-25 15:02
 */
@TableName(value = "sys_org_dept")
public class SysOrgDept extends SysOrgElement implements ISysOrgDept {

    public SysOrgDept() {
        super();
    }

    @Override
    public Class<?> getModelFromClass() {
        return SysOrgDept.class;
    }

    /**
     * 当前部门领导
     */
    @TableField(exist = false)
    @JsonIgnoreProperties(clazz = SysOrgUser.class, isShow = true, value = {"fdName"})
    private SysOrgUser fdThisLeader;

    public SysOrgUser getFdThisLeader() {
        return fdThisLeader;
    }

    public void setFdThisLeader(SysOrgUser fdThisLeader) {
        this.fdThisLeader = fdThisLeader;
    }

    @TableField(exist = false)
    @JsonIgnoreProperties(clazz = SysOrgDept.class, isShow = true, value = {"fdName"})
    private SysOrgDept fdParent;

    public SysOrgDept getFdParent() {
        return fdParent;
    }

    public void setFdParent(SysOrgDept fdParent) {
        this.fdParent = fdParent;
    }

    /**
     * 子部门
     */
    @TableField(exist = false)
    @JsonIgnoreProperties(clazz = SysOrgDept.class, isShow = true, value = {"fdName"})
    private List<SysOrgDept> fdChildren;

    public List<SysOrgDept> getFdChildren() {
        return fdChildren;
    }

    public void setFdChildren(List<SysOrgDept> fdChildren) {
        this.fdChildren = fdChildren;
    }

    /**
     * 所在机构
     */
    @TableField(exist = false)
    @JsonIgnoreProperties(clazz = SysOrgOrg.class, isShow = true, value = {"fdName"})
    private SysOrgOrg fdOrg;

    public SysOrgOrg getFdOrg() {
        return fdOrg;
    }

    public void setFdOrg(SysOrgOrg fdOrg) {
        this.fdOrg = fdOrg;
    }
}

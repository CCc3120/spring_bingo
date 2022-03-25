package com.bingo.spring_bingo.system.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bingo.spring_bingo.system.interfaces.ISysOrgDept;

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
        setFdOrgType(ORG_TYPE_DEPT);
    }

    @Override
    public Class<?> getModelFromClass() {
        return SysOrgDept.class;
    }

    /**
     * 当前部门领导
     */
    @TableField(exist = false)
    private SysOrgUser fdThisLeader;

    public SysOrgUser getFdThisLeader() {
        return fdThisLeader;
    }

    public void setFdThisLeader(SysOrgUser fdThisLeader) {
        this.fdThisLeader = fdThisLeader;
    }

    @TableField(exist = false)
    public SysOrgDept fdParent;

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
    public List<SysOrgDept> fdChildren;

    public List<SysOrgDept> getFdChildren() {
        return fdChildren;
    }

    public void setFdChildren(List<SysOrgDept> fdChildren) {
        this.fdChildren = fdChildren;
    }

}

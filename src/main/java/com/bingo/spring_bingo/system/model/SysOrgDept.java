package com.bingo.spring_bingo.system.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bingo.spring_bingo.system.interfaces.ISysOrgDept;
import com.bingo.spring_bingo.util.ObjectUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
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
    @JsonIgnoreProperties(clazz = SysOrgUser.class, isShow = true, value = {"fdName"})
    private SysOrgUser fdThisLeader;

    public SysOrgUser getFdThisLeader() {
        return fdThisLeader;
    }

    public void setFdThisLeader(SysOrgUser fdThisLeader) {
        this.fdThisLeader = fdThisLeader;
    }

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
    @JsonIgnoreProperties(clazz = SysOrgOrg.class, isShow = true, value = {"fdName"})
    private SysOrgOrg fdDeptOrg;

    public SysOrgOrg getFdDeptOrg() {
        return fdDeptOrg;
    }

    public void setFdDeptOrg(SysOrgOrg fdDeptOrg) {
        this.fdDeptOrg = fdDeptOrg;
    }

    /**
     * 部门员工(当前部门员工，不含子部门员工)
     */
    @JsonIgnoreProperties(clazz = SysOrgUser.class, isShow = true, value = {"fdName"})
    private List<SysOrgUser> fdDeptUser;

    public List<SysOrgUser> getFdDeptUser() {
        if (fdDeptUser == null) {
            fdDeptUser = new ArrayList<>();
        }
        // 判断部门领导是否存在,不存在则添加进去
        if (!ObjectUtil.isNull(fdThisLeader)) {
            for (SysOrgUser user : fdDeptUser) {
                if (user.getFdId().equals(fdThisLeader.getFdId())) {
                    return fdDeptUser;
                }
            }
            fdDeptUser.add(fdThisLeader);
        }
        return fdDeptUser;
    }

    public void setFdDeptUser(List<SysOrgUser> fdDeptUser) {
        this.fdDeptUser = fdDeptUser;
    }
}

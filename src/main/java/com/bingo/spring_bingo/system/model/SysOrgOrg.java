package com.bingo.spring_bingo.system.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bingo.spring_bingo.system.interfaces.ISysOrgOrg;
import com.bingo.spring_bingo.util.ArrayUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
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
     * 机构下的部门
     */
    @JsonIgnoreProperties(clazz = SysOrgDept.class, isShow = true, value = {"fdName"})
    private List<SysOrgDept> fdOrgDept;

    public List<SysOrgDept> getFdOrgDept() {
        return fdOrgDept;
    }

    public void setFdOrgDept(List<SysOrgDept> fdOrgDept) {
        this.fdOrgDept = fdOrgDept;
    }

    @Deprecated
    private List<SysOrgUser> getOrgAllEmployee(SysOrgOrg orgOrg) {
        List<SysOrgUser> rtnVal = new ArrayList<>();
        if (!ArrayUtil.isEmpty(orgOrg.getFdChildren())) {
            for (SysOrgOrg fdChild : orgOrg.getFdChildren()) {
                rtnVal.addAll(getOrgAllEmployee(fdChild));
            }
        }
        if (!ArrayUtil.isEmpty(orgOrg.getFdOrgDept())) {
            for (SysOrgDept dept : orgOrg.getFdOrgDept()) {
                List<SysOrgUser> userList = dept.getFdDeptUser();
            }
        }
        return rtnVal;
    }


}

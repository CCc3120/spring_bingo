package com.bingo.spring_bingo.system.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bingo.spring_bingo.common.constant.SysModelEnum;
import com.bingo.spring_bingo.system.interfaces.ISysOrgUser;
import com.bingo.spring_bingo.util.StringUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

/**
 * 用户
 *
 * @author bingo
 * @date 2022-03-24 16:25
 */
@TableName(value = "sys_org_user")
public class SysOrgUser extends SysOrgElement implements ISysOrgUser {

    public SysOrgUser() {
        super();
    }

    @Override
    public Class<?> getModelFromClass() {
        return SysOrgUser.class;
    }

    /**
     * 邮箱
     */
    @TableField(value = "fd_email")
    private String fdEmail;

    @Override
    public String getFdEmail() {
        return fdEmail;
    }

    public void setFdEmail(String fdEmail) {
        this.fdEmail = fdEmail;
    }

    /**
     * 手机号
     */
    @TableField(value = "fd_mobile_no")
    private String fdMobileNo;

    @Override
    public String getFdMobileNo() {
        return fdMobileNo;
    }

    public void setFdMobileNo(String fdMobileNo) {
        this.fdMobileNo = fdMobileNo;
    }

    /**
     * 登录名
     */
    @TableField(value = "fd_login_name")
    private String fdLoginName;

    @Override
    public String getFdLoginName() {
        return fdLoginName;
    }

    public void setFdLoginName(String fdLoginName) {
        this.fdLoginName = fdLoginName;
    }

    /**
     * 昵称/别名
     */
    @TableField(value = "fd_nick_name")
    private String fdNickName;

    public String getFdNickName() {
        return fdNickName;
    }

    public void setFdNickName(String fdNickName) {
        this.fdNickName = fdNickName;
    }

    /**
     * 办公电话
     */
    @TableField(value = "fd_work_phone")
    private String fdWorkPhone;

    public String getFdWorkPhone() {
        return fdWorkPhone;
    }

    public void setFdWorkPhone(String fdWorkPhone) {
        this.fdWorkPhone = fdWorkPhone;
    }

    /**
     * 微信号
     */
    @TableField(value = "fd_wechat_no")
    private String fdWechatNo;

    public String getFdWechatNo() {
        return fdWechatNo;
    }

    public void setFdWechatNo(String fdWechatNo) {
        this.fdWechatNo = fdWechatNo;
    }

    /**
     * 性别
     */
    @TableField(value = "fd_sex")
    private String fdSex;

    public String getFdSex() {
        return fdSex;
    }

    public void setFdSex(String fdSex) {
        this.fdSex = fdSex;
    }

    /**
     * 密码
     */
    @TableField(value = "fd_password")
    private String fdPassword;

    public String getFdPassword() {
        return fdPassword;
    }

    public void setFdPassword(String fdPassword) {
        this.fdPassword = fdPassword;
    }

    /**
     * 上一次修改密码时间
     */
    @TableField(value = "fd_last_change_pwd")
    private Date fdLastChangePwd;

    public Date getFdLastChangePwd() {
        return fdLastChangePwd;
    }

    public void setFdLastChangePwd(Date fdLastChangePwd) {
        this.fdLastChangePwd = fdLastChangePwd;
    }

    /**
     * 出生年月
     */
    @TableField(value = "fd_birthday")
    private Date fdBirthday;

    public Date getFdBirthday() {
        return fdBirthday;
    }

    public void setFdBirthday(Date fdBirthday) {
        this.fdBirthday = fdBirthday;
    }

    /**
     * 是否锁定
     */
    @TableField(value = "fd_is_lock")
    private String fdIsLock;

    public String getFdIsLock() {
        if (StringUtil.isNull(fdIsLock)) {
            fdIsLock = SysModelEnum.BOOLEAN_NO.getCode();
        }
        return fdIsLock;
    }

    public void setFdIsLock(String fdIsLock) {
        this.fdIsLock = fdIsLock;
    }

    /**
     * 账户锁定时间
     */
    @TableField(value = "fd_lock_time")
    private Date fdLockTime;

    public Date getFdLockTime() {
        return fdLockTime;
    }

    public void setFdLockTime(Date fdLockTime) {
        this.fdLockTime = fdLockTime;
    }

    /**
     * 用户拥有的角色
     */
    @JsonIgnoreProperties(clazz = SysOrgRole.class, isShow = true, value = {"fdName"})
    private List<SysOrgRole> fdUserRole;

    public List<SysOrgRole> getFdUserRole() {
        return fdUserRole;
    }

    public void setFdUserRole(List<SysOrgRole> fdUserRole) {
        this.fdUserRole = fdUserRole;
    }

    /**
     * 用户所在部门
     */
    @JsonIgnoreProperties(clazz = SysOrgDept.class, isShow = true, value = {"fdName"})
    private SysOrgDept fdUserDept;

    public SysOrgDept getFdUserDept() {
        return fdUserDept;
    }

    public void setFdUserDept(SysOrgDept fdUserDept) {
        this.fdUserDept = fdUserDept;
    }

    /**
     * 用户岗位
     */
    @JsonIgnoreProperties(clazz = SysOrgPost.class, isShow = true, value = {"fdName"})
    private List<SysOrgPost> fdUserPost;

    public List<SysOrgPost> getFdUserPost() {
        return fdUserPost;
    }

    public void setFdUserPost(List<SysOrgPost> fdUserPost) {
        this.fdUserPost = fdUserPost;
    }
}

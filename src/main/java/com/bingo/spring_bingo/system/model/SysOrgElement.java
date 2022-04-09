package com.bingo.spring_bingo.system.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.bingo.spring_bingo.common.constant.SysModelEnum;
import com.bingo.spring_bingo.system.core.model.BaseModel;
import com.bingo.spring_bingo.system.interfaces.ISysOrgElement;
import com.bingo.spring_bingo.util.ObjectUtil;
import com.bingo.spring_bingo.util.StringUtil;

import java.util.Date;

/**
 * 组织元素
 *
 * @author bingo
 * @date 2022-03-25 14:17
 */
public abstract class SysOrgElement extends BaseModel implements ISysOrgElement {

    /**
     * 名称
     */
    @TableField(value = "fd_name")
    private String fdName;

    @Override
    public String getFdName() {
        return fdName;
    }

    public void setFdName(String fdName) {
        this.fdName = fdName;
    }

    /**
     * 拼音名
     */
    @TableField(value = "fd_name_pin_yin")
    private String fdNamePinYin;

    public String getFdNamePinYin() {
        return fdNamePinYin;
    }

    public void setFdNamePinYin(String fdNamePinYin) {
        this.fdNamePinYin = fdNamePinYin;
    }

    /**
     * 编号
     */
    @TableField(value = "fd_no")
    private String fdNo;

    @Override
    public String getFdNo() {
        return fdNo;
    }

    public void setFdNo(String fdNo) {
        this.fdNo = fdNo;
    }

    /**
     * 排序号
     */
    @TableField(value = "fd_order")
    private Integer fdOrder;

    @Override
    public Integer getFdOrder() {
        return fdOrder;
    }

    public void setFdOrder(Integer fdOrder) {
        this.fdOrder = fdOrder;
    }

    /**
     * 是否可用
     */
    @TableField(value = "fd_is_available")
    private String fdIsAvailable;

    @Override
    public String getFdIsAvailable() {
        if (StringUtil.isNull(fdIsAvailable)) {
            fdIsAvailable = SysModelEnum.BOOLEAN_YES.getCode();
        }
        return fdIsAvailable;
    }

    public void setFdIsAvailable(String fdIsAvailable) {
        this.fdIsAvailable = fdIsAvailable;
    }

    /**
     * 备注
     */
    @TableField(value = "fd_memo")
    private String fdMemo;

    @Override
    public String getFdMemo() {
        return fdMemo;
    }

    public void setFdMemo(String fdMemo) {
        this.fdMemo = fdMemo;
    }

    /**
     * 创建时间
     */
    @TableField(value = "fd_create_time")
    private Date fdCreateTime = new Date();

    public Date getFdCreateTime() {
        return fdCreateTime;
    }

    public void setFdCreateTime(Date fdCreateTime) {
        this.fdCreateTime = fdCreateTime;
    }

    /**
     * 修改时间
     */
    @TableField(value = "fd_alter_time", fill = FieldFill.UPDATE)
    private Date fdAlterTime = new Date();

    public Date getFdAlterTime() {
        return fdAlterTime;
    }

    public void setFdAlterTime(Date fdAlterTime) {
        this.fdAlterTime = fdAlterTime;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (!(object instanceof SysOrgElement)) {
            return false;
        }
        BaseModel objModel = (BaseModel) object;
        return ObjectUtil.equals(objModel.getFdId(), this.getFdId(), false);
    }
}

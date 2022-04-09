package com.bingo.spring_bingo.system.core.interfaces;

import com.bingo.spring_bingo.system.model.SysOrgUser;

import java.util.Date;

/**
 * 软删除接口
 *
 * @author bingo
 * @date 2022-03-25 13:11
 */
public interface IBaseRecycle extends IBaseModel {
    // private String fdDeleteFlag;                 // 软删除
    // private Date fdDeleteTime;                   // 软删除时间
    // private SysOrgUser fdDeleteBy;                   // 删除人

    String getFdDeleteFlag();

    void setFdDeleteFlag(String fdDeleteFlag);

    Date getFdDeleteTime();

    void setFdDeleteTime(Date fdDeleteTime);

    SysOrgUser getFdDeleteBy();

    void setFdDeleteBy(SysOrgUser fdDeleteBy);
}


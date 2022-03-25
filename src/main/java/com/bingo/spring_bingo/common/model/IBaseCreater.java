package com.bingo.spring_bingo.common.model;

import com.bingo.spring_bingo.system.model.SysOrgUser;

import java.util.Date;

/**
 * 创建者  创建时间 接口
 *
 * @author bingo
 * @date 2022-03-25 13:03
 */
public interface IBaseCreater extends IBaseModel {

    // private Date fdCreateTime;                       // 创建时间
    // private SysOrgUser fdCreator;                    // 创建人

    Date getFdCreateTime();

    void setFdCreateTime(Date fdCreateTime);

    SysOrgUser getFdCreator();

    void setFdCreator(SysOrgUser fdCreator);
}

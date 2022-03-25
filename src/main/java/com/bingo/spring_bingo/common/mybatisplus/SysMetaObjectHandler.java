package com.bingo.spring_bingo.common.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.bingo.spring_bingo.system.model.SysOrgElement;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自动填充
 *
 * @author bingo
 * @date 2022-03-24 19:20
 */
@Component
public class SysMetaObjectHandler implements MetaObjectHandler {


    // ↓↓↓↓↓↓↓↓↓↓↓组织元素↓↓↓↓↓↓↓↓↓↓↓
    // 修改时间
    private static final String FD_ALTER_TIME = "fdAlterTime";


    // ↑↑↑↑↑↑↑↑↑↑↑组织元素↑↑↑↑↑↑↑↑↑↑↑

    // 自动填充字段
    private static final String FD_UPDATE_TIME = "fdUpdateTime";



    @Override
    public void insertFill(MetaObject metaObject) {
        // 创建人

        // 修改人

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Date now = new Date();
        // ↓↓↓↓↓↓↓↓↓↓↓组织元素↓↓↓↓↓↓↓↓↓↓↓
        // 修改时间
        if (metaObject.getOriginalObject() instanceof SysOrgElement) {
            this.strictUpdateFill(metaObject, FD_ALTER_TIME, Date.class, now);
        }


        // ↑↑↑↑↑↑↑↑↑↑↑组织元素↑↑↑↑↑↑↑↑↑↑↑


        // 修改人
    }
}

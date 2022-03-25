package com.bingo.spring_bingo.system.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bingo.spring_bingo.system.interfaces.ISysOrgPost;

/**
 * @author bingo
 * @date 2022-03-25 15:03
 */
@TableName(value = "sys_org_post")
public class SysOrgPost extends SysOrgElement implements ISysOrgPost {

    public SysOrgPost() {
        super();
        setFdOrgType(new Integer(ORG_TYPE_POST));
    }

    @Override
    public Class<?> getModelFromClass() {
        return SysOrgPost.class;
    }
}

package com.bingo.spring_bingo.system.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bingo.spring_bingo.system.interfaces.ISysOrgPost;

/**
 * 岗位
 *
 * @author bingo
 * @date 2022-03-25 15:03
 */
@TableName(value = "sys_org_post")
public class SysOrgPost extends SysOrgElement implements ISysOrgPost {

    public SysOrgPost() {
        super();
    }

    @Override
    public Class<?> getModelFromClass() {
        return SysOrgPost.class;
    }
}

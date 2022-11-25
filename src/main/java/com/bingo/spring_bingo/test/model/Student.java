package com.bingo.spring_bingo.test.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @author bingo
 * @date 2022-03-23 14:54
 */
@Getter
@Setter
@Document(collection = "mongo_student")
@TableName(value = "student")
public class Student {
    @TableId(type = IdType.ASSIGN_UUID)
    private String fdId;

    @TableField
    private String fdName;

    @TableField
    private Date fdBirthday;

    @Override
    public Class<?> getModelFromClass() {
        return Student.class;
    }
}

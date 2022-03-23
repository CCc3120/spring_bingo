package com.bingo.spring_bingo.test.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bingo.spring_bingo.test.model.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author bingo
 * @date 2022-03-23 14:55
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    int add(Student student);
}

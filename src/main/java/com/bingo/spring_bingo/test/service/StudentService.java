package com.bingo.spring_bingo.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.spring_bingo.test.model.Student;

/**
 * @author bingo
 * @date 2022-03-23 14:57
 */
public interface StudentService extends IService<Student> {


    int add(Student student);
}

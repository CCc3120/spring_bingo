package com.bingo.spring_bingo.test.service.spring;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.spring_bingo.test.dao.StudentMapper;
import com.bingo.spring_bingo.test.model.Student;
import com.bingo.spring_bingo.test.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * @author bingo
 * @date 2022-03-23 14:58
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Override
    public int add(Student student) {
       return baseMapper.add(student);
    }
}

package com.bingo.spring_bingo;

import com.bingo.spring_bingo.test.model.Student;
import com.bingo.spring_bingo.test.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class SpringBingoApplicationTests {

    @Autowired
    private StudentService studentService;

    @Test
    void contextLoads() {
        Student s1 = new Student();
        s1.setFdName("张三");
        s1.setFdBirthday(new Date());

        studentService.save(s1);

        Student s2 = new Student();
        s2.setFdName("李四");
        s2.setFdBirthday(new Date());

        studentService.add(s2);
    }

}

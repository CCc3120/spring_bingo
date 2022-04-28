package com.bingo.spring_bingo.test.controller;

import com.bingo.spring_bingo.system.core.response.AjaxResult;
import com.bingo.spring_bingo.system.core.response.AjaxResultFactory;
import com.bingo.spring_bingo.system.core.util.RedisUtil;
import com.bingo.spring_bingo.test.model.Student;
import com.bingo.spring_bingo.util.IDGenerator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author bingo
 * @date 2022-03-24 11:55
 */
@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public AjaxResult test() {
        String key = RedisUtil.getCacheKey("user_token");

        Student student = new Student();
        student.setFdName("张三");
        student.setFdId(IDGenerator.generateID());
        student.setFdBirthday(new Date());

        RedisUtil.setCacheObject(key, student, 60 * 60 * 24);

        Student student1 = RedisUtil.getCacheObject(key);

        return AjaxResultFactory.success(student1);
    }
}

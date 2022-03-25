package com.bingo.spring_bingo.test.controller;

import com.bingo.spring_bingo.util.response.AjaxResult;
import com.bingo.spring_bingo.util.response.AjaxResultFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bingo
 * @date 2022-03-24 11:55
 */
@RestController
@RequestMapping(value = "/")
public class StudentController {

    public AjaxResult test() {


        return AjaxResultFactory.build().get();
    }
}

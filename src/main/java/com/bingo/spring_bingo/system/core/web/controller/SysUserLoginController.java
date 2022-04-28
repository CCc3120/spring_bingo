package com.bingo.spring_bingo.system.core.web.controller;

import com.bingo.spring_bingo.system.core.response.AjaxResult;
import com.bingo.spring_bingo.system.core.web.form.SysLoginForm;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bingo
 * @date 2022-04-11 14:29
 */
@RestController
public class SysUserLoginController extends BaseController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public AjaxResult login(@RequestBody SysLoginForm form) {

        return success();
    }
}

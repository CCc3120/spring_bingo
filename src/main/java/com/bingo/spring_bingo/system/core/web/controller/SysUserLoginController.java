package com.bingo.spring_bingo.system.core.web.controller;

import com.bingo.spring_bingo.system.core.response.AjaxResult;
import com.bingo.spring_bingo.system.core.web.dto.SysLoginDto;
import com.bingo.spring_bingo.system.core.web.service.ISysUserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bingo
 * @date 2022-04-11 14:29
 */
@RestController
@RequestMapping(value = "/sys")
public class SysUserLoginController extends BaseController {

    @Autowired
    private ISysUserLoginService sysUserLoginService;

    // @RequestMapping(value = "/login", method = RequestMethod.POST)
    public AjaxResult login(@RequestBody SysLoginDto dto) {
        Map<String, String> map = new HashMap<>();
        String token = sysUserLoginService.doLogin(dto.getUsername(), dto.getPassword(), dto.getCode(),
                dto.getUuid());
        map.put("token", token);
        return success(map);
    }
}

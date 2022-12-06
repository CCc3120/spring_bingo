package com.bingo.spring_bingo.system.core.web.controller;

import com.bingo.spring_bingo.system.core.response.AjaxResult;
import com.bingo.spring_bingo.system.core.util.ProcessResult;
import com.bingo.spring_bingo.system.core.web.dto.SysLoginDto;
import com.bingo.spring_bingo.system.core.web.service.ISysUserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义的登陆接口，手动调用security方法，需配置该登陆接口放行
 * <p>
 * JwtLoginSuccessHandler类中有使用spring security的登陆方式，选其一
 *
 * @author bingo
 * @date 2022-04-11 14:29
 */
@RestController
@RequestMapping(value = "/sys")
public class SysUserLoginController extends BaseController {

    @Autowired
    private ISysUserLoginService sysUserLoginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public AjaxResult login(@RequestBody SysLoginDto dto) {
        Map<String, String> map = new HashMap<>();
        ProcessResult<String> result = sysUserLoginService.doLogin(dto.getUsername(), dto.getPassword(), dto.getCode(),
                dto.getUuid());
        if (result.isSuccess()) {
            map.put("token", result.getResult());
            return success(map);
        }
        return fail(result.getMessage());
    }
}

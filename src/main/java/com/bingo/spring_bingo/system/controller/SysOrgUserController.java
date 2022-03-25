package com.bingo.spring_bingo.system.controller;

import com.bingo.spring_bingo.common.constant.HttpStatusEnum;
import com.bingo.spring_bingo.common.constant.SysModelEnum;
import com.bingo.spring_bingo.system.dao.SysOrgDeptMapper;
import com.bingo.spring_bingo.system.model.SysOrgDept;
import com.bingo.spring_bingo.system.model.SysOrgUser;
import com.bingo.spring_bingo.system.service.ISysOrgDeptService;
import com.bingo.spring_bingo.system.service.ISysOrgUserService;
import com.bingo.spring_bingo.util.DateUtil;
import com.bingo.spring_bingo.util.response.AjaxResult;
import com.bingo.spring_bingo.util.response.AjaxResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bingo
 * @date 2022-03-25 11:19
 */
@RestController
@RequestMapping(value = "/user")
public class SysOrgUserController {

    @Autowired
    private ISysOrgUserService sysOrgUserService;

    @Autowired
    private ISysOrgDeptService sysOrgDeptService;

    @Autowired
    private SysOrgDeptMapper sysOrgDeptMapper;

    @RequestMapping(value = "/testDept")
    public AjaxResult testDept(){
        SysOrgDept dept = new SysOrgDept();
        dept.setFdMemo("这是部门");
        dept.setFdName("部门1");
        sysOrgDeptService.save(dept);


        return AjaxResultFactory.success();
    }

    @RequestMapping(value = "/testDept1")
    public AjaxResult testDept1(){
        SysOrgDept dept = sysOrgDeptService.getById("17fc02231d65aaf5548e13f400d934ea");
        dept.setFdParent(sysOrgDeptService.getById("17fc00e3c54d2383337edc1447180ba8"));

        sysOrgDeptService.updateById(dept);

        return AjaxResultFactory.success();
    }

    @RequestMapping(value = "/testDept2")
    public AjaxResult testDept2(){
        SysOrgDept dept = sysOrgDeptMapper.findByPrimaryKey("17fc00e3c54d2383337edc1447180ba8");

        System.out.println(222);

        SysOrgDept dept1 = dept.getFdParent();

        return AjaxResultFactory.success();
    }

    @RequestMapping(value = "/test")
    public AjaxResult test(){
        SysOrgUser user = sysOrgUserService.getById("17fbf55d1cd70fd7f0cee8e4c6fb8178");
        // user.setFdLoginName("15243625436");
        sysOrgUserService.updateById(user);
        return AjaxResultFactory.build(HttpStatusEnum.SUCCESS).get();
    }

    @RequestMapping(value = "/testAdd")
    public AjaxResult testAdd(){
        SysOrgUser user = new SysOrgUser();
        user.setFdName("张三");
        user.setFdEmail("xxxx@qq.com");
        user.setFdSex(SysModelEnum.SEX_MAN.getCode());
        user.setFdBirthday(DateUtil.convertStringToDate("1999-02-20","yyyy-MM-dd"));
        user.setFdWechatNo("15243625436");
        user.setFdWorkPhone("8316800===");
        user.setFdMobileNo("15243625436");
        user.setFdMemo("这是备注");
        user.setFdOrder(10);
        sysOrgUserService.save(user);

        return AjaxResultFactory.build(HttpStatusEnum.SUCCESS).get();
    }
}

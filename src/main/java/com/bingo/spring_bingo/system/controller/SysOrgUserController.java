package com.bingo.spring_bingo.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bingo.spring_bingo.common.constant.SysModelEnum;
import com.bingo.spring_bingo.system.core.response.AjaxResult;
import com.bingo.spring_bingo.system.core.web.controller.BaseController;
import com.bingo.spring_bingo.system.dao.SysOrgDeptMapper;
import com.bingo.spring_bingo.system.dao.SysOrgUserMapper;
import com.bingo.spring_bingo.system.model.SysOrgDept;
import com.bingo.spring_bingo.system.model.SysOrgUser;
import com.bingo.spring_bingo.system.service.ISysOrgDeptService;
import com.bingo.spring_bingo.system.service.ISysOrgUserService;
import com.bingo.spring_bingo.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author bingo
 * @date 2022-03-25 11:19
 */
@RestController
@RequestMapping(value = "/user")
public class SysOrgUserController extends BaseController {

    @Autowired
    private ISysOrgUserService sysOrgUserService;

    @Autowired
    private ISysOrgDeptService sysOrgDeptService;

    @Autowired
    private SysOrgDeptMapper sysOrgDeptMapper;

    @Autowired
    private SysOrgUserMapper sysOrgUserMapper;

    @RequestMapping(value = "/testDept")
    public AjaxResult testDept(){
        SysOrgDept dept = new SysOrgDept();
        dept.setFdMemo("这是部门");
        dept.setFdName("部门1");
        sysOrgDeptService.save(dept);


        return success();
    }

    @RequestMapping(value = "/testDept1")
    public AjaxResult testDept1(){
        // SysOrgDept dept = sysOrgDeptService.getById("17fc02231d65aaf5548e13f400d934ea");
        // dept.setFdParent(sysOrgDeptService.getById("17fc00e3c54d2383337edc1447180ba8"));

        IPage<SysOrgDept> page = new Page<>(1, 10);
        QueryWrapper<SysOrgDept> wrapper = new QueryWrapper(SysOrgDept.class);

        IPage<SysOrgDept> page1 = sysOrgDeptMapper.findPageList(page);

        System.out.println(1111);

        // sysOrgDeptService.updateById(dept);

        return success();
    }

    @RequestMapping(value = "/testDept2")
    public AjaxResult testDept2(){
        SysOrgDept dept = sysOrgDeptMapper.findByPrimaryKey("17fc00e3c54d2383337edc1447180ba8");

        System.out.println(222);

        SysOrgDept dept1 = dept.getFdParent();
        List<SysOrgDept> list = dept.getFdChildren();
        SysOrgUser user = dept.getFdThisLeader();

        return success();
    }

    @RequestMapping(value = "/test")
    public AjaxResult test(){
        SysOrgUser user = sysOrgUserMapper.findByPrimaryKey("17fbf55d1cd70fd7f0cee8e4c6fb8178");
        // user.setFdLoginName("15243625436");
        // sysOrgUserService.updateById(user);

        System.out.println(3333);
        return success();
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

        return success();
    }
}

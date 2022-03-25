package com.bingo.spring_bingo;

import com.bingo.spring_bingo.system.model.SysOrgUser;
import com.bingo.spring_bingo.system.service.ISysOrgUserService;
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

    @Autowired
    private ISysOrgUserService sysOrgUserService;

    @Test
    void testUser(){
        SysOrgUser user = new SysOrgUser();
        // user.setFdName("张三");
        // user.setFdEmail("xxxx@qq.com");
        // user.setFdSex(SysModelEnum.SEX_MAN.getCode());
        // user.setFdBirthday(DateUtil.convertStringToDate("1999-02-20","yyyy-MM-dd"));
        // user.setFdWechatNo("15243625436");
        // user.setFdWorkPhone("8316800===");
        // user.setFdMobileNo("15243625436");
        // sysOrgUserService.save(user);
        sysOrgUserService.update().set("fd_login_name","15243625436").eq("fd_id", "17fbf0c9426c775845e79544b5883ecf").update();
    }

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

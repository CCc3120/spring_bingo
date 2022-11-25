package com.bingo.spring_bingo.test.controller;

import com.bingo.spring_bingo.system.core.constant.ElasticSearchConstant;
import com.bingo.spring_bingo.system.core.elasticSearch.ElasticSearchService;
import com.bingo.spring_bingo.system.core.response.AjaxResult;
import com.bingo.spring_bingo.system.core.response.AjaxResultFactory;
import com.bingo.spring_bingo.test.model.Student;
import com.bingo.spring_bingo.test.service.StudentService;
import com.bingo.spring_bingo.util.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/mongo", method = RequestMethod.GET)
    public AjaxResult mongo(String name) {
        Student student = new Student();
        student.setFdName(name);
        student.setFdBirthday(new Date());
        student.setFdId(IDGenerator.generateID());
        mongoTemplate.insert(student);

        // mongoTemplate.

        return AjaxResultFactory.success();
    }

    @RequestMapping(value = "/es", method = RequestMethod.GET)
    public AjaxResult es(String name) throws Exception {
        Student student = new Student();
        student.setFdName(name);
        student.setFdSex("男");
        student.setFdClassName("大班");
        student.setFdNo("123123");
        student.setFdScope("123");
        student.setFdBirthday(new Date());
        student.setFdId(IDGenerator.generateID());

        // elasticSearchService.deleteDocument("bntang", "product", "KNYS2YIBinqSDbgzqHjn");
        // elasticSearchService.deleteIndex("bntang");
        // elasticSearchService.addDocument(ElasticSearchConstant.INDEX_STUDENT, ElasticSearchConstant.TYPE_STUDENT, student);
        // boolean existsDocument = elasticSearchService.isExistsDocument(ElasticSearchConstant.INDEX_STUDENT,
        //         ElasticSearchConstant.TYPE_STUDENT, "184a90dc2d1ba3240e69a694c13be502");
        //
        // System.out.println(existsDocument);
        //
        // Student document = elasticSearchService.getDocument(ElasticSearchConstant.INDEX_STUDENT,
        //         ElasticSearchConstant.TYPE_STUDENT, "184a90dc2d1ba3240e69a694c13be502", Student.class);
        // System.out.println(JsonMapper.getInstance().toJsonString(document));
        // document.setFdName("名字被修改了"+document.getFdName());
        // elasticSearchService.updateDocument(ElasticSearchConstant.INDEX_STUDENT,
        //         ElasticSearchConstant.TYPE_STUDENT, document);


        // List<String> integers = elasticSearchService.searchAllRequest(ElasticSearchConstant.INDEX_STUDENT);
        // System.out.println(JsonMapper.getInstance().toJsonString(integers));
        //
        List<Map<String, Object>> maps = elasticSearchService.searchRequest(ElasticSearchConstant.INDEX_STUDENT, name,
                0, 3,true, Student::getFdName, Student::getFdClassName);



        return AjaxResultFactory.success(maps);
    }
}

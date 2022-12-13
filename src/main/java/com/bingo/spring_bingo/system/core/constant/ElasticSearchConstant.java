package com.bingo.spring_bingo.system.core.constant;

import com.bingo.spring_bingo.test.model.Student;
import com.bingo.spring_bingo.util.StringUtil;

/**
 * es 索引和type配置
 */
public interface ElasticSearchConstant {

    int SUCCESS_CODE = 200;

    String KEYWORD = StringUtil.SEPARATOR_POINT + "keyword";

    String INDEX_STUDENT = "bntang";

    String TYPE_STUDENT = Student.class.getSimpleName();
}

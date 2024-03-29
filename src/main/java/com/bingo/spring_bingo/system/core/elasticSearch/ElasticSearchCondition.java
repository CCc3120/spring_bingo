package com.bingo.spring_bingo.system.core.elasticSearch;

import org.elasticsearch.index.query.BoolQueryBuilder;

/**
 * @ClassName ElasticSearchCondition
 * @Description TODO
 * @Author h-bingo
 * @Date 2022-12-13 14:51
 * @Version 1.0
 */
public interface ElasticSearchCondition {

    void build(BoolQueryBuilder boolQueryBuilder);
}

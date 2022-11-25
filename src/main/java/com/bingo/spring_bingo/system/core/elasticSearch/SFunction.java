package com.bingo.spring_bingo.system.core.elasticSearch;

import java.io.Serializable;
import java.util.function.Function;

@FunctionalInterface
public interface SFunction<T, R> extends Function<T, R>, Serializable {

}

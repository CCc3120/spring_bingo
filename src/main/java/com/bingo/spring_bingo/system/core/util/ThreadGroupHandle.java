package com.bingo.spring_bingo.system.core.util;

import java.util.List;

/**
 * @author bingo
 * @date 2022-04-28 17:19
 */
public interface ThreadGroupHandle<T> {

    void groupHandle(List<T> list);

}

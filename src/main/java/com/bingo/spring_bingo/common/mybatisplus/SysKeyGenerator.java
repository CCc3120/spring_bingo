package com.bingo.spring_bingo.common.mybatisplus;

import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.bingo.spring_bingo.util.IDGenerator;
import org.springframework.stereotype.Component;

/**
 * id生成器
 *
 * @author bingo
 * @date 2022-03-24 20:26
 */
@Component
public class SysKeyGenerator implements IKeyGenerator {

    @Override
    public String executeSql(String incrementerName) {
        return "select " + "'" + IDGenerator.generateID() + "'" + "from dual";
    }
}

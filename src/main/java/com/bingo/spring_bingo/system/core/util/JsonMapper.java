package com.bingo.spring_bingo.system.core.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Jackson 工具类
 *
 * @author bingo
 * @date 2022-04-29 16:01
 */
public class JsonMapper {
    private static ObjectMapper objectMapper;

    private static JsonMapper jsonMapper = new JsonMapper();

    private JsonMapper() {
        objectMapper = new ObjectMapper();
        objectMapper
                // 设置蛇形格式 字段名变化 fdCreateTime -> fd_create_time
                // .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                /**
                 * Support for non-standard data format constructs：支持非标准数据格式的json
                 * 该特性，决定了解析器是否可以解析含有Java/C++注释样式的JSON串(如：/*或//的注释符)
                 * 默认false：不解析含有注释符（即：true时能解析含有注释符的json串）
                 * 注意：该属性默认是false，因此必须显式允许，即通过JsonParser.Feature.ALLOW_COMMENTS 配置为true。
                 */
                .configure(JsonParser.Feature.ALLOW_COMMENTS, true)
                /**
                 * 这个特性决定parser是否能解析属性名字没有加双引号的json串（这种形式在Javascript中被允许，但是JSON标准说明书中没有）。
                 *（默认情况下，标准的json串里属性名字都需要用双引号引起来。比如：{age:12, name:"曹操"}非标准的json串，默认不能解析）
                 * 注意：由于JSON标准上需要为属性名称使用双引号，所以这也是一个非标准特性，默认是false的。
                 * 同样，需要设置JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES为true，打开该特性。
                 *
                 */
                .configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
                /**
                 * 默认false：不解析含有单引号的字符串或字符
                 * 该特性，决定了解析器是否可以解析单引号的字符串或字符(如：单引号的字符串，单引号'\'')
                 * 注意：可作为其他可接受的标记，但不是JSON的规范
                 */
                .configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true)
                /**
                 * 允许：默认false不解析含有结束语控制字符
                 * 该特性，决定了解析器是否可以解析结束语控制字符(如：ASCII<32，包含制表符\t、换行符\n和回车\r)
                 * 注意：设置false（默认）时，若解析则抛出异常;若true时，则用引号即可转义
                 */
                .configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true)
                // 处理反序列化中含有\\ 异常情况
                .configure(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER.mappedFeature(), true)
                // 数字可以0开头
                .configure(JsonReadFeature.ALLOW_LEADING_ZEROS_FOR_NUMBERS.mappedFeature(), true)
                // 序列化的时候序列对象的那些属性
                // JsonInclude.Include.NON_DEFAULT 属性为默认值不序列化
                // JsonInclude.Include.ALWAYS      所有属性
                // JsonInclude.Include.NON_EMPTY   属性为 空（“”） 或者为 NULL 都不序列化
                // JsonInclude.Include.NON_NULL    属性为NULL 不序列化
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                // true - 遇到没有的属性就报错 false - 没有的属性不会管，不会报错
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                // 转换为格式化的json 显示出来的格式美化
                .configure(SerializationFeature.INDENT_OUTPUT, true)
                // 如果是空对象的时候,不抛异常
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                // 修改序列化后日期格式
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false)
                // 序列化日期格式
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))

                // 忽略 @transient 修饰的属性
                .configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, false)

                // 处理不同的时区偏移格式
                .registerModule(new JavaTimeModule())
        ;
    }

    public static void main(String[] args) {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        list.add(map);
        Map<String, String> map1 = new HashMap<>();
        map1.put("key3", "value3");
        map1.put("key4", "value4");
        list.add(map1);
        System.out.println(JsonMapper.getInstance().toJsonString(list));

        String str = "[ {\n" +
                "  \"key1\" : \"value1\",\n" +
                "  \"key2\" : \"value2\"\n" +
                "}, {\n" +
                "  \"key3\" : \"value3\",\n" +
                "  \"key4\" : \"value4\"\n" +
                "} ]";

        JsonMapper.getInstance().fromJson(str, list.getClass(), map.getClass());
        JsonMapper.getInstance().fromJson(str, Map.class, String.class, String.class);

        System.out.println();
    }

    public static JsonMapper getInstance() {
        return jsonMapper;
    }

    public <T> String toJsonString(T t) {
        String result = null;
        try {
            result = objectMapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public <T> T fromJson(String jsonStr, Class<T> t) {
        T result = null;
        try {
            result = objectMapper.readValue(jsonStr, t);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    public <T> List<T> fromJson(String jsonStr, Class<? extends Collection> cls, Class<?> t) {
        List<T> result = null;
        try {
            JavaType type = objectMapper.getTypeFactory().constructCollectionType(cls, t);
            result = objectMapper.readValue(jsonStr, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public <K, V> Map<K, V> fromJson(String jsonStr, Class<?> cls, Class<K> key, Class<V> value) {
        Map<K, V> result = null;
        try {
            JavaType type = objectMapper.getTypeFactory().constructMapLikeType(cls, key, value);
            result = objectMapper.readValue(jsonStr, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}

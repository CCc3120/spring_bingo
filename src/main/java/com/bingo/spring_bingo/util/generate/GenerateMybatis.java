package com.bingo.spring_bingo.util.generate;

import org.springframework.util.Assert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GenerateMybatis {
    private final String TABLE_SEPARATOR = "\t";

    private static final String XML_SUFFER = ".xml";

    private static final String MAPPER_SUFFER = ".java";

    private static final int DEFAULT_NULL_CHAR_LENGTH = 12;

    private static final String NULL_CHAR = " ";

    private final String LINE_SEPARATOR = System.lineSeparator();

    private final StringBuffer xmlBuffer = new StringBuffer();

    private final StringBuffer mapperBuffer = new StringBuffer();

    private final Config config;

    public GenerateMybatis(Config config) {
        this.config = config;
    }

    // 主键字段
    private Field pkField;

    // 非主键字段
    private final List<Field> fieldList = new ArrayList<>();

    // 类的全路径
    private String type;

    // 转换后的表名
    private String tableName;

    // xml生成路径
    private String xmlPath;

    // mapper生成路径
    private String mapperPath;

    // mapper接口包路径
    private String mapperPackage;

    // mapper.xml命名空间 对应mapper接口的全路径
    private String nameSpace;

    // 接口名
    private String mapperName;

    private int fieldMaxLength = 0;

    public GenerateMybatis init() {
        // 初始化主键和非主键值
        for (Field field : config.getClazz().getDeclaredFields()) {
            if (checkFieldIsPk(field)) {
                pkField = field;
            } else {
                fieldList.add(field);
            }

            if (field.getName().length() > fieldMaxLength) {
                fieldMaxLength = field.getName().length();
            }
        }
        Assert.notNull(pkField, "主键不能为空");

        // 初始化表名
        tableName = humpToUnderline(config.getClazz().getSimpleName());
        // 初始化类全路径
        type = config.getClazz().getTypeName();
        // 初始化mapper接口和xml生成路径
        if (config.getMapperPackage() == null || config.getMapperPackage().trim().length() == 0) {
            mapperPackage = modelPackage(config.getClazz());
        } else {
            mapperPackage = config.getMapperPackage();
        }

        String path = Objects.requireNonNull(config.getClazz().getResource("/")).getPath();
        path = path.substring(0, path.lastIndexOf("target")) + "src/main/java" + File.separator;
        if (config.getXmlPackage() == null || config.getXmlPackage().trim().length() == 0) {
            xmlPath = path + modelPackage(config.getClazz()).replaceAll("\\.", File.separator);
        } else {
            xmlPath = path + config.getXmlPackage().replaceAll("\\.", File.separator);
        }
        mapperPath = path + mapperPackage.replaceAll("\\.", File.separator);

        // 接口名
        mapperName = config.getClazz().getSimpleName() + "Mapper";
        // mapper.xml命名空间 对应mapper接口的全路径
        nameSpace = mapperPackage + "." + mapperName;
        return this;
    }

    public GenerateMybatis build() {
        buildXml();
        buildMapper();
        return this;
    }

    public void writeToFile() {
        // 写入xml文件
        String xmlFileName = xmlPath + File.separator + mapperName + XML_SUFFER;
        writeToFile(xmlFileName, xmlBuffer.toString());

        // 写入mapper文件
        String mapperFileName = mapperPath + File.separator + mapperName + MAPPER_SUFFER;
        writeToFile(mapperFileName, mapperBuffer.toString());
    }

    private void buildMapper() {
        buildMapperHeader();
        if (config.isHasMethod()) {
            buildMapperMethod();
        }
        buildMapperEnd();
    }

    private void buildMapperMethod() {
        mapperBuffer.append(TABLE_SEPARATOR).append(config.getClazz().getSimpleName()).append(" findByPrimaryKey(")
                .append(pkField.getType().getSimpleName()).append(" ").append(pkField.getName()).append(");");
        mapperBuffer.append(LINE_SEPARATOR);
        mapperBuffer.append(LINE_SEPARATOR);

        mapperBuffer.append(TABLE_SEPARATOR).append("int insert(").append(config.getClazz().getSimpleName()).append(" ")
                .append(firstToLower(config.getClazz().getSimpleName())).append(");");
        mapperBuffer.append(LINE_SEPARATOR);
        mapperBuffer.append(LINE_SEPARATOR);

        mapperBuffer.append(TABLE_SEPARATOR).append("int update(").append(config.getClazz().getSimpleName()).append(" ")
                .append(firstToLower(config.getClazz().getSimpleName())).append(");");
        mapperBuffer.append(LINE_SEPARATOR);
        mapperBuffer.append(LINE_SEPARATOR);

        mapperBuffer.append(TABLE_SEPARATOR).append("int delete(")
                .append(pkField.getType().getSimpleName()).append(" ").append(pkField.getName()).append(");");
        mapperBuffer.append(LINE_SEPARATOR);
        mapperBuffer.append(LINE_SEPARATOR);
    }

    private void buildMapperHeader() {
        mapperBuffer.append("package ").append(mapperPackage).append(";");
        mapperBuffer.append(LINE_SEPARATOR);
        mapperBuffer.append(LINE_SEPARATOR);
        mapperBuffer.append("import ").append(type).append(";");
        mapperBuffer.append(LINE_SEPARATOR);
        mapperBuffer.append(LINE_SEPARATOR);
        mapperBuffer.append("public interface ").append(mapperName).append(" {");
        mapperBuffer.append(LINE_SEPARATOR);
        mapperBuffer.append(LINE_SEPARATOR);
    }

    private void buildMapperEnd() {
        mapperBuffer.append("}");
    }

    private void buildXml() {
        buildXmlHeader();
        buildXmlCommon();

        if (config.isHasMethod()) {
            buildXmlMethod();
        }

        buildXmlEnd();
    }

    private void buildXmlMethod() {
        buildXmlFind();
        buildXmlInsert();
        buildXmlUpdate();
        buildXmlDelete();
    }

    private void buildXmlHeader() {
        xmlBuffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        xmlBuffer.append(LINE_SEPARATOR);
        xmlBuffer.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis" +
                ".org/dtd/mybatis-3-mapper.dtd\" >");
        xmlBuffer.append(LINE_SEPARATOR);
        xmlBuffer.append("<mapper namespace=\"").append(nameSpace).append("\">");
        xmlBuffer.append(LINE_SEPARATOR);
    }

    private void buildXmlCommon() {
        xmlBuffer.append(TABLE_SEPARATOR).append("<resultMap id=\"BaseResultMap\" type=\"").append(type).append("\">");
        xmlBuffer.append(LINE_SEPARATOR);
        xmlBuffer.append(TABLE_SEPARATOR).append(TABLE_SEPARATOR).append("<id     property=\"")
                .append(pkField.getName()).append("\"").append(formatResult(pkField)).append("column=\"")
                .append(humpToUnderline(pkField.getName())).append("\"/>");
        xmlBuffer.append(LINE_SEPARATOR);
        for (Field field : fieldList) {
            xmlBuffer.append(TABLE_SEPARATOR).append(TABLE_SEPARATOR).append("<result property=\"")
                    .append(field.getName()).append("\"").append(formatResult(field)).append("column=\"")
                    .append(humpToUnderline(field.getName())).append("\"/>");
            xmlBuffer.append(LINE_SEPARATOR);
        }
        xmlBuffer.append(TABLE_SEPARATOR).append("</resultMap>");
        xmlBuffer.append(LINE_SEPARATOR);
        xmlBuffer.append(LINE_SEPARATOR);


        xmlBuffer.append(TABLE_SEPARATOR).append("<sql id=\"BaseSql\">");
        xmlBuffer.append(LINE_SEPARATOR);
        StringBuilder sql = new StringBuilder();
        sql.append("${mark}.").append(humpToUnderline(pkField.getName()));
        for (Field field : fieldList) {
            sql.append(", ").append("${mark}.").append(humpToUnderline(field.getName()));
        }
        xmlBuffer.append(TABLE_SEPARATOR).append(TABLE_SEPARATOR).append(sql);
        xmlBuffer.append(LINE_SEPARATOR);
        xmlBuffer.append(TABLE_SEPARATOR).append("</sql>");
        xmlBuffer.append(LINE_SEPARATOR);
        xmlBuffer.append(LINE_SEPARATOR);
    }

    private void buildXmlFind() {
        xmlBuffer.append(TABLE_SEPARATOR).append("<select id=\"findByPrimaryKey\" parameterType=\"")
                .append(pkField.getType().getTypeName()).append("\" resultMap=\"BaseResultMap\">");
        xmlBuffer.append(LINE_SEPARATOR);
        xmlBuffer.append(TABLE_SEPARATOR).append(TABLE_SEPARATOR).append("select");
        xmlBuffer.append(LINE_SEPARATOR);
        xmlBuffer.append(TABLE_SEPARATOR).append(TABLE_SEPARATOR).append("<include refid=\"BaseSql\">");
        xmlBuffer.append(LINE_SEPARATOR);
        xmlBuffer.append(TABLE_SEPARATOR).append(TABLE_SEPARATOR).append(TABLE_SEPARATOR)
                .append("<property name=\"mark\" value=\"").append(tableName).append("\"/>");
        xmlBuffer.append(LINE_SEPARATOR);
        xmlBuffer.append(TABLE_SEPARATOR).append(TABLE_SEPARATOR).append("</include>");
        xmlBuffer.append(LINE_SEPARATOR);
        xmlBuffer.append(TABLE_SEPARATOR).append(TABLE_SEPARATOR).append("from ").append(tableName).append(" where ")
                .append(tableName).append(".").append(humpToUnderline(pkField.getName()))
                .append(" = #{").append(pkField.getName()).append("}");
        xmlBuffer.append(LINE_SEPARATOR);
        xmlBuffer.append(TABLE_SEPARATOR).append("</select>");
        xmlBuffer.append(LINE_SEPARATOR);
        xmlBuffer.append(LINE_SEPARATOR);
    }

    private void buildXmlInsert() {
        xmlBuffer.append(TABLE_SEPARATOR).append("<insert id=\"insert\" parameterType=\"").append(type).append("\">");
        xmlBuffer.append(LINE_SEPARATOR);
        xmlBuffer.append(TABLE_SEPARATOR).append(TABLE_SEPARATOR).append("insert into ").append(tableName);
        xmlBuffer.append(LINE_SEPARATOR);
        xmlBuffer.append(TABLE_SEPARATOR).append(TABLE_SEPARATOR)
                .append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        xmlBuffer.append(LINE_SEPARATOR);
        insertSame(pkField);
        for (Field field : fieldList) {
            insertSame(field);
        }
        xmlBuffer.append(TABLE_SEPARATOR).append(TABLE_SEPARATOR).append("</trim>");
        xmlBuffer.append(LINE_SEPARATOR);

        xmlBuffer.append(TABLE_SEPARATOR).append(TABLE_SEPARATOR).append("values");
        xmlBuffer.append(LINE_SEPARATOR);

        xmlBuffer.append(TABLE_SEPARATOR).append(TABLE_SEPARATOR)
                .append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        xmlBuffer.append(LINE_SEPARATOR);
        insertValueSame(pkField);
        for (Field field : fieldList) {
            insertValueSame(field);
        }
        xmlBuffer.append(TABLE_SEPARATOR).append(TABLE_SEPARATOR).append("</trim>");
        xmlBuffer.append(LINE_SEPARATOR);
        xmlBuffer.append(TABLE_SEPARATOR).append("</insert>");
        xmlBuffer.append(LINE_SEPARATOR);
        xmlBuffer.append(LINE_SEPARATOR);
    }

    private void insertValueSame(Field pkField) {
        checkFieldType(pkField);
        xmlBuffer.append(TABLE_SEPARATOR).append(TABLE_SEPARATOR).append(TABLE_SEPARATOR).append(TABLE_SEPARATOR)
                .append("#{").append(pkField.getName()).append("}").append(",");
        xmlBuffer.append(LINE_SEPARATOR);
        xmlBuffer.append(TABLE_SEPARATOR).append(TABLE_SEPARATOR).append(TABLE_SEPARATOR).append("</if>");
        xmlBuffer.append(LINE_SEPARATOR);
    }

    private void insertSame(Field field) {
        checkFieldType(field);
        xmlBuffer.append(TABLE_SEPARATOR).append(TABLE_SEPARATOR).append(TABLE_SEPARATOR).append(TABLE_SEPARATOR)
                .append(humpToUnderline(field.getName())).append(",");
        xmlBuffer.append(LINE_SEPARATOR);
        xmlBuffer.append(TABLE_SEPARATOR).append(TABLE_SEPARATOR).append(TABLE_SEPARATOR).append("</if>");
        xmlBuffer.append(LINE_SEPARATOR);
    }

    private void buildXmlUpdate() {
        xmlBuffer.append(TABLE_SEPARATOR).append("<update id=\"update\" parameterType=\"").append(type).append("\">");
        xmlBuffer.append(LINE_SEPARATOR);
        xmlBuffer.append(TABLE_SEPARATOR).append(TABLE_SEPARATOR).append("update ").append(tableName);
        xmlBuffer.append(LINE_SEPARATOR);
        xmlBuffer.append(TABLE_SEPARATOR).append(TABLE_SEPARATOR).append("<set>");
        xmlBuffer.append(LINE_SEPARATOR);
        for (Field field : fieldList) {
            checkFieldType(field);
            xmlBuffer.append(TABLE_SEPARATOR).append(TABLE_SEPARATOR).append(TABLE_SEPARATOR).append(TABLE_SEPARATOR)
                    .append(humpToUnderline(field.getName())).append(" = #{").append(field.getName()).append("},");
            xmlBuffer.append(LINE_SEPARATOR);
            xmlBuffer.append(TABLE_SEPARATOR).append(TABLE_SEPARATOR).append(TABLE_SEPARATOR).append("</if>");
            xmlBuffer.append(LINE_SEPARATOR);
        }
        xmlBuffer.append(TABLE_SEPARATOR).append(TABLE_SEPARATOR).append("</set>");
        xmlBuffer.append(LINE_SEPARATOR);
        xmlBuffer.append(TABLE_SEPARATOR).append(TABLE_SEPARATOR).append("where ")
                .append(humpToUnderline(pkField.getName())).append(" = #{").append(pkField.getName()).append("}");
        xmlBuffer.append(LINE_SEPARATOR);
        xmlBuffer.append(TABLE_SEPARATOR).append("</update>");
        xmlBuffer.append(LINE_SEPARATOR);
        xmlBuffer.append(LINE_SEPARATOR);
    }

    private void checkFieldType(Field field) {
        if (field.getType().getTypeName().equals(String.class.getTypeName())) {
            xmlBuffer.append(TABLE_SEPARATOR).append(TABLE_SEPARATOR).append(TABLE_SEPARATOR)
                    .append("<if test=\"").append(field.getName()).append(" != null and ")
                    .append(field.getName()).append(" !=''\">");
        } else {
            xmlBuffer.append(TABLE_SEPARATOR).append(TABLE_SEPARATOR).append(TABLE_SEPARATOR)
                    .append("<if test=\"").append(field.getName()).append(" != null\">");
        }
        xmlBuffer.append(LINE_SEPARATOR);
    }

    private void buildXmlDelete() {
        xmlBuffer.append(TABLE_SEPARATOR).append("<delete id=\"delete\" parameterType=\"")
                .append(pkField.getType().getTypeName()).append("\">");
        xmlBuffer.append(LINE_SEPARATOR);
        xmlBuffer.append(TABLE_SEPARATOR).append(TABLE_SEPARATOR).append("delete from ").append(tableName)
                .append(" where ").append(humpToUnderline(pkField.getName())).append(" = #{").append(pkField.getName()).append("}");
        xmlBuffer.append(LINE_SEPARATOR);
        xmlBuffer.append(TABLE_SEPARATOR).append("</delete>");
        xmlBuffer.append(LINE_SEPARATOR);
        xmlBuffer.append(LINE_SEPARATOR);
    }

    private void buildXmlEnd() {
        xmlBuffer.append("</mapper>");
    }

    public String formatResult(Field field) {
        int c = fieldMaxLength - field.getName().length() + DEFAULT_NULL_CHAR_LENGTH;
        StringBuilder nullChar = new StringBuilder();
        for (int i = 0; i < c; i++) {
            nullChar.append(NULL_CHAR);
        }
        return nullChar.toString();
    }

    /* 获取model所在包 */
    public String modelPackage(Class<?> clazz) {
        return clazz.getTypeName().substring(0, clazz.getTypeName().lastIndexOf("."));
    }

    /* 判断是否是主键 需要@PK注解 */
    public boolean checkFieldIsPk(Field field) {
        PK pk = field.getAnnotation(PK.class);
        return pk != null;
    }

    /* 下划线命名转为驼峰命名 */
    @SuppressWarnings("all")
    public String underlineToHump(String para) {
        StringBuilder result = new StringBuilder();
        String[] a = para.split("_");
        for (String s : a) {
            if (result.length() == 0) {
                result.append(s.toLowerCase());
            } else {
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    /* 驼峰命名转为下划线命名 */
    public String humpToUnderline(String para) {
        StringBuilder sb = new StringBuilder(para);
        // 偏移量，第i个下划线的位置是 当前的位置+ 偏移量（i-1）,第一个下划线偏移量是0
        int temp = 0;
        for (int i = 0; i < para.length(); i++) {
            if (Character.isUpperCase(para.charAt(i))) {
                if (i != 0) {
                    sb.insert(i + temp, "_");
                    temp += 1;
                }
            }
        }
        return sb.toString().toLowerCase();
    }

    /* 首字母小写 */
    public String firstToLower(String para) {
        return para.substring(0, 1).toLowerCase() + para.substring(1);
    }

    /* 写入文件 */
    public void writeToFile(String path, String content) {
        FileWriter fw = null;
        try {
            File file = new File(path);
            if (!file.getParentFile().exists()) {
                boolean flag = file.getParentFile().mkdirs();
                System.out.println("文件夹创建成功" + flag);
            }
            fw = new FileWriter(file);
            fw.write(content);
            fw.flush();

            System.out.println(path);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ignored) {
            }
        }
    }
}

package com.bingo.spring_bingo.util.generate;

import com.bingo.spring_bingo.test.model.Student;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

public class GenerateFactory {

    private Config config;

    private Class<?>[] clazz;

    private List<GenerateMybatis> generateMybatisList = new ArrayList<>();

    public static GenerateFactory create(Config config, Class<?>... clazz) {
        return new GenerateFactory(config, clazz);
    }

    private GenerateFactory(Config config, Class<?>... clazz) {
        Assert.notNull(clazz, "域模型不能为空");
        this.config = config;
        this.clazz = clazz;
    }

    public GenerateFactory build() {
        for (Class<?> aClass : clazz) {
            config.setClazz(aClass);
            generateMybatisList.add(new GenerateMybatis(config).init().build());
        }
        return this;
    }

    public void writeToFile() {
        for (GenerateMybatis generateMybatis : generateMybatisList) {
            generateMybatis.writeToFile();
        }
    }

    public static void main(String[] args) {
        Config build = Config.Builder.getDefaule()
                // .clazz(User.class)
                .xmlPackage("com.bingo.spring_bingo.dao.xml")
                .mapperPackage("com.bingo.spring_bingo.dao")
                .hasMethod(true)
                .build();

        GenerateFactory.create(build, Student.class).build().writeToFile();
    }
}

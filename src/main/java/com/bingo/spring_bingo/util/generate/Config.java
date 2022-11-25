package com.bingo.spring_bingo.util.generate;

import org.springframework.util.Assert;

public class Config {
    /* model */
    private Class<?> clazz;

    /* xml包路径 */
    private String xmlPackage;

    /* mapper接口包路径 */
    private String mapperPackage;

    /* 是否生成基本方法 */
    private boolean hasMethod = true;

    private Config(Class<?> clazz) {
        this.clazz = clazz;
    }

    private Config(Class<?> clazz, String xmlPackage, String mapperPackage, boolean hasMethod) {
        this.clazz = clazz;
        this.xmlPackage = xmlPackage;
        this.mapperPackage = mapperPackage;
        this.hasMethod = hasMethod;
    }

    public static Config defaultConfig(Class<?> clazz) {
        Assert.notNull(clazz, "域模型不能为空");
        return new Config(clazz);
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getXmlPackage() {
        return xmlPackage;
    }

    public void setXmlPackage(String xmlPackage) {
        this.xmlPackage = xmlPackage;
    }

    public String getMapperPackage() {
        return mapperPackage;
    }

    public void setMapperPackage(String mapperPackage) {
        this.mapperPackage = mapperPackage;
    }

    public boolean isHasMethod() {
        return hasMethod;
    }

    public void setHasMethod(boolean hasMethod) {
        this.hasMethod = hasMethod;
    }

    public static class Builder {

        private Class<?> clazz;

        private String xmlPackage;

        private String mapperPackage;

        private boolean hasMethod = true;

        public static Builder getDefaule() {
            return new Builder();
        }

        public Builder clazz(Class<?> clazz) {
            this.clazz = clazz;
            return this;
        }

        public Builder xmlPackage(String xmlPackage) {
            this.xmlPackage = xmlPackage;
            return this;
        }

        public Builder mapperPackage(String mapperPackage) {
            this.mapperPackage = mapperPackage;
            return this;
        }

        public Builder hasMethod(boolean hasMethod) {
            this.hasMethod = hasMethod;
            return this;
        }

        public Config build() {
            return new Config(clazz, xmlPackage, mapperPackage, hasMethod);
        }
    }
}

package com.bingo.spring_bingo.system.core.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.bingo.spring_bingo.system.core.interfaces.IBaseModel;
import com.bingo.spring_bingo.util.IDGenerator;
import com.bingo.spring_bingo.util.ObjectUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * @author bingo
 * @date 2022-03-25 13:37
 */
@JsonIgnoreProperties(value = {"handler"}) // 解决因懒加载触发序列化问题
public abstract class BaseModel implements IBaseModel, Serializable {

    @MongoId
    @TableId(value = "fd_id", type = IdType.INPUT)
    private String fdId;

    @Override
    public String getFdId() {
        if (fdId == null) {
            fdId = IDGenerator.generateID();
        }
        return fdId;
    }

    @Override
    public void setFdId(String fdId) {
        this.fdId = fdId;
    }

    /**
     * 覆盖toString方法，使用列出域模型中的所有get方法返回的值（不获取返回值类型为非java.lang.*的值）
     *
     * @see java.lang.Object#toString()
     */
    public String toString() {
        try {
            Method[] methodList = this.getClass().getMethods();
            ToStringBuilder rtnVal = new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE);
            for (int i = 0; i < methodList.length; i++) {
                String methodName = methodList[i].getName();
                if (methodList[i].getParameterTypes().length > 0
                        || !methodName.startsWith("get")
                        || methodName.equals("getClass")) {
                    continue;
                }
                methodName = methodList[i].getReturnType().toString();
                if ((methodName.startsWith("class") || methodName.startsWith("interface"))
                        && !(methodName.startsWith("class java.lang.") || methodName.startsWith("interface java.lang."))) {
                    continue;
                }
                try {
                    rtnVal.append(methodList[i].getName().substring(3), methodList[i].invoke(this));
                } catch (Exception e) {
                }
            }
            return rtnVal.toString().replaceAll("@[^\\[]+\\[\\r\\n", "[\r\n");
        } catch (Exception e) {
            return super.toString();
        }
    }

    /**
     * 覆盖equals方法，仅比较类型是否相等以及关键字是否相等
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (!object.getClass().getName().equals(this.getClass().getName())) {
            return false;
        }
        BaseModel objModel = (BaseModel) object;
        return ObjectUtil.equals(objModel.getFdId(), this.getFdId(), false);
    }

    /**
     * 覆盖hashCode方法，通过模型中类名和ID构建哈希值
     *
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        HashCodeBuilder rtnVal = new HashCodeBuilder(-426830461, 631494429);
        rtnVal.append(this.getClass().getName());
        rtnVal.append(getFdId());
        return rtnVal.toHashCode();
    }
}

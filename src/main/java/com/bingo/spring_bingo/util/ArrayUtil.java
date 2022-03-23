package com.bingo.spring_bingo.util;

import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 集合工具类
 *
 * @author bingo
 * @date 2022-03-23 17:54
 */
public class ArrayUtil {
    /**
     * 判断两个列表中的内容是否一样（忽略顺序）
     *
     * @param list1
     * @param list2
     * @return
     */
    public static <T> boolean isListSame(List<T> list1, List<T> list2) {
        return list1.size() == list2.size() && list1.containsAll(list2);
    }

    /**
     * 判断两个列表中是否有交集
     *
     * @param list1
     * @param list2
     * @return
     */
    public static <T> boolean isListIntersect(List<T> list1, List<T> list2) {
        for (int i = 0; i < list1.size(); i++)
            if (list2.contains(list1.get(i)))
                return true;
        return false;
    }

    /**
     * 判断两个数组的内容是否一样（忽略顺序）
     *
     * @param objArr1
     * @param objArr2
     * @return
     */
    public static boolean isArraySame(Object[] objArr1, Object[] objArr2) {
        return isListSame(Arrays.asList(objArr1), Arrays.asList(objArr2));
    }

    /**
     * 判断objArr1是否包含了objArr2
     *
     * @param objArr1
     * @param objArr2
     * @return
     */
    public static boolean isArrayContains(Object[] objArr1, Object[] objArr2) {
        return Arrays.asList(objArr1).containsAll(Arrays.asList(objArr2));
    }

    /**
     * 判断两个数组中是否有交集
     *
     * @param objArr1
     * @param objArr2
     * @return
     */
    public static boolean isArrayIntersect(Object[] objArr1, Object[] objArr2) {
        return isListIntersect(Arrays.asList(objArr1), Arrays.asList(objArr2));
    }

    /**
     * 将fromList中的元素添加到toList中，过滤重复值
     *
     * @param fromList
     * @param toList
     */
    public static <T> void concatTwoList(List<T> fromList, List<T> toList) {
        if (fromList == null || toList == null)
            return;
        for (int i = 0; i < fromList.size(); i++) {
            T obj = fromList.get(i);
            if (!toList.contains(obj))
                toList.add(obj);
        }
    }

    /**
     * 判断集合是否为null或empty
     *
     * @param collection
     * @return
     */
    public static <T> boolean isEmpty(Collection<T> collection) {
        if (collection == null)
            return true;
        return collection.isEmpty();
    }

    /**
     * 判断list是否为null或empty
     *
     * @param list
     * @return
     */
    public static <T> boolean isEmpty(List<T> list) {
        if (list == null)
            return true;
        return list.isEmpty();
    }

    /**
     * 数组转为List
     */
    public static <T> List<T> convertArrayToList(T[] objects) {
        List<T> rtnList = new ArrayList();
        for (int i = 0; i < objects.length; i++) {
            rtnList.add(objects[i]);
        }
        return rtnList;
    }

    /**
     * 字符串数组转为字符串，使用指定字符连接数组元素
     *
     * @param arr 字符串数组
     * @param c   连接字符
     * @return 字符串表达式
     */
    public static String concat(String[] arr, char c) {
        String rtnStr = "";
        if (arr != null && arr.length > 0) {
            StringBuilder sbd = new StringBuilder();
            for (String str : arr) {
                sbd.append(c).append(str);
            }
            rtnStr = sbd.substring(1);
        }
        return rtnStr;
    }

    /**
     * 数组去重去null（直接在原数组删除后面的重复值）
     */
    public static void unique(List<?> list) {
        for (int i = 0; i < list.size(); i++) {
            Object vi = list.get(i);
            if (vi == null) {
                list.remove(i);
                i--;
                continue;
            }
            for (int j = list.size() - 1; j > i; j--) {
                Object vj = list.get(j);
                if (ObjectUtil.equals(vi, vj)) {
                    list.remove(j);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> asList(T[] arr) {
        if (arr == null || arr.length == 0) {
            return Collections.EMPTY_LIST;
        } else {
            return Arrays.asList(arr);
        }
    }

    /**
     * 将一个对象转换为Long类型
     *
     * @param value
     * @return
     */
    public static Long parseLong(Object value) {
        if (value instanceof Long)
            return (Long) value;
        if (value instanceof String)
            return new Long((String) value);
        if (value instanceof Number)
            return ((Number) value).longValue();
        return null;
    }

    /**
     * 将一个对象转换为Double类型
     *
     * @param value
     * @return
     */
    public static Double parseDouble(Object value) {
        if (value != null) {
            try {
                if (value instanceof Double)
                    return (Double) value;
                if (value instanceof String)
                    return new Double((String) value);
                if (value instanceof Number)
                    return ((Number) value).doubleValue();
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * 校验某个类的某个属性是否能够执行get或set方法
     *
     * @param c
     * @param property
     * @return
     */
    // public static PropertyDescriptor getPropertyDescriptor(Class c, String property) {
    //     if (StringUtil.isNull(property))
    //         return null;
    //     // 按层级校验形如a.b.c的属性
    //     String[] properties = property.split("\\.");
    //     outloop: for (int i = 0; i < properties.length; i++) {
    //         if (StringUtil.isNull(properties[i]))
    //             return null;
    //         // 遍历类里面的每个属性，查找等于properties[i]的进行校验
    //         PropertyDescriptor[] propertyDescriptors = PropertyUtils .getPropertyDescriptors(c);
    //         for (int j = 0; j < propertyDescriptors.length; j++) {
    //             if (propertyDescriptors[j].getName().equals(properties[i])) {
    //                 // 注意：校验setter方法的时候，仅在最后一级校验setter，前面的都校验getter
    //                 if (i == properties.length - 1)
    //                     return propertyDescriptors[j];
    //                 else if (propertyDescriptors[j].getReadMethod() == null)
    //                     return null;
    //                 Class childClass = propertyDescriptors[j].getPropertyType();
    //                 if (!childClass.isAssignableFrom(IBaseTreeModel.class))
    //                     c = childClass;
    //                 continue outloop;
    //             }
    //         }
    //         // 没有找到，校验失败
    //         return null;
    //     }
    //     return null;
    // }

    /**
     * 获取 目标对象
     *
     * @param proxy 代理对象
     * @return
     * @throws Exception
     */
    public static Object getTarget(Object proxy) throws Exception {
        if (!AopUtils.isAopProxy(proxy)) {
            return proxy;//不是代理对象
        }
        if (AopUtils.isJdkDynamicProxy(proxy)) {
            return getJdkDynamicProxyTargetObject(proxy);
        } else { //cglib
            return getCglibProxyTargetObject(proxy);
        }
    }

    public static Object getCglibProxyTargetObject(Object proxy) throws Exception {
        Field field = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
        field.setAccessible(true);
        Object dynamicAdvisedInterceptor = field.get(proxy);
        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        return ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
    }


    public static Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {
        Field field = proxy.getClass().getSuperclass().getDeclaredField("h");
        field.setAccessible(true);
        AopProxy aopProxy = (AopProxy) field.get(proxy);
        Field advised = aopProxy.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        return ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();
    }
}

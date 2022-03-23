package com.bingo.spring_bingo.util;

/**
 * @author bingo
 * @date 2022-03-23 18:04
 */
public class ObjectUtil {
    /**
     * 比较两个对象是否相等（同时处理null的情况），当两个对象都为空时返回true
     *
     * @param obj1
     * @param obj2
     * @return
     */
    public static boolean equals(Object obj1, Object obj2) {
        return equals(obj1, obj2, true);
    }

    /**
     * 比较两个对象是否相等（同时处理null的情况），当两个对象都为空时返回指定值bothNullReturn
     *
     * @param obj1
     * @param obj2
     * @param bothNullReturn
     * @return
     */
    public static boolean equals(Object obj1, Object obj2, boolean bothNullReturn) {
        if (obj1 == null && obj2 == null)
            return bothNullReturn;
        if (obj1 == obj2)
            return true;
        if (obj1 == null || obj2 == null)
            return false;
        return obj1.equals(obj2);
    }
}

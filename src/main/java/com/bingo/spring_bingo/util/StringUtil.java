package com.bingo.spring_bingo.util;

import java.util.Iterator;

/**
 * 字符串工具类
 *
 * @author bingo
 * @date 2022-03-23 17:44
 */
public class StringUtil {

    /**
     * 判断一个字符串是否为null或空
     *
     * @param str
     * @return
     */
    public static boolean isNull(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 判断一个字符串是否为null或空
     *
     * @param str
     * @return
     */
    public static boolean isNotNull(String str) {
        return !isNull(str);
    }

    /**
     * 检查给定的字符串既不是空，也不是长度为0。
     * <p>
     * 样例：
     *
     * <pre>
     * StringUtil.hasLength(null) = false
     * StringUtil.hasLength(&quot;&quot;) = false
     * StringUtil.hasLength(&quot; &quot;) = true
     * StringUtil.hasLength(&quot;Hello&quot;) = true
     * </pre>
     *
     * @param str 需检查的字符串(可以为<code>null</code>)
     * @return 若字符串不为null或长度不为0，则返回<code>true</code>。
     */
    public static boolean hasLength(String str) {
        return (str != null && str.length() > 0);
    }

    /**
     * 将字符串转换为数字，如果有错，采用缺省值
     *
     * @param value        字符串
     * @param defaultValue 缺省值
     * @return
     */
    public static int getIntFromString(String value, int defaultValue) {
        int ret = defaultValue;
        if (StringUtil.isNotNull(value)) {
            try {
                ret = Integer.parseInt(value);
            } catch (NumberFormatException e) {
                ret = defaultValue;
            }
        }
        return ret;
    }

    /**
     * 数组连接成字符串，按照指定的分隔符
     *
     * @param array
     * @param separator
     * @return
     */
    public static String join(String[] array, String separator) {
        if (array == null) {
            return null;
        }
        if (separator == null) {
            separator = "";
        }
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            if (i > 0) {
                buf.append(separator);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

    public static String join(Iterable iterable, String separator) {
        if (iterable == null) {
            return null;
        }
        return join(iterable.iterator(), separator);
    }

    public static String join(Iterator iterator, String separator) {
        if (iterator == null) {
            return null;
        }
        if (!(iterator.hasNext())) {
            return "";
        }
        Object first = iterator.next();
        if (!(iterator.hasNext())) {
            return ((first == null) ? "" : first.toString());
        }

        StringBuffer buf = new StringBuffer(256);
        if (first != null) {
            buf.append(first);
        }

        while (iterator.hasNext()) {
            if (separator != null) {
                buf.append(separator);
            }
            Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }
        return buf.toString();
    }
}

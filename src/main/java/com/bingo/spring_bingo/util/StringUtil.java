package com.bingo.spring_bingo.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

/**
 * 字符串工具类
 *
 * @author bingo
 * @date 2022-03-23 17:44
 */
public class StringUtil {

    private static Logger logger = LoggerFactory.getLogger(StringUtil.class);

    //==========分割符===========
    public static final String SEPARATOR_SEMICOLON = ";";
    public static final String SEPARATOR_MIDDLE_LINE = "-";
    public static final String SEPARATOR_COLON = ":";
    public static final String SEPARATOR_BELOW_LINE = "_";
    public static final String SEPARATOR_COMMA = ",";
    public static final String SEPARATOR_POINT = ".";


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
     * 字符串连接，按照指定的分隔符
     *
     * @param str
     * @return
     */
    public static String join(String... str) {
        return join(str, "");
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

    public static <T> String join(Iterable<T> iterable, String separator) {
        if (iterable == null) {
            return null;
        }
        return join(iterable.iterator(), separator);
    }

    public static <T> String join(Iterator<T> iterator, String separator) {
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

    /**
     * 汉字转拼音,保留无法转换的
     *
     * @param message
     * @return
     */
    public static String getPinYinString(String message) {
        return getPinYinString(message, true, "", true);
    }

    /**
     * 汉字转拼音
     *
     * @param message
     * @param isLow    true 小写  false 大写
     * @param separate 分隔符
     * @param retain   是否保留无法转换的字符
     * @return
     */
    public static String getPinYinString(String message, boolean isLow, String separate, boolean retain) {
        if (isNull(message)) {
            return null;
        }
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        if (isLow) {
            // 拼音小写
            format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        } else {
            // 拼音大写
            format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        }
        // 不带声调
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        try {
            return PinyinHelper.toHanYuPinyinString(message, format, separate, retain);
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            logger.warn("字符串转化为拼音出现异常：" + e);
            return null;
        }
    }

    /**
     * 是否包含字符串
     *
     * @param str  验证字符串
     * @param strs 字符串组
     * @return 包含返回true
     */
    public static boolean inStringIgnoreCase(String str, String... strs) {
        if (StringUtil.isNotNull(str) && !ObjectUtil.isNull(strs)) {
            for (String s : strs) {
                if (str.equalsIgnoreCase(s.trim())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 首字母转换小写
     *
     * @param str 需要转换的字符串
     * @return 转换好的字符串
     */
    public static String firstToLowerCase(String str) {
        if (isNull(str)) {
            return "";
        }
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }
}

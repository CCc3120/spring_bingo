package com.bingo.spring_bingo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 地址工具类
 *
 * @author bingo
 * @date 2022-04-29 11:24
 */
public class AddressUtil {
    private static final Logger log = LoggerFactory.getLogger(AddressUtil.class);

    // IP地址查询
    public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";

    // 未知地址
    public static final String UNKNOWN = "XX XX";

    public static String getRealAddressByIP(String ip) {
        // 内网不查询
        if (IPUtil.internalIp(ip)) {
            return "内网IP";
        }
        try {
            // String rspStr = HttpUtils.sendGet(IP_URL, "ip=" + ip + "&json=true", Constants.GBK);
            // if (StringUtil.isNull(rspStr)) {
            //     log.error("获取地理位置异常 {}", ip);
            //     return UNKNOWN;
            // }
            // JSONObject obj = JSONObject.parseObject(rspStr);
            // String region = obj.getString("pro");
            // String city = obj.getString("city");
            // return String.format("%s %s", region, city);
        } catch (Exception e) {
            log.error("获取地理位置异常 {}", ip);
        }
        return UNKNOWN;
    }
}

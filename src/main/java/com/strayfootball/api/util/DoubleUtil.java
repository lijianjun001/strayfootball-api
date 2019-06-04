package com.strayfootball.api.util;

import java.math.BigDecimal;

/**
 * 浮点工具类
 *
 * @author karl
 */
public class DoubleUtil {
    /**
     * 浮点精确小数点后N位
     * @param d 浮点
     * @param formatStr 格式:0.00
     * @return 被格式化的浮点，字符串
     */
    public static String toFixed(double d, String formatStr) {
        java.text.DecimalFormat df = new java.text.DecimalFormat(formatStr);
        return df.format(d);
    }

    /**
     * 浮点精确小数点后N位并舍弃小数点后的0
     *
     * @param d         浮点
     * @return 被格式化的浮点，字符串
     */
    public static String toFixedAbandon0(double d) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
        String s = df.format(d);
        if (s.indexOf(".") > 0) {
            //去掉后面无用的零
            s = s.replaceAll("0+?$", "");
            //如小数点后面全是零则去掉小数点
            s = s.replaceAll("[.]$", "");
        }
        return s;
    }

    /**
     * 浮点精确小数点后N位
     * @param d 浮点
     * @param formatStr 格式:0.00
     * @return 被格式化的浮点，字符串
     */
    public static String toFixed(BigDecimal d, String formatStr) {
        java.text.DecimalFormat df = new java.text.DecimalFormat(formatStr);
        return df.format(d);
    }
}

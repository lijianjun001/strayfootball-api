package com.strayfootball.api.util;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串辅助类
 *
 * @author karl
 */
public class StringUtil {

    public static String getObjectValue(Object obj){
        return obj==null?"":obj.toString();
    }

    /***
     * 空或者Null返回默认字符串
     * @param str 被判断的字符串
     * @return 字符串
     */
    public static String nullOrEmptyReturnDefault(String str) {
        return isNullOrEmpty(str) ? "" : str;
    }

    /***
     * 判断字符串不为null并且不为空
     * @param str 被校验的字符串
     * @return true 空 ，false非空
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || "".equals(str);
    }

    /***
     * 过来html标签
     * @param htmlStr 被过滤字符串
     * @return 过滤后字符串
     */
    public static String filterHTMLTag(String htmlStr) {
        String regexScript = "<script[^>]*?>[\\s\\S]*?</script>";
        String regexStyle = "<style[^>]*?>[\\s\\S]*?</style>";
        String regexHtml = "<[^>]+>";

        Pattern pScript = Pattern.compile(regexScript, Pattern.CASE_INSENSITIVE);
        Matcher mScript = pScript.matcher(htmlStr);
        htmlStr = mScript.replaceAll("");

        Pattern pStyle = Pattern.compile(regexStyle, Pattern.CASE_INSENSITIVE);
        Matcher mStyle = pStyle.matcher(htmlStr);
        htmlStr = mStyle.replaceAll("");

        Pattern pHtml = Pattern.compile(regexHtml, Pattern.CASE_INSENSITIVE);
        Matcher mHtml = pHtml.matcher(htmlStr);
        htmlStr = mHtml.replaceAll("");

        return htmlStr.trim();
    }

    /***
     * 截取字符串
     * @param str 被截取字符串
     * @param length 长度
     * @return 截取后的字符串
     */
    public static String subStr(String str, int length) {
        length = str.length() < length ? str.length() : length;
        if (length < 1) {
            return str;
        }
        return str.substring(0, length - 1);
    }

    /**
     * 替换指定标签的属性和值
     * @param str 需要处理的字符串
     * @param tag 标签名称
     * @param tagAttrib 要替换的标签属性值
     * @param startTag 新标签开始标记
     * @param endTag 新标签结束标记
     * @return 被替换后的字符串
     */
    public static String replaceHtmlTag(String str, String tag, String tagAttrib, String startTag, String endTag) {
        String regxpForTag = "<\\s*" + tag + "\\s+([^>]*)\\s*" ;
        String regxpForTagAttrib = tagAttrib + "=\\s*\"([^\"]+)\"" ;
        Pattern patternForTag = Pattern.compile (regxpForTag,Pattern. CASE_INSENSITIVE );
        Pattern patternForAttrib = Pattern.compile (regxpForTagAttrib,Pattern. CASE_INSENSITIVE );
        Matcher matcherForTag = patternForTag.matcher(str);
        StringBuffer sb = new StringBuffer();
        boolean result = matcherForTag.find();
        while (result) {
            StringBuffer sbreplace = new StringBuffer( "<"+tag+" ");
            Matcher matcherForAttrib = patternForAttrib.matcher(matcherForTag.group(1));
            if (matcherForAttrib.find()) {
                String attributeStr = matcherForAttrib.group(1);

                if("img".equals(tag)){
                    if(!(attributeStr.startsWith("http://")||attributeStr.startsWith("https://"))){
                        matcherForAttrib.appendReplacement(sbreplace, startTag + attributeStr + endTag);
                    }
                }else{
                    matcherForAttrib.appendReplacement(sbreplace, startTag + attributeStr + endTag);
                }

            }
            matcherForAttrib.appendTail(sbreplace);
            matcherForTag.appendReplacement(sb, sbreplace.toString());
            result = matcherForTag.find();
        }
        matcherForTag.appendTail(sb);
        return sb.toString();
    }

    /***
     * 验证是否是手机号
     * @param str 被校验字符串
     * @return true 是，false不是
     */
    public static boolean isPhone(String str) {
        return Pattern.matches("^\\d{11}$", str);
    }

    /**
     * 隐藏手机号中间4位
     * @param str
     * @return
     */
    public static String hidePhone(String str) {

        if(str == null || "".equals(str)){
            return "";
        }else {
            str = str.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        }
        return str;
    }


    /**
     * 隐藏身份证号
     * @param str
     * @return
     */
    public static String hideCardNo(String str) {

        if(str == null || "".equals(str)){
            return "";
        }else {
            str = str.replaceAll("(\\d{4})\\d{10}(\\d{4})","$1****$2");
        }
        return str;
    }

    /**
     * 使用java正则表达式去掉多余的.与0
     *
     * @param s 字符串
     * @return 新的字符串
     */
    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            //去掉多余的0
            s = s.replaceAll("0+?$", "");
            //如最后一位是.则去掉
            s = s.replaceAll("[.]$", "");
        }
        return s;
    }

    /***
     * 字符串"周一、周二、周三、周四、周五、周六、周日"转换阿拉伯数字
     * @param week 周"一、二、三、四、五、六、日"
     * @return true 空 ，false非空
     */
    public static int convertWeekToNumber(String week) {
        switch (week) {
            case "周一":
                return 1;
            case "周二":
                return 2;
            case "周三":
                return 3;
            case "周四":
                return 4;
            case "周五":
                return 5;
            case "周六":
                return 6;
            case "周日":
                return 7;
            default:
                return 0;
        }
    }

    /**
     * 格式化string类型保留两位数
     * @param obj 参数
     * @return string
     */
    public static String strFormat(Object obj) {

        if(obj == null || obj.equals("")){
            return "";
        }else {
            obj = String.format("%.2f",obj);
        }
        return obj.toString();
    }


    /**
     * 隐藏手机号后10位
     * @return string
     */
    public static String getHideTenNumber() {

        return "1**********";
    }

    /**
     * 隐藏人名中间为星号
     * @param name 名字
     * @return string
     */
    public static String hideName(String name) {

        String str = "";
        if(name == null || "".equals(name)){
            str =  "";
        }else {
            if (name.length() <= 1){
                 str = "*";
            }else if (name.length() == 2){
                 str = name.charAt(0) + "*";
            }else {
                str = name.charAt(0) + "*" + name.charAt(name.length() - 1);
            }
        }
        return str;
    }

    /***
     * 获取uuuid
     * @return String
     */
    public static String uuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}

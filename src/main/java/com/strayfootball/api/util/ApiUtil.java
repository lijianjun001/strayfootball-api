package com.strayfootball.api.util;


/***
 * 接口工具类
 * @author 孙阿龙
 */
public class ApiUtil {


    /***
     * 生成token
     * @param memeberId memeberId
     * @return token
     */
    public static String token(String memeberId) {
        return StringUtil.uuid() + SecurityUtil.md5(memeberId);
    }

}

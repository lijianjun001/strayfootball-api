package com.strayfootball.api.util;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.Map;

/**
 * 序列化类
 *
 * @author karl
 */
public class SerializeUtil {
    /***
     * 序列化对象
     * @param o 对象
     * @return 序列化后的字符串
     */
    public static String serialize(Object o) {
        try {
            return new Gson().toJson(o);
        } catch (Exception ignored) {
        }
        return "";
    }

    /***
     * 反序列化对象
     * @param jsonStr json字符串
     * @param  c 类
     * @param <T> 一个对象
     * @return 对象
     */
    public static <T> T deSerialize(String jsonStr, Class<T> c) {
        try {

            return new Gson().fromJson(jsonStr, c);
        } catch (Exception ignored) {

        }
        return null;
    }

    /***
     * 根据json字符串的key获取value
     * @param json 字符串
     * @param key key
     * @return value
     */
    public static String getJsonStringValueByKey(String json, String key) {
        if (StringUtil.isNullOrEmpty(json)) {
            return null;
        }
        JsonElement je = new JsonParser().parse(json);

        String value = je.getAsJsonObject().has(key) ? je.getAsJsonObject().get(key).toString() : null;
        return value == null ? null : value.replace("\"", "");
    }

    /***
     * JSon字符串转字典
     * @param jsonStr
     * @return
     */
    public static Map<String, Object> jsonStrToMap(String jsonStr) {
        return JSON.parseObject(jsonStr);
    }
}

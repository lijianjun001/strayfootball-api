package com.strayfootball.api.authorization.context;
import com.strayfootball.api.authorization.constatns.CommonConstants;
import com.strayfootball.api.util.StringUtil;

import java.util.HashMap;
import java.util.Map;


/**
 * @author karl
 */
public class BaseContextHandler {
    public static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

    public static void set(String key, Object value) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        map.put(key, value);
    }

    public static Object get(String key) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        return map.get(key);
    }

    public static int getCustomerId() {
        Object value = get(CommonConstants.CONTEXT_KEY_USER_ID);
        if (null == value) {
            return 0;
        }
        return Integer.parseInt(returnObjectValue(value));
    }

    public static String getToken() {
        Object value = get(CommonConstants.CONTEXT_KEY_USER_TOKEN);
        return StringUtil.getObjectValue(value);
    }

    public static String getPlatform() {
        Object value = get(CommonConstants.CONTEXT_KEY_SYSTEM_PLATFORM);
        return StringUtil.getObjectValue(value);
    }

    public static void setToken(String token) {
        set(CommonConstants.CONTEXT_KEY_USER_TOKEN, token);
    }

    public static void setCustomerId(int customerId) {
        set(CommonConstants.CONTEXT_KEY_USER_ID, customerId);
    }

    public static void setPlatform(String platform) {
        set(CommonConstants.CONTEXT_KEY_SYSTEM_PLATFORM, platform);
    }

    private static String returnObjectValue(Object value) {
        return value == null ? null : value.toString();
    }

    public static void remove() {
        threadLocal.remove();
    }

}

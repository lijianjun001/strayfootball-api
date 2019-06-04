package com.strayfootball.api.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 常量
 *
 * @author karl
 */
public class Constants {


    /**
     * 每页显示条数
     */
    public static final int PAGE_SIZE = 20;

    /**
     * 存储当前登录用户id的字段名
     */
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";

    /**
     * token有效期（小时）
     */
    public static final int TOKEN_EXPIRES_HOUR = 72;

    /**
     * 存放Authorization的header字段
     */
    public static final String AUTHORIZATION = "Authorization";

    /**
     * 存放PLATFORM的header字段
     */
    public static final String PLATFORM = "Platform";








    //region redis 锁
    /**
     * 签到lock锁
     */
    public static final String LOCK_SIGN_KEY = "LOCK_SIGN_";
    //endregion

    /**
     * 日期格式
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 日期格式
     */
    public static final String DATE_FORMAT_CHINA = "yyyy年MM月dd日";

    /**
     * 日期时间格式
     */
    public static final String DATETIME_FORMAT_CHINA = "yyyy年MM月dd日 HH:mm:ss";

    /**
     * 日期时间格式
     */
    public static final String DATETIME_FORMAT_CHINA_SHORT = "MM月dd日 HH:mm";

    /**
     * 日期时间格式
     */
    public static final String DATETIME_FORMAT_MONTH_DAY = "MM月dd日";

    /**
     * 日期格式
     */
    public static final String DATE_FORMAT_DOT = "yyyy.MM.dd";

    /**
     * 日期格式
     */
    public static final String DATE_FORMAT_MM_DD = "MM.dd";

    /**
     * 日期时分秒格式
     */
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期时分秒格式
     */
    public static final String DATE_TIME_FORMAT_DOT = "yyyy.MM.dd HH:mm:ss";

    /**
     * 时分格式
     */
    public static final String TIME_HOUR_MINUTE_FORMAT = "HH:mm";

    /**
     * 月日 时分格式
     */
    public static final String TIME_MONTH_DAY_HOUR_MINUTE_FORMAT = "MM-dd HH:mm";

    /**
     * 日期时分秒字符串格式
     */
    public static final String DATE_TIME_STRING_FORMAT = "yyyyMMddHHmmss";

    /**
     * 年月格式
     */
    public static final String DATE_YYYY_MM = "yyyy年MM月";


    /**
     * 金额格式（两位小数点）
     */
    public static final String PRICE_FORMAT = "0.00";






    }



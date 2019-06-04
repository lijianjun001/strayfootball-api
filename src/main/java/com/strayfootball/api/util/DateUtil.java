package com.strayfootball.api.util;

import com.strayfootball.api.config.Constants;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间工具类
 *
 * @author karl
 */
public class DateUtil {

    private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();

    private static final Object object = new Object();

    private static SimpleDateFormat getDateFormat(String pattern)
            throws RuntimeException {
        SimpleDateFormat dateFormat = threadLocal.get();
        if (dateFormat == null) {
            synchronized (object) {
                if (dateFormat == null) {
                    dateFormat = new SimpleDateFormat(pattern);
                    dateFormat.setLenient(false);
                    threadLocal.set(dateFormat);
                }
            }
        }
        dateFormat.applyPattern(pattern);
        return dateFormat;
    }

//    /**
//     * 给定时间段和星期几，计算该时间段内共有多少个给定的星期几
//     * @param start 开始时间,格式yyyy-MM-dd
//     * @param end 结束时间，格式yyyy-MM-dd
//     * @param a 星期几，从星期一到星期天，分别用数字1-7表示
//     * @return 星期几统计数
//     */
//    private long weekend(String start,String end,int a){
//        DateFormat format = getDateFormat("yyyy-MM-dd");
//        long sunDay = 0;//计数
//        try{
//            Calendar startDate = Calendar.getInstance(); //开始时间
//            startDate.setTime(format.parse(start));
//
//            Calendar endDate = Calendar.getInstance();//结束时间
//            endDate.setTime(format.parse(end));
//
//            int SW = startDate.get(Calendar.DAY_OF_WEEK)-1;//开始日期是星期几
//            int EW = endDate.get(Calendar.DAY_OF_WEEK)-1;//结束日期是星期几
//
//            long diff = endDate.getTimeInMillis()-startDate.getTimeInMillis();
//            long days = diff / (1000 * 60 * 60 * 24);//给定时间段内一共有多少天
//            long w = Math.round(Math.ceil(((days+SW+(7-EW))/7.0)));//给定时间内，共有多少个星期
//            sunDay = w;//总的星期几统计数
//            if(a<SW)//给定的星期几小于起始日期的星期几，需要减少一天
//                sunDay--;
//            if(a>EW)//给定的星期几大于结束日期的星期几，需要减少一天
//                sunDay--;
//        }catch(Exception se){
//            se.printStackTrace();
//        }
//        return sunDay;
//    }


    public static int getWeekOfDate(String strDate) {
        SimpleDateFormat format = getDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(strDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);

        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * get Calendar of given year
     *
     * @param year
     * @return
     */
    private static Calendar getCalendarFormYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.YEAR, year);
        return cal;
    }


    /**
     * get start date of given week no of a year
     *
     * @param year
     * @param weekNo
     * @return
     */
    public static String getStartDayOfWeekNo(int year, int weekNo) {
        Calendar cal = getCalendarFormYear(year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" +
                cal.get(Calendar.DAY_OF_MONTH);

    }

    /**
     * get the end day of given week no of a year.
     *
     * @param year
     * @param weekNo
     * @return
     */
    public static String getEndDayOfWeekNo(int year, int weekNo) {
        Calendar cal = getCalendarFormYear(year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        cal.add(Calendar.DAY_OF_WEEK, 6);
        return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" +
                cal.get(Calendar.DAY_OF_MONTH);
    }


    /**
     * 一个月的第一天
     *
     * @return 时间
     */
    public static Date monthFirstDay() {
        Calendar cale = Calendar.getInstance();
        SimpleDateFormat format = getDateFormat(Constants.DATE_TIME_FORMAT);
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return weeHours(strToDate(format.format(cale.getTime())), 0);
    }


    /**
     * 一个月的最后一天
     *
     * @return 时间
     */
    public static Date monthLastDay() {
        Calendar cale = Calendar.getInstance();
        SimpleDateFormat format = getDateFormat(Constants.DATE_TIME_FORMAT);
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        return weeHours(strToDate(format.format(cale.getTime())), 1);
    }

    /**
     * 指定日期那个月的第一天
     *
     * @return 时间
     */
    public static Date monthFirstDay(Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        SimpleDateFormat format = getDateFormat(Constants.DATE_TIME_FORMAT);
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return weeHours(strToDate(format.format(cale.getTime())), 0);
    }

    /**
     * 指定日期那个月的最后一天
     *
     * @return 时间
     */
    public static Date monthLastDay(Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        SimpleDateFormat format = getDateFormat(Constants.DATE_TIME_FORMAT);
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        return weeHours(strToDate(format.format(cale.getTime())), 1);
    }

    /**
     * 取当天时间凌晨
     *
     * @param date 日期
     * @param day  要添加的天数
     */
    public static Date addDay(Date date, int day) {
        Calendar calendar = new GregorianCalendar();
        //把日期往后增加一天.整数往后推,负数往前移动
        calendar.setTime(date);
        //这个时间就是日期往后推一天的结果
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 给一个时间添加小时
     *
     * @param date 时间
     * @param hour 小时
     * @return 新时间
     */
    public static Date addHours(Date date, int hour) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        return calendar.getTime();
    }

    /**
     * 给一个时间添加小时
     *
     * @param date   时间
     * @param minute 分钟
     * @return 新时间
     */
    public static Date addMinute(Date date, int minute) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 时间格式化成Mongo时间
     *
     * @param date 时间
     * @return 新时间
     */
    public static Date formatMongo(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, 8);
        return calendar.getTime();
    }

    /**
     * 反格式化成Mongo时间
     *
     * @param date 时间
     * @return 新时间
     */
    public static Date formatDMongo(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, -8);
        return calendar.getTime();
    }

    /**
     * 取当天时间凌晨
     */
    public static Date dayBegin() throws ParseException {
        return weeHours(new Date(), 0);
    }

    /**
     * 取当天时间23：59：59
     */
    public static Date dayEnd() throws ParseException {
        return weeHours(new Date(), 1);
    }

    /**
     * 取一个时间00：00：00
     */
    public static Date dayBegin(Date date) {
        try {
            return weeHours(date, 0);
        } catch (Exception ex) {
            return date;
        }
    }

    /**
     * 取一个时间23：59：59
     */
    public static Date dayEnd(Date date) {
        try {
            return weeHours(date, 1);
        } catch (Exception ex) {
            return date;
        }
    }

    /**
     * 取一个时间10：00：00
     */
    public static Date tenPoints(Date date) {
        try {
            return weeHours(date, 2);
        } catch (Exception ex) {
            return date;
        }
    }

    /**
     * 取一个时间的凌晨和23：59：59
     *
     * @param date 日期
     * @param flag 0 取凌晨 1取23：59：59 2取10：00：00
     */
    private static Date weeHours(Date date, int flag) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        //时分秒（毫秒数）
        long millisecond = hour * 60 * 60 * 1000 + minute * 60 * 1000 + second * 1000;
        //凌晨00:00:00
        cal.setTimeInMillis(cal.getTimeInMillis() - millisecond);

        if (flag == 0) {
            return cal.getTime();
        } else if (flag == 1) {
            //凌晨23:59:59
            cal.setTimeInMillis(cal.getTimeInMillis() + 23 * 60 * 60 * 1000 + 59 * 60 * 1000 + 59 * 1000);
        } else if (flag == 2) {
            //10:00:00
            cal.setTimeInMillis(cal.getTimeInMillis() + 10 * 60 * 60 * 1000);
        }
        return cal.getTime();
    }

    /**
     * 把时间格式化成yyyy-MM-dd HH:mm:ss
     *
     * @param date 日期
     */
    public static String formatDefault(Date date) {
        try {
            SimpleDateFormat formater = getDateFormat(Constants.DATE_TIME_FORMAT);
            return formater.format(date);
        } catch (Exception ex) {
            return "";
        }
    }

    /**
     * 把时间格式化成y
     *
     * @param date    日期
     * @param pattern 要格式成的样子
     */
    public static String format(Date date, String pattern) {
        try {
            return getDateFormat(pattern).format(date);
        } catch (Exception ex) {
            return "";
        }
    }

    /**
     * 把时间格式化成yyyy-MM-dd
     *
     * @param date 日期
     */
    public static String formatShort(Date date) {
        try {
            SimpleDateFormat formater = getDateFormat(Constants.DATE_FORMAT);
            return formater.format(date);
        } catch (Exception ex) {
            return "";
        }
    }

    /**
     * 把时间格式化成今天、昨天、以前
     *
     * @param date 日期
     */
    public static String format(Date date) {
        try {
            long paraDate = date.getTime();
            Date now = new Date();
            long todayBegin = dayBegin().getTime();
            long todayEnd = dayEnd().getTime();
            long yesterdayBegin = weeHours(addDay(now, -1), 0).getTime();
            if (paraDate >= todayBegin && paraDate <= todayEnd) {
                String cuur = "今天";
                return cuur + format(date, Constants.TIME_HOUR_MINUTE_FORMAT);
            } else if (paraDate >= yesterdayBegin && paraDate < todayBegin) {
                String cuur = "昨天";
                return cuur + format(date, Constants.TIME_HOUR_MINUTE_FORMAT);
            }
            return format(date, Constants.TIME_MONTH_DAY_HOUR_MINUTE_FORMAT);
        } catch (Exception ex) {
            return "";
        }
    }

    /**
     * 字符串格式化时间
     *
     * @param strDate 时间字符串
     * @return 日期
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = getDateFormat(Constants.DATE_TIME_FORMAT);
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }

    /**
     * 字符串格式化时间
     *
     * @param strDate 时间字符串
     * @return 日期
     */
    public static Date strToShortDate(String strDate) {
        SimpleDateFormat formatter = getDateFormat(Constants.DATE_FORMAT);
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }

    /**
     * 某个日期对应小时
     *
     * @param date 日期
     */
    public static int hourOfDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 某个日期对应的天
     *
     * @param date 日期
     */
    public static int dateOfDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DATE);
    }

    /**
     * 某个日期对应的月
     *
     * @param date 日期
     */
    public static int monthOfDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 两个时间相减得到天
     *
     * @param late  时间
     * @param early 被减时间
     */
    public static int subDateOfDay(Date late, Date early) {

        Calendar calst = Calendar.getInstance();
        Calendar caled = Calendar.getInstance();
        calst.setTime(early);
        caled.setTime(late);
        //设置时间为0时
        calst.set(Calendar.HOUR_OF_DAY, 0);
        calst.set(Calendar.MINUTE, 0);
        calst.set(Calendar.SECOND, 0);
        caled.set(Calendar.HOUR_OF_DAY, 0);
        caled.set(Calendar.MINUTE, 0);
        caled.set(Calendar.SECOND, 0);
        //得到两个日期相差的天数
        return ((int) (caled.getTime().getTime() / 1000) - (int) (calst
                .getTime().getTime() / 1000)) / 3600 / 24;
    }

    /**
     * 两个时间相减得到小时
     *
     * @param date1 时间
     * @param date2 被减时间
     */
    public static long subDateOfHour(Date date1, Date date2) {
        return (date1.getTime() - date2.getTime()) / (60 * 60 * 1000);
    }

    /**
     * 两个时间相减得到秒
     *
     * @param date1 时间
     * @param date2 被减时间
     */
    public static long subDateOfSecond(Date date1, Date date2) {
        return (date1.getTime() - date2.getTime()) / (1000);
    }

    /**
     * 获取当前日期是星期几
     *
     * @param dt 日期
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"日", "一", "二", "三", "四", "五", "六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 获取当前日期是星期几（数字）
     *
     * @param dt 日期
     * @return 当前日期是星期几
     */
    public static int getWeekOfDateNumber(Date dt) {
        int[] weekDays = {7, 1, 2, 3, 4, 5, 6};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 获取当前日期的周一和周日
     *
     * @return 2018年03月12日-2018年03月18日
     */
    public static String getWeekSection(Date date) {
        String weekSection;
        SimpleDateFormat sdf = getDateFormat(Constants.DATE_FORMAT_CHINA);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayWeek == 1) {
            dayWeek = 8;
        }
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);
        Date mondayDate = cal.getTime();
        String weekBegin = sdf.format(mondayDate);
        cal.add(Calendar.DATE, 4 + cal.getFirstDayOfWeek());
        Date sundayDate = cal.getTime();
        String weekEnd = sdf.format(sundayDate);

        weekSection = weekBegin + "-" + weekEnd;
        return weekSection;
    }

    /**
     * 获取当前日期的周一和周五
     *
     * @return 2018年03月12日-2018年03月18日
     */
    public static String getWeekSectionFistToFive(Date date) {
        String weekSection;
        SimpleDateFormat sdf = getDateFormat(Constants.DATE_FORMAT_CHINA);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayWeek == 1) {
            dayWeek = 8;
        }
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);
        Date mondayDate = cal.getTime();
        String weekBegin = sdf.format(mondayDate);
        cal.add(Calendar.DATE, 2 + cal.getFirstDayOfWeek());
        Date sundayDate = cal.getTime();
        String weekEnd = sdf.format(sundayDate);


        weekSection = weekBegin + "-" + weekEnd;
        return weekSection;
    }

    /**
     * 字符串转日期
     *
     * @param str     日期字符串
     * @param partten 转换格式
     * @return Date
     */
    public static Date strToDate(String str, String partten) {
        SimpleDateFormat sdf = getDateFormat(partten);
        try {
            Date d = sdf.parse(str);
            Date d1 = new Date(d.getTime());
            return d1;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 得到某一天是这一年的第几周
     *
     * @param date 某一天
     * @return int 第几周
     */
    public static int getYearWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 格式化时间
     *
     * @param date
     * @return String
     */
    public static String formatToStr(Date date) {
        long delta = System.currentTimeMillis() - date.getTime();

        if (delta < 1L * 60000L) {
            return "现在";
        }

        if (delta < 45L * 60000L) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + "分钟前";
        }

        if (delta < 24L * 3600000L) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + "小时前";
        }

        if (delta < 48L * 3600000L) {
            return "昨天";
        }

        if (delta < 7L * 86400000L) {
            long days = toDays(delta);
            return (days <= 0 ? 1 : days) + "天前";
        }

        if (delta < 30L * 86400000L) {
            long weeks = toWeeks(delta);
            return (weeks <= 0 ? 1 : weeks) + "周前";
        }

        if (delta < 12L * 4L * 604800000L) {
            long months = toMonths(delta);
            return (months <= 0 ? 1 : months) + "月前";
        } else {
            long years = toYears(delta);
            return (years <= 0 ? 1 : years) + "年前";
        }
    }

    /**
     * 判断某个时间是否是在条件的起始时间与结束时间之内
     *
     * @param time 参数
     * @param from 参数
     * @param to   参数
     * @return boolean
     */
    public static boolean belongCalendar(Date time, Date from, Date to) {
        Calendar date = Calendar.getInstance();
        date.setTime(time);
        Calendar after = Calendar.getInstance();
        after.setTime(from);
        Calendar before = Calendar.getInstance();
        before.setTime(to);
        if (date.after(after) && date.before(before)) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 判断某一年是否是闰年
     * 是闰年返回true
     *
     * @param year 参数
     * @return boolean
     */
    public static boolean isBissextile(int year) {
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            return true;
        }
        return false;
    }

    /**
     * 计算具体某月有多少天
     *
     * @param month 参数
     * @param year  参数
     * @return int
     */
    public static int daysOfmonthInyear(int month, int year) {
        int months[] = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        //判断是否是闰年，闰年2月有29 天
        if (isBissextile(year)) {
            months[1] = 29;
        } else {
            months[1] = 28;
        }
        return months[month - 1];
    }


    /**
     * 计算具体某天距离1900年1月1日有多少天数
     *
     * @param day   参数
     * @param month 参数
     * @param year  参数
     * @return int
     */
    public static int daysFromNovecento(int day, int month, int year) {
        //接收天数差值
        int daysSum = 0;
        //将距离1900年的整年天数相加
        for (int i = 1900; i < year; i++) {
            //是闰年则为366天不是为365天
            daysSum += isBissextile(i) ? 366 : 365;
        }
        //计算当年距离1月的整月天数
        for (int j = 1; j < month; j++) {
            daysSum += daysOfmonthInyear(j, year);
        }
        //加上当月天数
        daysSum += day;
        return daysSum;
    }

    /**
     * <p>判断具体某天是星期几
     * <p>return 1 2 3 4 5 6 0
     *
     * @param day   参数
     * @param month 参数
     * @param year  参数
     * @return int
     */
    public static int whichWeek(int day, int month, int year) {
        //1900.1.1是星期一，取余得1234560分别代表星期一到星期天
        return daysFromNovecento(day, month, year) % 7;
    }

    /**
     * 时间差详细信息
     *
     * @param endDate 时间参数
     * @param nowDate 时间参数
     * @return String
     */
    public static String getDatePoor(Date endDate, Date nowDate) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟" + sec + "秒";
    }


    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    private static long toWeeks(long date) {
        return toDays(date) / 7L;
    }

    private static long toMonths(long date) {
        return toDays(date) / 30L;
    }

    private static long toYears(long date) {
        return toMonths(date) / 365L;
    }


    /**
     * 获取天开始日期
     *
     * @return
     */
    public static Date getDayBegin() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取未来 第 past 天的日期
     *
     * @param past
     * @return
     */
    public static Date getFetureDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 获取当天的结束时间
     *
     * @return
     */
    public static Date getDayEnd() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * 获取明天开始日期
     *
     * @return
     */
    public static Date getTomorrow() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * 取得当月天数
     */
    public static int getCurrentMonthLastDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 得到指定月的天数
     */
    public static int getMonthLastDay(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 取得指定日期是月数的第几天
     */
    public static int getCurrentDayIsMonthDay(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int days = ca.get(Calendar.DAY_OF_MONTH);
        return days;
    }

    /**
     * 取得指定日期是月数
     */
    public static String getMonthByDate(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int month = ca.get(Calendar.MONTH) + 1;
        if (month == 1) {
            return "一月";
        }
        if (month == 2) {
            return "二月";
        }
        if (month == 3) {
            return "三月";
        }
        if (month == 4) {
            return "四月";
        }
        if (month == 5) {
            return "五月";
        }
        if (month == 6) {
            return "六月";
        }
        if (month == 7) {
            return "七月";
        }
        if (month == 8) {
            return "八月";
        }
        if (month == 9) {
            return "九月";
        }
        if (month == 10) {
            return "十月";
        }
        if (month == 11) {
            return "十一月";
        }
        if (month == 12) {
            return "十二月";
        }
        return "";
    }

    public static int getYear(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int days = ca.get(Calendar.DAY_OF_YEAR);
        return days;
    }

    public static int getMonth(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int days = ca.get(Calendar.DAY_OF_MONTH);
        return days;
    }

    /**
     * 给指定日期添加一个月
     *
     * @param date
     * @return date
     */
    public static Date addMonth(Date date, int month) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 获取月初
     *
     * @return
     */
    public static Date getMonthStart(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取月末
     *
     * @return
     */
    public static Date getMonthEnd(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * 判断是否是日期格式
     *
     * @param str
     * @return
     */
    public static boolean isValidDate(String str) {
        boolean convertSuccess = true;
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        try {
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     * 获取当前日期的周一
     *
     * @return 2018年03月12日
     */
    public static Date getWeekSectionFist(Date date) {
        SimpleDateFormat sdf = getDateFormat(Constants.DATE_FORMAT_CHINA);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayWeek == 1) {
            dayWeek = 8;
        }
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date mondayDate = cal.getTime();
        return mondayDate;
    }

    /**
     * 获取当前日期的周五
     *
     * @return 2018年03月12日-2018年03月18日
     */
    public static Date getWeekSectionFive(Date date) {
        SimpleDateFormat sdf = getDateFormat(Constants.DATE_FORMAT_CHINA);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayWeek == 1) {
            dayWeek = 8;
        }
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);
        cal.add(Calendar.DATE, 2 + cal.getFirstDayOfWeek());

        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);

        return cal.getTime();
    }
}

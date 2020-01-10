package com.troy.commons.utils;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * 日期工具类
 *
 * @author yudongpo
 * @version 2017-07-20
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    public static final String DATE_SSS = "SSS";

    public static final String NORMAL_TIME_PATTERN = "HH:mm:ss";

    public static final String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final String FORMAT_DATE = "yyyy-MM-dd";

    public static final String FORMAT_DATE1 = "yyyyMMdd";

    public static final String FORMAT_DATE2 = "yyMMdd";

    public static final String FORMAT_MONTH = "yyyy-MM";

    public static final String FORMAT_DATE_TIME = FORMAT_DATE + " " + NORMAL_TIME_PATTERN;

    public static final String DATE_TIME_FORMAT2 = FORMAT_DATE + " " + NORMAL_TIME_PATTERN + " " + DATE_SSS;

    public static final String FORMAT_DATE_TIME_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static final String FORMAT_DATE_TIME_YYYYMMDDHHMM2 = "yyyyMMddHHmm";

    public static final String FORMAT_DATE_TIME_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    public static final String FORMAT_DATE_TIME_235959 = "yyyy-MM-dd 23:59:59";

    public static final String FORMAT_DATE_TIME_YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";

    public static final String FORMAT_DATE_TIME_YYYYMMDDHH0000 = "yyyy-MM-dd HH:00:00";

    public static final String FORMAT_DATE_TIME_ISO8601 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static final String FORMAT_DATE_TIME_ISO8601SS = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public static final String FORMAT_DATE_TIME_T = "yyyy-MM-dd'T'HH:mm:ss";

    public static final String FORMAT_DATE_TIME_YYYYMMDD000000 = FORMAT_DATE + " 00:00:00";

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate(FORMAT_DATE);
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * @return 以字符串形式返回当前时间的long型
     */
    public static String getTime() {
        return System.currentTimeMillis() + "";
    }

    /**
     * @return 以字符串形式返回时间参数的long型
     */
    public static String getTime(Date date) {
        return date.getTime() + "";
    }

    /**
     * 取得指定月份的第一天
     *
     * @param strdate String
     * @return String
     */
    public static String getMonthBegin(String strdate) {
        Date date = parseFormatDate(strdate);
        return formatDate(date, "yyyy-MM") + "-01";
    }

    /**
     * 取得指定月份的第一天(00:00:00)
     *
     * @param strdate
     * @return Calendar
     */
    public static Calendar getDateMonthStartWithStartTime(String strdate) {
        Date date = parseFormatDate(getMonthBegin(strdate));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar;
    }

    /**
     * 取得指定月份的第一天
     *
     * @param strdate String
     * @return String
     */
    public static String getMonthBeginWithTime(String strdate) {
        Date date = parseFormatDate(strdate);
        return formatDate(date, "yyyy-MM") + "-01" + " 00:00:00";
    }

    /**
     * 取得日期开始时间
     *
     * @param date
     * @return
     */
    public static Date getBeginOfDay(Date date) {
        Calendar currentDate = Calendar.getInstance();
        currentDate.clear();
        currentDate.setTime(date);
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.MILLISECOND, 0);
        return (Date) currentDate.getTime().clone();
    }

    /**
     * 取得日期结束时间
     *
     * @param date
     * @return
     */
    public static Date getEndOfDay(Date date) {
        Calendar currentDate = Calendar.getInstance();
        currentDate.clear();
        currentDate.setTime(date);
        currentDate.set(Calendar.HOUR_OF_DAY, 23);
        currentDate.set(Calendar.MINUTE, 59);
        currentDate.set(Calendar.SECOND, 59);
        return (Date) currentDate.getTime().clone();
    }

    /**
     * 取得指定月份的最后一天
     *
     * @param strdate String
     * @return String
     */
    public static String getMonthEnd(String strdate) {
        Date date = parseFormatDate(getMonthBegin(strdate));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return formatDate(calendar.getTime());
    }

    /**
     * 取得指定月份的最后一天(00:00:00)
     *
     * @param strdate String
     * @return String
     */
    public static String getMonthEndWithTime(String strdate) {
        Date date = parseFormatDate(getMonthBegin(strdate));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return formatDate(calendar.getTime()) + " 00:00:00";
    }

    /**
     * 取得指定月份的最后一天(23:59:59)
     *
     * @param strdate String
     * @return String
     */
    public static String getMonthEndWithEndTime(String strdate) {
        Date date = parseFormatDate(getMonthBegin(strdate));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return formatDate(calendar.getTime()) + " 23:59:59";
    }

    /**
     * 取得指定月份的最后一天(23:59:59)
     *
     * @param strdate
     * @return Calendar
     */
    public static Calendar getDateMonthEndWithEndTime(String strdate) {
        Date date = parseFormatDate(getMonthBegin(strdate));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar;
    }

    /**
     * yyyy-MM-dd
     *
     * @param strdate
     * @return
     */
    public static Date parseFormatDate(String strdate) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE);
        try {
            return sdf.parse(strdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static Date parseFormatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE);
        try {
            return sdf.parse(formatDate(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 常用的格式化日期yyyy-MM-dd
     *
     * @param date Date
     * @return String
     */
    public static String formatDate(Date date) {
        return formatDate(date, FORMAT_DATE);
    }

    /**
     * 常用的格式化日期yyyy-MM-dd
     *
     * @param date Date
     * @return String
     */
    public static String formatDateFull(Date date) {
        return formatDate(date, FORMAT_FULL);
    }

    /**
     * 常用的格式化日期yyyyMMdd
     *
     * @param date Date
     * @return String
     */
    public static String formatDateYYYY(Date date) {
        return formatDate(date, "yyyy");
    }

    /**
     * 以指定的格式来格式化日期
     *
     * @param date   Date
     * @param format String
     * @return String
     */
    public static String formatDate(Date date, String format) {
        String result = "";
        if (date != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                result = sdf.format(date);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 格式化Date类型日期为yyyy-MM-dd格式
     */
    public static Date getFormatDate(Date date, String pattern) {
        if (date == null) {
            throw new IllegalArgumentException("date can not be null!");
        }
        if (pattern == null) {
            pattern = FORMAT_DATE;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        String dateStr = sdf.format(date);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 格式化Date类型日期为yyyy-MM-dd格式
     */
    public static String getFormatDateString(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        if (pattern == null) {
            pattern = FORMAT_DATE;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 将Long型转换成Date
     *
     * @param date
     * @return
     */
    public static Date parse(Long date) {
        return new Date(date);
    }

    /**
     * String 转date yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date parse(String date) throws Exception {
        return parse(date, FORMAT_DATE_TIME);
    }

    /**
     * 以指定格式将String转换成Date
     *
     * @param date
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date parse(String date, String format) throws Exception {
        return new SimpleDateFormat(format).parse(date);
    }

    /**
     * 得到几天前的时间
     */
    public static Date getDateBefore(Date d, int day) {

        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    /**
     * 得到几天后的时间
     */
    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    /**
     * 填加月
     *
     * @param date
     * @param months
     * @return
     */
    public static Date addMonths(Date date, int months) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(date.getTime());
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
    }

    /**
     * 填加年
     *
     * @param date
     * @param years
     * @return
     */
    public static Date addYears(Date date, int years) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(date.getTime());
        calendar.add(Calendar.YEAR, years);
        return calendar.getTime();
    }

    /**
     * 填加天
     *
     * @param date
     * @param days
     * @return
     */
    public static Date addDays(Date date, int days) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(date.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    /**
     * 指定时间的
     *
     * @param date
     * @param addHour
     * @return
     */
    public static Date dateAddHour(Date date, int addHour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, addHour);
        return calendar.getTime();
    }

    /**
     * 填加分钟
     *
     * @param date
     * @param minutes
     * @return
     */
    public static Date addMinutes(Date date, int minutes) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(date.getTime());
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

    public static long nextSecondTime(Date date, int amount) {
        final SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE + NORMAL_TIME_PATTERN);
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(sdf.format(date)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.add(Calendar.SECOND, amount);
        return cal.getTimeInMillis();
    }

    /**
     * 两个日期时间差(后-前)
     */
    public static long getTimeDuration(Date start, Date end) {
        if (start == null || end == null) {
            return 0;
        }
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(start);
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(end);
        return endCal.getTimeInMillis() - startCal.getTimeInMillis();
    }

    /**
     * 获得 Calendar
     */
    public static Calendar getCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        return calendar;
    }

    /**
     * 比较两个日期的时间差，超过365天显示##年前，不超过365天显示##天前
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static String compareTo(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return "";
        }

        long startDateLong = startDate.getTime();
        long endDateLong = endDate.getTime();
        if (startDateLong > endDateLong) {
            return "";
        }

        long difference = endDateLong - startDateLong;

        double ri = difference / (1000 * 60 * 60 * 24);

        double nian = difference / (1000 * 60 * 60 * 24 * 365);

        if (nian > 0) {
            return (int) Math.floor(nian) + "年前";
        } else if (ri >= 1) {
            return (int) Math.floor(ri) + "天前";
        } else if (ri >= 0 && ri < 1) {
            return "今天";
        }
        return "";

    }

    /**
     * @return String
     * @Title: TodayByNextYear
     * @Description: TODO(根据当天 ， 计算明年的日期 ， 天数 - 1)
     */
    public static String TodayByNextYear() {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, +1);
        calendar.add(Calendar.DATE, -0);
        date = calendar.getTime();
        return DateUtils.formatDate(date);
    }

    /**
     * oss获取上传 所需获取时间方法
     *
     * @param utcTime
     * @return
     */
    public static Date utc2LocalDate(String utcTime) {

        SimpleDateFormat utcFormater = new SimpleDateFormat(FORMAT_DATE_TIME_ISO8601SS);
        utcFormater.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date gpsUTCDate = null;
        try {
            gpsUTCDate = utcFormater.parse(utcTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return gpsUTCDate;
    }

    /**
     * ali请求所需获取时间方法
     *
     * @return
     */
    public static String gmtLocalDate() {
        SimpleDateFormat gmtFormater = new SimpleDateFormat(FORMAT_DATE_TIME_ISO8601SS);
        gmtFormater.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return gmtFormater.format(new Date());
    }

    /**
     * 获取当前时间的第一天
     *
     * @return
     */
    public static String getFristDay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String firstday;
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        firstday = format.format(cale.getTime()) + " 00:00:00";
        return firstday;
    }

    /**
     * 获取当前时间的最后一天
     *
     * @return
     */
    public static String getLastDay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String lastday;
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        lastday = format.format(cale.getTime()) + " 23:59:59";
        return lastday;
    }

    /**
     * 常用的格式化日期yyyy-MM
     *
     * @param date Date
     * @return String
     */
    public static String formatMonthDate(Date date) {
        return formatDate(date, FORMAT_MONTH);
    }

    /**
     * 获取整月的天数
     *
     * @param year  指定的年份 某一年的
     * @param month 指定的月份
     * @param day   指定的一天
     * @return 整月的天数如 2018-01-01 至
     */
    public static List<Date> getMonthlong(int year, int month, int day) {
        List<Date> monthlongList = new ArrayList<>();
        if (day <= 0) {
            day = 1;
        }
        // 获得当前日期对象
        Calendar cal = Calendar.getInstance();
        // 清除信息
        cal.clear();
        cal.set(Calendar.YEAR, year);
        // 1月从0开始
        cal.set(Calendar.MONTH, month - 1);
        // 设置为1号,当前日期既为本月第一天
        cal.set(Calendar.DAY_OF_MONTH, day);
        int count = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        // 月份的最后一天
        String lastDay = getLastDay(year, month);
        // 今天
        String today = getDate();
        for (int j = 0; j <= (count - 1); j++) {
            cal.add(Calendar.DAY_OF_MONTH, j == 0 ? +0 : +1);

            monthlongList.add(cal.getTime());
            // 如果不是本月截止到月底，如果是本月则截止到今天为止
            if (formatDate(cal.getTime()).equals(lastDay) || formatDate(cal.getTime()).equals(today)) {
                break;
            }
        }
        return monthlongList;
    }


    /**
     * 某年月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getLastDay(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        return formatDate(cal.getTime());
    }

    /**
     * @param day
     * @return
     */
    public static String getSpecifiedDayWeeHours(String day) {

        if (StringUtils.isBlank(day)) {
            return "";
        }

        try {
            return formatDate(parse(day), FORMAT_DATE_TIME_235959);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }


    /**
     * 获取指定月份的某一天
     *
     * @param day          日期字符串格式:如2018-01-01
     * @param specifiedDay 指定日期 如某个月的15号 15
     * @return Date 类型的指定日期
     */
    public static Date getMonthOfSpecifiedDay(String day, int specifiedDay) {
        Calendar cal = Calendar.getInstance();// 获得当前日期对象
        cal.clear();// 清除信息
        cal.setTime(DateUtils.parseFormatDate(day));
        cal.set(Calendar.DAY_OF_MONTH, specifiedDay);// 设置为1号,当前日期既为本月第一天

        return cal.getTime();
    }

    /**
     * 获得当前时间和给定时间的差（ms）
     *
     * @param startTime
     * @return
     */
    public static long getTimeDifference(Date startTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startTime);
        cal.getTimeInMillis();
        return System.currentTimeMillis() - cal.getTimeInMillis();
    }

    /**
     * 获取与当前时间差ms（毫秒）的时间
     *
     * @param ms
     * @return
     */
    public static Date getDateByNowDurationms(long ms) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis() + ms);
        return cal.getTime();
    }

    /**
     * 获取ISO8601时间字符串
     *
     * @return
     */
    public static String getISO8601() {
        return DateFormatUtils.format(new Date(), FORMAT_DATE_TIME_ISO8601);
    }

    /**
     * 获取ISO8601时间字符串
     *
     * @param date
     * @return
     */
    public static String getISO8601(Date date) {
        return DateFormatUtils.format(date, FORMAT_DATE_TIME_ISO8601);
    }

    /**
     * 获取ISO8601时间字符串 -- UTC时间
     *
     * @return
     */
    public static String getISO8601UTCTime() {
        Date now = getUTCTime();
        return getISO8601(now);
    }

    public static Date parseGMT8(String date) {//"GMT+8"
        return parseZone(date, "GMT+8");
    }

    public static Date parseZone(String date, String zone) {
        ZonedDateTime parse1 = ZonedDateTime.parse(date);
        ZonedDateTime parse = ZonedDateTime.ofInstant(parse1.toInstant(), ZoneId.of(zone));
        return Date.from(parse.toInstant());
    }

    /**
     * 获取UTC时间
     *
     * @return
     */
    public static Date getUTCTime() {
        Calendar cal = Calendar.getInstance();
        //获得时区和 GMT-0 的时间差,偏移量
        int offset = cal.get(Calendar.ZONE_OFFSET);
        //获得夏令时  时差
        int dstoff = cal.get(Calendar.DST_OFFSET);
        cal.add(Calendar.MILLISECOND, -(offset + dstoff));
        return cal.getTime();

    }

    /**
     * 1 第一季度 2 第二季度 3 第三季度 4 第四季度
     *
     * @param date
     * @return
     */
    public static int getSeason(Date date) {

        int season = 0;

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
            default:
                break;
        }
        return season;
    }

    /**
     * 取得季度月
     *
     * @param date
     * @return
     */
    public static Date[] getSeasonDate(Date date) {
        Date[] season = new Date[3];

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int nSeason = getSeason(date);
        if (nSeason == 1) {// 第一季度
            c.set(Calendar.MONTH, Calendar.JANUARY);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.FEBRUARY);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.MARCH);
            season[2] = c.getTime();
        } else if (nSeason == 2) {// 第二季度
            c.set(Calendar.MONTH, Calendar.APRIL);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.MAY);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.JUNE);
            season[2] = c.getTime();
        } else if (nSeason == 3) {// 第三季度
            c.set(Calendar.MONTH, Calendar.JULY);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.AUGUST);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.SEPTEMBER);
            season[2] = c.getTime();
        } else if (nSeason == 4) {// 第四季度
            c.set(Calendar.MONTH, Calendar.OCTOBER);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.NOVEMBER);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.DECEMBER);
            season[2] = c.getTime();
        }
        return season;
    }


    /**
     * Creates a date from a long representing milliseconds from epoch
     *
     * @param millisecondsFromEpoch
     * @return the Date object
     */
    public static Date fromMillisUtc(long millisecondsFromEpoch) {

        return new Date(millisecondsFromEpoch);
    }

    /**
     * Converts a date to a UTC String representation
     *
     * @param date
     * @return the formatted date
     */
    public static String toUTCString(Date date) {

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        sd.setTimeZone(TimeZone.getTimeZone("GMT"));
        return sd.format(date);
    }

    /**
     * Converts an ISO formatted Date String to a Java Date ISO format: yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
     *
     * @param isoFormattedDate
     * @return Date
     * @throws com.fasterxml.jackson.databind.exc.InvalidFormatException
     */
    public static Date fromISODateString(String isoFormattedDate)
            throws com.fasterxml.jackson.databind.exc.InvalidFormatException {

        SimpleDateFormat isoDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        // set UTC time zone - 'Z' indicates it
        isoDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            return isoDateFormat.parse(isoFormattedDate);
        } catch (ParseException e) {
            throw new InvalidFormatException("Error parsing as date", isoFormattedDate, Date.class);
        }
    }

    /**
     * Converts an ISO 8601 formatted Date String to a Java Date ISO 8601 format:
     * yyyy-MM-dd'T'HH:mm:ss
     *
     * @param iso8601FormattedDate
     * @return Date
     * @throws com.fasterxml.jackson.databind.exc.InvalidFormatException
     */
    public static Date fromISO8601DateString(String iso8601FormattedDate)
            throws com.fasterxml.jackson.databind.exc.InvalidFormatException {

        SimpleDateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        // set UTC time zone
        iso8601Format.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            return iso8601Format.parse(iso8601FormattedDate);
        } catch (ParseException e) {
            throw new InvalidFormatException("Error parsing as date", iso8601FormattedDate, Date.class);
        }
    }

    /**
     * Converts an rfc1123 formatted Date String to a Java Date rfc1123 format: EEE, dd MMM yyyy
     * HH:mm:ss zzz
     *
     * @param rfc1123FormattedDate
     * @return Date
     * @throws com.fasterxml.jackson.databind.exc.InvalidFormatException
     */
    public static Date fromRfc1123DateString(String rfc1123FormattedDate, Locale locale)
            throws com.fasterxml.jackson.databind.exc.InvalidFormatException {

        SimpleDateFormat rfc1123DateFormat =
                new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", locale);
        try {
            return rfc1123DateFormat.parse(rfc1123FormattedDate);
        } catch (ParseException e) {
            throw new InvalidFormatException("Error parsing as date", rfc1123FormattedDate, Date.class);
        }
    }

    /**
     * Converts an RFC3339 formatted Date String to a Java Date RFC3339 format: yyyy-MM-dd HH:mm:ss
     *
     * @param rfc3339FormattedDate RFC3339 formatted Date
     * @return an {@link Date} object
     * @throws InvalidFormatException the RFC3339 formatted Date is invalid or cannot be parsed.
     * @see <a href="https://tools.ietf.org/html/rfc3339">The Internet Society - RFC 3339</a>
     */
    public static Date fromRfc3339DateString(String rfc3339FormattedDate)
            throws InvalidFormatException {

        SimpleDateFormat rfc3339DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return rfc3339DateFormat.parse(rfc3339FormattedDate);
        } catch (ParseException e) {
            throw new InvalidFormatException("Error parsing as date", rfc3339FormattedDate, Date.class);
        }
    }

    /** Convert java time long to unix time long, simply by dividing by 1000 */
    public static long toUnixTime(long javaTime) {
        return javaTime / 1000;
    }

    /** Convert java time to unix time long, simply by dividing by the time 1000 */
    public static long toUnixTime(Date time) {
        return time.getTime() / 1000;
    }

    /** Convert java time to unix time long, simply by dividing by the time 1000. Null safe */
    public static Long toUnixTimeNullSafe(Date time) {

        return time == null ? null : time.getTime() / 1000;
    }

    public static Long toMillisNullSafe(Date time) {

        return time == null ? null : time.getTime();
    }

    /** Convert unix time to Java Date */
    public static Date fromUnixTime(long unix) {
        return new Date(unix * 1000);
    }

}

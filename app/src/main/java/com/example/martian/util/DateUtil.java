package com.example.martian.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author martian on 2017/11/21.
 */

public class DateUtil {

  public static final String DEFAULT_FORMAT = "yyyy-MM-dd";
  public static final int WEEKDAYS = 7;
  public static String[] WEEK = new String[]{"周日", "周一", "周二", "周三", "周四", "周五", "周六"};

  public DateUtil() {
  }

  public static String date2String(Date time, String format) {
    try {
      SimpleDateFormat e = new SimpleDateFormat(format);
      String date = e.format(time);
      return date;
    } catch (Exception var4) {
      return "";
    }
  }

  public static long getGMTime1() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    sdf.setTimeZone(TimeZone.getTimeZone("Etc/Greenwich"));
    String format = sdf.format(new Date());
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date gmDate = null;

    try {
      gmDate = sdf1.parse(format);
    } catch (ParseException var5) {
      var5.printStackTrace();
    }

    return gmDate.getTime() / 1000L;
  }

  public static long getGMTime2() {
    int round = (int)(System.currentTimeMillis() / 1000L);
    return (long)round;
  }

  public static String getCurrentTime() {
    String time = null;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String date = sdf.format(new Date());
    String[] split = date.split("\\s");
    if(split.length > 1) {
      time = split[1];
    }

    return time;
  }

  public static String current() {
    Calendar c = Calendar.getInstance();
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH) + 1;
    int day = c.get(Calendar.DATE);
    int hour = c.get(Calendar.HOUR_OF_DAY);
    int minute = c.get(Calendar.MINUTE);
    int second = c.get(Calendar.SECOND);
    return year + "年" + month + "月" + day + "日" + hour + "时" + minute + "分" + second + "秒";
  }

  public static String getYesterdayDate() {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_MONTH, -1);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String yestoday = sdf.format(calendar.getTime());
    return yestoday;
  }

  public static String getTodayDate() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String date = sdf.format(new Date());
    return date;
  }

  public static String getTomorrowDate() {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_MONTH, 1);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String tomorrow = sdf.format(calendar.getTime());
    return tomorrow;
  }

  public static String timeStampToStr(long timeStamp) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String date = sdf.format(Long.valueOf(timeStamp * 1000L));
    return date;
  }

  public static String timeStampToStr1(long timeStamp) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    String date = sdf.format(Long.valueOf(timeStamp * 1000L));
    return date;
  }

  public static String timeStampToTime(long time) {
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    String date = sdf.format(Long.valueOf(time * 1000L));
    return date;
  }

  public static long getStringToDate(String time) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();

    try {
      date = sdf.parse(time);
    } catch (ParseException var4) {
      var4.printStackTrace();
    }

    return date.getTime();
  }

  public static long getString2Date(String time) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();

    try {
      date = sdf.parse(time);
    } catch (ParseException var4) {
      var4.printStackTrace();
    }

    return date.getTime() / 1000L;
  }

  public static boolean judgeCurrTime(String time) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    new Date();

    try {
      Date date = sdf.parse(time);
      long e = date.getTime();
      long round = System.currentTimeMillis();
      return e - round > 0L;
    } catch (Exception var7) {
      var7.printStackTrace();
      return false;
    }
  }

  public static boolean judgeCurrTime(long time) {
    long round = System.currentTimeMillis();
    return time - round > 0L;
  }

  public static boolean judgeTime2Time(String time1, String time2) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    try {
      Date e = sdf.parse(time1);
      Date date2 = sdf.parse(time2);
      long l1 = e.getTime() / 1000L;
      long l2 = date2.getTime() / 1000L;
      return l2 - l1 > 0L;
    } catch (ParseException var9) {
      var9.printStackTrace();
      return false;
    }
  }

  public static String formatDate(long time) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String date = sdf.format(Long.valueOf(time * 1000L));
    return date;
  }

  public static String getTime(long timeStamp) {
    String time = null;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String date = sdf.format(Long.valueOf(timeStamp * 1000L));
    String[] split = date.split("\\s");
    if(split.length > 1) {
      time = split[1];
    }

    return time;
  }

  public static String timeStampToFormat(long timeStamp) {
    long curTime = System.currentTimeMillis() / 1000L;
    long time = curTime - timeStamp;
    return time / 60L + "";
  }

  public static int nowCurrentTime(long timeStamp) {
    long curTime = System.currentTimeMillis() / 1000L;
    long time = timeStamp - curTime;
    return (int)time;
  }

  public static String nowCurrentPoint(boolean flag) {
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    String date = sdf.format(Long.valueOf(System.currentTimeMillis()));
    String[] split = date.split(":");
    String hour = null;
    String minute = null;
    if(flag) {
      if(split.length > 1) {
        hour = split[0];
        return hour;
      }
    } else if(split.length > 1) {
      minute = split[1];
      return minute;
    }

    return null;
  }

  public static String StandardFormatStr(String str) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    try {
      Date e = sdf.parse(str);
      long timeStamp = e.getTime();
      long curTime = System.currentTimeMillis() / 1000L;
      long time = curTime - timeStamp / 1000L;
      return time / 60L + "";
    } catch (ParseException var9) {
      var9.printStackTrace();
      return null;
    }
  }

  public static String convertTimeToFormat(long timeStamp) {
    long curTime = System.currentTimeMillis() / 1000L;
    long time = curTime - timeStamp;
    return time < 60L && time >= 0L?"刚刚":(time >= 60L && time < 3600L?time / 60L + "分钟前":(time >= 3600L && time < 86400L?time / 3600L + "小时前":(time >= 86400L && time < 2592000L?time / 3600L / 24L + "天前":(time >= 2592000L && time < 31104000L?time / 3600L / 24L / 30L + "个月前":(time >= 31104000L?time / 3600L / 24L / 30L / 12L + "年前":"刚刚")))));
  }

  public static String DateToWeek(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    int dayIndex = calendar.get(Calendar.DAY_OF_WEEK);
    return dayIndex >= 1 && dayIndex <= 7?WEEK[dayIndex - 1]:null;
  }
}

package com.liliuhuan.com.mylibrary.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间工具类
 */
public class TimeUtil {
  private static final SimpleDateFormat YMDHMS =
      new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
  private static final SimpleDateFormat YMD =
      new SimpleDateFormat("yyyy年M月d日", Locale.getDefault());
  private static final SimpleDateFormat MD =
      new SimpleDateFormat("M月d日", Locale.getDefault());
  private static final SimpleDateFormat YMDHM =
      new SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.getDefault());
  private static final SimpleDateFormat YMDS =
      new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
  private static final SimpleDateFormat YMDHM2 =
      new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
  public static final SimpleDateFormat YMD2 =
      new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
  public static final SimpleDateFormat HM =
      new SimpleDateFormat("H:mm", Locale.getDefault());
  public static final SimpleDateFormat E =
      new SimpleDateFormat("E", Locale.getDefault());

  public static String getNextDate(String dateStr, int count) {
    try {
      Date date = YMDS.parse(dateStr);
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      calendar.add(Calendar.MONTH, count);
      return YMD.format(calendar.getTime()).toString();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return dateStr;
  }

  public static String getYmd(String time) {
    if (TextUtils.isEmpty(time)) {
      return "";
    }
    try {
      return YMD.format(YMDHMS.parse(time)).toString();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return time;
  }
  public static String getMd(String time) {
    if (TextUtils.isEmpty(time)) {
      return "";
    }
    try {
      return MD.format(YMDHMS.parse(time)).toString();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return time;
  }
  public static String getYmdhm(String time) {
    if (TextUtils.isEmpty(time)) {
      return "";
    }
    try {
      return YMDHM.format(YMDHMS.parse(time)).toString();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return time;
  }
  public static String getYmdhm2(String time) {
    if (TextUtils.isEmpty(time)) {
      return "";
    }
    try {
      return YMDHM2.format(YMDHMS.parse(time)).toString();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return time;
  }
  public static String getHm(String time) {
    if (TextUtils.isEmpty(time)) {
      return "";
    }
    try {
      return HM.format(YMDHMS.parse(time)).toString();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return time;
  }
  /**
   * 将时间转换为年月日，以线分割
   * @param time
   * @return
   */
  public static String getYmd2(String time) {
    if (TextUtils.isEmpty(time)) {
      return "";
    }
    try {
      return YMD2.format(YMDHMS.parse(time)).toString();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return time;
  }
  /**
   * 将时间转换为年月日，以线分割
   * @param time
   * @return
   */
  public static String getYmds2Ymd2(String time) {
    if (TextUtils.isEmpty(time)) {
      return "";
    }
    try {
      return YMD.format(YMD2.parse(time)).toString();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return time;
  }
  // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
  // HH时mm分ss秒，
  // strTime的时间格式必须要与formatType的时间格式相同
  public static Date stringToDate(String strTime, String formatType)
      throws ParseException {
    if(TextUtils.isEmpty(formatType)){
      formatType = "yyyy-MM-dd";
    }
    SimpleDateFormat formatter = new SimpleDateFormat(formatType);
    Date date = null;
    date = formatter.parse(strTime);
    return date;
  }
  public static String MDToMD2(String strTime)
      throws ParseException {
    SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
    Date date = null;
    date = formatter.parse(strTime);
    String MD2Str = new SimpleDateFormat("MM月dd日").format(date);
    return MD2Str;
  }

  /**
   * 将时间转化为周几
   * @param time
   * @return
   */
  public static String getE(String time){
    if (TextUtils.isEmpty(time)) {
      return "";
    }
    try {
      return E.format(YMDHMS.parse(time)).toString();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return time;
  }
  /**
   * 根据当前时间判断是上午还是下午
   * @param strTime
   * @return
   */
  public static String getAPM(String strTime){
    String apmStr = "";
    Date date = null;
    try {
      date = YMDHMS.parse(strTime);
      final Calendar mCalendar = Calendar.getInstance();
      mCalendar.setTimeInMillis(date.getTime());
      int apm = mCalendar.get(Calendar.AM_PM);
      if(apm == 0){
        apmStr = "上午";
      }else if(apm == 1){
        apmStr = "下午";
      }
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return apmStr;
  }

  /*
* 将时间转换为时间戳
*/
  public static String dateToStamp(String s) throws ParseException{
    String res;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = YMDHMS.parse(s);
    long ts = date.getTime();
    res = String.valueOf(ts);
    return res;
  }
  /*
   * 将时间戳转换为时间
   */
  public static String stampToDate(String s){
    String res;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    long lt = new Long(s);
    Date date = new Date(lt);
    res = simpleDateFormat.format(date);
    return res;
  }
  /*
   * 将时间戳转换为时间
   */
  public static boolean comepareDate(String s,String s2){
    long l1 = Long.valueOf(s);
    long l2 = Long.valueOf(s2);
    double l = Math.abs(l1 - l2) / 1000 / 60 ;
    return l < 5.0 ? true : false;
  }

  /*
  * 将时间戳转换为时间
  */
  public static String stampToDateYDM2(String s){
    String res;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    long lt = new Long(s);
    Date date = new Date(lt);
    res = simpleDateFormat.format(date);
    return res;
  }
}

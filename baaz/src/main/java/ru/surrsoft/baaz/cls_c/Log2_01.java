package ru.surrsoft.baaz.cls_c;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * ПОНЯТИЯ:
 * -- _текст - текст предназначенный для вывода в лог
 * <p>
 * ВЕРСИЯ 4 2019-02-03 (stored)
 */
public class Log2_01 {

  private static long mLogLastTime;
  //--- выводить ли лог //TODO -real
  public static boolean CONF_LOG1 = true;
  //--- префикс обозначающий какое приложение сейчас используется //TODO -real исравить это
  public static String CONF_LOG_PREFIX = ":kd:";
  //--- отображать ли префикс
  public static boolean CONF_LOG_PREFIX_SHOW = true;

  /**
   * [[haug]] - массив подстрок. Если в _тексте будет встречена одна из указанных здесь подстрок,
   * то _текст будет выведен методом Log.v, вне зависимости от того каким методом изначально этот
   * _текст
   * планировалось вывести
   */
  private static final String[] arrStHaug = {"--52", "==21"};

  //constructors
  //``````````````````````````````````````````````````````````````````````````````````````````````

  public Log2_01() {

  }

  //
  //``````````````````````````````````````````````````````````````````````````````````````````````

  public static void v(String tag, String str, boolean... show) {
    if (show.length > 0 && !show[0]) {
      return;
    }
    Log.v(CONF_LOG_PREFIX + tag, str);
  }

  public static void i(String tag, String str, boolean... show) {
    if (show.length > 0 && !show[0]) {
      return;
    }
    //---
    long deviation = System.currentTimeMillis() - mLogLastTime;
    if (deviation > 1000) {
      mLogLastTime = System.currentTimeMillis();
      String specialWordWatch = "";
      Log.i(CONF_LOG_PREFIX + tag,
        String.format("//-----------------------------%d msec > 1000 msec--- %s",
          deviation,
          specialWordWatch));
    }
    //---
    if (!mtHaug(tag, str)) {
      Log.i(CONF_LOG_PREFIX + tag, str);
    } else {
      Log.v(CONF_LOG_PREFIX + tag, str);
    }
  }

  public static void d(String tag, String str, boolean... show) {
    if (show.length > 0 && !show[0]) {
      return;
    }
    if (!mtHaug(tag, str)) {
      Log.d(CONF_LOG_PREFIX + tag, str);
    } else {
      Log.v(CONF_LOG_PREFIX + tag, str);
    }
  }


  /**
   * @param strs (1) --
   * @return --
   */
  private static boolean mtHaug(String... strs) {
    if (arrStHaug == null || arrStHaug.length < 1) {
      return false;
    }
    //---
    for (String eStHaug : arrStHaug) { //LOOP
      for (String eSt : strs) { //LOOP-1
        if (eSt.contains(eStHaug)) {
          return true;
        }
      } //LOOP-1
    } //LOOP
    return false;
  }

  public static void vt(String tag, String str, Context context, boolean... show) {
    if (show.length > 0 && !show[0]) {
      return;
    }
    Log.v(CONF_LOG_PREFIX + tag, str);
    Toast.makeText(context, str, Toast.LENGTH_LONG).show();
  }

  /**
   * Отличие от "v" в том что выводит откуда был вызов метода
   *
   * @param tag  (1) --
   * @param str  (2) --
   * @param show (3) --
   */
  public static void vfrom(String tag, String str, boolean... show) {
    if (show.length > 0 && !show[0]) {
      return;
    }
    StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
    Log.v(CONF_LOG_PREFIX + tag, str + "; from " + stackTraceElements[4]);
  }

  public static void dt(String tag, String str, Context context, boolean... show) {
    if (show.length > 0 && !show[0]) {
      return;
    }
    Log.d(CONF_LOG_PREFIX + tag, str);
    Toast.makeText(context, str, Toast.LENGTH_LONG).show();
  }

  /**
   * Отличие от "v" в том что выводит откуда был вызов метода
   *
   * @param tag
   * @param str
   * @param show
   */
  public static void dfrom(String tag, String str, boolean... show) {
    if (show.length > 0 && !show[0]) {
      return;
    }
    StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
    Log.v(CONF_LOG_PREFIX + tag, str + "; from " + stackTraceElements[4]);
  }

  public static void d2(String tag, Object str, String prefix) {
    String str1 = str == null ? "null" : str.toString();
    //---
    if (!mtHaug(tag, str1, prefix)) {
      Log.d(CONF_LOG_PREFIX + tag, prefix + "=[" + str1 + "]");
    } else {
      Log.v(CONF_LOG_PREFIX + tag, prefix + "=[" + str1 + "]");
    }
  }

  public static void d2(String tag, Object str, String prefix, boolean show) {
    String str1 = str == null ? "null" : str.toString();
    //---
    if (CONF_LOG1 && show) {
      if (!mtHaug(tag, str1, prefix)) {
        Log.d(CONF_LOG_PREFIX + tag, prefix + "=[" + str1 + "]");
      } else {
        Log.v(CONF_LOG_PREFIX + tag, prefix + "=[" + str1 + "]");
      }
    }
  }
}

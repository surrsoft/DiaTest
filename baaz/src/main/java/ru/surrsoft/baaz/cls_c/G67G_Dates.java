package ru.surrsoft.baaz.cls_c;

import android.support.annotation.Nullable;
import android.text.format.DateFormat;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.TimeZone;

/**
 * Работа с датами
 * <p>
 * ПОНЯТИЯ:
 * -- ^[[yata]], _uums - {целое число} UTC-дата (т.е. без учёта временнОй зоны) в формате
 * unixtime ([vhem]), представляет собой количество
 * МИЛИСЕКУНД прошедших с полуночи 1-го января 1970 года
 * -- ^[[eavv]], _uus - {целое число, строка} тоже что и [yata] только в СЕКУНДАХ
 * -- ^[[dkib]] - тип java.util.Date
 * -- ^[[bgit]] - {строка} дата в формате "YYYY-MM-DD", например "2019-04-27"
 * -- ^[[jnne]] - {строка} дата в формате "DD.MM.YYYY", например "27.07.2019"
 * <p>
 */
public class G67G_Dates {

  /**
   * Получение даты в формате "YYYY-MM-DD" ([bgit])
   *
   * @param year           (1) -- например "1978"
   * @param tgtimemonthnil (1) -- 0..11, см. Tgtimemonthnil.java
   * @param dayOfMonth     (3) -- 1..31
   * @return например '2016-03-04'; null если исходные данные не соответствуют корректной дате
   */
  @Nullable
  public static String bgitCreate(int year, int tgtimemonthnil, int dayOfMonth) {
    Calendar c = Calendar.getInstance();
    c.setLenient(false);
    try {
      c.set(Calendar.YEAR, year);
      c.set(Calendar.MONTH, tgtimemonthnil);
      c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
      c.getTimeInMillis();
    } catch (Exception e) {
      return null;
    }
    String y = StringUtils.leftPad(year + "", 4, '0');
    String m = StringUtils.leftPad((tgtimemonthnil + 1) + "", 2, '0');
    String d = StringUtils.leftPad(dayOfMonth + "", 2, '0');
    return y + "-" + m + "-" + d;
  }

  /**
   * Конвертирует дату в с строковом формате "YYYY-MM-DD" ([bgit]) в Calendar
   *
   * @param stBgit (1) -- дата в формате [bgit], например '2016-03-04'
   * @return null при нештатах
   */
  @Nullable
  public static Calendar bgitToCalendar(String stBgit) {
    java.sql.Date date;
    try {
      date = java.sql.Date.valueOf(stBgit);
    } catch (IllegalArgumentException e) {
      return null;
    }
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    return c;
  }

  /**
   * @param stBgit (1) -- дата в формате [bgit] ("YYYY-MM-DD")
   * @return TRUE если (1) это валидная дата [bgit]-дата
   */
  public static boolean bgitValidIs(String stBgit) {
    try {
      java.sql.Date.valueOf(stBgit);
    } catch (IllegalArgumentException e) {
      return false;
    }
    return true;
  }

  /**
   * Конвертирует из даты в формате "YYYY-MM-DD" ([bgit]) в формат unixtimeMillisec ([yata])
   *
   * @param stBgit (1) -- дата в формате [bgit], например "2015-04-02"
   * @return например "1427932800000"; NULL если преобразование невозможно
   */
  @Nullable
  public static Long bgitToYata(String stBgit) {
    if (stBgit == null || stBgit.length() != 10) return null;
    Long ret = null;
    //=
    StringTokenizer sto = new StringTokenizer(stBgit, "-");
    String[] arr = {};
    while (sto.hasMoreTokens()) {
      String s = sto.nextToken();
      arr = ArrayUtils.add(arr, s);
    }
    //=
    int[] arr2 = {};
    for (String s1 : arr) {
      try {
        int i = Integer.parseInt(s1);
        arr2 = ArrayUtils.add(arr2, i);
      } catch (Exception e) {
        return ret;
      }
    }
    //=
    ret = G67G_Dates.yataCreate(arr2[0], arr2[1] - 1, arr2[2]);
    //=
    return ret;
  }

  /**
   * Получение разницы между датами в виде строки
   *
   * @param dateStart (1) -- начальное дата_время
   * @param dateEnd   (2) -- конечное дата_время
   * @param format    (3) -- например "mm:ss" или "mm:ss:SSS"
   * @return например "00:11"
   * #version 1 27-08-2016
   */
  public static String elapsedTime(Date dateStart, Date dateEnd, String format) {
    return DurationFormatUtils.formatDuration(dateEnd.getTime() - dateStart.getTime(),
      format);
  }

  /**
   * "Сжимает" две строки-даты (1) (2)
   *
   * @param date_w143wformat1 (1) -- дата в формате [w143w], например 01.04.2016
   * @param date_w143wformat2 (2) -- дата в формате [w143w], например 05.04.2016
   * @param divider           (3) -- например " ... "
   * @return например "01 ... 05.04.2016"
   * #version 1 10-09-2016
   */
  public static String compressTwoStDates(String date_w143wformat1, String date_w143wformat2, String divider) {
    String ret = "";
    if (divider == null) divider = "";
    if (date_w143wformat1 == null || date_w143wformat2 == null) return ret;
    if (date_w143wformat1.length() != 10 || date_w143wformat2.length() != 10) return ret;
    //
    String s1 = date_w143wformat1.substring(5);
    String s2 = date_w143wformat2.substring(5);
    if (s1.equals(s2)) {
      ret += s1;
    } else {
      return date_w143wformat1 + divider + date_w143wformat2;
    }
    //
    String sa1 = date_w143wformat1.substring(2, 5);
    String sa2 = date_w143wformat2.substring(2, 5);
    if (sa1.equals(sa2)) {
      ret = sa1 + ret;
    } else {
      return date_w143wformat1.substring(0, 5) + divider + date_w143wformat2;
    }
    //
    String sb1 = date_w143wformat1.substring(0, 2);
    String sb2 = date_w143wformat2.substring(0, 2);
    if (sb1.equals(sb2)) {
      ret = sb1 + ret;
    } else {
      return date_w143wformat1.substring(0, 2) + divider + date_w143wformat2;
    }
    return ret;
  }

  /**
   * Получение unixtimemillisec ([yata]) на базе года, месяца, и дня месяца
   *
   * @param year        (1) -- год, например 2016
   * @param monthOfYear (2) -- месяц минус 1, например для января следует указать 0
   * @param dayOfMonth  (3) -- день месяца, например 31
   * @return #version 1 10-04-2016
   */
  public static long yataCreate(int year, int monthOfYear, int dayOfMonth) {
    GregorianCalendar gc = new GregorianCalendar(year, monthOfYear, dayOfMonth);
    return gc.getTime().getTime();
  }

  /**
   * Преобразование строкового представления [yata] (1) в Long
   *
   * @param stYata (1) -- например "1460266975856" или "-1801752301000"
   * @return null если преобразование невозможно
   */
  @Nullable
  public static Long yataStToLong(String stYata) {
    Long ret = null;
    try {
      ret = Long.valueOf(stYata);
    } catch (NumberFormatException e) {

    }
    return ret;
  }

  /**
   * Tgtimeuums.java выраженное строкой
   *
   * @param stYata (1) -- например "1460266975856" или "-1801752301000"
   * @return TRUE если (1) похоже на Tgtimeuums.java
   */
  public static boolean yataStIsValid(String stYata) {
    Long aLong = null;
    try {
      aLong = Long.valueOf(stYata);
    } catch (NumberFormatException e) {

    }
    return aLong != null;
  }

  /**
   * Возвращает день недели в константах Calendar (1 - воскресенье, 2 - понедельник и т.д.)
   * с учтем временной зоны пользователя
   *
   * @param unixtimeMillisecUnixGMT (1) -- unixtime GMT в милисекундах
   * @return
   */
  public static int getDayOfWeekDefaultTimeZone(long unixtimeMillisecUnixGMT) {
    Calendar c = Calendar.getInstance();
    c.setTimeZone(TimeZone.getDefault());
    c.clear();
    c.setTimeInMillis(unixtimeMillisecUnixGMT);
    return c.get(Calendar.DAY_OF_WEEK);
  }

  /**
   * Возвращает unixtime время начала дня в который "попадает" время (1) в заданной временной зоне (2)
   *
   * @param unixtimeMillisecGMT (1) -- unixtime GMT время, милисекунды, например "1432738888000"
   * @param tzs                 (2) -- ноль или одна временная зона. Если 0 то используется GMT
   * @return например "1432684800000"
   */
  public static long getDayStartMillisecUnix(long unixtimeMillisecGMT, TimeZone... tzs) {
    Calendar c = Calendar.getInstance();
    c.setTimeInMillis(unixtimeMillisecGMT);
    int day = c.get(Calendar.DAY_OF_MONTH);

    Calendar c2 = Calendar.getInstance();
    if (tzs.length < 1) {
      c2.setTimeZone(TimeZone.getTimeZone("GMT"));
    } else {
      c2.setTimeZone(tzs[0]);
    }
    c2.clear();
    c2.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), day, 0, 0, 0);
    return c2.getTimeInMillis();
  }

  /**
   * Возвращает unixtime время конца дня в который "попадает" время (1) в заданной временной зоне (2)
   *
   * @param unixtimeMillisecGMT (1) -- unixtime GMT время, милисекунды, например "1432738888000"
   * @param tzs                 (2) -- ноль или одна временная зона. Если 0 то используется GMT
   * @return например "1432771199999"
   */
  public static long getDayEndMillisecUnix(long unixtimeMillisecGMT, TimeZone... tzs) {
    long t = getDayStartMillisecUnix(unixtimeMillisecGMT, tzs);
    t += 24 * 60 * 60 * 1000 - 1;
    return t;
  }

  /**
   * Преобразование [eavv]-даты (1) в [jnne]-дату ("dd.MM.yyyy") с учетом временной зоны по
   * умолчанию
   *
   * @param eavv (1) -- [eavv]-дата UTC; UNIX time в секундах, например 1441357200
   * @return "" если на входе null
   */
  public static String eavvToFormat(Long eavv) {
    if (eavv == null) return ""; //======X
    //---
    long lng = eavv;
    Calendar clr = Calendar.getInstance(TimeZone.getDefault());
    clr.setTimeInMillis(lng * 1000);
    Date d = clr.getTime();
    //---
    return G67G_Dates.dkibFormat(d, "dd.MM.yyyy");
  }

  /**
   * Преобразует (1) (unixtimeMillis ([yata])) в unixtimeSec ([eavv])
   *
   * @param yata (1) -- например 1556390478067
   * @return --
   */
  public static long eavvFromYata(long yata) {
    return yata / 1000;
  }

  /**
   * См. {@link #eavvFromYata(long)}
   *
   * @param yata (1) --
   * @return --
   */
  public static long yataToEavv(long yata) {
    return eavvFromYata(yata);
  }

  /**
   * Преобразование (1) к формату (2) с учетом временной зоны по умолчанию
   *
   * @param eavv   (1) -- unixtimeSec ([eavv]), например 1441357200
   * @param format (2) -- например "dd.MM.yyyy kk:mm:ss"; для дня недели используется "EE"
   * @return "" если на входе null
   */
  public static String eavvToFormat(Long eavv, String format) {
    if (eavv == null) return ""; //======X

    long lng = eavv;
    Calendar clr = Calendar.getInstance(TimeZone.getDefault());
    clr.setTimeInMillis(lng * 1000);
    Date d = clr.getTime();

    return G67G_Dates.dkibFormat(d, format);
  }

  /**
   * Преобразование [eavv]-даты (1) к формату (2) с учетом временной зоны по умолчанию
   *
   * @param eavv   (1) -- unixtimeSec ([eavv]), например 1441357200
   * @param format (2) -- например "dd.MM.yyyy kk:mm:ss"; для дня недели используется "EE"
   * @param GMT    (3) -- временная зона, например "GMT+3" для Москвы
   * @return "" если на входе null
   */
  public static String eavvToFormat(Long eavv, String format, String GMT) {
    if (eavv == null) return ""; //======X
    //---
    long lng = eavv;
    Calendar clr = Calendar.getInstance(TimeZone.getTimeZone(GMT));
    clr.setTimeInMillis(lng * 1000);
    Date d = clr.getTime();
    //---
    return G67G_Dates.dkibFormat(d, format);
  }

  /**
   * Возвращает дату (1) как String в формате (2)
   *
   * @param dkib     (1) -- [dkib]-дата
   * @param stFormat (2) -- формат, например "yyyy.MM.dd kk:mm:ss.SSS" или
   *                 "yyyyMMdd_kkmmssSSS" и т.п.
   */
  public static String dkibFormat(Date dkib, String stFormat) {
    return (String) DateFormat.format(stFormat, dkib);
  }

  /**
   * Текущая дата в [eavv]-формате
   *
   * @return UTC-количество-секунд прошедших с полуночи 1 января 1970 года
   */
  public static long eavvCurrentGet() {
    long sec = (long) Math.floor(Calendar.getInstance().getTime().getTime() / 1000);
    return sec;
  }

  /**
   * Текущее время в [yata]-формате
   *
   * @return количество МИЛИсекунд прошедших с полуночи 1 января 1970 года
   */
  public static long yataCurrentGet() {
    return Calendar.getInstance().getTime().getTime();
  }

  /**
   * Метод-синоним - см. {@link #yataCurrentGet()}
   *
   * @return --
   */
  public static long getTimestamp() {
    return yataCurrentGet();
  }

}

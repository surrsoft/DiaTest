package ru.surrsoft.baaz.univers;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import ru.surrsoft.baaz.Bysa_01;
import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.cls_c.G67G_Draw;

/**
 * Супер часто использующиеся методы
 */
public class U {

  //перехватить
  public static final boolean PEREHVAT = true;

  public static final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
  public static final int MP = ViewGroup.LayoutParams.MATCH_PARENT;

  public static int px(int dp) {
    return G67G_Draw.px(dp);
  }

  //colors
  //``````````````````````````````````````````````````````````````````````````````````````````````
  public static Color2 c2(int color) {
    return new Color2(color);
  }

  /**
   * @param color (1) -- например "#CE93D8"
   * @return --
   */
  public static Color2 c2(String color) {
    return new Color2(Color.parseColor(color));
  }

  public static Color2 c2r(@ColorRes int colorResId) {
    return new Color2(Bysa_01.appContext.getResources().getColor(colorResId));
  }

  /**
   * @param color (1) -- например "#CE93D8"
   * @return --
   */
  @ColorInt
  public static int c(String color) {
    return Color.parseColor(color);
  }

  @ColorInt
  public static int c(@ColorRes int colorRes) {
    return Bysa_01.appContext.getResources().getColor(colorRes);
  }

  public static ColorStateList csl(@ColorInt int color) {
    return ColorStateList.valueOf(color);
  }

  //``````````````````````````````````````````````````````````````````````````````````````````````

  /**
   * Получение слоя из ресурса для последующей его вставки куда либо самостоятельно
   *
   * @param layResId (1) -- resId слоя; например R.layout.lay1
   * @param ctx      (2) --
   * @param <T>      (t1) -- тип слоя который мы инфлейтим
   * @return
   */
  public static <T> T inflate(int layResId, Context ctx) {
    return (T) LayoutInflater.from(ctx).inflate(layResId, null);
  }

  /**
   * Оборачивание (1) в одинарные кавычки
   *
   * @param oj (1) --
   * @return --
   */
  public static String stWrap(Object oj) {
    if (oj != null)
      return "'" + oj.toString() + "'";
    return "null";
  }

  /**
   * Если (1) == NULL то возвращает пустую строку
   *
   * @param st (1) --
   * @return --
   */
  public static String st(String st) {
    return (st == null ? "" : st);
  }

  /**
   * Если (1) == true, бросает исключение с комментарием (2) дополненным слева текстом
   * "(debug) "
   *
   * @param b       (1) --
   * @param comment (2) --
   */
  public static void se(boolean b, String comment) {
    if (b) {
      throw new SomeException("(debug) " + comment);
    }
  }

}

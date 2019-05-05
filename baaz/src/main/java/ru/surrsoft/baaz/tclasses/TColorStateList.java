package ru.surrsoft.baaz.tclasses;

import android.content.res.ColorStateList;

/**
 * Утилитные методы для работы с ColorStateList
 */
public class TColorStateList {

  /**
   * Возвращает TRUE если (1) содержит состояние (2)
   *
   * @param colorStateList (1) --
   * @param iState         (2) -- одна из [fxdc]-констант, например "android.R.attr.state_pressed"
   * @return --
   */
  public static boolean isStateHave(ColorStateList colorStateList, int iState) {
    int colorForState = colorStateList.getColorForState(new int[]{iState}, Integer.MIN_VALUE);
    return colorForState != Integer.MIN_VALUE;
  }
}

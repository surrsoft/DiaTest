package ru.surrsoft.baaz.tclasses;

import android.support.annotation.NonNull;
import android.widget.LinearLayout;

import ru.surrsoft.baaz.univers.U;

/**
 * Утилитные методы для LinearLayout
 * <p>
 * //new//
 */
public class TLinearLayout {

  /**
   * Возвращает TRUE если разметка (1) имеет вертикальную ориентацию
   *
   * @param lay (1) --
   * @return --
   */
  public static boolean isOrientV(@NonNull LinearLayout lay) {
    U.se(lay == null, "");
    return lay.getOrientation() == LinearLayout.VERTICAL;
  }

  /**
   * Возвращает TRUE если разметка (1) имеет горизонтальную ориентацию
   *
   * @param lay (1) --
   * @return --
   */
  public static boolean isOrientH(@NonNull LinearLayout lay) {
    U.se(lay == null, "");
    return lay.getOrientation() == LinearLayout.HORIZONTAL;
  }
}

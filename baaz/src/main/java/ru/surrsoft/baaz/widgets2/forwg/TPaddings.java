package ru.surrsoft.baaz.widgets2.forwg;

import android.util.Log;
import android.view.View;

/*
 * Утилитные функции для работы с paddings
 *
 * ВЕРСИЯ 1 1.0 2018-11-18 (stored)
 */
public class TPaddings {

  /**
   * Применение для view (1) паддингов (2)
   *
   * @param v (1) --
   * @param p (2) -- 0 тоже учитывается
   */
  public static void apply(View v, TxPaddings p) {
    if (v != null && p != null) {
      v.setPadding(p.l_px, p.t_px, p.r_px, p.b_px);
    } else {
      if (v == null) {
        Log.w("", "(WARN) view == null");
      }
      if (p == null) {
        Log.w("", "(WARN) p == null");
      }
    }
  }
}

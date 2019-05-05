package ru.surrsoft.baaz.tclasses;

import android.view.View;

import ru.surrsoft.baaz.SomeException;

/**
 * Утилитные методы для работы с виджетами
 * <p>
 * ВЕРСИЯ 1 1.0 2019-02-05 (stored)
 */
public class TView_01 {

  /**
   * Возвращает TRUE если у виджета (1) есть ViewGroup-родитель (иными словами вставлен ли он уже в
   * какой-либо layout)
   *
   * @param view (1) --
   * @return --
   */
  public static boolean parentHas(View view) {
    if (view == null) {
      throw new SomeException("(debug) NULL");
    }
    return view.getParent() != null;
  }

  /**
   * Возвращает TRUE если виджет (1) имеет ссылку на объект-слушатель-нажатий View.OnClickListener
   *
   * @param view (1) --
   * @return --
   */
  public static boolean listenerHas(View view) {
    if (view == null) {
      throw new SomeException("(debug) NULL");
    }
    return view.hasOnClickListeners();
  }
}

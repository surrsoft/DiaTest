package ru.surrsoft.baaz.tclasses;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.flexbox.FlexboxLayout;

import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.univers.U;

/**
 * ВЕРСИЯ 2 1.1 2018-11-18 (stored)
 * ВЕРСИЯ 1 1.0 2018-11-07 (stored)
 */
public class TLayoutParams_01 {

  /**
   * Создание экземпляра ViewGroup.MarginLayoutParams класса (1)
   *
   * @param clsLayoutParams (1) -- поддерживаются только некоторые классы (см. в коде); если ==NULL, то
   *                        создается и возвращается LinearLayout.LayoutParams
   * @return -- notnull
   */
  public static ViewGroup.MarginLayoutParams create(Class clsLayoutParams) {
    //---
    ViewGroup.MarginLayoutParams lp = null;
    //---
    if (clsLayoutParams != null) {
      if (clsLayoutParams.equals(LinearLayout.LayoutParams.class)) {
        //LinearLayout
        lp = new LinearLayout.LayoutParams(U.WC, U.WC);
      } else if (clsLayoutParams.equals(RelativeLayout.LayoutParams.class)) {
        //RelativeLayout
        lp = new RelativeLayout.LayoutParams(U.WC, U.WC);
      } else if (clsLayoutParams.equals(FlexboxLayout.LayoutParams.class)) {
        //FlexboxLayout
        lp = new FlexboxLayout.LayoutParams(U.WC, U.WC);
      } else if (clsLayoutParams.equals(FrameLayout.LayoutParams.class)) {
        //FrameLayout
        lp = new FrameLayout.LayoutParams(U.WC, U.WC);
      }
    }
    //---
    if (lp == null) {
      //LinearLayout
      lp = new LinearLayout.LayoutParams(U.WC, U.WC);
    }
    //---
    return lp;
  }

  /**
   * Проверяет наличие у виджета (1) layoutParams. Если отсутствуют, создает новые (соответствующие
   * классу (2)) и применяет их для этого виджета
   *
   * @param view            (1) --
   * @param clsLayoutParams (2) -- здесь следует указывать класс родительского для виджета (1)
   *                        слоя. Поддерживаются LinearLayout.LayoutParams,
   *                        RelativeLayout.LayoutParams, FlexboxLayout.LayoutParams,
   *                        FrameLayout.LayoutParams; если указано что либо другое, либо NULL, то
   *                        применяется LinearLayout.LayoutParams
   * @param w_px            (3) --
   * @param h_px            (4) --
   */
  public static void verify(View view, Class clsLayoutParams, int w_px, int h_px) {
    if (view == null) {
      throw new SomeException("(debug)");
    }
    //---
    ViewGroup.LayoutParams lp = view.getLayoutParams();
    //---
    if (lp == null) {
      //---
      if (clsLayoutParams != null) {
        if (clsLayoutParams.equals(RelativeLayout.LayoutParams.class)) {
          lp = new RelativeLayout.LayoutParams(w_px, h_px);
        } else if (clsLayoutParams.equals(FlexboxLayout.LayoutParams.class)) {
          lp = new FlexboxLayout.LayoutParams(w_px, h_px);
        } else if (clsLayoutParams.equals(FrameLayout.LayoutParams.class)) {
          lp = new FrameLayout.LayoutParams(w_px, h_px);
        }
      }
      //---
      if (lp == null) {
        lp = new LinearLayout.LayoutParams(w_px, h_px);
      }
      //---
      view.setLayoutParams(lp);
    }
  }

  /**
   * Отличается от А только тем что дополнительно можно задать margins
   *
   * @param view            (1) --
   * @param clsLayoutParams (2) --
   * @param w_px            (3) --
   * @param h_px            (4) --
   * @param marginL_dp      (5) --
   * @param marginT_dp      (6) --
   * @param marginR_dp      (7) --
   * @param marginB_dp      (8) --
   */
  public static void verify_B(
    View view,
    Class clsLayoutParams,
    int w_px,
    int h_px,
    int marginL_dp,
    int marginT_dp,
    int marginR_dp,
    int marginB_dp
  ) {
    //---
    verify(view, clsLayoutParams, w_px, h_px);
    //---
    ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
    //---
    lp.leftMargin = U.px(marginL_dp);
    lp.topMargin = U.px(marginT_dp);
    lp.rightMargin = U.px(marginR_dp);
    lp.bottomMargin = U.px(marginB_dp);
  }


}

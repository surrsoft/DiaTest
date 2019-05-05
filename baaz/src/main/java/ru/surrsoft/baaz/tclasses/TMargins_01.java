package ru.surrsoft.baaz.tclasses;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.univers.U;

/**
 * ВЕРСИЯ 1 1.0 2018-11-18 (stored)
 */
public class TMargins_01 {

  public static void set(View view, ViewGroup.MarginLayoutParams lp, int l_dp, int t_dp, int r_dp, int b_dp) {
    if (view == null) {
      throw new SomeException("(debug)");
    }
    if (lp == null) {
      throw new SomeException("(debug)");
    }
    //---
    lp.leftMargin = U.px(l_dp);
    lp.topMargin = U.px(t_dp);
    lp.rightMargin = U.px(r_dp);
    lp.bottomMargin = U.px(b_dp);
    //---
    view.setLayoutParams(lp);
  }

  public static void setForLL(View view, int w_dp, int h_dp, int l_dp, int t_dp, int r_dp, int b_dp) {
    set(view, new LinearLayout.LayoutParams(w_dp, h_dp), l_dp, t_dp, r_dp, b_dp);
  }

}

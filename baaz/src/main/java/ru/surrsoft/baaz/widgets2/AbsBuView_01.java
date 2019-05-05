package ru.surrsoft.baaz.widgets2;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.flexbox.FlexboxLayout;

import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.univers.Gravity_01;
import ru.surrsoft.baaz.widgets2.utils.BuCSL;
import ru.surrsoft.baaz.widgets2.forwg.Margins_01;

/**
 * Основа для билдеров виджетов
 * <p>
 * Это только каркас, и некоторые вспомогательные методы, наследники должны сами реализовать
 * (в методе make()) применение
 * заданных билдер-параметров
 * <p>
 * Следует использовать метод {@link #lpConfigure(View)} внутри {@link #build()}
 * для настройки праметров размещения - размеров, margins, gravity и т.д.
 * <p>
 * В конструкторе наследника следует задать значения для {@link #mW_px} и {@link #mH_px}.
 * Например: mW_px = ViewGroup.LayoutParams.MATCH_PARENT;
 * <p>
 * СТАРЫЕ НАЗВАНИЯ: [AbsBuView]
 */
public abstract class AbsBuView_01 {
  protected Margins_01 mMargins;
  private int mW_dp;
  protected int mW_px;
  private int mH_dp;
  protected int mH_px;
  private Class<?> mLayParamClass;
  protected Gravity_01 mGravityOut;
  private Gravity_01 mGravityIn;
  public BuCSL mBgColors;

  //==============================================================================================

  /**
   * Притяжение текущего виджета по отношению к сторонам его родителя
   *
   * @param g (1) --
   * @return --
   */
  public AbsBuView_01 gravityOut(Gravity_01 g) {
    mGravityOut = g;
    return this;
  }

  /**
   * Притежение детей текущего виджета по отношению к сторонам этого виджета
   *
   * @param g (1) --
   * @return --
   */
  public AbsBuView_01 gravityIn(Gravity_01 g) {
    mGravityIn = g;
    return this;
  }

  public AbsBuView_01 margins(Margins_01 ms) {
    mMargins = ms;
    return this;
  }

  public AbsBuView_01 wh(int dp) {
    mW_dp = dp;
    mW_px = G67G_Draw.px(dp);
    mH_dp = dp;
    mH_px = G67G_Draw.px(dp);
    return this;
  }

  public AbsBuView_01 h(int dp) {
    mH_dp = dp;
    mH_px = G67G_Draw.px(dp);
    return this;
  }

  public AbsBuView_01 w(int dp) {
    mW_dp = dp;
    mW_px = G67G_Draw.px(dp);
    return this;
  }

  public AbsBuView_01 bgColors(BuCSL csl) {
    mBgColors = csl;
    return this;
  }

  public AbsBuView_01 layParamClass(Class<?> cls) {
    mLayParamClass = cls;
    return this;
  }

  public AbsBuView_01 wMP() {
    mW_px = ViewGroup.LayoutParams.MATCH_PARENT;
    return this;
  }

  public AbsBuView_01 whMP() {
    mW_px = ViewGroup.LayoutParams.MATCH_PARENT;
    mH_px = ViewGroup.LayoutParams.MATCH_PARENT;
    return this;
  }

  public AbsBuView_01 whWC() {
    mW_px = ViewGroup.LayoutParams.WRAP_CONTENT;
    mH_px = ViewGroup.LayoutParams.WRAP_CONTENT;
    return this;
  }

  public AbsBuView_01 hMP() {
    mH_px = ViewGroup.LayoutParams.MATCH_PARENT;
    return this;
  }

  public AbsBuView_01 wWC() {
    mW_px = ViewGroup.LayoutParams.WRAP_CONTENT;
    return this;
  }

  public AbsBuView_01 hWC() {
    mH_px = ViewGroup.LayoutParams.WRAP_CONTENT;
    return this;
  }

  //make
  //``````````````````````````````````````````````````````````````````````````````````````````````
  public View build() {
    if (mW_px == 0 || mH_px == 0) {
      throw new SomeException("(debug) ширина и/или высота не должны быть = 0");
    }
    //---
    return null;
  }

  //``````````````````````````````````````````````````````````````````````````````````````````````

  /**
   * Задает LayoutParams основываясь на {@link #mLayParamClass}; одновременно назначает размеры
   * основываясь на {@link #mW_px} и {@link #mH_px}; одновременно задает внешний gravity основываясь
   * на {@link #mGravityOut}; одновременно задает margins на базе {@link #mMargins}
   *
   * СТАРЫЕ НАЗВАНИЯ: "settingLP_sizes_gravityOut_margins"
   *
   * @param v (1) --
   * @return --
   */
  protected ViewGroup.MarginLayoutParams lpConfigure(View v) {
    //lp == null если виджет (1) ещё никуда не добавлялся дочерний
    ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
    //=== создание LP на базе mLayParamClass
    if (lp == null) {
      if (mLayParamClass != null) {
        if (mLayParamClass.equals(RelativeLayout.LayoutParams.class)) {
          lp = new RelativeLayout.LayoutParams(mW_px, mH_px);
        } else if (mLayParamClass.equals(FlexboxLayout.LayoutParams.class)) {
          lp = new FlexboxLayout.LayoutParams(mW_px, mH_px);
        } else if (mLayParamClass.equals(FrameLayout.LayoutParams.class)) {
          lp = new FrameLayout.LayoutParams(mW_px, mH_px);
        }
      }
    }
    if (lp == null) {
      lp = new LinearLayout.LayoutParams(mW_px, mH_px);
    }
    //=== gravity
    if (mGravityOut != null) {
      if (lp.getClass().equals(FrameLayout.LayoutParams.class)) {
        ((FrameLayout.LayoutParams) lp).gravity = mGravityOut.val;
      }
      if (lp.getClass().equals(LinearLayout.LayoutParams.class)) {
        ((LinearLayout.LayoutParams) lp).gravity = mGravityOut.val;
      }
    }
    //=== margins
    if (mMargins != null) {
      lp.leftMargin = mMargins.l_px;
      lp.topMargin = mMargins.t_px;
      lp.rightMargin = mMargins.r_px;
      lp.bottomMargin = mMargins.b_px;
    }
    //===
    return lp;
  }

}

package ru.surrsoft.baaz.widgets2.buLay;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import org.apache.commons.lang3.ArrayUtils;

import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.widgets2.nviews.NCardView_01;

/**
 * По умолчанию создается CardView с шириной MP, высотой WC, c paddings == 5dp
 * <p>
 * #id [[w357w]]
 * <p>
 * СТАРЫЕ НАЗВАНИЯ: [BuCardView]
 * КОДЫ: [zipc]
 * <p>
 * ВЕРСИЯ 2 1.1 2019-02-05 (stored)
 */
public class BuLayCardView_01 {
  private final Context context;
  private int mWidth_px;
  private int mHeight_px;
  private int mMarginTop_dp;
  private int mPaddingTop_dp;
  private int mPaddingBottom_dp;
  private int mPaddingRight_dp;
  private int mPaddingLeft_dp;
  private View[] mViews;
  private ColorStateList mColorBgCSL;

  //==============================================================================================

  //constructors
  //``````````````````````````````````````````````````````````````````````````````````````````````
  public BuLayCardView_01(Context context) {
    this.context = context;
    mWidth_px = LinearLayout.LayoutParams.MATCH_PARENT;
    mHeight_px = LinearLayout.LayoutParams.WRAP_CONTENT;
    paddings(5);
  }

  //``````````````````````````````````````````````````````````````````````````````````````````````
  public CardView create() {
    if (mColorBgCSL != null) {
      throw new SomeException("(debug) если задан mColorBgCSL, то нужно использовать метод createN()");
    }
    //===
    CardView _layout = new CardView(context);
    return getCardView(_layout);
  }

  /**
   *
   *
   * @return фактичекий возвращаемый тип - NCardView
   */
  public CardView createN() {
    NCardView_01 _layout = new NCardView_01(context);
    //==
    if (mColorBgCSL != null) {
      _layout.setCardBackgroundColor(mColorBgCSL);
    }
    //==
    return getCardView(_layout);
  }


  //builders
  //``````````````````````````````````````````````````````````````````````````````````````````````

  /**
   * @param csl (1) --
   * @return --
   */
  public BuLayCardView_01 colorBgCSL(ColorStateList csl) {
    mColorBgCSL = csl;
    return this;
  }

  public BuLayCardView_01 wMP() {
    mWidth_px = LinearLayout.LayoutParams.MATCH_PARENT;
    return this;
  }

  public BuLayCardView_01 wWC() {
    mWidth_px = LinearLayout.LayoutParams.WRAP_CONTENT;
    return this;
  }

  public BuLayCardView_01 hMP() {
    mHeight_px = LinearLayout.LayoutParams.MATCH_PARENT;
    return this;
  }

  public BuLayCardView_01 hWC() {
    mHeight_px = LinearLayout.LayoutParams.WRAP_CONTENT;
    return this;
  }

  public BuLayCardView_01 h(int height_dp) {
    mHeight_px = G67G_Draw.px(height_dp);
    return this;
  }

  public BuLayCardView_01 w(int width_dp) {
    mWidth_px = G67G_Draw.px(width_dp);
    return this;
  }

  public BuLayCardView_01 marginTop_dp(int dp) {
    mMarginTop_dp = dp;
    return this;
  }

  public BuLayCardView_01 paddingT(int dp) {
    mPaddingTop_dp = dp;
    return this;
  }

  public BuLayCardView_01 paddingL(int dp) {
    mPaddingLeft_dp = dp;
    return this;
  }

  public BuLayCardView_01 paddingR(int dp) {
    mPaddingRight_dp = dp;
    return this;
  }

  public BuLayCardView_01 paddingB(int dp) {
    mPaddingBottom_dp = dp;
    return this;
  }

  public BuLayCardView_01 paddings(int dp) {
    mPaddingLeft_dp = dp;
    mPaddingTop_dp = dp;
    mPaddingRight_dp = dp;
    mPaddingBottom_dp = dp;
    return this;
  }

  public BuLayCardView_01 addView(View view) {
    mViews = ArrayUtils.add(mViews, view);
    return this;
  }

  //private
  //``````````````````````````````````````````````````````````````````````````````````````````````
  @NonNull
  private CardView getCardView(CardView _layout) {
    FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(mWidth_px, mHeight_px);
    lp.setMargins(0, G67G_Draw.px(mMarginTop_dp), 0, 0);
    _layout.setLayoutParams(lp);
    _layout.setPadding(G67G_Draw.px(mPaddingLeft_dp), G67G_Draw.px(mPaddingTop_dp),
      G67G_Draw.px(mPaddingRight_dp), G67G_Draw.px(mPaddingBottom_dp));
    if (mViews != null && mViews.length > 0) {
      for (View elem : mViews) {
        _layout.addView(elem);
      }
    }

    return _layout;
  }

}

package ru.surrsoft.baaz.widgets2.buLay;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import ru.surrsoft.baaz.tclasses.TColorStateList;
import ru.surrsoft.baaz.univers.Color2;
import ru.surrsoft.baaz.univers.U;
import ru.surrsoft.baaz.widgets2.forwg.Margins_01;
import ru.surrsoft.baaz.widgets2.forwg.Paddings_01;

/**
 * Билдер CardView
 * <p>
 * По умолчанию создаёт CardView с шириной MATCH_PARENT и высотой WRAP_CONTENT,
 * с радиусом закруглений 4dp, с elevation 6dp, с margins 10dp.
 * <p>
 * Т.к. CardView наследуется от FrameLayout, то обычно в него нет смысла вставлять ничего кроме
 * одного LinearLayout, в который в свою очередь вставляется всё остальное.
 * <p>
 * ОПЦИИ:
 * -- в качестве фона можно задать как просто цвет ({@link #buBgColor(Color2)}) , так и
 * ColorStateList ({@link #buBgColor(ColorStateList)}) . Если этот ColorStateList будет
 * содержать состояние android.R.attr.state_pressed, то CardView будет автоматически сделан
 * кликабельным с помощью setClickable(true)
 * <p>
 * ДЕМО:
 * -- см. ^ACardViewDemo^
 */
public class BuLayCardView_02 {

  private final Context mContext;
  private int iElevationPx = U.px(6);
  private int iCornerRadiusPx = U.px(6);
  private ViewGroup layParent;
  private Margins_01 margins = new Margins_01(10);
  private Paddings_01 paddings;
  private int iW_px = ViewGroup.LayoutParams.MATCH_PARENT;
  private int iH_px = ViewGroup.LayoutParams.WRAP_CONTENT;
  private Color2 bgColor;
  private ArrayList<View> childsView = new ArrayList<>();
  private ColorStateList bgCSL;
  private View.OnClickListener onclick;

  //--- constructors
  public BuLayCardView_02(Context ctx) {
    mContext = ctx;
  }

  //--- builders
  public BuLayCardView_02 buElevation(int dp) {
    this.iElevationPx = U.px(dp);
    return this;
  }

  public BuLayCardView_02 buCornerRadius(int dp) {
    this.iCornerRadiusPx = U.px(dp);
    return this;
  }

  public BuLayCardView_02 buAddTo(ViewGroup layParent) {
    this.layParent = layParent;
    return this;
  }

  public BuLayCardView_02 buAddChild(View child) {
    if (child != null) {
      childsView.add(child);
    }
    return this;
  }

  public BuLayCardView_02 buMargins(Margins_01 margins) {
    this.margins = margins;
    return this;
  }

  public BuLayCardView_02 buPaddings(Paddings_01 paddings) {
    this.paddings = paddings;
    return this;
  }

  public BuLayCardView_02 buWMP(boolean b) {
    if (b) {
      iW_px = ViewGroup.LayoutParams.MATCH_PARENT;
    } else {
      iW_px = ViewGroup.LayoutParams.WRAP_CONTENT;
    }
    return this;
  }

  public BuLayCardView_02 buHMP(boolean b) {
    if (b) {
      iH_px = ViewGroup.LayoutParams.MATCH_PARENT;
    } else {
      iH_px = ViewGroup.LayoutParams.WRAP_CONTENT;
    }
    return this;
  }

  public BuLayCardView_02 buBgColor(Color2 bgColor) {
    this.bgColor = bgColor;
    return this;
  }

  public BuLayCardView_02 buBgColor(ColorStateList csl) {
    this.bgCSL = csl;
    return this;
  }

  public BuLayCardView_02 buOnclick(View.OnClickListener onclick) {
    this.onclick = onclick;
    return this;
  }

  //--- build
  public CardView build() {
    CardView cv = new CardView(mContext);
    //--- elevation
    cv.setCardElevation(iElevationPx);
    //--- radius
    cv.setRadius(iCornerRadiusPx);
    //--- lay parent
    if (layParent != null) {
      layParent.addView(cv);
    }
    //--- lp
    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(iW_px, iH_px);
    //--- margins
    if (margins != null) {
      margins.setFor(lp);
    }
    //---
    cv.setLayoutParams(lp);
    //--- paddings
    if (paddings != null) {
      cv.setContentPadding(paddings.l_px, paddings.t_px, paddings.r_px, paddings.b_px);
    }
    //--- color
    if (bgColor != null) {
      cv.setCardBackgroundColor(bgColor.val);
    } else if (bgCSL != null) {
      cv.setCardBackgroundColor(bgCSL);
      //---
      if (TColorStateList.isStateHave(bgCSL, android.R.attr.state_pressed)) {
        cv.setClickable(true);
      }
    }
    //--- child views
    for (View vChild : childsView) {
      cv.addView(vChild);
    }
    //---
    if (onclick != null) {
      cv.setClickable(true);
      cv.setOnClickListener(onclick);
    }
    //---
    return cv;
  }

}

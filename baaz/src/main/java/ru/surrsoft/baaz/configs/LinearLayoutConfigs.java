package ru.surrsoft.baaz.configs;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.univers.Color2;
import ru.surrsoft.baaz.univers.IApply;
import ru.surrsoft.baaz.univers.Size2;


/**
 * #version 1 18.06.2016  #id [[w277w]]
 */
public class LinearLayoutConfigs implements IApply {

  public Size2 sizeWidth;
  public Size2 sizeHeight;

  public boolean border;
  public int borderTh_dp;
  public int borderRadius_dp;
  public Color2 borderColor;
  public Color2 borderFillColor;
  public int borderInset_dp;

  public Integer paddingLeft_dp;
  public Integer paddingTop_dp;
  public Integer paddingRight_dp;
  public Integer paddingBottom_dp;

  public Integer marginLeft_dp;
  public Integer marginTop_dp;
  public Integer marginRight_dp;
  public Integer marginBottom_dp;

  public LinearLayoutConfigs() {
  }

  @Override
  public void apply_w282w(Object view) {
    LinearLayout lay = (LinearLayout) view;
    //=== layout params
    LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) lay.getLayoutParams();
    if (lp == null) lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
      ViewGroup.LayoutParams.WRAP_CONTENT);
    if (sizeWidth != null) lp.width = sizeWidth.val;
    if (sizeHeight != null) lp.height = sizeHeight.val;

    if (marginLeft_dp != null || marginTop_dp != null || marginRight_dp != null || marginBottom_dp != null) {
      int pL = marginLeft_dp == null ? lp.leftMargin : G67G_Draw.px(marginLeft_dp);
      int pT = marginTop_dp == null ? lp.topMargin : G67G_Draw.px(marginTop_dp);
      int pR = marginRight_dp == null ? lp.rightMargin : G67G_Draw.px(marginRight_dp);
      int pB = marginBottom_dp == null ? lp.bottomMargin : G67G_Draw.px(marginBottom_dp);
      lp.setMargins(pL, pT, pR, pB);
    }

    lay.setLayoutParams(lp);
    //=== border
    if (border) {
      GradientDrawable gd = new GradientDrawable();
      boolean b1 = false;
      if (borderFillColor != null) {
        gd.setColor(borderFillColor.val); // Changes this drawbale to use a single textColor instead of a gradient
        b1 = true;
      }
      if (borderTh_dp > 0 && borderColor != null) {
        gd.setCornerRadius(G67G_Draw.px(borderRadius_dp));
        gd.setStroke(G67G_Draw.px(borderTh_dp), borderColor.val);
        b1 = true;
      }
      if (b1) {
        LayerDrawable ld = new LayerDrawable(new Drawable[]{gd});
        ld.setPaddingMode(LayerDrawable.PADDING_MODE_NEST);
        int px = G67G_Draw.px(borderInset_dp);
        ld.setLayerInset(0, px, px, px, px);
        lay.setBackgroundDrawable(ld);
      }
    }
    //===
    if (paddingLeft_dp != null || paddingTop_dp != null || paddingRight_dp != null
      || paddingBottom_dp != null) {
      int pL = paddingLeft_dp == null ? lay.getPaddingLeft() : G67G_Draw.px(paddingLeft_dp);
      int pT = paddingTop_dp == null ? lay.getPaddingTop() : G67G_Draw.px(paddingTop_dp);
      int pR = paddingRight_dp == null ? lay.getPaddingRight() : G67G_Draw.px(paddingRight_dp);
      int pB = paddingBottom_dp == null ? lay.getPaddingBottom() : G67G_Draw.px(paddingBottom_dp);
      lay.setPadding(pL, pT, pR, pB);
    }
  }

  @Override
  public void commit_w282w() {

  }
}

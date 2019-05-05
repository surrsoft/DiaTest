package ru.surrsoft.baaz.configs;

import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.text.util.Linkify;
import android.widget.LinearLayout;
import android.widget.TextView;

import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.univers.Color2;
import ru.surrsoft.baaz.univers.ETypefaces;
import ru.surrsoft.baaz.univers.IApply;
import ru.surrsoft.baaz.univers.Size2;
import ru.surrsoft.baaz.univers.StringL;
import ru.surrsoft.baaz.univers.TwoColors;
import ru.surrsoft.baaz.w265w_configer.ann.N2269_IsConstantsOfClass;
import ru.surrsoft.baaz.w265w_configer.ann.N2269_IsEnum;
import ru.surrsoft.baaz.w265w_configer.ann.N2269_IsTypeface;

/**
 *
 */
public class TextConfigs implements IApply {



  /**
   * Текст с локализацией
   */
  public StringL textText_L;

  public Color2 textColor;
  public Color2 textColorPressed;
  public Float textSize_sp;
  /**
   * Ассоциация: {@link ETypefaces}
   */
  @N2269_IsTypeface
  public String textTypefaceName;
  public boolean textIsClickable;
  public boolean textStyleIsUnderline;
  public boolean textStyleIsBold;
  public boolean textStyleIsItalic;

  public Integer paddingLeft_dp;
  public Integer paddingTop_dp;
  public Integer paddingRight_dp;
  public Integer paddingBottom_dp;

  public Integer marginLeft_dp;
  public Integer marginTop_dp;
  public Integer marginRight_dp;
  public Integer marginBottom_dp;

  public boolean border;
  public int borderTh_dp;
  public int borderRadius_dp;
  public Color2 borderColor;
  public Color2 borderFillColor;
  public int borderInset_dp;

  public Size2 sizeWidth;
  public Size2 sizeHeight;

  @N2269_IsEnum(clazzEnum = TextUtils.TruncateAt.class)
  public String ellipsize = null;
  public int ellipsize_maxLine = 1;

  @N2269_IsConstantsOfClass(clazz = Linkify.class, clazzConstant = int.class)
  public int[] linkifyType;
  public Color2 linkifyLinkColorNormal;
  public Color2 linkifyLinkColorPressed;
  public boolean linkifyLinkIsCliclable;

  public TextConfigs() {

  }

  public void apply_w282w(Object tv0) {
    TextView tv = (TextView) tv0;
    if (tv == null) return;
    //===
    if (textText_L != null) {
      String str = textText_L.getString();
      if (str != null) tv.setText(str);
    }
    //text textColor
    if (textColor != null) {
      tv.setTextColor(textColor.val);
    }
    if (textColorPressed != null) {
      tv.setClickable(true);
      int[][] states = new int[][]{new int[]{android.R.attr.state_pressed}, // pressed
        new int[]{}}; // по умолчанию
      int val = textColor != null ? textColor.val : tv.getCurrentTextColor();
      int[] statesColors = new int[]{textColorPressed.val, val};
      ColorStateList csl = new ColorStateList(states, statesColors);
      tv.setTextColor(csl);
    }
    //=== typeface
    if (textTypefaceName != null) {
      ETypefaces tf = ETypefaces.valueOf(textTypefaceName);
      tv.setTypeface(tf.val);
    }
    //=== underline bold italic
    CharSequence st = tv.getText();
    if (st != null && st.length() > 0) {
      SpannableString ss = null;
      if (textStyleIsUnderline) {
        if (ss == null) ss = new SpannableString(st);
        ss.setSpan(new UnderlineSpan(), 0, ss.length(), 0);
      }
      if (this.textStyleIsBold) {
        if (ss == null) ss = new SpannableString(st);
        ss.setSpan(new StyleSpan(Typeface.BOLD), 0, ss.length(), 0);
      }
      if (this.textStyleIsItalic) {
        if (ss == null) ss = new SpannableString(st);
        ss.setSpan(new StyleSpan(Typeface.ITALIC), 0, ss.length(), 0);
      }
      if (textStyleIsUnderline || textStyleIsBold || textStyleIsItalic) tv.setText(ss);
    }
    //===
    if (textSize_sp != null) tv.setTextSize(textSize_sp);
    tv.setClickable(textIsClickable);
    //===
    if (border) {
      GradientDrawable gd = new GradientDrawable();
      boolean b = false;
      if (borderFillColor != null) {
        gd.setColor(borderFillColor.val); // Changes this drawbale to use a single textColor instead of a gradient
        b = true;
      }
      if (borderTh_dp > 0 && borderColor != null) {
        gd.setCornerRadius(G67G_Draw.px(borderRadius_dp));
        gd.setStroke(G67G_Draw.px(borderTh_dp), borderColor.val);
        b = true;
      }
      if (b) {
        LayerDrawable ld = new LayerDrawable(new Drawable[]{gd});
        ld.setPaddingMode(LayerDrawable.PADDING_MODE_NEST); //!хрен знает почему IDE это подчеркивает!
        int px = G67G_Draw.px(borderInset_dp);
        ld.setLayerInset(0, px, px, px, px);
        tv.setBackgroundDrawable(ld);
      }
    }
    //===
    if (paddingLeft_dp != null || paddingTop_dp != null || paddingRight_dp != null
      || paddingBottom_dp != null) {
      int pL = paddingLeft_dp == null ? tv.getPaddingLeft() : G67G_Draw.px(paddingLeft_dp);
      int pT = paddingTop_dp == null ? tv.getPaddingTop() : G67G_Draw.px(paddingTop_dp);
      int pR = paddingRight_dp == null ? tv.getPaddingRight() : G67G_Draw.px(paddingRight_dp);
      int pB = paddingBottom_dp == null ? tv.getPaddingBottom() : G67G_Draw.px(paddingBottom_dp);
      tv.setPadding(pL, pT, pR, pB);
    }
    //===
    if (sizeWidth != null) {
      LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tv.getLayoutParams();
      if (lp == null) {
        lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
          LinearLayout.LayoutParams.WRAP_CONTENT);
      }
      LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(sizeWidth.val, lp.height);
      tv.setLayoutParams(lp1);
    }
    if (sizeHeight != null) {
      LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tv.getLayoutParams();
      if (lp == null) {
        lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
          LinearLayout.LayoutParams.WRAP_CONTENT);
      }
      LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(lp.width, sizeHeight.val);
      tv.setLayoutParams(lp1);
    }
    //===
    if (marginLeft_dp != null || marginTop_dp != null || marginRight_dp != null || marginBottom_dp != null) {
      LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tv.getLayoutParams();
      boolean b = false;
      if (lp == null) {
        lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
          LinearLayout.LayoutParams.WRAP_CONTENT);
        b = true;
      }
      int pL = marginLeft_dp == null ? lp.leftMargin : G67G_Draw.px(marginLeft_dp);
      int pT = marginTop_dp == null ? lp.topMargin : G67G_Draw.px(marginTop_dp);
      int pR = marginRight_dp == null ? lp.rightMargin : G67G_Draw.px(marginRight_dp);
      int pB = marginBottom_dp == null ? lp.bottomMargin : G67G_Draw.px(marginBottom_dp);

      lp.setMargins(pL, pT, pR, pB);
      if (b) tv.setLayoutParams(lp);
    }
    //=== ellipsize
    if (ellipsize != null) {
      TextUtils.TruncateAt ta = TextUtils.TruncateAt.valueOf(ellipsize);
      tv.setEllipsize(ta);
      if (ellipsize_maxLine > 0) {
        tv.setMaxLines(ellipsize_maxLine);
      }
    }
    //=== linkify
    if (linkifyType != null) {
      int ii;
      if (linkifyType.length == 1) ii = linkifyType[0];
      else {
        ii = linkifyType[0] | linkifyType[1];
        if (linkifyType.length > 2) {
          for (int i = 2; i < linkifyType.length; i++) {
            int elem = linkifyType[i];
            ii = ii | elem;
          }
        }
      }
      tv.setAutoLinkMask(ii);
      if (linkifyLinkColorNormal != null && linkifyLinkColorPressed == null) {
        tv.setLinkTextColor(linkifyLinkColorNormal.val);
      } else if (linkifyLinkColorNormal != null && linkifyLinkColorPressed != null) {
        int[][] states = new int[][]{
          new int[]{android.R.attr.state_pressed},
          new int[]{}
        };
        int[] colors = new int[]{linkifyLinkColorPressed.val, linkifyLinkColorNormal.val};
        ColorStateList csl = new ColorStateList(states, colors);
        tv.setLinkTextColor(csl);
      }
      tv.setLinksClickable(linkifyLinkIsCliclable);
      tv.setText(tv.getText()); //иначе подсветка ссылок не отображается почему-то
    }

  }

  @Override
  public void commit_w282w() {

  }

  public TwoColors convertToTwoColors() {
    TwoColors tc = new TwoColors();
    if (textColor != null) tc.color(textColor.val);
    if (textColorPressed != null) tc.colorPressed(textColorPressed.val);
    if (textSize_sp != null) {
      if (textSize_sp > 0) tc.fontSize(textSize_sp.intValue());
    } else {
      tc.fontSize(14);
    }
    if (textTypefaceName != null) {
      tc.typeface(ETypefaces.valueOf(textTypefaceName).val);
    }
    return tc;
  }

}

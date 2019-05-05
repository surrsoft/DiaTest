package ru.surrsoft.baaz.widgets.buttons;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;

import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.new_.N2269_IsDrawable;
import ru.surrsoft.baaz.univers.Color2;
import ru.surrsoft.baaz.univers.EDrawables;
import ru.surrsoft.baaz.univers.IApply;

/**
 * Кнопка для которой задаются параметры в виде Drawable и цветов для состояний normal, pressed, disable.
 * Текущий класс самостоятельно "перекрашивает" заданный Drawable для вышеуказанных цветов
 * <p/>
 * Параметры задаются одним из методов ySetParams
 * <p/>
 * Для изменения размеров Drawable внутри контейнера можно задавать padding
 * <p/>
 * #version 1 24.05.2016  #id [[w249w]]
 */
public class N2249_BtnPNG extends android.support.v7.widget.AppCompatImageView {


  public N2249_BtnPNG(Context context) {
    super(context);
    init(context);
  }

  public N2249_BtnPNG(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public N2249_BtnPNG(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  private void init(Context context) {
    this.setClickable(true);
  }

  /**
   * @param drw (1) -- Drawable; для лучшего перекрашивания желательно чтобы рисунок изображения был черного цвета
   */
  public void ySetParams(Drawable drw, ColorStateList csl) {
    Drawable drw2 = G67G_Draw.drawableToDrawableColoredState(drw, csl);
    this.setImageDrawable(drw2);
  }

  /**
   * Задание параметров - drawable и цветов
   *
   * @param drw           (1) -- Drawable; для лучшего перекрашивания желательно чтобы рисунок изображения был черного цвета
   * @param normalColor   (2) --
   * @param pressedColor  (3) --
   * @param disabledColor (4) --
   */
  public void ySetParams(Drawable drw, Color2 normalColor, Color2 pressedColor, Color2 disabledColor) {
    StateListDrawable drw2 = G67G_Draw.drawableToStateListDrawable_D(drw, normalColor, pressedColor, disabledColor);
    this.setImageDrawable(drw2);
  }

  public void ySetParams(N2249_Configs configs) {
    if (configs == null || configs.drwName == null) return;
    if (configs.drwPaddings_dp >= 0) {
      int px = G67G_Draw.px(configs.drwPaddings_dp);
      this.setPadding(px, px, px, px);
    }
    ySetParams(EDrawables.valueOf(configs.drwName).val,
      configs.normalColor == null ? new Color2(Color.BLACK) : configs.normalColor,
      configs.pressedColor,
      configs.disabledColor
    );
  }

  public static class N2249_Configs implements IApply {
    @N2269_IsDrawable
    public String drwName;
    public int drwPaddings_dp;
    public Color2 normalColor;
    public Color2 pressedColor;
    public Color2 disabledColor;

    public void apply_w282w(Object btn0) {
      N2249_BtnPNG btn = (N2249_BtnPNG) btn0;
      if (btn != null) {
        btn.ySetParams(this);
      }
    }

    @Override
    public void commit_w282w() {

    }
  }
}

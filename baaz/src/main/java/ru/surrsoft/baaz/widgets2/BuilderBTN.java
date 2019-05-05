package ru.surrsoft.baaz.widgets2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;

import ru.surrsoft.baaz.Bysa_01;
import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.suite.figures.N1208_AbsDrawer;
import ru.surrsoft.baaz.univers.Color2;

/**
 * Билдер кнопки с картинкой. Задается Drawable и цвета для его состояний
 * <p>
 * ПРИМЕРЫ
 * <li> new BuilderFG().make().new Circle() </li>
 * <p>
 * #version 1 13.11.2016  #id [[w396w]]
 */
public class BuilderBTN {
  private static final String TAG = ":" + BuilderBTN.class.getSimpleName();

  private final Context mContext;
  private Drawable mDrawable;
  private int mColorNormal = Color.BLACK;
  private int mColorPressed = Color.RED;
  private int mColorDisabled = Color.LTGRAY;
  private int mW_dp = LinearLayout.LayoutParams.WRAP_CONTENT;
  private int mH_dp = LinearLayout.LayoutParams.WRAP_CONTENT;
  private View.OnClickListener mLst;
  private ViewGroup mLay;
  private int mGravity;
  private int mMarginL_dp;
  private int mMarginT_dp;
  private int mMarginR_dp;
  private int mMarginB_dp;
  private ImageView _iv;
  private ArrayList<N1208_AbsDrawer> mIDrwBgs = new ArrayList<>();

  public BuilderBTN(Context c) {
    this.mContext = c;
  }

  public BuilderBTN drw(Drawable drw) {
    mDrawable = drw;
    return this;
  }

  public BuilderBTN colorNormal(int color) {
    mColorNormal = color;
    return this;
  }

  public BuilderBTN colorPressed(int color) {
    mColorPressed = color;
    return this;
  }

  public BuilderBTN colorDisabled(int color) {
    mColorDisabled = color;
    return this;
  }

  public BuilderBTN w(int dp) {
    mW_dp = dp;
    return this;
  }

  public BuilderBTN h(int dp) {
    mH_dp = dp;
    return this;
  }

  public BuilderBTN wh(int w_dp, int h_dp) {
    mW_dp = w_dp;
    mH_dp = h_dp;
    return this;
  }

  public BuilderBTN wh(int wh_dp) {
    mW_dp = wh_dp;
    mH_dp = wh_dp;
    return this;
  }

  public BuilderBTN onclick(View.OnClickListener lst) {
    mLst = lst;
    return this;
  }

  public void apply(ImageView iv) {
    _iv = iv;
    create();
  }

  public ImageView create() {
    if (_iv == null) _iv = new ImageView(mContext);
    //===
    _iv.setClickable(true);
    if (mLst != null) _iv.setOnClickListener(mLst);
    //===
    if (mIDrwBgs != null && !mIDrwBgs.isEmpty()) {
      ViewTreeObserver vto = _iv.getViewTreeObserver();
      if (vto.isAlive()) {
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
          @Override
          public boolean onPreDraw() {
            _iv.getViewTreeObserver().removeOnPreDrawListener(this);
            int w = _iv.getWidth();
            int h = _iv.getHeight();
            if (w > 0 && h > 0) {
              Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
              Canvas canvas = new Canvas(bmp);
              for (N1208_AbsDrawer elem : mIDrwBgs) {
                elem.drawableStates(_iv.getDrawableState());
                elem.canvas(canvas)
                  .w_px(w)
                  .h_px(h)
                  .draw();
              }
              _iv.setBackgroundDrawable(
                new BitmapDrawable(Bysa_01.appContext.getResources(), bmp));
            }
            return true;
          }
        });
      }
    }
    //===
    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
      mW_dp > 0 ? G67G_Draw.px(mW_dp) : mW_dp, mH_dp > 0 ? G67G_Draw.px(mH_dp) : mH_dp);
    if (mGravity != 0) {
      lp.gravity = mGravity;
    }
    _iv.setLayoutParams(lp);
    //===
    StateListDrawable sld = G67G_Draw.drawableToStateListDrawable_D(
      mDrawable,
      new Color2(mColorNormal),
      new Color2(mColorPressed),
      new Color2(mColorDisabled)
    );
    _iv.setImageDrawable(sld);
    //===
    if (mLay != null) mLay.addView(_iv);
    //=== margins
    if (mMarginL_dp != 0 || mMarginT_dp != 0 || mMarginR_dp != 0 || mMarginB_dp != 0) {
      ViewGroup.LayoutParams lp0 = _iv.getLayoutParams();
      if (lp0 == null) lp0 = new LinearLayout.LayoutParams(mW_dp, mH_dp);
      //=== log
      Class<?> aClass = lp0.getClass();
      //===
      ViewGroup.MarginLayoutParams lp01 = null;
      if (aClass.equals(LinearLayout.LayoutParams.class)) {
        lp01 = (LinearLayout.LayoutParams) lp0;
      }
      if (aClass.equals(RelativeLayout.LayoutParams.class)) {
        lp01 = (RelativeLayout.LayoutParams) lp0;
      }
      if (aClass.equals(FlexboxLayout.LayoutParams.class)) {
        lp01 = (FlexboxLayout.LayoutParams) lp0;
      }
      lp01.leftMargin = G67G_Draw.px(mMarginL_dp);
      lp01.topMargin = G67G_Draw.px(mMarginT_dp);
      lp01.rightMargin = G67G_Draw.px(mMarginR_dp);
      lp01.bottomMargin = G67G_Draw.px(mMarginB_dp);
      _iv.setLayoutParams(lp01);
    }
    //===
    return _iv;
  }

  public BuilderBTN addTo(ViewGroup lay) {
    mLay = lay;
    return this;
  }

  /**
   * Здание выравнивания this относительного контейнера
   *
   * @param align (1) -- например Gravity.RIGHT
   * @return
   */
  public BuilderBTN gravity(int align) {
    mGravity = align;
    return this;
  }

  public BuilderBTN margins(int l_dp, int t_dp, int r_dp, int b_dp) {
    mMarginL_dp = l_dp;
    mMarginT_dp = t_dp;
    mMarginR_dp = r_dp;
    mMarginB_dp = b_dp;
    return this;
  }

  /**
   * Рисует фигуру (1) на фоне. Можно вызывать несколько раз чтобы нарисовать несколько фигур
   *
   * @param idrw (1) --
   * @return
   */
  public BuilderBTN drwBg(N1208_AbsDrawer idrw) {
    mIDrwBgs.add(idrw);
    return this;
  }
}

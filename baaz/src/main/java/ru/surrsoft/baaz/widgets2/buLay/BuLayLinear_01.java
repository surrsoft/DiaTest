package ru.surrsoft.baaz.widgets2.buLay;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.flexbox.FlexboxLayout;

import org.apache.commons.lang3.ArrayUtils;

import ru.surrsoft.baaz.Bysa_01;
import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.suite.experiments.ecud.AnnEcud;
import ru.surrsoft.baaz.suite.experiments.ecud.EEcudTypes;
import ru.surrsoft.baaz.suite.figures.N1208_AbsDrawer;
import ru.surrsoft.baaz.tclasses.TLinearLayout;
import ru.surrsoft.baaz.univers.UniPresenter;
import ru.surrsoft.baaz.widgets2.nviews.NLinearLayout;

/**
 * Конструктор обычных LinearLayout
 * <p>
 * По умолчанию создается вертикальный LinearLayout, с шириной MP и высотой WC
 * <p>
 * СТАРЫЕ НАЗВАНИЯ: [BuilderLL]
 * <p>
 * #id [[w274w]]
 * <p>
 * ВЕРСИЯ 3 1.3 2018-11-18 (stored)
 * ВЕРСИЯ 1.2 01.10.2016
 * <p>
 */
public class BuLayLinear_01 {
  private static final String TAG = ":" + BuLayLinear_01.class.getSimpleName();


  private NLinearLayout _layout;
  protected final Context mContext;
  private int mWidth_px;
  private int mHeight_px;
  private int mMarginT_dp;
  private int mPaddingL_dp;
  private int mPaddingT_dp;
  private int mPaddingR_dp;
  private int mPaddingB_dp;
  private View[] mViews = new View[]{};
  private Integer mBoderColor;
  private Integer mBorderTh_px;
  private int mGravitySelf;
  private int mMarginL_dp;
  private int mMarginR_dp;
  private int mMarginB_dp;

  @AnnEcud(type = EEcudTypes.NODE)
  public N1208_AbsDrawer mDrawer;

  private ViewGroup mParentView;
  private int mGravityIn;
  private Class<?> mLayParamsClass;

  private String mEcudComment;
  private int mBgColor;
  private View[] mChilds = {};
  private Activity mActivityContentView;
  /**
   * Если FALSE то layout будет в состоянии GONE, иначе VISIBLE
   */
  private boolean mVisibility = true;
  private View.OnClickListener onclick;

  //constructors
  //``````````````````````````````````````````````````````````````````````````````````````````````

  /**
   * По умолчанию создается вертикальный LinearLayout, с шириной MP и высотой WC
   *
   * @param context (1) --
   */
  public BuLayLinear_01(Context context) {
    mContext = context;
    _layout = new NLinearLayout(mContext);
    _layout.setOrientation(LinearLayout.VERTICAL);
    mWidth_px = LinearLayout.LayoutParams.MATCH_PARENT;
    mHeight_px = LinearLayout.LayoutParams.WRAP_CONTENT;
  }

  /**
   * По умолчанию создается вертикальный LinearLayout, с шириной MP и высотой WC
   */
  public BuLayLinear_01() {
    this(Bysa_01.appContext);
  }

  //callbacks
  //``````````````````````````````````````````````````````````````````````````````````````````````

  public interface Get {
    void f(NLinearLayout lay, BuLayLinear_01 buLay);
  }

  //make
  //``````````````````````````````````````````````````````````````````````````````````````````````

  public void apply(NLinearLayout lay) {
    _layout = lay;
    build();
  }

  public NLinearLayout build(Get get) {
    NLinearLayout lay = build();
    get.f(lay, this);
    return lay;
  }

  /**
   * @param back (1) -- используется для возврата также ссылки на слой
   * @return --
   */
  public AlertDialog createDialog(@Nullable UniPresenter<NLinearLayout> back) {
    NLinearLayout lay = build();
    AlertDialog.Builder bu = new AlertDialog.Builder(mContext);
    bu.setView(lay);
    if (back != null) {
      back.fun(lay);
    }
    return bu.create();
  }

  /**
   * По умолчанию создается вертикальный LinearLayout, с шириной MP и высотой WC
   *
   * @return --
   */
  public NLinearLayout build() {
    _layout.setBu(this);
    //---
    ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) _layout.getLayoutParams();
    if (lp == null) {
      if (mLayParamsClass != null) {
        if (mLayParamsClass.equals(FlexboxLayout.LayoutParams.class)) {
          lp = new FlexboxLayout.LayoutParams(mWidth_px, mHeight_px);
        } else if (mLayParamsClass.equals(RelativeLayout.LayoutParams.class)) {
          lp = new RelativeLayout.LayoutParams(mWidth_px, mHeight_px);
        } else if (mLayParamsClass.equals(FrameLayout.LayoutParams.class)) {
          lp = new FrameLayout.LayoutParams(mWidth_px, mHeight_px);
        } else {
          lp = new LinearLayout.LayoutParams(mWidth_px, mHeight_px);
        }
      } else {
        //Log.w("BuilderLL", "(debug) (warning)");
        lp = new LinearLayout.LayoutParams(mWidth_px, mHeight_px);
      }
    }
    //---
    lp.setMargins(
      G67G_Draw.px(mMarginL_dp),
      G67G_Draw.px(mMarginT_dp),
      G67G_Draw.px(mMarginR_dp),
      G67G_Draw.px(mMarginB_dp)
    );
    //---
    if (lp.getClass().equals(LinearLayout.LayoutParams.class)) {
      if (mGravitySelf != 0) ((LinearLayout.LayoutParams) lp).gravity = mGravitySelf;
    }
    if (lp.getClass().equals(FrameLayout.LayoutParams.class)) {
      if (mGravitySelf != 0) ((FrameLayout.LayoutParams) lp).gravity = mGravitySelf;
    }
    _layout.setLayoutParams(lp);
    _layout.setGravity(mGravityIn);
    //---
    _layout.setPadding(
      G67G_Draw.px(mPaddingL_dp),
      G67G_Draw.px(mPaddingT_dp),
      G67G_Draw.px(mPaddingR_dp),
      G67G_Draw.px(mPaddingB_dp)
    );
    if (mViews != null && mViews.length > 0) {
      for (View elem : mViews) {
        _layout.addView(elem);
      }
    }
    //---
    _layout.setBackgroundColor(mBgColor);
    //--- border
    mtBorder();
    //--- рисование на фоне
    mtBgDrawing();
    //---
    if (mParentView != null) mParentView.addView(_layout);
    //--- childs
    if (mChilds.length > 0) {
      for (View eChild : mChilds) {
        _layout.addView(eChild);
      }
    }
    //---
    if (onclick != null) {
      _layout.setOnClickListener(onclick);
    }
    //---
    if (mActivityContentView != null) {
      mActivityContentView.setContentView(_layout);
    }
    //---
    _layout.setVisibility(mVisibility ? View.VISIBLE : View.GONE);
    //---
    return _layout;
  }

  private void mtBgDrawing() {
    if (mDrawer != null) {
      _layout.setWillNotDraw(false);
      _layout.drawer(mDrawer);
      ViewTreeObserver vto = _layout.getViewTreeObserver();
      if (vto.isAlive()) {
        vto.addOnPreDrawListener(() -> {
          //борьба с проблемой [w130w]
          mDrawer
            .w_px(_layout.getWidth())
            .h_px(_layout.getHeight());
          return true;
        });
      }
    }
  }

  private void mtBorder() {
    if (mBoderColor != null) {
      //описание: используется техника [w397w] - рисуется GradientDrawable с рамкой и вставляется
      //  в LayerDrawable в котором используется отступ
      if (mBorderTh_px == null) mBorderTh_px = 1;
      GradientDrawable gd = new GradientDrawable();
      gd.setColor(mBgColor);
      gd.setStroke(mBorderTh_px, mBoderColor);
      LayerDrawable ld = new LayerDrawable(new Drawable[]{gd});
      //на самом деле ниже отшибки нет - компилятор пропускает это
      ld.setPaddingMode(LayerDrawable.PADDING_MODE_NEST);
      int px = mBorderTh_px;
      ld.setLayerInset(0, px, px, px, px);
      _layout.setBackgroundDrawable(ld);
    }
  }


  private int getChildsH() {
    int hh = 0;
    for (int i = 0; i < _layout.getChildCount() - 1; i++) {
      View eChild = _layout.getChildAt(i);
      hh += eChild.getHeight();
    }
    return hh;
  }


  public static LinearLayout create2() {
    return new BuLayLinear_01().build();
  }

  //builders
  //``````````````````````````````````````````````````````````````````````````````````````````````

  //region builders

  /**
   * Для добавления созданного слоя в качестве content view в (1)
   *
   * @param a (1) --
   * @return --
   */
  public BuLayLinear_01 asContentView(Activity a) {
    mActivityContentView = a;
    return this;
  }

  public BuLayLinear_01 bgColor(int color) {
    mBgColor = color;
    return this;
  }

  //region border

  public BuLayLinear_01 border(int color) {
    mBoderColor = color;
    return this;
  }

  public BuLayLinear_01 border(int color, int th_dp) {
    mBoderColor = color;
    mBorderTh_px = G67G_Draw.px(th_dp);
    return this;
  }

  //endregion border

  public BuLayLinear_01 addChild(View child) {
    if (child != null) {
      mChilds = ArrayUtils.add(mChilds, child);
    }
    return this;
  }


  //region orientation

  public BuLayLinear_01 horizontal() {
    _layout.setOrientation(LinearLayout.HORIZONTAL);
    return this;
  }

  public BuLayLinear_01 vertical() {
    _layout.setOrientation(LinearLayout.VERTICAL);
    return this;
  }

  public BuLayLinear_01 horizontal(boolean b) {
    if (b) {
      _layout.setOrientation(LinearLayout.HORIZONTAL);
    } else {
      _layout.setOrientation(LinearLayout.VERTICAL);
    }
    return this;
  }

  public BuLayLinear_01 vertical(boolean b) {
    if (b) {
      _layout.setOrientation(LinearLayout.VERTICAL);
    } else {
      _layout.setOrientation(LinearLayout.HORIZONTAL);
    }
    return this;
  }

  //endregion

  //region размеры

  public BuLayLinear_01 wMP() {
    mWidth_px = LinearLayout.LayoutParams.MATCH_PARENT;
    return this;
  }

  public BuLayLinear_01 wWC() {
    mWidth_px = LinearLayout.LayoutParams.WRAP_CONTENT;
    return this;
  }

  public BuLayLinear_01 hMP() {
    mHeight_px = LinearLayout.LayoutParams.MATCH_PARENT;
    return this;
  }

  public BuLayLinear_01 hWC() {
    mHeight_px = LinearLayout.LayoutParams.WRAP_CONTENT;
    return this;
  }

  public BuLayLinear_01 h(int height_dp) {
    mHeight_px = G67G_Draw.px(height_dp);
    return this;
  }

  public BuLayLinear_01 w(int width_dp) {
    mWidth_px = G67G_Draw.px(width_dp);
    return this;
  }

  //endregion размеры

  public BuLayLinear_01 margins(int l_dp, int t_dp, int r_dp, int b_dp) {
    mMarginL_dp = l_dp;
    mMarginT_dp = t_dp;
    mMarginR_dp = r_dp;
    mMarginB_dp = b_dp;
    return this;
  }


  //region paddings

  public BuLayLinear_01 paddingT(int dp) {
    mPaddingT_dp = dp;
    return this;
  }

  public BuLayLinear_01 paddingL(int dp) {
    mPaddingL_dp = dp;
    return this;
  }

  public BuLayLinear_01 paddingR(int dp) {
    mPaddingR_dp = dp;
    return this;
  }

  public BuLayLinear_01 paddingB(int dp) {
    mPaddingB_dp = dp;
    return this;
  }

  public BuLayLinear_01 paddings(int dp) {
    mPaddingL_dp = dp;
    mPaddingT_dp = dp;
    mPaddingR_dp = dp;
    mPaddingB_dp = dp;
    return this;
  }

  public BuLayLinear_01 paddings(int l_dp, int t_dp, int r_dp, int b_dp) {
    mPaddingL_dp = l_dp;
    mPaddingT_dp = t_dp;
    mPaddingR_dp = r_dp;
    mPaddingB_dp = b_dp;
    return this;
  }

  //endregion paddings

  public BuLayLinear_01 addView(View view) {
    mViews = ArrayUtils.add(mViews, view);
    return this;
  }

  public BuLayLinear_01 drawer(N1208_AbsDrawer idrw) {
    mDrawer = idrw;
    return this;
  }


  public BuLayLinear_01 addTo(ViewGroup lay) {
    mParentView = lay;
    return this;
  }

  //region gravity

  public BuLayLinear_01 gravityIn(int g) {
    mGravityIn = g;
    return this;
  }

  /**
   * Позиционирование себя относительно контейнера
   *
   * @param g (1) -- например Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL
   * @return --
   */
  public BuLayLinear_01 gravitySelf(int g) {
    mGravitySelf = g;
    return this;
  }

  //endregion gravity

  public BuLayLinear_01 classLayParams(Class<?> c) {
    mLayParamsClass = c;
    return this;
  }

  public BuLayLinear_01 ecudComment(String st) {
    mEcudComment = st;
    return this;
  }

  public BuLayLinear_01 visibility(boolean b) {
    mVisibility = b;
    return this;
  }

  public BuLayLinear_01 buOnclick(View.OnClickListener onclick) {
    this.onclick = onclick;
    return this;
  }

  //endregion builders

  //region get set

  public String getEcudComment() {
    return mEcudComment;
  }

  /**
   * Возвращает TRUE если у this вертикальная ориентация
   * @return (1) --
   */
  public boolean isOrientV() {
    return TLinearLayout.isOrientV(_layout);
  }

  //endregion get set
}

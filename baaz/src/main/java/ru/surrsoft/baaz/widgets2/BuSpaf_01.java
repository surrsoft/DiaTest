package ru.surrsoft.baaz.widgets2;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.apache.commons.lang3.ArrayUtils;

import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.tclasses.TLayoutParams_01;
import ru.surrsoft.baaz.tclasses.TView_01;
import ru.surrsoft.baaz.univers.Color2;
import ru.surrsoft.baaz.univers.Ddbf;

/**
 * [[spaf]] - генератор переключателя. Контентом являются Drawable
 * <p>
 * //--- ИСПОЛЬЗОВАНИЕ:
 * -- Drawable добавляются последовательно по одному методом {@link
 * #buAddDrw(Drawable)}
 * -- также последовательно, в той же последовательности что и Drawable, для каждого Drawable
 * добавляется их цвет в нормальном состоянии методом {@link #buAddColorNormal(int)}.
 * -- цвет pressed и disabled - один для всех состояний, задается соответственно методами {@link
 * #buColorPressed(int)} и {@link #buColorDisabled(int)}
 * -- так же, отдельно для каждого состояния, можно указывать paddings (см. {@link #buAddPaddings(int)} )
 * -- стартовый индекс задается с помощью {@link #buStartStateIndex(int)}
 * -- для обратной связи используется презентер {@link Ddbf}
 * <p>
 * //---
 * МЕТКИ: [zipc], [ksao]
 * <p>
 * СТАРОЕ НАЗВАНИЕ: BuVebf, BuilderSW
 * <p>
 * ПРИМЕРЫ:
 * -- https://gist.github.com/surrsoft/c8ef47cb86a8f9bba52f653bd581d079
 * <p>
 * ВЕРСИЯ 4 1.3 2019-02-05 (stored)
 * ВЕРСИЯ 3 1.2 2018-11-08 (stored)
 * ВЕРСИЯ 2 1.1 2018-11-07 (stored)
 * ВЕРСИЯ 1 1.0 2016-10-04 (stored)
 */
public class BuSpaf_01 {

  //fields
  //---------------------------------------------------------------------------------------------

  private final Context mContext;
  private Drawable[] mDrawables = {};
  private int[] mColorNormals = {};
  private int mColorPressed = Color.TRANSPARENT;
  private int mColorDisabled = Color.GRAY;
  private int mCurrStateIndex;
  private Ddbf mPresenter;
  private int[] mPaddings_px = {};
  private int mW_px = ViewGroup.LayoutParams.WRAP_CONTENT;
  private int mH_px = ViewGroup.LayoutParams.WRAP_CONTENT;
  private ImageView mRetIv;
  private Class mLayoutParamsClass;
  private int mMarginL_dp;
  private int mMarginT_dp;
  private int mMarginR_dp;
  private int mMarginB_dp;
  private ViewGroup mParentLay;
  private boolean mBEnable = true;
  private boolean bSetColorPressed;
  private boolean bSetColorDisabled;
  private boolean bSetMargins;
  private boolean bSetLayoutParamsClass;
  private boolean bSetEnable;
  private boolean bSetW;
  private boolean bSetH;
  private boolean bFirst;

  //constructors
  //```````````````````````````````````````````````````````````````````````````````````````````````
  public BuSpaf_01(Context context) {
    mContext = context;
  }

  //builders
  //```````````````````````````````````````````````````````````````````````````````````````````````
  public BuSpaf_01 buAddDrw(Drawable drw) {
    if (mRetIv != null) {
      throw new SomeException("(debug) не поддерживается добавление drw после build()");
    }
    //---
    mDrawables = ArrayUtils.add(mDrawables, drw);
    return this;
  }

  public BuSpaf_01 buAddColorNormal(int color) {
    if (mRetIv != null) {
      throw new SomeException("(debug) не поддерживается добавление цвета после build()");
    }
    //---
    mColorNormals = ArrayUtils.add(mColorNormals, color);
    return this;
  }

  public BuSpaf_01 buColorPressed(int color) {
    bSetColorPressed = true;
    mColorPressed = color;
    return this;
  }

  public BuSpaf_01 buColorDisabled(int color) {
    bSetColorDisabled = true;
    mColorDisabled = color;
    return this;
  }

  public BuSpaf_01 buStartStateIndex(int ix) {
    mCurrStateIndex = ix;
    return this;
  }

  public BuSpaf_01 buPresenter(Ddbf p) {
    mPresenter = p;
    return this;
  }

  public BuSpaf_01 buAddPaddings(int dp) {
    if (mRetIv != null) {
      throw new SomeException("(debug) не поддерживается после build()");
    }
    //---
    mPaddings_px = ArrayUtils.add(mPaddings_px, G67G_Draw.px(dp));
    return this;
  }

  public BuSpaf_01 buW(int dp) {
    bSetW = true;
    //---
    if (dp > 0) {
      mW_px = G67G_Draw.px(dp);
    }
    return this;
  }

  public BuSpaf_01 buH(int dp) {
    bSetH = true;
    if (dp > 0) {
      mH_px = G67G_Draw.px(dp);
    }
    return this;
  }

  public BuSpaf_01 buWH(int dp) {
    bSetW = true;
    bSetH = true;
    //---
    if (dp > 0) {
      mW_px = G67G_Draw.px(dp);
      mH_px = G67G_Draw.px(dp);
    }
    return this;
  }

  public BuSpaf_01 buLayoutParamsClass(Class cls) {
    bSetLayoutParamsClass = true;
    this.mLayoutParamsClass = cls;
    return this;
  }

  public BuSpaf_01 buMargins(int l_dp, int t_dp, int r_dp, int b_dp) {
    bSetMargins = true;
    mMarginL_dp = l_dp;
    mMarginT_dp = t_dp;
    mMarginR_dp = r_dp;
    mMarginB_dp = b_dp;
    return this;
  }

  public BuSpaf_01 buAddTo(ViewGroup viewGroup) {
    if (mRetIv != null) {
      throw new SomeException("(debug) не поддерживается после build()");
    }
    //---
    mParentLay = viewGroup;
    return this;
  }

  public BuSpaf_01 buEnable(boolean b) {
    bSetEnable = true;
    mBEnable = b;
    return this;
  }

  //create & apply
  //`````````````````````````````````````````````````````````````````````````````````````````````
  public void apply() {
    if (mRetIv != null) {
      build();
    }
  }

  public void apply(ImageView iv) {
    if (iv != null) {
      mRetIv = iv;
      build();
    }
  }

  public ImageView build() {
    if (mRetIv == null) {
      bFirst = true;
    }
    //--- только для первого создания
    if (bFirst) {
      //--- создание ImageView если еще не создано
      mRetIv = new ImageView(mContext);
      //--- --- colorNormals
      //--- если никакой colorNormal не был указан, то задаем цвет Color.TRANSPARENT
      if (mColorNormals.length == 0) {
        mColorNormals = ArrayUtils.add(mColorNormals, Color.TRANSPARENT);
      }
      //--- указываем mColorNormals для всех остальных drw такой же как для первого
      if (mColorNormals.length < mDrawables.length) {
        for (int i = mColorNormals.length; i < mDrawables.length; i++) {
          mColorNormals = ArrayUtils.add(mColorNormals, mColorNormals[0]);
        }
      }
      //--- ---
      //---
      mRetIv.setClickable(true);
    }

    //---
    //--- layoutParams
    if (bSetLayoutParamsClass && bSetMargins) {
      TLayoutParams_01.verify_B(
        mRetIv,
        mLayoutParamsClass,
        mW_px,
        mH_px,
        mMarginL_dp,
        mMarginT_dp,
        mMarginR_dp,
        mMarginB_dp
      );
      //---
      bSetLayoutParamsClass = false;
      bSetMargins = false;
    }

    //--- paddings
    if (mPaddings_px.length > 0 && mPaddings_px.length < mDrawables.length) {
      int last = mPaddings_px[mPaddings_px.length - 1];
      for (int i = mPaddings_px.length; i < mDrawables.length; i++) {
        mPaddings_px = ArrayUtils.add(mPaddings_px, last);
      }
    }
    if (mPaddings_px.length > 0) {
      int px = mPaddings_px[mCurrStateIndex];
      mRetIv.setPadding(px, px, px, px);
    }
    //---
    mRetIv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //---
        int iStateBeforeClick = mCurrStateIndex;
        //--- инкремент индекса текущего состояния
        mtCurrStateIndexIncrement();
        //---
        StateListDrawable sld = G67G_Draw.drawableToStateListDrawable_D(
          mDrawables[mCurrStateIndex],
          mColorNormals[mCurrStateIndex] == Color.TRANSPARENT ? null : new Color2(mColorNormals[mCurrStateIndex]),
          mColorPressed == Color.TRANSPARENT ? null : new Color2(mColorPressed),
          mColorDisabled == Color.TRANSPARENT ? null : new Color2(mColorDisabled)
        );
        //---
        mRetIv.setImageDrawable(sld);
        //---
        if (mPresenter != null) {
          mPresenter.ddbfOn(iStateBeforeClick, mCurrStateIndex);
        }
      }
    });
    //---
    StateListDrawable sld0 = G67G_Draw.drawableToStateListDrawable_D(
      mDrawables[mCurrStateIndex],
      mColorNormals[mCurrStateIndex] == Color.TRANSPARENT ? null : new Color2(mColorNormals[mCurrStateIndex]),
      mColorPressed == Color.TRANSPARENT ? null : new Color2(mColorPressed),
      mColorDisabled == Color.TRANSPARENT ? null : new Color2(mColorDisabled)
    );
    //---
    mRetIv.setImageDrawable(sld0);
    //---
    if (mParentLay != null && !TView_01.parentHas(mRetIv)) {
      mParentLay.addView(mRetIv);
    }
    //---
    mRetIv.setEnabled(mBEnable);
    //---
    return mRetIv;
  }


  //local methods
  //```````````````````````````````````````````````````````````````````````````````````````````````

  /**
   * Инкремент индекса текущего состояния
   */
  private void mtCurrStateIndexIncrement() {
    mCurrStateIndex++;
    if (mCurrStateIndex >= mDrawables.length) {
      mCurrStateIndex = 0;
    }
  }


}

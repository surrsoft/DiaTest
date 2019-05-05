package ru.surrsoft.baaz.widgets2;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.tclasses.TLayoutParams_01;
import ru.surrsoft.baaz.tclasses.TMargins_01;
import ru.surrsoft.baaz.tclasses.TxInt_01;
import ru.surrsoft.baaz.univers.Color2;
import ru.surrsoft.baaz.univers.Ddbf;
import ru.surrsoft.baaz.univers.U;
import ru.surrsoft.baaz.widgets2.forwg.Pyux;
import ru.surrsoft.baaz.widgets2.forwg.TPaddings;
import ru.surrsoft.baaz.widgets2.forwg.TxPaddings;

/**
 * [[spaf]] - генератор переключателя. Контентом являются Drawable
 * <p>
 * КЛАССИФИКАЦИЯ: [mddk]-виджет
 * ТИП КОНСТРУКЦИИ КЛАССА: [rsdk]
 * <p>
 * //--- ИСПОЛЬЗОВАНИЕ:
 * -- Drawable + paddings + colorNormal добавляются последовательно по одному в виде {@link Pyux}
 * методом {@link #buAddPyux(Pyux)}
 * -- цвет pressed и disabled - один для всех состояний, задается соответственно методами {@link
 * #buColorPressed(int)} и {@link #buColorDisabled(int)}
 * -- индекс состояния задается с помощью {@link #buCurrStateIndex(int)}. По умолчанию это 0
 * -- для обратной связи используется презентер {@link Ddbf}
 * <p>
 * //---
 * МЕТКИ: [zipc], [ksao]
 * <p>
 * СТАРОЕ НАЗВАНИЕ: BuVebf, BuilderSW
 * <p>
 * ВЕРСИИ:
 * -- ВЕРСИЯ 3 2.0 2018-11-08 (stored)
 * -- ВЕРСИЯ 2 1.1 2018-11-07 (stored)
 * -- ВЕРСИЯ 1 1.0 2016-10-04 (stored)
 */
public class BuSpaf_02 {

  //fields
  //---------------------------------------------------------------------------------------------

  private final Context mContext;
  private int mColorPressed = Color.TRANSPARENT;
  private int mColorDisabled = Color.GRAY;
  private int mCurrStateIndex;
  private Ddbf mPresenter;
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
  private ArrayList<Pyux> mPyuxs = new ArrayList<>();
  //---
  private boolean bSetMargins;
  private boolean bSetEnable;
  private boolean bSetW;
  private boolean bSetH;
  private boolean bSetCurrStateIndex;
  private boolean bSetColorPressed;
  private boolean bSetColorDisabled;
  private boolean bSetMarginL;
  private boolean bSetMarginT;
  private boolean bSetMarginR;
  private boolean bSetMarginB;

  //constructors
  //```````````````````````````````````````````````````````````````````````````````````````````````
  public BuSpaf_02(Context context) {
    mContext = context;
  }

  //builders
  //```````````````````````````````````````````````````````````````````````````````````````````````
  public BuSpaf_02 buAddPyux(Pyux pyux) {
    mtVerifyEx();
    mPyuxs.add(pyux);
    return this;
  }

  public BuSpaf_02 buColorPressed(int color) {
    bSetColorPressed = true;
    mColorPressed = color;
    return this;
  }

  public BuSpaf_02 buColorDisabled(int color) {
    bSetColorDisabled = true;
    mColorDisabled = color;
    return this;
  }

  public BuSpaf_02 buCurrStateIndex(int ix) {
    bSetCurrStateIndex = true;
    TxInt_01.verify_ex(ix, TxInt_01.E.NIL_PLUS);
    //---
    if (mRetIv != null && ix > (mPyuxs.size() - 1)) {
      throw new SomeException("(debug) неверный индекс состояния; [" + ix + "]");
    }
    //---
    mCurrStateIndex = ix;
    return this;
  }

  public BuSpaf_02 buPresenter(Ddbf p) {
    mPresenter = p;
    return this;
  }

  public BuSpaf_02 buW(int dp) {
    bSetW = true;
    //---
    if (dp > 0) {
      mW_px = G67G_Draw.px(dp);
    }
    return this;
  }

  public BuSpaf_02 buH(int dp) {
    bSetH = true;
    if (dp > 0) {
      mH_px = G67G_Draw.px(dp);
    }
    return this;
  }

  public BuSpaf_02 buWH(int dp) {
    TxInt_01.verify_ex(dp, TxInt_01.E.NIL_PLUS);
    //---
    bSetW = true;
    bSetH = true;
    //---
    if (dp > 0) {
      mW_px = U.px(dp);
      mH_px = U.px(dp);
    }
    return this;
  }

  public BuSpaf_02 buLayoutParamsClass(Class cls) {
    mtVerifyEx();
    //---
    this.mLayoutParamsClass = cls;
    return this;
  }

  public BuSpaf_02 buMargins(int l_dp, int t_dp, int r_dp, int b_dp) {
    if (bSetMarginL || bSetMarginT || bSetMarginR || bSetMarginB) {
      throw new SomeException("(debug) нельзя использовать buMargins вместе с buMargin..");
    }
    //---
    bSetMargins = true;
    //---
    mMarginL_dp = l_dp;
    mMarginT_dp = t_dp;
    mMarginR_dp = r_dp;
    mMarginB_dp = b_dp;
    return this;
  }

  public BuSpaf_02 buMarginL(int l_dp) {
    mtEx51();
    bSetMarginL = true;
    mMarginL_dp = l_dp;
    return this;
  }

  public BuSpaf_02 buMarginT(int t_dp) {
    mtEx51();
    bSetMarginT = true;
    mMarginT_dp = t_dp;
    return this;
  }

  public BuSpaf_02 buMarginR(int r_dp) {
    mtEx51();
    bSetMarginR = true;
    mMarginR_dp = r_dp;
    return this;
  }

  public BuSpaf_02 buMarginB(int b_dp) {
    mtEx51();
    bSetMarginB = true;
    mMarginB_dp = b_dp;
    return this;
  }

  public BuSpaf_02 buAddTo(ViewGroup viewGroup) {
    mtVerifyEx();
    //---
    mParentLay = viewGroup;
    return this;
  }


  public BuSpaf_02 buEnable(boolean b) {
    bSetEnable = true;
    //---
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

  /**
   * @return --
   */
  public ImageView build() {
    //--- проверки
    if (mPyuxs.size() < 1) {
      throw new SomeException("(debug) состояний меньше 1 не поддерживается; [" + mPyuxs.size() + "]");
    }
    //-
    for (int i = 0; i < mPyuxs.size(); i++) {
      Pyux pyux = mPyuxs.get(i);
      if (pyux.drwGet() == null) {
        throw new SomeException("(debug) pyux.drw == null не поддерживется; [" + i + "]");
      }
    }

    //---
    boolean bFirst = mRetIv == null;

    //---
    ViewGroup.MarginLayoutParams lp = null;

    //--- только для первого создания
    if (bFirst) {
      //--- создание ImageView если еще не создано
      mRetIv = new ImageView(mContext);
      //---
      mRetIv.setClickable(true);
      //---
      if (mParentLay != null) {
        mParentLay.addView(mRetIv);
        lp = (ViewGroup.MarginLayoutParams) mRetIv.getLayoutParams();
      } else {
        lp = TLayoutParams_01.create(mLayoutParamsClass);
        mRetIv.setLayoutParams(lp);
      }
    } else {
      lp = (ViewGroup.MarginLayoutParams) mRetIv.getLayoutParams();
    }

    //--- margins
    if (bFirst || bSetMargins || bSetMarginL || bSetMarginT || bSetMarginR || bSetMarginB) {
      if (bSetMargins) {
        TMargins_01.set(mRetIv, lp, mMarginL_dp, mMarginT_dp, mMarginR_dp, mMarginB_dp);
        bSetMargins = false;
      } else if (bSetMarginL || bSetMarginT || bSetMarginR || bSetMarginB) {
        if (bSetMarginL) {
          lp.leftMargin = U.px(mMarginL_dp);
          bSetMarginL = false;
        }
        if (bSetMarginT) {
          lp.topMargin = U.px(mMarginT_dp);
          bSetMarginT = false;
        }
        if (bSetMarginR) {
          lp.rightMargin = U.px(mMarginR_dp);
          bSetMarginR = false;
        }
        if (bSetMarginB) {
          lp.bottomMargin = U.px(mMarginB_dp);
          bSetMarginB = false;
        }
      }
    }

    //---
    if (bFirst || bSetH) {
      if (lp != null) {
        lp.height = mH_px;
      }
      bSetH = false;
    }
    //---
    if (bFirst || bSetW) {
      if (lp != null) {
        lp.width = mW_px;
      }
      bSetW = false;
    }

    //---
    if (bFirst || bSetEnable) {
      mRetIv.setEnabled(mBEnable);
      bSetEnable = false;
    }

    //---
    if (bFirst || bSetCurrStateIndex || bSetColorPressed || bSetColorDisabled) {
      mtDrwSetByIndexAnd_x2();
      //---
      bSetCurrStateIndex = false;
      bSetColorPressed = false;
      bSetColorDisabled = false;
    }

    //---
    if (bFirst) {
      mRetIv.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          System.out.println("нажатие //181111-162000");
          //---
          int iStateBefore = mCurrStateIndex;
          //--- инкремент индекса текущего состояния
          BuSpaf_02.this.mtCurrStateIndexIncrement();
          //---
          BuSpaf_02.this.mtDrwSetByIndexAnd_x2();
          //---
          if (mPresenter != null) {
            mPresenter.ddbfOn(iStateBefore, mCurrStateIndex);
          }
        }
      });
    }

    //---
    if (!bFirst) {
      mRetIv.invalidate();
      mRetIv.requestLayout();
    }

    //---
    return mRetIv;
  }


  //local methods
  //```````````````````````````````````````````````````````````````````````````````````````````````

  /**
   * Применение изображения + paddings в соответствии с текущим индексом
   */
  private void mtDrwSetByIndexAnd_x2() {
    //---
    StateListDrawable sld = G67G_Draw.drawableToStateListDrawable_D(
      mPyuxs.get(mCurrStateIndex).drwGet(),
      mPyuxs.get(mCurrStateIndex).colorNormalGet() == Color.TRANSPARENT
        ? null : new Color2(mPyuxs.get(mCurrStateIndex).colorNormalGet()),
      mColorPressed == Color.TRANSPARENT ? null : new Color2(mColorPressed),
      mColorDisabled == Color.TRANSPARENT ? null : new Color2(mColorDisabled)
    );
    //-
    mRetIv.setImageDrawable(sld);

    //--- paddings
    TxPaddings p = mPyuxs.get(mCurrStateIndex).paddingsGet();
    TPaddings.apply(mRetIv, p);
  }

  /**
   * Инкремент индекса текущего состояния
   */
  private void mtCurrStateIndexIncrement() {
    mCurrStateIndex++;
    if (mCurrStateIndex >= mPyuxs.size()) {
      mCurrStateIndex = 0;
    }
  }

  private void mtEx51() {
    if (bSetMargins) {
      throw new SomeException("(debug) нельзя использовать вместе с buMargins");
    }
  }

  private void mtVerifyEx() {
    if (mRetIv != null) {
      throw new SomeException("(debug) не поддерживается после build()");
    }
  }
}

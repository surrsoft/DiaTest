package ru.surrsoft.baaz.widgets2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import ru.surrsoft.baaz.tclasses.TLayoutParams_01;
import ru.surrsoft.baaz.univers.Gravity_01;
import ru.surrsoft.baaz.univers.U;
import ru.surrsoft.baaz.widgets2.nviews.NButton;

/**
 * Билдер кнопки
 */
public class BuButton_01 {
  private final Context mContext;
  private String mStText = "//181111024200";
  private View.OnClickListener mLst;
  private ViewGroup mParent;
  private NButton mRetBtn;
  private Gravity_01 gravityOut;

  //constructors
  //``````````````````````````````````````````````````````````````````````````````````````````````
  public BuButton_01(Context ctx) {
    mContext = ctx;
  }

  //builders
  //``````````````````````````````````````````````````````````````````````````````````````````````

  public BuButton_01 buText(String st) {
    this.mStText = st;
    return this;
  }

  public BuButton_01 buListener(View.OnClickListener lst) {
    mLst = lst;
    return this;
  }

  /**
   * Синоним для {@link #buListener(View.OnClickListener)}
   *
   * @param lst (1) --
   * @return --
   */
  public BuButton_01 buOnclick(View.OnClickListener lst) {
    return buListener(lst);
  }

  public BuButton_01 buAddTo(ViewGroup vg) {
    mParent = vg;
    return this;
  }

  /**
   * Гравитация кнопки относительно своего родителя
   *
   * @param gravity (1) -- например "Gravity.BOTTOM | Gravity.RIGHT"
   * @return --
   */
  public BuButton_01 buGravityOut(Gravity_01 gravity) {
    this.gravityOut = gravity;
    return this;
  }

  //build & apply
  //``````````````````````````````````````````````````````````````````````````````````````````````

  public NButton build() {
    mRetBtn = new NButton(mContext);
    apply(mRetBtn);
    return mRetBtn;
  }

  public void apply() {
    if (mRetBtn != null) {
      apply(mRetBtn);
    }
  }

  public void apply(NButton btn) {
    if (btn == null) {
      return;
    }
    //---
    btn.setText(mStText);
    //---
    btn.setOnClickListener(mLst);
    //---
    TLayoutParams_01.verify(btn, null, U.WC, U.WC);
    //---
    if (mParent != null) {
      mParent.addView(btn);
    }
    //--- gravity
    ViewGroup.LayoutParams lp = btn.getLayoutParams();
    if (gravityOut != null) {
      if (lp.getClass().equals(FrameLayout.LayoutParams.class)) {
        ((FrameLayout.LayoutParams) lp).gravity = gravityOut.val;
      }
      if (lp.getClass().equals(LinearLayout.LayoutParams.class)) {
        ((LinearLayout.LayoutParams) lp).gravity = gravityOut.val;
      }
    }
  }
}

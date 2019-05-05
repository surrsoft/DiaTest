package ru.surrsoft.baaz.widgets2.buLay;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import java.util.ArrayList;

import ru.surrsoft.baaz.univers.U;
import ru.surrsoft.baaz.widgets2.nviews.NLinearLayout;
import ru.surrsoft.baaz.widgets2.nviews.NScrollView;

/**
 * Билдер вертикальных ScrollView. На выходе {@link NScrollView}
 * <p>
 */
public class BuLayScroll_01 {

  private final Context mContext;
  private int mPaddingDpL = -1;
  private int mPaddingDpT = -1;
  private int mPaddingDpR = -1;
  private int mPaddingDpB = -1;
  private ArrayList<View> mViews = new ArrayList<>();
  /**
   * Если TRUE то созданный ScrollView добавляется как корневой с помощью ctx.setContentView(...)
   */
  private boolean bAsContentView;
  private NScrollView retLay;

  //constructors
  //```````````````````````````````````````````````````````````````````````````````````````````````
  public BuLayScroll_01(Context ctx) {
    mContext = ctx;
  }


  //builders
  //```````````````````````````````````````````````````````````````````````````````````````````````
  public BuLayScroll_01 buPaddings(int l_dp, int t_dp, int r_dp, int b_dp) {
    mPaddingDpL = l_dp;
    mPaddingDpT = t_dp;
    mPaddingDpR = r_dp;
    mPaddingDpB = b_dp;
    return this;
  }

  public BuLayScroll_01 buPaddings(int dp) {
    mPaddingDpL = dp;
    mPaddingDpT = dp;
    mPaddingDpR = dp;
    mPaddingDpB = dp;
    return this;
  }

  /**
   * {мультивызов}
   *
   * @param view (1) --
   * @return --
   */
  public BuLayScroll_01 buAddChild(View view) {
    mViews.add(view);
    return this;
  }

  public BuLayScroll_01 buAsContentView(boolean bAsContentView) {
    this.bAsContentView = bAsContentView;
    return this;
  }

  //```````````````````````````````````````````````````````````````````````````````````````````````
  public NScrollView build() {
    NScrollView retLay = new NScrollView(mContext);
    apply(retLay);
    return retLay;
  }

  /**
   * Создаёт помимо самого ScrollView ещё и вложенный LinearLayout (вертикальный, c paddings=5dp),
   * на который и возвращает ссылку
   *
   * @return -- ссылка на вложенный LinearLayout
   */
  public NLinearLayout build_B() {
    //---
    NLinearLayout layRet = new BuLayLinear_01(mContext)
      .paddings(5)
      .build();
    //---
    buAddChild(layRet);
    //---
    NScrollView layScroll = new NScrollView(mContext);
    apply(layScroll);
    //---
    return layRet;
  }

  public NScrollView build(Get get) {
    U.se(get == null, "");
    //---
    NScrollView ret = build();
    get.fun(retLay, this);
    return ret;
  }

  public void apply(NScrollView view) {
    //--- paddings
    if (mPaddingDpL >= 0 || mPaddingDpT >= 0 || mPaddingDpR >= 0 || mPaddingDpB >= 0) {
      view.setPadding(
              U.px(mPaddingDpL),
              U.px(mPaddingDpT),
              U.px(mPaddingDpR),
              U.px(mPaddingDpB)
      );
    }

    //--- child views
    if (mViews.size() > 0) {
      for (View elem : mViews) {
        view.addView(elem);
      }
    }

    //---
    if (bAsContentView) {
      ((Activity) mContext).setContentView(view);
    }

  }

  //---

  /**
   * Если передать этот тип в build(Get), то можно получить в (1) ссылку на созданный scrollView,
   * а в (2) ссылку на сам билдер
   */
  public interface Get {
    void fun(NScrollView createdNScrollView, BuLayScroll_01 builder);
  }

}

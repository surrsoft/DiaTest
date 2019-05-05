package ru.surrsoft.baaz.widgets2;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;

import ru.surrsoft.baaz.R;
import ru.surrsoft.baaz.univers.Gravity_01;
import ru.surrsoft.baaz.univers.U;
import ru.surrsoft.baaz.widgets2.utils.BuCSL;

/**
 * Билдер для {@link FloatingActionButton}
 * <p>
 * ПО УМОЛЧАНИЮ
 * <li> размеры кнопки - 48x48 dp </li>
 * <li> внешний gravity - Gravity.RIGHT | Gravity.BOTTOM</li>
 * <li> картинка - в виде "+"</li>
 * <p>
 * ОПЦИИ
 * <li> задание наружного gravity -- {@link AbsBuView_01#gravityOut(Gravity_01)}  </li>
 * <li> задание цветов поверхности кнопки (нормальный и нажато) -- {@link AbsBuView_01#bgColors(BuCSL)}  </li>
 * <li> иконку (см. setImageDrawable) и слушатель нажатия (см. setOnClickListener) нужно задавать отдельно,
 * после вызова {@link #build()} </li>
 *
 * ПОНЯТИЯ:
 * -- _картинка - изображение внутри кнопки (например "плюс" и т.п.)
 * <p>
 * [zipc]
 */
public class BuFAB extends AbsBuView_01 {
  private Context mContext;
  private Drawable mDrawable;
  private ViewGroup layParent;

  //--- constructors

  public BuFAB(Context c) {
    mContext = c;
    //--- дефолты
    mW_px = U.px(48);
    mH_px = U.px(48);
    mGravityOut = new Gravity_01(Gravity.RIGHT | Gravity.BOTTOM);
    mDrawable = mContext.getResources().getDrawable(R.drawable.ic_plus, null);
  }

  //---

  /**
   * {есть дефолт} Drawable иконки отображаемой на кнопке
   *
   * @param drw (1) --
   * @return --
   */
  public BuFAB image(Drawable drw) {
    mDrawable = drw;
    return this;
  }

  public BuFAB buAddTo(ViewGroup layParent) {
    this.layParent = layParent;
    return this;
  }

  //--- build

  @Override
  public FloatingActionButton build() {
    super.build();

    //---
    FloatingActionButton fab = new FloatingActionButton(mContext);

    //---
    if (layParent != null) {
      layParent.addView(fab);
    }

    //--- параметры размещения
    ViewGroup.MarginLayoutParams lp = lpConfigure(fab);
    lp.height = mH_px;
    lp.width = mW_px;
    fab.setLayoutParams(lp);

    //--- цвет кнопки
    if (mBgColors != null) {
      ColorStateList csl = mBgColors.create();
      fab.setBackgroundTintList(csl);
    }

    //--- _картинка
    fab.setImageDrawable(mDrawable);
    //- без этого _картинка будет не по центру
    fab.setScaleType(ImageView.ScaleType.CENTER);

    //---
    return fab;
  }

  //---

}

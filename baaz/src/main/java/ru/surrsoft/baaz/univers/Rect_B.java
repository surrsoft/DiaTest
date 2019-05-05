package ru.surrsoft.baaz.univers;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;


/**
 * Подготавливает данные для рисования прямоугольника - объекты Paint и Path.
 * Возможно скруглять углы задавая масштаб радиуса (скругляются все 4 угла
 * одновременно, через {@link #setRadiusScale(int)}), либо задавая масштаб
 * радиуса для каждого из 4-х углов в отдельности (
 * {@link #setRadiusesScale(int[])}. Класс является Builder-классом, т.е. можно
 * строить цепочки. Сначала объект "нашпиговывается" значениями, затем нужно
 * вызвать метод {@link #commit()}
 */
public class Rect_B {


  /**
   * Плотность
   */
  private float mDensity = 1.0f;

  /**
   * Толщина границы в dip
   */
  private int mTh_dp = 1;
  /**
   * Цвет границы
   */
  private int mThColor = Color.RED;
  /**
   * Коэффициэнт радиуса. Должен быть в диапазоне 0..100
   */
  private int mRadiusScale = 50;
  /**
   * Отступ общий в dip
   */
  private int mPadding_dp = 0;

  private Paint mPaint;

  /**
   * Ширина наружная в px
   */
  private int w_px = 0;
  /**
   * Высота наружная в px
   */
  private int h_px = 0;
  private int[] mRadiusesScale = null;

  private Path mPath;

  public Rect_B() {
    // mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaint = new Paint();
    mPaint.setStyle(Paint.Style.STROKE);
    // mPaint.setPathEffect(PathEffect)

    mPath = new Path();

    commit();
  }

  public Rect_B commit() {
    mPaint.setColor(getThColor());
    mPaint.setStrokeWidth(getTh_px());
    // mPaint.setPathEffect(new CornerPathEffect(100f));

    int th2 = (int) (getTh_px() / 2f);

    int c = 0;
    // если толщина четная
    if (correct()) {
      c = 1;
    }

    int mLeft = (th2) + padding_px();
    int mTop = (th2) + padding_px();
    int mRight = (w_px - 1) - th2 + c - padding_px();
    int mBottom = (h_px - 1) - th2 + c - padding_px();

    // расчет радиуса
    int rl = mRight - mLeft + 1;
    int bt = mBottom - mTop + 1;
    // минимум из ширины и высоты path
    int min = Math.min(rl, bt);
    //
    int[] mRs_px = new int[4];
    if (mRadiusesScale == null && min > 3) {
      for (int i = 0; i < 4; i++) {
        float v = ((int) (min / 2f)) * (mRadiusScale / 100f);
        mRs_px[i] = (int) v;
      }
    }
    if (mRadiusesScale != null && min > 3) {
      mRs_px = new int[4];
      for (int i = 0; i < 4; i++) {
        float v = ((int) (min / 2f)) * (mRadiusesScale[i] / 100f);
        mRs_px[i] = (int) v;
      }
    }

    mPath.reset();
    mPath.moveTo(mLeft + mRs_px[0], mTop);
    //
    mPath.lineTo(mRight - mRs_px[1], mTop);
    //
    RectF rectRT = new RectF(mRight - mRs_px[1] * 2, mTop, mRight, mTop
      + mRs_px[1] * 2);
    mPath.arcTo(rectRT, 270, 90);
    //
    mPath.lineTo(mRight, mBottom - mRs_px[2]);
    //
    RectF rectRB = new RectF(mRight - mRs_px[2] * 2, mBottom - mRs_px[2]
      * 2, mRight, mBottom);
    mPath.arcTo(rectRB, 0, 90);
    //
    mPath.lineTo(mLeft + mRs_px[3], mBottom);
    //
    RectF rectLB = new RectF(mLeft, mBottom - mRs_px[3] * 2, mLeft
      + mRs_px[3] * 2, mBottom);
    mPath.arcTo(rectLB, 90, 90);
    //
    mPath.lineTo(mLeft, mTop + mRs_px[0]);
    //
    RectF rectLT = new RectF(mLeft, mTop, mLeft + mRs_px[0] * 2, mTop
      + mRs_px[0] * 2);
    mPath.arcTo(rectLT, 180, 90);
    mPath.close();

    return this;
  }

  public Path getPath() {
    return mPath;
  }

  /**
   * Толщина границы в пикселях
   *
   * @return
   */
  public int getTh_px() {
    return px(getTh_dp());
  }

  /**
   * Возврат TRUE означает что нужно учесть m109m@эффект-несим
   *
   * @return
   */
  private boolean correct() {
    return getTh_px() % 2 == 0;
  }

  public int padding_px() {
    return px(getPadding_dp());
  }

  /**
   * Dp в Px
   *
   * @param dp
   * @return
   */
  private int px(int dp) {
    if (dp == 0) {
      return 0;
    }
    int v = (int) (dp * getDensity());
    v = (v == 0) ? 1 : v;
    return v;
  }

  public int getW_px() {
    return w_px;
  }

  public Rect_B setW_px(int w_px) {
    this.w_px = w_px;
    return this;
  }

  public int getH_px() {
    return h_px;
  }

  public Rect_B setH_px(int h_px) {
    this.h_px = h_px;
    return this;
  }

  public Paint getPaint() {
    return mPaint;
  }

  public float getDensity() {
    return mDensity;
  }

  public Rect_B setDensity(float density) {
    this.mDensity = density;
    return this;
  }

  public int getTh_dp() {
    return mTh_dp;
  }

  public Rect_B setTh_dp(int th_dp) {
    this.mTh_dp = th_dp;
    return this;
  }

  public int getThColor() {
    return mThColor;
  }

  public Rect_B setThColor(int thColor) {
    this.mThColor = thColor;
    return this;
  }

  public int getRadiusScale() {
    return mRadiusScale;
  }

  /**
   * Относительный размер скругления для всех 4-х углов (0-100). Данный метод
   * перекрывается методом {@link #setRadiusesScale(int[])}
   *
   * @param radiusScale (1) - 0..100
   * @return
   */
  public Rect_B setRadiusScale(int radiusScale) {
    this.mRadiusScale = radiusScale;
    return this;
  }

  public int getPadding_dp() {
    return mPadding_dp;
  }

  public Rect_B setPadding_dp(int padding_dp) {
    this.mPadding_dp = padding_dp;
    return this;
  }

  /**
   * Массив из 4-х значений. Значения представляют собой относительные размеры
   * скругления (от 0 до 100) для каждого из 4-х углов прямоугольника. Первым
   * указывается радиус левого-верхнего угла и далее по часовой стрелке. По
   * умолчанию == null. Данный метод перекрывает метод
   * {@link #setRadiusScale(int)}
   *
   * @param radiuses (1)
   */
  public Rect_B setRadiusesScale(int[] radiuses) {
    this.mRadiusesScale = radiuses;
    return this;
  }
}
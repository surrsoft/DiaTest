package ru.surrsoft.baaz.univers;


import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;


/**
 * Подготавливает данные для рисования прямоугольника - объекты Paint и RectF.
 * Возможно скруглять углы задавая масштаб радиуса (скругляются все 4 угла
 * одновременно). Класс является Builder-классом, т.е. можно строить цепочки.
 * Сначала объект "нашпиговывается" значениями, затем нужно вызвать метод
 * {@link #commit()}
 *
 * @author 0000
 */
public class Rect_A {

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
  private RectF mRectF;

  /**
   * Ширина наружная в px
   */
  private int w_px = 0;
  /**
   * Высота наружная в px
   */
  private int h_px = 0;

  private int mRadius_px;

  public Rect_A() {
    // mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaint = new Paint();
    mPaint.setStyle(Paint.Style.STROKE);
    // mPaint.setPathEffect(PathEffect)

    mRectF = new RectF();

    commit();
  }

  public void commit() {
    mPaint.setColor(getThColor());
    mPaint.setStrokeWidth(getTh_px());

    int th2 = (int) (getTh_px() / 2f);

    int c = 1;
    // если толщина четная
    if (correct()) {
      c = 0;
    }

    int mLeft = 1 + th2 + padding_px();
    int mTop = th2 + padding_px() + 1;
    int mRight = w_px - th2 - 1 - padding_px();
    int mBottom = h_px - th2 - 1 - padding_px();

    // расчет радиуса
    int rl = mRight - mLeft + 1;
    int bt = mBottom - mTop + 1;
    // минимум из ширины и высоты path
    int min = Math.min(rl, bt);
    mRadius_px = 0;
    if (min > 3) {
      float v = ((int) (min / 2f)) * (mRadiusScale / 100f);
      mRadius_px = (int) v;
    }

    mRectF.set(mLeft, mTop, mRight, mBottom);
  }

  public int getRadius_px() {
    return mRadius_px;
  }

  /**
   * Толщина границы в пикселях
   *
   * @return --
   */
  public int getTh_px() {
    return px(getTh_dp());
  }

  /**
   * Возврат TRUE означает что нужно учесть m109m@эффект-несим
   *
   * @return --
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
   * @param dp (1) --
   * @return --
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

  public Rect_A setW_px(int w_px) {
    this.w_px = w_px;
    return this;
  }

  public int getH_px() {
    return h_px;
  }

  public Rect_A setH_px(int h_px) {
    this.h_px = h_px;
    return this;
  }

  public Paint getPaint() {
    return mPaint;
  }

  public RectF getRectF() {
    return mRectF;
  }

  public float getDensity() {
    return mDensity;
  }

  public Rect_A setDensity(float density) {
    this.mDensity = density;
    return this;
  }

  public int getTh_dp() {
    return mTh_dp;
  }

  public Rect_A setTh_dp(int th_dp) {
    this.mTh_dp = th_dp;
    return this;
  }

  public int getThColor() {
    return mThColor;
  }

  public Rect_A setThColor(int thColor) {
    this.mThColor = thColor;
    return this;
  }

  public int getRadiusScale() {
    return mRadiusScale;
  }

  public Rect_A setRadiusScale(int radiusScale) {
    this.mRadiusScale = radiusScale;
    return this;
  }

  public int getPadding_dp() {
    return mPadding_dp;
  }

  public Rect_A setPadding_dp(int padding_dp) {
    this.mPadding_dp = padding_dp;
    return this;
  }
}
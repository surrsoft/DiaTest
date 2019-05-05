package ru.surrsoft.baaz.suite.figures;

import android.animation.TimeInterpolator;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.suite.experiments.ecud.AnnEcud;
import ru.surrsoft.baaz.suite.experiments.ecud.EEcudTypes;
import ru.surrsoft.baaz.univers.Color2;
import ru.surrsoft.baaz.univers.FourSizes_01;
import ru.surrsoft.baaz.univers.IDebugString;
import ru.surrsoft.baaz.univers.Trans;
import ru.surrsoft.baaz.widgets2.utils.BuCSL;


/**
 * Генератор фигур (линий и т.п.).
 * <p>
 * ОПЦИИ:
 * -- если фигура поддерживает анимацию, то при инициализации нужно применить метод {@link #animMode(boolean)}.
 * -- если при первом показе сразу должна проиграться анимация, то при инициализации следует применить метод
 * {@link #animPlay(boolean)}. Пример: https://gist.github.com/surrsoft/1145340f758b3451b1229833df39e183
 * <p>
 * ТЕРМИНЫ
 * <li> _холст </li>
 * <li> _фигура </li>
 * <p>
 * #version 1 20.11.2016  #id [[w398w]]
 */
public abstract class N1208_AbsDrawer implements IDebugString {
  private static final String TAG = ":" + N1208_AbsDrawer.class.getSimpleName();

  protected final Matrix mMatrix;
  protected Path mPath;
  protected FourSizes_01 mP = null;

  //colors
  //`````````````````````````````````````````````````````
  @AnnEcud(type = EEcudTypes.COLOR_INT)

  @ColorInt
  public int mColor = Color.RED;

  @AnnEcud(type = EEcudTypes.NODE)
  public Color2 mColorFill;

  //`````````````````````````````````````````````````````
  protected int mTh_dp = 1;
  protected Paint mPaintFill;
  protected Paint mPaint;

  public int mW_px;
  public int mH_px;
  public Canvas mCanvas;
  public int mGravity = Gravity.CENTER;
  protected boolean mAnimPlay;
  protected boolean mAnimMode;

  protected View mAnimView;
  protected TimeInterpolator mAnimInterp = new OvershootInterpolator();
  protected long mAnimDuration = 200;
  protected float mRotateCenterAngle;
  protected boolean mRotateCenterAngleIsSet;
  protected boolean mAnimToEndStart;
  protected Color2 mColorPressed;
  protected int[] mDrawableStates;
  protected Paint mPaintColorBgInner;
  protected Paint mPaintColorBgOuter;
  protected Color2 mColorBgInner;
  protected Color2 mColorBgOuter;
  protected String mDebugString;
  protected ColorStateList mColorCSL;
  protected ColorStateList mColorFillCSL;

  //==============================================================================================

  //constructors
  //``````````````````````````````````````````````````````````````````````````````````````````````
  public N1208_AbsDrawer() {
    mP = new FourSizes_01(0, 0, 0, 0);
    mColorCSL = new BuCSL()
      .normal(new Color2(mColor))
      .create();

    //===
    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setStrokeWidth(G67G_Draw.px(mTh_dp));
    mPaint.setColor(mColor);

    mPaintFill = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaintFill.setStyle(Paint.Style.FILL);
    mPaintFill.setColor(mColor);

    mPaintColorBgInner = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaintColorBgInner.setStyle(Paint.Style.FILL_AND_STROKE);

    mPaintColorBgOuter = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaintColorBgOuter.setStyle(Paint.Style.FILL_AND_STROKE);

    mPath = new Path();
    mMatrix = new Matrix();
  }

  //IDebugString
  //``````````````````````````````````````````````````````````````````````````````````````````````


  @Override
  public void n1475_setDebugString(String st) {
    mDebugString = st;
  }

  @Override
  public String n1475_getDebugString() {
    return mDebugString;
  }

  //``````````````````````````````````````````````````````````````````````````````````````````````


  public N1208_AbsDrawer drawableStates(int[] ds) {
    mDrawableStates = ds;
    return this;
  }

  public Color2 getColorPressed() {
    return mColorPressed;
  }

  /**
   * @return цвет stroke в состоянии normal
   */
  public int getColor() {
    return mColor;
  }

  public N1208_AbsDrawer rotateCenterAngle(int angle) {
    mRotateCenterAngle = angle;
    mRotateCenterAngleIsSet = true;
    return this;
  }

  public N1208_AbsDrawer animDuration(long msec) {
    mAnimDuration = msec;
    return this;
  }

  public N1208_AbsDrawer animInterpolator(TimeInterpolator interp) {
    mAnimInterp = interp;
    return this;
  }

  /**
   * Используется в режиме анимирования. Виджет на котором будет вызываться invalidate для анимирования.
   *
   * @param v
   * @return
   */
  public N1208_AbsDrawer animView(View v) {
    mAnimView = v;
    return this;
  }

  public boolean isAnimMode() {
    return mAnimMode;
  }

  public N1208_AbsDrawer animMode(boolean b) {
    mAnimMode = b;
    return this;
  }

  /**
   * Нужно вызвать, иначе анимация выполняться не будет
   *
   * @return
   */
  public N1208_AbsDrawer animPlay(boolean b) {
    mAnimPlay = b;
    return this;
  }

  /**
   * При первом показе сразу анимацию не проигрывать, а сразу отобразить ее конечный результат
   *
   * @return --
   */
  public N1208_AbsDrawer animToEndOnStart(boolean b) {
    mAnimToEndStart = b;
    return this;
  }

  /**
   * _Холст
   *
   * @param c --
   * @return --
   */
  public N1208_AbsDrawer canvas(Canvas c) {
    mCanvas = c;
    return this;
  }

  /**
   * Ширина _холста
   *
   * @param w_px --
   * @return --
   */
  public N1208_AbsDrawer w_px(int w_px) {
    mW_px = w_px;
    return this;
  }

  /**
   * Высота _холста
   *
   * @param h_px --
   * @return --
   */
  public N1208_AbsDrawer h_px(int h_px) {
    mH_px = h_px;
    return this;
  }

  //draw
  //``````````````````````````````````````````````````````````````````````````````````````````````

  /**
   * Выполнение отрисовки. Предварительно должны быть заданы {@link #canvas(Canvas)},
   * {@link #w_px(int)}, {@link #h_px(int)}
   */
  public void draw() {
    //Log2_01.i(TAG, "--39-->[atcu]# d# draw()", LOG2);
    //===
    if (mAnimMode) {
      if (mAnimPlay) {
        mAnimPlay = false;
        if (mAnimToEndStart) {
          mAnimToEndStart = false;
          animToEnd();
        } else animStart();
      } else {
        _draw0_x2();
      }
    } else {
      _draw0_x2();
    }
  }

  private void _draw0_x2() {
    //Log2_01.i(TAG, "--39-->[atcu]# d# _draw0_x2()", LOG2);
    if (mDrawableStates != null) {
      //= color
      int color = mColorCSL.getColorForState(mDrawableStates, Color.MAGENTA);
      mPaint.setColor(color);
      //= colorFill
      if (mColorFillCSL != null) {
        int colorFill = mColorFillCSL.getColorForState(mDrawableStates, Color.MAGENTA);
        mPaintFill.setColor(colorFill);
      }
    } else {
      throw new SomeException("(debug) виджетом для drawer не был передан drawableStates");
    }
    //===
    _draw();
  }

  public abstract void _draw();

  //``````````````````````````````````````````````````````````````````````````````````````````````

  /**
   * Вызов данного метода нужен на самом деле только для класса {@link N1208_TapeBend}
   *
   * @param color   (1) --
   * @param pressed (2) --
   */
  private void paintsColorUpdate_x2(int color, boolean pressed) {
    //===
    if (mColorBgInner != null) {
      mPaintColorBgInner.setColor(mColorBgInner.val);
    }
    if (mColorBgOuter != null) {
      mPaintColorBgOuter.setColor(mColorBgOuter.val);
    }
  }


  //``````````````````````````````````````````````````````````````````````````````````````````````

  //``` animation ```````````````````````````````````````````````````````````

  /**
   * Наследник должен выполнить что-то вроде valueAnimator.end();
   * <p>
   * Используется чтобы сразу установить конечное значение анимации
   */
  public abstract void animToEnd();

  /**
   * Наследник должен выполнить что-то вроде valueAnimator.start();
   */
  public abstract void animStart();

  /**
   * Наследник должен проверить выполняется ли в настоящий момент анимация. Если да, то ничего не
   * делать. Если нет, то запустить ее
   */
  public void animContinue() {

  }

  /**
   * Наследник должен выполнить что-то вроде valueAnimator.cancel();
   */
  public abstract void animCancel();

  /**
   * Наследник должен выполнить что-то вроде valueAnimator.pause();
   */
  public void animPause() {

  }

  /**
   * Наследник должен выполнить что-то вроде valueAnimator.resume();
   * означает следующее - продолжить ранее поставленную на паузу анимацию
   */
  public void animResume() {

  }

  /**
   * Наследник должен возвращать TRUE если в настоящий момент анимация работает
   *
   * @return --
   */
  public abstract boolean animIsRunning();


  //margins
  //`````````````````````````````````````````````````````````````````````````
  public N1208_AbsDrawer margins(FourSizes_01 p) {
    mP = p;
    return this;
  }

  public N1208_AbsDrawer margins(int dp) {
    mP.setPaddings(dp);
    return this;
  }

  public N1208_AbsDrawer marginsF(float px) {
    mP.setPaddingsF(px);
    return this;
  }

  //colors
  //`````````````````````````````````````````````````````````````````````````

  /**
   * Набор цветов для stroke, для разных состояний
   *
   * @param csl (1) --
   * @return --
   */
  public N1208_AbsDrawer colorStroke(@Nullable ColorStateList csl) {
    if (csl != null) {
      mColorCSL = csl;
      mColor = csl.getDefaultColor();
      paintsColorUpdate_x2(mColor, false);
    }
    return this;
  }

  /**
   * Набор цветов для заливки, для разных состояний
   *
   * @param csl (1) --
   * @return --
   */
  public N1208_AbsDrawer colorFill(ColorStateList csl) {
    if (csl != null) {
      mColorFillCSL = csl;
      int colorFill = csl.getDefaultColor();
      mColorFill = new Color2(colorFill);
    }
    return this;
  }


  /**
   * Набор цветов для заливки, для разных состояний
   *
   * @param csl (1) --
   * @return --
   */
  public N1208_AbsDrawer colorStrokeFill(ColorStateList csl) {
    colorStroke(csl);
    colorFill(csl);
    return this;
  }

  /**
   * Используется некоторыми рисунками, например {@link N1208_TapeBend}
   *
   * @param clr
   * @return
   */
  public N1208_AbsDrawer colorBgOnInner(Color2 clr) {
    mColorBgInner = clr;
    return this;
  }

  /**
   * Используется некоторыми рисунками, например {@link N1208_TapeBend}
   *
   * @param clr
   * @return
   */
  public N1208_AbsDrawer colorBgOnOuter(Color2 clr) {
    mColorBgOuter = clr;
    return this;
  }


  //`````````````````````````````````````````````````````````````````````````
  public N1208_AbsDrawer th(int dp) {
    mTh_dp = dp;
    mPaint.setStrokeWidth(G67G_Draw.px(mTh_dp));
    return this;
  }

  public N1208_AbsDrawer gravity(int gravity) {
    mGravity = gravity;
    return this;
  }


  public N1208_AbsDrawer linkTo(Trans<N1208_AbsDrawer> trans) {
    trans.val = this;
    return this;
  }

  protected float l(float val) {
    float val1 = mP.l_px + val;
//        if (mGravity == Gravity.RIGHT) val1 = mW_px - mP.r_px - mWfig_px + val;
    return val1;
  }

  protected float t(float val) {
    return val + mP.t_px;
  }

  protected float r(float val) {
    float val1 = val - mP.r_px;
//        if (mGravity == Gravity.RIGHT) val1 = mW_px - mP.r_px - mWfig_px + val;
    return val1;
  }

  protected float b(float val) {
    return val - mP.b_px;
  }

  protected void _drawPath() {
    mCanvas.drawPath(mPath, mPaint);
    if (mColorFill != null) {
      mCanvas.drawPath(mPath, mPaintFill);
    }
  }

  /**
   * Отрисовка прямоугольника
   *
   * @param p1    (1) -- левая верхняя точка
   * @param p2    (2) -- правая нижняя точка
   * @param paint (3) --
   */
  protected void drawRect(Prum p1, Prum p2, Paint paint) {
    float pp1 = p1.getSpcX_px(mW_px, mP);
    float pp2 = p1.getSpcY_px(mH_px, mP);
    float pp3 = p2.getSpcX_px(mW_px, mP);
    float pp4 = p2.getSpcY_px(mH_px, mP);
    mCanvas.drawRect(
      pp1,
      pp2,
      pp3,
      pp4,
      paint);
  }

  /**
   * Отрисовка линии
   *
   * @param p1
   * @param p2
   * @param paint
   */
  protected void drawLine(Prum p1, Prum p2, Paint paint) {
    float x1 = p1.getSpcX_px(mW_px, mP);
    float y1 = p1.getSpcY_px(mH_px, mP);

    float x2 = p2.getSpcX_px(mW_px, mP);
    float y2 = p2.getSpcY_px(mH_px, mP);

    mCanvas.drawLine(x1, y1, x2, y2, paint);
  }

  /**
   * Отрисовка линии с возможностью ее поворота вокруг центра
   *
   * @param p1
   * @param p2
   * @param paint
   * @param rotateCenterAngle
   */
  protected void drawLine(Prum p1, Prum p2, Paint paint, int rotateCenterAngle) {
    float x1 = p1.getSpcX_px(mW_px, mH_px, mP, rotateCenterAngle);
    float y1 = p1.getSpcY_px(mW_px, mH_px, mP, rotateCenterAngle);

    float x2 = p2.getSpcX_px(mW_px, mH_px, mP, rotateCenterAngle);
    float y2 = p2.getSpcY_px(mW_px, mH_px, mP, rotateCenterAngle);

    mCanvas.drawLine(x1, y1, x2, y2, paint);
  }

  protected void transform() {
    mMatrix.reset();
    RectF rfNew = new RectF(mP.l_px, mP.t_px, mW_px - mP.r_px, mH_px - mP.b_px);
    mMatrix.setRectToRect(new RectF(0, 0, 24, 24), rfNew, Matrix.ScaleToFit.FILL);
    mPath.transform(mMatrix);
  }
}

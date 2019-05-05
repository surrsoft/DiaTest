package ru.surrsoft.baaz.suite.figures;

import android.animation.ValueAnimator;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;

import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.univers.BuValueAnimator;
import ru.surrsoft.baaz.univers.DrawXY;

/**
 * ВАРИАНТЫ
 * <li> с {@link #gap(int, int)} будет нарисовано разованное кольцо  </li>
 * <li> с {@link #_animInfinityRotate(AppCompatActivity)} + {@link #gap(int, int)} будет бесконечно анимированное
 * разованное кольцо</li>
 * <li> с {@link #asArc(int, int)} будет нарисовано полукольцо  </li>
 * <p>
 * //
 */
public class N1208_Circle extends N1208_AbsDrawer {

    private int mWidthByAngle;
    private float mAngleOfMiddleGap;
    private boolean mbGap;
    private ValueAnimator va;
    private int mAngleOfMiddleGapOnStart;
    private int mStartAngle;
    private int mSweepAngle;
    private boolean mArcMode;
    private ETypeAnim mTypeAnim = ETypeAnim.BASE;

    //==============================================================================================

    public enum ETypeAnim {
        BASE,
        /**
         * Эффект разгоняющегося колеса
         */
        FAST
    }

    //commands
    //``````````````````````````````````````````````````````````````````````````````````````````````

    public void _setTypeAnim(ETypeAnim t) {
        mTypeAnim = t;
    }

    /**
     * Работает вместе с {@link #gap(int, int)}
     *
     * @param animActy (1) -- контекст который используется для подлкючения фрагмента-без-UI к активити для остановки
     *                 анимации
     * @return --
     */
    public N1208_Circle _animInfinityRotate(AppCompatActivity animActy) {
        if (!mbGap) {
            throw new SomeException("(debug) должен быть задан разрыв - см. метод разрыв(int, int)");
        }

        //===
        if (animActy == null) {
            throw new SomeException("(debug) для остановки анимации необходима ссылка на активити");
        }

        //===
        va = new BuValueAnimator(animActy)
                .create();
        va.setFloatValues(360);
        va.setDuration(mAnimDuration);
        va.setInterpolator(mAnimInterp);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animVal = (float) animation.getAnimatedValue();

                if (mTypeAnim == ETypeAnim.FAST) {
                    mAngleOfMiddleGap = (mAngleOfMiddleGap + animVal) % 360;
                } else {
                    mAngleOfMiddleGap = (mAngleOfMiddleGapOnStart + animVal) % 360;
                }
                if (mAnimView == null) throw new SomeException("(debug) mView == null");
                mAnimView.invalidate();
            }
        });
        va.setRepeatCount(ValueAnimator.INFINITE);
        return this;
    }


    //builders
    //``````````````````````````````````````````````````````````````````````````````````````````````

    /**
     * Если кольцо должно быть с разрывом
     *
     * @param widthByAngle     (1) -- угол определяющий длину разрыва (угол сектора)
     * @param angleOfMiddleGap (2) -- угол расположения середины разрыва;
     *                            0 соответствует 15 минутам
     * @return --
     */
    public N1208_Circle gap(int widthByAngle, int angleOfMiddleGap) {
        if (widthByAngle <= 0 || widthByAngle >= 360) {
            throw new SomeException("(debug)");
        }
        if (angleOfMiddleGap < 0 || angleOfMiddleGap > 360) {
            throw new SomeException("(debug)");
        }
        mbGap = true;
        mWidthByAngle = widthByAngle;
        mAngleOfMiddleGap = angleOfMiddleGap;
        //стартовый
        mAngleOfMiddleGapOnStart = angleOfMiddleGap;
        return this;
    }

    /**
     * @param startAngle (1) -- 0...360; 0 соответствует 15 минутам
     * @return --
     */
    public N1208_Circle asArc(int startAngle, int sweepAngle) {
        /*
        Log2_01.i(TAG, "--41> asArc()" + "; startAngle=[" + startAngle + "]"
                + "; sweepAngle=[" + sweepAngle + "]", LOG2);
                */
        mArcMode = true;
        mStartAngle = startAngle % 360;
        mSweepAngle = sweepAngle % 360;
        return this;
    }

    public N1208_Circle typeAnim(ETypeAnim t) {
        mTypeAnim = t;
        return this;
    }

    //``````````````````````````````````````````````````````````````````````````````````````````````
    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void _draw() {
        //Log2_01.i(TAG, "--9:28--> _draw()" + "; getDebugString()=[" + (getDebugString()) + "]", LOG2);
        DrawXY dxy = new DrawXY(mP, mTh_dp, mW_px, mH_px);
        RectF rf = dxy.toRectF();
        if (mArcMode) {
            mCanvas.drawArc(rf, mStartAngle, mSweepAngle, false, mPaint);
        } else if (!mbGap) {
            //===
            if (mColorFill != null) mCanvas.drawOval(rf, mPaintFill);
            if (mPaint != null) {
                mCanvas.drawOval(rf, mPaint);
            }
        } else {
            float startAngle = mAngleOfMiddleGap + mWidthByAngle / 2;
            mCanvas.drawArc(rf, startAngle % 360, 360 - mWidthByAngle, false, mPaint);
        }
    }

    @Override
    public void animToEnd() {
        va.end();
    }

    @Override
    public void animStart() {
        va.start();
    }

    @Override
    public void animCancel() {
        va.cancel();
    }

    @Override
    public void animPause() {
    }

    @Override
    public void animResume() {
    }

    @Override
    public boolean animIsRunning() {
        return true;
    }

}

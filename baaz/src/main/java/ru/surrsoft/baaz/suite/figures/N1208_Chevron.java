package ru.surrsoft.baaz.suite.figures;

import android.animation.ValueAnimator;
import android.graphics.Matrix;
import android.graphics.RectF;

import ru.surrsoft.baaz.SomeException;

/**
 * ВЕРСИЯ 2 1.1 2019-02-05 (stored)
 */
public class N1208_Chevron extends N1208_AbsDrawer {
    private ValueAnimator va;
    /**
     * TRUE если нужно чтобы картинка заполняла все пространство по горизонтали (с соответствующим
     * масштабированием по вертикали)
     */
    private boolean mIsFillDraw;

    //constructors
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public N1208_Chevron() {
    }

    //``````````````````````````````````````````````````````````````````````````````````````````````

    /**
     * см. {@link #mIsFillDraw}
     *
     * @param b (1) --
     * @return --
     */
    public N1208_Chevron fillDraw(boolean b) {
        mIsFillDraw = b;
        return this;
    }

    /**
     * @param angle     (1) --
     * @param поЧасовой (2) --
     * @return --
     */
    public N1208_Chevron _animRotate(int angle, boolean поЧасовой) {
        if (!mRotateCenterAngleIsSet) {
            throw new SomeException(
              "(debug) перед вызовом текущего метода должен быть вызван метод rotateCenter()");
        }
        va = ValueAnimator
          .ofFloat(поЧасовой ? mRotateCenterAngle : mRotateCenterAngle + angle,
            поЧасовой ? mRotateCenterAngle + angle : mRotateCenterAngle)
          .setDuration(mAnimDuration);
        va.setInterpolator(mAnimInterp);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mRotateCenterAngle = (float) animation.getAnimatedValue();
                if (mAnimView == null) throw new SomeException("(debug) mView == null");
                mAnimView.invalidate();
            }
        });

        return this;
    }

    //``````````````````````````````````````````````````````````````````````````````````````````````
    @Override
    public void draw() {
        super.draw();
    }

    public void _draw() {
        mPath.reset();
        if (!mIsFillDraw) {
            mPath.moveTo(6.75f, 24f - 14.6f);
            mPath.lineTo(24f / 2, 24f - 9.5f);
            mPath.lineTo(24f - 6.75f, 24f - 14.6f);
        } else {
            float f26 = 3.1f;
            mPath.moveTo(0f, 24f - 14.6f - 6.75f + f26);
            mPath.lineTo(24f / 2, 24f - 9.5f + 6.75f - f26);
            mPath.lineTo(24f, 24f - 14.6f - 6.75f + f26);
        }

        mMatrix.reset();
        //= трансформация в нужный масштаб
        RectF rfNew = new RectF(mP.l_px, mP.t_px, mW_px - mP.r_px, mH_px - mP.b_px);
        mMatrix.setRectToRect(new RectF(0, 0, 24, 24), rfNew, Matrix.ScaleToFit.FILL);
        mPath.transform(mMatrix);
        //=
        if (mRotateCenterAngle != 0) {
            mMatrix.reset();
            mMatrix.postRotate(mRotateCenterAngle, rfNew.centerX(), rfNew.centerY());
            mPath.transform(mMatrix);
        }
        mCanvas.drawPath(mPath, mPaint);
    }

    //anim
    //``````````````````````````````````````````````````````````````````````````````````````````````
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

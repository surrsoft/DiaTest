package ru.surrsoft.baaz.suite.figures;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;

import ru.surrsoft.baaz.SomeException;

/**
 *
 */
public class N1208_SortASC extends N1208_AbsDrawer {

    private static final String TAG = ":" + N1208_SortASC.class.getSimpleName();

    private final Prum _1_p1;
    private final Prum _2_p1;
    private final Prum _3_p1;
    private final Prum _1_k2;
    private final Prum _2_k2;
    private final Prum _3_k2;
    private AnimatorSet as;

    //==============================================================================================

    public void _anim() {
        long d = mAnimDuration;

        int d1 = Math.round(d * _1_k2.x_rt / _3_k2.x_rt);
        ValueAnimator _1_k2_va = ValueAnimator.ofFloat(0, _1_k2.x_rt).setDuration(d1);
        _1_k2_va.setInterpolator(mAnimInterp);
        _1_k2_va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                _1_k2.x_rt = (float) animation.getAnimatedValue();
            }
        });

        int d2 = Math.round(d * _2_k2.x_rt / _3_k2.x_rt);
        ValueAnimator _2_k2_va = ValueAnimator
                .ofFloat(0, _2_k2.x_rt).setDuration(d2);
        _2_k2_va.setInterpolator(mAnimInterp);
        _2_k2_va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                _2_k2.x_rt = (float) animation.getAnimatedValue();
            }
        });

        ValueAnimator _3_k2_va = ValueAnimator
                .ofFloat(0, _3_k2.x_rt).setDuration(d);
        _3_k2_va.setInterpolator(mAnimInterp);
        _3_k2_va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                _3_k2.x_rt = (float) animation.getAnimatedValue();
                if (mAnimView == null) throw new SomeException("(debug) mView == null");
                mAnimView.invalidate();
            }
        });

        //===
        as = new AnimatorSet();
        as.playTogether(_1_k2_va, _2_k2_va, _3_k2_va);
    }

    public N1208_SortASC() {
        _1_p1 = new Prum(3f / 24, 6f / 24);
        _2_p1 = new Prum(3f / 24, 11f / 24);
        _3_p1 = new Prum(3f / 24, 16f / 24);
        _1_k2 = new Prum(8f / 24, 8f / 24);
        _2_k2 = new Prum(15f / 24, 13f / 24);
        _3_k2 = new Prum(21f / 24, 18f / 24);
        //===
        _anim();
    }

    @Override
    public void draw() {
        super.draw();
    }

    public void _draw() {
        drawRect(_1_p1, _1_k2, mPaintFill);
        drawRect(_2_p1, _2_k2, mPaintFill);
        drawRect(_3_p1, _3_k2, mPaintFill);
    }

    @Override
    public void animToEnd() {
        as.end();
    }

    @Override
    public void animStart() {
        as.start();
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

package ru.surrsoft.baaz.suite.figures;

import android.graphics.RectF;

import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.univers.DrawXY;
import ru.surrsoft.baaz.univers.FourSizes_01;

/**
 * Прямоугольная рамка со скруглениями
 */
public class N1208_RectRound extends N1208_AbsDrawer {

    private int mRoundRaius_px;
    private int mRoundRadiusPercent;
    private int mRoundRadiusLT_px;
    private int mRoundRadiusRT_px;
    private int mRoundRadiusRB_px;
    private int mRoundRadiusLB_px;
    private boolean mRoundRadiusIs4;

    //==========================================================================================
    public N1208_RectRound rounds(int radius_dp) {
        mRoundRaius_px = G67G_Draw.px(radius_dp);
        return this;
    }

    public N1208_RectRound rounds(int lt_dp, int rt_dp, int rb_dp, int lb_dp) {
        mRoundRadiusLT_px = G67G_Draw.px(lt_dp);
        mRoundRadiusRT_px = G67G_Draw.px(rt_dp);
        mRoundRadiusRB_px = G67G_Draw.px(rb_dp);
        mRoundRadiusLB_px = G67G_Draw.px(lb_dp);
        mRoundRadiusIs4 = true;
        return this;
    }

    public N1208_RectRound roundsPercent(int percent) {
        mRoundRadiusPercent = percent;
        return this;
    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void _draw() {
        if (mP == null) mP = new FourSizes_01(0, 0, 0, 0);
        //===
        DrawXY dxy = new DrawXY(mP, mTh_dp, mW_px, mH_px);
        RectF rf = dxy.toRectF();
        //===
        int rii_px = mRoundRaius_px;
        int min = Math.min((int) rf.width(), (int) rf.height());
        if (mRoundRadiusPercent > 0 && mRoundRadiusPercent <= 100) {
            rii_px = min / 2 * mRoundRadiusPercent / 100;
        }
        if (rii_px * 2 > min) {
            rii_px = min / 2;
        }
        //===
        if (mColorFill != null) mCanvas.drawRoundRect(rf, rii_px, rii_px, mPaintFill);
        //===
        if (mPaint != null) {
            if (mRoundRadiusIs4) {
                //= lines
                mCanvas.drawLine(dxy.l_px + mRoundRadiusLT_px, dxy.t_px, dxy.r_px - mRoundRadiusRT_px, dxy.t_px, mPaint);
                mCanvas.drawLine(dxy.l_px + mRoundRadiusLB_px, dxy.b_px, dxy.r_px - mRoundRadiusRB_px, dxy.b_px, mPaint);
                mCanvas.drawLine(dxy.l_px, dxy.t_px + mRoundRadiusLT_px, dxy.l_px, dxy.b_px - mRoundRadiusLB_px, mPaint);
                mCanvas.drawLine(dxy.r_px, dxy.t_px + mRoundRadiusRT_px, dxy.r_px, dxy.b_px - mRoundRadiusRB_px, mPaint);
                //= arcs
                int lt = 2 * mRoundRadiusLT_px;
                int rt = 2 * mRoundRadiusRT_px;
                int lb = 2 * mRoundRadiusLB_px;
                int rb = 2 * mRoundRadiusRB_px;
                if (lt > 0)
                    mCanvas.drawArc(new RectF(dxy.l_px, dxy.t_px, dxy.l_px + lt, dxy.t_px + lt),
                            180, 90, false, mPaint);
                if (rt > 0)
                    mCanvas.drawArc(new RectF(dxy.r_px - rt, dxy.t_px, dxy.r_px, dxy.t_px + rt),
                            270, 90, false, mPaint);
                if (rb > 0)
                    mCanvas.drawArc(new RectF(dxy.r_px - rb, dxy.b_px - rb, dxy.r_px, dxy.b_px),
                            0, 90, false, mPaint);
                if (lb > 0)
                    mCanvas.drawArc(new RectF(dxy.l_px, dxy.b_px - lb, dxy.l_px + lb, dxy.b_px),
                            90, 90, false, mPaint);
            } else {
                mCanvas.drawRoundRect(rf, rii_px, rii_px, mPaint);
            }
        }

    }

    @Override
    public void animToEnd() {

    }

    @Override
    public void animStart() {

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

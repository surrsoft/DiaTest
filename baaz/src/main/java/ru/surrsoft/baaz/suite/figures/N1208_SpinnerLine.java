package ru.surrsoft.baaz.suite.figures;

import android.graphics.Path;

import ru.surrsoft.baaz.cls_c.G67G_Draw;

/**
 * Линия как у Spinner - линия и треугольник справа
 *
 * ВЕРСИЯ 1 1.0 2019-02-05 (stored)
 */
public class N1208_SpinnerLine extends N1208_AbsDrawer {

    private int mHeightTreangle_dp = 5;

    public N1208_SpinnerLine heightTreangle_dp(int dp) {
        mHeightTreangle_dp = dp;
        return this;
    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void _draw() {
        int l = mP.l_px;
        int r = mP.r_px;
        int b = mP.b_px;
        int z = G67G_Draw.px(mHeightTreangle_dp);
        //=== линия
        mCanvas.drawLine(l, mH_px - b, mW_px - r, mH_px - b, mPaint);
        //=== треугольник
        Path path = new Path();
        path.moveTo(mW_px - r - z, mH_px - b);
        path.lineTo(mW_px - r, mH_px - b - z);
        path.lineTo(mW_px - r, mH_px - b);
        mCanvas.drawPath(path, mPaintFill);

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

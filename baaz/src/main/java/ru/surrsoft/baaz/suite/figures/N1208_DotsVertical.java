package ru.surrsoft.baaz.suite.figures;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;

/**
 *
 */

public class N1208_DotsVertical extends N1208_AbsDrawer {
    @Override
    public void _draw() {
        mPath.reset();
        mPath.addCircle(12f, 24f - 18f, 1f, Path.Direction.CW);
        mPath.addCircle(12f, 24f / 2f, 1f, Path.Direction.CW);
        mPath.addCircle(12f, 24f - 6f, 1f, Path.Direction.CW);
        //=
        mMatrix.reset();
        RectF rfNew = new RectF(mP.l_px, mP.t_px, mW_px - mP.r_px, mH_px - mP.b_px);
        mMatrix.setRectToRect(new RectF(0, 0, 24, 24), rfNew, Matrix.ScaleToFit.FILL);
        mPath.transform(mMatrix);
        //=
        _drawPath();
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

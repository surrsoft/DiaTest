package ru.surrsoft.baaz.suite.figures;

import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;

import ru.surrsoft.baaz.univers.Color2;

/**
 *
 */
public class N1208_DragVertical extends N1208_AbsDrawer {

    public N1208_DragVertical() {
        mColorFill = new Color2(Color.BLACK);
    }

    @Override
    public void _draw() {
        float step = 4f;
        mPath.reset();
        mPath.addRect(9f, 3f, 11f, 5f, Path.Direction.CW);
        mPath.addRect(9f, 3f + step, 11f, 5f + step, Path.Direction.CW);
        mPath.addRect(9f, 3f + step * 2, 11f, 5f + step * 2, Path.Direction.CW);
        mPath.addRect(9f, 3f + step * 3, 11f, 5f + step * 3, Path.Direction.CW);
        mPath.addRect(9f, 3f + step * 4, 11f, 5f + step * 4, Path.Direction.CW);

        mPath.addRect(9f + step, 3f, 11f + step, 5f, Path.Direction.CW);
        mPath.addRect(9f + step, 3f + step, 11f + step, 5f + step, Path.Direction.CW);
        mPath.addRect(9f + step, 3f + step * 2, 11f + step, 5f + step * 2, Path.Direction.CW);
        mPath.addRect(9f + step, 3f + step * 3, 11f + step, 5f + step * 3, Path.Direction.CW);
        mPath.addRect(9f + step, 3f + step * 4, 11f + step, 5f + step * 4, Path.Direction.CW);

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

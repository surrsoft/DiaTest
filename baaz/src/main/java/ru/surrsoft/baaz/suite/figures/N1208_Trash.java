package ru.surrsoft.baaz.suite.figures;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;

/**
 * "Корзина"
 * <p>
 *
 */
public class N1208_Trash extends N1208_AbsDrawer {

    @Override
    public void _draw() {

        //=== === крышка
        mPath.reset();
        mPath.moveTo(5f, 4f);
        mPath.lineTo(8.57f, 4f);
        mPath.lineTo(9.53f, 3f);
        mPath.lineTo(24f - 9.53f, 3f);
        mPath.lineTo(24f - 8.57f, 4f);
        mPath.lineTo(24f - 5f, 4f);
        mPath.lineTo(24f - 5f, 6f);
        mPath.lineTo(5f, 6f);
        mPath.close();
        //===
        mMatrix.reset();
        RectF rfNew = new RectF(mP.l_px, mP.t_px, mW_px - mP.r_px, mH_px - mP.b_px);
        mMatrix.setRectToRect(new RectF(0, 0, 24, 24), rfNew, Matrix.ScaleToFit.FILL);
        mPath.transform(mMatrix);
        //===
        _drawPath();

        //=== === основа
        mPath.reset();
        mPath.addRoundRect(
                new RectF(6f, 7f, 24f - 6f, 24f - 3f),
                new float[]{0f, 0f, 0f, 0f, 2f, 2f, 2f, 2f},
                Path.Direction.CW);
        //===
        mMatrix.reset();
        rfNew = new RectF(mP.l_px, mP.t_px, mW_px - mP.r_px, mH_px - mP.b_px);
        mMatrix.setRectToRect(new RectF(0, 0, 24, 24), rfNew, Matrix.ScaleToFit.FILL);
        mPath.transform(mMatrix);
        //===
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

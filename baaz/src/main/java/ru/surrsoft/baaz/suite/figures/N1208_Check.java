package ru.surrsoft.baaz.suite.figures;

import android.graphics.Matrix;
import android.graphics.RectF;

/**
 * "Галочка"
 *
 * https://drive.google.com/file/d/0B8nan3WGLcseTjUwRXF2N3MtNGs/view?usp=sharing
 *
 *
 */
public class N1208_Check extends N1208_AbsDrawer {

    @Override
    public void _draw() {
        mPath.reset();
        mPath.moveTo(4.3f, 24f - 11.2f);
        mPath.lineTo(9f, 24f - 6.6f);
        mPath.lineTo(20.2f, 24f - 17.7f);
        //===
        mMatrix.reset();
        RectF rfNew = new RectF(mP.l_px, mP.t_px, mW_px - mP.r_px, mH_px - mP.b_px);
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

package ru.surrsoft.baaz.suite.figures;

/**
 * Рисует крестик "x"
 * <p>
 * #version 1 25.12.2016
 */
public class N1208_Cancel extends N1208_AbsDrawer {

    //==============================================================================================

    //``````````````````````````````````````````````````````````````````````````````````````````````

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void _draw() {
        mPath.reset();
        mPath.moveTo(mP.l_px, mH_px - mP.b_px);
        mPath.lineTo(mW_px - mP.r_px, mP.t_px);
        mPath.moveTo(mW_px - mP.r_px, mH_px - mP.b_px);
        mPath.lineTo(mP.l_px, mP.t_px);
        //===
        mCanvas.drawPath(mPath, mPaint);
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

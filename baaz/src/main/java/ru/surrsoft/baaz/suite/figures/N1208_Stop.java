package ru.surrsoft.baaz.suite.figures;


/**
 * "Остановка проигрывания" - квадрат в центре
 * <p>
 *
 */
public class N1208_Stop extends N1208_AbsDrawer {

    @Override
    public void _draw() {
        //Log2_01.i(TAG, "--9:28--> _draw()" + "; getDebugString()=[" + (getDebugString()) + "]", LOG2);
        mPath.reset();
        mPath.moveTo(6f, 24f - 18f);
        mPath.lineTo(18f, 24f - 18f);
        mPath.lineTo(18f, 24f - 6f);
        mPath.lineTo(6f, 24f - 6f);
        mPath.close();
        //===
        transform();
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

package ru.surrsoft.baaz.suite.figures;

/**
 * "Запуск проигрывания" - треугольник вправо
 * <p>
 *
 */
public class N1208_Play extends N1208_AbsDrawer {

    @Override
    public void _draw() {

        mPath.reset();
        mPath.moveTo(8f, 24f - 19f);
        mPath.lineTo(19f, 24f - 12f);
        mPath.lineTo(8f, 24f - 5f);
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

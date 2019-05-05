package ru.surrsoft.baaz.suite.figures;


/**
 * "Ручка"
 * <p>
 *
 */
public class N1208_Pensil extends N1208_AbsDrawer {

    @Override
    public void _draw() {
        //=== === "основа"
        mPath.reset();
        mPath.moveTo(4f, 24f - 7f);
        mPath.lineTo(7.7f, 24f - 7f);
        mPath.lineTo(17.8f, 24f - 17f);
        mPath.lineTo(14f, 24f - 20.7f);
        mPath.lineTo(4f, 24f - 10.7f);
        mPath.close();
        //===
        transform();
        //===
        _drawPath();

        //=== === "колпачек"
        mPath.reset();
        mPath.moveTo(18.8f, 24f - 18f);
        mPath.lineTo(20.7f, 24f - 20f);
        mPath.lineTo(17f, 24f - 23.7f);
        mPath.lineTo(15f, 24f - 21.7f);
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

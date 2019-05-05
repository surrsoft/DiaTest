package ru.surrsoft.baaz.suite.figures;

import android.graphics.Color;
import android.graphics.Paint;

import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.widgets2.utils.BuPaint;

/**
 * Здание
 */
public class N2440_Building extends N1208_AbsDrawer {

    private final Paint mPaint2;

    public N2440_Building() {
        mPaint2 = new BuPaint().color(Color.WHITE).fillAndStroke().create();
    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void _draw() {
        float коэфШиринаОкна = 0.2f;
        float ширинаОкна = (mW_px - mP.l_px - mP.r_px) * коэфШиринаОкна;
        float отступ = ((mW_px - mP.l_px - mP.r_px) - ширинаОкна * 2) / 3;
        int k = G67G_Draw.px(1);

        //=== корпус здания
        mCanvas.drawRect(
                l(0),
                t(0),
                r(mW_px),
                b(mH_px),
                mPaintFill);

        //=== левое верхнее окно
        mCanvas.drawRect(
                l(отступ - k),
                t(отступ),
                l(отступ + ширинаОкна - k),
                t(отступ + ширинаОкна),
                mPaint2);
        //=== правое верхнее окно
        mCanvas.drawRect(
                l(отступ * 2 + ширинаОкна),
                t(отступ),
                l(отступ * 2 + ширинаОкна * 2),
                t(отступ + ширинаОкна),
                mPaint2);

        //=== левое нижнее окно
        mCanvas.drawRect(
                l(отступ - k),
                t(отступ * 2 + ширинаОкна - k),
                l(отступ + ширинаОкна - k),
                t(отступ * 2 + ширинаОкна * 2 - k),
                mPaint2);
        //=== правое нижнее окно
        mCanvas.drawRect(
                l(отступ + ширинаОкна + отступ),
                t(отступ + ширинаОкна + отступ - k),
                l(отступ + ширинаОкна + отступ + ширинаОкна),
                t(отступ + ширинаОкна + отступ + ширинаОкна - k),
                mPaint2);

        //=== левое
        mCanvas.drawRect(
                l(отступ - k),
                t(отступ * 3 + ширинаОкна * 2 - k * 2),
                l(отступ + ширинаОкна - k),
                t(отступ * 3 + ширинаОкна * 3 - k * 2),
                mPaint2);
        //=== правое
        mCanvas.drawRect(
                l(отступ + ширинаОкна + отступ),
                t(отступ * 3 + ширинаОкна * 2 - k * 2),
                l(отступ + ширинаОкна + отступ + ширинаОкна),
                t(отступ * 3 + ширинаОкна * 3 - k * 2),
                mPaint2);

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

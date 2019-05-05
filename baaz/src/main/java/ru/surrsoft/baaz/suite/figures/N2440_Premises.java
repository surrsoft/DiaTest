package ru.surrsoft.baaz.suite.figures;

import ru.surrsoft.baaz.cls_c.G67G_Draw;

/**
 * Помещение
 */
public class N2440_Premises extends N1208_AbsDrawer {


  public N2440_Premises() {
    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void _draw() {
        float val = mH_px * 0.35f;
        mCanvas.drawRect(l(0), t(0), r(mW_px), b(mH_px), mPaint);
        mCanvas.drawLine(mW_px / 2, t(0), mW_px / 2, b(mH_px), mPaint);
        //=== ручки окон
        int длинаРучки = G67G_Draw.px(2);
        int отступРучки = G67G_Draw.px(2);
        mCanvas.drawLine(
                mW_px / 2 - отступРучки - длинаРучки,
                mH_px / 2,
                mW_px / 2 - отступРучки,
                mH_px / 2,
                mPaint);
        mCanvas.drawLine(
                mW_px / 2 + отступРучки ,
                mH_px / 2,
                mW_px / 2 + отступРучки+ длинаРучки,
                mH_px / 2,
                mPaint);

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

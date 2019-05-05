package ru.surrsoft.baaz.suite.figures;

import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.suite.experiments.ecud.AnnEcud;
import ru.surrsoft.baaz.suite.experiments.ecud.EEcudTypes;
import ru.surrsoft.baaz.univers.DrawXY;

/**
 * Лента с загибами на конце
 * <p>
 *
 */
public class N1208_TapeBend extends N1208_AbsDrawer {

    @AnnEcud(type = EEcudTypes.INT)
    public int dY_px;

    @AnnEcud(type = EEcudTypes.INT)
    public int dX_px;

    @AnnEcud(type = EEcudTypes.INT)
    public int мКорректорКривизны_px;

    public N1208_TapeBend() {
    }


    //==============================================================================================




    /**
     * Смещение конца ленты вертикальное
     *
     * Если задать отрицательное значение то концы ленты будут загибаться вверх
     *
     * @param dp
     * @return
     */
    public N1208_TapeBend divTypeEndVertic(int dp) {
        int jj = 0;
        if (dp < 0) jj = dp * -1;
        dY_px = G67G_Draw.px(jj);
        if (dp < 0) dY_px = -1 * dY_px;
        return this;
    }

    /**
     * Смещение конца ленты гороизонтальное
     * @param dp
     * @return
     */
    public N1208_TapeBend divTypeEndHoriz(int dp) {
        dX_px = G67G_Draw.px(dp);
        return this;
    }

    /**
     * Корректор кривизны
     * @param dp
     * @return
     */
    public N1208_TapeBend curveCorrect(int dp) {
        мКорректорКривизны_px = G67G_Draw.px(dp);
        return this;
    }


    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void _draw() {
        DrawXY xy = new DrawXY(mP, mTh_dp, mW_px, mH_px);
        //===
        if (mColorBgInner != null) {
            mPath.reset();
            if (dY_px >= 0) {
                mPath.moveTo(0, mH_px);
                mPath.lineTo(mW_px, mH_px);
                mPath.lineTo(mW_px, xy.b_px - dY_px - 1);
                mPath.lineTo(0, xy.b_px - dY_px - 1);
            } else {
                int uu = dY_px * -1;
                mPath.moveTo(0, 0);
                mPath.lineTo(mW_px, 0);
                mPath.lineTo(mW_px, xy.t_px + uu + 1);
                mPath.lineTo(0, xy.t_px + uu + 1);
            }
            mPath.close();
            mCanvas.drawPath(mPath, mPaintColorBgInner);
        }
        //===
        if (mColorBgOuter != null) {
            mPath.reset();
            if (dY_px >= 0) {
                mPath.moveTo(0, 0);
                mPath.lineTo(mW_px, 0);
                mPath.lineTo(mW_px, xy.t_px + dY_px + 1);
                mPath.lineTo(0, xy.t_px + dY_px + 1);
            } else {
                int uu = dY_px * -1;
                mPath.moveTo(0, mH_px);
                mPath.lineTo(mW_px, mH_px);
                mPath.lineTo(mW_px, xy.b_px - uu - 1);
                mPath.lineTo(0, xy.b_px - uu - 1);
            }
            mPath.close();
            mCanvas.drawPath(mPath, mPaintColorBgOuter);
        }
        //===
        mPath.reset();
        if (dY_px >= 0) {
            //рисование начинается с левого нижнего угла
            mPath.moveTo(xy.l_px, xy.b_px);
            mPath.quadTo(xy.l_px + мКорректорКривизны_px, xy.b_px - dY_px, xy.l_px + dX_px, xy.b_px - dY_px);
            mPath.lineTo(xy.r_px - dX_px, xy.b_px - dY_px);
            mPath.quadTo(xy.r_px - мКорректорКривизны_px, xy.b_px - dY_px, xy.r_px, xy.b_px);
            mPath.lineTo(xy.r_px, xy.t_px + dY_px);
            mPath.quadTo(xy.r_px - мКорректорКривизны_px, xy.t_px, xy.r_px - dX_px, xy.t_px);
            mPath.lineTo(xy.l_px + dX_px, xy.t_px);
            mPath.quadTo(xy.l_px + мКорректорКривизны_px, xy.t_px, xy.l_px, xy.t_px + dY_px);
        } else {
            //рисование начинается с левого верхнего угла
            int uu = dY_px * -1;
            mPath.moveTo(xy.l_px, xy.t_px);
            mPath.quadTo(xy.l_px + мКорректорКривизны_px, xy.t_px + uu, xy.l_px + dX_px, xy.t_px + uu);
            mPath.lineTo(xy.r_px - dX_px, xy.t_px + uu);
            mPath.quadTo(xy.r_px - мКорректорКривизны_px, xy.t_px + uu, xy.r_px, xy.t_px);
            mPath.lineTo(xy.r_px, xy.b_px - uu);
            mPath.quadTo(xy.r_px - мКорректорКривизны_px, xy.b_px, xy.r_px - dX_px, xy.b_px);
            mPath.lineTo(xy.l_px + dX_px, xy.b_px);
            mPath.quadTo(xy.l_px + мКорректорКривизны_px, xy.b_px, xy.l_px, xy.b_px - uu);
        }
        mPath.close();
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

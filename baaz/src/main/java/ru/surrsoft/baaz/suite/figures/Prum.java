package ru.surrsoft.baaz.suite.figures;

import ru.surrsoft.baaz.univers.FourSizes_01;

/**
 * _Инкарнация точки. Для рисования.
 * Координаты задаются в относительном измерении.
 * <p>
 * //
 */

public class Prum {
    /**
     * Относительное расположение точки по оси X (отсчет слева)
     */
    public float x_rt;
    /**
     * Относительное расположение точки по оси Y (отсчет сверху)
     */
    public float y_rt;

    //==============================================================================================
    public Prum(float x_rt, float y_rt) {
        this.x_rt = x_rt;
        this.y_rt = y_rt;
    }

    public float getSpcX_px(int w_px, FourSizes_01 fs) {
        int w3 = w_px - fs.l_px - fs.r_px;
        return Math.round(w3 * x_rt) + fs.l_px;
    }

    public float getSpcY_px(int h_px, FourSizes_01 fs) {
        int h3 = h_px - fs.t_px - fs.b_px;
        return Math.round(h3 * y_rt) + fs.t_px;
    }

    public float getSpcX_px(int w_px, int h_px, FourSizes_01 fs, int rotateCenterAngle) {
        if (rotateCenterAngle == 0) return getSpcX_px(w_px, fs);
        //===
        int w3 = w_px - fs.l_px - fs.r_px;
        int h3 = h_px - fs.t_px - fs.b_px;
        int x = Math.round(w3 * x_rt) + fs.l_px;
        int y = Math.round(h3 * y_rt) + fs.t_px;
        float xCenter = fs.l_px + w3 / 2f;
        float yCenter = fs.t_px + h3 / 2f;

        double rad = Math.toRadians(rotateCenterAngle);
        int newx = (int) ((x * Math.cos(rad) - (y * Math.sin(rad))) + xCenter / 2);
        int newy = (int) ((y * Math.cos(rad) + (x * Math.sin(rad))) + yCenter / 2);
        return newx;
    }

    public float getSpcY_px(int w_px, int h_px, FourSizes_01 fs, int rotateCenterAngle) {
        if (rotateCenterAngle == 0) return getSpcY_px(h_px, fs);
        //===
        int w3 = w_px - fs.l_px - fs.r_px;
        int h3 = h_px - fs.t_px - fs.b_px;
        int x = Math.round(w3 * x_rt) + fs.l_px;
        int y = Math.round(h3 * y_rt) + fs.t_px;
        float xCenter = fs.l_px + w3 / 2f;
        float yCenter = fs.t_px + h3 / 2f;

        double rad = Math.toRadians(rotateCenterAngle);
        int newx = (int) ((x * Math.cos(rad) - (y * Math.sin(rad))) + xCenter / 2);
        int newy = (int) ((y * Math.cos(rad) + (x * Math.sin(rad))) + yCenter / 2);
        return newy;
    }

}

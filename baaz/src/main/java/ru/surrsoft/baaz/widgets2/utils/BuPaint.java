package ru.surrsoft.baaz.widgets2.utils;

import android.graphics.Color;
import android.graphics.Paint;

import ru.surrsoft.baaz.cls_c.G67G_Draw;

/**
 * По умолчанию стиль STROKE, толщина линии = 1.
 * <p>
 * #version 1 10.12.2016  #id [[w417w]]
 */
public class BuPaint {

    private Paint.Style mStyle = Paint.Style.STROKE;
    private int mColor = Color.RED;
    private int mTh_px = 1;

    //==============================================================================================


    public BuPaint() {
    }

    public BuPaint(int th_dp, int color) {
        mTh_px = G67G_Draw.px(th_dp);
        mColor = color;
    }

    public BuPaint color(int c) {
        mColor = c;
        return this;
    }

    public BuPaint th(int dp) {
        mTh_px = G67G_Draw.px(dp);
        return this;
    }

    public BuPaint stroke() {
        mStyle = Paint.Style.STROKE;
        return this;
    }

    public BuPaint fill() {
        mStyle = Paint.Style.FILL;
        return this;
    }

    public BuPaint fillAndStroke() {
        mStyle = Paint.Style.FILL_AND_STROKE;
        return this;
    }

    public Paint create() {
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(mColor);
        p.setStyle(mStyle);
        p.setStrokeWidth(mTh_px);
        return p;
    }
}

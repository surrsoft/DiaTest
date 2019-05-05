package ru.surrsoft.baaz.widgets2;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import ru.surrsoft.baaz.univers.U;

/**
 * Создание фигур на базе {@link GradientDrawable}
 *
 * ВЕРСИЯ 1 1.0 2019-02-05 (stored)
 */
public class BuShape {

    private int mColor = Color.TRANSPARENT;
    private int mW_dp;
    private int mH_dp;

    //constructors
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public BuShape() {
    }

    //builders
    //``````````````````````````````````````````````````````````````````````````````````````````````

    /**
     * @param color (1) --
     * @return --
     */
    public BuShape color(int color) {
        mColor = color;
        return this;
    }

    /**
     *
     * @param w_dp (1) --
     * @param h_dp (2) --
     * @return --
     */
    public BuShape wh(int w_dp, int h_dp) {
        mW_dp = w_dp;
        mH_dp = h_dp;
        return this;
    }

    //make
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public GradientDrawable create() {
        GradientDrawable gd = new GradientDrawable();
        //===
        gd.setColor(mColor);
        //===
        if (mW_dp > 0 || mH_dp > 0) {
            gd.setSize(U.px(mW_dp), U.px(mH_dp));
        }
        return gd;
    }

}

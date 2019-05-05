package ru.surrsoft.baaz.widgets2.forwg;

import android.view.ViewGroup;

import ru.surrsoft.baaz.univers.FourSizes_01;

/**
 * Представление margins
 * <p>
 * ВЕРСИЯ 1 1.2 2018-12-16 (stored)
 */
public class Margins_01 extends FourSizes_01 {

    //constructors
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public Margins_01(int dp) {
        super(dp);
    }

    public Margins_01(int l_dp, int t_dp, int r_dp, int b_dp) {
        super(l_dp, t_dp, r_dp, b_dp);
    }

    //``````````````````````````````````````````````````````````````````````````````````````````````
    public void setFor(ViewGroup.MarginLayoutParams lp) {
        lp.leftMargin = l_px;
        lp.topMargin = t_px;
        lp.rightMargin = r_px;
        lp.bottomMargin = b_px;
    }
}

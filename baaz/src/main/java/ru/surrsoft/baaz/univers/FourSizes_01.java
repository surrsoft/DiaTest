package ru.surrsoft.baaz.univers;

import ru.surrsoft.baaz.Bysa_01;
import ru.surrsoft.baaz.cls_c.G67G_Draw;

/**
 * Стандартное "отражение" для paddings и т.п.
 * <p>
 * #version 1 08.12.2016  #id [[w414w]]
 */
public class FourSizes_01 {

    public int l_dp;
    public int t_dp;
    public int r_dp;
    public int b_dp;

    public int l_px;
    public int t_px;
    public int r_px;
    public int b_px;

    //==============================================================================================
    public FourSizes_01(int paddings_dp) {
        l_dp = t_dp = r_dp = b_dp = paddings_dp;
        l_px = t_px = r_px = b_px = G67G_Draw.px(paddings_dp);
    }

    public FourSizes_01(int l_dp, int t_dp, int r_dp, int b_dp) {
        this.l_dp = l_dp;
        this.t_dp = t_dp;
        this.r_dp = r_dp;
        this.b_dp = b_dp;

        l_px = G67G_Draw.px(l_dp);
        t_px = G67G_Draw.px(t_dp);
        r_px = G67G_Draw.px(r_dp);
        b_px = G67G_Draw.px(b_dp);
    }

    public void setPaddings(int paddings_dp) {
        this.l_dp = paddings_dp;
        this.t_dp = paddings_dp;
        this.r_dp = paddings_dp;
        this.b_dp = paddings_dp;

        l_px = G67G_Draw.px(l_dp);
        t_px = G67G_Draw.px(t_dp);
        r_px = G67G_Draw.px(r_dp);
        b_px = G67G_Draw.px(b_dp);
    }

    public void setPaddingsF(float paddings_px) {
        l_px = t_px = r_px = b_px = Math.round(paddings_px);
        //===
        int dp = Math.round(paddings_px / Bysa_01.fDensity);
        l_dp = t_dp = r_dp = b_dp = dp;
    }

    @Override
    public String toString() {
        BuString bst = new BuString().divider(",");
        bst
                .append("l_px=" + l_px)
                .append("t_px=" + t_px)
                .append("r_px=" + r_px)
                .append("b_px=" + b_px);
        return bst.toString();
    }
}

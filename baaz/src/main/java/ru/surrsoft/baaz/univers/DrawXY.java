package ru.surrsoft.baaz.univers;

import android.graphics.RectF;

import ru.surrsoft.baaz.cls_c.G67G_Draw;

/**
 * #version 1 09.12.2016  #id [[w416w]]
 */
public class DrawXY {

    public int th_px;
    public int th_dp;
    public int l_px;
    public int t_px;
    public int r_px;
    public int b_px;

    public DrawXY(int l_px, int t_px, int r_px, int b_px) {
        this.l_px = l_px;
        this.t_px = t_px;
        this.r_px = r_px;
        this.b_px = b_px;
    }

    public DrawXY(FourSizes_01 p, int th_dp, int w_px, int h_px) {
        if (p == null) p = new FourSizes_01(0, 0, 0, 0);

        this.th_dp = th_dp;
        this.th_px = G67G_Draw.px(th_dp);

        int th_px2 = th_px / 2;
        if (th_px2 == 0) th_px2 = 1;

        l_px = p.l_px + th_px2;
        t_px = p.t_px + th_px2 + 1;
        r_px = w_px - p.r_px - th_px2 - 1;
        b_px = h_px - p.b_px - th_px2 - 1;
    }

    public RectF toRectF() {
        return new RectF(l_px, t_px, r_px, b_px);
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

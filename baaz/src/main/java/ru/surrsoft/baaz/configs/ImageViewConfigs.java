package ru.surrsoft.baaz.configs;

import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.new_.N2269_IsDrawable;
import ru.surrsoft.baaz.univers.Color2;
import ru.surrsoft.baaz.univers.EDrawables;
import ru.surrsoft.baaz.univers.IApply;
import ru.surrsoft.baaz.w265w_configer.ann.N2269_IsConstantsOfClass;

/**
 * #version 1 17.06.2016  #id [[w275w]]
 */
public class ImageViewConfigs implements IApply {


    @N2269_IsDrawable
    public String drw = null;
    public Color2 color = null;
    public int paddings_dp;
    public int sizeWidth_dp;
    public int sizeHeight_dp;
    @N2269_IsConstantsOfClass(clazz = Gravity.class, clazzConstant = int.class)
    public int[] gravityLayoutType;

    public ImageViewConfigs() {

    }

    public void apply_w282w(Object iv0) {
        ImageView iv = (ImageView) iv0;
        if (drw != null) {
            EDrawables drw = EDrawables.valueOf(this.drw);
            iv.setImageDrawable(drw.val);
            if (color != null) {
                DrawableCompat.setTint(drw.val, color.val);
            }
            //===
            int px = G67G_Draw.px(paddings_dp);
            iv.setPadding(px, px, px, px);
            //===
            if (sizeWidth_dp > 0) iv.getLayoutParams().width = G67G_Draw.px(sizeWidth_dp);
            if (sizeHeight_dp > 0) iv.getLayoutParams().height = G67G_Draw.px(sizeHeight_dp);
            //=== gravity
            if (gravityLayoutType != null) {
                Object lp = iv.getLayoutParams();
                if (lp instanceof LinearLayout.LayoutParams) {
                    LinearLayout.LayoutParams lp1 = (LinearLayout.LayoutParams) lp;
                    int ii;
                    if (gravityLayoutType.length > 0) {
                        switch (gravityLayoutType.length) {
                            case 1:
                                ii = gravityLayoutType[0];
                                break;
                            case 2:
                                ii = gravityLayoutType[0] | gravityLayoutType[1];
                                break;
                            default:
                                ii = gravityLayoutType[0] | gravityLayoutType[1];
                                for (int i = 2; i < gravityLayoutType.length; i++) {
                                    ii = ii | gravityLayoutType[i];
                                }
                                break;
                        }
                        //noinspection WrongConstant
                        lp1.gravity = ii;
                        iv.setLayoutParams(lp1);
                    }
                }
            }

            //===
            iv.invalidate();
        }
    }

    @Override
    public void commit_w282w() {

    }
}

package ru.surrsoft.baaz.univers;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import ru.surrsoft.baaz.Bysa_01;
import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.suite.figures.N1208_AbsDrawer;
import ru.surrsoft.baaz.suite.figures.N1208_Circle;
import ru.surrsoft.baaz.widgets2.BuilderTV;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;

/**
 *
 */
public enum EColors {
    _NULL(0),
    C52(Color.BLUE),
    C54(Color.RED),
    ;

    public final int val;
    public final Color2 color2;

    EColors(int color) {
        this.val = color;
        color2 = new Color2(color);
    }

    private static int get(int r) {
        return Bysa_01.appContext.getResources().getColor(r);
    }

    public static ScrollView getSelectView(Context context,
                                           int startColor,
                                           final UniPresenter<Integer> presenter) {
        ScrollView ret = new ScrollView(context);
        LinearLayout lay = new BuLayLinear_01(context)
          .build();
        ret.addView(lay);

        for (final EColors elem : values()) { //LOOP
            LinearLayout layElem = new BuLayLinear_01(context)
              .addTo(lay)
              .horizontal()
              .margins(0, 7, 0, 0)
              .build();
            //== изображение цвета
            N1208_AbsDrawer drw = null;
            if (elem.val == startColor) {
                drw = new N1208_Circle()
                  .colorStroke(ColorStateList.valueOf(Color.BLACK))
                  .colorFill(ColorStateList.valueOf(Color.WHITE))
                  .th(3)
                  .margins(16);
            }
            new BuilderTV(context)
              .addTo(layElem)
              .drawer(drw)
              .wh(48)
              .bgColor(elem.val)
              .textColor(Color.BLACK)
              .onclick(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      if (presenter != null) {
                          presenter.fun(elem.val);
                      }
                  }
              })
              .create();
            //==
            new BuilderTV(context)
              .addTo(layElem)
              .text(elem.name() + " (" + G67G_Draw.colorIntToString(elem.val) + ", " + elem.val + ")")
              .textColor(Color.BLACK)
              .gravitySelf(EGravity.CV.val)
              .margins(7, 0, 0, 0)
              .create();
        } //LOOP
        return ret;
    }


}

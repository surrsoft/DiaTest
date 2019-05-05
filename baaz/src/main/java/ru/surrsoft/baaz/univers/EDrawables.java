package ru.surrsoft.baaz.univers;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.LinearLayout;

import ru.surrsoft.baaz.Bysa_01;
import ru.surrsoft.baaz.R;
import ru.surrsoft.baaz.widgets2.BuilderTV;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;


/**
 * Набор drawables
 * #version 1 16.06.2016  #id [[w273w]]
 */
public enum EDrawables {
  _LAUNCHER(Bysa_01.appContext.getResources().getDrawable(R.mipmap.ic_launcher)),
  _PENCIL(Bysa_01.appContext.getResources().getDrawable(R.drawable.ic_pencil)),
  _CHECK(Bysa_01.appContext.getResources().getDrawable(R.drawable.ic_check)),
  _CLOSE(Bysa_01.appContext.getResources().getDrawable(R.drawable.ic_close)),
  _HELP_CIRCLE_OUTLINE(Bysa_01.appContext.getResources().getDrawable(R.drawable.ic_help_circle_outline)),
  _FILTER(Bysa_01.appContext.getResources().getDrawable(R.drawable.ic_filter)),
  _FILTER2(Bysa_01.appContext.getResources().getDrawable(R.drawable.ic_filter_outline)),
  _PLUS(Bysa_01.appContext.getResources().getDrawable(R.drawable.ic_plus)),
  N2241_B_DOTS(Bysa_01.appContext.getResources().getDrawable(R.mipmap.n2241_b_dots)),
  EYE_ON(Bysa_01.appContext.getResources().getDrawable(R.drawable.ic_eye)),
  EYE_OFF(Bysa_01.appContext.getResources().getDrawable(R.drawable.ic_eye_off)),;

  public final Drawable val;

  EDrawables(Drawable drw) {
    this.val = drw;
  }

  public static LinearLayout getLayAllDrws() {
    LinearLayout lay = new BuLayLinear_01(Bysa_01.appContext).build();
    new BuilderTV(Bysa_01.appContext).text("--- EDrawables ---")
      .addTo(lay)
      .bgColor(Color.YELLOW)
      .textColor(Color.BLACK)
      .fontSize(16)
      .margins(0, 16, 0, 0)
      .create();
    for (EDrawables elem : EDrawables.values()) {
      new BuilderTV(Bysa_01.appContext)
        .text(elem.name())
        .textColor(Color.BLACK)
        .addTo(lay)
        .create();
      ImageView iv = new ImageView(Bysa_01.appContext);
      iv.setImageDrawable(elem.val);
      lay.addView(iv);
    }
    return lay;
  }
}

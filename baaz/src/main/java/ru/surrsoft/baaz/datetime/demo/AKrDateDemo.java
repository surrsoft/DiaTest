package ru.surrsoft.baaz.datetime.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ru.surrsoft.baaz.datetime.EDateFormat;
import ru.surrsoft.baaz.datetime.KrDate;
import ru.surrsoft.baaz.widgets2.BuilderTV_02;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;
import ru.surrsoft.baaz.widgets2.buLay.BuLayScroll_01;
import ru.surrsoft.baaz.widgets2.nviews.NLinearLayout;
import ru.surrsoft.baaz.widgets2.nviews.NScrollView;

/**
 * //needdesc
 * <p>
 * //new//
 */
public class AKrDateDemo extends AppCompatActivity {
  private NLinearLayout layMain;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    //---
    NScrollView layScroll = new BuLayScroll_01(this)
      .buAsContentView(true)
      .build();

    layMain = new BuLayLinear_01(this)
      .addTo(layScroll)
      .paddings(10)
      .build();

    //---
    KrDate krDate = new KrDate()
      .constrCurrent();

    //---
    mt("[jnne] DD.MM.YYYY", krDate.get(EDateFormat.JNNE));
    mt("[bgit] YYYY-MM-DD", krDate.get(EDateFormat.BGIT));
    mt("[ivab] YYYY-MM-DD HH:MM:SS", krDate.get(EDateFormat.IVAB));

  }

  private void mt(String desc, Object value) {
    int confTextSize = 18;
    //---
    new BuilderTV_02(this)
      .addTo(layMain)
      .text(desc)
      .textColor(Color.BLUE)
      .textSize(confTextSize)
      .margins(0, 5, 0, 0)
      .build();
    //---
    new BuilderTV_02(this)
      .addTo(layMain)
      .text(value.toString())
      .textColor(Color.RED)
      .textSize(confTextSize)
      .build();
    //---
  }
}

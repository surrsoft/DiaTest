package ru.surrsoft.baaz.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import ru.surrsoft.baaz.R;
import ru.surrsoft.baaz.tclasses.TInflate_01;
import ru.surrsoft.baaz.univers.LogInfo;
import ru.surrsoft.baaz.widgets2.BuilderTV;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;
import ru.surrsoft.baaz.widgets2.nviews.NLinearLayout;

/**
 * Демонстрация инфлейта разного
 */
public class AInflateDemo_01 extends AppCompatActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);


    NLinearLayout layMain = new BuLayLinear_01(this)
      .asContentView(this)
      .hMP()
      .bgColor(Color.GRAY)
      .paddings(10)
      .build();

    //---
    NLinearLayout lay1 = new BuLayLinear_01(this)
      .addTo(layMain)
      .bgColor(Color.YELLOW)
      .paddings(10)
      .addChild(new BuilderTV(this)
        .text("text 1")
        .create())
      .build();

    //---
    NLinearLayout lay2 = new BuLayLinear_01(this)
      .addTo(layMain)
      .bgColor(Color.GREEN)
      .paddings(10)
      .addChild(new BuilderTV(this)
        .text("text 1")
        .create())
      .build();

    //---
    TInflate_01.inflate(R.layout.demo_190310_191900, lay1);

    lay1.addView(layMain);
    String st = LogInfo.viewTreeInfo(layMain, 0);
    Log.d("tag", "st [" + st + "]");

  }
}

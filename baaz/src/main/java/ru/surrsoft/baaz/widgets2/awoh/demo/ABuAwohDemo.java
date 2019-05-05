package ru.surrsoft.baaz.widgets2.awoh.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ru.surrsoft.baaz.widgets2.BuilderTV;
import ru.surrsoft.baaz.widgets2.awoh.BuAwoh;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;
import ru.surrsoft.baaz.widgets2.nviews.NLinearLayout;

/**
 * //needdesc
 * <p>
 * //new//
 */
public class ABuAwohDemo extends AppCompatActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    //---
    NLinearLayout layMain = new BuLayLinear_01(this)
      .asContentView(this)
      .paddings(10)
      .build();

    //---
    new BuilderTV(this)
      .addTo(layMain)
      .text("-1-")
      .create();

    new BuAwoh(this)
      .addTo(layMain)
      .buText("Google-1")
      .focusableOnStart(false)
      .build_B();

    //---
    new BuilderTV(this)
      .addTo(layMain)
      .text("-2-")
      .create();

    new BuAwoh(this)
      .addTo(layMain)
      .buText("Google-2")
      .focusableOnStart(true)
      .build_B();
  }
}

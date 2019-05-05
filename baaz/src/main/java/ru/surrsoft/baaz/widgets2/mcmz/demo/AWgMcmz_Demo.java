package ru.surrsoft.baaz.widgets2.mcmz.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ru.surrsoft.baaz.new_02.p02_mmvd.a.MmvdNumberIntegerNilPlus;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;
import ru.surrsoft.baaz.widgets2.nviews.NLinearLayout;
import ru.surrsoft.baaz.widgets2.mcmz.WgMcmz;

/**
 * //needdesc
 * <p>
 * //new//
 */
public class AWgMcmz_Demo extends AppCompatActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //---
    NLinearLayout layMain = new BuLayLinear_01(this)
      .asContentView(this)
      .paddings(5)
      .build();

    //---
    new WgMcmz(this)
      .buAddTo(layMain)
      .buValue(new MmvdNumberIntegerNilPlus("11"))
      //.buHintText("hint sample")
      .build();

  }
}

package ru.surrsoft.baaz.widgets2.amgr.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ru.surrsoft.baaz.new_02.p02_mmvd.a.MmvdNumberIntegerNilPlus;
import ru.surrsoft.baaz.new_02.p02_mmvd.a.MmvdTextMultiLine;
import ru.surrsoft.baaz.new_02.p02_mmvd.a.MmvdTextOneLine;
import ru.surrsoft.baaz.widgets2.amgr.WgAmgr;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;
import ru.surrsoft.baaz.widgets2.buLay.BuLayScroll_01;
import ru.surrsoft.baaz.widgets2.nviews.NLinearLayout;

/**
 * //needdesc
 * <p>
 * //new//
 */
public class AWgAmgrDemo extends AppCompatActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    //---
    NLinearLayout layMain = new BuLayLinear_01(this)
      .addTo(new BuLayScroll_01(this)
        .buAsContentView(true)
        .build()
      )
      .paddings(20)
      .build();

    //---
    new WgAmgr(this)
      .buAddTo(layMain)
      .buTitle("ИМЯ")
      .buValue(new MmvdTextOneLine("Google"))
      .buValuePosition(WgAmgr.EValuePosition.BOTTOM)
      .build_B();

    //---
    new WgAmgr(this)
      .buAddTo(layMain)
      .buTitle("URL")
      .buValue(new MmvdTextOneLine("https://www.google.ru"))
      .buValuePosition(WgAmgr.EValuePosition.BOTTOM)
      .build_B();

    //---
    new WgAmgr(this)
      .buAddTo(layMain)
      .buTitle("URL-2")
      .buValue(new MmvdTextOneLine("https://yandex.ru"))
      .buValuePosition(WgAmgr.EValuePosition.RIGHT)
      .build_B();

    //---
    new WgAmgr(this)
      .buAddTo(layMain)
      .buTitle("ПРИМЕЧАНИЕ-2")
      .buValue(new MmvdTextMultiLine("это примечание, это примечание, это примечание, это примечание, " +
        "это примечание, это примечание, это примечание, это примечание, это примечание, это примечание, " +
        "это примечание, это примечание, это примечание, это примечание, это примечание, это примечание, " +
        "это примечание, это примечание, это примечание, это примечание, это примечание, это примечание, "))
      .buValuePosition(WgAmgr.EValuePosition.BOTTOM)
      .buLayBasic(new BuLayLinear_01(this)
        .paddings(0, 20, 0, 10)
      )
      .build_B();

    //---
    new WgAmgr(this)
      .buAddTo(layMain)
      .buTitle("ЧИСЛО НАЖАТИЙ")
      .buValue(new MmvdNumberIntegerNilPlus("0"))
      .buValuePosition(WgAmgr.EValuePosition.RIGHT)
      .build_B();

  }
}

package ru.surrsoft.baaz.widgets2.uasa.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ru.surrsoft.baaz.suite.lvl0.EEditTextInputTypes;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;
import ru.surrsoft.baaz.widgets2.nviews.NLinearLayout;
import ru.surrsoft.baaz.widgets2.uasa.WgUasa_01;

/**
 * //needdesc
 */
public class AWgUasa_01_Demo extends AppCompatActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    //---
    NLinearLayout layMain = new BuLayLinear_01(this)
      .asContentView(this)
      .paddings(5)
      .build();

    //---
    new WgUasa_01(this)
      .buAddTo(layMain)
      .buText("sample text")
      .buHintText("hint text")
      .buInputType(EEditTextInputTypes.INTEGER_POSITIVE)
      .build();

  }
}

package ru.surrsoft.baaz.widgets2.buedittext.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;
import ru.surrsoft.baaz.widgets2.buedittext.BuEditText_01;
import ru.surrsoft.baaz.widgets2.nviews.NLinearLayout;

/**
 * Демонстрация класса {@link BuEditText_01}
 */
public class ABuEditTextDemo_01 extends AppCompatActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    //---
    NLinearLayout layMain = new BuLayLinear_01(this)
      .asContentView(this)
      .paddings(5)
      .build();

    //---
    new BuEditText_01(this)
      .buAddTo(layMain)
      .buText("start text 1")
      .buHint("hint example")
      .build();

    //---
    new BuEditText_01(this)
      .buAddTo(layMain)
      .buText("start text 2")
      .buHint("hint example")
      .build();

  }
}

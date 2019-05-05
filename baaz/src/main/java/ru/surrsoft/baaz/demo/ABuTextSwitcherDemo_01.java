package ru.surrsoft.baaz.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.TextSwitcher;

import ru.surrsoft.baaz.univers.Color2;
import ru.surrsoft.baaz.widgets2.BuButton_01;
import ru.surrsoft.baaz.widgets2.BuTextSwitcher_01;
import ru.surrsoft.baaz.widgets2.BuilderTV;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;
import ru.surrsoft.baaz.widgets2.nviews.NLinearLayout;

/**
 * Демонстрация работы билдера {@link BuTextSwitcher_01}
 */
public class ABuTextSwitcherDemo_01 extends AppCompatActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    NLinearLayout layMain = new BuLayLinear_01(this)
      .asContentView(this)
      .paddings(10)
      .build();

    //---
    TextSwitcher ts = new BuTextSwitcher_01(this)
      .buAddTo(layMain)
      .buTextStart("init")
      .buBgColor(new Color2(Color.RED))
      .buViewFactory(() -> new BuilderTV(ABuTextSwitcherDemo_01.this)
        .layParamClass(FrameLayout.LayoutParams.class)
        .textColor(Color.BLUE)
        .bgColor(Color.YELLOW)
        .textSize(18)
        .create())
      .buOnclick(v -> ((TextSwitcher) v).setText("меня нажали"))
      .build();

    //---
    new BuButton_01(this)
      .buAddTo(layMain)
      .buText("set text 001")
      .buListener(v -> ts.setText("001"))
      .build();

    //---
    new BuButton_01(this)
      .buAddTo(layMain)
      .buText("set text 002")
      .buListener(v -> ts.setText("002"))
      .build();

    //---
    String s = "003-003-003-003-003-003-003-003-003-003-003-003-003-003-";
    new BuButton_01(this)
      .buAddTo(layMain)
      .buText("set text " + s)
      .buListener(v -> ts.setText(s))
      .build();

  }
}

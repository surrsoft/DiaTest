package ru.surrsoft.baaz.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import ru.surrsoft.baaz.widgets.other.BuSeekBar_01;
import ru.surrsoft.baaz.widgets2.BuilderTV;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;
import ru.surrsoft.baaz.widgets2.forwg.Margins_01;
import ru.surrsoft.baaz.widgets2.nviews.NLinearLayout;

/**
 * Демонстрация работы BuSeekBar_01
 */
public class ASeekBarDemo_01 extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //---
    NLinearLayout layMain = new BuLayLinear_01(this)
      .asContentView(this)
      .margins(10, 10, 10, 10)
      .build();

    //---
    new BuSeekBar_01(this)
      .buAddTo(layMain)
      .buMargins(new Margins_01(10))
      .buProgressMax(100)
      .buProgressStart(50)
      .buLabel(new BuilderTV(this).text("Величина: %d"))
      .buProgressListener(iProgress -> {
        Toast.makeText(this, String.format("progress: %d", iProgress), Toast.LENGTH_SHORT).show();
      })
      .build();
  }

}

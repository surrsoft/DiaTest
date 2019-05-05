package ru.surrsoft.baaz.demo.pkor_b_demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ru.surrsoft.baaz.univers.U;
import ru.surrsoft.baaz.widgets2.BuButton_01;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;
import ru.surrsoft.baaz.widgets2.nviews.NLinearLayout;
import ru.surrsoft.baaz.widgets2.pkor_b.WgPkor_B;
import ru.surrsoft.baaz.widgets2.pkor_b.a.EDragMode;

/**
 * Активити для демонстрации WgPkor_B
 */
public class AWgPkor_B_Demo_01 extends AppCompatActivity {

  private WgPkor_B pkor;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    //---
    NLinearLayout layMain = new BuLayLinear_01(this)
      .asContentView(this)
      .bgColor(Color.YELLOW)
      .hMP()
      .build();

    //---
    new BuButton_01(this)
      .buAddTo(layMain)
      .buText("Восстановить исходное состояние списка")
      .buListener(v -> {
        pkor.refresh(new DwzrImplDemo(DwzrImplDemo.Mode.MODE_RESET));
      })
      .build();

    //---
    pkor = new WgPkor_B<VHolder, PkorDemoElem>(this)
      .buAddTo(layMain)
      .buDwzr(new DwzrImplDemo(DwzrImplDemo.Mode.MODE_INITIAL))
      //.buNanvs(C_Utils.sampleNanvsGenerate())
      .buDragAndDrop(EDragMode.HANDLE)
      //.buPresenter(new Presenter())
      .buBgColor(U.c2(Color.GREEN))
      .buMenuElemShow(true);
    //---
    pkor.build();

  }


}

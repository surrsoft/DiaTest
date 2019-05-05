package ru.surrsoft.baaz.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ru.surrsoft.baaz.TActivity_01;
import ru.surrsoft.baaz.datetime.demo.AKrDateDemo;
import ru.surrsoft.baaz.demo.cardview.ACardViewDemo;
import ru.surrsoft.baaz.demo.pkor_b_demo.AWgPkor_B_Demo_01;
import ru.surrsoft.baaz.demo.pkor_demo.AWgPkorDemo_01;
import ru.surrsoft.baaz.p01_db.demo.ADrvxDemo;
import ru.surrsoft.baaz.widgets2.BuButton_01;
import ru.surrsoft.baaz.widgets2.BuilderTV;
import ru.surrsoft.baaz.widgets2.amgr.demo.AWgAmgrDemo;
import ru.surrsoft.baaz.widgets2.awoh.demo.ABuAwohDemo;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;
import ru.surrsoft.baaz.widgets2.buLay.BuLayScroll_01;
import ru.surrsoft.baaz.widgets2.budialogview.demo.ABuDialogDemo;
import ru.surrsoft.baaz.widgets2.buedittext.demo.ABuEditTextDemo_01;
import ru.surrsoft.baaz.widgets2.mcmz.demo.AWgMcmz_Demo;
import ru.surrsoft.baaz.widgets2.nviews.NLinearLayout;
import ru.surrsoft.baaz.widgets2.uasa.demo.AWgUasa_01_Demo;
import ru.surrsoft.baaz.widgets2.ztoa.demo.AZtoaDemo_01;

/**
 * Стартовое активити для запуска разных демок
 */
public class ABaazDemo extends AppCompatActivity {

  private NLinearLayout layMain;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    //---
    layMain = new BuLayScroll_01(this)
      .buAsContentView(true)
      .buPaddings(10)
      .build_B();

    //---
    mt(AKrDateDemo.class, "AKrDateDemo", "AKrDateDemo");
    mt(ATimeDemo.class, "ATimeDemo", "ATimeDemo");
    mt(A_FAB_Demo.class, "A_FAB_Demo", "FloatingActionButton");
    mt(AWgAmgrDemo.class, "AWgAmgrDemo.java", "WgAmgr");
    mt(AWgMcmz_Demo.class, "AWgMcmzDemo.java", "WgMcmz - EditText-подобный-виджет");
    mt(AWgUasa_01_Demo.class, "AWgUasa_01_Demo.java", "WgUasa_01 - EditText-подобный-виджет");
    mt(ABuAwohDemo.class, "ABuAwohDemo.java", "BuAwoh - EditText-подобный-виджет");
    mt(AZtoaDemo_01.class, "AZtoaDemo_01.java", "WgZtoa_01");
    mt(ABuEditTextDemo_01.class, "ABuEditTextDemo_01.java", "BuEditText");
    mt(ABuDialogDemo.class, "ABuDialogDemo.java", "BuDialoView");
    mt(ADrvxDemo.class, "ADrvxDemo.java", "активити с демонстрациями [drvx]-интерполяции-реляционной-БД");
    mt(ACardViewDemo.class, "cardview/ACardViewDemo.java", "активити демонстрации CardView");
    mt(ASeekBarDemo_01.class, "ASeekBarDemo.java", "активити демонстрации SeekBar");
    mt(ABuTextSwitcherDemo_01.class, "ABuTextSwitcherDemo.java", "активити демонстрации ABuTextSwitcher");
    mt(AInflateDemo_01.class, "AInflateDemo.java", "активити демонстрации inflate");
    mt(AWgPkorDemo_01.class, "AWgPkorDemo.java", "активити демонстрации WgPkor (RecyclerView)");
    mt(AWgPkor_B_Demo_01.class, "AWgPkor_B_Demo.java", "активити демонстрации WgPkor_B (RecyclerView)");

  }

  private void mt(Class clsActy, String stBtnText, String stLavelText) {
    new BuLayLinear_01(this)
      .addTo(layMain)
      .addChild(new BuButton_01(this)
        .buText(stBtnText)
        .buListener(v -> {
          TActivity_01.start(this, clsActy);
        })
        .build())
      .addChild(new BuilderTV(this)
        .text(stLavelText)
        .create())
      .paddingB(15)
      .build();
  }

}

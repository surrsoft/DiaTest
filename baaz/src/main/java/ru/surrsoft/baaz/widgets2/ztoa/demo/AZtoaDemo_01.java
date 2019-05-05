package ru.surrsoft.baaz.widgets2.ztoa.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import ru.surrsoft.baaz.logs.Vdin_01;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;
import ru.surrsoft.baaz.widgets2.nviews.NLinearLayout;
import ru.surrsoft.baaz.widgets2.ztoa.WgZtoa_01;

/**
 * Демонстрация класса {@link WgZtoa_01}
 */
public class AZtoaDemo_01 extends AppCompatActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    //---
    NLinearLayout layMain = new BuLayLinear_01(this)
      .asContentView(this)
      .paddings(5)
      .build();

    //---
    new WgZtoa_01(this)
      .buAddTo(layMain)
      .buText("sample text")
      .buInputType(WgZtoa_01.EInputType.TEXT)
      .buPresenter(mtGetPresenter())
      .build();

    //---

  }

  //---

  /**
   * Для демонстрации обратных вызовов
   *
   * @return --
   */
  @NonNull
  private WgZtoa_01.Presenter mtGetPresenter() {
    return new WgZtoa_01.Presenter() {
      @Override
      public void onTextLive(String text) {
        super.onTextLive(text);
        String st = "onTextLive(text=[" + text + "])";
        Vdin_01.logM(st, this);
        Toast.makeText(AZtoaDemo_01.this, st, Toast.LENGTH_LONG).show();
      }

      @Override
      public void onFocusChanged(boolean focused, String textET) {
        super.onFocusChanged(focused, textET);
        String st = "onFocusChanged(focused=[" + focused + "], textET=[" + textET + "])";
        Vdin_01.logM(st, this);
        Toast.makeText(AZtoaDemo_01.this, st, Toast.LENGTH_LONG).show();
      }
    };
  }

  //---

}

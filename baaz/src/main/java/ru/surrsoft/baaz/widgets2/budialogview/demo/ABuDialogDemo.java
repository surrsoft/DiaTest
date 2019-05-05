package ru.surrsoft.baaz.widgets2.budialogview.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.ViewGroup;
import android.widget.Toast;

import ru.surrsoft.baaz.logs.Vdin_01;
import ru.surrsoft.baaz.univers.EStrings;
import ru.surrsoft.baaz.univers.ETextCase;
import ru.surrsoft.baaz.widgets2.BuButton_01;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;
import ru.surrsoft.baaz.widgets2.budialogview.BuDialogView;
import ru.surrsoft.baaz.widgets2.nviews.NLinearLayout;
import ru.surrsoft.baaz.widgets2.uasa.WgUasa_01;

/**
 * Активити для демонстрации работы класса {@link BuDialogView}
 */
public class ABuDialogDemo extends AppCompatActivity {
  private AlertDialog dialog;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //---
    NLinearLayout layMain = new BuLayLinear_01(this)
      .paddings(5)
      .asContentView(this)
      .build();

    //---
    new BuButton_01(this)
      .buAddTo(layMain)
      .buText("dialog_A (простой диалог)")
      .buListener(v -> {
        mtDialog_A();
      })
      .build();

    //---
    new BuButton_01(this)
      .buAddTo(layMain)
      .buText("dialog_B (с EditText)")
      .buListener(v -> {
        mtDialog_B();
      })
      .build();

  }

  private void mtDialog_A() {
    dialog = new BuDialogView(this)
      .buTitle(BuDialogView.getDefaultTitle())
      .buBody(BuDialogView.getDefaultBody())
      .buAddButton(BuDialogView
        .getDefaultButton()
        .text(EStrings._CANCEL.val())
        .onclick(v -> {
          dialog.cancel();
        })
      )
      .build();
    //---
    dialog.show();
  }

  private void mtDialog_B() {
    dialog = new BuDialogView(this)
      .buTitle(BuDialogView.getDefaultTitle())
      .buBodyLay(mtBody())
      //добавление кнопки ОК
      .buAddButton(BuDialogView.getDefaultButton()
        .text(EStrings._OK.val(ETextCase.UC))
        .onclick(v -> {
          String st = "нажата кнопка OK";
          Vdin_01.logM(st, this);
          Toast.makeText(this, st, Toast.LENGTH_LONG).show();
        })
      )
      //добавление кнопки "Отмена_01"
      .buAddButton(BuDialogView.getDefaultButton()
        .text(EStrings._CANCEL.val() + "_01")
        .onclick(v -> {
          dialog.cancel();
        })
      )
      //альтернативный способ добавления кнопки "Отмена"
      .buAddButtonCancelDefault(true)
      .build();
    //---
    dialog.show();
  }

  private ViewGroup mtBody() {
    NLinearLayout ret = new BuLayLinear_01(this)
      .build();
    //---
    new WgUasa_01(this)
      .buAddTo(ret)
      .buText("sample text")
      .buPresenter(new WgUasa_01.Presenter() {
        @Override
        public void onTextChange(Editable st) {
          String st1 = "onTextChange(st:Editable=[" + st + "])";
          Vdin_01.logM(st1, this);
          Toast.makeText(ABuDialogDemo.this, st1, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onFocusChange(boolean focus) {
          String st1 = "onFocusChange(focus=[" + focus + "])";
          Vdin_01.logM(st1, this);
          Toast.makeText(ABuDialogDemo.this, st1, Toast.LENGTH_LONG).show();
        }
      })
      .build();
    //---
    return ret;
  }

}

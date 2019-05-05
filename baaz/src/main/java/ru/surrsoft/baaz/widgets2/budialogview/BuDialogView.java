package ru.surrsoft.baaz.widgets2.budialogview;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.view.ViewGroup;

import java.util.ArrayList;

import ru.surrsoft.baaz.Bysa_01;
import ru.surrsoft.baaz.R;
import ru.surrsoft.baaz.suite.figures.N1208_RectRound;
import ru.surrsoft.baaz.univers.EGravity;
import ru.surrsoft.baaz.univers.Roih;
import ru.surrsoft.baaz.univers.U;
import ru.surrsoft.baaz.widgets2.BuilderTV;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;
import ru.surrsoft.baaz.widgets2.budialogview.demo.ABuDialogDemo;
import ru.surrsoft.baaz.widgets2.nviews.NLinearLayout;
import ru.surrsoft.baaz.widgets2.utils.BuCSL;

/**
 * Билдер диалога у которого _заголовком, _телом и _кнопками могут быть любые виджеты.
 * <p>
 * Кнопки будут располагаться друг над другом в порядке их добавления. В "onclick" нужно самостоятельно
 * останавливать диалог через dialog.cancel()
 * <p>
 * ОПЦИИ:
 * -- для получения преднастроенного _заголовка, _тела и _кнопок можно использовать методы
 * {@link #getDefaultTitle()}, {@link #getDefaultBody()}, {@link #getDefaultButton()}
 * -- если воспользоваться методом {@link #buAddButtonCancelDefault(boolean)} со значением TRUE, то
 * будет добавлена дефолтная кнопка "Отмена" по нажатию на которую текущий диалог будет закрываться
 * <p>
 * Есть демо - {@link ABuDialogDemo}
 * [zipc]
 */
public class BuDialogView {

  public static final int DEFAULT_BUTTON_COLOR = Color.parseColor("#f44336");

  private final Context mContext;
  private ArrayList<BuilderTV> mButtons = new ArrayList<>();
  private ViewGroup mLayParent;
  private NLinearLayout mLay;
  private BuilderTV mTitle;
  private BuilderTV mBodyTv;
  private ViewGroup mBodyLay;
  private boolean bDefaultCancelButton;

  //---===========================================================================================

  //constructors
  //``````````````````````````````````````````````````````````````````````````````````````````````
  public BuDialogView(Context c) {
    mContext = c;
  }

  //builders
  //``````````````````````````````````````````````````````````````````````````````````````````````

  public BuDialogView buTitle(BuilderTV btv) {
    mTitle = btv;
    return this;
  }

  public BuDialogView buBody(BuilderTV btv) {
    mBodyTv = btv;
    U.se(mBodyLay != null, "нельзя использовать вместе с buBodyLay(...)");
    return this;
  }

  public BuDialogView buBodyLay(ViewGroup vg) {
    U.se(vg == null, "");
    U.se(mBodyTv != null, "нельзя использовать вместе с buBody(...)");
    //---
    this.mBodyLay = vg;
    return this;
  }

  public BuDialogView buAddButton(BuilderTV btv) {
    mButtons.add(btv);
    return this;
  }

  /**
   * @param b (1) -- если TRUE то будет добавлена дефолтная кнопка "Отмена" по нажатию на которую
   *          будет выполнено закрытие диалога
   * @return --
   */
  public BuDialogView buAddButtonCancelDefault(boolean b) {
    this.bDefaultCancelButton = b;
    return this;
  }

  /**
   * @param vg
   * @return
   */
  public BuDialogView buAddTo(ViewGroup vg) {
    mLayParent = vg;
    return this;
  }

  //create
  //``````````````````````````````````````````````````````````````````````````````````````````````
  public AlertDialog build() {
    AlertDialog retAlertDialog = new BuLayLinear_01(mContext)
      .paddings(16)
      .createDialog(lay -> mLay = lay);

    //--- title
    if (mTitle != null) {
      mTitle
        .addTo(mLay)
        .create();
    }

    //--- body
    if (mBodyTv != null) {
      mBodyTv
        .addTo(mLay)
        .create();
    }

    //---
    if (mBodyLay != null) {
      mLay.addView(mBodyLay);
    }

    //--- space, вставка пространства перед кнопками
    if (mTitle != null || mBodyTv != null) {
      new BuLayLinear_01(mContext)
        .addTo(mLay)
        .h(7)
        .build();
    }

    //--- этот код должен располагаться перед кодом {{2212}}
    if (bDefaultCancelButton) {
      mButtons.add(
        getDefaultButton()
          .text(Bysa_01.res.getString(R.string.baaz_dialog_btn_cancel))
          .onclick(v -> retAlertDialog.cancel())
      );
    }

    //--- buttons (код {2212})
    if (mButtons.size() > 0) {
      for (BuilderTV eBtn : mButtons) { //LOOP --
        eBtn
          .addTo(mLay)
          .create();
      } //LOOP --
    }

    //---
    if (mLayParent != null) {
      mLayParent.addView(mLay);
    }

    //---
    return retAlertDialog;
  }

  //commands
  //``````````````````````````````````````````````````````````````````````````````````````````````
  public static BuilderTV getDefaultTitle() {
    return new BuilderTV(Bysa_01.appContext)
      .textColor(Color.BLACK)
      .textSize(18)
      .text("title")
      .textFont(Roih.get(Roih.TF_CYR_ROBOTO_REGULAR));
  }

  public static BuilderTV getDefaultBody() {
    return new BuilderTV(Bysa_01.appContext)
      .text("body")
      .margins(0, 7, 0, 0)
      .textColor(Color.BLACK)
      .textSize(14)
      .textFont(Roih.get(Roih.TF_CYR_ROBOTO_LIGHT));
  }

  public static BuilderTV getDefaultButton() {
    return new BuilderTV(Bysa_01.appContext)
      .drawer(new N1208_RectRound()
        .roundsPercent(100)
        .margins(1)
        .colorStroke(new BuCSL()
          .pressed(U.c2(Color.BLUE))
          .normal(U.c2(DEFAULT_BUTTON_COLOR))
          .create()
        )
      )
      .paddings(9)
      .margins(0, 7, 0, 0)
      .gravitySelf(EGravity.RI.val)
      .gravityIn(EGravity.RI.val)
      .text("button")
      .textColor(DEFAULT_BUTTON_COLOR)
      .textColorPressed(Color.BLUE)
      .fontSize(14);
  }

}

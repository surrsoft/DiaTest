package ru.surrsoft.baaz.widgets2.amgr;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;

import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.logs.Vdin_01;
import ru.surrsoft.baaz.new_02.p02_mmvd.MmvdValue;
import ru.surrsoft.baaz.suite.figures.N1208_AbsDrawer;
import ru.surrsoft.baaz.suite.figures.N1208_RectRound;
import ru.surrsoft.baaz.univers.Color2;
import ru.surrsoft.baaz.univers.EC;
import ru.surrsoft.baaz.univers.EStrings;
import ru.surrsoft.baaz.univers.ETextCase;
import ru.surrsoft.baaz.univers.U;
import ru.surrsoft.baaz.widgets2.BuilderTV;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;
import ru.surrsoft.baaz.widgets2.mcmz.WgMcmz;
import ru.surrsoft.baaz.widgets2.nviews.NLinearLayout;
import ru.surrsoft.baaz.widgets2.utils.BuCSL;

/**
 * [[amgr]] - виджет на базе [mcmz]-виджета. Предназначен для ввода пользователем значений.
 * Изначально может выглядеть как кнопка, при нажатии показывает EditText для ввода
 * <p>
 * ПОНЯТИЯ:
 * -- _базовый-слой - ([[bhbd]]::) это контейнер для всего содержимого текущего виджета. Клиент, с помощью метода
 * {@link #buLayBasic(BuLayLinear_01)}, подложить
 * свою реализацию этого слоя (со своими padding, margins и пр.) если его не устраивает дефолтная. К
 * подкладываемому слою есть одно
 * требование, он должен быть в вертикальной ориентации; если это будет не так, будет брошено
 * исключение
 * -- _заголовок-вдж, _titlewg - виджет заголовка
 * -- _значение-вдж, _valuewg - виджет значения
 * -- _состояние, _state - текущее состояние текущего виджета. См. {@link EState}
 * -- _uasa - виджет выполняющий роль EditText (см. {@link WgMcmz} )
 * -- _cancelbutton - кнопка "Отмена" ("Cancel")
 * СОСТОЯНИЯ:
 * -- "на старте":
 * -- -- 'значение пустое' - отображается текст _заголовка в обралении
 * -- -- 'значение НЕ пустое'
 * -- -- -- 'mode == BOTTOM' - отображается _заголовок в обралмении и под ним _значение
 * -- -- -- 'mode == RIGHT' - отображается _заголовок, справа от него _значение и всё это вместе
 * обрамляется
 * -- "редактирование" - отображается _заголовок в обрамлении и под ним поле ввода
 * <p>
 * <p>
 * КОДЫ: [ksao]
 * <p>
 * //new//new//
 */
public class WgAmgr {
  private final Context ctx;
  private String stTitle = "- - -";
  private MmvdValue value;
  private EValuePosition eValuePosition = EValuePosition.BOTTOM;
  private ViewGroup buLayParent;
  //--- cfg
  private static final int cfgFontSize1_sp = 14;
  private static final int cfgFontSize2_sp = 10;

  private static final Color2 cfgBorderColorNormal = U.c2(Color.RED);
  private static final EC cfgBorderColorPressed = EC.MD_GREEN_700;
  private static final EC cfgBorderColorDisable = EC.MD_BLUE_GREY_400;

  private static final int cfgColorCancelButtonText = EC.MD_DEEP_ORANGE_300.val;

  private static final Color2 cfgColor1 = U.c2(Color.RED);
  private static final Color2 cfgColor2 = U.c2("#FF8B0000");

  //---
  private static final String textDivider = ":";
  private NLinearLayout layBasic;
  private NLinearLayout layCancelButtonContainer;
  private WgMcmz uasa;
  private BuLayLinear_01 buLayBasicBu;

  //--- constructors
  public WgAmgr(Context ctx) {
    this.ctx = ctx;
  }

  //---

  /**
   * Константы отражающие состояния текущего виджета
   */
  public enum EState {
    /**
     * Стартовое состояние при пустом _значении
     */
    EMPTY,
    /**
     * Стартовое состояние, когда _значение не пустое и должно располагаться справа
     */
    NOEMPTY_VALUE_BOTTOM,
    /**
     * Стартовое состояние, когда _значение не пустое и должно располагаться справа
     */
    NOEMPTY_VALUE_RIGHT,
    /**
     *
     */
    EDIT
  }

  /**
   * Констната определяющая как должно располагаться _значение-вдж относительно
   * _заголовка-вдж
   */
  public enum EValuePosition {
    /**
     * В этом режиме _значение-вдж отображается под _заголовком-вдж
     */
    BOTTOM,
    /**
     * В этом режиме _значение-вдж отображается справа от _заголовка-вдж
     */
    RIGHT
  }

  //region builders

  public WgAmgr buTitle(String stTitle) {
    if (stTitle != null) {
      this.stTitle = stTitle;
    }
    return this;
  }

  public WgAmgr buValue(MmvdValue value) {
    this.value = value;
    return this;
  }

  public WgAmgr buValuePosition(EValuePosition EValuePosition) {
    this.eValuePosition = EValuePosition;
    return this;
  }

  public WgAmgr buAddTo(ViewGroup layParent) {
    this.buLayParent = layParent;
    return this;
  }

  //endregion builders


  public NLinearLayout build() {
    U.se(value == null, "");
    //---
    layBasic = mtWgLayBasic_x1();

    //---
    mtWgCreateByState_x6(layBasic, mtOnStartStateResolve_x3());

    //---
    if (buLayParent != null) {
      buLayParent.addView(layBasic);
    }
    //---
    return layBasic;
  }

  public WgAmgr build_B() {
    build();
    return this;
  }

  /**
   * Создаёт текущий виджет соответствующий _состоянию (2), и добавляет его в (1)
   *
   * @param layMain (1) --
   * @param state   (2) --
   */
  private void mtWgCreateByState_x6(ViewGroup layMain, EState state) {
    switch (state) {
      case EMPTY:
        mtWgTitle_x4(layMain, state);
        break;
      case NOEMPTY_VALUE_BOTTOM:
        mtWgNoEmptyBottom_x1(layMain, state);
        break;
      case NOEMPTY_VALUE_RIGHT:
        mtWgNoEmptyRight(layMain, state);
        break;
      case EDIT:
        mtWgEdit_x1(layMain, state);
        break;
    }
  }

  /**
   * см. [bhbd]::
   *
   * @param layBasicBu (1) -- _базовый-слой
   * @return --
   */
  public WgAmgr buLayBasic(BuLayLinear_01 layBasicBu) {
    U.se(layBasicBu == null, "");
    U.se(!layBasicBu.isOrientV(), "разметка должна иметь вертикальную ориентацию");
    //---
    this.buLayBasicBu = layBasicBu;
    return this;
  }

  /**
   * Виджет для _состояния {@link EState#EDIT}
   *
   * @param layMain (1) -- сюда виджет бставляется после создания
   * @param state   (2) -- нужно для _заголовка-вдж
   */
  private void mtWgEdit_x1(ViewGroup layMain, EState state) {
    //---
    layMain.getLayoutParams().width = U.MP;
    //---
    mtWgTitle_x4(layMain, state);
    //---
    uasa = new WgMcmz(ctx)
      .buAddTo(layMain)
      .buValue(value)
      .buActiveOnStart(true)
      .buPresenter(new WgMcmz.Presenter() {
        @Override
        public void onFocusChange(boolean focus, WgMcmz.EErrState eErrState) {
          //event//
          Vdin_01.logM("onFocusChange(focus=[" + focus + "], eErrState=[" + eErrState + "])", WgAmgr.this);
          if (!focus) {
            //^ если потеря фокуса
            mtValueResolve_x2(eErrState);
          }
        }

        @Override
        public void onKeyBackPress() {
          Vdin_01.logM("нажата 'аппаратная кнопка Назад'", WgAmgr.this);

        }
      })
      .build_B();

    //--- _cancelbutton
    //-- контейнер для кнопки
    layCancelButtonContainer = new BuLayLinear_01(ctx)
      .addTo(layMain)
      .build();
    //--
    mtWgCancelButtonCreate_x2();

  }

  /**
   * Если состояние (1)  _uasa это {@link WgMcmz.EErrState#ERR}, то включается отображение кнопки
   * _cancelbutton. Иначе происходит переход в состояние соответствующее состоянию после
   * редактирования
   *
   * @param uasaErrState (1) -- текущее состояние _uasa
   */
  private void mtValueResolve_x2(WgMcmz.EErrState uasaErrState) {
    if (uasaErrState == WgMcmz.EErrState.ERR) {
      //^ если виджет в состоянии WgUasa_02:_errstate
      mtWgCancelButtonCreate_x2();
    } else {
      //переходим в соответствующее состояние
      mtGoToState_x5(mtOnStartStateResolve_x3());
    }
  }

  //region wg-_cancelbutton

  /**
   * Удаление кнопки _cancelbutton
   */
  private void mtCancelButtonRemove_x1() {
    if (layCancelButtonContainer != null) {
      layCancelButtonContainer.removeAllViews();
    }
  }

  /**
   * Создаёт кнопку Cancel и добавляет её в разметку {@link #layCancelButtonContainer} перед этим очищая
   * её содержимое
   */
  private void mtWgCancelButtonCreate_x2() {
    mtCancelButtonRemove_x1();
    //---
    new BuilderTV(ctx)
      .addTo(layCancelButtonContainer)
      .text(EStrings._CANCEL.val(ETextCase.UC))
      .paddings(9, 5, 9, 5)
      .fontSize(cfgFontSize1_sp)
      .textColor(cfgColorCancelButtonText)
      .drawer(mtCfgBoundDrawerCreate_B())
      .gravitySelf(Gravity.END)
      .margins(0, 10, 10, 0)
      .onclick(v -> {
        //event//
        Vdin_01.logM("нажатие, 'Cancel button' //190412-085500", this);
        //---
        uasa.deactivate(true, true);
        //---
        mtGoToState_x5(mtOnStartStateResolve_x3());
      })
      .create();
  }

  //endregion wg-_cancelbutton

  private void mtWgNoEmptyRight(ViewGroup layMain, EState state) {
    NLinearLayout lay = new BuLayLinear_01(ctx)
      .addTo(layMain)
      .horizontal()
      .wWC()
      .drawer(mtCfgBoundDrawerCreate())
      .buOnclick(v -> {
        //event//
        Vdin_01.logM("нажатие, NLinearLayout //190412-083200", this);
        mtGoToState_x5(EState.EDIT);
      })
      .build();
    //---
    mtWgTitle_x4(lay, state);
    //---
    mtWgValue_x2(lay, state);
  }

  private void mtWgValue_x2(ViewGroup layMain, EState state) {
    //---
    new BuilderTV(ctx)
      .addTo(layMain)
      .text(value.textGet())
      .textColor(Color.BLACK)
      .fontSize(cfgFontSize1_sp)
      .paddings(
        state == EState.NOEMPTY_VALUE_RIGHT ? 0 : 12,
        4,
        8,
        0
      )
      .onclick(v -> mtGoToState_x5(EState.EDIT))
      .create();
  }

  private void mtWgNoEmptyBottom_x1(ViewGroup layMain, EState state) {
    mtWgTitle_x4(layMain, state);
    mtWgValue_x2(layMain, state);
  }

  /**
   * Создание _базового-слоя
   *
   * @return --
   */
  private NLinearLayout mtWgLayBasic_x1() {
    if (buLayBasicBu != null) {
      return buLayBasicBu.build();
    }
    //---
    return new BuLayLinear_01(ctx)
      .wWC()
      .paddings(0, 16, 0, 0)
      .build();
  }

  /**
   *
   */
  private void mtWgTitle_x4(ViewGroup layMain, EState state) {
    //---
    new BuilderTV(ctx)
      .addTo(layMain)
      .text(stTitle + (state == EState.NOEMPTY_VALUE_RIGHT ? textDivider : ""))
      .textColor(cfgColor2.val)
      .paddings(
        9,
        5,
        state == EState.NOEMPTY_VALUE_RIGHT ? 6 : 9,
        5
      )
      .fontSize(cfgFontSize1_sp)
      .drawer(state == EState.NOEMPTY_VALUE_RIGHT ? null : mtCfgBoundDrawerCreate())
      .onclick(v -> {
        Vdin_01.logM("нажатие, title; state [" + state + "] //190412-083600", this);
        if (state != EState.EDIT) {
          //^ если не в состоянии редактирования
          mtGoToState_x5(EState.EDIT);
        } else {
          //--- если не деактивитировать будет исключение
          uasa.deactivate(true, false);
          //---
          mtValueResolve_x2(uasa.getErrState());
        }
      })
      .create();
  }

  private void mtGoToState_x5(EState newState) {
    Vdin_01.logM("mtGoToState(newState=[" + newState + "]) //190413-185000", WgAmgr.this);
    //---
    layBasic.removeAllViews();
    //---
    mtWgCreateByState_x6(layBasic, newState);
  }

  //region mt-appearance

  /**
   * Drawer рамки
   *
   * @return --
   */
  private N1208_AbsDrawer mtCfgBoundDrawerCreate() {
    return new N1208_RectRound()
      .roundsPercent(100)
      .margins(1)
      .colorFill(new BuCSL()
        .disable(U.c2(Color.YELLOW))
        .pressed(U.c2(Color.YELLOW))
        .normal(U.c2(Color.YELLOW))
        .create()
      )
      .colorStroke(new BuCSL()
        .disable(cfgBorderColorDisable)
        .pressed(cfgBorderColorPressed)
        .normal(cfgBorderColorNormal)
        .create()
      );
  }

  /**
   * Drawer рамки
   *
   * @return --
   */
  private N1208_AbsDrawer mtCfgBoundDrawerCreate_B() {
    return new N1208_RectRound()
      .roundsPercent(100)
      .margins(1)
      .colorStroke(new BuCSL()
        .disable(U.c2(Color.GRAY))
        .pressed(U.c2(Color.RED))
        .normal(U.c2(cfgColorCancelButtonText))
        .create()
      );
  }

  //endregion appearance

  /**
   * Определение _состояния текущего виджет на старте его показа
   *
   * @return --
   */
  private EState mtOnStartStateResolve_x3() {
    if (value.isEmpty(true)) {
      return EState.EMPTY;
    }
    //---
    switch (eValuePosition) {
      case BOTTOM:
        return EState.NOEMPTY_VALUE_BOTTOM;
      case RIGHT:
        return EState.NOEMPTY_VALUE_RIGHT;
      default:
        throw new SomeException("(debug)");
    }
  }

  /**
   * Пишет в (1) все виджеты во всех _состояниях
   *
   * @param layParent (1) --
   */
  public void mtDebugAllWidgets(ViewGroup layParent) {
    NLinearLayout lay = new BuLayLinear_01(ctx)
      .addTo(layParent)
      .build();
    //---
    mtDebugComment(lay, EState.EMPTY.name(), 20);
    mtWgCreateByState_x6(lay, EState.EMPTY);

    mtDebugComment(lay, EState.NOEMPTY_VALUE_RIGHT.name(), 10);
    mtWgCreateByState_x6(lay, EState.NOEMPTY_VALUE_RIGHT);

    mtDebugComment(lay, EState.NOEMPTY_VALUE_BOTTOM.name(), 10);
    mtWgCreateByState_x6(lay, EState.NOEMPTY_VALUE_BOTTOM);

    mtDebugComment(lay, EState.EDIT.name(), 10);
    mtWgCreateByState_x6(lay, EState.EDIT);
  }

  private void mtDebugComment(ViewGroup layParent, String comment, int paddingTop) {
    new BuilderTV(ctx)
      .addTo(layParent)
      .text(comment)
      .paddingT(paddingTop)
      .create();
  }
}

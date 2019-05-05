package ru.surrsoft.baaz.widgets2.pkor_b.b;

import android.view.View;

import ru.surrsoft.baaz.univers.UniPresenter;
import ru.surrsoft.baaz.widgets2.BuDialogConfirm;

/**
 * [[zucb]]
 * <p>
 * Отражает элемент/пункт меню
 * <p>
 * //new//
 */
public class MenuElem {
  public final String name;
  public final UniPresenter<View> presenter;
  public final BuDialogConfirm buDialogConfirm;

  //--- constructors

  /**
   * @param name            (1) -- отображаемое имя пункта меню
   * @param presenter       (2) -- элемент вызываемый при выборе пункта меню
   * @param buDialogConfirm (3) -- опциональный диалог позволяющий выбрать продолжать или нет (т.е.
   *                        вызывать ли дальше (2) или нет)
   */
  public MenuElem(String name, UniPresenter<View> presenter, BuDialogConfirm buDialogConfirm) {
    this.name = name;
    this.presenter = presenter;
    this.buDialogConfirm = buDialogConfirm;
  }

  //---

}

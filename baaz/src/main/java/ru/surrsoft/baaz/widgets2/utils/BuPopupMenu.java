package ru.surrsoft.baaz.widgets2.utils;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import ru.surrsoft.baaz.univers.UniPresenter;
import ru.surrsoft.baaz.widgets2.BuDialogConfirm;
import ru.surrsoft.baaz.widgets2.pkor_b.b.MenuElem;

/**
 * Инкапсулирует popup menu
 * <p>
 * ИСПОЛЬЗОВАНИЕ:
 * -- наполнить пунктами с помощью {@link #addElem(String, UniPresenter, BuDialogConfirm)}. Здесь:
 * -- -- UniPresenter - см. _презентер
 * -- -- BuDialogConfirm - если указан, то перед тем как будет вызван презентер, будет вызван этот
 * диалог подтверждения. И если в нём будет нажато "Отмена" то презентер вызван не будет
 * -- вызвать {@link #show(View)} передав _виджет-опорный
 * <p>
 * ПОНЯТИЯ:
 * -- _презентер - вызывается при нажатии на элемент меню. В _презентер, как параметр, передаётся
 * _виджет-опорный
 * -- _виджет-опорный, _anchorView - виджет на котором мы хотим отобразить popup menu. Этот виджет
 * передаётся в
 * метод {@link #show(View)}, и он же попадает в _презентер как параметр при нажатии на любой из
 * пунктов popup menu
 */
public class BuPopupMenu {

  private final Context mContext;
  private ArrayList<String> names = new ArrayList<>();
  private ArrayList<UniPresenter<View>> unipresenters = new ArrayList<>();
  private ArrayList<BuDialogConfirm> confirmDialogs = new ArrayList<>();

  //--- constructors

  public BuPopupMenu(Context ctx) {
    mContext = ctx;
  }

  //---

  /**
   * @param name             (1) -- текст пункта меню
   * @param presenter        (2) --
   * @param buDialogConfirms (3) --
   * @return --
   */
  public BuPopupMenu addElem(
    String name,
    UniPresenter<View> presenter,
    BuDialogConfirm buDialogConfirms) {
    //---
    mtSet(name, presenter, buDialogConfirms);
    //---
    return this;
  }

  /**
   * @param menuElem (1) --
   * @return --
   */
  public BuPopupMenu addElem(MenuElem menuElem) {
    mtSet(menuElem.name, menuElem.presenter, menuElem.buDialogConfirm);
    return this;
  }

  /**
   * @param name             (1) --
   * @param presenter        (2) --
   * @param buDialogConfirms (3) --
   */
  private void mtSet(String name, UniPresenter<View> presenter, BuDialogConfirm buDialogConfirms) {
    names.add(name);
    unipresenters.add(presenter);
    confirmDialogs.add(buDialogConfirms);
  }


  /**
   * @param anchorView (1) -- указанный здесь виджет будет передан как параметр в презентер при
   *                   нажатии на любой из пунктов меню (грубо говоря, это виджет на котором был вызван popup menu)
   */
  public void show(final View anchorView) {
    //---
    PopupMenu popupMenu = new PopupMenu(mContext, anchorView);
    //---
    Menu menu = popupMenu.getMenu();
    for (int i = 0; i < names.size(); i++) {
      String stName = names.get(i);
      menu.add(0, i, i, stName);
    }
    //---
    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem item) {
        final int iItemId = item.getItemId();
        //---
        BuDialogConfirm buDialogConfirm = confirmDialogs.get(iItemId);
        //если был указан диалог подтверждения
        if (buDialogConfirm != null) {
          buDialogConfirm
            .presenter(() -> mt42(iItemId))
            .show();
        } else {
          mt42(iItemId);
        }
        return true;
      }

      private void mt42(int itemId) {
        UniPresenter<View> presenter = unipresenters.get(itemId);
        presenter.fun(anchorView);
      }

    });
    popupMenu.show();
  }

}

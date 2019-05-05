package ru.surrsoft.baaz.suite.figures;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.Nullable;

import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.suite.figures.N1208_AbsDrawer;
import ru.surrsoft.baaz.suite.figures.N1208_DotsVertical;
import ru.surrsoft.baaz.univers.U;
import ru.surrsoft.baaz.widgets2.utils.BuCSL;

/**
 * Банк drawer-отрисовок.
 * <p>
 * ИСПОЛЬЗОВАНИЕ:
 * -- вызвать метод {@link #create(E)} передав в него константу желаемого drawer
 * <p>
 * [sbso]-вид: _класс-библиотека ([pvpe])
 * <p>
 * //new//
 */
public class DrawerBank_01 {

  public enum E {
    //drawer "три вертикальные точки" (используется как правило для виджета "кнопка меню")
    VERTICAL_DOTS_A
  }

  /**
   * @return --
   */
  @Nullable
  public static N1208_AbsDrawer create(E edrawer) {
    U.se(edrawer == null, "");
    //---
    switch (edrawer) {
      case VERTICAL_DOTS_A:
        return mt001();
      default:
        throw new SomeException("(debug) неподдерживаемая константа [" + edrawer + "]");
    }
  }

  //---

  private static N1208_AbsDrawer mt001() {
    return new N1208_DotsVertical()
      .colorStroke(
        new BuCSL()
          .pressed(U.c2(Color.WHITE))
          .normal(U.c2(Color.BLACK))
          .create()
      )
      .colorFill(ColorStateList.valueOf(Color.BLACK));
  }

}

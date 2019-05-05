package ru.surrsoft.baaz.tclasses;

import ru.surrsoft.baaz.SomeException;

/**
 * Используется чтобы задавать окграничния на возможные значения int.
 * Если значение выходит за заданные рамки то бросается исключение
 * <p>
 * ВЕРСИЯ 1 1.1 2019-02-04 (stored)
 * ВЕРСИЯ 1 1.0 2018-11-18 (stored)
 */
public class TxInt_01 {
  //fields
  //```````````````````````````````````````````````````````````````````````````````````````````````
  private int miVal;

  //
  //```````````````````````````````````````````````````````````````````````````````````````````````
  public enum E {
    /**
     * Допускается int 0 или больше
     */
    NIL_PLUS,
    /**
     * Допускается int 1 или больше
     */
    ONE_PLUS
  }

  //constructors
  //```````````````````````````````````````````````````````````````````````````````````````````````

  /**
   * @param iVal (1) --
   * @param e    (2) -- если NULL то проверки не выполняются
   */
  public TxInt_01(int iVal, E e) {
    miVal = iVal;
    //---
    if (e != null) {
      verify_ex(iVal, e);
    }
  }

  //
  //```````````````````````````````````````````````````````````````````````````````````````````````
  public int val() {
    return miVal;
  }

  public static void verify_ex(int iVal, E e) {
    switch (e) {
      case NIL_PLUS:
        if (iVal < 0) {
          throw new SomeException("(debug) [" + e + "] [" + iVal + "]");
        }
        break;
      case ONE_PLUS:
        if (iVal < 1) {
          throw new SomeException("(debug) [" + e + "] [" + iVal + "]");
        }
    }
  }

}

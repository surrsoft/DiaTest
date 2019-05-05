package ru.surrsoft.baaz.tclasses;

import java.util.ArrayList;

import ru.surrsoft.baaz.SomeException;


/*
 * Утилитные методы для работы с ArrayList
 *
 * ВЕРСИЯ 1 1.0 2019-01-19 (stored)
 */
public class TArrayList_01 {

  /**
   * Возвращает TRUE если список (1) != NULL и содержим 1+ элементов
   *
   * @param list (1) --
   * @return --
   */
  public static boolean isFill(ArrayList list) {
    if (list == null) {
      return false;
    }
    //---
    if (list.size() < 1) {
      return false;
    }
    //---
    return true;
  }

  /**
   * Возвращает TRUE если {список (1) != NULL} И {содержим 1+ элементов} И {среди элементов нет
   * NULL}
   *
   * @param list (1) --
   * @return --
   */
  public static boolean isFill_B(ArrayList list) {
    if (list == null) {
      return false;
    }
    //---
    if (list.size() < 1) {
      return false;
    }
    //---
    if (isNullExist(list)) {
      return false;
    }
    //---
    return true;
  }

  /**
   * Возвращает TRUE если среди элементов списка (1) есть элементы == NULL, иначе возвращает FALSE
   *
   * @param list (1) -- NULL недопустим
   * @return --
   */
  public static boolean isNullExist(ArrayList list) {
    if (list == null) {
      throw new SomeException("(debug)");
    }
    //---
    if (list.size() == 0) {
      return false;
    }
    //---
    for (Object oj : list) {
      if (oj == null) {
        return true;
      }
    }
    //---
    return false;
  }

}

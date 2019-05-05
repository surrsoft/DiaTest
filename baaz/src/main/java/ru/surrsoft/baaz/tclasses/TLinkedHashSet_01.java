package ru.surrsoft.baaz.tclasses;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Утилитные методы для работы с {@link LinkedHashSet}
 * <p>
 * ВЕРСИЯ 1 1.0 2019-02-05 (stored)
 */
public class TLinkedHashSet_01 {

  /**
   * Преобразование LinkedHashSet в ArrayList
   *
   * @param set (1) --
   * @return --
   */
  public static ArrayList asArrayList(LinkedHashSet set) {
    if (set != null) {
      ArrayList arr = new ArrayList(set);
      return arr;
    }
    return null;
  }
}

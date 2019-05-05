package ru.surrsoft.baaz.cls_a;

/**
 * Описание расположения чего либо (слева, справа, слева вверху, в центре и т.д.
 */
public enum EPlaces_01 {
  LEFT(0), TOP(1), RIGHT(2), BOTTOM(3), LT(4), TR(5), RB(6), BL(7), CENTER(8),;
  private final int _place;

  EPlaces_01(int place) {
    _place = place;
  }
}

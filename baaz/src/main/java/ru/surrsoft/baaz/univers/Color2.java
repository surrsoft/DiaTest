package ru.surrsoft.baaz.univers;

import android.graphics.Color;

import ru.surrsoft.baaz.suite.experiments.ecud.AnnEcud;
import ru.surrsoft.baaz.suite.experiments.ecud.EEcudTypes;

/**
 * Создан для того чтобы иметь возможность передавать NULL для цвета
 * <p/>
 * #version 1 24.05.2016  #id [[w246w]]
 */
public class Color2 extends Color {
  @AnnEcud(type = EEcudTypes.COLOR_INT)
  public int val;

  public Color2(int color) {
    this.val = color;
  }
}

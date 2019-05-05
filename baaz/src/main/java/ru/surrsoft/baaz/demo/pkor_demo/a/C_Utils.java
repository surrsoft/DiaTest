package ru.surrsoft.baaz.demo.pkor_demo.a;

import java.util.ArrayList;

import ru.surrsoft.baaz.widgets2.INanv;

/**
 * Утилитные методы
 */
public class C_Utils {
  /**
   * Генерируем список именно INanv, не B_INanv
   *
   * @return --
   */
  public static ArrayList<INanv> sampleNanvsGenerate() {
    ArrayList<INanv> ret = new ArrayList<>();
    //---
    ret.add(new B_INanv().buFirstName("Иван").buLastName("Утюгов"));
    ret.add(new B_INanv().buFirstName("Семён").buLastName("Иванов"));
    ret.add(new B_INanv().buFirstName("Макар").buLastName("Коровин"));
    //---
    return ret;
  }
}

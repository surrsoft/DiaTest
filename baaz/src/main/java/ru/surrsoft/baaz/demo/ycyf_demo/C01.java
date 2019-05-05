package ru.surrsoft.baaz.demo.ycyf_demo;

import ru.surrsoft.baaz.debug.Ycyf_01;

/**
 *
 */
public class C01 {
  public String cSt1 = "Валерия";
  public String cSt2 = "Aнжелика";

  public C01() {
  }

  @Override
  public String toString() {
    //toString-template '[snuz]'
    return Ycyf_01.RWRY_OPEN + "C01{" +
      "\n" + Ycyf_01.RWRY_INN + ":cSt1='" + cSt1 + '\'' +
      "\n" + Ycyf_01.RWRY_INN + ":cSt2='" + cSt2 + '\'' +
      "\n" + Ycyf_01.RWRY_CLOSE + "}";
  }
}

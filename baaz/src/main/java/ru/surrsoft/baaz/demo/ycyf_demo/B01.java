package ru.surrsoft.baaz.demo.ycyf_demo;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;

import ru.surrsoft.baaz.debug.Ycyf_01;

/**
 *
 */
public class B01 {

  private String bSt1;
  private String bSt2;
  private C01[] bArrC1 = new C01[]{};
  private ArrayList<C01> bListC1 = new ArrayList<>();

  public B01(String bSt1, String bSt2) {
    this.bSt1 = bSt1;
    this.bSt2 = bSt2;
    //---
    bArrC1 = ArrayUtils.add(bArrC1, new C01());
    bArrC1 = ArrayUtils.add(bArrC1, new C01());
    //---
    bListC1.add(new C01());
    bListC1.add(new C01());
    //---
  }

  @Override
  public String toString() {
    //toString-template '[snuz]'
    return Ycyf_01.RWRY_OPEN + "B01{" +
      "\n" + Ycyf_01.RWRY_INN + ":bSt1='" + bSt1 + '\'' +
      "\n" + Ycyf_01.RWRY_INN + ":bSt2='" + bSt2 + '\'' +
      "\n" + Ycyf_01.RWRY_INN + ":bArrC1=" + Ycyf_01.toStringArr(bArrC1) +
      "\n" + Ycyf_01.RWRY_INN + ":bListC1=" + Ycyf_01.toStringList(bListC1) +
      "\n" + Ycyf_01.RWRY_CLOSE + "}";
  }
}

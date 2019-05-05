package ru.surrsoft.baaz.demo.pkor_b_demo;

/**
 * Модель элемента
 */
public class PkorDemoElem {
  private String st1 = "st1";
  private String st2 = "st2";

  public PkorDemoElem() {
  }

  public PkorDemoElem buText1(String st1) {
      this.st1 = st1;
      return this;
  }

  public PkorDemoElem buText2(String st2) {
      this.st2 = st2;
      return this;
  }

  public String getSt1() {
    return st1;
  }

  public String getSt2() {
    return st2;
  }
}

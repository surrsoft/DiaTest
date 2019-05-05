package ru.surrsoft.baaz.new_02.p02_mmvd.b;

import ru.surrsoft.baaz.univers.U;

/**
 * Представляет результат _коррекции ([npbd])
 * <p>
 * //new//
 */
public class CorrectionResult {
  /**
   * Значение после _коррекции ([npbd])
   */
  private String text = "";
  /**
   * TRUE здесь означает, что значение которое в text, отличается как либо от того значения которое
   * подвергалось _коррекции ([npbd])
   */
  public boolean isCorrected;

  public CorrectionResult(String text, boolean isCorrected) {
    U.se(text == null, "");
    //---
    this.text = text;
    this.isCorrected = isCorrected;
  }

  public void setText(String text) {
    U.se(text == null, "");
    this.text = text;
  }

  public String getText() {
    return text;
  }
}

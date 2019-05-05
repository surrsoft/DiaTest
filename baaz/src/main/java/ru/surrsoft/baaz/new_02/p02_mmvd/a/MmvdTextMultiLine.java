package ru.surrsoft.baaz.new_02.p02_mmvd.a;

import ru.surrsoft.baaz.new_02.p02_mmvd.MmvdValue;
import ru.surrsoft.baaz.new_02.p02_mmvd.b.CorrectionResult;
import ru.surrsoft.baaz.new_02.p02_mmvd.b.ValidateResult;
import ru.surrsoft.baaz.tclasses.TString_01;
import ru.surrsoft.baaz.univers.U;

/**
 * Инкарнация многострочного текста.
 * Начальное _значение == ""
 * <p>
 * //new//
 */
public class MmvdTextMultiLine extends MmvdValue {

  public MmvdTextMultiLine() {
    textSetIf("");
  }

  public MmvdTextMultiLine(String pretext) {
    super(pretext);
  }

  @Override
  public CorrectionResult correct(String st) {
    String st1 = TString_01.normalizeTrim(st);
    return new CorrectionResult(st1, st1.length() != st.length());
  }

  @Override
  public ValidateResult validate(String pretext) {
    U.se(pretext == null, "");
    return new ValidateResult(true, "");
  }

}

package ru.surrsoft.baaz.new_02.p02_mmvd.a;

import org.apache.commons.lang3.StringUtils;

import ru.surrsoft.baaz.new_02.p02_mmvd.MmvdValue;
import ru.surrsoft.baaz.new_02.p02_mmvd.b.CorrectionResult;
import ru.surrsoft.baaz.new_02.p02_mmvd.b.ValidateResult;
import ru.surrsoft.baaz.tclasses.TString_01;
import ru.surrsoft.baaz.univers.EStrings;
import ru.surrsoft.baaz.univers.U;

/**
 * Инкарнация однострочного текста.
 * Начальное _значение == ""
 * <p>
 * //new//
 */
public class MmvdTextOneLine extends MmvdValue {

  public MmvdTextOneLine() {
    textSetIf("");
  }

  public MmvdTextOneLine(String pretext) {
    super(pretext);
  }

  @Override
  public CorrectionResult correct(String st) {
    String st1 = TString_01.normalize_B(st, "");
    return new CorrectionResult(st1, st1.length() != st.length());
  }

  @Override
  public ValidateResult validate(String pretext) {
    U.se(pretext == null, "");
    if (StringUtils.containsAny(pretext, ((char) 10), ((char) 12))) {
      return new ValidateResult(false, EStrings._TEXT_NEED_NO_LINE_BREAKS.val());
    }
    return new ValidateResult(true, "");
  }

}

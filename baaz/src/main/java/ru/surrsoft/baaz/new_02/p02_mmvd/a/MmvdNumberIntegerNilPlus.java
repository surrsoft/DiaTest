package ru.surrsoft.baaz.new_02.p02_mmvd.a;

import android.support.annotation.Nullable;

import ru.surrsoft.baaz.new_02.p02_mmvd.MmvdValue;
import ru.surrsoft.baaz.new_02.p02_mmvd.b.CorrectionResult;
import ru.surrsoft.baaz.new_02.p02_mmvd.b.ValidateResult;
import ru.surrsoft.baaz.tclasses.TString_01;
import ru.surrsoft.baaz.univers.EStrings;
import ru.surrsoft.baaz.univers.U;

/**
 * Инкарнация целого числа >= 0 и <= Integer.MAX_VALUE.
 * Начальное _значение == "0"
 * <p>
 * //new//
 */
public class MmvdNumberIntegerNilPlus extends MmvdValue {

  public MmvdNumberIntegerNilPlus() {
    textSetIf("0");
  }

  public MmvdNumberIntegerNilPlus(String pretext) {
    super(pretext);
  }

  @Override
  public CorrectionResult correct(@Nullable String st) {
    //--- причёсываем
    String st1 = TString_01.normalize_B(st, "");
    //--- если состоит только из 0..9
    if (TString_01.isNumeric(st1)) {
      //--- удаление лишних нулей в начале
      String st2 = TString_01.removeStart_B(st1, "0");
      if (st2.length() == 0) { //если удаление имело место
        st2 = "0";
      }
      //---
      return new CorrectionResult(st2, (st == null) || (st2.length() != st.length()));
    }
    //---
    return new CorrectionResult(st1, (st == null) || (st1.length() != st.length()));
  }

  @Override
  public ValidateResult validate(String pretext) {
    U.se(pretext == null, "");
    //---
    //если состоит не только из чисел 0..9
    if (!TString_01.isNumeric(pretext)) {
      return new ValidateResult(false, EStrings._INVALID_NUMBER.val());
    } else {
      //--- если начинается с нуля но это не значение "0"
      if (pretext.length() > 1 && TString_01.startWith(pretext, "0", true)) {
        return new ValidateResult(false, EStrings._INVALID_NUMBER.val());
      }
      //---
      //long бросит NumberFormatException если число будет слишком большое (и следовательно длинное)
      if (pretext.length() > 11) {
        return new ValidateResult(false, EStrings._NEED_NUMBER_LESS.val() + Integer.MAX_VALUE);
      }
      //---
      try {
        Long _long = Long.valueOf(pretext);
        if (_long < 0) {
          return new ValidateResult(false, EStrings._NEED_NUMBER_GREATER_OR_EQUAL_NIL.val());
        } else if (_long > Integer.MAX_VALUE) {
          return new ValidateResult(false, EStrings._NEED_NUMBER_LESS.val() + Integer.MAX_VALUE);
        }
      } catch (NumberFormatException e) {
        return new ValidateResult(false, EStrings._INVALID_NUMBER.val());
      }
      //---
      return new ValidateResult(true, "");
    }
  }

}

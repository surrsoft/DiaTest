package ru.surrsoft.baaz.new_02.p02_mmvd.b;

import ru.surrsoft.baaz.new_02.p02_mmvd.MmvdValue;
import ru.surrsoft.baaz.univers.U;

/**
 * Представляет результат работы метода {@link MmvdValue#validate(String)}.
 * Если _валидация прошла успешно, то этот объект представляет собой {isValid:TRUE, stComment:""},
 * если не успешна то {isValid:FALSE, stComment:"текст говорящий в чём проблема, для показа
 * пользователю"}
 * <p>
 * //new//
 */
public class ValidateResult {
  public boolean isValid;
  public String stComment = "";

  public ValidateResult(boolean isValid, String stComment) {
    U.se(stComment == null, "");
    //---
    this.isValid = isValid;
    this.stComment = stComment;
  }
}

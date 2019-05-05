package ru.surrsoft.baaz.widgets2.mcmz;

import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.new_02.p02_mmvd.a.MmvdNumberIntegerNilPlus;
import ru.surrsoft.baaz.new_02.p02_mmvd.a.MmvdTextMultiLine;
import ru.surrsoft.baaz.new_02.p02_mmvd.a.MmvdTextOneLine;
import ru.surrsoft.baaz.suite.lvl0.EEditTextInputTypes;

/**
 * //needdesc
 * <p>
 * //new//
 */
public class Utils {

  /**
   * Получение {@link EEditTextInputTypes} на базе (1)
   * <p>
   * #errors если текущий метод не поддерживает класс указанный в (1)
   *
   * @param mmvdValueCls (1) --
   * @return --
   */
  public static EEditTextInputTypes inputTypeGet(Class<?> mmvdValueCls) {
    //---
    if (mmvdValueCls == MmvdNumberIntegerNilPlus.class) {
      return EEditTextInputTypes.INTEGER_POSITIVE;
    } else if (mmvdValueCls == MmvdTextOneLine.class) {
      return EEditTextInputTypes.TEXT_ONELINE;
    } else if (mmvdValueCls == MmvdTextMultiLine.class) {
      return EEditTextInputTypes.TEXT_MULTILINE;
    }
    //---
    throw new SomeException("(debug) mmvdValueCls [" + mmvdValueCls + "]");
  }

}

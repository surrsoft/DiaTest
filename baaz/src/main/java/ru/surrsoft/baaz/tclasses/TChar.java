package ru.surrsoft.baaz.tclasses;

/**
 * Утилитные методы для работы с типом char и Character
 * <p>
 * ПОНЯТИЯ:
 * -- _codesymb - код символа выраженный через int, например 65 это 'A'
 * <p>
 * //new//
 */
public class TChar {

  /**
   * Максимальный _codesymb который вмещает в себя один char
   */
  public static final int MAX_CODE = 65535;

  /**
   * Получение char на базе _codesymb (1)
   *
   * @param iCodesymb (1) -- например 65
   * @return -- например 'A'
   */
  public static char create(int iCodesymb) {
    return (char) iCodesymb;
  }

}

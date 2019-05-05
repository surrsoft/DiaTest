package ru.surrsoft.baaz.tclasses;


import ru.surrsoft.baaz.SomeException;

/**
 * Методы по части NULL
 * <p>
 * ВЕРСИЯ 1 1.0 2019-01-12 (stored)
 */
public class TNull_01 {

  /**
   * Возвращает TRUE если хотябы один из элементов (1), или сам (1) равен NULL
   *
   * @param _ojs (1) -- сюда можно передавать что угодно, в том числе примтивные значения. Если
   *             ничего не передать то будет RuntimeException
   * @return --
   */
  public static boolean isNull(Object... _ojs) {
    if (_ojs == null) return true;
    if (_ojs.length < 1) throw new RuntimeException("(debug)");
    for (Object oj : _ojs) {
      if (oj == null) return true;
    }
    return false;
  }

  /**
   * Бросает исключение с текстом (2) если объект (1) == null
   *
   * @param oj   (1) --
   * @param info (2) -- можно null
   */
  public static void isNullEx(Object oj, String info) {
    if (oj == null) {
      throw new SomeException("(debug)" + (info == null ? "" : info));
    }
  }

  /**
   * Бросает исключение с текстом "(debug)" если (1) == NULL
   * <p>
   * см. так же {@link #isNullEx(Object, String)}
   *
   * @param oj (1) --
   */
  public static void isNullEx(Object oj) {
    isNullEx(oj, null);
  }
}

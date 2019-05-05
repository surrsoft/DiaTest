package ru.surrsoft.baaz.tclasses;


import ru.surrsoft.baaz.SomeException;

/**
 * ВЕРСИЯ 1 1.0 2019-01-19 (stored)
 */
public class TObject_01 {

  /**
   * Вернет TRUE если переменные (1) и (2) хранят ссылку на один и тот же объект
   *
   * @param oj1 (1) -
   * @param oj2 (2) -
   * @return -
   */
  public static boolean eq(Object oj1, Object oj2) {
    return oj1 == oj2;
  }

  /**
   * @param oj (1) --
   * @return -- TRUE если (1) == null
   */
  public static boolean isNull(Object oj) {
    return oj == null;
  }

  /**
   * Бросает исключение если (1) == null
   *
   * @param oj (1) --
   */
  public static void isNull_exception(Object oj) {
    if (oj == null) {
      throw new SomeException("(debug)");
    }
  }

  /**
   * @param oj (1) --
   * @return -- TRUE если (1) != null
   */
  public static boolean isNullNot(Object oj) {
    return oj != null;
  }

  /**
   * Возвращает _identity-hash-code ([uond]) объекта (1)
   *
   * @param oj (1) -- {v1: null}
   * @return -- {v1: 0}
   */
  public static int uondGet(Object oj) {
    return System.identityHashCode(oj);
  }

}

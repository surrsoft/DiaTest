package ru.surrsoft.baaz.univers;

/**
 * Простой класс для "транспорта" ссылок
 */
public class Trans<T> {
  public T val;

  public Trans(T val) {
    this.val = val;
  }

  public static boolean noNull(Trans t) {
    return t != null && t.val != null;
  }

  @Override
  public String toString() {
    return val.toString();
  }
}

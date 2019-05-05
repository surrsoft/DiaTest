package ru.surrsoft.baaz.p01_db.b;

import org.apache.commons.lang3.StringUtils;

import ru.surrsoft.baaz.TypeConsts;
import ru.surrsoft.baaz.debug.Ycyf_01;
import ru.surrsoft.baaz.univers.U;

/**
 * Инкарнация имени поля таблицы БД
 */
public class FieldName {

  private String name;

  public FieldName(String name) {
    U.se(!isCorrect(name), "некорректное имя для поля");
    this.name = name;
  }

  /**
   * Проверяет имя поля (1) на корректность
   *
   * @param name (1) -- допустимы только "0-9A-Za-z_$-"
   * @return --
   */
  private boolean isCorrect(String name) {
    if (StringUtils.isNoneBlank(name)) {
      String stValidSymbols = TypeConsts.CFED_ENG_ALL + TypeConsts.CFED_DIGITS + "_$-";
      return StringUtils.containsOnly(name, stValidSymbols);
    }
    return false;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    //toString-template '[snuz]'
    return Ycyf_01.RWRY_OPEN + "FieldName{" +
      "\n" + Ycyf_01.RWRY_INN + ":name='" + name + '\'' +
      "\n" + Ycyf_01.RWRY_CLOSE + "}";
  }
}

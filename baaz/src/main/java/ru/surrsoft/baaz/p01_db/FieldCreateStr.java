package ru.surrsoft.baaz.p01_db;

import org.apache.commons.lang3.StringUtils;

import ru.surrsoft.baaz.debug.Ycyf_01;
import ru.surrsoft.baaz.p01_db.b.FieldName;
import ru.surrsoft.baaz.tclasses.TArray_01;
import ru.surrsoft.baaz.tclasses.TString_01;
import ru.surrsoft.baaz.univers.U;

/**
 * [[zvsu]] - строка которая используется для описания поля, в SQL-инструкции создания таблицы БД
 * <p>
 * ПРИМЕР: ниже, то что в угловых скобках это [zvsu]
 * CREATE tableName &lt;fieldName integer primary key autoincrement>, &lt;fieldName2 text UNIQUE>, ...
 */
public class FieldCreateStr {
  private String stZvsu;

  private String stFieldName = "";

  private FieldName fieldName;

  private String stFieldType = "";

  private EFieldType fieldType;

  private String stFieldConstraints = "";

  /**
   * При создании выполняется нормализация и разбор (1)
   * <p>
   * В поля st.. пишется строковое представление, в поля oj.. - объектное
   *
   * @param stZvsu (1) --
   */
  public FieldCreateStr(String stZvsu) {
    U.se(!isCorrect(stZvsu), "");
    //---
    stZvsu = TString_01.normalize(stZvsu);
    stZvsu = StringUtils.lowerCase(stZvsu);
    this.stZvsu = stZvsu;
    //---
    mtResolve(stZvsu);
  }

  /**
   * Разбирает [zvsu]-строку (1) и раскладывает полученное по полям текущего класса
   * <p>
   * #errors - если при разборе обнаружится что входная строка (1) некорректна.
   *
   * @param stZvsu (1)
   */
  private void mtResolve(String stZvsu) {
    String[] x = StringUtils.split(stZvsu, " ");
    U.se(x.length == 0, "не обнаружено имя поля");
    //---
    stFieldName = x[0];
    if (x.length > 1) {
      stFieldType = x[1];
    }
    stFieldConstraints = "";
    if (x.length > 2) {
      x = TArray_01.elemRemoveFirst(x);
      x = TArray_01.elemRemoveFirst(x);
      stFieldConstraints = StringUtils.join(x, " ");
    }
    //--- field name oj
    fieldName = new FieldName(stFieldName);
    //--- field type oj
    fieldType = EFieldType.OTHER;
    for (EFieldType eFieldType : EFieldType.values()) {
      if (eFieldType.stVal.equals(stFieldType)) {
        fieldType = eFieldType;
        break;
      }
    }
    //---
  }

  public boolean isCorrect(String zvsu) {
    if (!StringUtils.isBlank(zvsu)) {
      zvsu = TString_01.normalize(zvsu);
      if (zvsu.contains(" ") && zvsu.length() >= 3) { //если содержит пробел и ...
        return true;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    //toString-template '[snuz]'
    return Ycyf_01.RWRY_OPEN + "FieldCreateStr{" +
      "\n" + Ycyf_01.RWRY_INN + ":stZvsu='" + stZvsu + '\'' +
      "\n" + Ycyf_01.RWRY_INN + ":stFieldName='" + stFieldName + '\'' +
      "\n" + Ycyf_01.RWRY_INN + ":fieldName=" + fieldName +
      "\n" + Ycyf_01.RWRY_INN + ":stFieldType='" + stFieldType + '\'' +
      "\n" + Ycyf_01.RWRY_INN + ":fieldType=" + fieldType +
      "\n" + Ycyf_01.RWRY_INN + ":stFieldConstraints='" + stFieldConstraints + '\'' +
      "\n" + Ycyf_01.RWRY_CLOSE + "}";
  }
}

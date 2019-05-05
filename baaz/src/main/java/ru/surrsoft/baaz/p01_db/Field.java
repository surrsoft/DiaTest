package ru.surrsoft.baaz.p01_db;

import ru.surrsoft.baaz.debug.Ycyf_01;
import ru.surrsoft.baaz.p01_db.b.FieldName;
import ru.surrsoft.baaz.univers.U;

import static ru.surrsoft.baaz.tclasses.TString_01.spaceLeftIf;

/**
 * Инкарнирует поле таблицы БД
 */
public class Field {
  /**
   * Имя поля
   */
  private FieldName fieldName;

  /**
   * Тип поля
   */
  public EFieldType fieldType;

  public boolean bPrimaryKeyAutoincrement;
  public boolean bUnique;

  //--- enum

  //--- construstors
  public Field() {
  }

  //--- builders
  public Field buName(FieldName name) {
    this.fieldName = name;
    return this;
  }

  public Field buFieldType(EFieldType eFieldType) {
    this.fieldType = eFieldType;
    return this;
  }

  /**
   * Ищет по (1) константу обозначающую тип поля и записывает её в {@link #fieldType} .
   * Запиешт в {@link #fieldType} NULL если не найдет константу
   * <p>
   * #errors нет
   *
   * @param stFieldType (1) --
   * @return --
   */
  public Field buFieldType(String stFieldType) {
    EFieldType e = EFieldType.enumGetByVal(stFieldType);
    fieldType = e;
    return this;
  }

  /**
   * Передать TRUE чтобы поле было вида "primary key autoincrement"
   *
   * @param b
   * @return
   */
  public Field buPrimaryKeyAutoincrement(boolean b) {
    this.bPrimaryKeyAutoincrement = b;
    return this;
  }

  /**
   * Передать TRUE чтобы поле было вида "unique"
   *
   * @param b
   * @return
   */
  public Field buUnique(boolean b) {
    this.bUnique = b;
    return this;
  }


  //--- </ builders

  /**
   * [zvsu] - строка которая используется для описания поля, в SQL-инструкции создания таблицы БД
   * <p>
   * ПРИМЕР: ниже, то что в угловых скобках это [zvsu]
   * CREATE tableName &lt;fieldName integer primary key autoincrement>, &lt;fieldName2 text UNIQUE>, ...
   *
   * @return --
   */
  public String createZvsu() {
    U.se(fieldName == null, "не указано имя поля");
    U.se(fieldType == null, "не указан тип поля");
    U.se(
      bPrimaryKeyAutoincrement && fieldType != EFieldType.INTEGER,
      "если primary key autoincrement, то должен быть указан тип поля integer"
    );
    U.se(
      bPrimaryKeyAutoincrement && bUnique,
      "нужно указать что-то одно: unique или primary key autoincrement"
    );
    //---
    String stFieldType = "";
    if (fieldType != null) {
      stFieldType = fieldType.stVal;
    }
    //---
    return fieldName.getName()
      + spaceLeftIf(stFieldType)
      + spaceLeftIf((bPrimaryKeyAutoincrement ? "primary key autoincrement" : ""))
      + spaceLeftIf((bUnique ? "unique" : ""));
  }

  /**
   * {не реализовано} создаёт себя (поле) в таблице (1)
   *
   * @param table
   */
  public void create__NotRealise(Table table) {

  }

  public FieldName getFieldName() {
    return fieldName;
  }

  @Override
  public String toString() {
    //toString-template '[snuz]'
    return Ycyf_01.RWRY_OPEN + "Field{" +
      "\n" + Ycyf_01.RWRY_INN + ":fieldName=" + fieldName +
      "\n" + Ycyf_01.RWRY_INN + ":fieldType=" + fieldType +
      "\n" + Ycyf_01.RWRY_INN + ":bPrimaryKeyAutoincrement=" + bPrimaryKeyAutoincrement +
      "\n" + Ycyf_01.RWRY_INN + ":bUnique=" + bUnique +
      "\n" + Ycyf_01.RWRY_CLOSE + "}";
  }
}

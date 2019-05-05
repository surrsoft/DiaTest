package ru.surrsoft.baaz.p01_db;

/**
 * Инкарнация ячейки таблицы
 */
public class Cell {

  private Row row;

  /**
   * Поле к которому относится ячейка
   */
  private Field field;

  /**
   * Значение ячейки
   */
  private String stValue;

  public Cell() {
  }

  public Cell buField(Field field) {
    this.field = field;
    return this;
  }

  public Cell buValue(String stValue) {
    this.stValue = stValue;
    return this;
  }

  public Cell buRow(Row row) {
    this.row = row;
    return this;
  }

  public Field getField() {
    return field;
  }

  public String getValue() {
    return stValue;
  }
}

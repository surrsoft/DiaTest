package ru.surrsoft.baaz.p01_db;

import java.util.ArrayList;

import ru.surrsoft.baaz.debug.Ycyf_01;

/**
 * Инкарнация полей таблицы БД
 */
public class Fields {

  private ArrayList<Field> fields = new ArrayList<>();

  public Fields() {
  }

  public Fields buAddField(Field field) {
    this.fields.add(field);
    return this;
  }

  public ArrayList<Field> getFields() {
    return fields;
  }

  @Override
  public String toString() {
    //toString-template '[snuz]'
    return Ycyf_01.RWRY_OPEN + "Fields{" +
      "\n" + Ycyf_01.RWRY_INN + ":fields=" + Ycyf_01.toStringList(fields) +
      "\n" + Ycyf_01.RWRY_CLOSE + "}";
  }
}

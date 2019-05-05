package ru.surrsoft.baaz.univers;

import java.util.UUID;

import ru.surrsoft.baaz.debug.Ycyf_01;

/**
 * _Инкарнация UUID.
 * UUID генерируется в конструкторе в момент создания текущего объекта
 * <p>
 * Пример UUID: f1ec14a0-4f51-48cb-9868-4e1c4045a116
 * <p>
 * //new//
 */
public class TypeUUID_01 {

  private String stUUID;

  public TypeUUID_01() {
    stUUID = UUID.randomUUID().toString();
  }

  /**
   * @return например "f1ec14a0-4f51-48cb-9868-4e1c4045a116"
   */
  public String getStUUID() {
    return stUUID;
  }

  @Override
  public String toString() {
    //toString-template '[snuz]'
    return Ycyf_01.RWRY_OPEN + "TypeUUID_01{" +
      "\n" + Ycyf_01.RWRY_INN + ":stUUID='" + stUUID + '\'' +
      "\n" + Ycyf_01.RWRY_CLOSE + "}";
  }
}

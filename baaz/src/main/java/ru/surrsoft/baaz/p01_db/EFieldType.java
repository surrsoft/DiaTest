package ru.surrsoft.baaz.p01_db;

/**
 * [[uakf]] - типы полей; значения (1) констант не менять
 * <p>
 * См. https://www.sqlite.org/datatype3.html
 */
public enum EFieldType {

  TEXT("text"),
  INTEGER("integer"),
  REAL("real"),
  BLOB("blob"),
  NULL("null"),
  //на всякий случай
  OTHER("c78a0296-f11a-4362-b0dc-6e4806a3a67a")
  ;

  public final String stVal;

  EFieldType(String stVal) {
    this.stVal = stVal;
  }

  /**
   * Вовзращает константу у которой stVal == (1)
   * @param stVal (1) --
   * @return null если константа не найдена
   */
  public static EFieldType enumGetByVal(String stVal) {
    for (EFieldType e : EFieldType.values()) {
      if(e.stVal.equals(stVal)){
        return e;
      }
    }
    return null;
  }

}

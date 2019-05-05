package ru.surrsoft.baaz;

/**
 * Типовые константы
 */
public class TypeConsts {

  //--- основное SharedPreferences хранилище (см. [ppmk])
  public static final String SHARPREF_COMMON_NAME = "sharprefCommon";

  //--- [[cfed]] - алфавиты ([алфавит])
  public static final String CFED_RUS_ALL = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";
  public static final String CFED_RUS_UP = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
  public static final String CFED_RUS_DOWN = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
  public static final String CFED_ENG_ALL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
  public static final String CFED_ENG_UP = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  public static final String CFED_ENG_DOWN = "abcdefghijklmnopqrstuvwxyz";
  public static final String CFED_DIGITS = "0123456789";

  //--- [[kuuz]] - константы обозначающие результат выполнения какой-либо операции над чем-либо
  public enum EKuuz{
    /**
     * Выполнено успешно
     */
    DONE,
    /**
     * Не выполнено т.к. не обнаружен объект над которым можно было бы выполнить действие (например
     * не существует запись которую можно было бы удалить)
     */
    NOT_EXIST,
    /**
     * Не выполнено по другим причинам
     */
    NOT
  }
}

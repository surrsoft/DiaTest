package ru.surrsoft.baaz.univers;

/**
 * [[moaz]] - представляет результат единичного интернет-запроса
 * <p>
 * #id [[w349w]]
 *
 * ВЕРСИЯ 2 1.1 2019-02-05 (stored)
 */
public class Moaz_j_ {
  /**
   * Тело ответа ([dsif])
   */
  public String dsif;
  /**
   * Код состояния HTTP ([baut])
   */
  public int baut;
  /**
   * Тип ответа
   */
  public ETypeResponse type;
  /**
   * Предназначен для любых вспомогательных целей
   */
  public Object backObject;

  //==============================================================================================

  /**
   * @return TRUE если ответ не был получен из-за проблем с интернет соединением на стороне
   * клиентского устройства
   */
  public boolean isInternetProblems() {
    return type == ETypeResponse.ERR_CONNECT;
  }

  @Override
  public String toString() {
    return String.format("codeHttp=%s; type=%s; body=%s", baut, type, dsif);
  }
}

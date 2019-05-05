package ru.surrsoft.baaz.datetime;

/**
 * ^[[dcwg]] - строковые форматы представления даты
 * <p>
 * //new//
 */
public enum EDateFormat {
  /**
   * ^[[jnne]] - "DD.MM.YYYY", например "28.04.2019"
   */
  JNNE,
  /**
   * ^[[bgit]] - "YYYY-MM-DD", например "2019-04-28"
   */
  BGIT,
  /**
   * ^[[ivab]] - "YYYY-MM-DD HH:MM:SS", например "2019-04-29 09:20:18"
   */
  IVAB,
  /**
   * ^[[mnep]] - "YYYYMMDD", например "20190428"
   */
  MNEP,
  /**
   * ^[[necz]] - [eavv] в виде строки, например "1460266975"
   */
  NECZ,
  /**
   * ^[[tafb]] - [yata] в виде строки, например "1460266975856"
   */
  TAFB
}

package ru.surrsoft.baaz.datetime;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.temporal.ChronoField;

import ru.surrsoft.baaz.univers.U;

/**
 * Инкарнация даты ([wxuw])
 * <p>
 * Внутри хранится в [yata]-форме (UTC unixtime millisec) в _дате
 * <p>
 * ПОНЯТИЯ:
 * -- _дата - поле {@link #date}
 * -- _uums - синоним для [yata] (UTC unixtime millisec)
 * -- _uus - синоним для [eavv] (UTC unixtime sec)
 * <p>
 * //new//new//
 */
public class KrDate {
  /**
   * UTC unixtime millisec ([yata])
   */
  private long date = Instant.MIN.getLong(ChronoField.INSTANT_SECONDS) + Instant.MIN.getLong(ChronoField.MILLI_OF_SECOND);
  private boolean bConstr;

  //region constructors

  public KrDate() {
  }

  /**
   * @param uus (1) -- [eavv] (UTC unixtime sec)
   */
  public KrDate constrUus(long uus) {
    mtConstrVerifyEx();
    //---
    date = uus * 1000;
    return this;
  }


  /**
   * @param uums (1) -- [yata] (UTC unixtime millisec)
   */
  public KrDate constrUums(long uums) {
    mtConstrVerifyEx();
    //---
    date = uums;
    return this;
  }

  /**
   * Инициализируем _дату текущей датой (датой момента вызова текущего метода)
   *
   * @return --
   */
  public KrDate constrCurrent() {
    mtConstrVerifyEx();
    //---
    date = Instant.now().toEpochMilli();
    return this;
  }

  //endregion constructors

  /**
   * Получение даты в [yata]-форме (UTC unixtime millisec)
   *
   * @return --
   */
  public long asUums() {
    return date;
  }

  /**
   * Получение даты в [eavv]-форме (UTC unixtime sec)
   *
   * @return --
   */
  public long asUus() {
    return date / 100;
  }

  /**
   * Получение текстового представления даты ([dcwg]) в форме (1) в текущей time-zone устройства
   *
   * @param eDateFormat (1) --
   * @return --
   */
  public String get(EDateFormat eDateFormat) {
    Instant instant = Instant.ofEpochMilli(date);
    ZoneId zoneId = ZoneId.systemDefault();
    LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
    //---
    switch (eDateFormat) {
      case JNNE:
        return localDateTime.format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
      case BGIT:
        return localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE);
      case IVAB:
        String stDate = localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String stTime = localDateTime.toLocalTime().withNano(0).format(DateTimeFormatter.ISO_LOCAL_TIME);
        return stDate + " " + stTime;
      case MNEP:
        //TODO
        break;
      case NECZ:
        //TODO
        break;
      case TAFB:
        //TODO
        break;
    }
    return "";
  }

  /**
   * Проверяет чтобы методы-конструкторы вызывались только один раз
   */
  private void mtConstrVerifyEx() {
    U.se(bConstr, "метод-конструктор уже вызывался");
    bConstr = true;
  }

}

package ru.surrsoft.baaz.cls_b;

import ru.surrsoft.baaz.cls_d.Nmhn_01;

/**
 * [[zzca]]
 * <p>
 * Класс для использования только реализующими интерфейс {@link Nmhn_01}.
 * Это инкарнация _значения ([ebef]), т.е. результата обращения к _хранилищу ([pgui]).
 * <p>
 * [sbso]-вид: _класс-хранитель ([toio])
 * <p>
 * Старое название "N2168Dbval"
 */
public class Zzca_01 {

  //--- ---
  /**
   * [[sixz]] - контанты для обозначения какой именно тип хранит текущий объект
   */
  public enum ESixz {
    SIXZ_STRING,
    SIXZ_BOOLEAN,
    SIXZ_FLOAT,
    SIXZ_INT,
    SIXZ_LONG,
    /**
     * означает что текущая сущность не представляет ещё никакого типа и следовательно не хранит никакого значения
     */
    SIXZ_NONE
  }

  //--- --- fields
  public ESixz sixz = ESixz.SIXZ_NONE;

  /**
   * Здесь TRUE если {значение} {ячейки} было успешно прочитано (т.е. если указанный ключ существует)
   */
  public boolean readed = false;

  //--- поля для хранения значений
  /**
   * {Значение} {ячейки}. Это либо NULL либо НЕ NULL
   */
  public String val_string = null;

  public boolean val_boolean;

  public int val_int;

  public float val_float;

  public long val_long;

  //--- --- constructors
  public Zzca_01() {
  }
}

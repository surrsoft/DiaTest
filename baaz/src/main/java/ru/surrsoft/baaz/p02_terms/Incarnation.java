package ru.surrsoft.baaz.p02_terms;

import ru.surrsoft.baaz.debug.Ycyf_01;
import ru.surrsoft.baaz.univers.U;

/**
 * Инкарнация ([cbua])
 * <p>
 * ^[[cbua]] - инкарнация
 */
public abstract class Incarnation {

  private EFspk cbuaExist = EFspk.NOT_DEFINED;

  /**
   * Конкретная реализация должна вернуть константу существования сущности
   *
   * @return --
   */
  public EFspk cbuaExistGet() {
    return cbuaExist;
  }

  public void cbuaExistSet(EFspk e) {
    U.se(e == null, "");
    cbuaExist = e;
  }

  @Override
  public String toString() {
    //toString-template '[snuz]'
    return Ycyf_01.RWRY_OPEN + "Incarnation{" +
      "\n" + Ycyf_01.RWRY_INN + ":cbuaExist=" + cbuaExist +
      "\n" + Ycyf_01.RWRY_CLOSE + "}";
  }
}

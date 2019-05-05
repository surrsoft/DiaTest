package ru.surrsoft.baaz.p01_db;

import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.debug.Ycyf_01;

/**
 * Инкарнирует версию БД
 */
public class DatabaseVersion {
  private int version;

  public DatabaseVersion(int version) {
    if (!isCorrect(version)) {
      throw new SomeException("(debug) некорректная версия; [" + version + "]");
    }
    //---
    this.version = version;
  }

  private boolean isCorrect(int version) {
    return version >= 0;
  }

  public int getVersion() {
    return version;
  }

  @Override
  public String toString() {
    //toString-template '[snuz]'
    return Ycyf_01.RWRY_OPEN + "DatabaseVersion{" +
      "\n" + Ycyf_01.RWRY_INN + ":version=" + version +
      "\n" + Ycyf_01.RWRY_CLOSE + "}";
  }
}

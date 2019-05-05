package ru.surrsoft.baaz.tclasses;

import android.arch.persistence.room.RoomDatabase;

/**
 * Утилитные методы для работы с Room-БД
 * <p>
 * //new//
 */
public class TRoomDb {

  /**
   * -- удяляет все ряды всех таблиц зарегестрированных на БД (1) как Database.entities()
   * -- не сбрасывает auto-increment значение сгенерированное через PrimaryKey.autoGenerate()
   * -- после удаления рядов, Room будет устанавливать WAL checkpoint и запускать VACUUM. Это
   * означает что данные полностью удаляться. Пространство будет восстановлено в системе если
   * размер превысит пороговый размер файла Б
   *
   * @param roomDb (1) --
   */
  public static void tablesClearAll(RoomDatabase roomDb) {
    roomDb.clearAllTables();
  }

}

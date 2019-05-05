package ru.surrsoft.baaz.p01_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

import ru.surrsoft.baaz.debug.Ycyf_01;
import ru.surrsoft.baaz.p01_db.b.DatabaseName;
import ru.surrsoft.baaz.univers.U;

/**
 * Инкарнация _бд-пути ([vtpf])
 * <p>
 * Пример пути:
 * /data/user/0/ru.surrsoft.fortests_w206w/databases/dbtest10
 */
public class DatabasePath {
  private static final String DELIMITER = "/";

  private String path;

  public DatabasePath(String path) {
    U.se(!isCorrect(path), "некорректный _бд-путь [" + path + "] ");
    this.path = path;
  }

  public DatabasePath(DatabaseName databaseName, Context ctx) {
    U.se(databaseName == null, "");
    U.se(ctx == null, "");
    //---
    File fileDb = ctx.getDatabasePath(databaseName.getName());
    this.path = fileDb.getAbsolutePath();
  }

  public DatabasePath(SQLiteDatabase db) {
    U.se(db == null, "");
    this.path = db.getPath();
  }

  /**
   * Возвращает абсолютный путь к БД ([vtpf])
   * @return например "/data/user/0/ru.surrsoft.fortests_w206w/databases/dbtest10"
   */
  public String getPath() {
    return path;
  }

  public boolean isCorrect(String path) {
    if (StringUtils.isNoneBlank(path)) {
      if (path.contains(DELIMITER) && path.length() > 7) {
        return true;
      }
    }
    return false;
  }

  public DatabaseName getDatabaseName() {
    String stDbName = path.substring(path.lastIndexOf(DELIMITER) + 1);
    return new DatabaseName(stDbName);
  }

  @Override
  public String toString() {
    //toString-template '[snuz]'
    return Ycyf_01.RWRY_OPEN + "DatabasePath{" +
      "\n" + Ycyf_01.RWRY_INN + ":path='" + path + '\'' +
      "\n" + Ycyf_01.RWRY_CLOSE + "}";
  }
}

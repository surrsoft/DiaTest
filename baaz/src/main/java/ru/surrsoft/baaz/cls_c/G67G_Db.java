package ru.surrsoft.baaz.cls_c;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.apache.commons.lang3.ArrayUtils;

import java.io.File;

import ru.surrsoft.baaz.Bysa_01;

/**
 * Работа с БД
 */
public class G67G_Db {
  private static final String TAG = ":G67G:Db";

  /**
   * Размер БД
   *
   * @param dbName  (1) -- имя БД; например "name.db"
   * @param context (2) -- контекст
   * @return --
   */
  public static long getDbSize(String dbName, Context context) {
    File f = context.getDatabasePath(dbName);
    return f.length();
  }

  /**
   * Размер БД
   *
   * @param dbName  (1) -- имя БД; например "name.db"
   * @param context (2) -- контекст
   * @return --
   */
  public static String getDbSize_B(String dbName, Context context) {
    File f = context.getDatabasePath(dbName);
    long size = f.length();
    if (size < 1000) {
      return size + "b";
    } else if (size >= 1000 && size < 1000000) {
      return size / 1000 + "Kb";
    }
    return TMath_01.roundSt_B(size / 1000000f, 1, null) + "Mb";
  }

  /**
   * Количество записей в таблице (2) БД (1)
   *
   * @param db        (1) --
   * @param tableName (2) --
   * @return
   */
  public static long getEntriesCount(SQLiteDatabase db, String tableName) {
    return DatabaseUtils.queryNumEntries(db, tableName);
  }

  /**
   * Количество записей таблицы (2) у которых в поле (3) содержится значение (4)
   *
   * @param _db       (1) --
   * @param tableName (2) -- имя таблицы
   * @param fieldName (3) -- имя поля
   * @param value     (4) -- значение
   * @return --
   * <p>
   * #version 1 14-06-2016
   */
  public static long getEntriesCountWhere(SQLiteOpenHelper _db, String tableName, String fieldName, String value) {
    long ret = 0L;
    SQLiteDatabase db = _db.getReadableDatabase();
    String sql = String.format("SELECT count(%s) FROM %s WHERE %s = '%s'", fieldName, tableName, fieldName, value);
    Cursor cursor = db.rawQuery(sql, null);
    cursor.moveToFirst();
    if (cursor.getCount() > 0) {
      do {
        ret = cursor.getLong(0);
      } while (cursor.moveToNext());
    }
    cursor.close();
    db.close();
    return ret;
  }

  /**
   * Обновление значения поля (5) ряда, который в поле (3) содержит значение (4), значением (6)
   *
   * @param _db         (1) --
   * @param tableName   (2) --
   * @param whereField  (3) --
   * @param whereValue  (4) --
   * @param updateField (5) --
   * @param updateValue (6) --
   */
  public static void updateValueWhere(SQLiteOpenHelper _db, String tableName,
                                      String whereField, String whereValue,
                                      String updateField, String updateValue) {
    SQLiteDatabase db = _db.getWritableDatabase();
    ContentValues cv = new ContentValues();
    cv.put(updateField, updateValue);
    String sql = String.format("%s = %s", whereField, whereValue);
    db.update(tableName, cv, sql, null);
    db.close();
  }

  /**
   * Получение user_version базы данных с именем (2). Для поиска: [версия]
   * [version]
   *
   * @param aDbNm (2) -- имя БД, например name.db
   * @return число больше 0 или 0 при проблемах (например если БД с
   * указанным именем нет)
   */
  public static int getDbVersion_v1(String aDbNm) {
    // проверки
    if (Bysa_01.appContext == null) {
      return 0;
    }
    if (aDbNm == null || aDbNm.length() < 1) {
      return 0;
    }

    // если БД не существует
    if (!isDbExist(aDbNm)) {
      return 0;
    }

    // путь размещения БД
    String dbPa = Bysa_01.appContext.getDatabasePath(aDbNm).getAbsolutePath();

    // получение БД по ее имени
    SQLiteDatabase odb = SQLiteDatabase.openDatabase(dbPa, null, SQLiteDatabase.OPEN_READONLY);

    if (odb != null) {
      // получение значения user-version БД
      Cursor cu = odb.rawQuery("PRAGMA user_version", null);
      cu.moveToFirst();
      String version = cu.getString(0);
      cu.close();

      return Integer.parseInt(version);
    }

    return 0;

  }

  /**
   * Получение версии базы данных с именем (1).
   * <p>
   * Этот вариант проще чем getDbVersion_v1 - просто используется метод
   * getVersion вместо cursor и PRAGMA :).
   * <p>
   * <br/>
   * Для поиска: [версия] [version]
   *
   * @param aDbNm (1) -- имя БД, например name.db
   * @return число больше 0 или 0 при проблемах (например если БД с
   * указанным именем нет)
   */
  public static int getDbVersion_B_v1(String aDbNm) {
    // проверки
    if (Bysa_01.appContext == null) {
      return 0;
    }
    if (aDbNm == null || aDbNm.length() < 1) {
      return 0;
    }

    // если БД не существует
    if (!isDbExist(aDbNm)) {
      return 0;
    }

    // путь размещения БД
    String dbPa = Bysa_01.appContext.getDatabasePath(aDbNm).getAbsolutePath();

    // полчение БД по ее имени
    SQLiteDatabase odb = SQLiteDatabase.openDatabase(dbPa, null, SQLiteDatabase.OPEN_READONLY);
    int ret = odb.getVersion();
    odb.close();
    return ret;
  }

  /**
   * Имена всех таблиц БД (1)
   *
   * @param aDbNm (1) -- имя БД, например name.db
   * @return массив имен или null если таблиц нет или проблемы
   */
  public static String[] getTableNames(String aDbNm) {
    // проверки
    if (Bysa_01.appContext == null || aDbNm == null || aDbNm.length() < 1) {
      return null;
    }
    // если БД с таким именем не существует
    if (!isDbExist(aDbNm)) {
      return null;
    }

    // путь размещения БД
    String dbPa = Bysa_01.appContext.getDatabasePath(aDbNm).getAbsolutePath();
    // полчение БД по ее имени
    SQLiteDatabase odb = SQLiteDatabase.openDatabase(dbPa, null, SQLiteDatabase.OPEN_READONLY);

    Cursor cursor = odb.rawQuery("SELECT * FROM sqlite_master", null);
    cursor.moveToFirst();

    // количество таблиц
    int ctTbs = cursor.getCount();
    if (ctTbs < 1) {
      return null;
    }

    // имена таблиц
    String[] nmTbs = new String[ctTbs];
    int c = 0;
    do {
      nmTbs[c] = cursor.getString(cursor.getColumnIndex("tbl_name"));
      c++;
    } while (cursor.moveToNext());
    cursor.close();

    return nmTbs;
  }

  /**
   * Возвращает TRUE если в приложении существует БД с именем (1)
   *
   * @param stDatabaseName (1) -- имя БД, например "name.db"
   * @return --
   */
  public static boolean isDbExist(String stDatabaseName) {
    if (stDatabaseName == null || stDatabaseName.length() < 1) {
      return false;
    }
    //---
    String[] dbNms = Bysa_01.appContext.databaseList();
    for (String dbNm : dbNms) {
      if (stDatabaseName.equals(dbNm)) {
        return true;
      }
    }
    return false;
  }

  /**
   * ЦЕЛЬ: получить содержимое всей {таблицы} (2) БД (1) в виде строки
   *
   * @param db
   * @param tableName
   * @return
   */
  public static String toStringTable(SQLiteDatabase db, String tableName) {
    String ret = "";
    Cursor cursor = db.rawQuery("SELECT * FROM " + tableName, null);
    cursor.moveToFirst();
    ret = toStringCursor(cursor);
    cursor.close();
    return ret;
  }

  /**
   * ЕЛЬ: получить содержимое всей {таблицы} (2) БД (1) в виде строки
   *
   * @param dbOH
   * @param tableName
   * @return
   */
  public static String toStringTable(SQLiteOpenHelper dbOH, String tableName) {
    String ret = "";
    SQLiteDatabase rdb = dbOH.getReadableDatabase();
    ret = toStringTable(rdb, tableName);
    rdb.close();
    return ret;
  }

  /**
   * ЦЕЛЬ: получить курсор в виде строки для вывода в лог
   * <p>
   * ОПИСАНИЕ: возвращает курсор (1) в виде строки
   *
   * @param cursor
   * @return
   */
  public static String toStringCursor(Cursor cursor) {
    return DatabaseUtils.dumpCursorToString(cursor);
  }

  /**
   * Вывод в лог содержимого cursor (1)
   *
   * @param cursor (1) --
   *               <p>
   */
  public static void cursorToLog(Cursor cursor, String prefix) {
    Log2_01.d(TAG, prefix + " " + toStringCursor(cursor), true);
  }

  /**
   * Возвращение File существующей БД (ThFiNm111). В лог и toast выводятся
   * сообщения о нештатных ситуациях.
   *
   * @param aDbFiNm (1) - имя файла БД, например "dbmain"
   * @return 1) File БД <br>
   * 2) null если БД с именем (1) не существует или она не
   * позволяет чтение
   */
  public static File getDbFile(String aDbFiNm, Context context) {

    // получение пути к файлу
    String pa = Bysa_01.appContext.getDatabasePath(aDbFiNm).getAbsolutePath();

    // проверка существования файла по указанному пути
    File dbFi2 = G67G_Storages.createFile(pa, context);
    if (dbFi2 == null) {
      return null;
    }

    return dbFi2;
  }

  /**
   * Возвращение абсолютного пути к БД по имени (1). При этом такой БД в приложении может и не
   * существовать
   *
   * @param stDatabaseName (1) - имя файла БД, например "dbmain"
   * @return путь в любом случае будет возвращен, например
   * "/data/data/your.package/databases/dbmain"
   */
  public static String getDbFilePath(String stDatabaseName) {
    File fileDb = Bysa_01.appContext.getDatabasePath(stDatabaseName);
    return fileDb.getAbsolutePath();
  }

  /**
   * TRUE если таблица (3) существует в БД (2). Корректность входных
   * данных не проверяется
   *
   * @param dbName    (2) -- имя базы данных, например name.db
   * @param tableName (3) -- имя таблицы, наприме table
   * @return
   */
  public static boolean isTableExistInDb(String dbName, String tableName, Context ctx) {

    // проверка существования таблицы
    boolean tbExist = false;
    String p = ctx.getDatabasePath(dbName).getAbsolutePath();
    SQLiteDatabase db = SQLiteDatabase.openDatabase(p, null, SQLiteDatabase.OPEN_READONLY);
    Cursor cu = db.rawQuery("SELECT tbl_name FROM sqlite_master", null);
    cu.moveToFirst();
    if (cu.getCount() > 0) {
      do {
        String nameTb = cu.getString(cu.getColumnIndex("tbl_name"));
        if (nameTb.equals(tableName)) {
          tbExist = true;
          break;
        }
      } while (cu.moveToNext());
    }
    cu.close();
    if (tbExist) {
      db.close();
      return true;
    }

    db.close();
    return false;
  }

  /**
   * TRUE если таблица (3) базы данных (2) содержит столбец (4). (ThNVE)
   *
   * @param db         (2) -- база данных
   * @param tableName  (3) -- имя таблицы, например table
   * @param columnName (4) -- имя столбца, например column
   * @return null при проблемах
   */
  public static boolean isFieldExistInTableDb(SQLiteDatabase db, String tableName, String columnName) {
    Cursor cu2 = db.rawQuery("SELECT * FROM " + tableName + " LIMIT 1", null);
    int i = cu2.getColumnIndex(columnName);
    cu2.close();
    return i != -1;
  }

  /**
   * Получение объекта БД по его имени. Объект только для чтения
   *
   * @param dbName (1) -- имя БД, например name.db
   * @return null при проблемах
   */
  public static SQLiteDatabase getDbReadableByNm(String dbName) {
    if (Bysa_01.appContext == null || dbName == null || dbName.length() < 1) {
      return null;
    }
    if (!isDbExist(dbName)) {
      return null;
    }

    String p = Bysa_01.appContext.getDatabasePath(dbName).getAbsolutePath();
    return SQLiteDatabase.openDatabase(p, null, SQLiteDatabase.OPEN_READONLY);
  }

  /**
   * Получение объекта БД по его имени с правами на чтение и запись
   *
   * @param dbName (1) -- имя БД, например name.db
   * @return null при проблемах
   */
  public static SQLiteDatabase getDbWritableByNm(String dbName) {
    if (Bysa_01.appContext == null || dbName == null || dbName.length() < 1) {
      return null;
    }
    if (!isDbExist(dbName)) {
      return null;
    }

    String p = Bysa_01.appContext.getDatabasePath(dbName).getAbsolutePath();
    return SQLiteDatabase.openDatabase(p, null, SQLiteDatabase.OPEN_READWRITE);
  }

  /**
   * Возвращает массив имен всех столбцов таблицы (2) базы данных (1)
   *
   * @param aDbNm (1) -- имя базы данных, например name.db
   * @param aTbNm (2) -- имя таблицы базы данных, например table
   * @return
   */
  public static String[] getFieldNames(String aDbNm, String aTbNm) {

    String path = Bysa_01.appContext.getDatabasePath(aDbNm).getAbsolutePath();
    SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);

    Cursor cu = db.rawQuery("SELECT * FROM " + aTbNm + " LIMIT 1", null);
    cu.moveToFirst();
    String[] clNms = cu.getColumnNames();
    cu.close();

    return clNms;
  }

  /**
   * Получение из БД (1) значений столбца (3) (типа long) по условию (2)
   *
   * @param db         (1) -- БД
   * @param sql        (2) -- например "SELECT _id FROM tableName"
   * @param columnName (3) -- "_id"
   * @return --
   * <p>
   * #version 1 21.05.2016
   */
  public static long[] getColumnValues_long(SQLiteDatabase db, String sql, String columnName) {
    long[] ret = {};
    Cursor cursor = db.rawQuery(sql, null);
    cursor.moveToFirst();
    if (cursor.getCount() > 0) {
      long val;
      do {
        val = cursor.getLong(cursor.getColumnIndex(columnName));
        ret = ArrayUtils.add(ret, val);
      } while (cursor.moveToNext());
    }
    cursor.close();
    db.close();
    return ret;
  }

  /**
   * Получение из БД (1) значений столбца (3) (типа String) по условию (2)
   *
   * @param db         (1) -- БД
   * @param sql        (2) -- например "SELECT _id FROM tableName"
   * @param columnName (3) -- "_id"
   * @return --
   * <p>
   * #version 1 21.05.2016
   */
  public static String[] getColumnValues_String(SQLiteDatabase db, String sql, String columnName) {
    String[] ret = {};
    Cursor cursor = db.rawQuery(sql, null);
    cursor.moveToFirst();
    if (cursor.getCount() > 0) {
      String val;
      do {
        val = cursor.getString(cursor.getColumnIndex(columnName));
        ret = ArrayUtils.add(ret, val);
      } while (cursor.moveToNext());
    }
    cursor.close();
    db.close();
    return ret;
  }

  /**
   * Создание в таблице (3) точной копии ряда (1)
   *
   * @param _id          (1) -- первичный ключ ряда клон которого нужно создать
   * @param dbHelper     (2) -- БД
   * @param tableName    (3) -- имя таблицы
   * @param idFieldName  (4) -- имя поля с первичными ключами, например "_id"
   * @param spcFieldName (5) -- имя АБСОЛЮТНО ЛЮБОГО поля СУЩЕСТВУЮЩЕГО в таблице (3) (кроме поля с первичными ключами);
   *                     необходимо для технологических целей при создании нового ряда;
   * @return первичный ключ новой записи
   */
  public static long cloneRow(long _id, SQLiteOpenHelper dbHelper, String tableName, String idFieldName, String spcFieldName) {
    //=== вставка нового "пустого" ряда и получение его первичного ключа
    SQLiteDatabase db = dbHelper.getWritableDatabase();
    ContentValues cv = new ContentValues();
    cv.put(spcFieldName, "");
    long newID = db.insert(tableName, null, cv);
    //=== извлечение данных из клонируемого ряда
    String sql = String.format("SELECT * FROM %s WHERE %s = %s", tableName, idFieldName, _id);
    Cursor cursor = db.rawQuery(sql, null);
    cursor.moveToFirst();
    ContentValues cv2 = new ContentValues();
    if (cursor.getCount() > 0) {
      do {
        for (String elem : cursor.getColumnNames()) {
          if (!elem.equals(idFieldName)) { //кроме столбца с первичными ключами
            cv2.put(elem, cursor.getString(cursor.getColumnIndex(elem)));
          }
        }
      } while (cursor.moveToNext());
    }
    cursor.close();
    //=== запись клонированных данных
    String sql2 = String.format("%s = %s", idFieldName, newID);
    db.update(tableName, cv2, sql2, null);
    //===
    db.close();
    return newID;
  }

  /**
   * Удаляет таблицу (1) из БД (2)
   *
   * @param tableName        (1) -- имя таблицы
   * @param sqLiteOpenHelper (2) --
   */
  public static void dropTable(String tableName, SQLiteOpenHelper sqLiteOpenHelper) {
    SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
    String sql = String.format("DROP TABLE IF EXISTS %s", tableName);
    db.execSQL(sql);
    db.close();
  }


  /**
   * Удаляет таблицу (1) из БД (2)
   *
   * @param tableName (1) -- имя таблицы
   * @param db        (2) --
   */
  public static void dropTable(String tableName, SQLiteDatabase db) {
    String sql = String.format("DROP TABLE IF EXISTS %s", tableName);
    db.execSQL(sql);
  }

  /**
   * Создает в таблице (2) БД (1) колонку (3)
   * <p>
   * //-change-4.9
   *
   * @param db         (1) -- writable db
   * @param tableName  (2) -- имя таблицы
   * @param columnName (3) -- имя колонки
   * @return TRUE если колонка была создана, FALSE иначе (например если она уже существует)
   */
  public static boolean createColumn(SQLiteDatabase db, String tableName, String columnName) {
    if (!isFieldExistInTableDb(db, tableName, columnName)) {
      String sql = String.format("ALTER TABLE %s ADD COLUMN %s", tableName, columnName);
      db.execSQL(sql);
      return true;
    }
    return false;
  }

}

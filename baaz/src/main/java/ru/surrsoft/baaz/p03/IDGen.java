package ru.surrsoft.baaz.p03;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import ru.surrsoft.baaz.cls_c.G67G_Db;

/**
 * [[dczi]]
 * <p>
 * Генератор целочисленных ID. Отсчёт ID начинается с числа 101.
 * <p>
 * ИСПОЛЬЗОВАНИЕ: {@link #idCreate()}, {@link #idCreate_B()}
 * <p>
 * КАК РАБОТАЕТ: используется специально предназначенная для хранения одного
 * * последнего ID БД. Берёт текущее значение ID из БД, увеличивает его на 1, записывает обрано в БД,
 * возвращает увеличенный ID
 * <p>
 * КОДЫ: [neoi]
 */
public class IDGen {

  private static final String DB_NAME = "dczi_db5.sqlite";
  private static final String TABLE_NAME = "dczi_table";
  private static final String FIELD_NAME = "dczi_field";
  private static final long lgStartValue = 100L;

  private IDGen() {
  }

  /**
   * Создаёт новый ID
   *
   * @return 101, 102, ...
   */
  public static long idCreate() {
    //--- если БД отсутствует, создаём
    SQLiteDatabase db;
    if (!G67G_Db.isDbExist(DB_NAME)) {
      db = mtDbCreate();
    } else {
      db = SQLiteDatabase.openDatabase(
        G67G_Db.getDbFilePath(DB_NAME), null, SQLiteDatabase.OPEN_READWRITE
      );
    }
    //---  получение текущего значения ID
    Cursor cursor = db.rawQuery("SELECT " + FIELD_NAME + " FROM " + TABLE_NAME, null);
    cursor.moveToFirst();
    long idBefore = cursor.getLong(cursor.getColumnIndex(FIELD_NAME));
    cursor.close();
    //--- инкремент
    long idAfter = idBefore + 1L;
    //--- запись инкремента обрано в БД
    db.execSQL("UPDATE " + TABLE_NAME + " SET " + FIELD_NAME + " = " + idAfter
      + " WHERE " + FIELD_NAME + " = " + idBefore);
    //--- возврат инкремента
    return idAfter;
  }

  /**
   * Отличается от А только тем что возвращает не long а int
   *
   * @return --
   */
  public static int idCreate_B() {
    return (int) idCreate();
  }

  /**
   * Создание БД
   */
  private static SQLiteDatabase mtDbCreate() {
    //---
    SQLiteDatabase db = SQLiteDatabase.openDatabase(
      G67G_Db.getDbFilePath(DB_NAME),
      null,
      SQLiteDatabase.CREATE_IF_NECESSARY
    );
    //---
    db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + FIELD_NAME + " integer);");
    //---
    db.execSQL("INSERT INTO " + TABLE_NAME + " (" + FIELD_NAME + ") VALUES (" + lgStartValue + ");");
    //---
    return db;
  }

}

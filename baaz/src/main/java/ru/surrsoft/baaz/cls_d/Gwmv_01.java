package ru.surrsoft.baaz.cls_d;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import ru.surrsoft.baaz.cls_b.Zzca_01;
import ru.surrsoft.baaz.cls_c.G67G_Db;

/**
 * Класс-реализация {@link Nmhn_01} которая в качестве мета хранения _пар испльзует таблицу БД
 * <p>
 * Каждый тип данных хранится в своем столбце "_val_...".
 * <p>
 * В конструктор нужно передать имя для файла-БД и номер-версии-этой-БД. Если такая БД еще не
 * существует, она будет создана
 * <p>
 * ПОНЯТИЯ:
 * -- [[pshb]], _таблица - таблица в которйой хранятся все данные
 * <p>
 * IDs: [[gwmv]], [[w169w]], старое имя "N2169_Dbsharpref"
 * <p>
 * КОДЫ: [zipc]
 */
public abstract class Gwmv_01 extends SQLiteOpenHelper implements Nmhn_01 {

  //--- имя таблицы
  public static final String TABLE_NAME = "main";
  //--- столбцы
  public static final String CID = "_id";
  //столбец для хранения _ключей ([hprb])
  public static final String CKEY = "_key";
  public static final String CVAL_STRING = "_val_string";
  private static final String CVAL_BOOLEAN = "_val_boolean";
  private static final String CVAL_FLOAT = "_val_float";
  private static final String CVAL_LONG = "_val_long";
  private static final String CVAL_INT = "_val_int";

  /**
   * Конструктор. При вызове, создаётся таблица в БД с именем (2) версии (3)
   *
   * @param ctx     (1) --
   * @param dbName  (2) -- имя файла-БД
   * @param version (3) -- версия БД (2)
   */
  public Gwmv_01(Context ctx, String dbName, int version) {
    super(ctx, dbName, null, version);
  }

  //--- < SQLiteOpenHepler >
  @Override
  public void onCreate(SQLiteDatabase db) {
    //создание таблицы
    String fields = CID + " integer primary key autoincrement"
      + ", " + CKEY + " text UNIQUE"
      + ", " + CVAL_STRING + " text"
      + ", " + CVAL_INT + " integer"
      + ", " + CVAL_BOOLEAN + " integer"
      + ", " + CVAL_FLOAT + " real"
      + ", " + CVAL_LONG + " integer";
    db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + fields + ");");
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

  }
  //--- </ SQLiteOpenHepler >


  //--- interface [Nmhn]

  @Override
  public int putString(String stKey, String stVal) {
    return put(CVAL_STRING, stKey, stVal);
  }

  @Override
  public int putInt(String key, int val) {
    return put(CVAL_INT, key, val + "");
  }

  @Override
  public int putBoolean(String key, boolean val) {
    return put(CVAL_BOOLEAN, key, val ? "1" : "0");
  }

  @Override
  public int putFloat(String key, float val) {
    return put(CVAL_FLOAT, key, Float.toString(val));
  }

  @Override
  public int putLong(String key, long val) {
    return put(CVAL_LONG, key, Long.toString(val));
  }

  @Override
  public Zzca_01 getString(String key, String defVal) {
    Zzca_01 o = new Zzca_01();
    o.readed = false;
    o.val_string = defVal;
    o.sixz = Zzca_01.ESixz.SIXZ_STRING;
    //---
    SQLiteDatabase rdb = this.getReadableDatabase();
    Cursor cursor = rdb.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + CKEY + " = '" + key + "'", null);
    cursor.moveToFirst();
    if (cursor.getCount() > 0) {
      o.readed = true;
      o.val_string = cursor.getString(cursor.getColumnIndex(CVAL_STRING));
    }
    cursor.close();
    rdb.close();
    return o;
  }

  @Override
  public Zzca_01 getInt(String key, int defVal) {
    Zzca_01 o = new Zzca_01();
    o.readed = false;
    o.val_int = defVal;
    o.sixz = Zzca_01.ESixz.SIXZ_INT;
    //---
    SQLiteDatabase rdb = this.getReadableDatabase();
    Cursor cursor = rdb.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + CKEY + " = '" + key + "'", null);
    cursor.moveToFirst();
    if (cursor.getCount() > 0) {
      o.readed = true;
      o.val_int = cursor.getInt(cursor.getColumnIndex(CVAL_INT));
    }
    cursor.close();
    rdb.close();
    return o;
  }

  @Override
  public Zzca_01 getLong(String key, long defVal) {
    Zzca_01 o = new Zzca_01();
    o.readed = false;
    o.val_long = defVal;
    o.sixz = Zzca_01.ESixz.SIXZ_LONG;
    //---
    SQLiteDatabase rdb = this.getReadableDatabase();
    Cursor cursor = rdb.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + CKEY + " = '" + key + "'", null);
    cursor.moveToFirst();
    if (cursor.getCount() > 0) {
      o.readed = true;
      o.val_long = cursor.getLong(cursor.getColumnIndex(CVAL_LONG));
    }
    cursor.close();
    rdb.close();
    return o;
  }

  @Override
  public Zzca_01 getFloat(String key, float defVal) {
    Zzca_01 o = new Zzca_01();
    o.readed = false;
    o.val_float = defVal;
    o.sixz = Zzca_01.ESixz.SIXZ_FLOAT;
    //---
    SQLiteDatabase rdb = this.getReadableDatabase();
    Cursor cursor = rdb.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + CKEY + " = '" + key + "'", null);
    cursor.moveToFirst();
    if (cursor.getCount() > 0) {
      o.readed = true;
      o.val_float = cursor.getFloat(cursor.getColumnIndex(CVAL_FLOAT));
    }
    cursor.close();
    rdb.close();
    return o;
  }

  @Override
  public Zzca_01 getBoolean(String key, boolean defVal) {
    Zzca_01 o = new Zzca_01();
    o.readed = false;
    o.val_boolean = defVal;
    o.sixz = Zzca_01.ESixz.SIXZ_BOOLEAN;
    //---
    SQLiteDatabase rdb = this.getReadableDatabase();
    Cursor cursor = rdb.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + CKEY + " = '" + key + "'", null);
    cursor.moveToFirst();
    if (cursor.getCount() > 0) {
      o.readed = true;
      o.val_boolean = cursor.getInt(cursor.getColumnIndex(CVAL_BOOLEAN)) != 0;
    }
    cursor.close();
    rdb.close();
    return o;
  }

  @Override
  public int remove(String key) {
    SQLiteDatabase db = this.getWritableDatabase();
    int ct = db.delete(TABLE_NAME, CKEY + " = '" + key + "'", null);
    db.close();
    return ct;
  }

  /**
   * Удаление всех рядов из _таблицы ([pshb])
   *
   * @return количество удалённых рядов
   */
  @Override
  public int clear() {
    SQLiteDatabase db = this.getWritableDatabase();
    int ct = db.delete(TABLE_NAME, null, null);
    db.close();
    return ct;
  }

  @Override
  public boolean contains(String stKey) {
    SQLiteDatabase db = this.getReadableDatabase();
    //---
    String stReq = String.format("SELECT %s FROM %s WHERE %s = '%s'", CKEY, TABLE_NAME, CKEY, stKey);
    Cursor cursor = db.rawQuery(stReq, null);
    cursor.moveToFirst();
    //---
    int ct = cursor.getCount();
    cursor.close();
    db.close();
    return ct > 0;
  }

  //--- </ interface [Nmhn]

  /**
   * Выводит в лог всё содержимое _хранилища
   */
  public void printAllToLog() {
    SQLiteDatabase rdb = this.getReadableDatabase();
    Cursor cursor = rdb.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    cursor.moveToFirst();
    //---
    String s = G67G_Db.toStringCursor(cursor);
    //---
    Log.d(":G67G", "start ======== содержимое хранилища =============");
    Log.d(":G67G", s);
    Log.d(":G67G", "end =============================================");
    //---
    cursor.close();
    rdb.close();
  }

  /**
   * Ищет в хранилище запись у которой в поле {@link Gwmv_01#CKEY} значение == (2).
   * Если находит такую запись, то обновляет у неё поле (1) значением (3).
   * Если не находит, то создаёт такую запись
   *
   * @param stFielTargetdName (1) --
   * @param stFieldKeyValue   (2) --
   * @param stValue           (3) --
   * @return --
   */
  private int put(String stFielTargetdName, String stFieldKeyValue, String stValue) {
    SQLiteDatabase db = this.getWritableDatabase();
    //---
    ContentValues cv = new ContentValues();
    cv.put(CKEY, stFieldKeyValue);
    cv.put(stFielTargetdName, stValue);
    //--- если обновление будет выполнено, то вернётся число > 0
    int ct = db.update(TABLE_NAME, cv, CKEY + " = '" + stFieldKeyValue + "'", null);
    //---
    if (ct < 1) {
      //^ т.к. записи не существует, создаём новую
      db.insert(TABLE_NAME, null, cv);
    }
    //---
    db.close();
    return ct;
  }
}

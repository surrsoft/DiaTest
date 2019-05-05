package ru.surrsoft.baaz.p01_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ru.surrsoft.baaz.debug.Ycyf_01;
import ru.surrsoft.baaz.logs.Vdin_01;

/**
 * Используется при создании БД и его таблиц. При вызове конструктора, если БД ещё не существует,
 * срабатывает метод обратного вызова onCreate. Если же уже существует, но версия БД отлична от
 * уже существующей БД, то вызывается метод обратного вызова onUpdate
 */
public class DbHelper extends SQLiteOpenHelper {

  private final Tables tables;

  /**
   * Свой конструктор. Создан с целью передать параметр (5) (таблицы)
   *
   * @param context (1) --
   * @param dbName  (2) --
   * @param factory (3) --
   * @param version (4) --
   * @param tables  (4) -- таблицы для их создания в БД (2)
   */
  public DbHelper(Context context, String dbName, SQLiteDatabase.CursorFactory factory,
                  int version, Tables tables) {
    super(context, dbName, factory, version);
    Vdin_01.logStart("constructor", this);
    this.tables = tables;
    Vdin_01.logEnd("", this);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    Vdin_01.logStart("onCreate", this);

    tables.createOnDb(db);

    Vdin_01.logEnd("", this);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Vdin_01.logStart("onUpgrade", this);

    Vdin_01.logEnd("", this);

  }

  @Override
  public String toString() {
    //toString-template '[snuz]'
    return Ycyf_01.RWRY_OPEN + "DbHelper{" +
      "\n" + Ycyf_01.RWRY_INN + ":tables=" + tables +
      "\n" + Ycyf_01.RWRY_CLOSE + "}";
  }
}

package ru.surrsoft.baaz.p01_db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;

import ru.surrsoft.baaz.TypeConsts;
import ru.surrsoft.baaz.cls_c.G67G_Db;
import ru.surrsoft.baaz.debug.Ycyf_01;
import ru.surrsoft.baaz.logs.Vdin_01;
import ru.surrsoft.baaz.p01_db.b.DatabaseName;
import ru.surrsoft.baaz.p01_db.b.TableName;
import ru.surrsoft.baaz.p01_db.t_toio.DatabaseCreateRet;
import ru.surrsoft.baaz.tclasses.TArray_01;
import ru.surrsoft.baaz.univers.U;

/**
 * Инкарнация ([cbua]) базы данных
 * <p>
 * ПОНЯТИЯ:
 * -- [[ocuk]], _имя-БД, _dbname - имя БД; например "db10", "databaseTemp"
 */
public class Database {

  /**
   * _имя-БД ([ocuk])
   */
  public DatabaseName databaseName;

  /**
   * Абсолютный путь к БД ([vtpf])
   */
  private DatabasePath databasePath;

  /**
   * Верия БД. Если версия будет больше уже существующей, то будет вызываться метод onUpgrade класса расширяющего
   * абстрактный класс SQLiteOpenHelper
   */
  private DatabaseVersion databaseVersion;

  /**
   * Реинкарнации ([iwsc]) таблиц БД
   */
  private Tables tables;

  /**
   * Контекст
   */
  private Context ctx;


  //--- constructors
  public Database(Context ctx, DatabaseName databaseName) {
    this.ctx = ctx;
    //---
    this.databaseName = databaseName;
    this.databasePath = new DatabasePath(databaseName, ctx);
    //---
  }

  public Database buVersion(DatabaseVersion databaseVersion) {
    this.databaseVersion = databaseVersion;
    return this;
  }

  public Database buTables(Tables tables) {
    this.tables = tables;
    tables.buDatabase(this);
    return this;
  }

  /**
   * Удаление текущей БД.
   * Вместе с текущей БД удаляется так же соответствующая "-journal"-БД
   *
   * @return --
   */
  public TypeConsts.EKuuz remove() {
    Vdin_01.logStart("удаление БД [" + databaseName.getName() + "]", this);
    if (isExist()) {
      ctx.deleteDatabase(databaseName.getName());
      Vdin_01.logM("удалена", this);
      Vdin_01.logEnd("", this);
      return TypeConsts.EKuuz.DONE;
    } else {
      Vdin_01.logM("не удалена т.к. не существует", this);
      Vdin_01.logEnd("", this);
      return TypeConsts.EKuuz.NOT_EXIST;
    }
  }


  //---

  /**
   * [[ivny]]
   */
  public enum EIvny {
    /**
     * Если БД была успешно создана
     */
    SUCCESS_CREATE,
    /**
     * Если никаких действий не производилось т.к. БД уже существует
     */
    ALREADY_EXISTS
  }

  /**
   * Создаёт себя (т.е БД). Если БД с таким именем уже сущетсвует то заполняет список таблиц и
   * больше ничего не делает
   *
   * @return информация о результате работы метода
   */
  public DatabaseCreateRet create() {
    U.se(databaseName == null, "не указано имя БД");
    U.se(databaseVersion == null, "не указана версия БД");
    //---
    if (isExist()) { //если БД уже сущетсвует
      //--- наполняем список таблиц _реинкарнациями существующих таблиц
      tables = tablesGet();
      //---
      return new DatabaseCreateRet(EIvny.ALREADY_EXISTS, "", this, true);
    } else {
      DbHelper dbHelper = new DbHelper(
        ctx,
        databaseName.getName(),
        null,
        databaseVersion.getVersion(),
        tables
      );
      //--- следующие инструкции нужны чтобы БД создалась прямо сейчас, а не при первом обращении
      // к ней
      dbHelper.getReadableDatabase();
      dbHelper.close();
      //--
      return new DatabaseCreateRet(EIvny.SUCCESS_CREATE, "", this, true);
      //---
    }
  }

  /**
   * Отличается от А способом создания БД
   *
   * @return --
   */
  public DatabaseCreateRet create_B() {
    U.se(databaseName == null, "");
    U.se(databasePath == null, "");
    Vdin_01.logStart("создание БД; [" + databaseName.getName() + "] //190304-104400", this);
    //---
    if (isExist()) { //если БД уже существует
      Vdin_01.logM("не создаём, найдена уже существующая БД", this);
      //--- наполняем список таблиц _реинкарнациями существующих таблиц
      tables = tablesGet();
      //---
      Vdin_01.logEnd("", this);
      return new DatabaseCreateRet(EIvny.ALREADY_EXISTS, "", this, true);
    } else {
      //--- создание БД если она ещё не существует
      SQLiteDatabase db = SQLiteDatabase.openDatabase(databasePath.getPath(), null, SQLiteDatabase.CREATE_IF_NECESSARY);
      Vdin_01.logM("БД создана", this);
      //---
      buVersion(new DatabaseVersion(db.getVersion()));
      //---
      if (tables != null) {
        tables.buDatabase(this);
        tables.create();
      }
      Vdin_01.logEnd("", this);
      return new DatabaseCreateRet(EIvny.SUCCESS_CREATE, "", this, true);
    }
  }

  /**
   * Возвращает TRUE если текущая реинкарнация Db существует в приложении.
   * Поиск БД выполняется по _имени-БД (dbName.getName()).
   *
   * @return TRUE если существует, иначе FALSE
   */
  public boolean isExist() {
    U.se(databaseName == null, "не указано имя БД");
    //---
    return TArray_01.arrToList(ctx.databaseList()).contains(databaseName.getName());
  }

  /**
   * Получение объекта "только для чтения" SQLiteDatabase текущей БД
   *
   * @return null если БД не существует и в других случаях
   */
  public SQLiteDatabase dbGetReadOnly() {
    U.se(databasePath == null, "требуется путь к БД");
    try {
      return SQLiteDatabase.openDatabase(databasePath.getPath(), null, SQLiteDatabase.OPEN_READONLY);
    } catch (SQLiteException e) {
      return null;
    }
  }

  /**
   * Получение объекта "чтение и запись" SQLiteDatabase текущей БД
   *
   * @return null если БД не существует и в других случаях
   */
  public SQLiteDatabase dbGetReadWrite() {
    U.se(databasePath == null, "требуется путь к БД");
    try {
      return SQLiteDatabase.openDatabase(databasePath.getPath(), null, SQLiteDatabase.OPEN_READWRITE);
    } catch (SQLiteException e) {
      return null;
    }
  }


  /**
   * Получение _реинкарнации всех реально существующих таблиц текущей БД, если текущая БД существует.
   * Если БД не существует, то возвращается пустой список
   *
   * @return not null
   */
  public Tables tablesGet() {
    Tables ret = new Tables(ctx);
    //---
    if (isExist()) {
      SQLiteDatabase db = SQLiteDatabase.openDatabase(
        databasePath.getPath(),
        null,
        SQLiteDatabase.OPEN_READONLY
      );
      //--- курсор мастер-таблицы sqlite_master
      Cursor cursor = db.rawQuery("SELECT * FROM sqlite_master", null);
      cursor.moveToFirst();
      //---
      if (cursor.getCount() > 0) {
        //--- получение списка имён таблиц (tableNames)
        ArrayList<String> listTableNames = new ArrayList<>();
        String stConfType = "type";
        do {
          String stType = cursor.getString(cursor.getColumnIndex(stConfType));
          String stTableName = cursor.getString(cursor.getColumnIndex("tbl_name"));
          //---
          if (stType.equals("table")) {
            listTableNames.add(stTableName);
          }
        } while (cursor.moveToNext());
        //--- таблицы
        for (String stTableName : listTableNames) { //LOOP
          Table table = new Table(new TableName(stTableName))
            .buDatabase(this);
          //--- поля таблицы
          table.buFields(table.fieldsGet());
          //---
          ret.buAddTable(table);
        } //LOOP
      }
      cursor.close();
      //---
    }
    return ret;
  }

  /**
   * Выводит в лог информацию из таблицы sqlite_master текущей БД
   *
   * @param stPrefix (1) --
   */
  public void infoSqliteMasterToLog(String stPrefix) {
    String stDbName = (databaseName == null ? "" : databaseName.getName());
    if (isExist()) {
      SQLiteDatabase db = dbGetReadOnly();
      //---
      Cursor cursor1 = db.rawQuery("SELECT * FROM sqlite_master", null);
      cursor1.moveToFirst();
      G67G_Db.cursorToLog(cursor1, "db name [" + stDbName + "]: " + stPrefix);
      cursor1.close();
    } else {
      Log.d("tag", stPrefix + ": таблица по имени [" + stDbName + "] не найдена");
    }
  }

  public DatabasePath getDatabasePath() {
    return databasePath;
  }

  @Override
  public String toString() {
    //toString-template '[snuz]'
    return Ycyf_01.RWRY_OPEN + "Database{" +
      "\n" + Ycyf_01.RWRY_INN + ":databaseName=" + databaseName +
      "\n" + Ycyf_01.RWRY_INN + ":databasePath=" + databasePath +
      "\n" + Ycyf_01.RWRY_INN + ":databaseVersion=" + databaseVersion +
      "\n" + Ycyf_01.RWRY_INN + ":tables=" + tables +
      "\n" + Ycyf_01.RWRY_INN + ":ctx=" + ctx +
      "\n" + Ycyf_01.RWRY_CLOSE + "}";
  }
}

package ru.surrsoft.baaz.p01_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import ru.surrsoft.baaz.debug.Ycyf_01;
import ru.surrsoft.baaz.p01_db.b.DatabaseName;
import ru.surrsoft.baaz.p01_db.t_toio.DatabaseCreateRet;
import ru.surrsoft.baaz.p02_terms.EFspk;
import ru.surrsoft.baaz.tclasses.TArray_01;
import ru.surrsoft.baaz.tclasses.TString_01;
import ru.surrsoft.baaz.univers.U;

/**
 * Инкарнация нескольких баз данных
 */
public class Databases {

  private final Context ctx;
  //количество БД
  private int iDbCount;
  //список имён БД
  private String[] arrStDbNames;
  //список баз данных
  private ArrayList<Database> listDatabases = new ArrayList<>();

  /**
   * При создании поле {@link #listDatabases} заполняется всеми БД существующими в приложении (так же
   * именами БД заполняется поле {@link #arrStDbNames} )
   *
   * @param ctx (1) --
   */
  public Databases(Context ctx) {
    U.se(ctx == null, "(debug)");
    //---
    this.ctx = ctx;
  }

  /**
   * [apck]-операция ("подтягивание" существующих БД)
   */
  public EFspk pull() {
    return mtListDatabasesPopulate();
  }

  /**
   * [[vsbg]]
   */
  public enum EVsbg {
    /**
     * Все БД успешно созданы
     */
    SUCCESS,
    /**
     * Не указаны БД которые нужно создать
     */
    NOT_DATABASES
  }

  /**
   * Класс представляющий результат создания баз данных
   */
  public static class DatabasesCreateRet {
    public EVsbg eVsbg;
    public ArrayList<Database.EIvny> ivnys = new ArrayList<>();
  }

  /**
   * [fphn]-операция (содание баз данных на устройстве)
   *
   * @return -- если во время создания баз данных возникали проблемы, список ivnys будет иметь
   * длину > 0
   */
  public DatabasesCreateRet create() {
    DatabasesCreateRet ret = new DatabasesCreateRet();
    if (TArray_01.isFillNot(listDatabases)) {
      ret.eVsbg = EVsbg.NOT_DATABASES;
      return ret;
    }
    //---
    for (Database database : listDatabases) {
      DatabaseCreateRet dcr = database.create_B();
      if (dcr.eivny != Database.EIvny.SUCCESS_CREATE) {
        ret.ivnys.add(dcr.eivny);
      }
    }
    //---
    if (ret.ivnys.size() < 1) {
      ret.eVsbg = EVsbg.SUCCESS;
    }
    return ret;
  }

  /**
   * Получение реинкарнаций ([iwsc]) всех БД приложения.
   * Эти реинкарнации содержат следующую информацию:
   * - _имя-БД ([ocuk]);
   */
  private EFspk mtListDatabasesPopulate() {
    EFspk ret = EFspk.NOT_EXIST;
    //--- имена всех баз данных приложения
    String[] stDbNames = ctx.databaseList();
    //--- sort
    if (stDbNames.length > 1) {
      TString_01.sortStringArray(stDbNames);
      ret = EFspk.EXIST_ONE;
    }
    //---
    arrStDbNames = stDbNames;
    //---
    iDbCount = stDbNames.length;
    //---
    for (String stDbName : stDbNames) {
      Database database = new Database(ctx, new DatabaseName(stDbName));
      //--- чтобы заполнились таблицы
      database.create_B();
      //--- получение объекта БД
      SQLiteDatabase db = database.dbGetReadOnly();
      //--- версия БД
      database.buVersion(new DatabaseVersion(db.getVersion()));
      //---
      listDatabases.add(database);
    }
    return ret;
  }

  /**
   * Добавление БД
   *
   * @param database (1) --
   * @return --
   */
  public Databases buAddDatabase(Database database) {
    U.se(database == null, "");
    listDatabases.add(database);
    return this;
  }

  @Override
  public String toString() {
    //toString-template '[snuz]'
    return Ycyf_01.RWRY_OPEN + "Databases{" +
      "\n" + Ycyf_01.RWRY_INN + ":ctx=" + ctx +
      "\n" + Ycyf_01.RWRY_INN + ":iDbCount=" + iDbCount +
      "\n" + Ycyf_01.RWRY_INN + ":arrStDbNames=" + Ycyf_01.toStringArr(arrStDbNames) +
      "\n" + Ycyf_01.RWRY_INN + ":listDatabases=" + Ycyf_01.toStringList(listDatabases) +
      "\n" + Ycyf_01.RWRY_CLOSE + "}";
  }
}

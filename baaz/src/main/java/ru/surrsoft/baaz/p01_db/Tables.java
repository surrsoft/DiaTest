package ru.surrsoft.baaz.p01_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import ru.surrsoft.baaz.debug.Ycyf_01;
import ru.surrsoft.baaz.logs.Vdin_01;
import ru.surrsoft.baaz.tclasses.TArray_01;
import ru.surrsoft.baaz.univers.U;

/**
 * Инкарнация таблиц БД
 */
public class Tables {
  private final Context ctx;

  private ArrayList<Table> listTables = new ArrayList<>();

  private Database database;

  public Tables(Context ctx) {
    U.se(ctx == null, "");
    this.ctx = ctx;
  }

  /**
   * @param listTables (1) -- null или пустой список означает пустую БД, без таблиц
   * @return --
   */
  public Tables buAddTables(ArrayList<Table> listTables) {
    U.se(listTables == null, "");
    for (Table table : listTables) {
      table.buDatabase(database);
    }
    this.listTables.addAll(listTables);
    return this;
  }

  public Tables buAddTable(Table table) {
    U.se(table == null, "");
    U.se(!mtTablesNamesVefify(table), "повтор имени таблицы; [" + table.getTableName().getName() + "]");
    //---
    table.buDatabase(database);
    listTables.add(table);
    return this;
  }

  /**
   * Возвращает TRUE если среди таблиц списка {@link #listTables} нет таблицы с именем (1)
   *
   * @param table (1) --
   * @return --
   */
  private boolean mtTablesNamesVefify(Table table) {
    for (Table table0 : listTables) {
      if (table0.getTableName().getName().equals(table.getTableName().getName())) {
        return false;
      }
    }
    return true;
  }

  public Tables buDatabase(Database database) {
    this.database = database;
    for (Table table : listTables) {
      table.buDatabase(database);
    }
    return this;
  }

  /**
   * Создаёт таблицы в БД (1)
   * <p>
   * #errors - если db = null; при ошибке создание одной из таблиц
   *
   * @param db (1)
   */
  public void createOnDb(SQLiteDatabase db) {
    Vdin_01.logStart("создание таблиц //190219-160200", this);
    U.se(db == null, "не указана БД");
    //---
    if (TArray_01.isFill(listTables)) {
      for (Table table : listTables) {
        Table.TableCreateRet ret = table.createOnDb(db);
        U.se(
          ret.eTableCreateResult != Table.ETableCreateResult.SUCCESS_CREATE,
          "ошибка при создании таблицы [" + ret.eTableCreateResult + "]"
        );
      }
    } else {
      Vdin_01.logM("ВНИМАНИЕ: пустой список таблиц", this);
    }
    Vdin_01.logEnd("", this);
  }

  /**
   * Создаёт таблицы в БД {@link #database}
   */
  public void create() {
    U.se(database == null, "");
    U.se(!database.isExist(), "");
    SQLiteDatabase db = database.dbGetReadWrite();
    createOnDb(db);
  }

  public ArrayList<Table> getListTables() {
    return listTables;
  }

  @Override
  public String toString() {
    //toString-template '[snuz]'
    return Ycyf_01.RWRY_OPEN + "Tables{" +
      "\n" + Ycyf_01.RWRY_INN + ":ctx=" + ctx +
      "\n" + Ycyf_01.RWRY_INN + ":listTables=" + Ycyf_01.toStringList(listTables) +
      "\n" + Ycyf_01.RWRY_INN + ":database=..." +
      "\n" + Ycyf_01.RWRY_CLOSE + "}";
  }
}

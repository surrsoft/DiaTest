package ru.surrsoft.baaz.p01_db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.apache.commons.lang3.StringUtils;

import ru.surrsoft.baaz.debug.Ycyf_01;
import ru.surrsoft.baaz.p01_db.b.FieldName;
import ru.surrsoft.baaz.p01_db.b.TableName;
import ru.surrsoft.baaz.tclasses.TString_01;
import ru.surrsoft.baaz.univers.U;

/**
 * Инкарнирует таблицу БД
 */
public class Table {


  /**
   * Имя таблицы
   */
  private TableName tableName;

  /**
   * БД которой принадлежит текущая таблица
   */
  private Database database;

  /**
   * Поля таблицы
   */
  private Fields fields;

  /**
   * Ряды таблицы
   */
  private Rows rows;

  //--- constructors
  public Table(TableName tableName) {
    U.se(tableName == null, "");
    //---
    this.tableName = tableName;
  }

  //--- builders
  public Table buFields(Fields fields) {
    this.fields = fields;
    return this;
  }

  public Table buDatabase(Database database) { //??? не лучше ли передавать имя БД?
    this.database = database;
    return this;
  }

  public Table buRows(Rows rows) {
    this.rows = rows;
    rows.buTable(this);
    return this;
  }

  /**
   * Выполняет запрос "PRAGMA table_info" на БД {@link #database},
   * и из полученного курсора извлекает информацию о полях возвращаяя её в виде объекта {@link Fields}
   * <p>
   * #errors если tableName == null, database == null, database не существует, таблицы текущей не существует
   *
   * @return --
   */
  public Fields fieldsGet() {
    U.se(tableName == null, "");
    U.se(database == null, "");
    U.se(!database.isExist(), "");
    U.se(!isExist(), "");
    //---
    Fields ret = new Fields();
    //---
    SQLiteDatabase db = database.dbGetReadOnly();
    Cursor cursor = db.rawQuery("PRAGMA table_info(" + tableName.getName() + ")", null);
    cursor.moveToFirst();
    //---
    do { //LOOP
      String stFieldName = cursor.getString(cursor.getColumnIndex("name"));
      String stFielType = StringUtils.lowerCase(cursor.getString(cursor.getColumnIndex("type")));
      int iPrimaryKey = cursor.getInt(cursor.getColumnIndex("pk"));
      //---
      Field field = new Field()
        .buName(new FieldName(stFieldName))
        .buFieldType(stFielType)
        .buPrimaryKeyAutoincrement(iPrimaryKey == 1); //??? ввобще то про автоинкремент речи не идёт
      //---
      ret.buAddField(field);
      //---
    } while (cursor.moveToNext()); //LOOP
    //---
    cursor.close();
    return ret;
  }

  //--- </ builders

  /**
   * Представляет результат работы метода {@link #createOnDb(SQLiteDatabase)} и {@link #create()}
   */
  public enum ETableCreateResult {
    /**
     * Таблица успешно создана
     */
    SUCCESS_CREATE,
    /**
     * Не указано имя таблицы
     */
    NOT_NAME,
    /**
     * Таблица с указанным именем в указанной БД уже существует
     */
    ALREADY_EXISTS,
    /**
     * Указанная БД == null
     */
    DB_NULL,
    /**
     * Указанная БД не найдена
     */
    DB_NOT_EXIST
  }

  /**
   * Представляет результат работы метода {@link #createOnDb(SQLiteDatabase)}
   */
  public static class TableCreateRet {
    public ETableCreateResult eTableCreateResult;
    public String comment;

    public TableCreateRet(ETableCreateResult eret, String comment) {
      this.eTableCreateResult = eret;
      this.comment = comment;
    }

    @Override
    public String toString() {
      return "Ret{" +
        "eTableCreateResult=" + eTableCreateResult +
        ", comment='" + comment + '\'' +
        '}';
    }
  }

  /**
   * Создаёт таблицу (себя) в БД {@link #database}
   *
   * @return информация о результате работы текущего метода
   */
  public TableCreateRet create() {
    if (tableName == null) {
      return new TableCreateRet(ETableCreateResult.NOT_NAME, "");
    }
    if (database == null) {
      return new TableCreateRet(ETableCreateResult.DB_NULL, "");
    }
    if (!database.isExist()) {
      return new TableCreateRet(ETableCreateResult.DB_NOT_EXIST, "");
    }
    if (isExist()) {
      return new TableCreateRet(ETableCreateResult.ALREADY_EXISTS, "");
    }
    //---
    return createOnDb(database.dbGetReadWrite());
  }

  /**
   * Создаёт себя (таблицу) в БД (1)
   *
   * @param db (1)
   * @return информация о результате работы текущего метода
   */
  public TableCreateRet createOnDb(SQLiteDatabase db) {
    //--- проверки
    if (db == null) {
      return new TableCreateRet(ETableCreateResult.DB_NULL, "");
    }
    if (tableName == null) {
      return new TableCreateRet(ETableCreateResult.NOT_NAME, "");
    }
    if (isExist(db)) {
      return new TableCreateRet(ETableCreateResult.ALREADY_EXISTS, "таблица по имени [" + tableName.getName() + "] уже существуте");
    }
    //---
    String s = "";
    if (fields != null && fields.getFields().size() > 0) {
      s = TString_01.join(fields.getFields(), ", ", Field::createZvsu);
      if (s.length() > 0) {
        s = " (" + s + ")";
      }
    }
    //---
    String sql = "CREATE TABLE " + tableName.getName() + s + ";";
    db.execSQL(sql);
    //--- --- создание рядов
    if (rows != null) {
      rows.create();
    }
    //---
    return new TableCreateRet(ETableCreateResult.SUCCESS_CREATE, "");
  }

  /**
   * Возвращает TRUE если текущая таблица существует в БД (1)
   *
   * @param db (1) --
   * @return --
   */
  public boolean isExist(SQLiteDatabase db) {
    boolean ret = false;
    //---
    Cursor c = db.rawQuery("SELECT * FROM sqlite_master", null);
    c.moveToFirst();
    if (c.getCount() > 0) {
      do { //LOOP
        String s = c.getString(c.getColumnIndex("name"));
        if (tableName.getName().equals(s)) {
          ret = true;
          break;
        }
      } while (c.moveToNext()); //LOOP
    }
    c.close();
    //---
    return ret;
  }

  /**
   * Возвращает TRUE если текущая таблица существует в БД {@link #database}
   * <p>
   * #errors если tableName == null или database == null или если database не существует
   *
   * @return --
   */
  public boolean isExist() {
    U.se(tableName == null, "");
    U.se(database == null, "");
    U.se(!database.isExist(), "");
    //---
    return isExist(database.dbGetReadOnly());
  }

  public TableName getTableName() {
    return tableName;
  }

  public Database getDatabase() {
    return database;
  }

  @Override
  public String toString() {
    //toString-template '[snuz]'
    return Ycyf_01.RWRY_OPEN + "Table{" +
      "\n" + Ycyf_01.RWRY_INN + ":tableName=" + tableName +
      "\n" + Ycyf_01.RWRY_INN + ":database=..." +
      "\n" + Ycyf_01.RWRY_INN + ":fields=" + fields +
      "\n" + Ycyf_01.RWRY_CLOSE + "}";
  }
}

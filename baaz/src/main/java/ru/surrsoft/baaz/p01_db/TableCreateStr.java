package ru.surrsoft.baaz.p01_db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

import ru.surrsoft.baaz.debug.Ycyf_01;
import ru.surrsoft.baaz.p01_db.b.TableName;
import ru.surrsoft.baaz.p02_terms.EFspk;
import ru.surrsoft.baaz.p02_terms.Incarnation;
import ru.surrsoft.baaz.tclasses.TString_01;
import ru.surrsoft.baaz.univers.U;

/**
 * Инкарнация [yzpm]-инструкции (строки)
 * <p>
 * ^[[yzpm]] - это SQL-инструкция с помощью которой таблица была созадана.
 * Например "CREATE TABLE stats (keyfieldx integer primary key autoincrement,  textfieldx text, field1 text )"
 */
public class TableCreateStr extends Incarnation {
  private String stYzpm;
  private String stTableName;
  private ArrayList<FieldCreateStr> fieldCreateStrs;

  public TableCreateStr(String stYzpm) {
    U.se(!isCorrect(stYzpm), "");
    //---
    init(stYzpm);
  }

  /**
   * Через (1) делает обращение к таблице sqlite_master, ищет в ней запись относящуюсья к таблице
   * (2), извлекает из этой записи [yzpm]-строку на базе которой формирует результат. Если таблицы
   * (2) в БД (1) не обнаруживается, возвращает null
   * <p>
   * В {@link #cbuaExist} пишется либо {@link EFspk#EXIST_ONE} либо {@link EFspk#NOT_EXIST} если
   * таблицы по имени (2) нет в БД (1)
   *
   * #errors - если (1) == null; если некорректное имя (2)
   *
   * @param db        (1) --
   * @param tableName (2) --
   */
  public static TableCreateStr createOnDb(SQLiteDatabase db, TableName tableName) {
    U.se(db == null, "");
    //---
    String yzpm = "";
    //--- извлекаем [yzpm] по имени таблицы
    Cursor c = db.rawQuery("SELECT * FROM sqlite_master", null);
    c.moveToFirst();
    if (c.getCount() > 0) {
      do {
        String tableName2 = c.getString(c.getColumnIndex("name"));
        if (tableName.getName().equals(tableName2)) {
          yzpm = c.getString(c.getColumnIndex("sql"));
          break;
        }
      } while (c.moveToNext());
    }
    c.close();
    //---
    return StringUtils.isBlank(yzpm) ? null : new TableCreateStr(yzpm);
  }

  private void init(String stYzpm) {
    fieldCreateStrs = new ArrayList<>();
    this.stYzpm = stYzpm;
    mtResolve(stYzpm);
  }

  public boolean isCorrect(String stYzpm) {
    if (!StringUtils.isBlank(stYzpm)) {
      String st = stYzpm;
      st = TString_01.normalize(st);
      st = StringUtils.lowerCase(st);
      //---
      int ix = st.indexOf("create table");
      return ix != -1;
    }
    return false;
  }

  public void mtResolve(String stYzpm) {
    String st = stYzpm;
    st = TString_01.normalize(st);
    st = StringUtils.lowerCase(st); //переводим в нижний регистр
    int i = st.indexOf("(");
    //--- выделение имени таблицы
    String s = "table";
    int i2 = st.indexOf(s);
    stTableName = st.substring(i2 + s.length() + 1, i);
    stTableName = TString_01.normalize(stTableName);
    //---
    st = st.substring(i + 1); //всё что после скобки "("
    st = TString_01.replaceAll(st, ")", ""); //убираем правую скобку
    st = TString_01.normalize(st);
    st = TString_01.replaceAll(st, ", ", ","); //убираем пробелы после запятых
    st = TString_01.replaceAll(st, " ,", ","); //убираем пробелы перед запятыми
    //---
    String[] zvsus = StringUtils.split(st, ",");
    //---
    for (String zvsu : zvsus) { //LOOP
      FieldCreateStr fieldCreateStr = new FieldCreateStr(zvsu);
      fieldCreateStrs.add(fieldCreateStr);
    } //LOOP
    //---
  }

  @Override
  public String toString() {
    //toString-template '[snuz]'
    return Ycyf_01.RWRY_OPEN + "TableCreateStr{" +
      "\n" + Ycyf_01.RWRY_INN + ":stYzpm='" + stYzpm + '\'' +
      "\n" + Ycyf_01.RWRY_INN + ":stTableName='" + stTableName + '\'' +
      "\n" + Ycyf_01.RWRY_INN + ":fieldCreateStrs=" + Ycyf_01.toStringList(fieldCreateStrs) +
      "\n" + Ycyf_01.RWRY_CLOSE + "}";
  }
}

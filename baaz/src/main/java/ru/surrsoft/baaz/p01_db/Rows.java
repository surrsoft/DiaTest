package ru.surrsoft.baaz.p01_db;

import java.util.ArrayList;

import ru.surrsoft.baaz.tclasses.TArray_01;
import ru.surrsoft.baaz.univers.U;

/**
 * Инкарнация нескольких рядов таблицы
 */
public class Rows {

  /**
   * Таблица к которой относятся ряды
   */
  private Table table;

  private ArrayList<Row> listRows = new ArrayList<>();

  public Rows() {
  }

  public Rows buAddRow(Row row) {
    U.se(row == null, "");
    listRows.add(row);
    row.buTable(table);
    return this;
  }

  public Rows buAddRows(ArrayList<Row> rows) {
    listRows.addAll(rows);
    for (Row row : rows) {
      row.buTable(table);
    }
    return this;
  }

  public Rows buTable(Table table) {
    this.table = table;
    for (Row row : listRows) {
      row.buTable(table);
    }
    return this;
  }

  public void create() {
    if (TArray_01.isFill(listRows)) {
      for (Row row : listRows) {
        row.create();
      }
    }
  }
}

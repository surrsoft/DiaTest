package ru.surrsoft.baaz.p01_db;

import ru.surrsoft.baaz.univers.U;

/**
 * Инкарнация ряда таблицы
 */
public class Row {

  private Table table;

  private Cells cells;

  public Row() {
  }

  public Row buAddCells(Cells cells) {
    U.se(cells == null, "");
    this.cells = cells;
    cells.buRow(this);
    return this;
  }

  public Row buTable(Table table) {
    this.table = table;
    return this;
  }

  public void create() {
    if (cells != null) {
      cells.create();
    }
  }

  public Table getTable() {
    return table;
  }
}

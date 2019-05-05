package ru.surrsoft.baaz.p01_db;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import ru.surrsoft.baaz.tclasses.TArray_01;
import ru.surrsoft.baaz.univers.U;

/**
 * Инкарнация нескольких ячеек
 */
public class Cells {

  private Row row;

  private ArrayList<Cell> listCells = new ArrayList<>();

  public Cells() {
  }

  public Cells buAddCell(Cell cell) {
    U.se(cell == null, "");
    //---
    cell.buRow(row);
    listCells.add(cell);
    return this;
  }

  public Cells buAddCells(ArrayList<Cell> cells) {
    U.se(cells == null, "");
    for (Cell cell : cells) {
      cell.buRow(row);
    }
    listCells.addAll(cells);
    return this;
  }

  public Cells buRow(Row row) {
    this.row = row;
    for (Cell cell : listCells) {
      cell.buRow(row);
    }
    return this;
  }

  public void create() {
    //---
    if (TArray_01.isFill(listCells)) {
      ArrayList<String> listFieldNames = new ArrayList<>();
      ArrayList<String> listValues = new ArrayList<>();
      //---
      for (Cell cell : listCells) { //LOOP
        Field field = cell.getField();
        String stValue = cell.getValue();
        if (field != null && stValue != null && stValue.length() > 0) {
          listFieldNames.add(field.getFieldName().getName());
          listValues.add("'" + stValue + "'");
        }
      } //LOOP
      //---
      if (listFieldNames.size() > 0) {
        String st0 = "(" + TArray_01.join_A(listFieldNames, ", ") + ") " +
          " VALUES (" + TArray_01.join_A(listValues, ", ") + ") ";
        String st1 = "INSERT INTO " + row.getTable().getTableName().getName() + " " + st0 + ";";
        //---
        SQLiteDatabase db = row.getTable().getDatabase().dbGetReadWrite();
        db.execSQL(st1);
      }
    }
  }
}

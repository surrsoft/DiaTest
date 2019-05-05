package ru.surrsoft.baaz.widgets.recyclerview;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.apache.commons.lang3.ArrayUtils;

import ru.surrsoft.baaz.cls_c.G67G_Db;
import ru.surrsoft.baaz.tclasses.TArray_01;
import ru.surrsoft.baaz.univers.Uoj;

/**
 * Класс-помошник для работы со схемой хранения иерархических структур данных [w252w]
 * <p/>
 * См. описание в Trooget
 * <p/>
 * #version 1 28.05.2016  #id [[w253w]]
 */
public class N2253_DbTechnicW252W {


  private final SQLiteOpenHelper _db;
  public String _table;
  private final String _fieldID;
  private final String _fieldP;
  private final String _addingWhere;
  private final String _fieldO;
  private final boolean mode1_isSimple;
  private final boolean mode1_isNormal;
  private final String mExtendContidion;

  /**
   * @param _db           (1) --
   * @param _table        (2) --
   * @param _fieldID      (3) --
   * @param _fieldP       (4) --
   * @param _addingWhere  (5) -- доп. условие для элемента WHERE запросов
   * @param _fieldO       (6) --
   * @param _isSimpleMode (7) -- в упрощенном режиме не задействуется поле {@link #_fieldP},
   *                      не задействуется (5)
   */
  public N2253_DbTechnicW252W(SQLiteOpenHelper _db, String _table,
                              String _fieldID, String _fieldP, String _addingWhere,
                              String _fieldO, boolean _isSimpleMode) {
    this._db = _db;
    this._table = _table;
    this._fieldID = _fieldID;
    this._fieldP = _fieldP;
    this._addingWhere = _addingWhere;
    this._fieldO = _fieldO;
    mode1_isSimple = _isSimpleMode;
    mode1_isNormal = !mode1_isSimple;
    if (mode1_isNormal) {
      mExtendContidion = (_addingWhere == null || _addingWhere.length() < 1) ? "" :
        " AND (" + _addingWhere + ") ";
    } else {
      mExtendContidion = "";
    }
  }

  /**
   * Получение количества _потомков _элемента (1)
   *
   * @param _idElem (1) -- первичный ключ _элемента _потомков которого нужно найти
   * @return 0 если _потомки не найдены
   */
  public int getDescendantCount(long _idElem) {
    //Log2_01.i(TAG, "--> getDescendantCount(); _idElem = " + _idElem, LOG2);
    int ret = 0;
    long[] ids = getChildIDs(_idElem);
    ret = ids.length;
    for (long elem : ids) {
      ret += getDescendantCount(elem);
    }
    return ret;
  }

  /**
   * Получение списка первичных ключей всех _потомков _элемента (1)
   *
   * @param _idElem (1) -- первичный ключ _элемента _потомков которого нужно найти
   * @return 0 если _потомки не найдены
   */
  public long[] getDescendantIDs(long _idElem) {
    //Log2_01.i(TAG, "--> getDescendantIDs(); _idElem = " + _idElem, LOG2);
    long[] ret = {};
    long[] ids = getChildIDs(_idElem);
    if (ids.length > 0) ret = ArrayUtils.addAll(ret, ids);
    for (long elem : ids) {
      long[] ids2 = getDescendantIDs(elem);
      if (ids2.length > 0) ret = ArrayUtils.addAll(ret, ids2);
    }
    return ret;
  }

  /**
   * Получение списка первичных ключей всех _предков _элемента (1)
   *
   * @param _idElem (1) -- первичный ключ _элемента _предков которого нужно найти
   * @return 0 если _предки не найдены
   */
  public long[] getParentgrandIDs(long _idElem) {
    long[] ret = {};
    long id = _idElem;
    do {
      id = getParentID(id);
      if (id > -1)
        ret = ArrayUtils.add(ret, id);
    } while (id > -1);
    return ret;
  }


  /**
   * Удаление _элемента (1) и всех его _потомков
   *
   * @param _idElem (1) -- первичный ключ удаляемого элемента
   * @return количество удаленных _потомков
   */
  public int deleteElemAndDescendants(long _idElem) {

    long[] ids = {_idElem};
    String ids3;
    if (mode1_isNormal) {
      ids = getDescendantIDs(_idElem);
      ids = ArrayUtils.add(ids, _idElem);
      String[] ids2 = TArray_01.toStringArr(ids);
      ids3 = TArray_01.arrToString_B(ids2, ",");
    } else {
      ids3 = _idElem + "";
    }

    SQLiteDatabase db = _db.getWritableDatabase();

    String sql = String.format("(%s in (%s)) %s", _fieldID, ids3, mExtendContidion);
    db.delete(_table, sql, null);
    db.close();

    return ids.length;
  }

  /**
   * Получение первичных ключей _элементов являющихся _детьми _элемента (1)
   *
   * @param _parentID (1) -- _элемент чьих _детей нужно найти
   * @return пустой массив если не находит детей
   */
  public long[] getChildIDs(long _parentID) {
    long[] ret = {};
    SQLiteDatabase db = _db.getReadableDatabase();
    String sql = String.format("SELECT %s FROM %s WHERE ((%s = %s) %s)",
      _fieldID, _table, _fieldP, _parentID, mExtendContidion);
    Cursor cursor = db.rawQuery(sql, null);
    cursor.moveToFirst();
    if (cursor.getCount() > 0) {
      long val;
      do {
        val = cursor.getLong(cursor.getColumnIndex(_fieldID));
        ret = ArrayUtils.add(ret, val);
      } while (cursor.moveToNext());
    }
    cursor.close();
    db.close();
    return ret;
  }

  /**
   * Получение первичного ключа _элемента являющегося _родителем для _элемента (1)
   *
   * @param _idElem (1) -- первичный ключ элемента
   * @return -1 если не находит (1)
   */
  public long getParentID(long _idElem) {
    long ret = -1;
    SQLiteDatabase db = _db.getReadableDatabase();
    String sql = String.format("SELECT %s FROM %s WHERE ((%s = %s) %s)",
      _fieldP, _table, _fieldID, _idElem, mExtendContidion);
    Cursor cursor = db.rawQuery(sql, null);
    cursor.moveToFirst();
    if (cursor.getCount() > 0) {
      do {
        ret = cursor.getLong(cursor.getColumnIndex(_fieldP));
      } while (cursor.moveToNext());
    }
    cursor.close();
    db.close();
    return ret;
  }

  /**
   * Изменяет _порядок элемента с первичным ключем (1) в соответствии с (2), сдвигая при этом _порядок
   * других _элементов если нужно
   *
   * @param _idPosFrom (1) -- первичный ключ элемента _порядок которого нужно изменить
   * @param _idPosTo   (2) -- первичный ключ опорного _элемента
   * @param parentId   (3) -- первичный ключ текущего родителя
   */
  public void changeOrder(long _idPosFrom, long _idPosTo, long parentId) {

    if (_idPosFrom == _idPosTo) return;
    int orderTo = getOrder(_idPosTo);
    int orderFrom = getOrder(_idPosFrom);
    if (orderTo < orderFrom) { //перемещение _элемента выше
      incrementOrderMulti(orderTo, orderFrom, parentId);
    } else { //перемещение _элемента ниже
      decrementOrderMulti(orderTo, orderFrom, parentId);
    }
    updateOrder(_idPosFrom, orderTo);
  }

  /**
   * Увеличивает на единицу _порядок у тех _элементов у которых он в
   * текущий момент >= (1) и <= (2)
   *
   * @param orderTo   (1) -- _порядок
   * @param orderFrom (2) -- _порядок
   * @param parentId
   */
  public void incrementOrderMulti(int orderTo, int orderFrom, long parentId) {
    SQLiteDatabase db = _db.getWritableDatabase();
    String sql;
    if (!mode1_isSimple) {
      sql = String.format("UPDATE %1$s SET %2$s = %2$s + 1 WHERE ((%2$s >= %3$s) AND " +
          "(%2$s <= %4$s) %5$s AND (%6$s = %7$s))",
        _table, _fieldO, orderTo, orderFrom, mExtendContidion, _fieldP, parentId);
    } else {
      sql = String.format("UPDATE %1$s SET %2$s = %2$s + 1 WHERE ((%2$s >= %3$s) AND " +
          "(%2$s <= %4$s) %5$s)",
        _table, _fieldO, orderTo, orderFrom, mExtendContidion);
    }
    db.execSQL(sql);
    db.close();
  }

  /**
   * Увеличивает на единицу _порядок у тех _элементов у которых он в
   * текущий момент <= (1) и >= (2)
   *
   * @param orderTo   (1) -- _порядок
   * @param orderFrom (2) -- _порядок
   * @param parentId
   */
  public void decrementOrderMulti(int orderTo, int orderFrom, long parentId) {
    SQLiteDatabase db = _db.getWritableDatabase();
    String sql = "";
    if (!mode1_isSimple) {
      sql = String.format("UPDATE %1$s SET %2$s = %2$s - 1 WHERE ((%2$s <= %3$s) AND " +
          "(%2$s >= %4$s) %5$s AND (%6$s = %7$s))",
        _table, _fieldO, orderTo, orderFrom, mExtendContidion, _fieldP, parentId);
    } else {
      sql = String.format("UPDATE %1$s SET %2$s = %2$s - 1 WHERE ((%2$s <= %3$s) AND " +
          "(%2$s >= %4$s) %5$s)",
        _table, _fieldO, orderTo, orderFrom, mExtendContidion);
    }
    db.execSQL(sql);
    db.close();
  }

  /**
   * Установка _порядка (2) для _элемента с первичным ключем (1)
   *
   * @param _id   (1) -- первичный ключ _элемента _порядок которого нужно обновить
   * @param order (2) -- значение _порядка
   */
  public void updateOrder(long _id, int order) {
    SQLiteDatabase db = _db.getWritableDatabase();
    ContentValues cv = new ContentValues();
    cv.put(_fieldO, order);
    String sql = String.format("((%s = %s) %s)", _fieldID, _id, mExtendContidion);
    db.update(_table, cv, sql, null);
    db.close();
  }

  /**
   * Получение значения _порядка _элемента с первичным ключем (1)
   *
   * @param _id (1) -- первичный ключ _элемента чей _порядок необхдоимо получить
   * @return _порядок элемента; -1 если
   */
  public int getOrder(long _id) {
    if (_fieldO == null)
      throw new IllegalArgumentException("!!! для вызова метода getOrder нужно задать значение _fieldO");
    int ret = -1;
    SQLiteDatabase db = _db.getReadableDatabase();
    String sql = String.format("SELECT %s FROM %s WHERE ((%s = %s) %s)", _fieldO, _table,
      _fieldID, _id, mExtendContidion);
    Cursor cursor = db.rawQuery(sql, null);
    cursor.moveToFirst();
    if (cursor.getCount() > 0) {
      do {
        ret = cursor.getInt(cursor.getColumnIndex(_fieldO));
      } while (cursor.moveToNext());
    }
    cursor.close();
    db.close();
    return ret;
  }

  /**
   * Создание в таблице БД точной копии _элемента (1) (кроме поля первичных ключей и _порядка)
   * включая всех его _потомков
   *
   * @param _id       (1) -- первичный ключ клонируемого _элемента
   * @param _parentId (2) -- если != -1 то данный первичный ключ назначается как _родительский для полученного
   *                  в результате клонирования КОРНЕВОГО _элемента
   * @return первичный ключ копии
   */
  public long cloneElemHierarchy(long _id, long _parentId) {
    long[] currIds = getDescendantIDs(_id);
    currIds = ArrayUtils.add(currIds, _id);

    long[] newIds = {};
    long newId;
    for (long currId : currIds) {
      newId = G67G_Db.cloneRow(currId, _db, _table, _fieldID, _fieldP);
      newIds = ArrayUtils.add(newIds, newId);
    }

    long pid;
    for (long newId2 : newIds) {
      pid = getParentID(newId2); //старый parent id
      int i1 = ArrayUtils.indexOf(currIds, pid);
      if (i1 != -1) { //если родитель текущего _элемента среди тех _элементов которые клонировались
        updateParentID(newId2, newIds[i1]); //обновление новым parent id
      }
    }

    //=== обновление _порядка и _родителя корневого _элементак копируемой иерархии
    long parentID = getParentID(_id);
    int newOrder = getMaxOrder(parentID) + 1;
    int i = ArrayUtils.indexOf(currIds, _id);
    updateOrder(newIds[i], newOrder);
    if (_parentId != -1) {
      updateParentID(newIds[i], _parentId);
    }

    //===
    return newIds[i];
  }

  /**
   * Обновление поля {@link #_fieldP} _элемента (1)
   *
   * @param _id         (1) -- первичный ключ обновляемого _элемента
   * @param newParentId (2) -- новое значение parent _id
   */
  public void updateParentID(long _id, long newParentId) {
    SQLiteDatabase db = _db.getWritableDatabase();
    ContentValues cv = new ContentValues();
    cv.put(_fieldP, newParentId);
    String sql = String.format("%s = %s", _fieldID, _id);
    db.update(_table, cv, sql, null);
    db.close();
  }


  /**
   * Получение текущего наибольшего значения _порядка внутри _родителя (1)
   *
   * @param idParent (1) -- _id родителя
   * @return 0 если в таблице еще нет записей
   */
  public int getMaxOrder(long idParent) {
    int ret = 0;
    SQLiteDatabase db = _db.getReadableDatabase();
    String alias = "fmax";
    //=
    String where = String.format(" WHERE ((%s = %s) %s) ", _fieldP, idParent, mExtendContidion);
    //=
    String sql = String.format("SELECT max(%s) %s FROM %s %s",
      _fieldO, alias, _table, where);
    Cursor cursor = db.rawQuery(sql, null);
    cursor.moveToFirst();
    if (cursor.getCount() > 0) {
      ret = cursor.getInt(cursor.getColumnIndex(alias));
    }
    cursor.close();
    db.close();
    return ret;
  }

  /**
   * Установка максимального _порядка _элементу (1); имеется в виду _порядок в пределах _родителя(2)
   *
   * @param _id       (1) -- первичный ключ у которого нужно установить максимальный _порядок
   * @param _parentId (2) -- первичный ключ _родителя в пределах которого ищется максимальный _порядок
   */
  public void setMaxOrder(long _id, long _parentId) {
    int newOrder = getMaxOrder(_parentId) + 1;
    updateOrder(_id, newOrder);
  }

  /**
   * Получение значение поля _fieldAdding
   *
   * @param id          (1) -- первичный ключ _элемента
   * @param fieldAdding (2) -- имя поля _fieldAdding
   * @return значение
   */
  public String getFieldAddingValue(long id, String fieldAdding) {
    String ret = "";
    SQLiteDatabase db = _db.getReadableDatabase();
    String sql = String.format("SELECT %s FROM %s WHERE (%s = %s)", fieldAdding, _table, _fieldID, id);
    Cursor cursor = db.rawQuery(sql, null);
    cursor.moveToFirst();
    if (cursor.getCount() > 0) {
      do {
        ret = cursor.getString(cursor.getColumnIndex(fieldAdding));
      } while (cursor.moveToNext());
    }
    cursor.close();
    db.close();
    return ret;
  }

  /**
   * Получение пути _элемента (1) в виде пар id/"значение поля (2)"
   *
   * @param id          (1) -- первичный ключ _элемента путь которого необходимо получить
   * @param fieldAdding (2) -- имя поля содержащего тект-элемент-составляемого-пути
   * @return паралельный массив longArr/stringArr
   */
  public Uoj getPath(long id, String fieldAdding) {
    long[] parentgrandIDs = getParentgrandIDs(id);
    Uoj uoj = new Uoj();
    for (long elem : parentgrandIDs) {
      uoj.longArr = ArrayUtils.add(uoj.longArr, elem);
      uoj.stringArr = ArrayUtils.add(uoj.stringArr, getFieldAddingValue(elem, fieldAdding));
    }
    return uoj;
  }

  /**
   * Получение пути _элемента (1)
   *
   * @param id          (1) -- первичный ключ _элемента чей путь необходимо получить
   * @param fieldAdding (2) -- имя поля содержащего тект-элемент-составляемого-пути
   * @param divider     (3) -- разделитель элементов пути
   * @return путь
   */
  public String getPath_B(long id, String fieldAdding, String divider) {
    Uoj uoj = getPath(id, fieldAdding);
    ArrayUtils.reverse(uoj.stringArr);
    return TArray_01.arrToString(uoj.stringArr, divider);
  }
}

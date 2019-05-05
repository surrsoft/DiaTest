package ru.surrsoft.baaz.univers;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.tclasses.TNull_01;

/**
 * [[nhnv]] - это:
 * 1) древовидная структура. Представлена списком.
 * У каждого элемента списка можно спросить _уровень (интерфейс {@link OxtrGet_01} ). Таким образом
 * получается древовидная структура. Начальный уровень - 0
 * 2) структура проходящая проверку методом {@link #verify(ArrayList)}
 * <p>
 * ПОНЯТИЯ:
 * -- _корневые-элементы, _rootelems - элементы уровня 0
 * -- _уровень, _level - см. [oxtr]
 * -- _имя - [wrwn]-имя элемента. Используется в методе {@link #toStringM(ArrayList)}
 * <p>
 * КОДЫ: [ksao]
 * <p>
 * ВЕРСИЯ 1 1.0 2019-01-12 (stored)
 */
public class NhnvUtils_01 {

  /**
   * Проверка (1) на валидность. Результат проверки представлен классом {@link Vs41}.
   * Результат считается валидным если Vs41.isValid == TRUE
   *
   * @param arrNhnv (1) --
   * @param <T>     (01) -- элементы (1) должны реализовывать {@link OxtrGet_01}
   * @return --
   */
  public static <T extends OxtrGet_01> Vs41 verify(ArrayList<T> arrNhnv) {
    if (arrNhnv == null) {
      throw new SomeException("(debug)");
    }
    //---
    if (arrNhnv.size() == 0) {
      return new Vs41(true, En43.VALID, "нулевой длины [nhnv] считается валидным");
    }
    //---
    int iPrevLvl = 0;
    ArrayList arr = new ArrayList();
    for (int i = 0; i < arrNhnv.size(); i++) { //LOOP --
      OxtrGet_01 elem = arrNhnv.get(i);
      //---
      if (elem == null) {
        return new Vs41(false, En43.NULL_ELEM, "элемент == null; i [" + i + "]");
      }
      //---
      if (arr.contains(elem)) {
        return new Vs41(false, En43.POVTOR, "повтор элемента; i [" + i + "]");
      }
      arr.add(elem);
      //---
      int iLvl = elem.oxtrGet();
      //--- проверка на отрицательные уровни
      if (iLvl < 0) {
        return new Vs41(false, En43.NEGATIVE_LVL, "отрицательный уровень; на индексе " + i + "; iLvl [" + iLvl + "]");
      }
      //--- проверка что первый элемент имеет уровень = 0
      if (i == 0 && iLvl != 0) {
        return new Vs41(false, En43.FIRST_LVL_NO_NIL, "уровень первого элемента должен быть = 0");
      }
      //---
      if (iLvl - iPrevLvl > 1) {
        return new Vs41(false, En43.SKACH, "скачок уровня (в направлении увеличения); на индексе " + i + "; iLvl [" + iLvl + "]; iPrevLvl ["
          + iPrevLvl + "]");
      }
      //---
      iPrevLvl = iLvl;
    } //LOOP --
    //---
    return new Vs41(true, En43.VALID, "проблем не обнаружено");
  }

  //--- CLASS

  /**
   *
   */
  public static class Vs41 {
    public boolean isValid = false;
    public En43 state;
    public String comment;

    public Vs41(boolean isValid, En43 state, String comment) {
      this.isValid = isValid;
      this.state = state;
      this.comment = comment;
    }

    public boolean isValid() {
      return isValid;
    }

    @Override
    public String toString() {
      return "Vs41{" +
        "isValid=" + isValid +
        ", state=" + state +
        ", comment='" + comment + '\'' +
        '}';
    }
  }

  //--- ENUM

  /**
   *
   */
  public enum En43 {
    /**
     * Нет ошибок
     */
    VALID,
    /**
     * Один из уровней меньше нуля
     */
    NEGATIVE_LVL,
    /**
     * У первого элемента не нулевой уровень
     */
    FIRST_LVL_NO_NIL,
    /**
     * Один из элементов IS NULL
     */
    NULL_ELEM,
    /**
     * Есть одинаковые элементы (по ссылке)
     */
    POVTOR,
    /**
     * Встречена ситуация когда уровень нижележащего элемента на 2 или более больше чем уровень
     * вышележащего
     */
    SKACH
  }

  //---

  /**
   * Преобразование (1) в многоуровневую строку.
   * Предварительно (1) должен быть проверен на валидность с помощью {@link #verify(ArrayList)}
   *
   * @param _arrNhnv (1) --
   * @param <T>      --
   * @return --
   */
  public static <T extends OxtrGet_01 & WrwnGet_01> String toStringM(ArrayList<T> _arrNhnv) {
    StringBuilder ret = new StringBuilder();
    //--- шаг отступа слева
    int iConfStep = 2;
    //---
    for (T eNhnv : _arrNhnv) { //LOOP --
      int iLvl = eNhnv.oxtrGet();
      String stWrwn = eNhnv.wrwnGet();
      //---
      int i30 = iLvl * iConfStep;
      String stPrefix = StringUtils.repeat(' ', i30);
      //---
      if (ret.length() == 0) {
        ret = new StringBuilder("\n" + stPrefix + stWrwn);
      } else {
        ret.append("\n").append(stPrefix).append(stWrwn);
      }
    } //LOOP --
    ret.append("\n");
    return ret.toString();
  }

  /**
   * Извлекает из (1) _корневые-элементы и возвращает их в виде списка
   *
   * @param arrNhnv (1) -- NULL недопустим
   * @return пустой список если нет _корневых элементов
   */
  public static <T extends OxtrGet_01> ArrayList<T> rootelemsGet(ArrayList<T> arrNhnv) {
    if (arrNhnv == null) {
      throw new SomeException("(debug)");
    }
    //---
    ArrayList<T> ret = new ArrayList<>();
    //---
    for (T elem : arrNhnv) {
      if (elem.oxtrGet() == 0) {
        ret.add(elem);
      }
    }
    //---
    return ret;
  }

  /**
   * Определяет и возвращает индекс расположения элемента (2) в дереве (1)
   *
   * @param _arrNhnv (1) -- [nhnv]; NOT NULL
   * @param _elem    (2) -- NOT NULL
   * @return -- бросает исключение если (2) отсутствует в (1)
   */
  public static int indexGet(ArrayList _arrNhnv, Object _elem) {
    TNull_01.isNullEx(_arrNhnv);
    TNull_01.isNullEx(_elem);
    //---
    int iRet = _arrNhnv.indexOf(_elem);
    if (iRet == -1) {
      throw new SomeException("(debug)");
    }
    //---
    return iRet;
  }


  /**
   * Извлекает из [nhnv]-дерева (1) _детей ([benr]) элемента (2)
   *
   * @param _arrNhnv (1) -- [nhnv]-дерево
   * @param _elem    (2) -- элемент
   * @param <T>      --
   * @return -- пустой список если детей нет
   */
  public static <T extends OxtrGet_01> ArrayList<T> childsGet(ArrayList<T> _arrNhnv, T _elem) {
    if (_arrNhnv == null) {
      throw new SomeException("(debug)");
    }
    if (_elem == null) {
      throw new SomeException("(debug)");
    }
    if (!_arrNhnv.contains(_elem)) {
      throw new SomeException("(debug) элемент (2) не найден в дереве (1)");
    }
    //---
    ArrayList<T> ret = new ArrayList<>();
    //---
    int ix = indexGet(_arrNhnv, _elem);
    for (int i = (ix + 1); i < _arrNhnv.size(); i++) {
      T elem = _arrNhnv.get(i);
      if (elem.oxtrGet() == _elem.oxtrGet() + 1) {
        ret.add(elem);
      } else {
        break;
      }
    }
    //---
    return ret;
  }

  /**
   * Извлекает из [nhnv]-дерева (1) _потомков ([zkvn]) элемента (2)
   *
   * @param _arrNhnv (1) -- [nhnv]-дерево
   * @param _elem    (2) -- элемент
   * @param <T>      --
   * @return -- пустой список если _потомков нет
   */
  public static <T extends OxtrGet_01> ArrayList<T> descendantsGet(ArrayList<T> _arrNhnv, T _elem) {
    if (_arrNhnv == null) {
      throw new SomeException("(debug)");
    }
    if (_elem == null) {
      throw new SomeException("(debug)");
    }
    if (!_arrNhnv.contains(_elem)) {
      throw new SomeException("(debug) элемент (2) не найден в дереве (1)");
    }
    //---
    ArrayList<T> ret = new ArrayList<>();
    //---
    int ix = indexGet(_arrNhnv, _elem);
    for (int i = (ix + 1); i < _arrNhnv.size(); i++) {
      T elem = _arrNhnv.get(i);
      if (elem.oxtrGet() > _elem.oxtrGet()) {
        ret.add(elem);
      } else {
        break;
      }
    }
    //---
    return ret;
  }


}

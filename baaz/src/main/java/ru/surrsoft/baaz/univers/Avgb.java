package ru.surrsoft.baaz.univers;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import ru.surrsoft.baaz.SomeException;

/**
 * [[avgb]]
 * <p>
 * ЦЕЛЬ: есть некая _последовательность. Некоторые _элементы "чекаются" пользователем. Нужно
 * запомнить какие _элементы были _чекнуты. Так же нужно хранить длину _последовательности
 * <p>
 * ОПИСАНИЕ:
 * -- текущая сущность хранит 2 сущности: длину _последовательности (поле {@link #mSize}) и
 * _номера _чекнутых _элементов этой _последовательности (поле {@link #mChecklist}).
 * -- используется в WgPkor_B.java
 * <p>
 * ПОНЯТИЯ:
 * -- _последовательность - непрерывная последовательность чисел, начинается с 0, заканчивается
 * (_размер - 1)
 * -- _элемент - элемент _последовательности
 * -- _чеклист, _checklist - представляет собой [okov]-последовательность
 * -- --  _чекнут, чекнутость, _чекнуть -
 * -- -- _номер, _number - номер _элемента; хранится в _чеклисте
 * -- _размер, _size - количество _элементов в _последовательности (не путать с длиной _чеклиста)
 * <p>
 */
public class Avgb {

  /**
   * _чеклист
   */
  private Set<Long> mChecklist;

  /**
   * _размер
   */
  private long mSize = -1;

  //--- constructors
  public Avgb() {
    mChecklist = new TreeSet<>();
  }

  public Avgb(long size) {
    this();
    mSize = size;
  }

  //---

  /**
   * Возвращает длину _чеклиста (т.е. по сути количество _чекнутых)
   *
   * @return --
   */
  public long checklistSize() {
    return mChecklist.size();
  }

  /**
   * Если (2) == TRUE то добавляет _номер (1) в _чеклист, иначе удаляет оттуда
   *
   * @param number (1) --
   * @param bAdd   (2) --
   */
  public void checklistNumberAddOrRemove(long number, boolean bAdd) {
    if (bAdd) {
      mChecklist.add(number);
    } else {
      mChecklist.remove(number);
    }
  }

  /**
   * Добавление _номера (1) в _чеклист
   *
   * @param number (1) --
   */
  public void checklistNumberAdd(long number) {
    mChecklist.add(number);
  }

  /**
   * Удаление _номера (1) из _чеклиста
   *
   * @param number (1) --
   */
  public void checklistNumberRemove(long number) {
    mChecklist.remove(number);
  }

  /**
   * Обновление учитывающее swap.
   *
   * @param numberFrom (1) -- начальный _номер (перед swap)
   * @param numberTo   (2) -- конечный _номер (после swap)
   */
  public void checklistSwap(long numberFrom, long numberTo) {
    boolean bFrom = checklistIsChecked(numberFrom);
    boolean bTo = checklistIsChecked(numberTo);
    //---
    checklistNumberRemove(numberFrom);
    checklistNumberRemove(numberTo);
    //---
    if (bFrom) {
      checklistNumberAdd(numberTo);
    }
    if (bTo) {
      checklistNumberAdd(numberFrom);
    }
    //---
  }

  /**
   * Возвращает TRUE если _номер (1) есть среди _чекнутых
   *
   * @param number (1) --
   * @return --
   */
  public boolean checklistIsChecked(long number) {
    return mChecklist.contains(number);
  }

  /**
   * Инвертирование _чеков
   */
  public void checklistInvert() {
    if (mSize == -1) {
      throw new SomeException("(debug)");
    }
    //---
    for (long number = 0; number < mSize; number++) {
      if (mChecklist.contains(number)) {
        mChecklist.remove(number);
      } else {
        mChecklist.add(number);
      }
    }
  }

  /**
   * Если (1) == TRUE то _чекает все _элементы, иначе _дечекает все _элементы
   *
   * @param b (1) --
   */
  public void checklistCheckOrDecheckAll(boolean b) {
    if (mSize == -1) {
      throw new SomeException("(debug)");
    }
    for (long number = 0; number < mSize; number++) {
      if (b) {
        mChecklist.add(number);
      } else {
        mChecklist.clear();
      }
    }
  }

  @Override
  public String toString() {
    return "Avgb{" +
      "mChecklist=" + mChecklist +
      ", mSize=" + mSize +
      '}';
  }

  /**
   * Задание _размера
   *
   * @param size (1) --
   */
  public void sizeSet(long size) {
    mSize = size;
  }

  /**
   * Получение _размера
   *
   * @return --
   */
  public long sizeGet() {
    return mSize;
  }

  /**
   * Получение _чеклиста в формате [novd]
   *
   * @return --
   */
  public Map<Long, Long> checkListGet_B() {
    return Okov.okovAsNovd(mChecklist);
  }

  /**
   * Получение _чеклиста в формате [mvrc]
   *
   * @return --
   */
  public Set<Long> checklistGet() {
    return mChecklist;
  }

  /**
   * Удаление всех _номеров из _чеклиста
   */
  public void checklistClear() {
    mChecklist.clear();
  }


}

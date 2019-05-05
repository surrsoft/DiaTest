package ru.surrsoft.baaz.suite.new_.misc;

import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import ru.surrsoft.baaz.univers.Unox;

/**
 * [[vcnv]]
 * <p>
 * Утилитные методы для работы с [etof]-множествами ({@link _Etof})
 * <p>
 * ИСПОЛЬЗОВАНИЕ:
 * создать объект Vcnv передав [etof] в конструктор, затем использовать полученный объект
 * <p>
 * [[w390w]] [[N2390]]
 * <p>
 * ВЕРСИЯ 1 1.0 2019-02-06 (stored)
 *
 * @param <T> -- тип элементов множества [etof]
 */
public class Vcnv<T extends Unox> {
  /**
   * [etof] - множество вида [etof] с которым работают утилитные методы
   */
  private T[] mEtof;

  //constructors
  //``````````````````````````````````````````````````````````````````````````````````````````````

  /**
   * @param etof (1) -- множество вида {@link _Etof}
   */
  public Vcnv(T[] etof) {
    this.mEtof = etof;
  }

  public Vcnv(ArrayList<T> etof) {
    for (T elem : etof) {
      mEtof = ArrayUtils.add(mEtof, elem);
    }
  }

  //``````````````````````````````````````````````````````````````````````````````````````````````

  /**
   * @return --
   */
  public T[] getEtof() {
    return mEtof;
  }

  /**
   * Дети (^[odmu]s) элемента (1)
   *
   * @param oj (1) -- элемент из {@link #mEtof}
   * @return --
   */
  @NotNull
  public ArrayList<T> getOdmus(Unox oj) {
    ArrayList<T> ret = new ArrayList<>();
    for (T elem : mEtof) {
      if (elem.unoxGetNksc() == oj) {
        ret.add(elem);
      }
    }
    return ret;
  }

  /**
   * Получение потомков (^[wakt]s) элемента (1)
   *
   * @param oj (1) -- элемент из {@link #mEtof}
   * @return --
   */
  @NotNull
  public ArrayList<T> getWakts(Unox oj) {
    ArrayList<T> ret = new ArrayList<>();
    recurs40(oj, ret);
    return ret;
  }

  private void recurs40(Unox oj, ArrayList<T> ret) {
    ArrayList<T> дети = getOdmus(oj);
    for (T elem : дети) {
      ret.add(elem);
      recurs40(elem, ret);
    }
  }

  /**
   * Получение родителя (^[nksc]) элемента (1)
   *
   * @param oj (1) -- элемент из {@link #mEtof}
   * @return --
   */
  public T getNksc(Unox oj) {
    return (T) oj.unoxGetNksc();
  }

  /**
   * Получение предков (^[rfda]s) элемента (1)
   *
   * @param oj (1) -- элемент из {@link #mEtof}
   * @return --
   */
  @NotNull
  public ArrayList<T> getRfdas(Unox oj) {
    ArrayList<T> ret = new ArrayList<>();
    recurs48(oj, ret);
    return ret;
  }

  private void recurs48(Unox oj, ArrayList<T> ret) {
    Unox parent = oj.unoxGetNksc();
    if (parent != null) {
      ret.add((T) parent);
      recurs48(parent, ret);
    }
  }

  /**
   * Получение сиблингов (^[ecou]s) элемента (1)
   *
   * @param oj (1) -- элемент из {@link #mEtof}
   * @return --
   */
  @NotNull
  public ArrayList<T> getEcous(Unox oj) {
    ArrayList<T> ret = new ArrayList<>();
    for (T elem : mEtof) {
      if (elem.unoxGetNksc() == oj.unoxGetNksc() && elem != oj) {
        ret.add(elem);
      }
    }
    return ret;
  }

  /**
   * Получение списка _топ-элементов (^[fuzn]s)
   *
   * @return --
   */
  @NotNull
  public ArrayList<T> getFuzns() {
    ArrayList<T> ret = new ArrayList<>();
    for (T buzt : mEtof) { //LOOP
      boolean b = false;
      Unox nksc = buzt.unoxGetNksc(); //получение родителя
      if (nksc == null) {
        b = true;
      } else {
        //если родитель не принадлежит множеству [etof]
        if (!ArrayUtils.contains(mEtof, nksc)) b = true;
      }
      if (b) ret.add(buzt);
    } //LOOP
    return ret;
  }

}

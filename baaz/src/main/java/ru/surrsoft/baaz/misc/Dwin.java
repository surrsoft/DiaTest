package ru.surrsoft.baaz.misc;

import android.util.Log;

import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ru.surrsoft.baaz.cls_c.G67G_Strings;
import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.tclasses.TArray_01;
import ru.surrsoft.baaz.univers.BuString;

/**
 * [[dwin]], [[w191w]] - модель. Представляет элемент check-списка строк. Хранит строку
 * ({@link #text}) и состояние чекнута/не-чекнута ({@link #check} )
 * <p>
 * ВЕРСИЯ 1 1.0 2019-02-05 (stored)
 */
public class Dwin {
  private static final String TAG = ":Dwin";

  public String text;
  public boolean check;

  //constructors
  //``````````````````````````````````````````````````````````````````````````````````````````````

  public Dwin(String text, boolean isCheck) {
    this.text = text;
    this.check = isCheck;
  }

  //
  //``````````````````````````````````````````````````````````````````````````````````````````````


  /**
   * Создание массива используя параллельный массив (1)(2)
   *
   * @param texts  (1) --
   * @param checks (2) --
   * @return --
   */
  public static Dwin[] createDwins(String[] texts, boolean[] checks) {
    Dwin[] ret = {};
    for (int i = 0; i < texts.length; i++) {
      Dwin elem = new Dwin(texts[i], checks[i]);
      ret = ArrayUtils.add(ret, elem);
    }
    return ret;
  }

  /**
   * Создание [dwin]s на базе массива строк (1). Все элементы будут "не чекнуты"
   *
   * @param texts (1) --
   * @return --
   */
  public static Dwin[] createDwins(String[] texts) {
    if (texts == null || texts.length < 0) {
      return null;
    }
    //---
    Dwin[] ret = {};
    for (String eString : texts) {
      Dwin dwin = new Dwin(eString, false);
      ret = ArrayUtils.add(ret, dwin);
    }
    return ret;
  }

  /**
   * Отличается от А только тем что бросает ошибку если (1) isFillNot
   *
   * @param texts (1) --
   * @return --
   */
  @NotNull
  public static Dwin[] createDwins_B(String[] texts) {
    if (TArray_01.isFillNot(texts)) {
      throw new SomeException("(debug)");
    }
    return createDwins(texts);
  }

  /**
   * Сделать все элементы check==false
   *
   * @param dwins (1) --
   */
  public static void uncheckAll(Dwin[] dwins) {
    for (Dwin elem : dwins) {
      elem.check = false;
    }
  }

  /**
   * Делает все элементы (1) как "check == false"
   *
   * @param dwins (1) --
   */
  public static void uncheckAll(List<Dwin> dwins) {
    for (Dwin eDwin : dwins) {
      eDwin.check = false;
    }
  }

  /**
   * Возвращает массив строк всех элементов
   *
   * @param dwins (1) --
   * @return --
   */
  public static String[] getStrings(Dwin[] dwins) {
    String[] ret = new String[]{};
    for (Dwin eDwin : dwins) { //LOOP
      ret = ArrayUtils.add(ret, eDwin.text);
    } //LOOP
    return ret;
  }

  /**
   * Отличается от А только тем что бросает исключение если (1) == null
   * <p>
   * A: Возвращает массив строк всех элементов
   *
   * @param dwins (1) -- [dwin]s
   * @return --
   */
  public static String[] getStrings_B(Dwin[] dwins) {
    if (dwins == null) {
      throw new SomeException("(debug)");
    }
    //---
    String[] ret = new String[]{};
    for (Dwin eDwin : dwins) { //LOOP
      ret = ArrayUtils.add(ret, eDwin.text);
    } //LOOP
    return ret;
  }

  /**
   * Возвращает массив из id (порядковых номеров) отмеченных элементов (немерация с нуля)
   *
   * @param checkElems (1) --
   * @return --
   */
  public static int[] getCheckedElemsId(Dwin[] checkElems) {
    int[] ret = new int[]{};
    for (int i = 0; i < checkElems.length; i++) {
      Dwin elem = checkElems[i];
      if (elem.check) {
        ret = ArrayUtils.add(ret, i);
      }
    }
    return ret;
  }

  public static boolean[] getCheckedElemsBoolean(Dwin[] checkElems) {
    boolean[] ret = new boolean[checkElems.length];
    for (int i = 0; i < checkElems.length; i++) {
      Dwin elem = checkElems[i];
      if (elem.check) {
        ret[i] = true;
      }
    }
    return ret;
  }

  /**
   * Отмечает как check только элемент с индексом (2); остальные элементы устанавливаются в
   * check==false
   *
   * @param checkElems (1) --
   * @param idForCheck (2) --
   */
  public static void checkUpdateOne(Dwin[] checkElems, int idForCheck) {
    if (idForCheck > checkElems.length - 1 && idForCheck < 0) {
      Log.e(TAG, "(!!!) попытка обратится к несуществующему индексу массива");
      return;
    }
    for (int i = 0; i < checkElems.length; i++) {
      Dwin elem = checkElems[i];
      elem.check = i == idForCheck;
    }
  }

  /**
   * "Освежает" (1) в соотоветствии с (2). Если в (1) нет элемента представленного в (2) то он
   * туда
   * добавляется нечекнутым; если в (2) нет элемента представленного в (1) то элемент из (1)
   * удаляется
   *
   * @param currDwins (1) --
   * @param newTexts  (2) --
   * @return --
   */
  @NotNull
  public static Dwin[] refresh(Dwin[] currDwins, String[] newTexts) {
    //--- (debug)
    if (TArray_01.isNull(currDwins)) throw new SomeException("(debug)");
    if (TArray_01.isNull(newTexts)) throw new SomeException("(debug)");
    //---
    if (TArray_01.isEmpty(newTexts)) {
      return new Dwin[]{};
    }
    if (TArray_01.isEmpty(currDwins) && TArray_01.isEmpty(newTexts)) {
      return new Dwin[]{};
    }
    //---
    Dwin[] arr = {};
    for (String eNewText : newTexts) {
      if (!exist(currDwins, eNewText)) {
        arr = ArrayUtils.add(arr, new Dwin(eNewText, false));
      }
    }
    for (int i = currDwins.length - 1; i >= 0; i--) {
      Dwin elem = currDwins[i];
      if (ArrayUtils.indexOf(newTexts, elem.text) == -1) {
        currDwins = ArrayUtils.remove(currDwins, i);
      }
    }
    for (Dwin elem1 : arr) {
      currDwins = ArrayUtils.add(currDwins, elem1);
    }
    return currDwins;
  }

  /**
   * Возвращает TRUE если в (1) встречается чек с текстом (2)
   *
   * @param arr (1) --
   * @param str (2) --
   * @return --
   */
  public static boolean exist(Dwin[] arr, String str) {
    if (!G67G_Strings.isValid(str)) return false;
    if (arr == null || arr.length < 1) return false;
    for (Dwin elem : arr) {
      if (G67G_Strings.isValid(elem.text) && elem.text.equals(str)) return true;
    }
    return false;
  }

  /**
   * Сортировка (1) по text
   *
   * @param arr (1) --
   * @return --
   */
  public static Dwin[] sort(Dwin[] arr) {
    List<Dwin> list = Arrays.asList(arr);
    Collections.sort(list, new Comparator<Dwin>() {
      @Override
      public int compare(Dwin lhs, Dwin rhs) {
        return lhs.text.compareTo(rhs.text);
      }
    });
    return list.toArray(new Dwin[list.size()]);
  }

  public static String asString(Dwin[] arr) {
    BuString bst = new BuString()
      .divider(",")
      .addWraper("{", "}");
    for (Dwin elem : arr) {
      bst.appendWrap(elem.text + ", " + elem.check);
    }
    return bst.toString();
  }

  @Override
  public String toString() {
    return "Dwin{" +
      "text='" + text + '\'' +
      ", check=" + check +
      '}';
  }
}

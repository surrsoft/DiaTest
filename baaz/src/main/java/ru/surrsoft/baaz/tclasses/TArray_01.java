package ru.surrsoft.baaz.tclasses;

import android.support.annotation.NonNull;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.cls_c.G67G;
import ru.surrsoft.baaz.cls_c.G67G_Strings;
import ru.surrsoft.baaz.cls_c.Log2_01;

/**
 * Массивы
 * <p>
 * #[[ster]] - индекс массива ([[]]) с нумерацией с нуля
 * <p>
 * ПОНЯТИЯ:
 * -- _arr, _массив -
 * -- _elem, _элемент - элемент массива
 * -- _index, _индекс -
 * -- _list, _список -
 * <p>
 * ВЕРСИЯ 1 1.0 2019-02-04 (stored)
 */
public class TArray_01 {

  private static final boolean LOG2 = true;
  private static String TAG = ":TArray";

  /**
   * Удаление из _массива (1) _элемента расположенного по _индексу (2)
   *
   * @param arr   (1) -- недопустим null и _массив нулевой длины
   * @param index (2) -- должен быть >=0; должен быть в пределах длины _массива (1)
   * @param <T>   --
   * @return возвращает измененный клон (1)
   */
  public static <T> T[] elemRemoveByIndex(T[] arr, int index) {
    if (arr == null) {
      throw new SomeException("(debug) arr == null");
    }
    if (arr.length == 0) {
      throw new SomeException("(debug) arr.length == 0");
    }
    if (index < 0) {
      throw new SomeException("(debug) index < 0; index=" + index);
    }
    if (index >= arr.length) {
      throw new SomeException("(debug) index >= arr.length; index=" + index);
    }
    //---
    return ArrayUtils.remove(arr, index);
  }

  /**
   * Удаление последнего _элемента _массива (1)
   *
   * @param arr (1) -- недопустим null и _массив нулевой длины
   * @param <T> --
   * @return возвращает измененный клон (1)
   */
  public static <T> T[] elemRemoveLast(T[] arr) {
    if (arr == null) {
      throw new SomeException("(debug) arr == null");
    }
    if (arr.length == 0) {
      throw new SomeException("(debug) arr.length == 0");
    }
    //---
    return elemRemoveByIndex(arr, arr.length - 1);
  }

  public interface Predicate<T> {
    boolean isTrue(T elem);
  }

  /**
   * Возврат копии _массива (1), но с удаленными _элементами не удовлетворяющими предикату (2)
   *
   * @param arr       (1) --
   * @param predicate (2) --
   * @param <T>       -- тип элементов _массива (1)
   * @return --
   */
  public static <T> T[] elemsRemoveByPredicate(T[] arr, Predicate<T> predicate) {
    if (arr == null) {
      throw new SomeException("(debug) arr == null");
    }
    if (predicate == null) {
      throw new SomeException("(debug) predicate == null");
    }
    if (arr.length == 0) {
      return arr;
    }
    //--- определение индексов элементов которые подпадают под удаление
    int[] arrIndexes = {};
    for (int i = 0; i < arr.length; i++) { //LOOP --
      if (predicate.isTrue(arr[i])) {
        arrIndexes = ArrayUtils.add(arrIndexes, i);
      }
    } //LOOP --
    //--- удаление
    if (arrIndexes.length > 0) {
      for (int iIndex = arrIndexes.length - 1; iIndex >= 0; iIndex--) { //LOOP --
        arr = ArrayUtils.remove(arr, arrIndexes[iIndex]);
      } //LOOP --
    }
    //---
    return arr;
  }


  /**
   * Удаление первого _элемента _массива (1)
   *
   * @param arr (1) -- недопустим null и _массив нулевой длины
   * @param <T> --
   * @return возвращает измененный клон (1)
   */
  public static <T> T[] elemRemoveFirst(T[] arr) {
    if (arr == null) {
      throw new SomeException("(debug) arr == null");
    }
    if (arr.length == 0) {
      throw new SomeException("(debug) arr.length == 0");
    }
    //---
    return elemRemoveByIndex(arr, 0);
  }

  /**
   * Преобразование коллекции (1) в массив
   * <p>
   * ПРИМЕР: https://gist.github.com/surrsoft/2a03f44eacccf8c68846806118e68332
   *
   * @param list (1) -- коллекция которую нудно преобразовать
   * @param arr  (2) -- сюда нужно передать пустой массив такой же длины как коллекция (1)
   * @return --
   */
  public static <T> T[] listToArr(ArrayList<T> list, T[] arr) {
    if (list.size() != arr.length) throw new SomeException("(debug)");
    return list.toArray(arr);
  }

  /**
   * Возвращает TRUE если индекс (1) соответствует последнему элементу из (2)
   * <p>
   * Проверки на корректность входных данных не выполняются
   *
   * @param arr  (1) --
   * @param ster (2) -- [ster]
   * @return --
   */
  public static boolean isLastElem(Object[] arr, int ster) {
    return ster == arr.length - 1;
  }

  /**
   * Добавление в массив (1) элемента (2). Массив (1) при этом никак не изменяется, а
   * возвращаяется новый массив
   *
   * @param arr  (1) --
   * @param elem (2) --
   * @param <T>  --
   * @return --
   */
  public static <T> T[] add(T[] arr, T elem) {
    return ArrayUtils.add(arr, elem);
  }

  /**
   * Краткая информация о массиве (1)
   *
   * @param arr (1) --
   * @return --
   */
  public static String info(int[] arr) {
    if (arr == null) return "null";
    return "length=" + arr.length;
  }

  /**
   * Возвращает TRUE если массив (1) != null и имеет длинну больше 0
   *
   * @param arr (1) --
   * @return --
   */
  public static boolean isFill(Object[] arr) {
    return arr != null && arr.length > 0;
  }

  /**
   * Возвращает TRUE если список (1) != null и имеет длинну больше 0
   *
   * @param list (1) --
   * @return --
   */
  public static boolean isFill(Collection<?> list) {
    return list != null && list.size() > 0;
  }

  /**
   * Возвращает TRUE если map (1) != null и имеет длинну больше 0
   *
   * @param map (1) --
   * @return --
   */
  public static boolean isFill(Map<String, String> map) {
    return map != null && map.size() > 0;
  }

  /**
   * Противоположность {@link #isFill(Map)}
   *
   * @param map (1) --
   * @return --
   */
  public static boolean isFillNot(Map<String, String> map) {
    return map == null || map.size() < 1;
  }

  /**
   * Противоположность {@link #isFill(Object[])}
   *
   * @param arr (1) --
   * @return --
   */
  public static boolean isFillNot(Object[] arr) {
    return !isFill(arr);
  }

  /**
   * Противоположность {@link #isFill(Collection)}
   *
   * @param list (1) --
   * @return --
   */
  public static boolean isFillNot(Collection<?> list) {
    return !isFill(list);
  }

  public static boolean isEmpty(Object[] arr) {
    if (isNull(arr)) {
      throw new SomeException("(debug)");
    }
    return arr.length < 1;
  }

  public static boolean isEmptyNot(Object[] arr) {
    if (isNull(arr)) {
      throw new SomeException("(debug)");
    }
    return arr.length > 0;
  }

  /**
   * Возвращает TRUE если все массивы представленные в (1) {@link #isFill(Object[])}
   *
   * @param arrs (1) --
   * @return --
   */
  public static boolean isFill(Object[]... arrs) {
    for (Object[] eArr : arrs) { //LOOP
      if (!isFill(eArr)) {
        return false;
      }
    } //LOOP
    return true;
  }

  /**
   * Возвращает TRUE если (1) == null
   *
   * @param arr (1) --
   * @return --
   */
  public static boolean isNull(Object[] arr) {
    return arr == null;
  }

  /**
   * [[kipd]] - это (map != null) && (map.size() > 0) && (map не имеет ни одного ключа или
   * значения равного NULL)
   * <p>
   *
   * @param map (1) --
   * @return --
   */
  public static boolean isKipd(Map<Object, Object> map) {
    if (map != null && map.size() > 0) {
      for (Map.Entry<Object, Object> elem : map.entrySet()) { //LOOP
        if (elem.getKey() == null) return false;
        if (elem.getValue() == null) return false;
      } //LOOP
      return true;
    }
    return false;
  }


  /**
   * Возвращает TRUE если (1) != null
   *
   * @param arr (1) --
   * @return --
   */
  public static boolean isNullNot(Object[] arr) {
    return arr != null;
  }


  //
  //``````````````````````````````````````````````````````````````````````````````````````````````

  /**
   * ЦЕЛЬ: вставить (имеется виду вставка когда смещаются другие элементы) в массив элемент
   * ОПИСАНИЕ: Возвращает новый массив все элементы которого ссылаются туда же куда и элементы
   * массива (1)
   * но в новом массиве по индексу (3) кроме того вставлен элемент (2) (другие элементы
   * сдвигаются).
   * Не работает с ПРИМИТИВНЫМИ ТИПАМИ в (1)
   *
   * @param original (1) -- оригинальный массив любого типа КРОМЕ ПРИМИТИВНЫХ ТИПОВ; остается
   *                 неизменным по итогам работы текущего метода
   * @param element  (2) -- элемент который нужно вставить
   * @param index    (3) -- индекс по которому нужно выполнить вставку; если массив например
   *                 длинной 2, то здесь можно указать 2, тогда будет выполнено добавление
   *                 элемента (2) в конец массива (1)
   * @return -- новый массив; null если не входе (1) == null
   */
  public static <T> T[] insertElement(T[] original, T element, int index) {
    if (original == null) return null;
    if (index < 0 || index > original.length)
      throw new SomeException("!!! НЕВЕРНЫЙ ИНДЕКС");
    if (index == original.length) {
      return ArrayUtils.add(original, element);
    }

    T[] ret = (T[]) Arrays.copyOf(original, original.length + 1, original.getClass());

    int plus = 0;
    for (int i = 0; i < original.length; i++) {
      ret[i + plus] = original[i];
      if (i == index) {
        ret[i] = element;
        ret[i + 1] = original[i];
        plus = 1;
      }
    }
    return ret;
  }

  /**
   * Вставка в массив int[] элемента (3) по индексу (2). Возвращается новый массив созданный
   * на базе (1)
   *
   * @param originalArray (1) --
   * @param insertIndex   (2) --
   * @param insertValue   (3) --
   * @return --
   */
  public static int[] insertElement(int[] originalArray, int insertIndex, int insertValue) {
    int[] arr5 = new int[originalArray.length + 1];
    System.arraycopy(originalArray, 0, arr5, 0, insertIndex);
    System.arraycopy(originalArray, insertIndex, arr5, insertIndex + 1,
      originalArray.length - insertIndex);
    arr5[insertIndex] = insertValue;
    return arr5;
  }

  /**
   * Вставка в массив long[] элемента (3) по индексу (2). Возвращается новый массив созданный
   * на базе (1)
   *
   * @param originalArray (1) --
   * @param insertIndex   (2) --
   * @param insertValue   (3) --
   * @return --
   */
  public static long[] insertElement(long[] originalArray, int insertIndex, long insertValue) {
    long[] arr5 = new long[originalArray.length + 1];
    System.arraycopy(originalArray, 0, arr5, 0, insertIndex);
    System.arraycopy(originalArray, insertIndex, arr5, insertIndex + 1,
      originalArray.length - insertIndex);
    arr5[insertIndex] = insertValue;
    return arr5;
  }

  /**
   * Удаляет из (1) одинаковые строки. Сравнение регистрозависимое. Порядок элементов сохраняется
   * <p>
   * #version 1 25-02-2016
   * #version 2 16-04-2016
   *
   * @param strs (1) -- например {"aa", "bb", null, null, "", "", "aa", "cc", "aa"}
   * @return например {"aa", "bb", null, , "cc"}
   */
  public static String[] removeDoublicates(String[] strs) {
    if (strs == null) return null;
    if (strs.length == 0) return strs;
    //=== здесь убираются дубли
    Set<String> set = new LinkedHashSet<>(Arrays.asList(strs));
    //===
    Iterator<String> iterator = set.iterator();
    String[] ret = new String[]{};
    while (iterator.hasNext()) {
      ret = ArrayUtils.add(ret, iterator.next());
    }
    return ret;
  }

  /**
   * Возвращает сколько раз в массиве (1) встречается значение (2)
   *
   * @param arr
   * @param value
   * @return
   */
  public static int getValuesCount(int[] arr, int value) {
    int ret = 0;
    for (int i : arr) {
      if (value == i) ret++;
    }
    return ret;
  }

  public static Oj1 maxMinAverageValue(Float[] floats) {
    Oj1 oj1 = new Oj1();
    for (int i = 0; i < floats.length; i++) {
      Float f = floats[i];
      if (f != null) {
        if (oj1.max == null || f > oj1.max) {
          oj1.max = f;
        }
        if (oj1.min == null || f < oj1.min) {
          oj1.min = f;
        }
      }
    }
    if (oj1.max != null && oj1.min != null) {
      oj1.average = (oj1.max + oj1.min) / 2f;
    }
    if (oj1.max != null) {
      for (int i = 0; i < floats.length; i++) {
        Float f2 = floats[i];
        if (f2.equals(oj1.max)) oj1.maxIndexes = ArrayUtils.add(oj1.maxIndexes, i);
      }
    }
    if (oj1.min != null) {
      for (int i = 0; i < floats.length; i++) {
        Float f2 = floats[i];
        if (f2.equals(oj1.min)) oj1.minIndexes = ArrayUtils.add(oj1.minIndexes, i);
      }
    }
    return oj1;
  }

  /**
   * Преобразование массива long[] (1) в массив String[]
   *
   * @param arr (1)
   * @return если на входе пусто или null, возвращает пустой массив
   * <p>
   * #version 1 21.05.2016
   */
  @NonNull
  public static String[] toStringArr(long[] arr) {
    String[] ret = {};
    if (arr == null) return ret;
    for (long elem : arr) {
      ret = ArrayUtils.add(ret, elem + "");
    }
    return ret;
  }

  /**
   * Преобразует массив (1) в строку с разделителями (2)
   *
   * @param arr     (1) -- массив строк, например "{"a", "b", "c"}"
   * @param divider (2) -- разделитель, например ","
   * @return например "a,b,c"
   */
  public static String arrToString(String[] arr, String divider) {

    return StringUtils.join(arr, divider);
  }

  /**
   * Объединение коллекции строк (1) в одну строку через разделители (2)
   *
   * @param arr     (1) -- например {"aa", "bb", "cc"}
   * @param divider (2) -- например ";"
   * @return -- например "aa;bb;cc"
   */
  public static String join_A(ArrayList<String> arr, String divider) {
    String[] arr1 = arr.toArray(new String[0]);
    return StringUtils.join(arr1, divider);
  }

  /**
   * Преобразует (1) в строку с разделителями (2). В итогую строку попадают только: уникальные
   * элементы (1), элементы (1)
   * != null, длиной > 0. Перед сравнением на уникальность элементы (1) преобразуеются к нижнему
   * регистру и в таком виде
   * попадают в итоговую строку. Порядок элементов в выходной строке не гарантируется
   *
   * @param arr     (1) -- например ["a", "A", null, "", "B"]
   * @param divider (2) -- например ",". Может быть null или строкой нулевой длины
   * @return например "a,b". Если массив на входе нулевой длины возвращает пустую строку
   */
  public static String arrToString_B(String[] arr, String divider) {
    if (arr == null) throw new IllegalArgumentException("--!-- arr == null --!--");
    if (divider == null) divider = "";
    if (arr.length == 0) return "";

    Set<String> hs = new HashSet<>();
    for (String s : arr) {
      if (G67G_Strings.ok(s)) {
        hs.add(s.toLowerCase());
      }
    }

    if (hs.size() > 0) {
      String[] strs = new String[hs.size()];
      hs.toArray(strs);
      return StringUtils.join(strs, divider);
    }

    return "";
  }

  /**
   * Печать в LogCat содержимого строкового массива (1). Для поиска:
   * [print] [печать]
   *
   * @param aLi     (1) -- строковый одномерный массив, например ["a", "b", ..]
   * @param aPrefix (2) -- префикс
   */
  public static void print(Object[] aLi, String aPrefix) {
    if (aLi == null) {
      System.out.println(aPrefix + " -- is null");
      return;
    }
    if (aLi.length < 1) {
      System.out.println(aPrefix + " []");
      return;
    }

    StringBuilder sb = new StringBuilder();
    sb.append(aPrefix);
    sb.append(" [");
    for (int i = 0; i < aLi.length; i++) {
      if (i == 0) {
        sb.append(aLi[i].toString());
      } else {
        sb.append("; ");
        sb.append(aLi[i].toString());
      }
    }
    sb.append("]");
    System.out.println(sb);
  }

  /**
   * Печать в LogCat содержимого строкового массива (1). Для поиска:
   * [print] [печать]
   * <p>
   * От А отличает тем что (1) может быть массивом любой размерности
   *
   * @param arr    (1) -- массив любой размерности
   * @param prefix (2) -- префикс
   */
  public static void print_B(Object[] arr, String prefix) {
    Log2_01.d2(TAG, Arrays.deepToString(arr), prefix + " ", LOG2);
  }

  /**
   * Ищет в Map (1) первую entry у которой значение == (2) и возвращает
   * key данной entry
   *
   * @param map   (1) -- Map
   * @param value (2) -- значение
   * @return пустая строка при нештатах
   * @info "Все методы"
   */
  public static String getMapKeyByValue(Map<String, String> map, String value) {
    String res = "";
    if (map == null || value == null || map.size() < 1) {
      return res;
    }

    for (Map.Entry<String, String> e : map.entrySet()) {
      if (e.getValue().equals(value)) {
        res = e.getKey();
        break;
      }
    }
    return res;
  }

  /**
   * Замена местами элементов массива (1)
   *
   * @param elems          (1) --
   * @param firstPosition  (2) --
   * @param secondPosition (3) --
   */
  public static void swap(Object[] elems, int firstPosition, int secondPosition) {
    if (elems == null || elems.length < 1) return;
    G67G.assert_(firstPosition >= 0, "assert: firstPosition >= 0");
    G67G.assert_(secondPosition >= 0, "assert: secondPosition >= 0");
    G67G.assert_(firstPosition < elems.length, "assert: firstPosition < elems.length");
    G67G.assert_(secondPosition < elems.length, "assert: secondPosition < elems.length");
    if (firstPosition == secondPosition) return;

    Object elem = elems[firstPosition];
    elems[firstPosition] = elems[secondPosition];
    elems[secondPosition] = elem;
  }

  /**
   * Замена местами элементов коллекции (1). После замены в позиции (2) будет сидеть элемент ранее
   * находившийся в позиции (3), и наоборот
   *
   * @param elems          (1) --
   * @param firstPosition  (2) --
   * @param secondPosition (3) --
   */
  public static void swap(ArrayList elems, int firstPosition, int secondPosition) {
    if (elems == null || elems.size() < 1) return;
    //---
    G67G.assert_(firstPosition >= 0, "assert: firstPosition >= 0");
    G67G.assert_(secondPosition >= 0, "assert: secondPosition >= 0");
    G67G.assert_(firstPosition < elems.size(), "assert: firstPosition < elems.size()");
    G67G.assert_(secondPosition < elems.size(), "assert: secondPosition < elems.size()");
    //---
    if (firstPosition == secondPosition) return;
    //---
    Object elem = elems.get(firstPosition);
    elems.set(firstPosition, elems.get(secondPosition));
    elems.set(secondPosition, elem);
  }

  /**
   * Ищет индекс расположения объекта (2) в массиве (1) через сравнение ==
   *
   * @param arr (1) --
   * @param oj  (2) --
   * @return -1 если не найдено
   */
  public static int indexOf(Object[] arr, Object oj) {
    if (arr == null) throw new SomeException("(debug) null");
    int ret = -1;
    if (arr.length == 0) {
      return ret;
    }
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == oj) return i;
    }
    return ret;
  }

  /**
   * Преобразование массива (1) в коллекцию java.util.ArrayList
   *
   * @param ojs (1) -- простой массив
   * @param <T> --
   * @return java.util.ArrayList
   */
  public static <T> ArrayList<T> arrToList(T[] ojs) {
    if (ojs == null) return null;
    //---
    ArrayList<T> ret = new ArrayList<>();
    Collections.addAll(ret, ojs);
    return ret;
  }

  /**
   * Преобразование массива любых типов (1) в коллекцию List&lt;T>.
   * На фоне возвращаемого List по факту тип Arrays#ArrayList (не путать с java.util.ArrayList)
   *
   * @param ojs (1) --
   * @param <T> --
   * @return --
   */
  public static <T> List<T> arrToList_B(T[] ojs) {
    return Arrays.asList(ojs);
  }


  /**
   * Клонирование массива (1). Выполняется поэлементное клонирование элементов (1) в (2).
   * (2) должен быть зарание создан и иметь ту же длину что и (1)
   * <p>
   * У (2) _фактический-типтокен будет таким же как и у (1). Т.е. внешне (1) может быть
   * задекларирован
   * например как какой-нибудь интерфейс, а по факту иметь другой типтокен внутри - такое
   * дозволяется
   * <p>
   * Элементы типов (1) и (2) должны поддерживать интерфейс {@link Cloneable}, иначе будет ошибка
   * исполнения
   *
   * @param from (1) --
   * @param to   (2) --
   * @param <T>  --
   */
  public static <T> void cloneArr(T[] from, T[] to) {
    for (int i = 0; i < from.length; i++) {
      T elem = from[i];
      Class<?> cls = elem.getClass();
      try {
        Method m = cls.getMethod("clone");
        T invoke = (T) m.invoke(elem);
        to[i] = invoke;
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }
  }

  public static class Oj1 {
    public Float max = null;
    public Float min = null;
    public Float average = null;
    public int[] maxIndexes = new int[]{};
    public int[] minIndexes = new int[]{};
  }

}

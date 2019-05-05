package ru.surrsoft.baaz.debug;

import java.util.List;

import ru.surrsoft.baaz.tclasses.TString_01;
import ru.surrsoft.baaz.univers.U;

/**
 * [[ycyf]] - класс обеспечивающий ^[[dabx]]-технику формирования toString()-вывода в лог
 * <p>
 * ДЕМО: см. demo.ycyf_demo
 */
public class Ycyf_01 {

  //--- [[rwry]] - константы обозначающие места открытия, закрытия и внутренних строк. Здесь расчёт
  // ... на то что вряд ли такие же сочетания встретяться где-либо в значащих данных
  //открывающая строка
  public static final String RWRY_OPEN = "[rwry_2737be18]";
  //закрывающая строка
  public static final String RWRY_CLOSE = "[/rwry_2737be18]";
  //строка внутри
  public static final String RWRY_INN = "[rwry_inn_2737be18]";

  //---
  //префикс; повторяется многократно в заисимости от уровня
  private static final String PREFIX = "..";

  /**
   * Адаптирует ^[svso]-строку к показу в логе
   *
   * @param st (1) -- ^[svso]-строка
   * @return --
   */
  public static String adapt(String st) {
    if (st == null) {
      return "null";
    }
    //---
    Lvl ojLvl = new Lvl();
    do {
      st = mtReplace(st, ojLvl);
    } while (ojLvl.bContinue);
    return st;
  }

  /**
   * Вспомогательный класс
   */
  private static class Lvl {
    //уровень
    int iLvl = 0;
    //используется для завершения поисков
    boolean bContinue = false;
  }

  /**
   * Ищет в строке (1) ^[rwry]-строки и заменяет их нужным количеством отступов в соответствии с
   * уровнем из (2). Возвращает изменённую строку
   *
   * @param st  (1) --
   * @param lvl (2) --
   * @return ---
   */
  private static String mtReplace(String st, Lvl lvl) {
    U.se(lvl == null, "");
    //---
    String stRet = st;
    lvl.bContinue = false;
    //---
    int ixOpen = st.indexOf(RWRY_OPEN);
    int ixClose = st.indexOf(RWRY_CLOSE);
    int ixInn = st.indexOf(RWRY_INN);
    //--- адаптация для удобства сравнивания индексов
    ixOpen = (ixOpen == -1) ? Integer.MAX_VALUE : ixOpen;
    ixClose = (ixClose == -1) ? Integer.MAX_VALUE : ixClose;
    ixInn = (ixInn == -1) ? Integer.MAX_VALUE : ixInn;
    //---
    String stCurrRwryConst = null;
    //---
    if (ixOpen < ixClose && ixOpen < ixInn) {
      stCurrRwryConst = RWRY_OPEN;
    }
    if (ixClose < ixOpen && ixClose < ixInn) {
      stCurrRwryConst = RWRY_CLOSE;
    }
    if (ixInn < ixOpen && ixInn < ixClose) {
      stCurrRwryConst = RWRY_INN;
    }
    //---
    if (stCurrRwryConst != null) {
      lvl.bContinue = true;
      String stBefore = mtBefore(st, stCurrRwryConst);
      String stAfter = mtAfter(st, stCurrRwryConst);
      //---
      if (stCurrRwryConst.equals(RWRY_OPEN)) {
        stRet = stBefore + stAfter;
        lvl.iLvl++;
      } else if (stCurrRwryConst.equals(RWRY_CLOSE)) {
        lvl.iLvl--;
        String stPrefix = TString_01.repeat_B(PREFIX, lvl.iLvl);
        stRet = stBefore + stPrefix + stAfter;
      } else {
        String stPrefix = TString_01.repeat_B(PREFIX, lvl.iLvl);
        stRet = stBefore + stPrefix + stAfter;
      }
    }
    //---
    return stRet;
  }

  /**
   * Преобразование массива (1) к формату походящему под текущую технику.
   * <p>
   * При создании текущего метода был взят стандартный метод java.util.Arrays.toString()
   *
   * @param arr (1) --
   * @return --
   */
  public static String toStringArr(Object[] arr) {
    if (arr == null)
      return "null";
    //---
    int iMax = arr.length - 1;
    if (iMax == -1)
      return RWRY_OPEN + "[\n" + RWRY_CLOSE + "]";
    //---
    StringBuilder b = new StringBuilder();
    b.append(RWRY_OPEN + '[');
    for (int i = 0; ; i++) {
      b.append('\n').append(RWRY_INN).append(String.valueOf(arr[i]));
      if (i == iMax)
        return b.append("\n" + RWRY_CLOSE + "]").toString();
      b.append(", ");
    }
  }

  /**
   * Преобразование списка (1) к формату походящему под текущую технику
   *
   * @param list (1) --
   * @return --
   */
  public static String toStringList(List list) {
    return toStringArr(list.toArray());
  }

  /**
   * Возвращает текст располагающийся перед разделителем (2)
   *
   * @param st        (1) --
   * @param stDivider (2) --
   * @return null если разделитель не найден
   */
  private static String mtBefore(String st, String stDivider) {
    U.se(st == null, "");
    U.se(stDivider == null, "");
    //---
    int ix = st.indexOf(stDivider);
    if (ix != -1) {
      return st.substring(0, ix);
    }
    return null;
  }

  /**
   * Возвращает текст располагающийся после разделителя (2)
   *
   * @param st        (1) --
   * @param stDivider (2) --
   * @return null если разделитель не найден
   */
  private static String mtAfter(String st, String stDivider) {
    U.se(st == null, "");
    U.se(stDivider == null, "");
    //---
    int ix = st.indexOf(stDivider);
    if (ix != -1) {
      return st.substring(ix + stDivider.length());
    }
    return null;
  }

}

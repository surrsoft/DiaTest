package ru.surrsoft.baaz.debug.old;

import ru.surrsoft.baaz.tclasses.TString_01;

/**
 * Класс с методом для работы с техникой ^[[orxn]]
 */
public class OrxnCls_01 {
  /**
   * Обрабатывает результат работы метода toString() сформированного с использованием техники [orxn],
   * таким образом чтобы добавить отступы для удобного показа структуры
   * <p>
   * #errors нет
   * <p>
   * ID: [[nvur]]
   *
   * @param st (1) --
   * @return --
   */
  public static String methodToStringPretty(String st) {
    if (st == null) {
      return "null";
    }
    //---
    String stConfOtstup = "  ";
    char cConf1 = '-';
    //---
    int ct = 0;
    StringBuilder sb = new StringBuilder();
    //предыдущий символ
    char cBefore = cConf1;
    //следующий символ
    char cNext;
    //---
    for (int i = 0; i < st.length(); i++) {
      //---
      char cCurrent = st.charAt(i);
      //---
      cNext = cConf1;
      if (i < (st.length() - 1)) {
        cNext = st.charAt(i + 1);
      }
      //---
      sb.append(cCurrent);
      //---
      if (cCurrent == '\n') {
        if (cBefore == '{') {
          ct++; //вход в переносы
          String stR = TString_01.repeat_B(stConfOtstup, ct);
          sb.append(stR);
        } else if (cNext == '}') {
          ct--; //выход из переносов
          String stR = TString_01.repeat_B(stConfOtstup, ct);
          sb.append(stR);
        }
      }
      //---
      cBefore = cCurrent;
      //---
    }
    //---
    return sb.toString();
  }
}

package ru.diatest.vk.vkapi;

import ru.surrsoft.baaz.tclasses.TString_01;
import ru.surrsoft.baaz.univers.U;

/**
 * Утилитные методы для работы с результатами обращения к методам API ВКонтакте
 * <p>
 * ПОНЯТИЯ:
 * -- _vkresult ([csoc]) - {json-string} результат обращения к какому-либо методу API ВКонтакте
 */
public class VkResult {

  /**
   * Возвращает TRUE если строка (2) соответствует успешному обращению к методу (1)
   *
   * @param method   (1) --
   * @param vkresult (2) -- _vkresult
   * @return --
   */
  public static boolean isValid(VkEMethods method, String vkresult) {
    U.se(method == null, "");
    //---
    if (TString_01.isEmpty(vkresult)) {
      return false;
    }
    //---
    switch (method) {
      case FRIENDS_GET:
      case PHOTOS_GET:
        return vkresult.startsWith("{\"response\":{\"count\":");
    }
    return false;
  }
}

package ru.diatest.vk.vkapi.errors;

import ru.diatest.vk.vkapi.resp_obj.err.VkErr;
import ru.diatest.vk.vkapi.resp_obj.err.VkErrorMain;
import ru.surrsoft.baaz.Bysa_01;
import ru.surrsoft.baaz.tclasses.TString_01;

/**
 * //needdesc
 * <p>
 * ПОНЯТИЯ:
 * -- _строка-ошибки - входная строка
 * -- _вк-строка-ошибки, _vkstrerr - строки ошибки в формате API ВКонтакте
 */
public class VkErrors {

  public enum EErr {
    /**
     * Одна из ошибок аутентификации
     */
    AUTH_FAILED,

    /**
     * _строка-ошибки это _вк-строка-ошибки, но описываемая ей ошибка выходит за рамки компетенции
     * текущего класса
     */
    OTHER,
    /**
     * _строка-ошибки это не _вк-строка-ошибки
     */
    UNDEFINED
  }

  /**
   * @param stError (1) --
   * @return --
   */
  public static VkErr resolve(String stError) {
    VkErr ret = new VkErr();
    ret.eErr = EErr.UNDEFINED;
    //---
    if (isVkstrerr(stError)) {
      ret.eErr = EErr.OTHER;
      //---
      VkErrorMain vkErrorMain = Bysa_01.gson.fromJson(stError, VkErrorMain.class);
      ret.vkerror = vkErrorMain.error;
      //---
      if (vkErrorMain.error.error_msg.startsWith("User authorization failed:")) {
        ret.eErr = EErr.AUTH_FAILED;
      }
    }
    return ret;
  }

  /**
   * Возвращает TRUE если строка (1) похожа на _вк-строку-ошибки
   *
   * @param stError (1) --
   * @return --
   */
  public static boolean isVkstrerr(String stError) {
    if (TString_01.isEmpty(stError)) {
      return false;
    }
    return stError.startsWith("{\"error\":{\"error_code\":");
  }

}

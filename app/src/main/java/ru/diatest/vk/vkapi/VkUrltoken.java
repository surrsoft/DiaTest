package ru.diatest.vk.vkapi;

import java.util.Map;

import ru.surrsoft.baaz.univers.KrURL;

/**
 * Класс отражающий _urltoken ([vnbe])
 */
public class VkUrltoken {

  /**
   * _urltoken ([vnbe])
   */
  private String stUrltoken;

  /**
   * _vktoken ([gcza])
   */
  private String stVktoken = "";
  /**
   * _userid ([vgtx])
   */
  private String stUserid = "";
  private String stExpires = "";

  //--- constructors

  public VkUrltoken(String stUrltoken) {
    this.stUrltoken = stUrltoken;
    //---
    if (isValid()) {
      KrURL krURL = new KrURL(stUrltoken);
      Map<String, String> pairs = krURL.pairsGet(KrURL.EURLPart.FRAGMENT);
      //---
      stVktoken = mtStringGet(pairs, "access_token");
      stExpires = mtStringGet(pairs, "expires_in");
      stUserid = mtStringGet(pairs, "user_id");
    }
  }

  //---

  /**
   * Получение _vktoken ([gcza])
   *
   * @return пустая строка или _vktoken
   */
  public String getVktoken() {
    return stVktoken;
  }

  /**
   * Получение _userid ([vgtx])
   *
   * @return пустая строка или _userid
   */
  public String getUserid() {
    return stUserid;
  }

  //---

  /**
   * @return TRUE если инкапсулированный _urltoken валиден
   */
  public boolean isValid() {
    return isValid(stUrltoken);
  }

  /**
   * Возвращает TRUE если (1) это валидный _urltoken
   *
   * @param stUrltoken (1) -- строка возможно являющася _urltoken-ом
   * @return --
   */
  public static boolean isValid(String stUrltoken) {
    if (stUrltoken != null && stUrltoken.length() > 0) {
      return stUrltoken.contains("https://oauth.vk.com/blank.html#access_token=");
    }
    return false;
  }

  //---

  private String mtStringGet(Map<String, String> pairs, String key) {
    String st = pairs.get(key);
    if (st == null) {
      return "";
    }
    return st;
  }
}

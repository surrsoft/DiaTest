package ru.surrsoft.baaz.cls_c;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Утилитные методы для работы с Json
 */
public class TJson_01 {

  /**
   * Получение из JSON (1) значения ключа (2)
   *
   * @param jsonString (1) --
   * @param key        (2) --
   * @param def        (3) -- значение возвращаемое если поле не было найдено
   * @return --
   */
  public static String getValue(String jsonString, String key, String def) {
    try {
      JSONObject jso = new JSONObject(jsonString);
      if (jso.has(key)) {
        return jso.getString(key);
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return def;
  }
}

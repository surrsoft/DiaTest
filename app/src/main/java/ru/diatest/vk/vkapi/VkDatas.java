package ru.diatest.vk.vkapi;

import ru.surrsoft.baaz.logs.Vdin_01;
import ru.surrsoft.baaz.tclasses.TSharprefCommon_01;
import ru.surrsoft.baaz.univers.U;

/**
 * Хранилище ВКонтакте-ассоциированных значений, в том числе токена API ВКонтаке
 * <p>
 */
public class VkDatas {

  private static final String SHP_VKTOKEN_VALUE = "vktoken_190501121600";
  private static final String SHP_USERID_VALUE = "userid_190501121601";

  //--- constructors

  private VkDatas() {
  }

  //---

  /**
   * Обновление данных в хранилище данными из (1)
   *
   * @param vkurltoken (1) --
   */
  public static void update(VkUrltoken vkurltoken) {
    U.se(vkurltoken == null, "");
    U.se(!vkurltoken.isValid(), "");
    //---
    vktokenSet(vkurltoken.getVktoken());
    useridSet(vkurltoken.getUserid());
    Vdin_01.logM("обновлены элементы VkDatas //01.05.2019-13:02-40", VkDatas.class);
    //---
  }

  private static void vktokenSet(String vktoken) {
    TSharprefCommon_01.putString(SHP_VKTOKEN_VALUE, vktoken == null ? "" : vktoken);
  }

  private static void useridSet(String userid) {
    TSharprefCommon_01.putString(SHP_USERID_VALUE, userid == null ? "" : userid);
  }

  public static String vktokenGet() {
    //return "2c6ad58dea74e3ce6fe8d2be2db8e47c406bdfaa83964d599d94351cd7e6318e2025d62f42b63060b53f0"; // back
    return TSharprefCommon_01.getString(SHP_VKTOKEN_VALUE);
  }

  public static String useridGet() {
    return TSharprefCommon_01.getString(SHP_USERID_VALUE);
  }

  /**
   * Возвращает TRUE если аутентификационные данные (_vktoken) получены
   *
   * @return --
   */
  public static boolean isDatasExist() {
    return vktokenGet().length() > 0;
  }

}

package ru.diatest.vk.vkapi;

import ru.surrsoft.baaz.univers.U;

/**
 * Представляет _urlreq ([tvum])
 * <p>
 * ФОРМАТ _urlreq:
 * https://api.vk.com/method/METHOD_NAME?PARAMETERS&access_token=ACCESS_TOKEN&v=V
 * <p>
 * см. https://vk.com/dev/api_requests
 */
public class VkUrlreq {

  public VkUrlreq() {
  }

  /**
   * @param eMethod (1) --
   * @param userid  (2) -- в методе {@link VkEMethods#PHOTOS_GET} это _userid пользователя чьи
   *                фотографии мы хотим получить. В остальных методах не используется
   * @return --
   */
  public static String create(VkEMethods eMethod, String userid) {
    U.se(eMethod == null, "");
    //---
    String stMethodName = "";
    String stParams = "";
    //---
    switch (eMethod) {
      case FRIENDS_GET:
        stMethodName = "friends.get";
        stParams = "fields=city,domain,photo_100"
          + "&count=0"; //0 - все
        break;
      case PHOTOS_GET:
        stMethodName = "photos.get";
        stParams = "owner_id=" + userid
          + "&album_id=wall"  //фотографии со стены
          + "&rev=1"
          + "&extended=0"
          + "&photo_sizes=0"
          + "&count=1000";
        break;
    }
    //---
    return "https://api.vk.com/method/" + stMethodName + "?" +
      (stParams.length() > 0 ? stParams + "&" : "") +
      "access_token=" + VkDatas.vktokenGet() +
      "&v=" + VkConfigs.API_VERSION;
  }
}

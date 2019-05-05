package ru.diatest.vk.vkapi;

/**
 * _urlauth
 * <p>
 */
public class VkUrlauth {

  private String stBase = "https://oauth.vk.com/authorize";

  private String stAppID = VkConfigs.APPID;

  private String stVersion = VkConfigs.API_VERSION;

  private String stScope = "friends";

  private boolean revoke;

  //--- constructors

  public VkUrlauth() {
  }

  //---

  /**
   * Если (1) == TRUE то не будет пропускаться этап подтверждения прав, даже если пользователь уже
   * авторизован
   *
   * @param b (1) --
   * @return --
   */
  public VkUrlauth buRevoke(boolean b) {
    this.revoke = b;
    return this;
  }

  public String build() {
    String ret = stBase + "?"
      + "client_id=" + stAppID
      + "&display=mobile"
      + "&redirect_uri=https://oauth.vk.com/blank.html"
      + "&scope=" + stScope
      + "&response_type=token"
      + "&v=" + stVersion
      + "&state=001";
    //---
    if (revoke) {
      ret += "&revoke=1";
    }
    //---
    return ret;
  }

}

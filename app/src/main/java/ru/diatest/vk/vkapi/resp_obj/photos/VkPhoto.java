package ru.diatest.vk.vkapi.resp_obj.photos;

import java.util.ArrayList;

/**
 * //needdesc
 * <p>
 * ИНФО:
 * -- https://vk.com/dev/objects/photo
 */
public class VkPhoto {
  /**
   * Идентификатор фортографии
   */
  public int id;
  /**
   * Идентификатор альбома в котором находится фотография
   */
  public int album_id;

  /**
   * Описание фотографии
   */
  public String text;

  /**
   * Дата добавления фотографии
   */
  public int date;

  /**
   * Информация об изображениях в разных размерах
   */
  public ArrayList<VkPhotoSize> sizes;

  public String getPhotoFirstUrl() {
    return sizes.get(0).url;
  }

  public String getPhotoMaxSizeUrl() {
    String ret = "";
    int w = 0;
    int h = 0;
    //---
    for (VkPhotoSize size : sizes) {
      if (size.width > w || size.height > h) {
        ret = size.url;
      }
    }
    //---
    return ret;
  }

}

package ru.diatest.vk.vkapi.resp_obj.friends;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Используется одновременно как:
 * -- модель информации о пользователе ВКонтакте
 * -- модель для таблицы БД
 * <p>
 * ИНФО:
 * -- https://vk.com/dev/objects/user
 */
@Entity(tableName = "vkusers")
public class VkUser {
  @PrimaryKey(autoGenerate = true)
  public int uid;

  @ColumnInfo(name = "userid")
  public long id;

  @ColumnInfo(name = "first_name")
  public String first_name;

  @ColumnInfo(name = "last_name")
  public String last_name;

  @ColumnInfo(name = "online")
  public int online;

  @ColumnInfo(name = "photo_100")
  public String photo_100;

}

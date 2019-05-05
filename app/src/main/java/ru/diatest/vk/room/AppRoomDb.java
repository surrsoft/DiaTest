package ru.diatest.vk.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ru.diatest.vk.vkapi.VkItemDao;
import ru.diatest.vk.vkapi.resp_obj.friends.VkUser;

/**
 * //needdesc
 * <p>
 * //new//
 */
@Database(entities = {VkUser.class}, version = 1, exportSchema = false)
public abstract class AppRoomDb extends RoomDatabase {
  public static final String DB_NAME = "db_190501_181600";

  public abstract VkItemDao getVkUserDao();
}

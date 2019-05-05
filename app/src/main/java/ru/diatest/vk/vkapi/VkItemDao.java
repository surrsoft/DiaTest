package ru.diatest.vk.vkapi;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

import ru.diatest.vk.vkapi.resp_obj.friends.VkUser;

/**
 * DAO
 */
@Dao
public interface VkItemDao {
  @Query("SELECT * FROM vkusers")
  List<VkUser> getAll();

  @Query("SELECT * FROM vkusers LIMIT 1 OFFSET :index")
  VkUser getByIndex(int index);


  @Query("SELECT count(*) FROM vkusers")
  long getCount();

  /**
   * Удаление из таблицы всех записей
   */
  @Query("DELETE FROM vkusers")
  void clear();

  @Insert
  void insertAll(ArrayList<VkUser> items);

  @Delete
  void delete(VkUser cardRoom);

}

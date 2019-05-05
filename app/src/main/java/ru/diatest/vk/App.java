package ru.diatest.vk;

import android.app.Application;
import android.arch.persistence.room.Room;

import okhttp3.OkHttpClient;
import ru.diatest.vk.room.AppRoomDb;
import ru.surrsoft.baaz.Bysa_01;

/**
 * //needdesc
 * <p>
 * //new//
 */
public class App extends Application {

  public static OkHttpClient okHttpClient;
  public static AppRoomDb dbRoom;

  @Override
  public void onCreate() {
    super.onCreate();

    //--- [bysa]
    Bysa_01.initDefault(this);

    //--- okhttp
    okHttpClient = new OkHttpClient();

    //--- Room
    dbRoom = Room.databaseBuilder(this, AppRoomDb.class, AppRoomDb.DB_NAME)
      //разрешить запросы из main-потока
      .allowMainThreadQueries()
      .build();

  }
}

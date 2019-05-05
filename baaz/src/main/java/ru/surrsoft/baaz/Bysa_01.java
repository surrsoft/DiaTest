package ru.surrsoft.baaz;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.support.annotation.StringRes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.threetenabp.AndroidThreeTen;

import ru.surrsoft.baaz.univers.U;

/**
 * [[bysa]]. Хранит различные установки конкретного приложения.
 * <p>
 * ИСПОЛЬЗОВАНИЕ: наполняется в App (можно просто воспользоваться методом {@link #initDefault(Application)} );
 * затем передаётся в библиотечные методы [baaz] завязанные
 * на установках конкретного приложения.
 * Следует использовать метод {@link #verify()} для проверки что все поля инициализированы.
 */
public class Bysa_01 {

  /**
   * Контекст установленный в App
   */
  public static Context appContext;

  /**
   *
   */
  public static float fDensity;

  /**
   *
   */
  public static float fScaledDensity;

  /**
   * [[ppmk]] - свой универсальный SharedPreferences. Имя - {@link TypeConsts#SHARPREF_COMMON_NAME}
   */
  public static SharedPreferences sharprefCommon;

  /**
   * Экземпляр Gson созданного так "new Gson();"
   */
  public static Gson gson;

  /**
   * Экземпляр Gson созданного так "new GsonBuilder.setPrettyPrinting().create()".
   * Такой экземпляр обычно используется в toString() для красивого показа (с отступами) структуры
   * классов
   */
  public static Gson gsonPretty;

  /**
   *
   */
  public static String[] confLanguagesList;

  /**
   * Ссылка на ресурсы
   */
  public static Resources res;

  /**
   *
   */
  public static AssetManager assets;

  //constructors
  //````````````````````````````````````````````````````````````````````````````````````````````````
  public Bysa_01() {
  }

  //````````````````````````````````````````````````````````````````````````````````````````````````

  /**
   * Типовая инициализация [bysa]
   *
   * @param appContext (1) -- ссылка на App
   */
  public static void initDefault(Application appContext) {
    U.se(appContext == null, "");
    //---
    Bysa_01.appContext = appContext;
    Bysa_01.res = appContext.getResources();
    Bysa_01.fDensity = appContext.getResources().getDisplayMetrics().density;
    Bysa_01.fScaledDensity = appContext.getResources().getDisplayMetrics().scaledDensity;
    Bysa_01.sharprefCommon = appContext.getSharedPreferences(TypeConsts.SHARPREF_COMMON_NAME, 0);
    Bysa_01.gson = new Gson();
    Bysa_01.gsonPretty = new GsonBuilder().setPrettyPrinting().create();
    Bysa_01.confLanguagesList = new String[]{"en", "ru"};
    Bysa_01.assets = appContext.getAssets();
    //--- инициализация временнЫх зон библиотеки работы со временем "ThreeTenABP"
    AndroidThreeTen.init(appContext);
    //---
    Bysa_01.verify();
  }

  /**
   * Следует вызывать этот метод в App для проверки что инициализированны все поля Bysa
   */
  public static void verify() {
    U.se(gson == null, "gson == null");
    U.se(gsonPretty == null, "gsonPretty == null");
    U.se(appContext == null, "appContext == null");
    U.se(fDensity == 0f, "fDensity == 0f");
    U.se(fScaledDensity == 0f, "fScaledDensity == 0f");
    U.se(sharprefCommon == null, "sharprefCommon == null");
    U.se(confLanguagesList == null, "confLanguagesList == null");
    U.se(res == null, "res == null");
    U.se(assets == null, "assets == null");
  }


  /**
   * Получение строки (1) из ресурсов
   *
   * @param iStringResId (1) --
   * @return --
   */
  public static String resStringGet(@StringRes int iStringResId) {
    return Bysa_01.res.getString(iStringResId);
  }

}

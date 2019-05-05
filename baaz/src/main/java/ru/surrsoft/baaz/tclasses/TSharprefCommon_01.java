package ru.surrsoft.baaz.tclasses;

import android.support.annotation.NonNull;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import ru.surrsoft.baaz.Bysa_01;
import ru.surrsoft.baaz.univers.U;

/**
 * Статические методы для работы с {@link Bysa_01#sharprefCommon}
 * <p>
 * ПОНЯТИЯ:
 * -- _хранилище - это {@link Bysa_01#sharprefCommon}
 * <p>
 * //new//
 */
public class TSharprefCommon_01 {

  /**
   * Служебный интерфейс для получения списка элементов типа T
   *
   * @param <T>
   */
  public interface IListDefaultGet<T> {
    ArrayList<T> get();
  }

  /**
   * Если в _хранилище нет записи с ключом (1), то сериализует список (3) и записывает его в
   * _хранилище с ключом (1); и так же возвращает список (3).
   * <p>
   * Если же в _хранилище уже есть запись с ключом (1), то извлекает из _хранилища строку по ключу
   * (1), десериализует эту строку в список и возвращает этот список
   *
   * @param stKey         (1) --
   * @param gsonTypeToken (2) -- например "new TypeToken<ArrayList<Elem>>() {}" (фигурные скобки на
   *                      конце это не ошибка)
   * @param defaultList   (3) --
   * @param <T>           -- тип элементов списка (3)
   * @return --
   */
  @NonNull
  public static <T> ArrayList<T> listInit(String stKey,
                                          TypeToken<ArrayList<T>> gsonTypeToken,
                                          IListDefaultGet<T> defaultList) {
    //---
    U.se(TString_01.isEmpty(stKey), "");
    U.se(defaultList == null, "");
    U.se(gsonTypeToken == null, "");
    //---
    if (Bysa_01.sharprefCommon.contains(stKey)) {
      String stJson = Bysa_01.sharprefCommon.getString(stKey, "");
      //---
      U.se(TString_01.isEmpty(stJson), "");
      //---
      return Bysa_01.gson.fromJson(stJson, gsonTypeToken.getType());
    }
    //---
    ArrayList<T> list = defaultList.get();
    String stJson = Bysa_01.gson.toJson(list);
    Bysa_01.sharprefCommon.edit().putString(stKey, stJson).apply();
    //---
    return list;
  }

  /**
   * Сериализует список (2) и записывает его в _хранилище с ключом (1)
   *
   * @param stKey (1) --
   * @param list  (2) --
   */
  public static void listPut(String stKey, ArrayList list) {
    U.se(TString_01.isEmpty(stKey), "");
    U.se(list == null, "");
    //---
    String stJson = Bysa_01.gson.toJson(list);
    Bysa_01.sharprefCommon.edit().putString(stKey, stJson).apply();
  }

  /**
   * Удаление из _хранилища записи по ключу (1)
   * <p>
   * #errors см. в коде
   *
   * @param stKey (1) --
   */
  public static void remove(String stKey) {
    U.se(TString_01.isEmpty(stKey), "");
    //---
    Bysa_01.sharprefCommon.edit().remove(stKey).apply();
  }

  /**
   * Добавление в _хранилище записи с ключом (1) и значением (2)
   *
   * @param stKey   (1) -- null и пустая строка не допустимы
   * @param stValue (2) -- если null то записывается пустая строка
   */
  public static void putString(String stKey, String stValue) {
    U.se(TString_01.isEmpty(stKey), "");
    //---
    Bysa_01.sharprefCommon.edit().putString(stKey, stValue == null ? "" : stValue).apply();
  }

  /**
   * Получение строки из _хранилища по ключу (1)
   *
   * @param stKey (1) --
   * @return -- пустая строка если ключ (1) в _хранилище не найден
   */
  public static String getString(String stKey) {
    U.se(TString_01.isEmpty(stKey), "");
    //---
    return Bysa_01.sharprefCommon.getString(stKey, "");
  }

}

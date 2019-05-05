package ru.surrsoft.baaz.w265w_configer;

import android.content.Context;
import android.support.annotation.Nullable;

import ru.surrsoft.baaz.Bysa_01;
import ru.surrsoft.baaz.cls_b.Zzca_01;
import ru.surrsoft.baaz.cls_d.Gwmv_01;
import ru.surrsoft.baaz.univers.IApply;

/**
 * Created by евген on 15.06.2016.
 */
public class N2265C_Db extends Gwmv_01 {


  public N2265C_Db(Context context, String dbName, int version) {
    super(context, dbName, version);
  }

  /**
   * Получение типа указанного в (2) по ключу (1). Если по ключу не находит то возвращает новый класс
   *
   * @param <T>         (4) --
   * @param key         (1) -- ключ
   * @param clazz       (2) -- тип который нужно получить
   * @param forceCreate (3) -- TRUE чтобы создать объект если по ключу (1) ничего не найдено
   * @return -- возможен null
   */
  @Nullable
  public <T> T yGet(String key, Class<T> clazz, boolean forceCreate) {
    T ret = null;
    Zzca_01 dbval = getString(key, null);
    if (dbval.readed) {
      ret = Bysa_01.gson.fromJson(dbval.val_string, clazz);
    }
    if (forceCreate) {
      if (ret == null) try {
        ret = clazz.newInstance();
        if (ret instanceof IApply) {
          ((IApply) ret).commit_w282w(); //применение _ontoper (_наслоения)
        }
      } catch (InstantiationException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }
    return ret;
  }

  public String yAsJsonString(String key, Class clazz) {
    String ret = "";
    Zzca_01 dbval = getString(key, null);
    if (dbval.readed) {
      ret = dbval.val_string;
    }
    return ret;
  }
}

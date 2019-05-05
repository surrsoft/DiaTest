package ru.surrsoft.baaz.cls_d;

import ru.surrsoft.baaz.cls_b.Zzca_01;

/**
 * SharedPreferences-подобная сущность предназначенная для использования в качестве _хранилища _пар.
 * <p>
 * ОГРАНИЧЕНИЯ:
 * -- _ключи уникальны, имеют тип String
 * -- _ключём может быть какая угодно строка, а так же null
 * -- при добавлении _пары сначала проверяется есть ли уже такая _пара в _хранилище (проверка выполняется по _ключу).
 * Если есть, то обновляется _значение _пары в _хранилище, если нет - добавляется новая _пара в _хранилище.
 * -- за операцию добавления _пары отвечают методы "put..."
 * -- за операцию получения _пары из _хранилища отвечают методы "get..."
 * -- _значения могут быть одного из 5 типов - String, int, float, long, boolean
 * -- _значения выражаются инкарнацией {@link Zzca_01}
 * <p>
 * ПОНЯТИЯ:
 * -- [[pgui]], _хранилище
 * -- [[cnbr]], _пара - пара из _ключа и _значения
 * -- [[hprb]], _ключ, _key
 * -- [[ebef]], _значение, _val
 * <p>
 * [[nmhn]], старые названия {"N2166Storage", [[w166w]]}
 * <p>
 * КОДЫ: [neoi]
 * <p>
 * РЕАЛИЗАЦИИ:
 * -- {@link Gwmv_01} - в качестве места хранения используется БД
 */
public interface Nmhn_01 {

  //--- put... возвращают 0 если была обновлена существующая _пара, 1 - если была добавлена новая
  int putString(String key, String val);

  int putInt(String key, int val);

  int putFloat(String key, float val);

  int putLong(String key, long val);

  int putBoolean(String key, boolean val);

  //--- get...

  /**
   * Получение {значения} по {ключу}
   *
   * @param key    (1) -- _ключ
   * @param defVal (2) -- значение по-умолчанию; подставляется если считывание
   *               фактического _значения не произошло т.к. запись не была найдена (когда readed=false)
   * @return специальный объект; если у него поле "readed=false" то это значит что
   * считывания значения не просходило т.к. запись не удалось найти по _ключу (1),
   * в этом случае в соответствующем поле "zzca.val_.." будет значение (2)
   */
  Zzca_01 getString(String key, String defVal);

  Zzca_01 getInt(String key, int defVal);

  Zzca_01 getLong(String key, long defVal);

  Zzca_01 getFloat(String key, float defVal);

  Zzca_01 getBoolean(String key, boolean defVal);

  //---

  /**
   * Удалание _пары с ключём (1)
   *
   * @param key (1) --
   * @return количество удалённых _пар (тут должно быть число 0 или 1)
   */
  int remove(String key);

  /**
   * Удаление всех записей
   *
   * @returns количество удаленных _пар
   */
  int clear();

  /**
   * Проверка существования _пары с _ключём (1)
   *
   * @param key (1)
   * @return true если _пара существует, иначе false
   */
  boolean contains(String key);
}

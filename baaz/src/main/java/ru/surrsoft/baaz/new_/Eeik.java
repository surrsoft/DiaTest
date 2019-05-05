package ru.surrsoft.baaz.new_;

import ru.surrsoft.baaz.Bysa_01;
import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.univers.IUnicum;


/**
 * Организатор хранения массивов.
 * По сути это посредник для хранилища SharedPreferences.
 * <p>
 * [[eeik]]
 * <p>
 * [[w467w]]
 * <p>
 * [zipc]
 *
 * @param <T> - тип элементов которые будут храниться в хранилище
 */
public abstract class Eeik<T> {
    private static final String FIRST_START = "_const0846";

    private static final String TAG = ":" + Eeik.class.getSimpleName();


    //==============================================================================================
    //constructors
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public Eeik() {
        n1467_init();
    }

    //``````````````````````````````````````````````````````````````````````````````````````````````
    private void n1467_init() {
        if (n1467_getKey() == null) {
            throw new SomeException("(debug)");
        }
        //===
        boolean firstStart = Bysa_01.sharprefCommon.getBoolean(n1467_getKey() + FIRST_START, true);
        if (firstStart) {
            Bysa_01.sharprefCommon.edit().putBoolean(n1467_getKey() + FIRST_START, false).apply();
            //==
            T[] arr = n1467_createFirstStartDataset();
            //==
            String json = Bysa_01.gson.toJson(arr);
            Bysa_01.sharprefCommon.edit().putString(n1467_getKey(), json).apply();
        }
    }

    /**
     * Сохраняет массив (1) в хранилище
     *
     * @param elems (1) --
     */
    public void saveElems(T[] elems) {
        String json = Bysa_01.gson.toJson(elems);
        Bysa_01.sharprefCommon.edit().putString(n1467_getKey(), json).apply();
    }

    public void saveElem(T savedElem, Class<T[]> cls) {
        if (!(savedElem instanceof IUnicum)) {
            throw new SomeException("(debug) этот метод можно использовать только с объектами " +
                    "реализующими интерфейс IUnicum");
        }
        IUnicum e = (IUnicum) savedElem;
        String ugez = e.getUgez();
        T[] elems = getElems(cls);
        boolean b = false;
        for (int i = 0; i < elems.length; i++) {
            T e1 = elems[i];
            if (((IUnicum) e1).getUgez().equals(ugez)) {
                elems[i] = savedElem;
                b = true;
                break;
            }
        }
        if (b) {
            saveElems(elems);
        }
    }

    /**
     * Ключ по которому в Bysa_01.sharprefCommon храняться/будут-храниться элементы
     *
     * @return --
     */
    public abstract String n1467_getKey();

    /**
     * Создание стартового набора используемого только при "девственном" запуске приложения
     *
     * @return --
     */
    public abstract T[] n1467_createFirstStartDataset();

    /**
     * Возвращаение элементов в виде JSON-строки
     *
     * @return --
     */
    public String getElemsJson() {
        return Bysa_01.sharprefCommon.getString(n1467_getKey(), null);
    }

    /**
     * Возвращение элементов
     *
     * @param cls (1) -- например X[].class; Т это должен быть тот же тип которым генерализован класс
     * @return --
     */
    public T[] getElems(Class<T[]> cls) {
        String json = Bysa_01.sharprefCommon.getString(n1467_getKey(), null);
        return Bysa_01.gson.fromJson(json, cls);
    }

    /**
     * Получение элемента по индексу
     *
     * @param cls (1) --
     * @param ix  (2) --
     * @return --
     */
    public T getElemByIndex(Class<T[]> cls, int ix) {
        return getElems(cls)[ix];
    }
}

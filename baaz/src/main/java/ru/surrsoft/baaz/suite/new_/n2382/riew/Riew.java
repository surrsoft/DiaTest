package ru.surrsoft.baaz.suite.new_.n2382.riew;

import android.support.annotation.NonNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import ru.surrsoft.baaz.cls_c.G67G_Reflect;
import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.suite.new_.misc._Etof;
import ru.surrsoft.baaz.tclasses.TClass_01;
import ru.surrsoft.baaz.tclasses.TField_01;
import ru.surrsoft.baaz.univers.Unox;

/**
 * [[riew]] - преобразователь [wira] -> [zruf]
 * <p>
 * ОПИСАНИЕ:
 * Выполняет проход по всему дереву-полей объекта ([wira]) на всю
 * глубину; для каждого поля формирует дескриптор [omsp]; отбираются только поля помеченные
 * аннотацией {@link Fnog}; обладает защитой от зацикливания (см. [bcnc])
 * <p>
 * ИСПОЛЬЗОВАНИЕ:
 * инстанцировать Riew; задать билд-методом {@link #dpit(Class)} класс который нужно "выпрямить";
 * вызвать {@link #make()}
 * <p>
 * <p>
 * ПОНЯТИЯ:
 * (-) [[zruf]] - это "выпрямленный" ("плоский") класс ([wira]) представленный в виде списка
 * ArrayList^Omsp^; элементы этого списка образуют множество {@link _Etof} благодаря имплементации
 * {@link Unox}
 * (-) [[dpit]] - класс ([wira]) подготовленный для превращения его в [zruf]; у такого класса должны
 * быть поля помеченные аннотацией {@link Fnog}
 * СМ. ТАКЖЕ:
 * -- класс "Ekur"
 * <p>
 * ВЕРСИЯ 1 1.0 2019-02-06 (stored)
 */
public class Riew {

    //=== защита от зацикливания ([bcnc])
    private HashMap<Class, Void> mMapBcnc;

    //===
    /**
     *
     */
    private ArrayList<Omsp> mZruf = new ArrayList<>();

    /**
     * Класс который нужно "выпрямить"
     */
    private Class mDpit;

    //constructors
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public Riew() {
    }

    //builders
    //``````````````````````````````````````````````````````````````````````````````````````````````

    /**
     * Задание класса который нужно "выпрямить" - (1)
     *
     * @param dpit (1) -- класс который нужно "выпрямить"
     * @return --
     */
    public Riew dpit(Class dpit) {
        mDpit = dpit;
        return this;
    }

    //make
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public ArrayList<Omsp> make() {
        if (mDpit == null) {
            throw new SomeException("(debug) mCls == null; нужно задать билдером класс который нужно 'выпрямить'");
        }
        //---
        mMapBcnc = new HashMap<>();
        recurs(mDpit, null);
        //---
        return mZruf;
    }

    //private
    //``````````````````````````````````````````````````````````````````````````````````````````````
    private void recurs(Class cls, Omsp omspParent) {
        mMapBcnc.put(cls, null);
        Field[] fields = TClass_01.getFields_C(cls);
        for (Field eField : fields) { //ЦИКЛ
            //пропускаем поля не помеченные аннотацией Fnog
            if (!eField.isAnnotationPresent(Fnog.class)) {
                continue;
            }
            //---
            if (G67G_Reflect.isMnae(eField)) {
                mtAddOmsp(omspParent, eField);
            }
            //---
            Class clsField = null;
            if (G67G_Reflect.isMwuw(eField)) {
                clsField = TField_01.dboeGet(eField);
            } else if (G67G_Reflect.isUeun(eField)) {
                clsField = G67G_Reflect.getTypeParametrOfGeneric(eField);
            }
            //-
            if (clsField != null) {
                if (!mMapBcnc.containsKey(clsField)) {
                    Omsp omsp = mtAddOmsp(omspParent, eField);
                    recurs(clsField, omsp);                         //<=== RECURSION
                }
            }
            //---
        } //ЦИКЛ
        mMapBcnc.remove(cls);
    }

    @NonNull
    private Omsp mtAddOmsp(Omsp omspParent, Field eField) {
        Omsp omsp = new Omsp();
        omsp.setField(eField);
        omsp.setNksc(omspParent);
        mZruf.add(omsp);
        return omsp;
    }

}

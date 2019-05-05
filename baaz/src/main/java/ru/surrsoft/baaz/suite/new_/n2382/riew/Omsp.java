package ru.surrsoft.baaz.suite.new_.n2382.riew;

import android.support.annotation.Nullable;

import java.lang.reflect.Field;

import ru.surrsoft.baaz.univers.Unox;

/**
 * [[omsp]] - дескриптор поля класса ([wira])
 * <p>
 * ВЕРСИЯ 1 1.0 2019-02-06 (stored)
 */
public class Omsp implements Unox {

    /**
     *
     */
    private Omsp mNksc;
    /**
     *
     */
    private Field mField;

    //[unox]
    //``````````````````````````````````````````````````````````````````````````````````````````````
    @Nullable
    @Override
    public Omsp unoxGetNksc() {
        return mNksc;
    }


    //``````````````````````````````````````````````````````````````````````````````````````````````
    public Field getField() {
        return mField;
    }

    public void setField(Field field) {
        mField = field;
    }

    public void setNksc(Omsp nksc) {
        this.mNksc = nksc;
    }
}

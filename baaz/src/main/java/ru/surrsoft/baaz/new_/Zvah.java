package ru.surrsoft.baaz.new_;

import android.support.annotation.Nullable;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

import ru.surrsoft.baaz.SomeException;


/**
 * [[zvah]] - определяет универсальную сущность с _состояними. Подразумевает одиночный выбор.
 * _Состояниям можно задавать соответствия в виде _значений
 * <p>
 * ПОНЯТИЯ
 * <li> _состояние (_state) -  </li>
 * <li> _значение (_value) -  </li>
 * <p>
 * [zipc]
 */
public class Zvah {
    private String[] mIevzs;
    private String mCurrIevz;
    private String[] mUxirs;

    //==============================================================================================

    //``````````````````````````````````````````````````````````````````````````````````````````````

    /**
     * @param ievzs (1) -- [ievz]s
     * @return --
     */
    public Zvah ievzs(String[] ievzs) {
        mIevzs = ievzs;
        mCurrIevz = ievzs[0];
        return this;
    }

    /**
     * @param uxirs (1) -- [uxir]s
     * @return --
     */
    public Zvah uxirs(String[] uxirs) {
        mUxirs = uxirs;
        return this;
    }

    /**
     * Если не задано иначе, текущим является первый элемент {@link #mIevzs}[0]
     *
     * @param state (1) -- [ievz]
     * @return --
     */
    public Zvah currIevz(String state) {
        mCurrIevz = state;
        return this;
    }

    /**
     * @param ovdes (1) -- упорядоченная коллекция из единичных [ovde]
     * @return --
     */
    public Zvah ovdes(LinkedHashMap<String, String> ovdes) {
        mIevzs = new String[]{};
        mUxirs = new String[]{};
        //===
        for (Map.Entry<String, String> elem : ovdes.entrySet()) {
            mIevzs = ArrayUtils.add(mIevzs, elem.getKey());
            mUxirs = ArrayUtils.add(mUxirs, elem.getValue());
        }
        return this;
    }

    //create
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public Zvah create() {
        if (mIevzs == null || mIevzs.length < 1) {
            throw new SomeException("(debug)");
        }

        //===
        if (mCurrIevz == null) throw new SomeException("(debug)");

        //=== проверка уникальности состояний; регистр символов имеет значение
        HashSet<String> set = new HashSet<>();
        Collections.addAll(set, mIevzs);
        if (set.size() != mIevzs.length) throw new SomeException("(debug) ключи не уникальны");

        //=== проверка наличия стартового состояния в списке состояний
        if (ArrayUtils.indexOf(mIevzs, mCurrIevz) == -1) {
            throw new SomeException("(debug) состояния " + mCurrIevz + " нет в списке состояний");
        }

        //===
        return this;
    }

    //commands
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public String _getCurrIevz() {
        return mCurrIevz;
    }

    public String _getCurrUxir() {
        String ievz = _getCurrIevz();
        int i = ArrayUtils.indexOf(mIevzs, ievz);
        return mUxirs[i];
    }

    public int _getCurrIndex() {
        String ievz = _getCurrIevz();
        return ArrayUtils.indexOf(mIevzs, ievz);
    }

    public void _setCurrIndex(int index) {
        mCurrIevz = mIevzs[index];
    }

    public void _setCurrIevz(String ievz) {
        mCurrIevz = ievz;
    }

    @Nullable
    public String[] _getIevzs() {
        return mIevzs;
    }

    public String[] _getUxirs() {
        return mUxirs;
    }

    //``````````````````````````````````````````````````````````````````````````````````````````````

}

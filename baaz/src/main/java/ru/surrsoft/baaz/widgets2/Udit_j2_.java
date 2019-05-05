package ru.surrsoft.baaz.widgets2;

import org.apache.commons.lang3.ArrayUtils;

/**
 * [[udit]]
 * <p>
 * Обертка над коллекцией из [gsbe]s
 * <p>
 *
 */
public class Udit_j2_ {

    private Gsbe_j_[] mGsbes = {};

    //==============================================================================================
    public Udit_j2_ add(Gsbe_j_ gsbe) {
        mGsbes = ArrayUtils.add(mGsbes, gsbe);
        return this;
    }

    /**
     *
     * @param _key (1) --
     * @return NULL если не находит
     */
    public Gsbe_j_ get(String _key) {
        for (Gsbe_j_ elem : mGsbes) {
            if (elem.getKey().equals(_key)) {
                return elem;
            }
        }
        return null;
    }

    public boolean _exist(String _key) {
        for (Gsbe_j_ elem : mGsbes) {
            if (elem.getKey().equals(_key)) {
                return true;
            }
        }
        return false;
    }

    public int getCount(){
        return mGsbes.length;
    }
}

package ru.surrsoft.baaz.univers;

import android.view.Gravity;

import org.apache.commons.lang3.ArrayUtils;

/**
 * [[EGravity]]
 *
 * ВЕРСИЯ 1 1.0 2019-02-05 (stored)
 */
public enum EGravity {
    CH(Gravity.CENTER_HORIZONTAL, "CENTER_HORIZONTAL"),
    CV(Gravity.CENTER_VERTICAL, "CENTER_VERTICAL"),
    C(Gravity.CENTER, "CENTER"),
    L(Gravity.LEFT, "LEFT"),
    RI(Gravity.RIGHT, "RIGHT"),
    S(Gravity.START, "START"),
    E(Gravity.END, "END"),
    T(Gravity.TOP, "TOP"),
    B(Gravity.BOTTOM, "BOTTOM"),
    ;

    public final int val;
    public final String str;

    EGravity(int val, String str) {
        this.val = val;
        this.str = str;
    }

    public static Cls[] asIGetStrings(){
        Cls[] ret = {};
        for (EGravity elem : values()) {
            Cls cls = new Cls();
            cls.str = elem.str;
            cls.gravity = elem.val;
            ret = ArrayUtils.add(ret, cls);
        }
        return ret;
    }

    /**
     *
     * @param gravity (1) --
     * @return -1 если не найдено
     */
    public static int getIxByGravity(int gravity) {
        int ret = -1;
        for (EGravity elem : values()) {
            if(gravity == elem.val){
                ret = elem.ordinal();
                break;
            }
        }
        return ret;
    }

    public static class Cls implements IGet {
        public String str;
        public int gravity;

        @Override
        public String n1454_getString() {
            return str;
        }
    }
}

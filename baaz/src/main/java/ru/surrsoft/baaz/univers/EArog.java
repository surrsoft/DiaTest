package ru.surrsoft.baaz.univers;

import org.apache.commons.lang3.ArrayUtils;

/**
 * [[arog]], _соотнесение
 * <p>
 * Константы БОЛЬШЕ, МЕНЬШЕ, СОДЕРЖИТ и т.д.
 * <p>
 * ВЕРСИЯ 1 2019-02-04 (stored)
 */
public enum EArog {

    БОЛЬШЕ(EStrings._1_БОЛЬШЕ.val()),
    БОЛЬШЕ_ИЛИ_РАВНО(EStrings._1_БОЛЬШЕ_ИЛИ_РАВНО.val()),
    МЕНЬШЕ(EStrings._1_МЕНЬШЕ.val()),
    МЕНЬШЕ_ИЛИ_РАВНО(EStrings._1_МЕНЬШЕ_ИЛИ_РАВНО.val()),
    СОДЕРЖИТ(EStrings._1_СОДЕРЖИТ.val()),
    НАЧИНАЕТСЯ_С(EStrings._1_НАЧИНАЕТСЯ_С.val()),
    ЗАКАНЧИВАЕТСЯ_НА(EStrings._1_ЗАКАНЧИВАЕТСЯ_НА.val()),
    РАВНО(EStrings._1_РАВНО.val()),
    ПУСТО(EStrings._1_ПУСТО.val());

    //fields
    //``````````````````````````````````````````````````````````````````````````````````````````````

    /**
     * [[auan]] - текстовое локализованное представление константы
     */
    public String stAuan;

    //constructors
    //``````````````````````````````````````````````````````````````````````````````````````````````

    EArog(String stAuan) {
        this.stAuan = stAuan;
    }

    //
    //``````````````````````````````````````````````````````````````````````````````````````````````

    public static String[] getAuans() {
        String[] ret = {};
        for (EArog eArog : EArog.values()) {
            ret = ArrayUtils.add(ret, eArog.stAuan);
        }
        return ret;
    }

}

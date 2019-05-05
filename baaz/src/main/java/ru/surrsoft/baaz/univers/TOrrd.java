package ru.surrsoft.baaz.univers;

/**
 * ОПИСАНИЕ: утилитные методы для работы с типом [orrd]
 * <p>
 * ПОНЯТИЯ
 * <li> [[orrd]] - сущность представляющая собой строковую последовательность с разделителями, например "aaa/bbb/ccc", где
 * "/" это разделитель ([snec]), а "aaa,bbb,ccc" это элементы ([dhic])</li>
 * <li> [[dhic]] - элементы располагающиеся между разделителями, например "aaa" </li>
 * <li> [[snec]] - разделитель, например "/" </li>
 * <p>
 * ВЕРСИЯ 1 1.0 2019-02-05 (stored)
 */
public class TOrrd {
    private String mSnec;

    //constructors
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public TOrrd(String snec) {
        mSnec = snec;
    }

    //``````````````````````````````````````````````````````````````````````````````````````````````

    /**
     * Приплюсовывает в конец [orrd] (1) элемент [dhic] (2)
     * @param orrd (1) -- [orrd]
     * @param dhic (2) -- [dhic]
     * @return [orrd]
     */
    public String add(String orrd, String dhic) {
        if (orrd.length() < 1) {
            return dhic;
        } else {
            return orrd + mSnec + dhic;
        }
    }

}

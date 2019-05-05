package ru.surrsoft.baaz.tclasses;

import java.lang.reflect.Field;

import ru.surrsoft.baaz.cls_c.G67G_Reflect;
import ru.surrsoft.baaz.suite.terms.w359w.EPead;

/**
 * Утилитные методы для работы с типом "java.lang.reflect.Field"
 * <p>
 * ПОНЯТИЯ ЛОКАЛЬНЫЕ:
 * <li> _поле - поле объекта. Класс java.lang.reflect.Field является "отражением" этого поля </li>
 * <p>
 * ПОНЯТИЯ:
 * -- ^[[bnvn]] - тип которому непосредсвенно принадлежит поле. Например если у класса А есть поле Б то
 * А для Б является [bnvn]
 * <p>
 * ВЕРСИЯ 1 1.0 2019-02-05 (stored)
 */
public class TField_01 {

    /**
     * Возвращает [[dboe]] - тип данных хранящихся в Field (1)
     * <p>
     * ПРИМЕР:
     * предположим что некоторого класса есть поле "B b" которое инкапсулировано в виде "Field a".
     * Тогда
     * dboeGet(a) вернёт ссылку на "B" ([dboe]) т.е. "B b = TField.getClass(a);"
     *
     * @param f (1) --
     * @return -- [wira]
     */
    public static Class<?> dboeGet(Field f) {
        return f.getType();
    }


    /**
     * Извлечение из (1) значения ([rumz]) _поля
     *
     * @param f  (1) --
     * @param oj (2) -- объект обладающий полем (1)
     * @return --
     * @throws IllegalAccessException
     */
    public static Object getRumz(Field f, Object oj) throws IllegalAccessException {
        return f.get(oj);
    }


    /**
     * Извлечение из (1) значения ([rumz]) _поля
     * <p>
     * Отличие от А - не бросает ислкючение при проблемах, просто возврщает NULL в этом случае
     *
     * @param f  (1) --
     * @param oj (2) -- объект обладающий полем (1)
     * @return NULL при нештатах
     */
    public static Object getRumz_B(Field f, Object oj) {
        if (f != null)
            try {
                return f.get(oj);
            } catch (IllegalAccessException e) {
            }
        return null;
    }


    /**
     * Извлечение из (1) имени (#[[evnd]]) _поля
     *
     * @param f (1) -- поле _рифона, например "public String str"
     * @return -- например "str"
     */
    public static String getName(Field f) {
        return f.getName();
    }

    /**
     * Проверка поля (1) на принадлежность к одному из типов (2)
     *
     * @param f    (1) --
     * @param pead (2) --
     * @return --
     */
    public static boolean is(Field f, EPead pead) {
        switch (pead) {
            case MNAE:
                return G67G_Reflect.isMnae(f);
            case MWUW:
                return G67G_Reflect.isMwuw(f);
            case UEUN:
                return G67G_Reflect.isUeun(f);
        }
        return false;
    }
}

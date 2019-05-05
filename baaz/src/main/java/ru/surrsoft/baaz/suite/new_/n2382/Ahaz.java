package ru.surrsoft.baaz.suite.new_.n2382;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

import ru.surrsoft.baaz.suite.terms.w359w.EPead;
import ru.surrsoft.baaz.suite.terms.w359w.TTsnw;
import ru.surrsoft.baaz.univers.BuString;
import ru.surrsoft.baaz.univers.Unox;

/**
 * Декриптор поля ([miza]) класса. Оберткой множества [ahaz] служит класс {@link Paow}.
 * {@link Paow} является результатом работы преобразователя {@link Hipn}.
 * <p>
 * Стандартизованная сущность.
 * <p>
 * ПОНЯТИЯ:
 * <li> _поле </li>
 * <p>
 * СМ. ТАКЖЕ: {@link Hipn} , {@link Paow}
 * <p>
 * [[w383w]], [[N2383]], [[ahaz]]
 * <p>
 * ВЕРСИЯ 2 1.1 2019-02-05 (stored)
 */
public class Ahaz implements Unox {
    /**
     * Само _поле ([miza])
     */
    public Field field;
    /**
     * Тип поля ([akef])
     */
    public Class akef;
    /**
     * _Поле _родителя. NULL если _родителя нет
     */
    public Field fieldParent;
    /**
     * [ahaz] _родителя. NULL если родителя нет
     */
    public Ahaz ahazParent;
    /**
     * [pead] - разновидность _поля
     */
    public EPead pead;
    /**
     * 0 - root
     */
    public int level;
    /**
     * см. {@link TTsnw}
     */
    public boolean isTsnw;

    //==============================================================================================
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        BuString bst = new BuString()
          .divider(", ")
          .addWraperResult("{", "}", true);

        String f = "\"%s\":\"%s\"";
        bst.append(String.format(f, "field", field.getName()));
        bst.append(String.format(f, "fieldParent", fieldParent == null ? "null" : fieldParent.getName()));
        bst.append(String.format(f, "level", level));
        bst.append(String.format(f, "isTsnw", isTsnw));
        bst.append(String.format(f, "type", pead.name()));
        bst.append(String.format(f, "clazz", akef == null ? "null" : akef.getName()));

        return StringUtils.repeat(" ", level * 2) + bst.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Ahaz)) return false;
        Ahaz o1 = (Ahaz) o;
        boolean b = this.akef == o1.akef;

        return super.equals(o);
    }

    //impl [unox]
    //``````````````````````````````````````````````````````````````````````````````````````````````
    @Override
    public Unox unoxGetNksc() {
        return ahazParent;
    }

    public static String vdinInfo() {
        return "[ahaz] - декриптор поля ([miza]) класса (_рифона)";
    }
}

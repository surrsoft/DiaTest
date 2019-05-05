package ru.surrsoft.baaz.suite.new_.n2382;

import ru.surrsoft.baaz.univers.BuString;

/**
 * [[paow]] - "выпрямленное" ("плоское") представление [wira]
 * <p>
 * "Оборачивает" массив {@link Ahaz[]}. Является результатом работы {@link Hipn}.
 * Массив {@link Ahaz[]} является множеством типа ^[etof]
 * <p>
 * ВЕРСИЯ 2 1.1 2019-02-05 (stored)
 */
public class Paow {
    //```````````````````````````````````````````
    private Ahaz[] mAhazs;

    public Ahaz[] ahazsGet() {
        return mAhazs;
    }

    public void ahazsSet(Ahaz[] ahazs) {
        mAhazs = ahazs;
    }

    //```````````````````````````````````````````
    public Paow() {
    }

    //```````````````````````````````````````````

    @Override
    public String toString() {
        BuString bst = new BuString().divider(",\n");
        for (Ahaz eAhaz : mAhazs) {
            String s = eAhaz.toString();
            bst.append(s);
        }
        return "\n" + bst.toString();
    }

    public static String vdinInfo() {
        return "[paow] - выпрямленное ('плоское') представление [wira]. Является оберткой над " +
          "массивом из [ahaz]; получается с помощью _рифона Hipn";
    }
}

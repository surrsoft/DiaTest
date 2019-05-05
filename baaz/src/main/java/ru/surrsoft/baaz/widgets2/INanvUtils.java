package ru.surrsoft.baaz.widgets2;

import java.util.ArrayList;

/**
 *
 */
public class INanvUtils {
    public static int countChecked(ArrayList<INanv> inanvs) {
        int ret = 0;
        for (INanv elem : inanvs) {
            if (elem.n1457_isChecked()) {
                ret++;
            }
        }
        return ret;
    }
}

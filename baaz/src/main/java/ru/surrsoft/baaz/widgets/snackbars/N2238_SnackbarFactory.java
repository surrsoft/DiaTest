package ru.surrsoft.baaz.widgets.snackbars;

import android.support.design.widget.Snackbar;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Фабрика Snackbar-ов
 *
 * #version 1 13-05-2016  #id [[w238w]]
 */
public class N2238_SnackbarFactory {

    /**
     * Необрезаемый текст. Длительность показа == Snackbar.LENGTH_LONG. Текст пустой
     */
    public static final int COOK_1 = 101;

    public static Snackbar getSnackbar(int cook, ViewGroup parent) {
        if (cook == COOK_1) {
            Snackbar s = Snackbar.make(parent, "", Snackbar.LENGTH_LONG);
            TextView text = (TextView) s.getView().findViewById(android.support.design.R.id.snackbar_text);
            text.setMaxLines(100);
            return s;
        }
        return null;
    }
}

package ru.surrsoft.baaz.univers;

import android.view.Gravity;

/**
 * Обертка на стандартными константами {@link Gravity}
 * <p>
 * Старые названия: [WGravity]
 */
public class Gravity_01 {
    public int val;

    /**
     *
     * @param gravity (1) -- например "Gravity.RIGHT | Gravity.BOTTOM"
     */
    public Gravity_01(int gravity) {
        this.val = gravity;
    }
}

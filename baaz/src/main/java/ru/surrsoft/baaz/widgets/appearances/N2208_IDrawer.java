package ru.surrsoft.baaz.widgets.appearances;

import android.graphics.Canvas;

/**
 * Сущность, которая выполняет отрисовку на Canvas который ей передан
 * <p>
 * #version 1 02-03-2016  #id [[w208w]]
 */
public interface N2208_IDrawer {
    /**
     * Выполнение отрисовки на canvas (1) графики, в размерах (2) и (3)
     *
     * @param canvas
     * @param widthPx
     * @param heightPx
     */
    void draw(Canvas canvas, int widthPx, int heightPx);
}

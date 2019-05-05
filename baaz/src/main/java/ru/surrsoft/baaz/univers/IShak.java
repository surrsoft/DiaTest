package ru.surrsoft.baaz.univers;

import android.view.View;

/**
 * НАЗНАЧЕНИЯ
 * <li> для использования с пикерами. С помощью {@link #n1453_getView()} пикер берет виджет;
 * пользователь выполняет манипуляции с виджетом; по нажатию ОК, пикер с помощью {@link #n1453_getResultForUI(String)}
 * получает текст который нужно установить на "лицо" пикеру</li>
 * <p>
 * #version 1 14.01.2017  #id [[w453w]]
 */

public interface IShak {
    /**
     * @return --
     */
    View n1453_getView();

    /**
     * Результат для видимого отображения в UI
     * @param defaultText (1) -- текст который нужно вернуть если результат пустой
     * @return --
     */
    String n1453_getResultForUI(String defaultText);

    /**
     * Результат для кода
     * @return --
     */
    Object n1453_getResultSpecial();

    /**
     * Применить изменения сделанные в интерфейсе к модели
     */
    void n1453_applyModelChanges();

    /**
     * Откатить сделанные в интерфейсе изменения
     */
    void n1453_resetModelChanges();
}

package ru.surrsoft.baaz.widgets2.psmu;

import android.view.View;

/**
 * //
 */

public interface Psmu_e_i_ {

    void setIntegrator(Psmu_c_b_v_ integrator);

    View getView();

    int getLevel();

    boolean isTaezd();

    /**
     * Это технологический элемент. Реализатор должен сохранять это значение и возвращать его
     * по методу {@link #getIndex()}, а также передатавать интегратору установленному через
     * {@link #setIntegrator(Psmu_c_b_v_)}
     *
     * @param ix
     */
    void setIndex(int ix);

    int getIndex();

    /**
     * Перевести шеврон в состояние "закрыто"
     */
    void chevronToClose();

    /**
     * Задание состояния чекбокса
     *
     * @param b
     */
    void setCheck(boolean b);

    boolean isCheck();

    /**
     * Используется чтобы подсветить родителя когда его дети имеют чеки, но сами не видны
     * @param b
     */
    void выделить(boolean b);

    /**
     * Строка сумирующая сущность текущего элемента.
     * Например для использования в picker
     *
     * @return --
     */
    String getString();

}

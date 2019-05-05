package ru.surrsoft.baaz.misc;

/**
 * Классификация параметров при их использовании для фильтрации чего-либо
 */
public enum EFilterTypes {
    /**
     * Число, дробное или целое
     */
    DIGIT(),
    /**
     * Текст
     */
    TEXT(),
    /**
     * Список мультивыбора
     */
    MULT_ELEMS(),
    /**
     * Список одиночного выбора
     */
    ONESELECT_ELEMS(),
    /**
     * Дата
     */
    DATE()
}

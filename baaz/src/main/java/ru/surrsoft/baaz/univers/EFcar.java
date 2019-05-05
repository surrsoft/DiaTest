package ru.surrsoft.baaz.univers;

import android.text.InputType;

/**
 * [[fcar]] - множество; типы данных
 * <p>
 * ОПИСАНИЕ-1: РОЛЬ: предположим сущность А получает откуда-либо сущность Б. Мы знаем, что Б может
 * представлять
 * разные сущности, например дату, целое число, просто текст, одну строки из множества других строк
 * и т.д. Чтобы сказать А, что именно
 * отражает строка Б, мы вместе с Б передаем текущую сущность [fcar].
 * <p>
 * ОПИСАНИЕ-2: [fcar] - содержит условные обозначения для абстрактных: строки, целого числа,
 * дробного числа, даты, 0..1 элементов из нескольких, 0+ элементов из нескольких, неопределенной
 * сущности. Интерпретируется по разному в зависимости от конкретного примемения.
 * <p>
 * ПРИМЕНЕНИЯ:
 * -- применяется в [modk]. Там [fcar] говорит [modk]-функционалу какое условие можно/нужно
 * сформировать
 * -- благодаря части [fend] используется с EditText's для того чтобы сказать какого типа клавиатуру
 * отображать
 * <p>
 * ПОНЯТИЯ:
 * -- [[dsnp]] - строка Б из описания выше.
 * -- [[nizw]] - пара [dsnp] и [fcar].
 * <p>
 * <p>
 * ВЕРСИЯ 1.1 1.0 2019-02-05 (stored)
 * #version 1 (stored)
 */
public enum EFcar {
    /**
     * Для заголовочных элементов
     */
    NOTYPE("null", InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS),
    /**
     * Текст
     */
    TEXT(EStrings._TYPE_TEXT.val(), InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS),
    /**
     * Целое число
     */
    INTEGER(EStrings._TYPE_NUMBER.val(), InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED),
    /**
     * Дробное число
     */
    FLOAT(EStrings._TYPE_NUMBER.val(), InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED | InputType.TYPE_NUMBER_FLAG_DECIMAL),
    /**
     * Дата
     */
    DATE(EStrings._TYPE_DATE.val(), InputType.TYPE_CLASS_TEXT),
    /**
     * Список одиночного выбора
     */
    ONECHOICE(EStrings._TYPE_ONECHOICE.val(), InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS),
    /**
     * Список мнжоественного выбора
     */
    MULTCHOICE(EStrings._TYPE_MULTCHOICE.val(), InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

    //fields
    //``````````````````````````````````````````````````````````````````````````````````````````````

    /**
     * [[ypse]] - текстовое локализованное представление [fcar]
     */
    public String stYpse;
    /**
     * [[fend]] - InputType's для использования с EditText
     */
    public int iFend;

    //constructors
    //``````````````````````````````````````````````````````````````````````````````````````````````

    EFcar(String stYpse, int iFend) {
        this.stYpse = stYpse;
        this.iFend = iFend;
    }

    //
    //``````````````````````````````````````````````````````````````````````````````````````````````

}

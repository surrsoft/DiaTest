package ru.surrsoft.baaz.cls_c;

import java.util.Locale;

/**
 * Работа с локализацией
 */
public class TLang_01 {

    /**
     * Возвращает (2) если текущий язык "en". Иначе возвращает (1) - если
     * текущий язык это язык по умолчанию
     *
     * @param aTxDf (1) -- текст для языка по умолчанию
     * @param aTxEn (2) -- текст для английского языка ("en")
     * @return в нештатных ситуациях возвращает (1)
     */
    public static String getString_En(String aTxDf, String aTxEn) {
        // 2-х буквенное обозначение текущего языка
        String lang = Locale.getDefault().getLanguage();
        if (lang == null || lang.length() < 1) return aTxDf;
        // если начинается с "en"
        if (lang.indexOf("en") == 0) return aTxEn;
        return aTxDf;
    }

    /**
     * Возвращает текст "Да" на текущем языке приложения
     *
     * @return
     */
    public static String TxYes() {
        String ru = "Да";
        String en = "Yes";

        // 2-х буквенное обозначение текущего языка
        String lang = Locale.getDefault().getLanguage();

        if (lang == null || lang.length() < 1) {
            return en;
        }

        // если начинается с "en"
        if (lang.indexOf("en") == 0) {
            return en;
        }
        if (lang.indexOf("ru") == 0) {
            return ru;
        }

        return en;
    }

    /**
     * Возвращает текст "Нет" на текущем языке приложения
     *
     * @return
     */
    public static String TxNo() {
        String ru = "Нет";
        String en = "No";

        // 2-х буквенное обозначение текущего языка
        String lang = Locale.getDefault().getLanguage();

        if (lang == null || lang.length() < 1) {
            return en;
        }

        // если начинается с "en"
        if (lang.indexOf("en") == 0) {
            return en;
        }
        if (lang.indexOf("ru") == 0) {
            return ru;
        }

        return en;
    }

    /**
     * Возвращает текст "Отмена" на текущем языке приложения
     *
     * @return
     */
    public static String TxCancel() {
        String ru = "Отмена";
        String en = "Cancel";

        // 2-х буквенное обозначение текущего языка
        String lang = Locale.getDefault().getLanguage();

        if (lang == null || lang.length() < 1) {
            return en;
        }

        // если начинается с "en"
        if (lang.indexOf("en") == 0) {
            return en;
        }
        if (lang.indexOf("ru") == 0) {
            return ru;
        }

        return en;
    }

    /**
     * Возвращает текст "Выполнить" на текущем языке приложения
     *
     * @return если текущий язык неизвестен возвращает текст на английском
     */
    public static String TxExecute() {
        String ru = "Выполнить";
        String en = "Execute";

        // 2-х буквенное обозначение текущего языка
        String lang = Locale.getDefault().getLanguage();

        if (lang == null || lang.length() < 1) {
            return en;
        }

        // если начинается с "en"
        if (lang.indexOf("en") == 0) {
            return en;
        }
        if (lang.indexOf("ru") == 0) {
            return ru;
        }

        return en;
    }

    /**
     * Возвращает текст "Продолжить" на текущем языке приложения
     *
     * @return
     */
    public static String TxContinue() {
        String ru = "Продолжить";
        String en = "Continue";

        // 2-х буквенное обозначение текущего языка
        String lang = Locale.getDefault().getLanguage();

        if (lang == null || lang.length() < 1) {
            return en;
        }

        // если начинается с "en"
        if (lang.indexOf("en") == 0) {
            return en;
        }
        if (lang.indexOf("ru") == 0) {
            return ru;
        }

        return en;
    }

}

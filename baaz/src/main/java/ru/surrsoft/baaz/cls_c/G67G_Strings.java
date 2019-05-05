package ru.surrsoft.baaz.cls_c;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

import java.util.StringTokenizer;

import ru.surrsoft.baaz.SomeException;

/**
 * Работа со строками
 *
 * ВЕРСИЯ 1 2019-02-03 (stored)
 */
public class G67G_Strings {

    /**
     *
     * @param str (1) --
     * @param startStr (2) --
     * @return TRUE если (1) начинается с подстроки (2). Сравнение регистрочувствительное
     */
    public static boolean isStartWith(String str, String startStr){
        return StringUtils.startsWith(str, startStr);
    }

    /**
     *
     * @param str (1) --
     * @param symbols (2) --
     * @return TRUE если (1) состоит только из символов перечисленных в (2)
     */
    public static boolean isContainsOnly(String str, String symbols){
        return StringUtils.containsOnly(str, symbols);
    }

    /**
     * Удаление из строки (1) всех пробелов (под "пробел" здесь понимаются табы, переносы строк и т.п.)
     * @param str (1) --
     * @return --
     */
    public static String removeWhitespaces(String str){
        return StringUtils.deleteWhitespace(str);
    }

    /**
     * Удаляет из строки (1) подстроку (2) только если она находится в самом начале
     * @param str (1) -- например ";aa;"
     * @param stForRemove (2) -- например ";"
     * @return -- например "aa;"
     */
    public static String removeOnStart(String str, String stForRemove){
        return StringUtils.removeStart(str, stForRemove);
    }

    /**
     * Удаляет из строки (1) подстроку (2) только если она находится в самом конце
     * @param str (1) -- например ";aa;"
     * @param stForRemove (2) -- например ";"
     * @return -- например ";aa"
     */
    public static String removeOnEnd(String str, String stForRemove){
        return StringUtils.removeEnd(str, stForRemove);
    }

    /**
     * Заменяет в строке (1) все повторения символа (2) одним символом (2)
     *
     * @param str         (1) -- например "aa;;;bb;;cc"
     * @param replaceSymb (2) -- текст длиной 1, не меньше и не больше, например ";"
     * @return например "aa;bb;cc"
     */
    @Nullable
    public static String replaceRetrySymbAll(String str, @NonNull String replaceSymb) {
        if (str == null) {
            return null;
        }
        if (replaceSymb == null || replaceSymb.length() != 1) {
            throw new SomeException("(debug)");
        }
        return str.replaceAll(replaceSymb + "+", replaceSymb);
    }

    /**
     * Ищет в строке (1) закрывающий символ (4) для открывающего символа (3) начиная с символа (2)
     *
     * @param str        (1) -- строка в которой выполняется поиск, например "a(b(c)d)e"
     * @param startIndex (2) -- индекс с которого начинается поиск; по данному индексу должен
     *                   располагаться символ (3); например "1"
     * @param cOpen      (3) -- открывающий символ, например "("
     * @param cClose     (4) -- закрывающий символ, например ")"
     * @return -- индекс расположения искомого закрывающего символа, например "7"; -1 при нештатах
     */
    public static int indexCloseSymbol(String str, int startIndex, char cOpen, char cClose) {
        int ctOpen = 0;
        int ctClose = 0;
        if (str == null || str.length() == 0 || startIndex < 0 || startIndex >= str.length())
            return -1;
        if (str.charAt(startIndex) != cOpen)
            throw new SomeException("(debug) по указанному индексу не располагается символ cOpen");
        for (int i1 = startIndex; i1 < str.length(); i1++) {
            char c = str.charAt(i1);
            if (c == cOpen) ctOpen++;
            if (c == cClose) ctClose++;
            if (ctOpen == ctClose && ctOpen != 0) {
                return i1;
            }
        }
        return -1;
    }

    /**
     * Возвращает n-й (3) элемент строки (1). Количество элементов в строке определяется
     * количеством разделителей (2) в ней. Расположенные рядом разделитли (2) расцениваются как
     * 1 разделитель
     *
     * @param str     (1) -- исходная строка, например "a:b:c:d"
     * @param divider (2) -- разделитель, например ":"
     * @param n       (3) -- индекс элемента который нужно вернуть в итоговой строке, например 2.
     *                Элементы нумеруются с нуля
     * @return например "c". Пустая строка при любых нештатах
     */
    @NonNull
    public static String getElemString(String str, String divider, int n) {
        if (str == null || str.length() == 0 || divider == null || divider.length() == 0
                || n < 0)
            return "";
        StringTokenizer st = new StringTokenizer(str, divider);
        int ct = 0;
        while (st.hasMoreTokens()) {
            String s = st.nextToken();
            if (ct == n) return s;
            ct++;
        }
        return "";
    }


    /**
     * Если (1) это NULL или строка нулевой длины или строка из одних пробелов то возвращает
     * пустую строку.
     * Иначе, удаляет пробелы в начале и конце строки, повторения пробелов, и все похожее на
     * пробелы (табы, переносы строк и т.п.)
     *
     * @param str (1) -- исходная строка
     * @return --
     */
    public static String adapt(String str) {
        if (str == null || str.length() < 1) return "";
        return StringUtils.normalizeSpace(str);
    }

    /**
     * Вставляет разделители (2) разрядов (с конца) с указанным шагом (3). Метод полезен для
     * представления больших чисел
     *
     * @param str  (1) -- строка в которую нужно вставить разделители;
     *             если это добное число, то разделителем дрбной части должна быть точка;
     *             например "10009.00"
     * @param div  (2) -- разделитель, например "'"
     * @param step (3) -- шаг, например 3
     * @return например "10'009.00"; исходная строка при нештатах
     */
    public static String addDividersBigNumbers(String str, String div, int step) {
        if (div == null || str == null || div.length() == 0) return str;
        String str2 = str;
        String str3 = "";
        int i = str.indexOf(".");
        if (i != -1) {
            str2 = str.substring(0, i);
            str3 = str.substring(i);
        }
        if (str2.length() <= step) return str;
        StringBuilder sb = new StringBuilder("");
        StringBuilder sb2 = new StringBuilder("");
        for (int i1 = str2.length() - 1; i1 >= 0; i1--) {
            sb.append(str2.charAt(i1));
            sb2.append(str2.charAt(i1));
            if (sb2.length() % step == 0 && i1 != 0) {
                sb.append(div);
            }
        }
        return sb.reverse().append(str3).toString();
    }

    /**
     * Объединение строк (1) и (2) через разделитель (3).
     * Если (1) или (2) is {null || ""} то разделитель не вставляется
     *
     * @param str1    (1) -- строка, например "a"
     * @param str2    (2) -- строка, например "b"
     * @param divider (3) -- разделитель, например ";"
     * @return результат объединения, например "a;b" или пустая строка
     */
    @NonNull
    public static String joinStr_A(String str1, String str2, String divider) {
        str1 = (str1 == null) ? "" : str1;
        str2 = (str2 == null) ? "" : str2;
        divider = (divider == null) ? "" : divider;
        if (str1.length() == 0 || str2.length() == 0) divider = "";
        return str1 + divider + str2;
    }

    /**
     * От {@link #joinStr_A(String, String, String)}  отличается только возможностью добавлять префикс к (3)
     *
     * @param str1    (1) -- строка, например "a"
     * @param str2    (2) -- строка, например "b"
     * @param divider (3) -- разделитель, например "; "
     * @param prefix2 (4) -- префикс для элемент (3), например "1-"
     * @return результат объединения, например "a; 1-b" или пустая строка
     */
    @NonNull
    public static String joinStr_A(String str1, String str2, String divider, String prefix2) {
        str1 = (str1 == null) ? "" : str1;
        str2 = (str2 == null) ? "" : str2;
        divider = (divider == null) ? "" : divider;
        prefix2 = (prefix2 == null) ? "" : prefix2;
        if (str1.length() == 0 || str2.length() == 0) divider = "";
        if (str2.length() == 0) prefix2 = "";
        return str1 + divider + prefix2 + str2;
    }

    /**
     * От {@link #joinStr_A(String, String, String)} отличается только тем что если (2) is {null || ""} то сразу возвращается "".
     *
     * @param str1    (1) -- строка, например "a"
     * @param str2    (2) -- строка, например ""
     * @param divider (3) -- разделитель, например ";"
     * @return результат объединения, например ""
     */
    @NonNull
    public static String joinStr_B(String str1, String str2, String divider) {
        if (str2 == null || str2.length() == 0) return ""; //======X
        return joinStr_A(str1, str2, divider);
    }

    /**
     * От {@link #joinStr_B(String, String, String)}  отличается только возможностью добавлять префикс (4) к (3)
     *
     * @param str1    (1) -- строка, например "a"
     * @param str2    (2) -- строка, например ""
     * @param divider (3) -- разделитель, например ";"
     * @param prefix2 (4) -- префикс для (3), например "1:"
     * @return результат объединения, например ""
     */
    @NonNull
    public static String joinStr_B(String str1, String str2, String divider, String prefix2) {
        if (str2 == null || str2.length() == 0) return ""; //======X
        return joinStr_A(str1, str2, divider, prefix2);
    }

    /**
     * Объединение массива строк (1) в одну строку с разделителями (2). Пустые строки и строки null отбрасываются
     *
     * @param s         (1) -- например {"a", "", null, "b"}
     * @param separator (2) -- например ";"
     * @return например {"a", "b"}
     */
    public static String __todo__join(String[] s, String separator) {
        /*
        String[] s2 = ArrayUtils.removeElements(s, "", null);
        return StringUtils.join(s2, separator);
        */
        return null;
    }

    /**
     * Возвращает TRUE если строка (1) либо имеет нулевую длину, либо
     * состоит только из пробелов
     *
     * @param aStr (1) -- проверяемая строка
     * @return
     */
    public static boolean strIsEmptys(String aStr) {
        return aStr.matches("\\s*");
    }

    /**
     * @param s (1) -- проверяемая строка
     * @return TRUE если s!=null && s.length > 0 && s не состоит из одних пробелов (переносов и т.п.)
     */
    public static boolean isValid(CharSequence s) {
        return StringUtils.isNoneBlank(s);
    }

    /**
     * ^[[tuvg]] - строка !=null && ненулевой длины && не состоящая из одних пробелов (переносов и т.п.)
     *
     * @param s (1) -- проверяемая строка
     * @return TRUE если (1) is [tuvg]
     */
    public static boolean isTuvg(CharSequence s) {
        return isValid(s);
    }


    /**
     * Возвращает TRUE если строка (1) не null, не нулевой длины и не состоит из одних пробелов
     *
     * @param st (1) -- строка
     * @return
     */
    public static boolean ok(String st) {
        return !(st == null || strIsEmptys(st));
    }


    /**
     * Возвращает исходную строку (1) если она не null, не нулевой длины и не состоит из одних пробелов.
     * Иначе вовзращает пустую строку
     *
     * @param st (1) -- строка
     * @return
     */
    public static String ok_B(String st) {
        if (st == null || strIsEmptys(st)) return "";
        return st;
    }

    /**
     * Если (1) is ok и (2) != null то возвращает сумму (2) и (1), иначе возвращает пустую строку
     *
     * @param st   (1) -- например "name"
     * @param plus (2) -- например ", "
     * @return например ", name"
     */
    public static String okPlus(String st, String plus) {
        if (ok(st) && plus != null) {
            return plus + st;
        }
        return "";
    }

    /**
     * ЦЕЛЬ: получить слово (2) в том склонении которое соответствует числу (1)
     * <p>
     * ОПИСАНИЕ: склоняет словой (2) в соответствии с числом (1)
     *
     * @param number (1) -- число положительное или отрицательное, например 5
     * @param p1     (2) -- слово для склонения с числом 1, например "карточка"
     * @param p2     (3) -- слово для чисел 2, 3 и т.д., например "карточки"
     * @param p3     (4) -- слово для числе 5, 6 и т.д., например "карточек"
     * @return слово в нужной форме, например "карточек"
     */
    @NonNull
    public static String persuadeWord(int number,
                                      @NonNull String p1,
                                      @NonNull String p2,
                                      @NonNull String p3) {
        number = Math.abs(number);
        if (number == 1) return p1; //======X
        if (number == 10 || number == 11 || number == 0 || number == 5 || number == 6 ||
                number == 7 || number == 8 || number == 9)
            return p3; //======X
        if (number == 2 || number == 3 || number == 4) return p2; //======X

        String n = number + "";

        String s1 = n.substring(n.length() - 1, n.length());
        String s2 = n.substring(n.length() - 2, n.length());
        if (s2.equals("00") || s2.equals("10") || s2.equals("11") || s2.equals("12") || s2.equals("13")
                || s2.equals("14") || s2.equals("15") || s2.equals("16")
                || s2.equals("17") || s2.equals("18") || s2.equals("19")) {
            return p3; //======X
        } else if (s1.equals("0")) return p3;
        else if (s1.equals("1")) return p1;
        else if (s1.equals("2") || s1.equals("3") || s1.equals("4")) return p2;
        else if (s1.equals("5") || s1.equals("6") || s1.equals("7") || s1.equals("8") || s1.equals("9"))
            return p3;
        return p1;
    }

}

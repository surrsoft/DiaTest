package ru.surrsoft.baaz.tclasses;

import android.support.annotation.StringRes;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;

import ru.surrsoft.baaz.Bysa_01;
import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.cls_c.G67G_Strings;
import ru.surrsoft.baaz.univers.OxtrGet_01;
import ru.surrsoft.baaz.univers.U;
import ru.surrsoft.baaz.univers.WrwnGet_01;

/**
 * Утилитные методы для работы с типом java.lang.String
 * <p>
 * ПОНЯТИЯ:
 * -- _пробел, _space -
 * -- ^[[uxvb]] - это группа символов в которую в том числе входит обычный пробел \u0020, перенос
 * строки \u000a и первод каретки \u000c
 * -- [[bavg]]/[[юапр]] - формат вывода нескольких строк. Одна строка выводится как есть, например
 * "строка1", а несколько строк выводятся так (на примере двух строк):
 * - строка1;
 * - строка2
 * <p>
 * <p>
 * КОДЫ: [ksao]
 * ВЕРСИЯ 1 2019-02-04 (stored)
 */
public class TString_01 {

  //create
  //```````````````````````````````````````````

  /**
   * Преобразование (1) в строку
   *
   * @param i (1) --
   * @return --
   */
  public static String create(int i) {
    return String.valueOf(i);
  }

  /**
   * Преобразование (1) в строку
   *
   * @param i (1) --
   * @return --
   */
  public static String create(long i) {
    return String.valueOf(i);
  }

  /**
   * @param oj (1) --
   * @return --
   */
  public static String create(Object oj) {
    return String.valueOf(oj);
  }

  /**
   * Получение [evmd]-строки визуализирующей структуру (1) (древоподобную) построенную на уровнях ([oxtr])
   *
   * @param _arr (1) --
   * @param <T>  (01) --
   * @return --
   */
  public static <T extends OxtrGet_01 & WrwnGet_01> String createEvmd(ArrayList<T> _arr) {
    //---
    if (_arr == null) {
      return "is null []";
    }
    if (_arr.size() < 1) {
      return "[]";
    }
    //---
    ArrayList<String> arrSt = new ArrayList<>();
    //---
    String stfFigPar = "{%1$s}";
    String stfKvPar = "[%1$s]";
    String stElem, stLvlPrefix, stName;
    int iElemLevel;
    //---
    for (T elem : _arr) { //LOOP --
      if (elem != null) {
        iElemLevel = elem.oxtrGet(); //уровень
        stName = elem.wrwnGet(); //имя
        //--- подготовка префикса
        if (iElemLevel >= 0) {
          stLvlPrefix = TString_01.repeat_B("..", iElemLevel);
        } else {
          stLvlPrefix = "!!! level < 0 (= " + iElemLevel + ") ";
        }
        //---
        stElem = stLvlPrefix + String.format(stfFigPar, String.format(stfKvPar, stName));
      } else { //если элемент == NULL
        stElem = String.format(stfFigPar, "null");
      }
      //---
      arrSt.add(stElem);     //<===
    } //LOOP --
    //---
    String stBavg = createBavg_B(arrSt, "", "\n", null);
    //---
    return String.format("[\n%1$s\n]", stBavg);
  }

  /**
   * Возвращает результат повторения строки (1) (2) раз
   * <p>
   * ПРИМЕРЫ:
   * <pre>
   * ("e", 3) => "eee"
   * ("e", 1) => "e"
   *
   * ("", 3) => ошибка т.к. длина строки < 1
   * (null, 3) => ошибка т.к. строка == null
   * ("e", 0) => ошибка т.к. (2) меньше 1
   * </pre>
   *
   * @param _st     (1) -- например "e"; NOT NULL
   * @param _iCount (2) -- число 1+
   * @return --
   */
  public static String repeat(String _st, int _iCount) {
    verify_ex(_st, E.NOT_NULL_AND_LENGTH_NOT_NIL);
    TxInt_01.verify_ex(_iCount, TxInt_01.E.ONE_PLUS);
    //---
    return StringUtils.repeat(_st, _iCount);
  }

  /**
   * Отличается от А ({@link #repeat(String, int)}) только тем, что дозволяет в (2) ноль - в этом
   * случае возвращается пустая строка.
   *
   * @param _st     (1) --
   * @param _iCount (2) --
   * @return --
   */
  public static String repeat_B(String _st, int _iCount) {
    verify_ex(_st, E.NOT_NULL_AND_LENGTH_NOT_NIL);
    TxInt_01.verify_ex(_iCount, TxInt_01.E.NIL_PLUS);
    //---
    return StringUtils.repeat(_st, _iCount);
  }


  /**
   *
   */
  enum E {
    /**
     * Строка != null
     */
    NOT_NULL,
    /**
     * Строка != null и имеет длину > 0
     */
    NOT_NULL_AND_LENGTH_NOT_NIL
  }

  /**
   * Проверка строки (1) по критерию (2)
   *
   * @param _st (1) --
   * @param _e  (2) --
   */
  public static void verify_ex(String _st, E _e) {
    if (_e == null) {
      throw new SomeException("(debug)");
    }
    //---
    switch (_e) {
      case NOT_NULL:
        if (_st == null) {
          throw new SomeException("(debug)");
        }
        break;
      case NOT_NULL_AND_LENGTH_NOT_NIL:
        if (_st == null) {
          throw new SomeException("(debug)");
        }
        if (_st.length() < 1) {
          throw new SomeException("(debug)");
        }
        break;
      //---
      default:
        throw new SomeException("(debug) _e [" + _e + "]");
    }
  }

  /**
   * Получение [bavg]-строки из массива (1)
   *
   * @param ojs      (1) -- массив элементов любого типа; каждый элемент преобразуется используя
   *                 .toString()
   * @param stPrefix (2) -- префикс; если null то используется "- "
   * @param stSufix  (3) -- суфикс; если null то используется ";\n"
   * @return --
   */
  public static String createBavg(Object[] ojs, String stPrefix, String stSufix) {
    if (ojs == null) return null;
    //---
    stPrefix = stPrefix == null ? "- " : stPrefix;
    stSufix = stSufix == null ? ";\n" : stSufix;
    //---
    StringBuilder sb = new StringBuilder("");
    String st;
    for (int i = 0; i < ojs.length; i++) { //LOOP --
      Object elem = ojs[i];
      st = (elem == null) ? "null" : elem.toString();
      if (i > 0 && ojs.length > 1) {
        sb.append(stSufix);
      }
      if (ojs.length > 1) {
        sb.append(String.format(stPrefix, i));
      }
      sb.append(st);
    } //LOOP --
    return sb.toString();
  }

  /**
   * [[xkgr]] - формат представления ArrayList&lt;String> (А)
   * <p>
   * Если А == null, возвращается строка "null".
   * <p>
   * Если A.size() == 0, возвращается пустая строка "";
   * <p>
   * Если A.size() > 0, возвращается строка, с переносами строки в начале и конце (все элементы,
   * кроме null, обрамляются []), например: "
   * [elem1]
   * null     //если элемент == null то пишется null без обрамления скобками
   * [...]
   * "
   *
   * @param _arr (1) --
   * @return --
   */
  public static String createXkgr(ArrayList<String> _arr) {
    StringBuilder ret = new StringBuilder("");
    String stPar = "[%1$s]";
    //---
    if (_arr == null) {
      ret.append("null");
    } else if (_arr.size() == 0) {
      ret.append("");
    } else {
      ret.append("\n");
      boolean b58 = false;
      for (String st : _arr) { //LOOP --
        if (b58) ret.append("\n");
        if (st == null) {
          ret.append("null");
        } else {
          ret.append(String.format(stPar, st));
        }
        b58 = true;
      } //LOOP --
      ret.append("\n");
    }
    //---
    return ret.toString();
  }


  /**
   * [[aebo]] - формат отображения линейных или древовидных структур (списков). Предназначен для
   * использования только в информационных целях (в логах например)
   * <p>
   * ОПИСАНИЕ [aebo] (используется [kona]-терминология):
   * -- если _список ([fnoh]) равен _null то возвращается просто текст null
   * -- если _список ([fnoh]) не _null, а длина его == 0 то возвращается пустая строка
   * -- если _список ([fnoh]) не _null, а длина его > 0 то возвращаемое отображение информации об
   * _элементах ([bbfr]) обрамляется в начале и конце переносом строки
   * -- если в _контейнере ([iexz]) находится _объект ([kgow]) то отображаются
   * квадратные скобки. Если же в _контейнере находится _null, то отображается текст null без
   * квадратных скобок
   * -- если _текст ([apnx]) _объекта ([kgow]) не _null ([dvvn]), то он заключается в двойные кавычки.
   * Иначе выводится просто текст null. Если _объект реализует интерфейс {@link WrwnGet_01} то _текст
   * получается с помощью {@link WrwnGet_01#wrwnGet()}, если же не реализует - с помощью toString(),
   * при этом справа от квадратных скокоб добавляется текст "-ts"
   * -- если _элементом является _объект, и он реализует {@link OxtrGet_01} то
   * для того элемента справа добавляется текст "; lvl [x]", где x - число выражающее _уровень ([ppnw]).
   * Если этот _уровень > 0 то слева добавляется отступ длиной пропорциональной этому _уровню
   * <p>
   * КОДЫ: [ksao]
   *
   * @param _arrFnoh (1) -- список ([fnoh])
   * @return --
   */
  public static String createAebo(ArrayList _arrFnoh) {
    StringBuilder ret = new StringBuilder("");
    //---
    String stConfPar = "[%1$s]";
    String stConfKav = "\"%1$s\"";
    String stConfPrefixElem = "..";
    //---
    if (_arrFnoh == null) {
      ret.append("null");
    } else if (_arrFnoh.size() == 0) {
      ret.append("");
    } else {
      ret.append("\n");
      //---
      boolean b58 = false;
      String stElem;
      String stPrefixLevel;
      int iLevel;
      String stLvlInitial;

      for (Object elem : _arrFnoh) { //LOOP --
        if (b58) ret.append("\n");
        if (elem == null) {
          ret.append("null");
        } else {
          //--- name
          if (elem instanceof WrwnGet_01) {
            stElem = ((WrwnGet_01) elem).wrwnGet();
            if (stElem == null) {
              stElem = String.format(stConfPar, "null");
            } else {
              //оборачиваем в кавычки
              stElem = String.format(stConfKav, stElem);
              //оборачиваем в квадратные скобки
              stElem = String.format(stConfPar, stElem);
            }
          } else {
            stElem = elem.toString();
            if (stElem == null) {
              stElem = String.format(stConfPar, "null");
            } else {
              //оборачиваем в кавычки
              stElem = String.format(stConfKav, stElem);
              //оборачиваем в квадратные скобки
              stElem = String.format(stConfPar, stElem);
              stElem += "-ts";
            }
          }
          //--- добавление уровневой приставки
          if (elem instanceof OxtrGet_01) {
            iLevel = ((OxtrGet_01) elem).oxtrGet();
            stLvlInitial = String.valueOf(iLevel);
            if (iLevel < 0) iLevel = 0;
            stPrefixLevel = TString_01.repeat_B(stConfPrefixElem, iLevel);
            stElem = stPrefixLevel + stElem + "; lvl [" + stLvlInitial + "]";
          }
          //---
          ret.append(stElem);
        }
        b58 = true;
      } //LOOP --
      //---
      ret.append("\n");
    }
    //---
    return ret.toString();

  }

  public interface GetString<T> {
    String getString(T oj);
  }

  /**
   * - удаляет лидирующие и конечные пробелы
   * - заменяет повторения пробелов одним пробелом
   * - удаляет все переносы строк
   * <p>
   * #errors нет
   *
   * @param st (1) --
   * @return возвращает обратно (1) если (1) == null или нулевой длины
   */
  public static String normalize(String st) {
    String ret = st;
    if (st != null && st.length() > 0) {
      ret = StringUtils.normalizeSpace(st);
    }
    return ret;
  }

  /**
   * От А отличается тем что если (1) == null или длина (1) == 0 то вовзвращается строка (2)
   *
   * @param st        (1) --
   * @param stDefault (2) --
   * @return --
   */
  public static String normalize_B(String st, String stDefault) {
    if (st != null && st.length() > 0) {
      return StringUtils.normalizeSpace(st);
    }
    return stDefault;
  }

  /**
   * Нормализует (1) методом {@link #normalize(String)}, из получившейся строки извлекает
   * методом {@link #startString(String, int, String)}  первые (3)
   * символов и возращает их. Возвращает (2) если (1)==null или имеет нулевую длину, или если
   * после нормализации длина строки стала == 0
   *
   * @param st        (1) --
   * @param stDefault (2) --
   * @param iCount    (3) --
   * @return --
   */
  public static String normalize_C(String st, String stDefault, int iCount) {
    if (st != null && st.length() > 0) {
      String st1 = normalize(st);
      if (st1.length() > 0) {
        return startString(st, iCount, stDefault);
      }
    }
    return stDefault;
  }

  /**
   * Обрезает начальные и конечные [uxvb]-пробелы (т.е. в том числе обычные пробелы, перенос строки
   * и перевод картеки)
   *
   * @param st (1) -- например " \n a  a  "
   * @return -- например "a  a"
   */
  public static String normalizeTrim(String st) {
    if (st == null) {
      return null;
    }
    //---
    return StringUtils.trim(st);
  }

  /**
   * Преобразует список (1) в строку с разделителями (2). Объекты из (1) преобразуются в строки с
   * помощью (3)
   * <p>
   * #errors если (1)(2)(3)==null, если любой из элементов (1)==null, если результат работы (3) == null
   * для любого из элементов из (1)
   *
   * @param ojs       (1) --
   * @param delimiter (2) --
   * @param getString (3) --
   * @return --
   */
  public static <T> String join(ArrayList<T> ojs, String delimiter, GetString<T> getString) {
    U.se(ojs == null, "");
    U.se(delimiter == null, "");
    U.se(getString == null, "");
    //---
    StringBuilder ret = new StringBuilder();
    boolean first = true;
    for (T oj : ojs) {
      U.se(oj == null, "");
      //---
      String st = getString.getString(oj);
      U.se(st == null, "");
      //---
      ret.append(first ? "" : delimiter).append(st);
      first = false;
    }
    return ret.toString();
  }


  public interface Vs48 {
    String fun(Object oj);
  }

  /**
   * Получение [bavg]-строки из списка (1)
   *
   * @param _arr      (1) -- массив элементов любого типа; каждый элемент преобразуется используя (4)
   * @param _stPrefix (2) -- префикс; если null то используется "- "; можно использовать строку
   *                  с местом под число, например " (%d) "
   * @param _stSufix  (3) -- суфикс; если null то используется ";\n"
   * @param _vs       (4) -- опционально; сущность получающая на вход элемент из (1) и дающая на
   *                  выходе строку; если не указано то используется .toString()
   * @return --
   */
  public static String createBavg_B(ArrayList _arr, String _stPrefix, String _stSufix, Vs48 _vs) {
    if (_arr == null) {
      return null;
    }
    //---
    String[] arr = {};
    for (Object oj : _arr) {
      if (_vs == null) {
        arr = ArrayUtils.add(arr, oj == null ? null : oj.toString());
      } else {
        String st = _vs.fun(oj);
        arr = ArrayUtils.add(arr, st);
      }
    }
    //---
    return createBavg(arr, _stPrefix, _stSufix);
  }


  //```````````````````````````````````````````

  /**
   * Возвращает TRUE если строка (1) это непустая-строка (#[[omzt]])
   *
   * @param str (1) --
   * @return --
   */
  public static boolean isOmzt(String str) {
    return str != null && str.length() > 0;
  }

  /**
   * Метод-синоним для {@link #isOmzt(String)}
   *
   * @param st (1) --
   * @return --
   */
  public static boolean isNoEmpty(String st) {
    return isOmzt(st);
  }

  /**
   * Противоположность метода {@link #isOmzt(String)}
   *
   * @param st (1) --
   * @return TRUE если (1) == null или имеет нулевую длину
   */
  public static boolean isEmpty(String st) {
    return !isOmzt(st);
  }

  /**
   * Если (2) TRUE то возвращает TRUE если (1) null или имеет нулевую длину (см. {@link
   * #isOmzt(String)} )
   * <p>
   * Если (2) FALSE то вовзращае TRUE если (1) не null и имеет не нулевую длину (см. {@link
   * #isOmzt(String)} )
   *
   * @param st (1) --
   * @param b  (2) --
   * @return --
   */
  public static boolean isEmpty(String st, boolean b) {
    if (b) {
      return !isOmzt(st);
    }
    return isOmzt(st);
  }

  /**
   * Вовзвращает TRUE если (1) состоит только из чисел 0...9.
   * Возвращает FALSE если (1) это пустая строка или null
   *
   * @param st (1) --
   * @return --
   */
  public static boolean isNumeric(String st) {
    return StringUtils.isNumeric(st);
  }

  /**
   * Извлекает из строки (1) первые (2) символов. Если (1) == null или нулевой длины, то возвращает
   * (3). Если в (1) символов меньше чем (2) то вовзращает исходную строку (1)
   * <p>
   * #errors нет
   *
   * @param st        (1) -- например "тестСтрока"
   * @param iCount    (2) -- например 4; если == 0 или < 0 то возвращается строка (3)
   * @param stDefault (3) --
   * @return -- например "тест"
   */
  public static String startString(String st, int iCount, String stDefault) {
    if (iCount <= 0) {
      return stDefault;
    }
    //---
    if (iCount > st.length()) {
      iCount = st.length();
    }
    //---
    if (isNoEmpty(st)) {
      return st.substring(0, iCount);
    } else {
      return stDefault;
    }
  }

  /**
   * Возвращает TRUE если строка (1) начинается со строки (2).
   * Если (1) и (2) == NULL, возвращает TRUE.
   * Если (1) или (2) == NULL, возвращает false.
   * Если (1) не NULL, а (2) это пустая строка "", возвращает TRUE.
   *
   * @param str        (1) --
   * @param prefix     (2) --
   * @param ignoreCase (3) -- TRUE если при сравнении строк не учитывать регистр
   * @return --
   */
  public static boolean startWith(final CharSequence str, final CharSequence prefix, final boolean ignoreCase) {
    if (!ignoreCase) {
      return StringUtils.startsWith(str, prefix);
    }
    //---
    return StringUtils.startsWithIgnoreCase(str, prefix);
  }

  /**
   * ^[[tuvg]] - строка !=null && ненулевой длины && не состоящая из одних пробелов (переносов и
   * т.п.)
   *
   * @param cs (1) -- проверяемая строка
   * @return TRUE если (1) is [tuvg]
   */
  public static boolean isTuvg(CharSequence cs) {
    return G67G_Strings.isValid(cs);
  }

  /**
   * Бросает исключение если (1) !isTuvg
   *
   * @param charSequence (1) --
   */
  public static void isTuvg_exception(CharSequence charSequence) {
    if (!G67G_Strings.isValid(charSequence)) {
      throw new SomeException("(debug)");
    }
  }

  /**
   * Бросает исклюение если любой их элементов в (1) !isTuvg
   *
   * @param strs (1) --
   */
  public static void isTuvg_exception(CharSequence... strs) {
    for (CharSequence elem : strs) {
      if (!G67G_Strings.isValid(elem)) {
        throw new SomeException("(debug)");
      }
    }
  }


  /**
   * Возвращает TURE если все элементы (1) {@link #isTuvg(CharSequence)}
   *
   * @param cs (1) --
   * @return --
   */
  public static boolean isTuvg(CharSequence... cs) {
    for (CharSequence elem : cs) {
      if (!isTuvg(elem)) return false;
    }
    return true;
  }

  /**
   * Противоположность {@link #isTuvg(CharSequence)}
   *
   * @param s (1) --
   * @return --
   */
  public static boolean isTuvgNon(CharSequence s) {
    return !G67G_Strings.isValid(s);
  }

  /**
   * @param str (1) --
   * @return --
   */
  public static int length(String str) {
    if (str == null) {
      throw new SomeException("(debug)");
    }
    //---
    return str.length();
  }

  /**
   * Берёт копию строки (1), меняет в ней все вхождения строки (2) на строку (3), возвращает
   * измененную строку
   *
   * @param str        (1) --
   * @param strFind    (2) --
   * @param strReplace (3) --
   * @return --
   */
  public static String replaceAll(String str, String strFind, String strReplace) {
    return StringUtils.replace(str, strFind, strReplace);
  }

  /**
   * Если строка (1) начинается со строки (2), то удаляет подстроку (2) из начала строки (1) и
   * возвращает измененную строку.
   * Если (1) == null то возвращает null.
   * Если (2) == null то возвращает (1).
   *
   * @param st     (1) --
   * @param remove (2) --
   * @return --
   */
  public static String removeStart(String st, String remove) {
    return StringUtils.removeStart(st, remove);
  }


  /**
   * Удаляет из начала строки (1) все символы которые встречаются в строке (2).
   * Если (2) == null то из начала строки (1) удаляются [vcvo]-пробелы (т.е. символы определённые в
   * {@link Character#isWhitespace(char)} ).
   * Если (1) == null или "" то возвращает исходная строка (1)
   *
   * @param st          (1) -- например "август"
   * @param removeChars (2) -- например "ва"
   * @return -- например "густ"
   */
  public static String removeStart_B(String st, String removeChars) {
    return StringUtils.stripStart(st, removeChars);
  }

  /**
   * Если (1) не NULL и длиной > 0 то добавляет к (1) слева пробел и возвращает получившуюся строку
   * <p>
   * #errors - нет
   *
   * @param st (1) --
   * @return --
   */
  public static String spaceLeftIf(String st) {
    return textLeftIf(" ", st);
  }


  /**
   * Если (2) не NULL и длиной > 0 то берёт строку (1), прибавляет к ней справа строку (2) и
   * возвращает получившуюся строку.
   * <p>
   * NULL в (1) или (2) преобразует в пустую строку.
   * <p>
   * #errors - нет
   *
   * @param stLeftText (1) --
   * @param stText     (2) --
   * @return --
   */
  public static String textLeftIf(String stLeftText, String stText) {
    if (StringUtils.isNotEmpty(stText)) {
      return U.st(stLeftText) + stText;
    }
    return "";
  }

  /**
   * Сортирует массив (1)
   *
   * @param arrBack (1)
   */
  public static void sortStringArray(String[] arrBack) {
    if (arrBack != null && arrBack.length > 1) {
      Arrays.sort(arrBack);
    }
  }

  /**
   * Получение строки (1) из ресурсов
   *
   * @param iStrResId (1) --
   * @return --
   */
  public static String createFromRes(@StringRes int iStrResId) {
    return Bysa_01.res.getString(iStrResId);
  }

}

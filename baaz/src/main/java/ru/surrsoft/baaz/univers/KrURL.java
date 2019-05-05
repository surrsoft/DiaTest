package ru.surrsoft.baaz.univers;

import org.apache.commons.lang3.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import ru.surrsoft.baaz.SomeException;

/**
 * Инкарнация URL.
 * <p>
 * Конструктор не бросает исключение при некорректном URL на входе. Поэтому перед использованием
 * следует проверять
 * корректность инкапсулируемого URL методом {@link #isValid()}
 * <p>
 * ПОНЯТИЯ:
 * -- _пример - http://example.com:8042/over/there?name=ferret&color=red#nose=true&eye=false
 * -- _элемент, _часть, _elem - какой-либо элемент URL
 * -- _схема, _schema - часть "foo" _примера
 * -- _доменпорт, _domainport - часть "example.com:8042" _примера
 * -- -- _домен, _domain - часть "example.com" _примера
 * -- -- _порт, _port - часть "8042" _примера
 * -- _путьцепочка, _pathquery - часть "/over/there?name=ferret&color=red" _примера
 * -- -- _путь, _path - часть "/over/there" _примера
 * -- -- _цепочка, _query - часть "name=ferret&color=red" _примера
 * -- _фрагмент, _fragment - часть "nose=true&eye=false" _примера
 * -- _пара, _pair - части "name=ferret", "color=red", "nose=true" и "eye=false" _примера
 * -- -- _ключ, _key - часть _пары располагающаяся перед "=", например "name"
 * -- -- _значение, _value - часть _пары располагающаяся после "=", например "ferret"
 * -- _набор, _set - 1+ _пар разделённых "&"
 */
public class KrURL {

  private String stURL = "";
  private URL url = null;


  //--- constructors

  public KrURL(String stURL) {
    if (isValid(stURL)) {
      this.stURL = stURL;
      //---
      try {
        url = new URL(stURL);
      } catch (MalformedURLException e) {
        e.printStackTrace();
      }
    }
  }

  //---

  /**
   * Возвращает TRUE если текущий объект инкапсулирует корректные URL
   *
   * @return (1) --
   */
  public boolean isValid() {
    return isValid(stURL);
  }

  /**
   * Возвращает TRUE если URL (1) корректен
   *
   * @param stURL (1) --
   * @return --
   */
  public static boolean isValid(String stURL) {
    if (stURL != null && stURL.length() > 0) {
      try {
        new URL(stURL);
        return true;
      } catch (MalformedURLException e) {
        e.printStackTrace();
      }
    }
    return false;
  }

  /**
   * Константы обозначающие части URL
   */
  public enum EURLPart {
    SCHEMA,
    DOMAIN_PORT,
    DOMAIN,
    PORT,
    PATH_QUERY,
    PATH,
    QUERY,
    FRAGMENT
  }

  /**
   * Получение части (1) инкапсулируемого URL
   *
   * @param eUrlPart (1) --
   * @return --
   */
  public String elemGet(EURLPart eUrlPart) {
    switch (eUrlPart) {
      case SCHEMA:
        return url.getProtocol();
      case DOMAIN_PORT:
        return url.getAuthority();
      case DOMAIN:
        return url.getHost();
      case PORT:
        return url.getPort() + "";
      case PATH_QUERY:
        return url.getFile();
      case PATH:
        return url.getPath();
      case QUERY:
        return url.getQuery();
      case FRAGMENT:
        return url.getRef();
    }
    return "";
  }

  /**
   * Извлечение _пар из части (1) URL.
   * <p>
   * #errors исключение если неверная константа (1)
   *
   * @param eUrlPart (1) -- {@link EURLPart#QUERY} или {@link EURLPart#FRAGMENT}
   * @return пустой список если пары не обнаружены в части (1)
   */
  public Map<String, String> pairsGet(EURLPart eUrlPart) {
    U.se(eUrlPart == null, "");
    if (eUrlPart != EURLPart.QUERY && eUrlPart != EURLPart.FRAGMENT) {
      throw new SomeException("(debug) eUrlPart [" + eUrlPart + "]");
    }
    //--
    Map<String, String> ret = new HashMap<>();
    //---
    switch (eUrlPart) {
      case QUERY:
        String st = elemGet(EURLPart.QUERY);
        return mtPairsGet(st);
      case FRAGMENT:
        String st2 = elemGet(EURLPart.FRAGMENT);
        return mtPairsGet(st2);
    }
    //---
    return ret;
  }

  public String getURL() {
    return stURL;
  }

  private Map<String, String> mtPairsGet(String _set) {
    HashMap<String, String> ret = new HashMap<>();
    //---
    if (_set != null && _set.length() > 0) {
      String[] split = StringUtils.split(_set, '&');
      //---
      for (String st : split) { //LOOP
        String[] split1 = StringUtils.split(st, '=');
        if (split1.length == 2 && split1[0].length() > 0) {
          ret.put(split1[0], split1[1]);
        }
      } //LOOP
    }
    //---
    return ret;
  }

}

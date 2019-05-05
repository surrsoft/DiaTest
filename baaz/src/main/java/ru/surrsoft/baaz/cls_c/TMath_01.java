package ru.surrsoft.baaz.cls_c;

import android.support.annotation.Nullable;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Утилитные методы для работы с единицами измерения
 */
public class TMath_01 {

  /**
   * Возвращает какой процент составляет число (1) от числа (2)
   *
   * @param i1 (1) --
   * @param i2 (2) --
   * @return --
   */
  public static int getPercent(int i1, int i2) {
    if (i1 == 0) return 0;
    if (i1 == i2) return 100;
    return (int) (((i1 * 1f) / (i2 * 1f)) * 100f);
  }


  /**
   * Округление числа (1) с указанным количеством знаков после запятой (2)
   *
   * @param digit         (1) -- число которое нужно округлить
   * @param decimalsCount (2) -- число знаков после запятой; допустимо 0 и более
   * @return null при некорректных входных данных
   * <br>Примеры:
   * <br>this(5.054, 2) => 5.05;
   * <br>this(5.055, 2) => 5.05;
   * <br>this(5.056, 2) => 5.06;
   * <br>this(5.000, 2) => 5.0;
   */
  public static Float round(Float digit, int decimalsCount) {
    if (digit == null || decimalsCount < 0) return null; //======X
    return new BigDecimal(digit).setScale(decimalsCount, RoundingMode.HALF_UP).floatValue();
  }

  /**
   * Округление числа (1) с указанным количеством знаков после запятой (2)
   *
   * @param digit         (1) -- число которое нужно округлить
   * @param decimalsCount (2) -- число знаков после запятой; допустимо 0 и более
   * @return null при некорректных входных данных
   * <br>Примеры:
   * <br>this(5.054, 2) => 5.05;
   * <br>this(5.055, 2) => 5.05;
   * <br>this(5.056, 2) => 5.06;
   * <br>this(5.000, 2) => 5.0;
   */
  public static Double round(Double digit, int decimalsCount) {
    if (digit == null || decimalsCount < 0) return null; //======X
    return new BigDecimal(digit).setScale(decimalsCount, RoundingMode.HALF_UP).doubleValue();
  }

  /**
   * Округление числа (1) с указанным количеством знаков после запятой (2)
   *
   * @param digit         (1) -- число которое нужно округлить
   * @param decimalsCount (2) -- число знаков после запятой; допустимо 0 и более
   * @return null при некорректных входных данных
   * <br>Примеры:
   * <br>this(5.054, 2) => 5.05;
   * <br>this(5.055, 2) => 5.05;
   * <br>this(5.056, 2) => 5.06;
   * <br>this(5.000, 2) => 5.00;
   */
  public static String roundSt(Float digit, int decimalsCount) {
    if (digit == null || decimalsCount < 0) return null; //======X
    return new BigDecimal(digit).setScale(decimalsCount, RoundingMode.HALF_UP).toPlainString();
  }

  /**
   * Отличие от А ({@link #roundSt(Float, int)}) в том что отбрасываются нули на конце после точки
   * <p>
   * ПРОБЛЕМЫ: если на входе например 12.12, то на выходе будет 12.11999988555908203125
   *
   * @param f
   * @param decimalsCount
   * @param isStripZeros  (3) -- TRUE если нужно отбрасывать нули на конце
   * @param divider       (4) -- если != null то используется как разделитель групп
   * @return #version 2 09-04-2016 160409123400
   */
  public static String roundSt(Float f, int decimalsCount, boolean isStripZeros, String divider) {
    if (f == null || decimalsCount < 0) return null; //======X
    BigDecimal bd = new BigDecimal(f);
    bd.setScale(decimalsCount, RoundingMode.HALF_UP);
    if (isStripZeros) bd.stripTrailingZeros();
    String s = bd.toPlainString();
    //если на входе "0.00" то на выходе тоже "0.00" - в этом блоке это устраняется
    if (f < 1f && f > -1f) {
      CharSequence cc = s;
      boolean b = false;
      for (int i = 0; i < cc.length(); i++) {
        if (cc.charAt(i) != '0' && cc.charAt(i) != '.') {
          b = true;
        }
      }
      if (!b) s = "0";
    }
    //
    if (divider != null) s = G67G_Strings.addDividersBigNumbers(s, divider, 3);
    //
    return s;
  }

  /**
   * Эта версия не имеет проблемы версии А.
   * <p>
   * Округление числа (1) с указанным количеством знаков после запятой (2)
   * <p>
   * Преобразует Float (1) к String.
   * <p>
   * Примеры: (44444.4444, 2, " ") => "44 4444.45"
   *
   * @param digit         (1) -- Float в обычной или научной нотации
   * @param decimalsCount (2) -- количество цифр после запятой на выходе
   * @param groupDivider  (3) -- текст для разделителя групп; если null то разделитель групп не применяется
   * @return #version 1 27-07-2016
   */
  public static String roundSt_B(Float digit, int decimalsCount, @Nullable String groupDivider) {
    if (digit == null || decimalsCount < 0) return ""; //======X

    DecimalFormat df = new DecimalFormat();
    df.setMaximumFractionDigits(decimalsCount);
    if (groupDivider != null) df.setGroupingSize(3);
    else df.setGroupingSize(0);
    String s = df.format(digit); //<===
    s = s.replace(",", ".");
    if (groupDivider != null) s = s.replace(" ", groupDivider);
    return s;
  }

  /**
   * см. {@link #roundSt(Float, int, boolean, String)} - по аналогии
   *
   * @param digit
   * @param decimalsCount
   * @param isStripZeros
   * @param divider       (4) -- если != null то итоговое число-строка преобразуется к удобочитаемому виду с указанным здесь разделителем
   * @return #version 2 09-04-2016 160409123700
   */
  public static String roundSt(Double digit, int decimalsCount, boolean isStripZeros, String divider) {
    if (digit == null || decimalsCount < 0) return null; //======X
    BigDecimal bd = new BigDecimal(digit);
    bd.setScale(decimalsCount, RoundingMode.HALF_UP);
    if (isStripZeros) bd.stripTrailingZeros();
    String s = bd.toPlainString();
    //если на входе "0.00" то на выходе тоже "0.00" - в этом блоке это устраняется
    if (digit < 1d && digit > -1d) {
      CharSequence cc = s;
      boolean b = false;
      for (int i = 0; i < cc.length(); i++) {
        if (cc.charAt(i) != '0' && cc.charAt(i) != '.') {
          b = true;
        }
      }
      if (!b) s = "0";
    }
    //
    if (divider != null) s = G67G_Strings.addDividersBigNumbers(s, divider, 3);
    //
    return s;
  }


  /**
   * Округление числа (1) с указанным количеством знаков после запятой (2)
   *
   * @param digit         (1) -- число которое нужно округлить
   * @param decimalsCount (2) -- число знаков после запятой; допустимо 0 и более
   * @return null при некорректных входных данных
   * <br>Примеры:
   * <br>this(5.054, 2) => 5.05;
   * <br>this(5.055, 2) => 5.05;
   * <br>this(5.056, 2) => 5.06;
   * <br>this(5.000, 2) => 5.00;
   */
  public static String roundSt(Double digit, int decimalsCount) {
    if (digit == null || decimalsCount < 0) return null; //======X
    return new BigDecimal(digit).setScale(decimalsCount, RoundingMode.HALF_UP).toPlainString();
  }


  /**
   * Преобразование int-string в int
   *
   * @param digit (1) -- число int строкой
   * @return бросает NumberFormatException в случае если на входе что-то некоректное
   */
  public static int stringToInt(String digit) {
    return Integer.parseInt(digit);
  }

  /**
   * Отличие от Math.round только в том, что если результат == 0, то возвращается 1
   *
   * @param val
   * @return
   */
  public static int roundNoNil(float val) {
    int i = Math.round(val);
    return (i < 1) ? 1 : i;
  }

  /**
   * Возвращает TRUE если (1) это четное число
   * <p>Для поиска: четный, нечетный, нечетное</p>
   *
   * @param num
   * @return
   */
  public static boolean isEven(int num) {
    return (num % 2) == 0;
  }
}

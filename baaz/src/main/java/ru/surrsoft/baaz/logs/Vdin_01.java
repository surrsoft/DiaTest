package ru.surrsoft.baaz.logs;

import android.util.Log;

import org.apache.commons.lang3.StringUtils;

import ru.surrsoft.baaz.SomeException;


/**
 * ^[[vdin]]
 * <p>
 * ОПИСАНИЕ:
 * утилитные функции для режима повествования (^[[vdin]]). В этом режиме программа как бы
 * рассказывает о том что она делает
 * <p>
 * МЕТКИ: [neoi]
 * <p>
 * ВЕРСИЯ 1 1.0 2019-01-19 (stored)
 */
public class Vdin_01 {

  private static final boolean ON = true;

  private static int iLvl = -1;

  //коэффициент отступа
  private static int K = 2;

  private static boolean bLogI;
  private static boolean bLogTemp;

  public enum E {
    START,
    MIDDLE,
    END
  }

  public Vdin_01() {
  }

  public static void log(String st, E en, Object oj) {
    if (ON) {
      if (st == null || en == null) {
        throw new SomeException("(debug); st=" + st + "; en=" + en);
      }
      //---
      String stPrefix = "=[vdin]=";
      //---
      String st36 = null;
      int k = 2;
      switch (en) {
        case START:
          iLvl++;        //<=== LVL
          st36 = ", \"s\":{\"m\":\"" + mtStCls(oj) + st + mtStLvl(iLvl) + mtStEn(en) + "\"";
          st36 = mtLeftPad(st36, iLvl);
          break;
        case MIDDLE:
          st36 = ", \"m\":\"" + mtStCls(oj) + st + mtStLvl(iLvl) + mtStEn(en) + "\"";
          st36 = mtLeftPad(st36, iLvl + 1);
          break;
        case END:
          if (st.length() == 0) {
            st36 = "}";
            st36 = mtLeftPad(st36, iLvl);
          } else {
            st36 = ", \"e\":\"" + mtStCls(oj) + st + mtStLvl(iLvl) + mtStEn(en) + "\"}";
            st36 = mtLeftPad(st36, iLvl);
          }
          iLvl--;        //<=== LVL
          break;
      }
      if (st36 != null) {
        if (!bLogI && !bLogTemp) {
          Log.i(stPrefix, st36);                      //<=== LOG OUT info
        } else {
          if (bLogI) {
            Log.d(stPrefix, st36);                    //<=== LOG OUT debug
          }
          if (bLogTemp) {
            Log.v(stPrefix, st36);         //<=== LOG OUT verbose
          }
        }
      }
    }

  }

  public static void logI(String st, Object oj) {
    Vdin_01.bLogI = true;
    log(st, E.MIDDLE, oj);
    Vdin_01.bLogI = false;
  }

  public static void logTemp(String st, Object oj) {
    Vdin_01.bLogTemp = true;
    log(st, E.MIDDLE, oj);
    Vdin_01.bLogTemp = false;
  }

  //улучшайзинг
  //````````````````````````````````````````````````````````````````````````````````````````````````
  public static void logStart(String st, Object oj) {
    log(st, E.START, oj);
  }

  public static void logM(String st, Object oj) {
    log(st, E.MIDDLE, oj);
  }

  public static void logEnd(String st, Object oj) {
    log(st, E.END, oj);
  }

  //private
  //````````````````````````````````````````````````````````````````````````````````````````````````
  private static String mtStEn(E en) {
    return "";
    //return "; en="+en;
  }

  private static String mtStLvl(int lvl) {
    return "";
    //return "; lvl=" + lvl;
  }

  private static String mtStCls(Object oj) {
    if (oj != null) {
      return "[" + oj.getClass().getSimpleName() + "]$ ";
    }
    return "";
  }

  private static String mtLeftPad(String st36, int iLvl) {
    if (iLvl > 0) {
      return StringUtils.leftPad(st36, (iLvl * K) + st36.length());
    }
    return st36;
  }
}
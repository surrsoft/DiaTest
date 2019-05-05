package ru.surrsoft.baaz.univers;

import android.support.annotation.ColorInt;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.net.URL;
import java.util.Arrays;

import ru.surrsoft.baaz.cls_c.G67G_Draw;

/**
 * Адаптация информации о различных сущностях для вывода в лог
 * <p>
 */
public class LogInfo {

  public static String st(int[] drawableState) {
    if (drawableState == null) {
      return "null";
    }
    String ret = Arrays.toString(drawableState);
    ret = ret.replace("16842919", "state_pressed");
    ret = ret.replace("16842913", "state_selected");
    ret = ret.replace("16842909", "state_window_focused");
    ret = ret.replace("16842910", "state_enabled");
    ret = ret.replace("16843547", "state_accelerated");
    ret = ret.replace("16843597", "state_multiline");
    return ret;
  }

  public static String st(MotionEvent me) {
    if (me == null) return "null";
    return "action=" + MotionEvent.actionToString(me.getAction());
  }

  /**
   * Информация об URL (1)
   *
   * @param url (1) --
   * @return --
   */
  public static String st(URL url) {
    if (url == null) {
      return "null";
    }
    //---
    StringBuilder sb = new StringBuilder();
    sb.append("url [").append(url).append("]\n");
    sb.append("url.getAuthority() [").append(url.getAuthority()).append("]\n");
    sb.append("url.getHost() [").append(url.getHost()).append("]\n");
    sb.append("url.getPath() [").append(url.getPath()).append("]\n");
    sb.append("url.getFile() [").append(url.getFile()).append("]\n");
    sb.append("url.getProtocol() [").append(url.getProtocol()).append("]\n");
    sb.append("url.getQuery() [").append(url.getQuery()).append("]\n");
    sb.append("url.getRef() [").append(url.getRef()).append("]\n");
    sb.append("url.getUserInfo() [").append(url.getUserInfo()).append("]\n");
    sb.append("url.getDefaultPort() [").append(url.getDefaultPort()).append("]\n");
    sb.append("url.getPort() [").append(url.getPort()).append("]");
    //---
    return sb.toString();
  }

  public static String color(@ColorInt int color) {
    String ret = "";
    String colorHex = G67G_Draw.colorIntToString(color);
    ret += "hex:[" + colorHex + "]; ";
    ret += "int:[" + color + "]; ";
    //===
    String s = "";
    switch (colorHex) {
      case "#ffff0000":
        s = "RED";
        break;
      case "#ff00ff00":
        s = "GREEN";
        break;
      case "#ff0000ff":
        s = "BLUE";
        break;
      case "#ff000000":
        s = "BLACK";
        break;
      case "#ff444444":
        s = "DKGRAY";
        break;
      case "#ff888888":
        s = "GRAY";
        break;
      case "#ffcccccc":
        s = "LTGRAY";
        break;
      case "#ffffffff":
        s = "WHITE";
        break;
      case "#ffffff00":
        s = "YELLOW";
        break;
      case "#ff00ffff":
        s = "CYAN";
        break;
      case "#ffff00ff":
        s = "MAGENTA";
        break;
    }
    //
    ret += "string:[" + s + "]"; /*===*/
    //===
    return ret;
  }

  /**
   * Готовит строку с информацией о всем дереве ViewGroup (1)
   *
   * @param vg    (1) --
   * @param level (2) -- всегда 0, для рекурсии
   * @return --
   */
  public static String viewTreeInfo(ViewGroup vg, int level) {
    class N1 {
      private String fun(View v, int lvl) {
        String s = "\n" + pad(lvl) + "name=" + v.getClass().getName()
          + "; " + visibilitySt(v.getVisibility())
          + "; id=0x" + Integer.toHexString(v.getId())
          + "; w/h=" + v.getWidth() + "/" + v.getHeight()
          + "; LP w/h=" + (v.getLayoutParams() == null ? "(lp==null)" : (lp(v.getLayoutParams().width) + "/" + lp(v.getLayoutParams().height)))
          + "; l/t/r/b=" + v.getLeft() + "/" + v.getTop() + "/" + v.getRight() + "/" + v.getBottom()
          + "; LP_cls=" + (v.getLayoutParams() == null ? "null" : v.getLayoutParams().getClass().getName());
        if (v instanceof TextView) {
          s += "; text=" + ((TextView) v).getText();
        }
        return s;
      }
    }

    StringBuilder st = new StringBuilder();
    if (level == 0) {
      st.append(new N1().fun(vg, level));
      level++;
    }
    for (int i = 0; i < vg.getChildCount(); i++) {
      View view = vg.getChildAt(i);
      st.append(new N1().fun(view, level));
      if (view instanceof ViewGroup) {
        st.append(viewTreeInfo(((ViewGroup) view), level + 1));
      }
    }
    return st.toString();
  }

  /**
   * Информация о виджете (1)
   *
   * @param v (1) --
   * @return --
   */
  public static String viewInfo(View v) {
    if (v == null) {
      return "null";
    }
    //---
    if (v instanceof ViewGroup) {
      return viewTreeInfo(((ViewGroup) v), 0);
    }
    //---
    return v.getClass().getName();
  }

  /**
   * @param lp_wh (1) --
   * @return --
   */
  public static String lp(int lp_wh) {
    switch (lp_wh) {
      case ViewGroup.LayoutParams.MATCH_PARENT:
        return "MP";
      case ViewGroup.LayoutParams.WRAP_CONTENT:
        return "WC";
      default:
        return lp_wh + "";
    }
  }

  //private
  //``````````````````````````````````````````````````````````````````````````````````````````````
  private static String visibilitySt(int visibility) {
    switch (visibility) {
      case View.VISIBLE:
        return "VISIBLE";
      case View.INVISIBLE:
        return "INVISIBLE";
      case View.GONE:
        return "GONE";
      default:
        return "?";
    }
  }

  private static String pad(int ct) {
    StringBuilder ret = new StringBuilder();
    for (int i1 = 0; i1 < ct; i1++) {
      ret.append("  ");
    }
    return ret.toString();
  }

}

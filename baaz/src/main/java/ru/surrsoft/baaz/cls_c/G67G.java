package ru.surrsoft.baaz.cls_c;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang3.ArrayUtils;

import ru.surrsoft.baaz.Bysa_01;
import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.univers.EStrings;
import ru.surrsoft.baaz.univers.ETextCase;

import static ru.surrsoft.baaz.cls_c.G67G_Strings.isValid;

/**
 * Библиотека методов
 * <p>
 * #version 4.13 (14) 23-09-2016
 */
public class G67G {

  public static String tf1_roboto_light = "g67g/fonts/Roboto_Light.ttf";

  /**
   * Если (1) == false и debug==true приложение завершается
   *
   * @param b       (1) --
   * @param message (2) --
   */
  public static void assert_(boolean b, String message) {
    boolean debug = true; //установить в FALSE перед релизом //debug //back
    String detailMessage = Log2_01.CONF_LOG_PREFIX + " (debug) " + message;
    if (debug) {
      if (!b) throw new AssertionError(detailMessage);
    } else {
      if (!b) Log.w("ASSERT", detailMessage);
    }
  }

  /**
   * Бросает исключение если (1) == null или (1) нулевой длины, или (1) состоит из одних пробелов,
   * переносов строк и т.п.
   *
   * @param s (1) -- проверяемая строка
   */
  public static void assertString(CharSequence s) {
    if (!isValid(s)) {
      String s1 = "";
      if (s == null) {
        s1 = "равна null";
      } else if (s.length() == 0) {
        s1 = "нулевой длины";
      } else {
        s = "состоит из одних пробелов";
      }
      throw new SomeException("(debug) невалидная строка - " + s1);
    }
  }

  //----------------------------------------------------------------------------------------------

  /**
   * В части вывода информации в лог
   */
  public static class Logs {

    /**
     * @param prefix (1) --
     * @return
     */
    public static String prefix(String prefix) {
      if (Log2_01.CONF_LOG_PREFIX_SHOW) return prefix;
      return "";
    }

    /**
     * Вывод в лог и в виде Toast
     *
     * @param aText (1) -- текст для вывода
     */
    public static void ToastAaPrintln(String aText) {
      Toast.makeText(Bysa_01.appContext, aText, Toast.LENGTH_LONG).show();
      System.out.println(aText);
    }

    /**
     * Вывод в лог и в виде Toast
     * <p>
     * От А отличие только в передаче контекста напрямую
     *
     * @param aText   (1) -- текст для вывода
     * @param context (2) --
     */
    public static void ToastAaPrintln_B(String aText, Context context) {
      Toast.makeText(context, aText, Toast.LENGTH_LONG).show();
      System.out.println(aText);
    }

    /**
     * Выводит в лог информацию о том null или нет объект (2). <br>
     * <br>
     * Например ":info:g21g prefix == null"
     *
     * @param aPrefix (1) -- название объекта (2) которое будет отображено
     * @param aObj    (2) -- объект который проверяется на null
     */
    public static void isNull(String aPrefix, Object aObj) {
      if (aObj == null) {
        System.out.println(":info:g21g " + aPrefix + " == null");
      } else {
        System.out.println(":info:g21g " + aPrefix + " != null");
      }
    }
  }

  //=============================================================================================

  /**
   * Разное
   */
  public static class Misc {

    /**
     * Делает текст TextView (1) подчеркнутым
     *
     * @param tv (1)
     */
    public static void setTextViewAsUnderline(TextView tv) {
      CharSequence st = tv.getText();
      SpannableString ss = new SpannableString(st);
      ss.setSpan(new UnderlineSpan(), 0, ss.length(), 0);
      tv.setText(ss);
    }

    /**
     * Извлечение из атрибутов (2) значения атрибута с res-именем (1)
     *
     * @param attrResName (1) -- res-имя атрибута, например "R.attr.text1"
     * @param attrs       (2) -- массив атрибутов заданных в XML-слое
     * @param context     (3) -- контекст
     * @return null если аттрибут не был найден
     */
    public static String getAttrStringValue(int attrResName, AttributeSet attrs, Context context) {
      String res = null;
      int[] customAttrs = new int[]{attrResName};
      TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, customAttrs, 0, 0);
      int n = ta.getIndexCount();
      for (int i = 0; i < n; i++) {
        if (i == ArrayUtils.indexOf(customAttrs, attrResName)) {
          res = ta.getString(i);
          break;
        }
      }
      ta.recycle();
      return res;
    }

    /**
     * Здание видимости сразу нескольким виджетам
     *
     * @param views
     * @param visibilityCode
     */
    public static void setViewsVisibility(View[] views, int visibilityCode) {
      for (View view : views) {
        view.setVisibility(visibilityCode);
      }
    }

    /**
     * Применение margins к view (1)
     *
     * @param view       (1) -- элемент к которому нужно применить margin
     * @param margin_dp  (2) -- величина margin в dip
     * @param where      (3) -- указание с какой строны применить margin, например "lrtb" будет означать
     *                   что нужно применить со всех сторон, а "lr" что нужно применить только слева и справа
     * @param wrapWidth  (4) -- если TRUE то для ширины будет применен LinearLayout.LayoutParams.WRAP_CONTENT
     * @param wrapHeight (5) -- если TRUE то для высоты будет применен LinearLayout.LayoutParams.WRAP_CONTENT
     */
    public static void setMargins(View view, Integer margin_dp, String where, boolean wrapWidth, boolean wrapHeight) {
      if (view == null || where == null || where.length() < 1 || margin_dp == null || margin_dp < 1) {
        return;
      }

      //===
      int w = LinearLayout.LayoutParams.WRAP_CONTENT;
      int h = LinearLayout.LayoutParams.WRAP_CONTENT;

      if (!wrapWidth) w = LinearLayout.LayoutParams.MATCH_PARENT;
      if (!wrapHeight) h = LinearLayout.LayoutParams.MATCH_PARENT;

      LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(w, h);

      //===
      int l = 0;
      int r = 0;
      int t = 0;
      int b = 0;
      float fDensity = Bysa_01.appContext.getResources().getDisplayMetrics().density;
      if (where.contains("l")) l = (int) (margin_dp * fDensity);
      if (where.contains("r")) r = (int) (margin_dp * fDensity);
      if (where.contains("t")) t = (int) (margin_dp * fDensity);
      if (where.contains("b")) b = (int) (margin_dp * fDensity);
      lp.setMargins(l, t, r, b);

      view.setLayoutParams(lp);
    }

    /**
     * Показ клавиатуры для элемента (1)
     *
     * @param view    (1) -- элемент
     * @param context (2) -- активити
     */
    public static void keyboardShow(View view, Context context) {
      if (view == null) {
        return;
      }
      view.requestFocus();
      InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
      //imm.toggleSoftInputFromWindow(view.getWindowToken(), 0, 0);
      imm.showSoftInput(view, 0);
    }

    /**
     * Скрытие клавиатуры для элемента (1)
     *
     * @param view    (1) -- элемент
     * @param context (2) -- активити
     */
    public static void keyboardHide(View view, Context context) {
      if (view == null) {
        return;
      }
      InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Простой запуск активити (1)
     *
     * @param aClass (1) -- имя класса активити которое нужно запустить,
     *               например "ActivityName.class"
     */
    public static void startActivity(Class<?> aClass) {
      Intent i = new Intent(Bysa_01.appContext, aClass);
      Bysa_01.appContext.startActivity(i);
    }

    /**
     * @param killSafely Primitive boolean which indicates whether the app should
     *                   be killed safely or quickly. If true then the app will be
     *                   killed safely. Otherwise it will be killed quickly.
     * @ see
     * http://stackoverflow.com/questions/2092951/how-to-close-android-
     * application
     * <p>
     * Kill the app either safely or quickly. The app is killed safely by
     * killing the virtual machine that the app runs in after finalizing all
     * {@link Object}s created by the app. The app is killed quickly by
     * abruptly killing the process that the virtual machine that runs the
     * app runs in without finalizing all {@link Object}s created by the
     * app. Whether the app is killed safely or quickly the app will be
     * completely created as a new app in a new virtual machine running in a
     * new process if the user starts the app again. //Убивает приложение в
     * быстром или безопасном режимах. Приложение убивается безопасно через
     * убийство виртуальной машины в которой работает приложение после
     * финализации всех Object созданных приложением. Приложение убивается в
     * быстром режиме посредством резкого убийства процесса виртуальной
     * машины в котором работает приложение без финализации всех Object
     * созданных приложением. Вне зависимости приложение убивает в беопасном
     * или быстром режимах при следующем запуске приложения оно будет
     * создано в новой виртуальной машине работающей в новом процессе.
     * <p>
     * <p>
     * <B>NOTE:</B> The app will not be killed until all of its threads have
     * closed if it is killed safely. //ПРИМЕЧАНИЕ: в безопасном режиме
     * приложение не будет убито пока не будут убиты все его потоки
     * </P>
     * <p>
     * <p>
     * <B>NOTE:</B> All threads running under the process will be abruptly
     * killed when the app is killed quickly. This can lead to various
     * issues related to threading. For example, if one of those threads was
     * making multiple related changes to the database, then it may have
     * committed some of those changes but not all of those changes when it
     * was abruptly killed.
     * </P>
     */
    public static void appKill(boolean killSafely) {
      if (killSafely) {
        /**
         * Notify the system to finalize and collect all objects of the
         * app on exit so that the virtual machine running the app can
         * be killed by the system without causing issues. NOTE: If this
         * is set to true then the virtual machine will not be killed
         * until all of its threads have closed. //Сообщает системе
         * финализировать и выгрузить все объекты приложения на выходе
         * чтобы виртуальная машина в котором работает приложение могла
         * быть убита системой без вызова проблем. ПРИМЕЧАНИЕ: если
         * здесь задано TRUE то виртуальная машина не будет убита пока
         * все ее потоки не будут закрыты
         */
        System.runFinalizersOnExit(true);

        /**
         * Force the system to close the app down completely instead of
         * retaining it in the background. The virtual machine that runs
         * the app will be killed. The app will be completely created as
         * a new app in a new virtual machine running in a new process
         * if the user starts the app again. //Принуждение системы
         * закрыть приложение полностью вместо того чтобы оставлять его
         * в фоне. Виртуальная машина которая выполняет приложение будет
         * убита. Приложение будет создано как новое приложение в новой
         * виртуальной машине работающей в новом процессе если
         * пользователь запустит приложение снова
         */
        System.exit(0);
      } else {
        /**
         * Alternatively the process that runs the virtual machine could
         * be abruptly killed. This is the quickest way to remove the
         * app from the device but it could cause problems since
         * resources will not be finalized first. For example, all
         * threads running under the process will be abruptly killed
         * when the process is abruptly killed. If one of those threads
         * was making multiple related changes to the database, then it
         * may have committed some of those changes but not all of those
         * changes when it was abruptly killed. //Альтернативно, процесс
         * в котором работает виртуальная машина может быть убить резко.
         * Это быстрый путь удалить приложения с устройства но это может
         * стать причиной проблем если сперва не будут финализированы
         * ресурсы. Например, все потоки работающие в процессе будут
         * внезапно убиты вместе с процессом. Если один из этих потоков
         * выполнял множество взаимосвязанных изменений в базе данных,
         * то это может привести к тому что некоторые из этих изменений
         * пройдут а некоторые нет.
         */
        android.os.Process.killProcess(android.os.Process.myPid());
      }

    }

  }

  /**
   * Разная информация
   */
  public static class Info {
    /**
     * Используется для вещания. Означает что в настоящее время провайдер
     * GPS доступен на устройстве
     */
    public static final String ACTION_GPS_ENABLE = "action.gps.enable.150201225200";
    /**
     * Используется для вещания. Означает что в настоящее время провайдер
     * GPS не доступен на устройстве
     */
    public static final String ACTION_GPS_DISABLE = "action.gps.disable.150201225201";
    /**
     * Используется для вещания. Означает что в настоящее время провайдер
     * местопложения Network доступен на устройстве
     */
    public static final String ACTION_LOC_NETWORK_ENABLE = "action.location.network.enable.150201231200";
    /**
     * Используется для вещания. Означает что в настоящее время провайдер
     * местопложения Network не доступен на устройстве
     */
    public static final String ACTION_LOC_NETWORK_DISABLE = "action.location.network.disable.150201231201";

    /**
     * Проверка запущен ли и работает сервис (1)
     *
     * @param serviceClass (1) -- имя класса сервиса, например "SMain.class"
     * @return TRUE если сервис (1) запущен и работает, иначе FALSE
     */
    public static boolean serviceRunning(Class<?> serviceClass) {
      ActivityManager manager = (ActivityManager) Bysa_01.appContext.getSystemService(Context.ACTIVITY_SERVICE);
      for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
        if (serviceClass.getName().equals(service.service.getClassName())) {
          return true;
        }
      }
      return false;
    }

    /**
     * Каждые (2) милисекунд бросает локальный бродкаст с action (1)
     *
     * @param action (1) --
     * @param period (2) -- периодичность в милисекундах
     */
    public static void broadcastSome(final Context context, final String action, final int period) {
      final Handler handler = new Handler();
      Runnable r = new Runnable() {
        @Override
        public void run() {
          LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(action));
          handler.postDelayed(this, period);
        }
      };
      r.run();
    }

    /**
     * Вещание в локальный бродкаст о доступности провайдера GPS
     *
     * @param context (1) -- контекст
     * @param period  (2) -- периодичность оповещения в милисекундах
     */
    public static void broadcastGPSEnable(final Context context, final int period) {
      final LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
      final Handler handler = new Handler();
      Runnable r = new Runnable() {
        @Override
        public void run() {
          if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(ACTION_GPS_ENABLE));
          } else {
            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(ACTION_GPS_DISABLE));
          }
          handler.postDelayed(this, period);
        }
      };
      r.run();
    }

    /**
     * @param context
     * @return TRUE елси провайдер GPS доступен
     */
    public static boolean gpsEnable(Context context) {
      LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
      return lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    /**
     * @param context
     * @return TRUE если провайдер определения местоположения Network
     * доступен
     */
    public static boolean networkLocationEnable(Context context) {
      LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
      return lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    /**
     * Вещание в локальный бродкаст о доступности провайдера местоположения
     * Network
     *
     * @param context (1) -- контекст
     * @param period  (2) -- периодичность оповещения в милисекундах
     */
    public static void broadcastLocationNetworkEnable(final Context context, final int period) {
      final LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
      final Handler handler = new Handler();
      Runnable r = new Runnable() {
        @Override
        public void run() {
          if (lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(ACTION_LOC_NETWORK_ENABLE));
          } else {
            LocalBroadcastManager.getInstance(context)
              .sendBroadcast(new Intent(ACTION_LOC_NETWORK_DISABLE));
          }
          handler.postDelayed(this, period);
        }
      };
      r.run();
    }

    /**
     * Возвращает TRUE если в настоящий момент есть подключение к интернет
     * (любой тип подключения)
     * <p>
     * Требует разрешение android:name="android.permission.ACCESS_NETWORK_STATE"
     *
     * @param context   (1) --
     * @param showToast (2) -- если TRUE то в случае проблем с интернетом будет отображен Toast
     * @return --
     */
    public static boolean internetHave(Context context, boolean showToast) {
      ConnectivityManager conm = (ConnectivityManager) context
        .getSystemService(Context.CONNECTIVITY_SERVICE);
      NetworkInfo networkInfo = conm.getActiveNetworkInfo();
      if (networkInfo != null && networkInfo.isConnected()) {
        return true;
      }
      if (showToast) {
        Toast
          .makeText(
            context,
            EStrings._NO_INTERNET_2.val(ETextCase.AS_IS),
            Toast.LENGTH_LONG
          )
          .show();
      }
      return false;
    }

    /**
     * TRUE если есть подключение к сети, иначе FALSE
     *
     * @param intent (1) -- интент ConnectivityManager.CONNECTIVITY_ACTION
     * @return
     */
    public static boolean internetHaveFromIntent(Intent intent) {
      Bundle extras = intent.getExtras();
      for (String key : extras.keySet()) {
        if (key.equalsIgnoreCase("noConnectivity")) {
          if (extras.getBoolean(key)) {
            return false;
          }
        }
      }
      return true;
    }

    /**
     * TRUE если есть подключение к интернет по WIFI, иначе FALSE
     *
     * @param intent (1) -- интент ConnectivityManager.CONNECTIVITY_ACTION
     * @return
     */
    public static boolean internetHaveWIFI(Intent intent) {
      Object oj = intent.getExtras().get("networkInfo");
      NetworkInfo n = null;
      if (oj != null) {
        n = (NetworkInfo) oj;
      } else {
        return false;
      }
      if (n.getType() == ConnectivityManager.TYPE_WIFI) {
        return n.getState() == State.CONNECTED;
      }
      return false;
    }

    /**
     * TRUE если есть подключение к интернет через мобильные технологии (3G
     * и т.д), иначе FALSE
     *
     * @param intent (1) -- интент ConnectivityManager.CONNECTIVITY_ACTION
     * @return
     */
    public static boolean internetHaveMobile(Intent intent) {
      Object oj = intent.getExtras().get("networkInfo");
      NetworkInfo n = null;
      if (oj != null) {
        n = (NetworkInfo) oj;
      } else {
        return false;
      }
      if (n.getType() == ConnectivityManager.TYPE_MOBILE) {
        return n.getState() == State.CONNECTED;
      }
      return false;
    }

    /**
     * Вывод в информации об extras интента
     *
     * @param intent (1) -- интент
     * @param tag    (2) -- тег для лога
     */
    public static void intentPrint(Intent intent, String tag) {
      Log.v(tag, "action: " + intent.getAction());
      Log.v(tag, "component: " + intent.getComponent());
      Bundle extras = intent.getExtras();
      if (extras != null) {
        for (String key : extras.keySet()) {
          Log.v(tag, "key [" + key + "]: " + extras.get(key));
        }
      } else {
        Log.v(tag, "no extras");
      }
    }
  }

  /**
   * Разные ID/константы
   */
  public static class IDS {
    /**
     * Градусы Цельсия
     */
    public static final String TEMPERATURE_UNIT_CELSIUS = "TEMPERATURE_UNIT_CELSIUS_150307213200";
    /**
     * Градусы Фаренгейт
     */
    public static final String TEMPERATURE_UNIT_FAHRENHEIT = "TEMPERATURE_UNIT_FAHRENHEIT_150307213201";
    /**
     * Единица измерения давления. Паскали
     */
    public static final String PRESSURE_UNIT_PASCAL = "PRESSURE_UNIT_PASCAL_150307213400";
    /**
     * Единица измерения давления. Милиметры ртутного столба
     */
    public static final String PRESSURE_UNIT_MMHG = "PRESSURE_UNIT_MMHG_150307213401";
  }

  /**
   * Интерфейсы
   */
  public static class I {
    public interface CallbackInt {
      void invoke(int i);
    }

    public interface CallbackInt_B {
      void invoke(int i);

      void invoke2(int i);
    }

    public interface Converter {
      Float convert(Float val, boolean direction);
    }
  }

}
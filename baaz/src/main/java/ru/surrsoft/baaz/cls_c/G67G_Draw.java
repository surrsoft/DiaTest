package ru.surrsoft.baaz.cls_c;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import ru.surrsoft.baaz.Bysa_01;
import ru.surrsoft.baaz.cls_a.AndrAttrs_01;
import ru.surrsoft.baaz.univers.BuString;
import ru.surrsoft.baaz.univers.Color2;
import ru.surrsoft.baaz.univers.TwoColors;

/**
 * Разное касающееся отрисовки
 * <p>
 * ВЕРСИЯ 2 1.1 2018-11-11 (stored)
 * ВЕРСИЯ 1 1.0 2016-04-09 (stored)
 */
public class G67G_Draw {
  private static final String TAG = "G67G.Draw";

  /**
   *
   * @param widthMeasureSpec (1) --
   * @param heightMeasureSpec (2) --
   * @return --
   */
  public static String measureSpecsToString(int widthMeasureSpec, int heightMeasureSpec) {
    String stW = View.MeasureSpec.toString(widthMeasureSpec);
    String stH = View.MeasureSpec.toString(heightMeasureSpec);
    return String.format("sw=%s; sh=%s", stW, stH);
  }

  public static String layoutSpecsToString(boolean change, int l, int t, int r, int b) {
    BuString bst = new BuString();
    bst.divider(";");
    bst
      .append("change=" + change)
      .append("l=" + l)
      .append("t=" + t)
      .append("r=" + r)
      .append("b=" + b);
    return bst.toString();
  }

  /**
   * T ODO метод не устойчив к багу [w130w]
   * Отличается от стандартного метода Canvas.drawLine только тем что корректирует непонятку [w96w]
   *
   * @param x1     (1) -- координата Х начальной точки
   * @param y1     (2) -- координата Y начальной точки
   * @param x2     (3) -- координата Х конечной точки
   * @param y2     (4) -- координата Y конечной точки
   * @param p      (5) --
   * @param canvas (6) -- холст на котором будет нарисована линия
   */
  public static void drawLine(float x1, float y1, float x2, float y2, Paint p, Canvas canvas) {
    if (x2 == x1) {
      y2++;
    }
    if (y2 == y1) {
      x2++;
    }
    canvas.drawLine(x1, y1, x2, y2, p);
  }

  /**
   * (!) В новых разработках следует использовать версию B (T ODO).
   * Отрисовка вертикальной линии толщиной (2) на расстоянии (1) от левого края холста (7)
   *
   * @param padding_dp (1) --
   * @param th_dp      (2) --
   * @param density    (3) --
   * @param topY_px    (4) -- координата начала линии по оси Y
   * @param bottomY_px (5) -- координата конца линии по оси Y
   * @param paint      (6) --
   * @param canvas     (7) --
   */
  public static void drawLineL(int padding_dp, int th_dp, float density, int topY_px, int bottomY_px, Paint paint, Canvas canvas) {
    int th_px = px(th_dp, density);
    paint.setStrokeWidth(th_px);

    int c33 = centerLineLT(padding_dp, th_dp, density);
    drawLine(c33, topY_px, c33, bottomY_px, paint, canvas);
  }

  /**
   * (!) В новых разработках следует использовать версию B (T ODO).
   * Отрисовка вертикальной линии толщиной (2) на расстоянии (1) от правого края холста (7)
   *
   * @param padding_dp (1) --
   * @param th_dp      (2) --
   * @param density    (3) --
   * @param topY_px    (4) -- координата начала линии по оси Y
   * @param bottomY_px (5) -- координата конца линии по оси Y
   * @param p          (6) --
   * @param c          (7) --
   */
  public static void drawLineR(int padding_dp, int th_dp, float density, int topY_px, int bottomY_px, Paint p, Canvas c) {
    int th_px = px(th_dp, density);
    p.setStrokeWidth(th_px);

    int c33 = centerLineRB(padding_dp, th_dp, density);
    drawLine(c.getWidth() - 1 - c33, topY_px, c.getWidth() - 1 - c33, bottomY_px, p, c);
  }

  /**
   * (!) В новых разработках следует использовать версию B (T ODO).
   * Отрисовка горизонтальной линии толщиной (2) на расстоянии (1) от верхнего края холста (7)
   *
   * @param padding_dp (1) --
   * @param th_dp      (2) -- толщина линии в dip
   * @param density    (3) --
   * @param leftX_px   (4) -- координата начала линии по оси X
   * @param rightX_px  (5) -- координата конца линии по оси X
   * @param paint      (6) --
   * @param canvas     (7) --
   */
  public static void drawLineT(int padding_dp, int th_dp, float density, int leftX_px, int rightX_px, Paint paint, Canvas canvas) {
    int th_px = px(th_dp, density);
    paint.setStrokeWidth(th_px);

    int c33 = centerLineLT(padding_dp, th_dp, density);
    drawLine(leftX_px, c33, rightX_px, c33, paint, canvas);
  }

  /**
   * (!) В новых разработках следует использовать версию B.
   * Отрисовка на холсте (7) горизонтальной линии толщиной (2) на расстоянии (1) от нижнего края холста (7)
   *
   * @param padding_dp (1) -- отступ снизу
   * @param th_dp      (2) -- толщина линии в dip
   * @param density    (3) --
   * @param leftX_px   (4) -- координата начала линии по оси X
   * @param rightX_px  (5) -- координата конца линии по оси X
   * @param p          (6) --
   * @param c          (7) --
   */
  public static void drawLineB(int padding_dp, int th_dp, float density, int leftX_px, int rightX_px, Paint p, Canvas c) {
    int th_px = px(th_dp, density);
    p.setStrokeWidth(th_px);

    int c33 = centerLineRB(padding_dp, th_dp, density);
    drawLine(leftX_px, c.getHeight() - 1 - c33, rightX_px, c.getHeight() - 1 - c33, p, c);
  }

  /**
   * Версия А неустойчива к багу [w130w]. Эта версия по другому сконструирована и кроме того устойчива к вышеуказанному багу
   *
   * @param padding_dp (1) -- отступ снизу
   * @param th_dp      (2) -- толщина линии
   * @param leftX_px   (3) -- левая координата линии
   * @param rightX_px  (4) -- правая координата линии
   * @param paint      (5) --
   * @param height_px  (6) -- высота объекта на котором будет отрисовка
   * @param canvas     (7) -- холст на котором будет отрисовка
   */
  public static void drawLineB_B(int padding_dp, int th_dp, int leftX_px, int rightX_px, Paint paint, int height_px, Canvas canvas) {
    float fDensity = Bysa_01.appContext.getResources().getDisplayMetrics().density;
    //---
    int th_px = px(th_dp, fDensity);
    paint.setStrokeWidth(th_px);
    int c33 = centerLineRB(padding_dp, th_dp, fDensity);
    int h = height_px - 1 - c33;
    drawLine(leftX_px, h, rightX_px, h, paint, canvas);
  }

  /**
   * Сохранение bitmap (1) в файл (3) расположенный в папке (2) внешнего хранилища (например
   * storage/emulated/0/someFolder). Сохранение выполняется в формате PNG
   *
   * @param bitmap     (1) --
   * @param folderName (1) -- например "someFolder"
   * @param fileName   (1) -- например "file.png"
   */
  public static void saveBitmapToFile(Bitmap bitmap, String folderName, String fileName) {
    FileOutputStream fos = null;
    try {
      String pathF = G67G_Storages.getExternalFd();

      G67G_Storages.createFileEmpty(fileName, folderName);

      String fph = pathF + File.separator + folderName + File.separator + fileName;
      fos = new FileOutputStream(fph);

      bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
      fos.flush();
      fos.close();
    } catch (FileNotFoundException e) {
      Log.e(TAG, "(!) FileNotFoundException");
      e.printStackTrace();
    } catch (IOException e) {
      Log.e(TAG, "(!) IOException");
      e.printStackTrace();
    }
  }

  public static BitmapDrawable bitmapToBitmapDrawable(Bitmap bitmap) {
    return new BitmapDrawable(bitmap);
  }

  /**
   * Просто создание Bitmap с размерами (1) (2)
   *
   * @param width
   * @param height
   * @return
   */
  public static Bitmap createBitmap(int width, int height) {
    return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
  }

  /**
   * НЕ РАБОТАЕТ!
   * http://stackoverflow.com/questions/3035692/how-to-convert-a-drawable-to-a-bitmap
   *
   * @param drawable
   * @return
   */
  public static Bitmap drawableToBitmap(Drawable drawable) {
    if (drawable instanceof BitmapDrawable) {
      return ((BitmapDrawable) drawable).getBitmap();
    }

    int width = drawable.getIntrinsicWidth();
    width = width > 0 ? width : 1;
    int height = drawable.getIntrinsicHeight();
    height = height > 0 ? height : 1;

    Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
    drawable.draw(canvas);

    return bitmap;
  }

  /**
   * Получение объекта Paint для рисования контура
   *
   * @param color (1) -- цвет контура
   * @param th_px (2) -- толщина контура в пикселях
   * @return
   */
  public static Paint getPaintStroke(String color, int th_px) {
    return getPaintStroke(Color.parseColor(color), th_px);
  }

  /**
   * Получение объекта Paint для рисования контура
   *
   * @param color (1) -- цвет контура
   * @param th_px (2) -- толщина контура в пикселях
   * @return
   */
  public static Paint getPaintStroke(int color, int th_px) {
    Paint p = new Paint();
    p.setColor(color);
    p.setStyle(Paint.Style.STROKE);
    p.setStrokeWidth(th_px / 1f);
    return p;
  }


  /**
   * Получение значения атрибута colorBackground темы. У всех активити такой цвет по умолчанию
   * <p>
   * ДЛЯ ПОИСКА: [activity], [color], [background]
   *
   * @param context (1) --
   * @return --
   */
  public static int getThemeColorBackground(Context context) {
    TypedValue val = new TypedValue();
    context.getTheme().resolveAttribute(AndrAttrs_01.colorBackground, val, true);
    return val.data;
  }

  /**
   * Преобразует обычный Drawable (1) в специализированный
   * StateListDrawable который имеет "затуманенность" в состоянии disabled
   * и пропадает при нажатии
   *
   * @param sourceDrawable (1) -- какой-либо Drawable
   * @param context        (2) -- для доступа к ресурсам
   * @return StateListDrawable пригодный для enable/disable (при disable
   * картинка затеняется). При нажатии картинка
   * исчезает/появляется
   * <p>
   * #version 2 24-05-2016  #id [[w243w]]
   */
  public static StateListDrawable drawableToStateListDrawable(Drawable sourceDrawable, Context context) {
    // Drawable для состояния "нажато"
    // - пустое место - прозрачно
    GradientDrawable gdT = new GradientDrawable();
    gdT.setColor(Color.TRANSPARENT);

    // Изображение на базе заданного для состояния disabled -
    // "затуманенное" с помощью Alpha (disabled) изображение. Приходится
    // получать
    // BitmapDrawable, иначе StateListDrawable игнорирует setAlpha и
    // любые дригие фильтры
    Paint paint = new Paint();
    paint.setAlpha(50);
    BitmapDrawable bitmapDrawable = drawableToBitmapDrawable(sourceDrawable, paint);

    // построение итогового Drawable
    StateListDrawable sld = new StateListDrawable();
    // состояние "нажато"
    sld.addState(new int[]{android.R.attr.state_pressed}, gdT);
    // состояние "disabled"
    sld.addState(new int[]{-android.R.attr.state_enabled}, bitmapDrawable);
    // обычное состояние
    sld.addState(new int[]{}, sourceDrawable);

    return sld;
  }

  /**
   * Преобразование drawable (1) в StateListDrawable с двумя состояниями - нормальное и нажато.
   * <p>
   * От А отличается тем что нет состояния
   * disabled, и возможностью задавать цвет в нормальном и нажатом состоянии.
   * <p>
   * Метод учитывает проблему [w221w], т.е. получаемый StateListDrawable корректно работает на
   * младших версиях Android
   *
   * @param drw (1) -- drawable из которого нужно сделать StateListDrawable
   * @param tc  (2) -- getColor должен возвращать цвет в НОРМАЛЬНОМ сотоянии (или null);
   *            getColorPressed - должен возвращать цвет в НАЖАТОМ состоянии (или null).
   *            Может быть = null
   * @return #version 1 07-04-2016  #id [[w242w]]
   */
  @Nullable
  public static StateListDrawable drawableToStateListDrawable_B(Drawable drw, TwoColors tc) {
    if (drw == null) return null;
    Drawable drw1;
    Drawable drw2;
    if (tc != null) {
      Integer color = tc.getColor();
      Paint p1 = null;
      if (color != null) {
        p1 = new Paint();
        p1.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP));
      }
      drw1 = drawableToBitmapDrawable(drw, p1);
      //
      Integer colorPressed = tc.getColorPressed();
      Paint p2 = null;
      if (colorPressed != null) {
        p2 = new Paint();
        p2.setColorFilter(new PorterDuffColorFilter(colorPressed, PorterDuff.Mode.SRC_ATOP));
      }
      drw2 = drawableToBitmapDrawable(drw, p2);
    } else {
      drw1 = drawableToBitmapDrawable(drw, null);
      drw2 = drawableToBitmapDrawable(drw, null);
    }
    StateListDrawable sld = new StateListDrawable();
    sld.addState(new int[]{android.R.attr.state_pressed}, drw2);
    sld.addState(new int[]{}, drw1);
    return sld;

  }

  /**
   * Генерирует StateListDrawable из Drawable (1).
   * От версии B отличается тем что позволяет также задать цвет для состояния disabled
   * <p>
   * Не следует использовать один экземпляр в нескольких местах - для каждого места нужно
   * генерировать новый StateListDrawable
   *
   * @param drw           (1) -- Drawable на базе PNG
   * @param normalColor   (1) -- цвет в обычном состоянии; можно указать здесь null
   * @param pressedColor  (2) -- цвет в нажатом состоянии; можно указать здесь null
   * @param disabledColor (3) -- цвет в disabled состоянии; можно указать здесь null
   * @return #version 1 24-05-2016  #id[[w247w]]
   */
  public static StateListDrawable drawableToStateListDrawable_C(
    Drawable drw, Color2 normalColor, Color2 pressedColor, Color2 disabledColor, Context ctx ) {
    Drawable d1 = null, d2 = null, d3 = null;
    if (normalColor != null) {
      Paint p1 = new Paint();
      p1.setColorFilter(new PorterDuffColorFilter(normalColor.val, PorterDuff.Mode.SRC_ATOP));
      d1 = drawableToBitmapDrawable(drw, p1);
    }
    if (pressedColor != null) {
      Paint p2 = new Paint();
      p2.setColorFilter(new PorterDuffColorFilter(pressedColor.val, PorterDuff.Mode.SRC_ATOP));
      d2 = drawableToBitmapDrawable(drw, p2);
    }
    if (disabledColor != null) {
      Paint p3 = new Paint();
      p3.setColorFilter(new PorterDuffColorFilter(disabledColor.val, PorterDuff.Mode.SRC_ATOP));
      d3 = drawableToBitmapDrawable(drw, p3);
    }
    StateListDrawable sld = new StateListDrawable();
    sld.addState(new int[]{android.R.attr.state_pressed}, d2);
    sld.addState(new int[]{-android.R.attr.state_enabled}, d3);
    sld.addState(new int[]{}, d1);
    return sld;
  }

  /**
   * Получение объекта StateListDrawable на базе Drawable (1) и его цветов в нормальном (2), нажатом
   * (3) и disable (4) состоянии
   * <p>
   * Технически, создаются копии drawable (1) для каждого из цветов (2)-(4). На базе этих копий
   * формируется итоговый StateListDrawable
   *
   * @param drw           (1) -- drawable
   * @param normalColor   (2) -- цвет в нормальном состоянии
   * @param pressedColor  (3) -- цвет в нажатом состоянии
   * @param disabledColor (4) -- цвет в disable состоянии
   * @return --
   */
  public static StateListDrawable drawableToStateListDrawable_D(
    Drawable drw,
    Color2 normalColor,
    Color2 pressedColor,
    Color2 disabledColor
  ) {
    Drawable d1 = null, d2 = null, d3 = null;
    //---
    if (normalColor != null) {
      d1 = drawableClone(drw, Bysa_01.appContext);
      d1.mutate();
      DrawableCompat.setTint(d1, normalColor.val);
    }
    if (pressedColor != null) {
      d2 = drawableClone(drw, Bysa_01.appContext);
      d2.mutate();
      DrawableCompat.setTint(d2, pressedColor.val);
    }
    if (disabledColor != null) {
      d3 = drawableClone(drw, Bysa_01.appContext);
      d3.mutate();
      DrawableCompat.setTint(d3, disabledColor.val);
    }
    //---
    StateListDrawable sld = new StateListDrawable();
    sld.addState(new int[]{android.R.attr.state_pressed}, d2);
    sld.addState(new int[]{-android.R.attr.state_enabled}, d3);
    sld.addState(new int[]{}, d1);
    //---
    return sld;
  }

  /**
   * Преобразовывает int код цвета в HEX
   * <p>
   * Для поиска [color]
   *
   * @param color (1) -- int цвет, например Color.RED
   * @return например "#FF0000"
   */
  public static String colorIntToString(int color) {
    return "#" + Integer.toHexString(color);
    //return String.format("#%06X", (0xFFFFFF & textColor));
  }

  /**
   * Генерирует из (2) независымый StateListDrawable и применяет его к (1) в качестве фона.
   * Паралельно устанавливает setClickable(true) для (1) если указан цвет при нажатии
   *
   * @param view (1) --
   * @param tc   (2) -- определяет Drawable, цвет в НОРМАЛЬНОМ
   *             и НАЖАТОМ состояниях (getDrawable(), getColor() и getColorPressed() соответственно)
   * @return null либо полученный в результате преобразования (2) StateListDrawable
   * #version 1 09-04-2016 160409112400
   */
  @Nullable
  public static StateListDrawable setBackgroundSLD(View view, TwoColors tc) {
    if (tc == null || tc.getDrawable() == null) return null;
    StateListDrawable sld = drawableToStateListDrawable_B(tc.getDrawable(), tc);
    if (sld != null) {
      view.setBackgroundDrawable(sld);
      if (tc != null && tc.getColorPressed() != null) {
        view.setClickable(true);
      }
    }
    return sld;
  }

  /**
   * На базе drawable (1) создается BitmapDrawable никак не связанный с
   * (1), который можно корректно передать в StateListDrawable у котого на младших версиях Android есть проблема [w221w]
   *
   * @param drw     (1) -- объект Drawable
   * @param p       (3) -- Paint c фильтром, примеры <li>Paint p = new
   *                Paint(); <br>
   *                p.setAlpha(50); <br>
   *                <li>Paint p = new Paint(); <br>
   *                p.setColorFilter(new
   *                PorterDuffColorFilter(Color.parseColor("#50FF0000"),
   *                PorterDuff.Mode.SRC_ATOP));
   *                <br> <b>СЛЕДУЕТ ИСПОЛЬЗОВАТЬ ИМЕННО ЭТОТ ВАРИАНТ ЕСЛИ НУЖНО ЗАДАТЬ ЦВЕТ</b>
   *                </li>
   * @return null при неудачах; возвращаемый BitmapDrawable никак не связан с исходным Drawable, является mutable
   * #version 2.0.1 09-04-2016 160409094700  #id [w222w]
   */
  public static BitmapDrawable drawableToBitmapDrawable(Drawable drw, Paint p) {
    Bitmap b1 = ((BitmapDrawable) drw).getBitmap();
    Bitmap b2 = Bitmap.createBitmap(drw.getIntrinsicWidth(), drw.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(b2);
    //рисуем b1 поверх b2
    canvas.drawBitmap(b1, 0, 0, p);
    return new BitmapDrawable(Bysa_01.appContext.getResources(), b2);
  }

  /**
   * Преобразование drawable (1) в mutable drawable цвета (2) пригодный для использования в
   * StateListDrawable (см. проблему [w221w])
   *
   * @param drw   (1) -- drawable для преобразования
   * @param color (2) -- цвет который должен быть у итогового drawable
   * @return #version 1 09-04-2016  #id[[w245w]] 160409101300
   */
  public static BitmapDrawable drawableToBitmapDrawable(Drawable drw, int color) {
    if (drw == null) return null;
    Paint p = new Paint();
    p.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP));
    return drawableToBitmapDrawable(drw, p);
  }

  /**
   * Синоним для {@link #drawableToBitmapDrawable(Drawable, int)}
   *
   * @param drw   (1) -- см. {@link #drawableToBitmapDrawable(Drawable, int)}
   * @param color (2) -- см. {@link #drawableToBitmapDrawable(Drawable, int)}
   * @return --
   * <p>
   * #version 1 24-05-2016  #id[[w244w]]
   */
  public static BitmapDrawable drawableToColorBitmapDrawable(Drawable drw, int color) {
    return drawableToBitmapDrawable(drw, color);
  }

  /**
   * На базе Drawable (1) создает Drawable (2) обладающий указанными цветами в указанных состояниях (2).
   * Drawable (2) никак не связан с Drawable (1)
   * <p>
   * Проверено, работает: API 16 (Android 4.1.1), API 21 (Android 5.0)
   *
   * @param sourceDrw (1) --
   * @param csl       (2) --
   * @return --
   * <p>
   * #version 1 25.05.2016  #id [[w250w]]
   */
  public static Drawable drawableToDrawableColoredState(Drawable sourceDrw, ColorStateList csl) {
    Drawable d = sourceDrw.getConstantState().newDrawable(Bysa_01.appContext.getResources());
    Drawable d1 = DrawableCompat.wrap(d);
    DrawableCompat.setTintList(d1, csl);
    return d1;
  }

  /**
   * Возвращает клон drawable (1). Клонированный таким образом drawable не следует использовать
   * в StateListDrawable из-за проблемы [w221w]
   *
   * @param drw     (1) -- Drawable для клонирования
   * @param context
   * @return Drawable
   */
  public static Drawable drawableClone(Drawable drw, Context context) { // find_f100f
    return drw.getConstantState().newDrawable(context.getResources());
  }

  /**
   * Конвертирует размер dip (1) в размер в px
   *
   * @param aDip       (1) -- размер в dip
   * @param aResources (2) -- ссылка на ресурсы
   * @return размер в px
   */
  public static int convertDipToPx(int aDip, Resources aResources) {
    return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, aDip, aResources.getDisplayMetrics());
  }

  /**
   * Метод-заправщик. Заправляет canvas (1). Отрисовывает по краю canvas
   * (1) прямоугольный контур шириной (3) и высотой (4) (как правило,
   * данные значения соответствуют ширине и высоте некоего view) с
   * указанным отступом от данных краев (5)(6)(7)(8)
   *
   * @param canvas        (1) -- холст
   * @param paint         (2) -- кисть
   * @param widthView     (3) -- ширина в пикселях [0...]
   * @param heightView    (4) -- высота в пикселях [0...]
   * @param paddingLeft   (5) -- отступ слева в пикселях [0...]
   * @param paddingRight  (6) -- -/-
   * @param paddingTop    (7) -- -/-
   * @param paddintBottom (8) -- -/-
   */
  public static void drawRectOnCanvasAtEdges_A(Canvas canvas, Paint paint, float widthView, float heightView,
                                               float paddingLeft, float paddingRight, float paddingTop, float paddintBottom) {

    // левый X и т.д.
    float lx = 0 + paddingLeft;
    // 1 вычитается т.к. иначе будет заезд за контур (не ясно с чем это
    // связано)
    float rx = widthView - paddingRight - 1;
    // вехний Y и т.д.
    float ty = 0 + paddingTop;
    float by = heightView - paddintBottom - 1;

    // начало с левого верхнего угла и далее по часовой стрелке
    canvas.drawLine(lx, ty, rx, ty, paint);
    canvas.drawLine(rx, ty, rx, by, paint);
    canvas.drawLine(rx, by, lx, by, paint);
    canvas.drawLine(lx, by, lx, ty, paint);

  }

  /**
   * Возвращает рамку которую сразу можно применить в качестве фона для
   * view (например с помощью метода setBackgroundDrawable)
   *
   * @param jsonString (1) -- JSON-строка {<br>
   *                   w:px ширина (обязательно), <br>
   *                   h:px высота (обязательно), <br>
   *                   paddingall:int внутренний отступ по всему периметру, <br>
   *                   textColor:int цвет рамки, <br>
   *                   th:dp толщина рамки, <br>
   *                   l:bool отрисовывать ли рамку слева, <br>
   *                   t:bool, <br>
   *                   r:bool, <br>
   *                   b:bool, <br>
   *                   tleftstart:int слева у верхнего ребра начать отрисовку с
   *                   данной позиции (пустое место оставить если сказать проще)}
   * @return null при нештатах
   * @info "Все методы"
   */
  public static BitmapDrawable border(String jsonString, Context context) {
    JSONObject jso;
    try {
      jso = new JSONObject(jsonString);
    } catch (JSONException e) {
      e.printStackTrace();
      return null; // ============X
    }

    if (jso.length() < 1) {
      return null; // =========X
    }

    // ======= дефолтные значения
    int w = 0;
    int h = 0;
    int paddingall = 0;
    int color = Color.RED;
    int th = 1;
    boolean l = true;
    boolean t = true;
    boolean r = true;
    boolean b = true;
    int tleftstart = 0;

    // ========= получение данных из JSON
    String key = "w";
    if (jso.has(key)) {
      w = jso.optInt(key);
      if (w == 0) {
        return null; // ===========X
      }
    }

    key = "h";
    if (jso.has(key)) {
      h = jso.optInt(key);
      if (h == 0) {
        return null; // ===========X
      }
    }

    key = "color";
    if (jso.has(key)) {
      color = jso.optInt(key);
    }
    key = "th";
    if (jso.has(key)) {
      th = jso.optInt(key);
      th = G67G_Draw.convertDipToPx(th, context.getResources());
      Log.v(TAG, "-- th=" + th);
    }
    key = "l";
    if (jso.has(key)) {
      l = jso.optBoolean(key);
    }
    key = "r";
    if (jso.has(key)) {
      r = jso.optBoolean(key);
    }
    key = "t";
    if (jso.has(key)) {
      t = jso.optBoolean(key);
    }
    key = "b";
    if (jso.has(key)) {
      b = jso.optBoolean(key);
    }
    key = "paddingall";
    if (jso.has(key)) {
      paddingall = jso.optInt(key);
    }
    key = "tleftstart";
    if (jso.has(key)) {
      tleftstart = jso.optInt(key);
    }

    // ===========
    Paint paint = new Paint();
    paint.setColor(color);
    paint.setStrokeWidth(th);

    Bitmap pallet = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(pallet);

    // -1 добавляется иначе правая и нижняя линии получаются более
    // тонкие
    int corr = 1;
    int p = paddingall;
    if (l) {
      canvas.drawLine(p, p, p, h - p, paint);
    }
    if (r) {
      canvas.drawLine(w - p, p, w - p, h - p, paint);
    }
    if (t) {
      canvas.drawLine(tleftstart + p, p, w - p, p, paint);
    }
    if (b) {
      canvas.drawLine(p, h - p, w - p, h - p, paint);
    }

    BitmapDrawable bde = new BitmapDrawable(context.getResources(), pallet);

    return bde;
  }

  /**
   * Рисует на bitmap (2) рамку. Возвращает измененный bitmap.
   *
   * @param jsonString (1) -- JSON-строка {<br>
   *                   w:px ширина (обязательно), <br>
   *                   h:px высота (обязательно), <br>
   *                   paddingall:int внутренний отступ по всему периметру, <br>
   *                   textColor:int цвет рамки, <br>
   *                   th:dp толщина рамки, <br>
   *                   l:bool отрисовывать ли рамку слева, <br>
   *                   t:bool, <br>
   *                   r:bool, <br>
   *                   b:bool, <br>
   *                   tleftstart:int слева у верхнего ребра начать отрисовку с
   *                   данной позиции (пустое место оставить если сказать проще)}
   * @return null при нештатах
   * @info "Все методы"
   */
  public static Bitmap border_B(String jsonString, Bitmap bitmap, Context context) {
    JSONObject jso;
    try {
      jso = new JSONObject(jsonString);
    } catch (JSONException e) {
      e.printStackTrace();
      return null; // ============X
    }

    if (jso.length() < 1) {
      return null; // =========X
    }

    // ======= дефолтные значения
    int w = 0;
    int h = 0;
    int paddingall = 0;
    int color = Color.RED;
    int th = 1;
    boolean l = true;
    boolean t = true;
    boolean r = true;
    boolean b = true;
    int tleftstart = 0;

    // ========= получение данных из JSON
    String key = "w";
    if (jso.has(key)) {
      w = jso.optInt(key);
      if (w == 0) {
        return null; // ===========X
      }
    }

    key = "h";
    if (jso.has(key)) {
      h = jso.optInt(key);
      if (h == 0) {
        return null; // ===========X
      }
    }

    key = "color";
    if (jso.has(key)) {
      color = jso.optInt(key);
    }
    key = "th";
    if (jso.has(key)) {
      th = jso.optInt(key);
      th = G67G_Draw.convertDipToPx(th, context.getResources());
      Log.v(TAG, "-- th=" + th);
    }
    key = "l";
    if (jso.has(key)) {
      l = jso.optBoolean(key);
    }
    key = "r";
    if (jso.has(key)) {
      r = jso.optBoolean(key);
    }
    key = "t";
    if (jso.has(key)) {
      t = jso.optBoolean(key);
    }
    key = "b";
    if (jso.has(key)) {
      b = jso.optBoolean(key);
    }
    key = "paddingall";
    if (jso.has(key)) {
      paddingall = jso.optInt(key);
    }
    key = "tleftstart";
    if (jso.has(key)) {
      tleftstart = jso.optInt(key);
    }

    // ===========
    Paint paint = new Paint();
    paint.setColor(color);
    paint.setStrokeWidth(th);

    Canvas canvas = new Canvas(bitmap);

    // -1 добавляется иначе правая и нижняя линии получаются более
    // тонкие
    int corr = 1;
    int p = paddingall;
    if (l) {
      canvas.drawLine(p, p, p, h - p, paint);
    }
    if (r) {
      canvas.drawLine(w - p, p, w - p, h - p, paint);
    }
    if (t) {
      canvas.drawLine(tleftstart + p, p, w - p, p, paint);
    }
    if (b) {
      canvas.drawLine(p, h - p, w - p, h - p, paint);
    }

    return bitmap;
  }

  /**
   * Преобразует dip (1) в px. Если после округления px в диапазоне 0...1, возвращает 1.
   * Если (1) == 0, возвращает 0
   *
   * @param dp      (1) -- размер в dip
   * @param density (2) -- плотность экрана (пример получения - getResources().getDisplayMetrics().density)
   * @return размер в px
   */
  public static int px(int dp, float density) {
    if (dp == 0) return 0; //======X
    int i = Math.round(dp * density);
    if (i < 1) {
      return 1;
    }
    return i;
  }


  /**
   * Возвращает координату отрисовки "центра" линии на основе padding (1), толщины линии (2) и разрешения экрана (3).
   * Относится к рисованию слева/сверху. Для рисования справа/снизу используется centerLineRB
   *
   * @param paddingDp
   * @param thDp
   * @param density
   * @return
   */
  public static int centerLineLT(int paddingDp, int thDp, float density) {
    if (thDp < 1) return -1; //======X

    //=== th
    int thPx = G67G_Draw.px(thDp, density);

    //=== padding
    int paddingPx = px(paddingDp, density);

    //=== th/2
    int thPx2;
    if (thPx == 1) {
      thPx2 = 1;
    } else {
      thPx2 = (int) Math.floor(thPx / 2d);
    }

    //===
    int res = paddingPx + thPx2;

    return res;
  }

  /**
   * Возвращает смещение "центра" линии на основе padding (1), толщины линии (2) и разрешения экрана (3).
   * Относится к рисованию справа/снизу. Для рисования слева/сверху используется centerLineLT.
   * Используется например так: "x = width - centerLineRB(...);"
   *
   * @param paddingDp
   * @param thDp
   * @param density
   * @return
   */
  public static int centerLineRB(int paddingDp, int thDp, float density) {
    if (TMath_01.isEven(px(thDp, density))) {
      return centerLineLT(paddingDp, thDp, density) - 1;
    } else {
      return centerLineLT(paddingDp, thDp, density);
    }
  }

  /**
   * Преобразует dip (1) в (px)
   *
   * @param dp (1) -- dip
   * @return #version 1 03-04-2016 08-06
   */
  public static int px(float dp) {
    if (dp == 0) return 0;
    int i = Math.round(dp * Bysa_01.fDensity);
    if (i < 1) {
      return 1;
    }
    return i;
  }

  /**
   * Преобразует px (1) в dip
   *
   * @param px (1) -- dip
   */
  public static int dp(int px) {
    if (px == 0) return 0;
    int i = Math.round(px / Bysa_01.fDensity);
    if (i < 1) {
      return 1;
    }
    return i;
  }

  /**
   * Преобразует dip (1) в px. Если после округления px в диапазоне 0...1, возвращает 1.
   * Если (1) == 0, возвращает 0
   *
   * @param dp      (1) -- размер в dip
   * @param density (2) -- плотность экрана (пример получения - getResources().getDisplayMetrics().density)
   * @return размер в px
   */
  public static int px(float dp, float density) {
    if (dp == 0) return 0; //======X
    int i = Math.round(dp * density);
    if (i < 1) {
      return 1;
    }
    return i;
  }

  /**
   * Преобразует массив dip (1) в массив px. См. описание {@link #px(int, float)}
   *
   * @param dp      (1) -- массив dip
   * @param density (2) -- плотность экрана (пример получения - getResources().getDisplayMetrics().density)
   * @return массив px
   */
  public static int[] px(int[] dp, float density) {
    int[] px = new int[dp.length];
    for (int i = 0; i < dp.length; i++) {
      px[i] = px(dp[i], density);
    }
    return px;
  }

  /**
   * Перевод sp > floatPx. Если sp == 0 или меньше 0 то на выходе 0.
   * Если после преобразования px < 1 то возвращается 1
   *
   * @param sp (1) --
   * @return
   */
  public static float pxSp(int sp) {
    if (sp == 0 || sp < 0) return 0; //======X
    float f = sp * Bysa_01.fScaledDensity;
    return (f < 1.0f) ? 1f : f;
  }

  /**
   * Класс представляющий ширину и высоту
   */
  public static class HW {
    public int width = 0;
    public int height = 0;

    public HW(int height, int width) {
      this.height = height;
      this.width = width;
    }
  }

}

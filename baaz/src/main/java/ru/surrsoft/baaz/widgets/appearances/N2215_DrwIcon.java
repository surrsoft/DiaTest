package ru.surrsoft.baaz.widgets.appearances;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.view.ViewTreeObserver;

import java.util.Arrays;

import ru.surrsoft.baaz.Bysa_01;
import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.univers.TwoColors;

/**
 * Программно рисует иконку на background View указанного через метод {@link #setDrawedView(View)}
 * <p/>
 * Используется техника [w213w]
 * <p/>
 * ИСПОЛЬЗОВАНИЕ: сначала в конструктор передаются параметры иконки , затем методом
 * {@link #setDrawedView(View)} задается View на background которого иконка должна быть отрисована;
 * больше ничего не требуется - иконка будет отрисована автоматически при стандартном проходе отрисовки
 * <p/>
 * #version 1.1 15-12-2016  #id [[w215w]]
 */
public class N2215_DrwIcon {
  private static final int LINECOLOR = 0;
  private static final int BGCOLOR = 1;
  private static final int PRESS_LINECOLOR = 2;
  private static final int PRESS_BGCOLOR = 3;
  private final ETypes type;
  private int[] colors = null;
  private int[] thDps = null;
  public final int padding_dp;
  public TwoColors tc;

  public enum ETypes {
    /**
     * Крестик в виде Х. Требует colors{цвет_в_норме, цвет_при_нажатии}, thDps{толщина_линии}
     */
    KRESTIK(),
    /**
     * Три точки расположенные вертикально. С помощью tc.setThDp() задается радиус точки
     */
    MORE(),
    /**
     * Прямоугольная рамка образованная наложением друг на друга прямоугольника А (фоновый) и
     * Б (передний). TC.setColor() и TC.setColorPressed() задают цвета "А"; TC.setArr_int[0] задает
     * цвет "Б" (изменение цвета в нажатом состоянии не предусмотрено); TC.setArr_int[1] задает
     * цвет толщину получаемой рамки в dp
     */
    RECT1();

    ETypes() {
    }
  }

  /**
   * @param canvas
   * @param widthPx
   * @param heightPx
   * @param isPress
   * @return TRUE если что-то было отрисовано
   */
  private boolean draw(Canvas canvas, int widthPx, int heightPx, boolean isPress) {
    boolean doLine = false;
    boolean doBg = false;

    //=== bg
    int colorBg = colors[BGCOLOR];
    if (isPress) {
      if (colors.length >= 4 && colors[PRESS_BGCOLOR] != Color.TRANSPARENT) {
        colorBg = colors[PRESS_BGCOLOR];
        doBg = true;
      }
    }
    //=== line
    int drawColor = colors[LINECOLOR];
    if (isPress) {
      if (colors.length >= 3 && colors[PRESS_LINECOLOR] != Color.TRANSPARENT) {
        drawColor = colors[PRESS_LINECOLOR];
        doLine = true;
      }
    }
    //===


    //===
    if (isPress && !doBg && !doLine) return false;

    canvas.drawColor(colorBg);
    int pad = G67G_Draw.px(padding_dp);

    if (type == ETypes.KRESTIK) {
      Paint p = new Paint();
      p.setColor(drawColor);
      p.setStrokeWidth(G67G_Draw.px(thDps[0]));
      //линия снизу вверх вправо
      int x1 = pad;
      int y1 = heightPx - pad;
      int x2 = widthPx - pad;
      int y2 = pad;
      canvas.drawLine(x1, y1, x2, y2, p);

      //линия сверху вниз вправо
      x1 = pad;
      y1 = pad;
      x2 = widthPx - pad;
      y2 = heightPx - pad;
      canvas.drawLine(x1, y1, x2, y2, p);
    }
    if (type == ETypes.MORE) {
      Paint p = new Paint();
      p.setColor(drawColor);
      p.setStyle(Paint.Style.FILL);
      //радиус кружка
      int r = G67G_Draw.px(thDps[0]);
      //верхний кружок
      int cy = pad + r;
      canvas.drawCircle(widthPx / 2, cy, r, p);
      //средний кружок
      float between = 1f;
      cy += r * 2 + r / between;
      canvas.drawCircle(widthPx / 2, cy, r, p);
      //нижний кружок
      cy += r * 2 + r / between;
      canvas.drawCircle(widthPx / 2, cy, r, p);
    }
    if (type == ETypes.RECT1) {
      int[] arr = tc.getArr_int();
      Paint p = new Paint();
      p.setColor(drawColor);
      p.setStyle(Paint.Style.FILL);
      canvas.drawRect(pad + 1, pad + 1, widthPx - 1 - pad, heightPx - 1 - pad, p);
      p.setColor(arr[0]);
      //толщина рамки
      int th = G67G_Draw.px(arr[1]);
      canvas.drawRect(pad + 1 + th, pad + 1 + th, widthPx - 1 - pad - th, heightPx - 1 - pad - th, p);
    }
    return true;
  }


  //1 //constructors

  /**
   * @param type       (1) -- тип изображения (см. {@link ETypes})
   * @param tc         (2) -- используются setColor(), setColorBg(),
   *                   setColorPressed(), setColorBgPressed(), setThDp(), setThDpPressed()
   * @param padding_dp (3) -- отступ изображения иконки от краев, в dip
   */
  public N2215_DrwIcon(ETypes type, TwoColors tc, int padding_dp) {
    this.type = type;
    this.tc = tc;

    this.colors = new int[4];
    Arrays.fill(this.colors, Color.TRANSPARENT);
    this.thDps = new int[]{1, -1};

    if (tc != null) {
      Integer color = tc.getColor();
      Integer bg = tc.getColorBg();
      Integer colorPressed = tc.getColorPressed();
      Integer bgPressed = tc.getColorBgPressed();
      Integer thDp = tc.getThDp();
      Integer thDpPressed = tc.getThDpPressed();

      if (color != null) colors[0] = color;
      if (bg != null) colors[1] = bg;
      if (colorPressed != null) colors[2] = colorPressed;
      if (bgPressed != null) colors[3] = bgPressed;

      if (thDp != null) this.thDps[0] = thDp;
      if (thDpPressed != null) this.thDps[1] = thDpPressed;

    }

    this.padding_dp = padding_dp;
  }

  //2 //constructors

  /**
   * Задание view на background которого будет выполняться отрисовка
   */
  public void setDrawedView(final View view) {
    if (view == null) return;
//        if (isUseChache)
//            view.setDrawingCacheEnabled(true);
    view.setWillNotDraw(false);
    ViewTreeObserver vto = view.getViewTreeObserver();
    if (vto.isAlive()) {
      vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
        @Override
        public boolean onPreDraw() {
          view.getViewTreeObserver().removeOnPreDrawListener(this);
          int w = view.getWidth();
          int h = view.getHeight();
          //===
          Bitmap b;
//                    if (isUseChache) {
//                        b = view.getDrawingCache();
//                    } else {
//                        b = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
//                    }
          b = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
          //===
          draw(new Canvas(b), w, h, false);
          //===
          Drawable bg = null;
          if (colors.length >= 2) {
            Bitmap bPress = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            boolean drawed = draw(new Canvas(bPress), w, h, true);
            if (drawed) {
              view.setClickable(true);
              bg = new StateListDrawable();
              ((StateListDrawable) bg).addState(new int[]{android.R.attr.state_pressed}, new BitmapDrawable(Bysa_01.appContext.getResources(), bPress));
              ((StateListDrawable) bg).addState(new int[]{}, new BitmapDrawable(Bysa_01.appContext.getResources(), b));
            }
          }
          if (bg == null)
            bg = new BitmapDrawable(Bysa_01.appContext.getResources(), b);

          //===
          view.setBackgroundDrawable(bg);
          return true;
        }
      });
    }
  }
}

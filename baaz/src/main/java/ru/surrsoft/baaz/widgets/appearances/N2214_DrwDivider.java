package ru.surrsoft.baaz.widgets.appearances;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.view.ViewTreeObserver;

import ru.surrsoft.baaz.Bysa_01;
import ru.surrsoft.baaz.cls_c.G67G_Draw;

/**
 * Рисует горизонтальную линию на background View указанного через метод {@link #setDrawedView(View, boolean)}
 * <p/>
 * Цвет фона задается методом {@link #setFillColor(int)}
 * <p/>
 * Цвет фона при нажатии задается методом {@link #setFillColorPressed(int)}
 * <p/>
 * Используется техника [w213w]
 * <p/>
 * ИСПОЛЬЗОВАНИЕ: сначала в конструктор передаются параметры линии (можно также использовать конструктор
 * с предопределением свойств линии), затем методом
 * {@link #setDrawedView(View, boolean)} задается View на background которого линия должна быть отрисована;
 * больше ничего не требуется - линия будет отрисована автоматически при стандартном проходе отрисовки
 * <p/>
 * #version 2 03.04.2016 09-23  #id [[w214w]]
 */
public class N2214_DrwDivider {
    private final ETypes type;
    private final int color;
    private final int thDp;
    private final boolean true_bottom_false_top;
    private final int padLDp;
    private final int padRDp;
    private final int padVerticalDp;
    /**
     * Цвет общей фоновой заливки
     */
    private int fillColor = Color.TRANSPARENT;




    //``````````````````````````````````````````````````````````````````````````````````````````````
    public int getFillColorPressed() {
        return fillColorPressed;
    }

    public N2214_DrwDivider setFillColorPressed(int fillColorPressed) {
        this.fillColorPressed = fillColorPressed;
        return this;
    }

    private int fillColorPressed = Color.TRANSPARENT;

    public int getFillColor() {
        return fillColor;
    }

    public N2214_DrwDivider setFillColor(int fillColor) {
        this.fillColor = fillColor;
        return this;
    }


    private void draw(Canvas canvas, int widthPx, int heightPx) {
        if (type == ETypes.PLAIN_LINE) {
            int x1 = G67G_Draw.px(padLDp);
            int x2 = widthPx - G67G_Draw.px(padRDp);
            int y1, y2;
            int thDp2 = G67G_Draw.px(thDp) / 2 + 1;
            if (true_bottom_false_top) {
                y1 = y2 = heightPx - G67G_Draw.px(padVerticalDp) - thDp2;
            } else {
                y1 = y2 = G67G_Draw.px(padVerticalDp) + thDp2;
            }
            Paint p = new Paint();
            p.setColor(color);
            p.setStrokeWidth(G67G_Draw.px(thDp));
            canvas.drawColor(fillColor);
            canvas.drawLine(x1, y1, x2, y2, p);
        }
    }

    public enum ETypes {
        /**
         * Простая прямая линия
         */
        PLAIN_LINE()
    }

    /**
     * Коллекция предопределенных линий
     */
    public enum EPredefineTypes {
        /**
         * Черная линия внизу толщиной 1dp
         */
        PLAIN_LINE1(ETypes.PLAIN_LINE, Color.BLACK, 2, true, 1, 1, 0);

        private final ETypes types;
        private final int color;
        private final int thDp;
        private final boolean true_bottom_false_top;
        private final int padLDp;
        private final int padRDp;
        private final int padVerticalDp;

        EPredefineTypes(ETypes types, int color, int thDp, boolean true_bottom_false_top, int padLDp, int padRDp, int padVerticalDp) {
            this.types = types;
            this.color = color;
            this.thDp = thDp;
            this.true_bottom_false_top = true_bottom_false_top;
            this.padLDp = padLDp;
            this.padRDp = padRDp;
            this.padVerticalDp = padVerticalDp;
        }
    }

    //1 //constructors

    /**
     * @param type                  (1) -- тип линии
     * @param color                 (2) -- цвет
     * @param thDp                  (3) -- толщина
     * @param true_bottom_false_top (4) -- расположить сверху или снизу
     * @param padLdp                (5) -- отступ слева
     * @param padRdp                (6) -- отступ справа
     * @param padVerticalDp         (7) -- отступ снизу если (4)==TRUE, и сверху если (4)==FALSE
     */
    public N2214_DrwDivider(ETypes type, int color, int thDp, boolean true_bottom_false_top,
                            int padLdp, int padRdp, int padVerticalDp) {
        this.type = type;
        this.color = color;
        this.thDp = thDp;
        this.true_bottom_false_top = true_bottom_false_top;
        this.padLDp = padLdp;
        this.padRDp = padRdp;
        this.padVerticalDp = padVerticalDp;
    }

    public N2214_DrwDivider(EPredefineTypes t, Context ctx) {
        this.type = t.types;
        this.color = t.color;
        this.thDp = t.thDp;
        this.true_bottom_false_top = t.true_bottom_false_top;
        this.padLDp = t.padLDp;
        this.padRDp = t.padRDp;
        this.padVerticalDp = t.padVerticalDp;
    }
    //2 //constructors

    /**
     * Задание view на background которого будет выполняться отрисовка
     *
     * @param view (1) --
     */
    public void setDrawedView(final View view, final boolean isUseChache) {
        if (view == null) return;
        if (isUseChache)
            view.setDrawingCacheEnabled(true);
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
                    if (isUseChache) {
                        b = view.getDrawingCache();
                    } else {
                        b = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
                    }
                    //===
                    draw(new Canvas(b), w, h);
                    //===
                    Drawable bg;
                    if (fillColorPressed != Color.TRANSPARENT) {
                        Bitmap b2 = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
                        Canvas c2 = new Canvas(b2);
                        c2.drawColor(fillColorPressed);

                        bg = new StateListDrawable();
                        ((StateListDrawable) bg).addState(new int[]{android.R.attr.state_pressed}, new BitmapDrawable(Bysa_01.appContext.getResources(), b2));
                        ((StateListDrawable) bg).addState(new int[]{}, new BitmapDrawable(Bysa_01.appContext.getResources(), b));
                    } else {
                        bg = new BitmapDrawable(Bysa_01.appContext.getResources(), b);
                    }
                    //===
                    view.setBackgroundDrawable(bg);
                    return true;
                }
            });
        }
    }
}

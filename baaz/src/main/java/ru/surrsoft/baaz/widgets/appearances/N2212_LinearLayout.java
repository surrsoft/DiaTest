package ru.surrsoft.baaz.widgets.appearances;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Обычный LinearLayout реализующий паттерн [w211w]
 * <p>
 * #version 02-04-2016  #id [[w212w]]
 */
public class N2212_LinearLayout extends LinearLayout {

    public N2208_IDrawer _drawer;

    public N2212_LinearLayout(Context context) {
        super(context);
        init();
    }

    public N2212_LinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public N2212_LinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (_drawer != null) _drawer.draw(canvas, this.getWidth(), this.getHeight());
        super.onDraw(canvas);
    }
}

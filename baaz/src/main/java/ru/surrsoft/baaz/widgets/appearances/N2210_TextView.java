package ru.surrsoft.baaz.widgets.appearances;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

/**
 * Обычный TextView реализующий draw-паттерн [w211w]
 * <p>
 * #id [[w210w]]
 */
public class N2210_TextView extends android.support.v7.widget.AppCompatTextView {

    public N2208_IDrawer _drawer;

    public N2210_TextView(Context context) {
        super(context);
        init();
    }

    public N2210_TextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public N2210_TextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(_drawer!=null) _drawer.draw(canvas, this.getWidth(), this.getHeight());
        super.onDraw(canvas);
    }
}

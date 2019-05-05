package ru.surrsoft.baaz.widgets2.nviews;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import org.apache.commons.lang3.ArrayUtils;

import ru.surrsoft.baaz.cls_a.AndrAttrs_01;
import ru.surrsoft.baaz.suite.figures.N1208_AbsDrawer;
import ru.surrsoft.baaz.univers.IEazi;
import ru.surrsoft.baaz.widgets2.BuLabel;


/**
 * #version 1 25.12.2016  #id [[w436w]]
 */
public class NRelativeLayout extends RelativeLayout implements IEazi {

    public String debugText = "";
    private N1208_AbsDrawer mDrawer;
    public BuLabel builder;
    private String mEazi;

    //==============================================================================================
    public NRelativeLayout(Context context) {
        super(context);
    }

    public NRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //``````````````````````````````````````````````````````````````````````````````````````````````

    public NRelativeLayout drawer(N1208_AbsDrawer drawer) {
        mDrawer = drawer;
        return this;
    }

    @Override
    protected void onDraw(Canvas c) {
        //Log2_01.i(TAG, "--41>[atcu]# d# onDraw()", LOG2);
        int[] drawableStates = this.getDrawableState();
        if (mDrawer != null) {
            mDrawer.drawableStates(drawableStates);
            mDrawer
                    .canvas(c)
                    .draw();
        }
        //=== техника [ddrx]
        if (ArrayUtils.contains(drawableStates, AndrAttrs_01.state_pressed)) {
            this.invalidate();
        }
        //===
        super.onDraw(c);
    }

    //touching
    //``````````````````````````````````````````````````````````````````````````````````````````````

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //Log2_01.i(TAG, "--16:41-->[atcu]# t# dispatchTouchEvent()" + "; LogInfo.st(ev)=[" + LogInfo.st(ev) + "]" + "; (debugTagString)=[" + (debugText) + "]", LOG2);
        boolean b = super.dispatchTouchEvent(ev);
        //Log2_01.d(TAG, "--16:41--[atcu]# t# dispatchTouchEvent(); b=[" + b + "]", LOG2);
        return b;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //Log2_01.i(TAG, "--16:41-->[atcu]# t# onInterceptTouchEvent()" + "; LogInfo.st(ev)=[" + LogInfo.st(ev) + "]" + "; (debugTagString)=[" + (debugText) + "]", LOG2);
        boolean b = super.onInterceptTouchEvent(ev);
        //Log2_01.d(TAG, "--16:41--[atcu]# t# onInterceptTouchEvent(); b=[" + b + "]", LOG2);
        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //Log2_01.i(TAG, "--16:41-->[atcu]# t# onTouchEvent()" + "; LogInfo.st(ev)=[" + LogInfo.st(ev) + "]" + "; (debugTagString)=[" + (debugText) + "]", LOG2);
        boolean b = super.onTouchEvent(ev);
        //Log2_01.d(TAG, "--16:41--[atcu]# t# onTouchEvent(); b=[" + b + "]", LOG2);
        return b;
    }

    //utils
    //``````````````````````````````````````````````````````````````````````````````````````````````

    /**
     * Позволяет удалить из (1) align-правило указанное в (2).
     * В настоящий момент для (2) поддерживаются только следующие правила:
     * <li> {@link RelativeLayout#CENTER_VERTICAL} </li>
     *
     * @param lp   (1) --
     * @param rule (2) -- например {@link RelativeLayout#CENTER_VERTICAL}
     */
    public static void removeRule(RelativeLayout.LayoutParams lp, int rule) {
        if (rule == RelativeLayout.CENTER_VERTICAL) {
            lp.getRules()[15] = 0;
        }
    }

    //[eazi]
    //``````````````````````````````````````````````````````````````````````````````````````````````
    @Override
    public void eazi_set(String st) {
        mEazi = st;
    }

    @Override
    public String eazi_get() {
        return mEazi;
    }
}

package ru.surrsoft.baaz.widgets2.nviews;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import ru.surrsoft.baaz.suite.figures.N1208_AbsDrawer;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;

/**
 * #version 1 24.12.2016  #id [[w430w]]
 */
public class NLinearLayout extends LinearLayout {
    public String debugTagString = "";
    private N1208_AbsDrawer mDrawer;

    private BuLayLinear_01 bu;
    private Context mContext;

    //==============================================================================================
    //constructors
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public NLinearLayout(Context context) {
        super(context);
        init(context);
    }

    public NLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    //``````````````````````````````````````````````````````````````````````````````````````````````
    private void init(Context c) {
        mContext = c;
    }

    public NLinearLayout drawer(N1208_AbsDrawer drawer) {
        mDrawer = drawer;
        return this;
    }

    @Override
    protected void onDraw(Canvas c) {
        if (mDrawer != null) {
            int[] drawableStates = this.getDrawableState();
            mDrawer.drawableStates(drawableStates);
            mDrawer
                    .canvas(c)
                    .draw();
        }
        super.onDraw(c);
    }

    public BuLayLinear_01 getBu() {
        return bu;
    }

    public void setBu(BuLayLinear_01 bu) {
        this.bu = bu;
    }

    //touching
    //``````````````````````````````````````````````````````````````````````````````````````````````

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //Log2_01.i(TAG, "--08:47-->[atcu]# t# dispatchTouchEvent()" + "; LogInfo.st(ev)=[" + LogInfo.st(ev) + "]" + "]" + "; debugTagString=[" + (debugTagString) + "]", LOG2);
        new GestureDetectorCompat(mContext, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                //Log2_01.i(TAG, "--8:50-->[atcu]# t# onDown()", LOG2);
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                //Log2_01.i(TAG, "--8:50-->[atcu]# t# onShowPress()", LOG2);

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                //Log2_01.i(TAG, "--8:50-->[atcu]# t# onSingleTapUp()", LOG2);
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                //Log2_01.i(TAG, "--8:50-->[atcu]# t# onScroll()", LOG2);
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                //Log2_01.i(TAG, "--8:50-->[atcu]# t# onLongPress()", LOG2);

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                //Log2_01.i(TAG, "--8:50-->[atcu]# t# onFling()", LOG2);
                return false;
            }
        }).onTouchEvent(ev);
        boolean b = super.dispatchTouchEvent(ev);
        //Log2_01.d(TAG, "--08:47--[atcu]# t# dispatchTouchEvent(); b = [" + b + "]", LOG2);
        return b;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //Log2_01.i(TAG, "--08:47-->[atcu]# t# onInterceptTouchEvent()" + "; LogInfo.st(ev)=[" + LogInfo.st(ev) + "]" + "; (debugTagString)=[" + (debugTagString) + "]", LOG2);
        boolean b = super.onInterceptTouchEvent(ev);
        //Log2_01.d(TAG, "--08:47--[atcu]# t# onInterceptTouchEvent(); b = [" + b + "]", LOG2);
        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //Log2_01.i(TAG, "--08:47-->[atcu]# t# onTouchEvent()" + "; LogInfo.st(ev)=[" + LogInfo.st(ev) + "]" + "; (debugTagString)=[" + (debugTagString) + "]", LOG2);
        boolean b = super.onTouchEvent(ev);
        //Log2_01.d(TAG, "--08:47--[atcu]# t# onTouchEvent(); b = [" + b + "]", LOG2);
        return b;
    }
    //``````````````````````````````````````````````````````````````````````````````````````````````
}

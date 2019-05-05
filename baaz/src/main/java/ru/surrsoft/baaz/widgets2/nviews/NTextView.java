package ru.surrsoft.baaz.widgets2.nviews;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import ru.surrsoft.baaz.suite.figures.N1208_AbsDrawer;

/**
 * #version 1 24.12.2016  #id [[w431w]]
 */
public class NTextView extends AppCompatTextView {

    /**
     * Ссылка на создавший this билдер
     */
    public Object bu;

    /**
     * См. описание техники [wawt]
     */
    private N1208_AbsDrawer mDrawer;
    private Rect mClipBounds;

    public NTextView(Context context) {
        super(context);
    }

    public NTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //==============================================================================================

    public NTextView drawer(N1208_AbsDrawer idrw) {
        mDrawer = idrw;
        return this;
    }

    public N1208_AbsDrawer getDrawer() {
        return mDrawer;
    }

    //onDraw
    //``````````````````````````````````````````````````````````````````````````````````````````````
    @Override
    protected void onDraw(Canvas c) {
        //Log2_01.i(TAG, "--41-->[atcu]# d# onDraw()", true);
        //===
        if (mDrawer != null) {
            int[] drawableStates = this.getDrawableState();
            mDrawer.drawableStates(drawableStates);
            mDrawer
                    .canvas(c)
                    .draw();
        }
        super.onDraw(c);
    }


    //``````````````````````````````````````````````````````````````````````````````````````````````
    @Override
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void setClipBounds(Rect clipBounds) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            super.setClipBounds(clipBounds);
            return;
        }

        if (clipBounds != null) {
            if (clipBounds.equals(mClipBounds)) {
                return;
            }
            if (mClipBounds == null) {
                invalidate();
                mClipBounds = new Rect(clipBounds);
            } else {
                invalidate(Math.min(mClipBounds.left, clipBounds.left),
                        Math.min(mClipBounds.top, clipBounds.top),
                        Math.max(mClipBounds.right, clipBounds.right),
                        Math.max(mClipBounds.bottom, clipBounds.bottom));
                mClipBounds.set(clipBounds);
            }
        } else {
            if (mClipBounds != null) {
                invalidate();
                mClipBounds = null;
            }
        }
    }

    @Override
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public Rect getClipBounds() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            return super.getClipBounds();
        } else {
            return (mClipBounds != null) ? new Rect(mClipBounds) : null;
        }
    }

    //touching
    //``````````````````````````````````````````````````````````````````````````````````````````````

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean b = super.dispatchTouchEvent(ev);
        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        boolean b = super.onTouchEvent(ev);
        return b;
    }

    //``````````````````````````````````````````````````````````````````````````````````````````````
}

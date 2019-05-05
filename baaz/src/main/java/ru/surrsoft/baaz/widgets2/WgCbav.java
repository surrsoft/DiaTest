
package ru.surrsoft.baaz.widgets2;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import ru.surrsoft.baaz.SomeException;

/**
 * Билдер [cbav] виджета.
 * <p>
 * [[cbav]] - виджет-слайдер. Верхний слой сдвигается (вправо или влево) открывая нижний слой.
 * <p>
 * СЦЕНАРИИ
 * <li> 1 </li>
 * <li> задать нижний слой ({@link #setLayBack(ViewGroup)}, задать верхний слой {@link
 * #setLayOver(ViewGroup)}   </li>
 * <li> задать в каком направление будет выполняться сдвиг (по-умолчанию вправо) </li>
 * <li> вызвать {@link #commit()}  </li>
 * <li> пример: https://gist.github.com/surrsoft/d209fb9cfb3213337a62e8550e36ce1b </li>
 * <p>
 * ПОНЯТИЯ
 * <li> _слойПоверх (_layOver) - </li>
 * <li> _слойЗадний (_layBack) - </li>
 * <p>
 * [zipc]
 *
 * @link http://fedepaol.github.io/blog/2014/09/01/dragging-with-viewdraghelper/
 */
public class WgCbav extends FrameLayout {

    public String debugTagString = "";
    private int mLastDragState = 0;
    private ViewDragHelper mViewDragHelper;
    private int mCurrDragPosH;
    private int mRangeH;
    private boolean mIsOpen;
    private EDirect mSlideDirect = EDirect.SLIDE_TO_RIGHT;
    private ViewGroup _layOver;
    private ViewGroup _layBack;
    private boolean mOverToOpen;
    private boolean mOverToClose;
    private boolean mCommit;
    private Presenter_x_ mPresenter;

    //==============================================================================================
    public enum EDirect {
        SLIDE_TO_RIGHT,
        SLIDE_TO_LEFT
    }

    //presenter
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public static class Presenter_x_ {
        /**
         * Вызывается в начале любого драга
         */
        public void onStartDrag() {
        }

        /**
         * Вызывается когда завершено _закрытие
         *
         * @param self --
         */
        public void onClosed(WgCbav self) {
        }

        /**
         * Вызывается когда завершено _открытие
         *
         * @param self --
         */
        public void onOpened(WgCbav self) {

        }

        /**
         * Вызывается непрерывно во время драга
         *
         * @param deviation_px (1) -- величина отклонения
         * @param range_px     (2) -- максимальный диапазон отклонения (равен всегда ширине _слояЗаднего)
         */
        public void onCurrDeviation(int deviation_px, int range_px) {
        }
    }

    //builders
    //``````````````````````````````````````````````````````````````````````````````````````````````

    public WgCbav presenter(Presenter_x_ p) {
        mPresenter = p;
        return this;
    }

    /**
     * Направление в котором должен быть сдвиг для открытия фона
     *
     * @param edir
     * @return
     */
    public WgCbav slideDirect(EDirect edir) {
        mSlideDirect = edir;
        return this;
    }

    /**
     * Если (1) true, то при драге _слояПоверх его можно будет драгать сколько угодно за пределы
     * виджетов _слояЗаднего В СТОРОНУ ОТКРЫТИЯ; при отпускании пальца _слойПоверх все равно прижмется к виджетам
     * _слояЗаднего
     *
     * @param b
     * @return
     */
    public WgCbav overToOpen(boolean b) {
        mOverToOpen = b;
        return this;
    }

    /**
     * Если (1) true, то при драге _слояПоверх его можно будет драгать сколько угодно за пределы
     * виджетов _слояЗаднего В СТОРОНУ ЗАКРЫТИЯ; при отпускании пальца _слойПоверх все равно прижмется к виджетам
     * _слояЗаднего
     *
     * @param b
     * @return
     */
    public WgCbav overToClose(boolean b) {
        mOverToClose = b;
        return this;
    }

    public WgCbav setLayBack(ViewGroup lay) {
        _layBack = lay;
        return this;
    }

    public WgCbav setLayOver(ViewGroup lay) {
        _layOver = lay;
        return this;
    }

    public ViewGroup getLayOver() {
        return _layOver;
    }

    //constructors
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public WgCbav(Context context) {
        super(context);
        init(context);
    }

    public WgCbav(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WgCbav(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    //init & commit
    //``````````````````````````````````````````````````````````````````````````````````````````````
    private void init(Context c) {
        setIsOpen(false);
        //===
    }

    public WgCbav commit() {
        mCommit = true;
        //===
        if (_layBack == null || _layOver == null)
            throw new SomeException("(debug) не задан задний и передний слои");
        //===
        this.addView(_layBack);
        this.addView(_layOver);
        //=== instantiate drag helper
        mViewDragHelper = ViewDragHelper.create(this, 1.0f, new DragHelperCallback());
        //===
        _layOver.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        //===
        return this;
    }

    //drag helper class
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public class DragHelperCallback extends ViewDragHelper.Callback {

        /**
         * write: mIsOpen; mDraggingState;
         *
         * @param state
         */
        @Override
        public void onViewDragStateChanged(int state) {
            if (state == mLastDragState) { // no change
                return;
            }
            //виджет остановил движение
            if (state == ViewDragHelper.STATE_IDLE) {
                if (mCurrDragPosH == 0) {
                    setIsOpen(false);
                } else if (mCurrDragPosH == mRangeH) {
                    setIsOpen(true);
                }
            }
            if (state == ViewDragHelper.STATE_DRAGGING) {
                if (mPresenter != null) {
                    mPresenter.onStartDrag();
                }
            }
            mLastDragState = state;
        }

        private String m18(int state) {
            switch (state) {
                case ViewDragHelper.STATE_DRAGGING:
                    return "STATE_DRAGGING";
                case ViewDragHelper.STATE_IDLE:
                    return "STATE_IDLE";
                case ViewDragHelper.STATE_SETTLING:
                    return "STATE_SETTLING";
            }
            return "";
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            mCurrDragPosH = left;
            if (mPresenter != null) {
                mPresenter.onCurrDeviation(Math.abs(left), mRangeH);
            }
        }

        /**
         * Тут мы задаем что drag должен работать только на виджете {@link #_layOver}
         *
         * @param view      (1) --
         * @param pointerId (2) --
         * @return --
         */
        @Override
        public boolean tryCaptureView(View view, int pointerId) {
            //Log2_01.i(TAG, "--> tryCaptureView(); pointerId=" + pointerId, LOG2);
            return (view == _layOver);
        }

        //drag range
        //` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` `
        @Override
        public int getViewHorizontalDragRange(View child) {
            //Log2_01.i(TAG, "--15> getViewHorizontalDragRange(); mRange=" + mRangeH, LOG2);
            return mRangeH;
        }

        //clamp position
        //` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` `
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            //Log2_01.i(TAG, "--15> clampViewPositionHorizontal(); " + "left=" + left + "; dx=" + dx, LOG2);
            if (mOverToOpen && mOverToClose) return left;
            if (mSlideDirect == EDirect.SLIDE_TO_RIGHT) {
                if (left >= 0) {
                    if (!mOverToOpen) {
                        //ограничение движения вправо
                        if (left > mRangeH) return mRangeH;
                    }
                } else {
                    if (!mOverToClose) {
                        //ограничение движения влево
                        if (left < 0) return 0;
                    }
                }
            }
            if (mSlideDirect == EDirect.SLIDE_TO_LEFT) {
                if (left < 0) {
                    if (!mOverToOpen) {
                        //ограничение движения влево
                        if (left < -1 * mRangeH) return -1 * mRangeH;
                    }
                } else {
                    if (!mOverToClose) {
                        //ограничение движения вправо
                        if (left >= 0) return 0;
                    }
                }
            }
            return left;
        }

        //` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` ` `
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            int settleDestY = 0;
            int settleDestX = m52(xvel, mRangeH, mCurrDragPosH);
            //=== оповещаем до того как анимация завершиться; иначе при прокрутке в RecyclerView
            // сигнал презентере может так и не дойти
            if (mPresenter != null) {
                if (mIsOpen && settleDestX == 0) {
                    mPresenter.onClosed(WgCbav.this);
                }
                if (!mIsOpen && settleDestX == mRangeH) {
                    mPresenter.onOpened(WgCbav.this);
                }
            }
            //===
            if (mViewDragHelper.settleCapturedViewAt(settleDestX, settleDestY)) {
                ViewCompat.postInvalidateOnAnimation(WgCbav.this);
            }
        }

        private int m52(float vel, int range, int currDragPos) {
            if (currDragPos == 0) {
                return 0;
            }
            if (Math.abs(currDragPos) == range) {
                if (currDragPos < 0) range = range * -1;
                return range;
            }
            boolean settleToOpen = false;
            if (Math.abs(currDragPos) > range / 2) {
                settleToOpen = true;
            }

            if (currDragPos < 0) range = range * -1;
            return settleToOpen ? range : 0;
        }
    }

    //others
    //``````````````````````````````````````````````````````````````````````````````````````````````
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //mRangeV = (int) (h * 0.66);
        mRangeH = _layBack.getMeasuredWidth();
        super.onSizeChanged(w, h, oldw, oldh);
    }


    @Override
    public void computeScroll() { // необходим для автоматического settling
        //Log2_01.i(TAG, "--> computeScroll()", LOG2);
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public boolean isMoving() {
        return (mLastDragState == ViewDragHelper.STATE_DRAGGING ||
                mLastDragState == ViewDragHelper.STATE_SETTLING);
    }

    @Override
    protected void onFinishInflate() {
        if (!mCommit) throw new SomeException("(debug) не вызывался commit()");
        super.onFinishInflate();
    }

    /**
     * _Открыть
     * <p>
     * Используется "костыль" с setTranslationX
     *
     * @param b (1) -- TRUE если нужно _открыть, FALSE если нужно _закрыть
     */
    public void openState(boolean b) {
        setIsOpen(b);
    }

    public void openSmooth(boolean b) {
        if (b && !mIsOpen) {
            if (mViewDragHelper.smoothSlideViewTo(_layOver, mRangeH, 0)) {
                ViewCompat.postInvalidateOnAnimation(this);
            }
        } else if (!b && mIsOpen) {
            if (mViewDragHelper.smoothSlideViewTo(_layOver, 0, 0)) {
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }
    }

    public boolean isOpen() {
        return mIsOpen;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (getChildCount() < 2) return;
        View ch1 = getChildAt(1);
        if (mIsOpen) {
            ch1.layout(ch1.getLeft() + mRangeH, ch1.getTop(), ch1.getRight() + mRangeH, ch1.getBottom());
        } else {
            ch1.layout(ch1.getLeft(), ch1.getTop(), ch1.getRight(), ch1.getBottom());
        }
    }

    private void setIsOpen(boolean b) {
        mIsOpen = b;
    }

    //touching
    //``````````````````````````````````````````````````````````````````````````````````````````````

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean b = super.dispatchTouchEvent(ev);
        return b;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean b = mViewDragHelper.shouldInterceptTouchEvent(event);
        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        mViewDragHelper.processTouchEvent(ev);
        boolean b = super.onTouchEvent(ev);
        return b;
    }


    //``````````````````````````````````````````````````````````````````````````````````````````````
}


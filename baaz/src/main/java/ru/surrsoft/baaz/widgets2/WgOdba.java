package ru.surrsoft.baaz.widgets2;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

import ru.surrsoft.baaz.cls_c.G67G_Draw;

/**
 * Контейнер для двух элементов. Первый это основной. Второй (_slideView) отображается (выезжает) по требованию.
 * <p>
 * ПОНЯТИЯ
 * <li> _slideView - элемент который выезжает </li>
 * <p>
 * <p>
 *
 */
public class WgOdba extends AbsBuView_01 {

    private final Context mContext;
    private View mBaseView;
    private View mSlideView;
    private boolean isVisible;
    private int mPadding_slideView_dp;

    //==============================================================================================
    public WgOdba(Context c) {
        mContext = c;
    }

    public WgOdba baseView(View v) {
        mBaseView = v;
        return this;
    }

    public WgOdba slideView(View v) {
        mSlideView = v;
        return this;
    }

    public WgOdba padding_slideView(int dp) {
        mPadding_slideView_dp = dp;
        return this;
    }

    //``````````````````````````````````````````````````````````````````````````````````````````````
    public void showSlideView(boolean b) {
        int dy = -1 * G67G_Draw.px(48 + 7 + mPadding_slideView_dp);
        if (b && !isVisible) {
            mSlideView.setVisibility(View.VISIBLE);
            isVisible = true;
            //===
            TranslateAnimation ta = new TranslateAnimation(0, 0, dy, 0);
            ta.setDuration(250);
            ta.setStartTime(10);
            mSlideView.startAnimation(ta);
        }
        if (!b) {
            mSlideView.setVisibility(View.GONE);
            isVisible = false;
            //===
            TranslateAnimation ta = new TranslateAnimation(0, 0, 0, dy);
            ta.setDuration(250);
            ta.setStartTime(10);
            mSlideView.startAnimation(ta);
        }
    }

    //create
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public FrameLayout build() {
        //===
        FrameLayout _fl = new FrameLayout(mContext);
        _fl.addView(mBaseView);
        _fl.addView(mSlideView);
        //===
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mSlideView.getLayoutParams();
        lp.gravity = Gravity.CENTER_HORIZONTAL;
        //===
        mSlideView.setVisibility(View.GONE);


        //===
        return _fl;
    }


}

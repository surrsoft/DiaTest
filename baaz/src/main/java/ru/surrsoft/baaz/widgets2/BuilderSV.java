package ru.surrsoft.baaz.widgets2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.univers.TwoColors;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;

/**
 * Генератор переключателей текстовых, например or/and
 * <p>
 * #version 1 12.11.2016  #id [[w393w]]
 */
public class BuilderSV {
    private static final String TAG = ":" + BuilderSV.class.getSimpleName();

    private final Context context;
    private String[] mTexts;
    private int mStartIndex;
    private TwoColors mTCNormal;
    private TwoColors mTCSelected;
    private int mCurrIndex;
    private int mPaddingsElemLR_dp = 5;
    private int mGap_dp;
    private Presenter mPresenter;
    private int mGravity;
    private int mMarginL_dp;
    private int mMarginT_dp;
    private int mMarginR_dp;
    private int mMarginB_dp;
    ArrayList<TextView> tvs;
    private int mBorderTh_dp;
    private int mBorderColor;
    private ViewGroup mParent;
    private boolean _переключатьПриНажатииНаЛюбомЭлементе;
    private Class<?> mLayParamsClass;

    /**
     * gravity относительно контейнера
     *
     * @param g (1) --
     * @return --
     */
    public BuilderSV gravitySelf(int g) {
        mGravity = g;
        return this;
    }

    public BuilderSV margins(int l_dp, int t_dp, int r_dp, int b_dp) {
        mMarginL_dp = l_dp;
        mMarginT_dp = t_dp;
        mMarginR_dp = r_dp;
        mMarginB_dp = b_dp;
        return this;
    }

    public BuilderSV border(int th_dp, int color) {
        mBorderTh_dp = th_dp;
        mBorderColor = color;
        return this;
    }

    public BuilderSV addTo(ViewGroup lay) {
        mParent = lay;
        return this;
    }

    public BuilderSV переключатьПриНажатииНаЛюбомЭлементе() {
        _переключатьПриНажатииНаЛюбомЭлементе = true;
        return this;
    }

    public BuilderSV layParamsClass(Class<?> layoutParamsClass) {
        mLayParamsClass = layoutParamsClass;
        return this;
    }

    public static abstract class Presenter {
        public void onChange(int newState) {

        }
    }

    public BuilderSV(Context context) {
        this.context = context;
    }

    public BuilderSV texts(String[] texts) {
        mTexts = texts;
        return this;
    }

    public BuilderSV startIndex(int ix) {
        mStartIndex = ix;
        mCurrIndex = ix;
        return this;
    }

    public BuilderSV tcNormal(TwoColors tc) {
        mTCNormal = tc;
        return this;
    }

    public BuilderSV tcSelected(TwoColors tc) {
        mTCSelected = tc;
        return this;
    }

    public BuilderSV presenter(Presenter p) {
        mPresenter = p;
        return this;
    }

    /**
     * Отступы слева/справа внутри отдельного элемента
     *
     * @param dp (1) --
     * @return --
     */
    public BuilderSV paddingsElemLR(int dp) {
        mPaddingsElemLR_dp = dp;
        return this;
    }

    /**
     * Зазор между элементами
     *
     * @param dp (1) --
     * @return --
     */
    public BuilderSV gap(int dp) {
        mGap_dp = dp;
        return this;
    }

    public LinearLayout create() {
        LinearLayout lay = new BuLayLinear_01(context)
                .horizontal()
                .wWC()
                .hWC()
                .gravitySelf(mGravity)
                .gravityIn(Gravity.CENTER_VERTICAL)
                .margins(mMarginL_dp, mMarginT_dp, mMarginR_dp, mMarginR_dp)
                .classLayParams(mLayParamsClass)
                .border(mBorderColor, mBorderTh_dp)
                .paddings(2)
                .build();
        //===
        if (mTCNormal == null) mTCNormal = new TwoColors(Color.BLACK, null, null, 14);
        if (mTCSelected == null) mTCSelected = new TwoColors(Color.RED, null, null, 14);
        //===

        tvs = new ArrayList<>();
        for (int i = 0; i < mTexts.length; i++) { //ЦИКЛ
            String elem = mTexts[i];
            TextView tv = new BuilderTV(context)
                    .pressed()
                    .paddings(mPaddingsElemLR_dp, 0, mPaddingsElemLR_dp, 0)
                    .gravityIn(Gravity.CENTER_HORIZONTAL)
                    .text(elem)
                    .create();
            tv.setId(i);
            mTCNormal.apply(tv);
            if (i == mStartIndex) mTCSelected.apply(tv);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int ii = v.getId();
                    if (!_переключатьПриНажатииНаЛюбомЭлементе) {
                        if (ii != mCurrIndex) {
                            m27(ii);
                        }
                    } else {
                        mCurrIndex = ++mCurrIndex >= tvs.size() ? 0 : mCurrIndex;
                        m27(mCurrIndex);
                    }
                }
            });
            lay.addView(tv);
            if (mGap_dp > 0 && i > 0) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tv.getLayoutParams();
                lp.leftMargin = G67G_Draw.px(mGap_dp);
                tv.setLayoutParams(lp);
            }
            tvs.add(tv);
        } //ЦИКЛ
        //=== выравнивание ширин
        ViewTreeObserver vto = lay.getViewTreeObserver();
        if (vto.isAlive()) {
            vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    ((Activity) context)
                            .findViewById(android.R.id.content)
                            .getViewTreeObserver()
                            .removeGlobalOnLayoutListener(this);
                    ArrayList<Integer> is = new ArrayList<>();
                    for (TextView elem : tvs) {
                        int w = elem.getWidth();
                        is.add(w);
                    }
                    Integer max = Collections.max(is);
                    for (TextView elem : tvs) {
                        if (elem.getWidth() != max) elem.setWidth(max);
                    }
                }
            });
        }
        //===
        if (mParent != null) mParent.addView(lay);
        //===
        return lay;
    }

    private void m27(int ii) {
        mCurrIndex = ii;
        for (TextView elem : tvs) {
            mTCNormal.apply(elem);
        }
        mTCSelected.apply(tvs.get(ii));
        if (mPresenter != null) mPresenter.onChange(ii);
    }

}

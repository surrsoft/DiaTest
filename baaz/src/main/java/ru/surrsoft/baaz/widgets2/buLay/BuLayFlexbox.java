package ru.surrsoft.baaz.widgets2.buLay;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.flexbox.FlexboxLayout;

import org.apache.commons.lang3.ArrayUtils;

import ru.surrsoft.baaz.widgets2.BuShape;
import ru.surrsoft.baaz.widgets2.BuilderTV;
import ru.surrsoft.baaz.widgets2.forwg.Margins_01;

/**
 * [[jcab]]
 * <p>
 * Билдер {@link FlexboxLayout}
 * <p>
 * <p>
 * ПОНЯТИЯ:
 * <li> _флекс - FlexboxLayout возвращаемый текущим билдером </li>
 * <li> _элемент - элементы располагающиеся внутри _флекс </li>
 * <li> _зазор, _gap - расстояние между соседними элементами по горизонтали </li>
 * <p>
 * ВЕРСИЯ 1 1.0 2019-02-06 (stored)
 */
public class BuLayFlexbox {
    private final Context mContext;
    private View[] mChilds = {};

    //[[dype]] - константы задающие вертикальное выравнивание
    //```````````````````````````````````````````
    public static final int DYPE_START = 1;
    public static final int DYPE_CENTER = 2;
    public static final int DYPE_END = 3;

    //[[nmix]] - константы задающие относительное размещение _элементов внутри _флекс
    //```````````````````````````````````````````
    private int mNmix = NMIX_WRAP_WRAP;
    /**
     * _Элементы размещаются мультистрочно
     */
    public static int NMIX_WRAP_WRAP = FlexboxLayout.FLEX_WRAP_WRAP;

    //```````````````````````````````````````````
    /**
     *
     */
    private ViewGroup mParent;
    /**
     * _зазор
     */
    private int mGap_dp;
    /**
     *
     */
    private Margins_01 mMargins;
    /**
     * Минимальная высота виджета
     */
    private int mHMin_dp;
    /**
     *
     */
    private int mVertAlign;

    //```````````````````````````````````````````

    //constructors
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public BuLayFlexbox(Context c) {
        mContext = c;
    }


    //builders
    //``````````````````````````````````````````````````````````````````````````````````````````````

    public BuLayFlexbox hMin(int dp) {
        mHMin_dp = dp;
        return this;
    }

    /**
     * @param vg (1) --
     * @return --
     */
    public BuLayFlexbox addTo(ViewGroup vg) {
        mParent = vg;
        return this;
    }

    /**
     * Добавление _элементов.
     * Позволяет множественный вызов
     *
     * @param v (1) --
     * @return --
     */
    public BuLayFlexbox addChild(View v) {
        if (v != null)
            mChilds = ArrayUtils.add(mChilds, v);
        return this;
    }

    /**
     * @param nmix (1) --
     * @return --
     */
    public BuLayFlexbox nmix(int nmix) {
        mNmix = nmix;
        return this;
    }

    /**
     * @param dp (1) --
     * @return --
     */
    public BuLayFlexbox gap(int dp) {
        mGap_dp = dp;
        return this;
    }

    public BuLayFlexbox margins(Margins_01 margins) {
        mMargins = margins;
        return this;
    }

    //make
    //``````````````````````````````````````````````````````````````````````````````````````````````

    public FlexboxLayout create() {
        //===
        FlexboxLayout fl = new FlexboxLayout(mContext);
        if (mChilds.length < 1) return fl;
        //---
        fl.setFlexWrap(mNmix);
        //--- _gap
        if (mGap_dp > 0) {
            fl.setShowDivider(FlexboxLayout.SHOW_DIVIDER_MIDDLE);
            fl.setDividerDrawable(new BuShape()
                    .wh(mGap_dp, 0)
                    .create()
            );
        }
        //--- vertAlign
        if (mVertAlign > 0) {
            switch (mVertAlign) {
                case DYPE_START:
                    fl.setAlignItems(FlexboxLayout.ALIGN_ITEMS_FLEX_START);
                    break;
                case DYPE_CENTER:
                    fl.setAlignItems(FlexboxLayout.ALIGN_ITEMS_CENTER);
                    break;
                case DYPE_END:
                    fl.setAlignItems(FlexboxLayout.ALIGN_ITEMS_FLEX_END);
                    break;
            }
        }
        //---
        for (View elem : mChilds) {
            fl.addView(elem);
        }
        //---
        if (mParent != null) {
            mParent.addView(fl);
        }
        //--- layout params
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) fl.getLayoutParams();
        if (lp == null) {
            lp = new FlexboxLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            fl.setLayoutParams(lp);
        }
        //--- margins
        if (mMargins != null) {
            mMargins.setFor(lp);
        }
        //---
        if (mHMin_dp > 0) {
            //минимальную высоту задаем добавлением "фейкового" виджета нулевой ширины,
            // по другому никак не получается
            new BuilderTV(mContext)
                    .addTo(fl)
                    .h(mHMin_dp)
                    .w(0)
                    .create();
        }
        //---
        return fl;
    }


    public BuLayFlexbox alignVert(int dype) {
        mVertAlign = dype;
        return this;
    }
}

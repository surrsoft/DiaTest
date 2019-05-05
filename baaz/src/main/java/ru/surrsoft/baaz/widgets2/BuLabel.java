package ru.surrsoft.baaz.widgets2;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.surrsoft.baaz.R;
import ru.surrsoft.baaz.suite.figures.N1208_AbsDrawer;
import ru.surrsoft.baaz.suite.figures.N1208_Cancel;
import ru.surrsoft.baaz.univers.FourSizes_01;
import ru.surrsoft.baaz.widgets2.buLay.BuLayRelative_01;
import ru.surrsoft.baaz.widgets2.nviews.NRelativeLayout;
import ru.surrsoft.baaz.widgets2.utils.BuTextStyle;

/**
 * Билдер виджета в виде текстовой пары ключ/значение.
 * <p>
 * Возможности:
 * <li> можно задавать фоновый рисунок для виджета в целом, с помощью метода {@link #draw(N1208_AbsDrawer)}  </li>
 * <li> можно включать отображение кнопки удаления ("крестик"), см. {@link #showCancelButton()}  </li>
 * <p>
 * По классификации [pfir] это:
 * <li> составной виджет - [tweh] </li>
 * <li> из двух текстовых частей, левой и правой - [tieb] </li>
 * <p>
 * #id [[w435w]], [[vaff]]
 *
 * ВЕРСИЯ 2 1.1 2019-02-05 (stored)
 */
public class BuLabel {
    private final Context mContext;
    private String mTextValue;
    private String mTextKey;
    private ViewGroup mParentLay;
    private boolean mIsShowCancelButton;
    private int mColor;
    private BuTextStyle mKeyTextStyle;
    private BuTextStyle mValTextStyle;
    private FourSizes_01 mFourSizes;
    private N1208_AbsDrawer mDrawer;
    private String val;
    private TextView vVal;

    //==============================================================================================
    public BuLabel(Context context) {
        mContext = context;
    }

    //``````````````````````````````````````````````````````````````````````````````````````````````
    public NRelativeLayout create() {
        //===
        TextView vBtnDel = null;
        if (mIsShowCancelButton) {
            vBtnDel = new BuilderTV(mContext)
              .wh(20)
              .drawer(new N1208_Cancel()
                .colorStroke(ColorStateList.valueOf(mColor))
                .margins(7))
              .pressViewAnimation(R.anim.n1434_anim_fadeout1)
              .create();
        }
        //===
        vVal = new BuilderTV(mContext)
          .textStyle(mValTextStyle)
          .text(mTextValue)
          .create();
        //===
        TextView vKey = new BuilderTV(mContext)
          .textStyle(mKeyTextStyle)
          .text(mTextKey)
          .create();
        //===
        NRelativeLayout lay = new BuLayRelative_01(mContext)
          .appendToLeft(vKey)
          .appendToLeft(vVal)
          .lastMarginL(5)
          .appendToLeft(vBtnDel)
          .lastMarginL(5)
          .wWC()
          .bgColor(Color.YELLOW)
          .paddings(mFourSizes)
          .drawer(mDrawer)
          .build();
        //===
        if (mParentLay != null) mParentLay.addView(lay);
        //===
        lay.builder = this;
        //===
        return lay;
    }

    public BuLabel valText(String str) {
        mTextValue = str;
        return this;
    }

    public BuLabel keyText(String str) {
        mTextKey = str;
        return this;
    }

    public BuLabel addTo(ViewGroup parentLay) {
        mParentLay = parentLay;
        return this;
    }

    public BuLabel showCancelButton() {
        mIsShowCancelButton = true;
        return this;
    }

    public BuLabel btnColor(int c) {
        mColor = c;
        return this;
    }

    public BuLabel keyTextStyle(BuTextStyle style) {
        mKeyTextStyle = style;
        //if (mValTextStyle == null) mValTextStyle = mKeyTextStyle;
        return this;
    }

    public BuLabel valTextStyle(BuTextStyle style) {
        mValTextStyle = style;
        //if (mKeyTextStyle == null) mKeyTextStyle = mValTextStyle;
        return this;
    }

    public BuLabel paddings(FourSizes_01 p) {
        mFourSizes = p;
        return this;
    }

    public BuLabel draw(N1208_AbsDrawer drawer) {
        mDrawer = drawer;
        return this;
    }

    public void setVal(String val) {
        mTextValue = val;
        vVal.setText(val);
    }
}

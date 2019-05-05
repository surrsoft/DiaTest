package ru.surrsoft.baaz.widgets2;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.Map;

import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.univers.EC;
import ru.surrsoft.baaz.univers.TwoColors;
import ru.surrsoft.baaz.widgets2.buLay.BuLayCardView_01;
import ru.surrsoft.baaz.widgets2.buLay.BuLayFrame;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;
import ru.surrsoft.baaz.widgets2.utils.BuCSL;
import ru.surrsoft.baaz.widgets2.nviews.NCardView_01;

/**
 * Билдер виджета [krkf]
 * <p>
 * [[krkf]] - виджет, представляющий собой карточку с тектовыми парами. Моделью является
 * [apmp] (HashMap=String, String=)
 * <p>
 * ПОНЯТИЯ:
 * -- _модель, _model - моделью является [apmp] (HashMap=String, String=)
 * -- _параметр, _param - пара из _ключ и _значение
 * -- _ключ, _key - левый элемент
 * -- _значение, _value - правый элемент
 * -- _картинка, _img - картинка. Располагается в правом верхнем углу
 * <p>
 * ВЕРСИЯ 1 1.0 2019-02-05 (stored)
 */
public class WgKrkf {
    private Context mContext;
    private Map<String, String> mApmp;
    private BuilderTV keyStyle;
    private BuilderTV valueStyle;
    private TextView vIcon;
    private LinearLayout layContent;
    private ArrayList<TextView> tvValues = new ArrayList<>();

    private boolean mIsBuApmp;

    private TwoColors cfgDefault_keyTC = new TwoColors(Color.BLUE, null);

    //зазор между vKey и vValue
    private int mCfgGap_dp = 5;

    //constructors
    //``````````````````````````````````````````````````````````````````````````````````````````````

    public WgKrkf(Context ctx) {
        this.mContext = ctx;
    }


    //builders
    //``````````````````````````````````````````````````````````````````````````````````````````````

    public WgKrkf model(Map<String, String> apmp) {
        mIsBuApmp = true;
        mApmp = apmp;
        return this;
    }

    /**
     * КОММЕНТАРИИ: паддинги будут перезаписаны
     * @param btv
     * @return
     */
    public WgKrkf keyStyle(BuilderTV btv) {
        this.keyStyle = btv;
        return this;
    }

    public WgKrkf valueStyle(BuilderTV btv) {
        this.valueStyle = btv;
        return this;
    }

    //create
    //``````````````````````````````````````````````````````````````````````````````````````````````

    /**
     * [[entb]] - массив из TextView отражающих _значения
     */
    public interface Back {
        void fun(ArrayList<TextView> entb);
    }

    /**
     * @return [krkf]-виджет. Структура {frame[layContent[flex[vKey, vValue]], vIcon]}
     */
    public ViewGroup create(@Nullable Back back) {
        if (!mIsBuApmp) {
            throw new SomeException("(debug) нужно задать [apmp]");
        }
        if (mApmp == null) {
            throw new SomeException("(debug)");
        }
        //---
        CardView ret = mtGetCard();

        //--- vIcon и LayContent располагаем на frameLay
        vIcon = null;
        layContent = null;
        new BuLayFrame(mContext)
                .addTo(ret)
                .addChild(new BuilderTV(mContext)
                                .wh(30)
                                .create(),
                  new BuLayFrame.Back() {
                      @Override
                      public void fun(View view) {
                          vIcon = (TextView) view;
                      }
                  }
                )
                .addChild(new BuLayLinear_01(mContext)
                                .paddings(5)
                                .build(),
                  new BuLayFrame.Back() {
                      @Override
                      public void fun(View view) {
                          layContent = (LinearLayout) view;
                      }
                  }
                )
                .create();

        //--- наполнение layContent
        for (Map.Entry<String, String> eApmp : mApmp.entrySet()) { //LOOP
            //---
            String stKey = eApmp.getKey();
            String stValue = eApmp.getValue();
            //--- vKey и vValue
            TextView vKey = mtGetVKey(stKey);
            TextView vValue = mtGetVValue(stValue);
            //---
            FlexboxLayout layFlex = new FlexboxLayout(mContext);
            layFlex.setFlexWrap(FlexboxLayout.FLEX_WRAP_WRAP);
            layFlex.addView(vKey);
            layFlex.addView(vValue);
            //---
            layContent.addView(layFlex);
            //---
            if (back != null) {
                tvValues.add(vValue);
            }
        } //LOOP

        //---
        if (back != null) {
            back.fun(tvValues);
        }
        return ret;
    }

    //private
    //``````````````````````````````````````````````````````````````````````````````````````````````

    private TextView mtGetVKey(String stKey) {
        TextView vKey;
        if (keyStyle != null) {
            vKey = keyStyle
                    .text(stKey)
                    .paddings(0, 0, mCfgGap_dp, 0)
                    .apply(new TextView(mContext));
        } else {
            vKey = new BuilderTV(mContext)
                    .text(stKey)
                    .twocolors(cfgDefault_keyTC)
                    .paddings(0, 0, mCfgGap_dp, 0)
                    .create();
        }
        return vKey;
    }

    private TextView mtGetVValue(String stValue) {
        TextView vValue;
        if (valueStyle != null) {
            vValue = valueStyle
                    .text(stValue)
                    .apply(new TextView(mContext));
        } else {
            vValue = new BuilderTV(mContext)
                    .text(stValue)
                    .paddings(5, 0, 5, 0)
                    .create();
        }
        return vValue;
    }


    private CardView mtGetCard() {
        ColorStateList csl = new BuCSL()
                .pressed(EC.MD_YELLOW_200)
                .normal(EC.MD_WHITE_1000)
                .create();
        NCardView_01 cardView = (NCardView_01) new BuLayCardView_01(mContext)
                .colorBgCSL(csl)
                .createN();
        //===
        return cardView;
    }


}

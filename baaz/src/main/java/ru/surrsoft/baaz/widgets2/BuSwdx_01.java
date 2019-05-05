package ru.surrsoft.baaz.widgets2;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.univers.Ichx;
import ru.surrsoft.baaz.univers.U;
import ru.surrsoft.baaz.widgets2.nviews.NTableLayout;
import ru.surrsoft.baaz.widgets2.nviews.NTableRow;
import ru.surrsoft.baaz.widgets2.forwg.Margins_01;

/**
 * [[swdx]] - виджет в виде таблицы. Предназначен для отображения структуры вида [ichx]
 * <p>
 * Состоит из двух столбцов. Расстояние между столбцами задается с помощью {@link #gapH(int)}.
 * Начальный внешний вид _заголовков задается через {@link #buTvTitle(BuilderTV)}. Начальный
 * внешний вид _значений задается через {@link #buTvValue(BuilderTV)}. Индивидуальный внешний вид
 * _заголовков/_значений задается через {@link #titleUdit(Udit_j2_)} / {@link #valueUdit(Udit_j2_)}
 * <p>
 * ПОНЯТИЯ:
 * <li> _заголовки, _title - столбец заголовков</li>
 * <li> _значения, _value - столбец значений</li>
 * <p>
 * КОДЫ: [zipc]
 * <p>
 * ВЕРСИЯ 1 1.0 08-12-2018 (stored)
 */
public class BuSwdx_01 {
    private static final String TAG = ":" + BuSwdx_01.class.getSimpleName();


    private final Context mContext;
    private Ichx mIchx;
    private ViewGroup mParent;
    private BuilderTV mBuTvTitle;
    private BuilderTV mBuTvValue;
    private int mGapH_px = U.px(7);
    private NTableLayout mTable;
    private Map<String, TextView> mTVsTitle = new HashMap<>();
    private Map<String, TextView> mTVsValue = new HashMap<>();
    private Udit_j2_ mTitleUdit = new Udit_j2_();
    private Udit_j2_ mValueUdit = new Udit_j2_();
    private Margins_01 mMargins;

    //==============================================================================================
    public BuSwdx_01(Context c) {
        mContext = c;
    }

    //builders
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public BuSwdx_01 ichx(Ichx ichx) {
        mIchx = ichx;
        return this;
    }

    public BuSwdx_01 margins(Margins_01 margins) {
        mMargins = margins;
        return this;
    }

    /**
     * Общее оформление для _заголовков
     *
     * @param btv (1) --
     * @return --
     */
    public BuSwdx_01 buTvTitle(BuilderTV btv) {
        mBuTvTitle = btv;
        return this;
    }

    /**
     * Задание индивидуального оформления для _заголовков
     *
     * @param udit (1) --
     * @return --
     */
    public BuSwdx_01 titleUdit(Udit_j2_ udit) {
        mTitleUdit = udit;
        return this;
    }

    /**
     * Задание индивидуального оформления для _значений
     *
     * @param udit (1) --
     * @return --
     */
    public BuSwdx_01 valueUdit(Udit_j2_ udit) {
        mValueUdit = udit;
        return this;
    }

    /**
     * Общее оформление для _значений
     *
     * @param btv (1) --
     * @return --
     */
    public BuSwdx_01 buTvValue(BuilderTV btv) {
        mBuTvValue = btv;
        return this;
    }

    public BuSwdx_01 addTo(ViewGroup p) {
        mParent = p;
        return this;
    }

    /**
     * Расстояние между столбцами
     *
     * @param dp (1) --
     * @return --
     */
    public BuSwdx_01 gapH(int dp) {
        mGapH_px = U.px(dp);
        return this;
    }

    //make
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public NTableLayout create() {
        if (mIchx == null) {
            throw new SomeException("(debug)");
        }
        //===

        mTable = new NTableLayout(mContext);

        //===
        if (mMargins != null) {
            TableLayout.LayoutParams lp = new TableLayout.LayoutParams(
              ViewGroup.LayoutParams.MATCH_PARENT,
              ViewGroup.LayoutParams.WRAP_CONTENT
            );
            mMargins.setFor(lp);
            mTable.setLayoutParams(lp);
        }

        //===
        //LOOP
        for (String ichxKey : mIchx) {

            //=== ряд
            NTableRow row = new NTableRow(mContext);

            //===
            String title = mIchx.getTitleByKey(ichxKey);
            String value = mIchx.getValueByKey(ichxKey);

            //=== title
            Gsbe_j_ gsbeTitle = mTitleUdit.get(ichxKey);
            if (gsbeTitle != null) {
                TextView tv = new TextView(mContext);
                mTVsTitle.put(ichxKey, tv);
                gsbeTitle.getBtv()
                  .addTo(row)
                  .text(title)
                  .marginR(mGapH_px)
                  .apply(tv);
            } else if (mBuTvTitle != null) {
                TextView tv = new TextView(mContext);
                mTVsTitle.put(ichxKey, tv);
                mBuTvTitle
                  .addTo(row)
                  .text(title)
                  .marginR(mGapH_px)
                  .apply(tv);
            } else {
                TextView tv = new BuilderTV(mContext)
                  .addTo(row)
                  .text(title)
                  .marginR(mGapH_px)
                  .create();
                mTVsTitle.put(ichxKey, tv);
            }

            //=== ariw
            Gsbe_j_ gsbeValue = mValueUdit.get(ichxKey);
            if (gsbeValue != null) {
                TextView tv = new TextView(mContext);
                mTVsValue.put(ichxKey, tv);
                gsbeValue.getBtv()
                  .addTo(row)
                  .text(value)
                  .marginR(mGapH_px)
                  .apply(tv);
            } else if (mBuTvValue != null) {
                TextView tv = new TextView(mContext);
                mBuTvValue
                  .addTo(row)
                  .text(value)
                  .tag(ichxKey)
                  .apply(tv);
                mTVsValue.put(ichxKey, tv);
            } else {
                TextView tv = new BuilderTV(mContext)
                  .addTo(row)
                  .text(value)
                  .tag(ichxKey)
                  .create();
                mTVsValue.put(ichxKey, tv);
            }

            //===
            mTable.addView(row);
        }
        //LOOP

        //===
        if (mParent != null) {
            mParent.addView(mTable);
        }

        //===
        return mTable;
    }

    //commands
    //``````````````````````````````````````````````````````````````````````````````````````````````

    public void _setValueByKey(String key, String value) {
        mTVsValue.get(key).setText(value);
    }

}

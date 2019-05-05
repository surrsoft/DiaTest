package ru.surrsoft.baaz.widgets2;

import android.content.Context;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apache.commons.lang3.ArrayUtils;

import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.univers.IGet;
import ru.surrsoft.baaz.univers.UniPresenter;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;
import ru.surrsoft.baaz.widgets2.nviews.NCheckBox;
import ru.surrsoft.baaz.widgets2.psmu.Psmu_c_b_v_;
import ru.surrsoft.baaz.widgets2.psmu.Psmu_e_i_;

/**
 * Набор сущностей следует передавать одним из методов elems(...).
 * Результатом работы будет массив индексов чекнутых элементов.
 * <p>
 * #version 1 20.01.2017  #id [[w455w]]
 */
public class BuPickerChoice {
    private static final String TAG = ":" + BuPickerChoice.class.getSimpleName();

    private Context mContext;
    private String[] mStrings;
    private BuilderTV mBuTvElem;
    private int mStartIndex;
    private BuilderTV mBuTvFace;
    private boolean mOnechoceMode;
    private Psmu_e_i_[] mElems;
    private IGet[] mIGets;
    private Presenter mPresenter;
    private BuilderTV[] mBtvs;

    //==============================================================================================

    //constructors
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public BuPickerChoice(Context c) {
        mContext = c;
    }

    //``````````````````````````````````````````````````````````````````````````````````````````````

    public interface Presenter {
        /**
         * @param ixs (1) -- индексы чекнутых элементов
         */
        void result(int[] ixs);
    }

    public BuPickerChoice elems(String[] sts, BuilderTV btv) {
        mStrings = sts;
        mBuTvElem = btv;
        return this;
    }

    public BuPickerChoice elems(IGet[] sts, BuilderTV btv) {
        mIGets = sts;
        mBuTvElem = btv;
        return this;
    }

    /**
     * @param btvs (1) -- текст и все нужные настройки для каждого элемента
     * @return --
     */
    public BuPickerChoice elems(BuilderTV[] btvs) {
        mBtvs = btvs;
        return this;
    }

    public BuPickerChoice elems(Psmu_e_i_[] elms) {
        mElems = elms;
        return this;
    }

    public BuPickerChoice presenter(Presenter p) {
        mPresenter = p;
        return this;
    }

    public BuPickerChoice startIndex(int ix) {
        mStartIndex = ix;
        return this;
    }

    public BuPickerChoice face(BuilderTV btv) {
        mBuTvFace = btv;
        return this;
    }

    public BuPickerChoice onchoceMode(boolean b) {
        mOnechoceMode = b;
        return this;
    }

    //create
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public TextView create() {

        if (mElems == null) {
            mElems = new Psmu_e_i_[]{};
            if (mStrings != null) {
                for (int i = 0; i < mStrings.length; i++) {
                    String elem = mStrings[i];
                    V v = new V(elem, i == mStartIndex);
                    mElems = ArrayUtils.add(mElems, v);
                }
            } else if (mIGets != null) {
                for (int i = 0; i < mIGets.length; i++) {
                    String elem = mIGets[i].n1454_getString();
                    V v = new V(elem, i == mStartIndex);
                    mElems = ArrayUtils.add(mElems, v);
                }
            } else if (mBtvs != null) {
                for (int i = 0; i < mBtvs.length; i++) {
                    BuilderTV elem = mBtvs[i];
                    V v = new V(elem, i == mStartIndex);
                    mElems = ArrayUtils.add(mElems, v);
                }
            } else {
                throw new SomeException("(debug) нужно вызвать один из методов elems(...)");
            }
        }

        //===
        Psmu_c_b_v_ psmu = new Psmu_c_b_v_(mContext)
                .elems(mElems, false)
                .oneChoiceMode(mOnechoceMode);

        //===
        TextView ret = new BuPickerView(mContext)
                .shak_i_(psmu)
                .builderTV(mBuTvFace)
                .defText("default")
                .presenterPressOk(new UniPresenter<Object>() {
                    @Override
                    public void fun(Object oj) {
                        //приходитят индексы чекнутых элементов - int[]
                        int[] ixs = (int[]) oj;
                        if (mPresenter != null) mPresenter.result(ixs);
                    }
                })
                .presenterPressCancel(new UniPresenter<Object>() {
                    @Override
                    public void fun(Object oj) {

                    }
                })
                .create();
        //===
        return ret;
    }

    //``````````````````````````````````````````````````````````````````````````````````````````````

    private class V implements Psmu_e_i_ {

        private BuilderTV mBtv;
        private String mStr;
        private boolean mCheck;
        private int mIndex;
        private Psmu_c_b_v_ mIntegrator;
        private NCheckBox mCh;

        public V(String st, boolean check) {
            mStr = st;
            mCheck = check;
        }

        public V(BuilderTV btv, boolean check) {
            mBtv = btv;
            mCheck = check;
        }

        public String getStr() {
            return mStr;
        }

        @Override
        public void setIntegrator(Psmu_c_b_v_ integrator) {
            mIntegrator = integrator;
        }

        @Override
        public View getView() {
            LinearLayout lay = new BuLayLinear_01(mContext)
                    .horizontal()
                    .build();
            //===
            mCh = new NCheckBox(mContext);
            mCh.setChecked(mCheck);
            mCh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mIntegrator.changeCheckbox(isChecked, mIndex);
                }
            });
            lay.addView(mCh);
            //===
            TextView tvx = new TextView(mContext);
            if (mBtv == null) {
                mBuTvElem
                        .text(mStr)
                        .apply(tvx);
            } else {
                mBtv.apply(tvx);
            }
            lay.addView(tvx);
            //===
            return lay;
        }

        @Override
        public int getLevel() {
            return 0;
        }

        @Override
        public boolean isTaezd() {
            return false;
        }

        @Override
        public void setIndex(int ix) {
            mIndex = ix;
        }

        @Override
        public int getIndex() {
            return mIndex;
        }

        @Override
        public void chevronToClose() {

        }

        @Override
        public void setCheck(boolean b) {
            mCheck = b;
            mCh.setCheckedSilent(b);
        }

        @Override
        public boolean isCheck() {
            return mCheck;
        }

        @Override
        public void выделить(boolean b) {

        }

        @Override
        public String getString() {
            return mStr;
        }
    }

}

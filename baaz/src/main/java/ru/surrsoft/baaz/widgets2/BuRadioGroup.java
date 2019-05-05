package ru.surrsoft.baaz.widgets2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;

/**
 * Конструктор обычной радио-группы
 * <p>
 * [zipc]
 * <p>
 * ВЕРСИЯ 2 1.1 2019-02-05 (stored)
 */
public class BuRadioGroup {

    private static final String TAG = ":" + BuRadioGroup.class.getSimpleName();

    private final Context context;
    private ArrayList<String> mItems;
    private int mStartIndex;
    private RadioGroup.OnCheckedChangeListener mPresenter;
    private ViewGroup mParent;
    private RadioGroup rgroup;
    private ArrayList<RadioButton> mRadioButtons;
    private boolean mIsSelectedCallback;

    //==============================================================================================

    public interface N1419_Model {
        int n1419_getIndex();

        void n1419_setIndex(int index);
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public RadioGroup getRadioGroup() {
        return rgroup;
    }

    public ArrayList<RadioButton> getRadioButtons() {
        return mRadioButtons;
    }

    public BuRadioGroup(Context c) {
        this.context = c;
    }

    public BuRadioGroup items(ArrayList<String> items) {
        mItems = items;
        return this;
    }

    /**
     * @param ix (1) -- стартовый ИНДЕКС (не путать с ПОЗИЦИЕЙ)
     * @return
     */
    public BuRadioGroup startIndex(int ix) {
        mStartIndex = ix;
        return this;
    }

    /**
     * Вторым параметром будет передаваться ИНДЕКС (не путать с ПОЗИЦИЕЙ)
     *
     * @param lst (1) --
     * @return --
     */
    public BuRadioGroup presenter(RadioGroup.OnCheckedChangeListener lst) {
        mPresenter = lst;
        return this;
    }

    /**
     * Вызвать если нужно чтобы обратный вызов был в том числе при нажатии на уже выбранный элемент
     *
     * @return
     */
    public BuRadioGroup isSelectedNeedCallback() {
        mIsSelectedCallback = true;
        return this;
    }

    public LinearLayout create() {
        //===
        if (mItems == null || mItems.isEmpty())
            throw new SomeException("(debug) пустой список элементов");
        //===
        LinearLayout lay = new BuLayLinear_01(context)
          .build();
        //===
        rgroup = new RadioGroup(context);
        mRadioButtons = new ArrayList<>();
        for (int i = 0; i < mItems.size(); i++) {
            String elem = mItems.get(i);
            final RadioButton rb = new RadioButton(context);
            rb.setText(elem);
            rb.setId(i);
            if (mIsSelectedCallback) {
                rb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (rb.getId() == mStartIndex) {
                            if (mPresenter != null)
                                mPresenter.onCheckedChanged(rgroup, mStartIndex);
                        }
                    }
                });
            }
            mRadioButtons.add(rb);
            rgroup.addView(rb);
        }
        rgroup.check(mStartIndex);
        rgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (mPresenter != null) mPresenter.onCheckedChanged(group, checkedId);
            }
        });
        //===
        lay.setTag(this);
        lay.addView(rgroup);
        //===
        if (mParent != null) mParent.addView(lay);
        //===
        return lay;
    }

    public BuRadioGroup addTo(ViewGroup parent) {
        mParent = parent;
        return this;
    }

}

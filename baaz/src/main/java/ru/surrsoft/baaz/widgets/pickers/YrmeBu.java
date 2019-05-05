package ru.surrsoft.baaz.widgets.pickers;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;

import ru.surrsoft.baaz.cls_c.Log2_01;
import ru.surrsoft.baaz.suite.figures.N1208_SpinnerLine;
import ru.surrsoft.baaz.tclasses.TArray_01;
import ru.surrsoft.baaz.univers.EStrings;
import ru.surrsoft.baaz.univers.FourSizes_01;
import ru.surrsoft.baaz.univers.Ret2_j_bo;
import ru.surrsoft.baaz.univers.Roih;
import ru.surrsoft.baaz.univers.U;
import ru.surrsoft.baaz.univers.UniPresenter;
import ru.surrsoft.baaz.widgets2.BuRadioGroup;
import ru.surrsoft.baaz.widgets2.BuilderTV;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;

/**
 * Билдер виджета [yrme]
 * <p>
 * [[yrme]] - виджет для [vpvt]. Представляет собой "пикер", по нажатию на который отображается
 * диалог со списком одиночного выбора и элементом [iatg].
 * <p>
 * РОЛЬ: визуализировать/редактировать _соотнесение ([sujw]).
 * <p>
 * ПОНЯТИЯ:
 * -- [[edmf]] - элемент отображающий текст и линию под ним с треугольником на конце. По нажатии на
 * этот виджет открывается диалог [vkbo]
 * -- [[vkbo]] - диалог
 * -- -- [zxct] - элемент которым заполняется диалог [vkbo] (см. {@link ZxctBu} )
 * -- -- -- [uthf] - элемент со списком одиночного выбора.
 * -- -- -- [iatg] - элемент "инверсия" ("отрицание"). Предствлен чекбоксом.
 *
 * ВЕРСИЯ 1 1.0 2019-02-05 (stored)
 */
public class YrmeBu {

    private static final String TAG = ":" + YrmeBu.class.getSimpleName();
    private static final boolean LOG2 = true;

    private final Context context;

    private String[] mItems;
    private int mIndex;
    private boolean mIsNot;
    private Presenter mPresenter;
    private BuilderTV mEdmfBu;
    private AlertDialog mAlertDialog = null;

    //cfg
    //```````````````````````````````````````````````
    private static final Typeface cfg_tf1 = Roih.get(Roih.TF_CYR_ANDIKA_R);
    private static int cfg_color1 = U.c("#566270");
    private static int cfg_textSize1 = 14;

    //constructors
    //``````````````````````````````````````````````````````````````````````````````````````````````

    public YrmeBu(Context c) {
        this.context = c;
    }

    //presenters
    //``````````````````````````````````````````````````````````````````````````````````````````````

    /**
     * РОЛЬ: давать обратный отклик на действия пользователя
     */
    public interface Presenter {
        void uthfChange(int index);

        void iatgChange(boolean isNot);
    }

    //
    //``````````````````````````````````````````````````````````````````````````````````````````````


    public YrmeBu items(String[] items) {
        mItems = items;
        return this;
    }

    public YrmeBu startIndex(int ix) {
        mIndex = ix;
        return this;
    }

    public YrmeBu isNot(boolean b) {
        mIsNot = b;
        return this;
    }

    public YrmeBu presenter(Presenter p) {
        mPresenter = p;
        return this;
    }

    //create
    //``````````````````````````````````````````````````````````````````````````````````````````````

    public TextView create() {

        mEdmfBu = new BuilderTV(context)
                .text((mIsNot ? EStrings._NOT.val() + " " : "") + mItems[mIndex])
                .pressed()
                .gravitySelf(Gravity.CENTER_VERTICAL)
                .margins(10, 0, 0, 0)
                .layParamClass(FlexboxLayout.LayoutParams.class)
                .paddings(3, 0, 8, 1)
                .textFont(cfg_tf1)
                .textColor(Color.BLACK)
                .drawer(
                        ((N1208_SpinnerLine) new N1208_SpinnerLine()
                                .colorStroke(ColorStateList.valueOf(cfg_color1))
                                .margins(new FourSizes_01(0, 0, 1, 1)))
                                .heightTreangle_dp(7)
                );
        TextView vEdmf = mEdmfBu.create();
        vEdmf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log2_01.i(TAG, "--> onClick(); нажатие на [edmf]", LOG2);
                mEdmfClick();
            }
        });
        return vEdmf;
    }

    /**
     *
     */
    private void mEdmfClick() {
        //===
        LinearLayout vZxct = new ZxctBu(context)
                .uthfModel(TArray_01.arrToList(mItems))
                .uthfStartIndex(mIndex)
                .iatgModel(mIsNot)
                .presenter(new ZxctBu.Presenter() {
                    @Override
                    public void iatgChange(boolean bNewState) {
                        Log2_01.i(TAG, "--> n2392a_isNot(); isNot=" + bNewState, LOG2);
                        mIsNot = bNewState;
                        //---
                        vEdmfUpdate();
                        //---
                        mPresenter.iatgChange(bNewState);
                    }

                    @Override
                    public void uthfChange(int iNewIndex) {
                        Log2_01.i(TAG, "--> n2392a_index(); ix=" + iNewIndex, LOG2);
                        mIndex = iNewIndex;
                        //---
                        vEdmfUpdate();
                        //---
                        if (mPresenter != null) { //link 161214154100
                            mPresenter.iatgChange(mIsNot);
                            mPresenter.uthfChange(mIndex);
                        }
                        if (mAlertDialog != null) mAlertDialog.dismiss();
                    }
                })
                .create();

        //===
        AlertDialog.Builder buDialog = new AlertDialog.Builder(context);
        buDialog.setView(vZxct);
        mAlertDialog = buDialog.create();
        mAlertDialog.show();
    }

    private void vEdmfUpdate() {
        String stNot = mIsNot ? EStrings._NOT.val() + " " : "";
        mEdmfBu._setText(stNot + mItems[mIndex]);
    }

    //``````````````````````````````````````````````````````````````````````````````````````````````

    /**
     * [[zxct]] - виджет, представляет собой список одиночного выбора и чекбокс отрицания.
     * Виджет не универсальный, для использования в [yrme] (см. {@link YrmeBu} ).
     * <p>
     * ЭЛЕМЕНТЫ:
     * -- [[uthf]] - элемент со списком одиночного выбора
     * -- [[iatg]] - элемент "инверсия" ("отрицание"). Предствлен чекбоксом.
     * <p>
     * #version 1 11.12.2016  #id [[w420w]] // rgx new
     */
    private static class ZxctBu {
        private static final String TAG = ":" + ZxctBu.class.getSimpleName();


        private final Context context;

        //builders field
        //```````````````````````````````````````````````

        private ArrayList<String> mItems = new ArrayList<>();
        private int mStartIndex;
        private boolean mIsNot;
        private Presenter mPresenter;

        //```````````````````````````````````````````````

        private ArrayList<RadioButton> mRadioButtons;


        //constructors
        //``````````````````````````````````````````````````````````````````````````````````````````````

        ZxctBu(Context c) {
            this.context = c;
        }

        //presenters
        //``````````````````````````````````````````````````````````````````````````````````````````````

        interface Presenter {
            /**
             * Отражает нажатие на чекбокс отрицания
             *
             * @param bNewState (1) --
             */
            void iatgChange(boolean bNewState);

            /**
             * Вызывается при выборе пользователем элемента в списке одиночного выбора ([uthf])
             *
             * @param iNewIndex (1) -- индекс выбранного элемента
             */
            void uthfChange(int iNewIndex);
        }

        //
        //``````````````````````````````````````````````````````````````````````````````````````````````

        ZxctBu uthfModel(ArrayList<String> items) {
            mItems = items;
            return this;
        }

        ZxctBu uthfStartIndex(int ix) {
            mStartIndex = ix;
            return this;
        }

        ZxctBu iatgModel(boolean b) {
            mIsNot = b;
            return this;
        }

        public ZxctBu presenter(Presenter p) {
            mPresenter = p;
            return this;
        }

        //create
        //``````````````````````````````````````````````````````````````````````````````````````````````

        public LinearLayout create() {
            LinearLayout lay = new BuLayLinear_01(context)
                    .paddings(16)
                    .build();

            //=== [iatg] - чекбокс "отрицание"
            new BuilderTV(context)
                    .addTo(lay)
                    .text(EStrings._ОТРИЦАНИЕ.val())
                    .textFont(cfg_tf1)
                    .fontSize(cfg_textSize1)
                    .gravitySelf(Gravity.END)
                    .isCheckbox(mIsNot, new UniPresenter<Ret2_j_bo>() {
                        @Override
                        public void fun(Ret2_j_bo oj) {
                            if (mPresenter != null) mPresenter.iatgChange(oj._boolean);
                            fillTextRadioButtons(oj._boolean);
                        }
                    })
                    .create();

            //===
            BuRadioGroup vUthfBu = new BuRadioGroup(context)
                    .addTo(lay)
                    .items(mItems)
                    .startIndex(mStartIndex)
                    .isSelectedNeedCallback()
                    .presenter(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            Log2_01.i(TAG, "--> onCheckedChanged(); 0919; checkedId=" + checkedId, LOG2);
                            if (mPresenter != null) mPresenter.uthfChange(checkedId);
                        }
                    });
            vUthfBu.create();
            mRadioButtons = vUthfBu.getRadioButtons();

            //===
            fillTextRadioButtons(mIsNot);

            //===
            return lay;
        }

        private void fillTextRadioButtons(boolean isNot) {
            String s = isNot ? EStrings._NOT.val() + " " : "";
            for (int i = 0; i < mRadioButtons.size(); i++) {
                RadioButton elem = mRadioButtons.get(i);
                elem.setText(s + mItems.get(i));
            }
        }

    }
}

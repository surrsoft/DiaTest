package ru.surrsoft.baaz.widgets2.psmu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.apache.commons.lang3.ArrayUtils;

import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.tclasses.TArray_01;
import ru.surrsoft.baaz.univers.BuString;
import ru.surrsoft.baaz.univers.IShak;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;

/**
 * Интегратор виджетов. Организует многоуровневый список. Элементы должны реализовывать интерфейс
 * {@link Psmu_e_i_} - обладать свойством "уровень" и т.д.
 * <p>
 * Принимает на вход список моделей через {@link #elems(Psmu_e_i_[], boolean)}.
 * Передает себя каждому элементу с помощью {@link Psmu_e_i_#setIntegrator(Psmu_c_b_v_)} чтобы
 * элементы сообщали об изменении своего чекбокса и шеврона
 * <p>
 * //
 */

public class Psmu_c_b_v_ implements IShak {
    private static final String TAG = ":" + Psmu_c_b_v_.class.getSimpleName();

  private final Context mContext;
    private View[] mViews;
    private boolean mOneChoiceMode;
    private Psmu_e_i_[] mElemsOriginal;
    private Psmu_e_i_[] mElems;
    private boolean mCloneMode;


    //==============================================================================================

    public Psmu_c_b_v_(Context c) {
        mContext = c;
    }

    //builder part
    //``````````````````````````````````````````````````````````````````````````````````````````````

    /**
     * @param elems
     * @param cloneMode (2) -- при включенном режиме клонирования все изменения на интерфейсе вступают
     *                  в силу только после вызова {@link #n1453_applyModelChanges()}. Если изменения
     *                  на интерфейсе стали не нужны то следует вызывать {@link #n1453_resetModelChanges()}
     * @return
     */
    public Psmu_c_b_v_ elems(Psmu_e_i_[] elems, boolean cloneMode) {
        mCloneMode = cloneMode;
        mElemsOriginal = elems;
        //=
        if (!cloneMode) {
            mElems = elems;
        } else {
            mElems = new Psmu_e_i_[elems.length];
            TArray_01.cloneArr(elems, mElems);
        }
        return this;
    }

    public Psmu_c_b_v_ oneChoiceMode(boolean b) {
        mOneChoiceMode = b;
        return this;
    }

    //commands
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public void changeChevron(boolean b, int ix) {
        if (!b) отобразитьДетей(ix);
        else скрытьПотомков(ix);
    }

    public void changeCheckbox(boolean b, int ix) {
        if (b && mOneChoiceMode) чекнутьТекущийИСнятьЧекСОстальных(ix);
        отработатьВыделение();
    }

    //create
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public LinearLayout create() {
        return n1453_getView();
    }

    @Override
    public LinearLayout n1453_getView() {
        LinearLayout lay = new BuLayLinear_01(mContext).build();
        if (mElems == null) throw new SomeException("(debug)");
        if (mElems.length == 0) return lay;
        mViews = new View[]{};
        for (int i = 0; i < mElems.length; i++) { //ЦИКЛ
            Psmu_e_i_ elem = mElems[i];
            View vView = elem.getView();
            lay.addView(vView);
            mViews = ArrayUtils.add(mViews, vView);
            //=== передача себя
            elem.setIntegrator(this);
            //=== передача индекса
            elem.setIndex(i);
            //=== отступ для уровней
            ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) vView.getLayoutParams();
            if (elem.getLevel() > 0) {
                int l = elem.getLevel() * 10;
                if (!elem.isTaezd()) l = l + 15;
                lp.leftMargin = G67G_Draw.px(l);
                vView.setLayoutParams(lp);
            }
            //===
            if (elem.getLevel() > 0) vView.setVisibility(View.GONE);
        } //ЦИКЛ
        отработатьВыделение();
        return lay;
    }

    @Override
    public String n1453_getResultForUI(String defaultText) {
        BuString bst = new BuString().divider(", ");
        for (Psmu_e_i_ elem : mElems) {
            if (elem.isCheck()) {
                bst.append(elem.getString());
            }
        }
        String s = bst.toString();
        return s.length() > 0 ? s : defaultText;
    }

    /**
     * Возвращает индексы выбранных элементов
     *
     * @return
     */
    @Override
    public Object n1453_getResultSpecial() {
        int[] ixs = {};
        for (Psmu_e_i_ mElem : mElems) {
            if (mElem.isCheck()) ixs = ArrayUtils.add(ixs, mElem.getIndex());
        }
        return ixs;
    }

    @Override
    public void n1453_applyModelChanges() {
        if (mCloneMode) TArray_01.cloneArr(mElems, mElemsOriginal);
    }

    @Override
    public void n1453_resetModelChanges() {
        if (mCloneMode) TArray_01.cloneArr(mElemsOriginal, mElems);
    }

    //private zone
    //``````````````````````````````````````````````````````````````````````````````````````````````
    private void отработатьВыделение() {
        for (int i = 0; i < mElems.length; i++) {
            Psmu_e_i_ elem = mElems[i];
            //[170113175200]
            elem.выделить(false);
            boolean чекнут = elem.isCheck();
            if (чекнут) {
                int[] arr26 = получитьИндексыПредков(i);
                for (int elem2 : arr26) {
                    mElems[elem2].выделить(true);
                }
            }
        }
    }


    private int[] получитьИндексыПредков(int ix) {
        int[] ret = new int[]{};
        int lvl = mElems[ix].getLevel();
        if (lvl < 1) return ret;
        int lastLvl = lvl;
        for (int i = ix - 1; i >= 0; i--) { //ЦИКЛ
            Psmu_e_i_ elem = mElems[i];
            if (elem.getLevel() < lastLvl) {
                lastLvl = elem.getLevel();
                ret = ArrayUtils.add(ret, i);
                if (lastLvl < 1) break;
            }
        } //ЦИКЛ
        return ret;
    }

    /**
     * @param ix (1) -- индекс элемента на котором был выполнен чек
     */
    private void чекнутьТекущийИСнятьЧекСОстальных(int ix) {
        mElems[ix].setCheck(true);
        for (int i = 0; i < mElems.length; i++) {
            if (i != ix) {
                mElems[i].setCheck(false);
            }
        }
    }

    private void отобразитьДетей(int ix) {
        int уровеньНаКоторомНажато = mElems[ix].getLevel();
        for (int i = 0; i < mElems.length; i++) {
            if (i > ix) {
                if (mElems[i].getLevel() == уровеньНаКоторомНажато + 1) {
                    mViews[i].setVisibility(View.VISIBLE);
                }
                if (mElems[i].getLevel() <= уровеньНаКоторомНажато) {
                    break;
                }
            }
        }
    }

    private void скрытьПотомков(int ix) {
        int уровеньНаКоторомНажато = mElems[ix].getLevel();
        for (int i = 0; i < mElems.length; i++) {
            Psmu_e_i_ elem = mElems[i];
            if (i > ix) {
                if (elem.getLevel() > уровеньНаКоторомНажато) {
                    if (elem.isTaezd()) {
                        //сигнал на установку шеврона в закрытое состояние
                        elem.chevronToClose();
                    }
                    mViews[i].setVisibility(View.GONE);
                } else break;
            }
        }
    }
}

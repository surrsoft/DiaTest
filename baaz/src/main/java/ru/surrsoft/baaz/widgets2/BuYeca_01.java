package ru.surrsoft.baaz.widgets2;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

import ru.surrsoft.baaz.suite.figures.N1208_Chevron;
import ru.surrsoft.baaz.univers.Jmox;
import ru.surrsoft.baaz.univers.Prm;
import ru.surrsoft.baaz.univers.Gravity_01;
import ru.surrsoft.baaz.widgets2.nviews.NTextView;
import ru.surrsoft.baaz.widgets2.forwg.Margins_01;

/**
 * [[yeca]]
 * <p>
 * Виджет бинарный-переключать с анимированным изображением "шеврона" ([suzz]).
 * Обычно используется в элементах вида [iopr]
 * <p>
 * КОДЫ: [zipc], [ksao], [dzah]-(кнопки играющие роль "шеврона");
 * <p>
 * ВЕРСИЯ 1 1.0 2019-02-06 (stored)
 */
public class BuYeca_01 {
    private Context mContext;
    /**
     *
     */
    private Vs19 mPresenter;
    /**
     * Начальное состояние: TRUE - шеврон в виде V, FALSE - наоборот
     */
    private boolean mStartState = true;
    /**
     * Должен возвращать TRUE если дозволителен переход в следующее состояние
     */
    private Jmox mJmox;
    /**
     *
     */
    private int mW_dp = 48;
    /**
     *
     */
    private int mH_dp = 48;
    /**
     *
     */
    private BuDerb_01 mBuChevron;
    /**
     *
     */
    private Gravity_01 mGravitySelf;
    /**
     *
     */
    private Margins_01 mMargins;

    //constructors
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public BuYeca_01(Context c) {
        this.mContext = c;
    }

    //builders
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public BuYeca_01 buPresenter(Vs19 p) {
        mPresenter = p;
        return this;
    }

    public BuYeca_01 buStartState(boolean b) {
        mStartState = b;
        return this;
    }

    public BuYeca_01 buJmox(Jmox jmox) {
        mJmox = jmox;
        return this;
    }

    public BuYeca_01 buWH(int w_dp, int h_dp) {
        mW_dp = w_dp;
        mH_dp = h_dp;
        return this;
    }

    public BuYeca_01 buWH(int dp) {
        mW_dp = dp;
        mH_dp = dp;
        return this;
    }

    public BuYeca_01 buGravitySelf(Gravity_01 gravity) {
        this.mGravitySelf = gravity;
        return this;
    }

    public BuYeca_01 buMargins(Margins_01 margins) {
        this.mMargins = margins;
        return this;
    }


    //commands
    //``````````````````````````````````````````````````````````````````````````````````````````````

    /**
     * Программный перевод в следующее состояние
     */
    public void _toggle() {
        mBuChevron._toggleState();
    }


    //make
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public NTextView create() {
        Interpolator interp = new AccelerateDecelerateInterpolator();
        int iDuration = 300;
        //---
        N1208_Chevron drawerTrue = ((N1208_Chevron) new N1208_Chevron()
                .animMode(true)
                .animDuration(iDuration)
                .animPlay(true)
                .animToEndOnStart(true)
                .animInterpolator(interp)
                //.margins(new FourSizes(5, 5, 5, 5))
                .colorStroke(ColorStateList.valueOf(Color.BLACK))
                .rotateCenterAngle(180))
                .fillDraw(true)
                ._animRotate(180, Prm.поЧасовой(true));
        //---
        N1208_Chevron drawerFalse = ((N1208_Chevron) new N1208_Chevron()
                .animMode(true)
                .animDuration(iDuration)
                .animPlay(true)
                .animInterpolator(interp)
                //.margins(new FourSizes(5, 5, 5, 5))
                .colorStroke(ColorStateList.valueOf(Color.RED))
                .rotateCenterAngle(0))
                .fillDraw(true)
                ._animRotate(180, Prm.поЧасовой(true));
        //---
        mBuChevron = (BuDerb_01) new BuDerb_01(mContext)
                .drawerTrue(drawerTrue)
                .drawerFalse(drawerFalse)
                .startState(mStartState)
                .presenter(mPresenter)
                .jmox(mJmox)
                .gravityOut(mGravitySelf)
                .margins(mMargins)
                .w(mW_dp)
                .h(mH_dp);
        //---
        return mBuChevron.build();
    }

    /**
     * По отношению к BuDerb.Presenter добавлен функционал задания "тега".
     * Одно из назначений этого тега, это задать сущность через которую затем можно узнавать на каком
     * элементе интерфейса (виджете) было нажатие
     */
    public static abstract class Vs19<T> implements BuDerb_01.Presenter {
        private T mTag;

        @Override
        public void n1441_onChangeState(boolean newState) {

        }

        public Vs19 tagSet(T tag) {
            mTag = tag;
            return this;
        }

        public T tagGet() {
            return mTag;
        }

    }

}

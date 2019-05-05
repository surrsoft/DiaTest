package ru.surrsoft.baaz.widgets2;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import ru.surrsoft.baaz.suite.figures.N1208_Circle;
import ru.surrsoft.baaz.univers.IDebugString;
import ru.surrsoft.baaz.univers.Roih;
import ru.surrsoft.baaz.univers.Trans;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;
import ru.surrsoft.baaz.widgets2.utils.BuTextStyle;


/**
 * Сущность из двух виджетов. Верхний отображает текст в круге, нижний отображает также текст (подпись).
 * <p>
 * Круг _cir можно превратить в анимированный-круг-с-разрывом с помощью метода
 * {@link #_startAnimCircle(boolean)}
 * <p>
 * ПОНЯТИЯ:
 * <li> [[_wto]] или _верхний-виджет </li>
 * <li> [[_wvn]] или _нижний виджет </li>
 * <li> [[_cir]] или _круг - drawer _верхнего-виджета </li>
 * <p>
 * [zipc]
 */
public class Sieb_v_b_ implements IDebugString {
    private static final String TAG = ":" + Sieb_v_b_.class.getSimpleName();


    private Context mContext;
    private BuTextStyle mTextStyleUp;
    private BuTextStyle mTextStyleDown;
    private String mTextUp;
    private String mTextDown;
    private int mGrandColor;
    private Trans<TextView> mTrans;
    private N1208_Circle mDrawerCircleAnim;
    private boolean mApplyAnim;
    private N1208_Circle mDrawerCircle;
    private BuilderTV _wtoBu;
    public TextView _wto;
    public TextView _wvn;
    private int mTextDownMinWidth_dp;
    private String mDebugString;
    private long mAnimDuration = 3000;
    private Interpolator mAnimInterpolator = new LinearInterpolator();

    //==============================================================================================
    //constructors
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public Sieb_v_b_(Context c) {
        mContext = c;
    }

    //builders
    //``````````````````````````````````````````````````````````````````````````````````````````````

    public Sieb_v_b_ animInterpolator(Interpolator i ){
        mAnimInterpolator = i ;
        return this;
    }

    public Sieb_v_b_ animDuration(long duration){
        mAnimDuration = duration;
        return this;
    }

    public Sieb_v_b_ textDownMinWidth(int dp) {
        mTextDownMinWidth_dp = dp;
        return this;
    }

    public Sieb_v_b_ textUp(String st) {
        mTextUp = st;
        return this;
    }

    public Sieb_v_b_ textDown(String st) {
        mTextDown = st;
        return this;
    }

    /**
     * Указаным здесь текстом будет окрашен и _круг и текст _вехнего-виджета и _нижнего-виджета
     *
     * @param clr (1) --
     * @return --
     */
    public Sieb_v_b_ grandColor(int clr) {
        mGrandColor = clr;
        return this;
    }

    /**
     * Записывает в (1) ссылку на _верхний-виджет
     *
     * @param trans (1) --
     * @return --
     */
    public Sieb_v_b_ linkUpView(Trans<TextView> trans) {
        mTrans = trans;
        return this;
    }


    //create
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public LinearLayout create() {
        LinearLayout lay = new BuLayLinear_01(mContext)
                .wWC()
                .build();
        //=== [_wto] _верхний-виджет
        mDrawerCircle = ((N1208_Circle) new N1208_Circle()
                .colorStroke(ColorStateList.valueOf(mGrandColor))
                .margins(1));

        mDrawerCircleAnim = ((N1208_Circle) new N1208_Circle()
                .gap(15, 270)
                .colorStroke(ColorStateList.valueOf(mGrandColor))
                .margins(1)
                //anim
                .animMode(true)
                .animDuration(mAnimDuration)
                .animInterpolator(mAnimInterpolator))
                ._animInfinityRotate((AppCompatActivity) mContext);
        mDrawerCircleAnim.n1475_setDebugString(mDebugString);

        _wtoBu = new BuilderTV(mContext)
                .addTo(lay)
                .linkView3(mTrans)
                .drawer(mDrawerCircle)
                .text(mTextUp)
                .paddings(2, 0, 2, 0)
                .wh(48)
                .gravityIn(Gravity.CENTER)
                .gravitySelf(Gravity.CENTER_HORIZONTAL)
                .textStyle(new BuTextStyle()
                        .buTextColor(mGrandColor)
                        .buTextSize(13)
                        .buTextFace(Roih.get(Roih.TF_CYR_ROBOTO_CONDENSED))
                )
                .ecudComment("sieb_1656");
        _wto = _wtoBu.create();

        //=== [_wvn] _нижний-виджет
        _wvn = new BuilderTV(mContext)
                .addTo(lay)
                .text(mTextDown)
                .wMin(mTextDownMinWidth_dp)
                .margins(2, 0, 2, 0)
                .gravitySelf(Gravity.CENTER)
                .gravityIn(Gravity.CENTER_HORIZONTAL)
                .textStyle(new BuTextStyle()
                        .buTextColor(mGrandColor)
                        .buTextSize(10)
                        .buTextFace(Roih.get(Roih.TF_CYR_ROBOTO_LIGHT))
                )
                .ecudComment("sieb_1657")
                .create();

        return lay;
    }

    //commands
    //``````````````````````````````````````````````````````````````````````````````````````````````

    public void _startAnimCircle(boolean startAnim) {
        if (startAnim) {
            _wtoBu.drawer(mDrawerCircleAnim);
            mDrawerCircleAnim.animStart();
        } else {
            mDrawerCircleAnim.animCancel();
            _wtoBu.drawer(mDrawerCircle);
        }
    }

    /**
     * Задание текста для _верхнего-виджета
     *
     * @param st (1) --
     */
    public void _setTextUp(String st) {
        _wto.setText(st);
    }

    /**
     * Задание текста для _нижнего-виджета
     *
     * @param st (1) --
     */
    public void _setTextDown(String st) {
        _wvn.setText(st);
    }

    //IDebugString
    //``````````````````````````````````````````````````````````````````````````````````````````````

    @Override
    public void n1475_setDebugString(String st) {
        mDebugString = st;
    }

    @Override
    public String n1475_getDebugString() {
        return mDebugString;
    }
}

package ru.surrsoft.baaz.widgets2.utils.anim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.univers.Color2;

/**
 * Класс-фабрика.
 * <p>
 * Конструирует сущность [[sreu]] выполняющую анимацию виджета, заданного через {@link #target(View)}.
 * [sreu] привязывается к ЖЦ активити, синхронизируя старт/стоп анимации.
 * Для бесконечной анимации следует использовать {@link #infinity(boolean)}
 * <p>
 * Методы вида create... создают внутренний объект анимации и возвращают ссылку на [sreu], на
 * котором следует вызывать методы интерфейса {@link IKtub}, запускающие/останавливающие анимацию.
 * Анимация не считает остановленной если это сделано в результате onStop() активити; т.е.
 * фактически в фоне анимация не выполняется, но при onStart() активити анимация будет вновь запущена.
 * <p>
 * В настоящее время доступны анимации:
 * <li> {@link #create_FadeInOut_Alpha(float, float)} - пульсирующая анимация (за счет изменения alpha)  </li>
 * <p>
 * РУКОВОДСТВО ПО ФАБЛИЧНЫМ МЕТОДАМ
 * <li> даже если анимация состоит из одного элемента, следует всегда добавлять этот элемент в набор
 * {@link #mAnimSet}  </li>
 * <p>
 * ДЛЯ ПОИСКА: [animation], [анимация]
 * //tikub
 */
public class BuSreu implements IKtub {
    private static final String TAG = ":" + BuSreu.class.getSimpleName();


    private long mDuration = 2000;
    private View mTargetView;
    private AnimatorSet mAnimSet;
    private boolean mIsStarted;
    private boolean mIsInfinity;

    //==============================================================================================

    //constructors
    //``````````````````````````````````````````````````````````````````````````````````````````````

    public BuSreu(AppCompatActivity acty) {
        //=== привязка к ЖЦ активити
        MFragment mFragment = new MFragment();
        mFragment.buAnim = this;
        acty
                .getSupportFragmentManager()
                .beginTransaction()
                .add(mFragment, "buAnimation")
                .commit();
        //===
        mAnimSet = new AnimatorSet();
    }

    //builders
    //``````````````````````````````````````````````````````````````````````````````````````````````

    /**
     * Виджет который будет анимироваться
     *
     * @param v (1) --
     * @return --
     */
    public BuSreu target(View v) {
        mTargetView = v;
        return this;
    }

    /**
     * @param b (1) -- TRUE для бесконечной анимации
     * @return --
     */
    public BuSreu infinity(boolean b) {
        mIsInfinity = b;
        return this;
    }

    /**
     * @param duration (1) -- продолжительность анимации в милисекундах
     * @return --
     */
    public BuSreu duration(long duration) {
        mDuration = duration;
        return this;
    }

    //create
    //``````````````````````````````````````````````````````````````````````````````````````````````

    /**
     * Пульсирующая анимация alpha всего виджета целиком
     * (анимируется виджет целиком, т.е. вместе со всеми рамками и т.п.)
     *
     * @param startAlpha (1) -- 0..1f, например "1f"
     * @param endAlpha   (2) -- 0..1f, например "0f"
     * @return --
     */
    public BuSreu create_FadeInOut_Alpha(float startAlpha, float endAlpha) {
        типоваяПроверка();
        //===
        ObjectAnimator a = ObjectAnimator.ofFloat(this.mTargetView, View.ALPHA, startAlpha, endAlpha, startAlpha);
        a.setDuration(mDuration);
        if (mIsInfinity) {
            a.setRepeatMode(ValueAnimator.RESTART);
            a.setRepeatCount(ValueAnimator.INFINITE);
        }
        //===
        mAnimSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                mAnimSet.end();
            }
        });
        //==
        mAnimSet.play(a);
        //===
        return this;
    }

    /**
     * Пульсирующая анимация alpha текста виджета. Виджет должен быть потомком {@link TextView}
     *
     * @param endAlpha (1) -- относительная alpha в нижней точке анимации, 0.0 ... 1.0f
     * @param color    (2) -- базовый цвет alpha которого будет меняться; если NULL то
     *                 используется текущий (дефолтный, normal) цвет текста виджета
     * @return --
     */
    public BuSreu create_FadeInOut_TextColorAlpha(float endAlpha, Color2 color) {
        типоваяПроверка();
        //=== прочие проверки
        if (!(mTargetView instanceof TextView)) {
            throw new SomeException("(debug) target виджет должен быть помомком TextView");
        }
        //===
        int c1 = color != null ? color.val : ((TextView) mTargetView).getTextColors().getDefaultColor();
        //===
        int c2 = Color.argb((int) (endAlpha * 255), Color.red(c1), Color.green(c1), Color.blue(c1));
        //===
        ObjectAnimator a = ObjectAnimator.ofInt(this.mTargetView, "textColor", c1, c2, c1);
        //обязательная инструкция при анимации цветов
        a.setEvaluator(new ArgbEvaluator());
        a.setDuration(mDuration);
        if (mIsInfinity) {
            a.setRepeatMode(ValueAnimator.RESTART);
            a.setRepeatCount(ValueAnimator.INFINITE);
        }
        //===
        mAnimSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                mAnimSet.end();
            }
        });
        //==
        mAnimSet.play(a);
        //===
        return this;
    }

    //commands
    //``````````````````````````````````````````````````````````````````````````````````````````````

    private Animator _getAnim() {
        return mAnimSet;
    }

    //фрагмент без UI, для пристыковки к ЖЦ активити
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public static class MFragment extends Fragment {
        public BuSreu buAnim;
        //=====================================================================

        @Override
        public void onStart() {
            if (buAnim.iktub_isStarted()) {
                buAnim._getAnim().start();
            }
            super.onStart();
        }

        @Override
        public void onStop() {
            buAnim._getAnim().cancel();
            super.onStop();
        }
    }


    //IKtub
    //``````````````````````````````````````````````````````````````````````````````````````````````
    @Override
    public void iktub_start() {
        mIsStarted = true;
        mAnimSet.start();
    }

    @Override
    public void iktub_stop() {
        mIsStarted = false;
        mAnimSet.cancel();
    }

    @Override
    public boolean iktub_isStarted() {
        return mIsStarted;
    }


    //private
    //``````````````````````````````````````````````````````````````````````````````````````````````
    private void типоваяПроверка() {
        if (mTargetView == null) {
            throw new SomeException("(debug) задайте target view");
        }
    }

}

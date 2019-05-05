package ru.surrsoft.baaz.widgets2.lvl2;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import ru.surrsoft.baaz.suite.figures.DrawerComposer;
import ru.surrsoft.baaz.suite.figures.N1208_AbsDrawer;
import ru.surrsoft.baaz.suite.figures.N1208_Circle;
import ru.surrsoft.baaz.suite.figures.N1208_Play;
import ru.surrsoft.baaz.suite.figures.N1208_Stop;
import ru.surrsoft.baaz.univers.EC;
import ru.surrsoft.baaz.univers.U;
import ru.surrsoft.baaz.widgets2.BuilderTV;
import ru.surrsoft.baaz.widgets2.events.EvString;
import ru.surrsoft.baaz.widgets2.nviews.NTextView;
import ru.surrsoft.baaz.widgets2.utils.BuCSL;

import static ru.surrsoft.baaz.widgets2.lvl2.BuXdso_v_.EStates.STATE_1;
import static ru.surrsoft.baaz.widgets2.lvl2.BuXdso_v_.EStates.STATE_2;
import static ru.surrsoft.baaz.widgets2.lvl2.BuXdso_v_.EStates.STATE_3;

/**
 * Виджет запуска какого-либо сетевого действия.
 * <p>
 * ИСПОЛЬЗОВАНИЕ
 * <li> ВАЖНО: создавать следует методом {@link #createXdsoView()}, а не {@link #create()}   </li>
 * <li> {@link #onclick(View.OnClickListener)} НЕ ИСПОЛЬЗОВАТЬ. Вместо этого использовать presenter </li>
 * <p>
 * СОСТОЯНИЯ
 * <p>
 * <li> STATE_1: перед первым нажатием выглядит как круг со стрелкой вправо в центре </li>
 * <li> STATE_2: отображается вращающийся-круг-с-разрывом с квадратом в центре </li>
 * <li> STATE_3: помимо вращающегося-круга-с-разывом с квадратом в центре, отображается еще заполняющийся-круг </li>
 * <p>
 * ТЕХНИКА [moru]
 * <li> излучает {@link EvString} с константами вида "EV_STATE_..."  </li>
 * // rgx new
 * <p>
 * https://docs.google.com/document/d/175JwbM45Bcqou5oWRhgzPGDzpwxjbZqoT2KgNrr-5RQ/edit?usp=sharing
 */
public class BuXdso_v_ extends BuilderTV {
    private static final int START_ANGLE_STATE_3 = 270;

    /**
     * Сопровровождается значением boolean. TRUE означает что текущий виджет "занят"; как правило
     * в ответ на это событие другие виджеты должны переходить в состояние disabled
     */
    public static final long EVTYPE_BUSY_TRUEFALSE = 170217084200L;

    //=== техника [moru]
    /**
     * Означает что виджет перешел в состояние 1
     */
    public static final String EV_STATE_1 = "xdso_state_1";
    /**
     * Означает что предпринята попытка перехода из состояния 1 в состояние 2. Виджет еще
     * не в состоянии 2
     */
    public static final String EV_STATE_1A = "xdso_state_1A";
    /**
     * Означает что виджет перешел в состояние 2
     */
    public static final String EV_STATE_2 = "xdso_state_2";
    /**
     * Означает что предпринята попытка перевести виджет в состояние 1. Виджет еще не в состоянии 1
     */
    public static final String EV_STATE_2A = "xdso_state_2A";
    /**
     * Означает что виджет перешел в состояние 3
     */
    public static final String EV_STATE_3 = "xdso_state_3";

    //===
    /**
     * Необходимо для прикрепления к его жизненному циклу, для остановки бесконечной анимации
     */
    private final AppCompatActivity mActy;
    private final Fragm mFragm;

    private EStates mCurrState;
    private N1208_AbsDrawer mState1Drw;
    private N1208_AbsDrawer mState2Drw;
    private NTextView _tv;
    private Presenter_x_ mPresenter;
    private N1208_Circle mDrwCircleState3;
    private N1208_Circle mDrwCircleAnim;
    private DrawerComposer mState3Drw;
    private boolean mEnable;


    //``````````````````````````````````````````````````````````````````````````````````````````````
    enum EStates {
        /**
         * Состояние первоначальное, отображается круг и внутри него треугольник
         */
        STATE_1,
        /**
         * Состояние 2, отобржается треугольник, и вокруг него вращается круг
         */
        STATE_2,
        /**
         * Состояние 3, тоже что и состояние 2, только отбражается еще третий круг постепенно
         * заполняющийся
         */
        STATE_3
    }


    //callbacks
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public static class Presenter_x_ {
        /**
         * Вызывается при переходе в {@link EStates#STATE_2}
         *
         * @param self
         */
        public void onStart(BuXdso_v_ self) {

        }

        /**
         * Вызывается при принудительной остановке пользователем
         *
         * @param self
         */
        public void onStop(BuXdso_v_ self) {

        }

        /**
         * Вызывается когда процесс завершился естественным путем (круг достиг 100%)
         *
         * @param self
         */
        public void onEnd(BuXdso_v_ self) {

        }

        /**
         * Вернув FALSE, реализущий может запретить переход в другие состояния при нажатии. Например может
         * отсутствовать интернет-подключние
         *
         * @return --
         */
        public boolean onBeforeStart() {
            return true;
        }

        /**
         * Если на остановку может потребоваться некоторое время, то реализующий должен вернуть
         * здесь FALSE, и затем самостоятельно перевести виджет в состояние 1 методом {@link #_toState1()}
         *
         * @return --
         */
        public boolean onBeforeStop() {
            return true;
        }
    }

    public interface Get {
        void fun(TextView vXdso, BuXdso_v_ buXdso, N1208_Circle drwCircleAnim);
    }

    public static class Fragm extends Fragment {
        public N1208_Circle animCircle;

        @Override
        public void onStop() {
//            if (animCircle != null) {
//                animCircle.animCancel();
//            }
            super.onStop();
        }
    }

    //constructors
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public BuXdso_v_(Context context, AppCompatActivity acty) {
        super(context);
        mActy = acty;
        //===
        mFragm = new Fragm();
        acty
                .getSupportFragmentManager()
                .beginTransaction()
                .add(mFragm, "")
                .commit();
    }

    //make
    //``````````````````````````````````````````````````````````````````````````````````````````````

    public TextView createXdsoView(Get get) {
        TextView view = createXdsoView();
        get.fun(view, this, mDrwCircleAnim);
        return view;
    }

    public TextView createXdsoView() {

        //=== drawer-1
        ColorStateList color01_x4 = new BuCSL()
                .disable(U.c2(Color.GRAY))
                .pressed(U.c2(Color.RED))
                .normal(U.c2(Color.BLACK))
                .create();
        mState1Drw = new DrawerComposer(mContext)
                .addDrawer(new N1208_Play()
                        .colorStroke(color01_x4)
                        .colorFill(color01_x4)
                        .margins(12)
                )
                .addDrawer(new N1208_Circle()
                        .colorStroke(color01_x4)
                        .margins(5)
                );
        mState1Drw.n1475_setDebugString("mDrawerState1");

        //=== drawer-2
        //=
        N1208_AbsDrawer drwStop = new N1208_Stop()
                .colorStroke(color01_x4)
                .colorFill(color01_x4)
                .margins(14);
        drwStop.n1475_setDebugString("drwStop");
        //=
        mDrwCircleAnim = ((N1208_Circle) new N1208_Circle()
                .gap(15, 270)
                .colorStroke(color01_x4)
                .margins(5)
                //anim
                .animMode(true)
                .animPlay(true)
                .animDuration(3000)
                .animInterpolator(new LinearInterpolator()))
                ._animInfinityRotate((AppCompatActivity) mContext);
        mDrwCircleAnim.n1475_setDebugString("drwCircleState2");
        mFragm.animCircle = mDrwCircleAnim;                     //<===
        //=
        mState2Drw = new DrawerComposer(mContext)
                .addDrawer(drwStop)
                .addDrawer(mDrwCircleAnim);
        mState2Drw.n1475_setDebugString("mDrawerState2");

        //=== drawer-3
        int mDp_x2 = 8;
        mDrwCircleState3 = ((N1208_Circle) new N1208_Circle()
                .colorStroke(color01_x4)
                .margins(mDp_x2)
                .th(1))
                .asArc(START_ANGLE_STATE_3, 180);
        mState3Drw = new DrawerComposer(mContext)
                .addDrawer(mDrwCircleAnim)
                .addDrawer(drwStop)
                //серое кольцо на фоне
                .addDrawer(new N1208_Circle()
                        .colorStroke(new BuCSL()
                                .disable(EC.MD_GREY_300)
                                .pressed(U.c2(Color.RED))
                                .normal(EC.MD_GREY_300)
                                .create())
                        .margins(mDp_x2)
                )
                .addDrawer(mDrwCircleState3);

        //===
        mCurrState = STATE_1;
        //===
        _tv = super
                .drawer(mState1Drw)
                .createN();

        //=== onclick
        _tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //==
                if (mCurrState == STATE_1) {
                    //== запрос разрешения на продолжение у реализующего
                    if (mPresenter != null) {
                        boolean b = mPresenter.onBeforeStart();
                        if (!b) {
                            //^ клиент BuXdso запретил продолжать
                            return; //======X
                        }
                        if (mGefp != null) {
                            EventBus.getDefault().post(new EvString(mGefp, EV_STATE_1A));
                        }
                    }
                    _toState2();
                } else {
                    if (mGefp != null) {
                        EventBus.getDefault().post(new EvString(mGefp, EV_STATE_2A));
                    }
                    //===
                    if (mPresenter != null) {
                        boolean b = mPresenter.onBeforeStop();
                        if (!b) {
                            //^ клиент не разрешает перейти в другое состояние, ему необходимо время
                            // на остановку. Он сам выполнит перевод в состояние 1 когда придет время
                            return; //======X
                        }
                    }
                    //===
                    _toState1();
                    if (mPresenter != null) {
                        mPresenter.onStop(BuXdso_v_.this); //[[170221082100]]
                    }
                }
            }
        });
        _tv.setClickable(true);
        //===
        return _tv;
    }

    public void _toState1() {
        if (mCurrState == STATE_1) {
            return;
        }
        mCurrState = STATE_1;
        //=
        mDrwCircleAnim.animCancel();
        //=
        BuXdso_v_.this.drawer(mState1Drw);
        BuXdso_v_.this.apply(_tv);
        //=
        if (mGefp != null) {
            EventBus.getDefault().post(new EvString(mGefp, EV_STATE_1));
        }
    }

    //builders
    //``````````````````````````````````````````````````````````````````````````````````````````````

    public BuXdso_v_ presenter(Presenter_x_ p) {
        mPresenter = p;
        return this;
    }

    public BuXdso_v_ enable(boolean b) {
        mEnable = b;
        return this;
    }

    //commands
    //``````````````````````````````````````````````````````````````````````````````````````````````

    /**
     * Перевод виджета в состояние 2
     */
    public void _toState2() {
        if (mCurrState == STATE_2) {
            return;
        }
        mCurrState = STATE_2;
        mDrwCircleAnim
                .animMode(true)
                .animPlay(true);
        BuXdso_v_.this.drawer(mState2Drw);
        _tv.invalidate();
        //===
        if (mPresenter != null) {
            mPresenter.onStart(BuXdso_v_.this);
        }
        //===
        if (mGefp != null) {
            EventBus.getDefault().post(new EvString(mGefp, EV_STATE_2));
        }
    }

    /**
     * @param percent (1) -- 0..100
     */
    public void _toState3(int percent) {
        //Log2_01.i(TAG, "--41> startState3()" + "; percent=[" + percent + "]", LOG2);
        if (mCurrState == STATE_1 || mCurrState == STATE_2) {
            if (mGefp != null) {
                EventBus.getDefault().post(new EvString(mGefp, EV_STATE_3));
            }
            mCurrState = STATE_3;
        }
        if (percent < 2) {
            percent = 2;
        }

        if (percent >= 0 && percent <= 100) {
            int sweepAngle = (int) (((percent * 1f) / (100 * 1f)) * 360f);
            //=
            mDrwCircleState3
                    .asArc(START_ANGLE_STATE_3, sweepAngle);
            this.drawer(mState3Drw);
        }

//        if (percent < 100) {
//            int sweepAngle = (int) (((percent * 1f) / (100 * 1f)) * 360f);
//            //=
//            mDrwCircleState3
//                    .asArc(START_ANGLE_STATE_3, sweepAngle);
//            this.drawer(mState3Drw);
//        } else {
//            _toState1();
//            if (mPresenter != null) {
//                mPresenter.onEnd(this);
//            }
//        }
        _tv.invalidate();
    }


}

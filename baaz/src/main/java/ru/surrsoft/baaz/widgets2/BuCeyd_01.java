package ru.surrsoft.baaz.widgets2;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import ru.surrsoft.baaz.Bysa_01;
import ru.surrsoft.baaz.suite.figures.N1208_Chevron;
import ru.surrsoft.baaz.univers.EStrings;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;
import ru.surrsoft.baaz.widgets2.buLay.BuLayRelative_01;
import ru.surrsoft.baaz.widgets2.nviews.NLinearLayout;
import ru.surrsoft.baaz.widgets2.nviews.NRelativeLayout;
import ru.surrsoft.baaz.widgets2.nviews.NTextView;

/**
 * [[ceyd]]
 * <p>
 * Виджет с _областью, со скрытым _районом и с _кнопкой справа. По нажатию на _кнопку открывается _район
 * (расположен ниже _области) куда можно поместить любые виджеты. В _область тоже можно поместить
 * любые виджеты
 * <p>
 * Используюищий может передать _слушатель нажатия на _кнопку.
 * <p>
 * ПОНЯТИЯ:
 * -- _btn, _кнопка, ^[[dzah]] - кнопка
 * -- [[apmx]] - состояние _кнопки (и текущего виджета в целом). Состояния два - "открыто" и
 * "закрыто", см. {@link #APMX_OPEN} и {@link #APMX_CLOSE}
 * -- _obl, _область, [[mzox]] - виджет крайний слева. Это "наименование" как-бы
 * -- _met, _метка, [[uamc]] - опциональный TextView-виджет между _obl и _ray, прижат
 * к _btn слева. Используется как правило для показа сколько потомков у узла
 * -- _ray, _район, [[kurp]] - то что открывается по нажатию на _кнопку. Контейнер для [zcnc]
 * -- [[zcnc]] - виджет вставляемый в [kurp]
 * -- _слушатель, _lst - слушатель нажатия на _кнопку. Задается использующим. В этот _слушатель при
 * нажатии на _кнопку, если _кнопка была с
 * сотоянии {@link #APMX_CLOSE} перед нажатием, будет передан _район куда использующий может
 * вставить любые виджеты, в том числе очередной [ceyd]
 * <p>
 * КОДЫ: [zipc], [ksao]
 * <p>
 * ВЕРСИЯ 1 1.0 2019-02-06 (stored)
 */
public class BuCeyd_01 extends AbsBuView_01 {

  //[mApmx]
  //```````````````````````````````````````````
  private static final boolean APMX_CLOSE = true;
  private static final boolean APMX_OPEN = false;

  //```````````````````````````````````````````
  private final Context mContext;
  /**
   * drawer для _кнопки
   */
  private final N1208_Chevron mBtnDrawer;
  /**
   * Виджет для _области
   */
  private View mMzox;
  /**
   * Виджет для _района
   */
  private View mZcnc;
  /**
   *
   */
  private ViewGroup mParentView;
  /**
   * Слушатель нажатия на _кнопку
   */
  private View.OnClickListener mPresenter;
  /**
   *
   */
  private boolean mApmx = APMX_CLOSE;
  /**
   * см. [kurp]
   */
  private NLinearLayout mKurp;
  /**
   * см. [uamc]
   */
  private View mUamc;
  /**
   * Если TRUE то _btn будет располагаться справа, иначе слева
   */
  private boolean mDzahRight = false;
  /**
   *
   */
  private BuYeca_01 mYecaBu;


  //==============================================================================================
  //constructors
  //``````````````````````````````````````````````````````````````````````````````````````````````
  public BuCeyd_01(Context context) {
    mContext = context;
    mBtnDrawer = new N1208_Chevron();
    //---
    mW_px = ViewGroup.LayoutParams.MATCH_PARENT;
    mH_px = ViewGroup.LayoutParams.WRAP_CONTENT;
  }

  //commands
  //``````````````````````````````````````````````````````````````````````````````````````````````

  /**
   * Обновление содержимого [kurp]
   *
   * @param newZcnc (1) -- новое содержимое для [kurp]
   */
  public void _zcncUpdate(View newZcnc) {
    mKurp.removeAllViews();
    mKurp.addView(newZcnc);
  }

  /**
   * Программное переключение [ampx]-состояния
   */
  public void _apmxToggle() {
    mYecaBu._toggle();
  }


  //builders
  //``````````````````````````````````````````````````````````````````````````````````````````````

  public BuCeyd_01 buDzahRight(boolean b) {
    mDzahRight = b;
    return this;
  }

  /**
   * Задание виджета для _области
   *
   * @param view (1) --
   * @return --
   */
  public BuCeyd_01 buMzoxView(View view) {
    mMzox = view;
    return this;
  }

  /**
   * Задание виджета для _района
   *
   * @param view (1) --
   * @return --
   */
  public BuCeyd_01 buZcncView(View view) {
    mZcnc = view;
    return this;
  }

  /**
   * Задание виджета для _метки
   *
   * @param view (1) --
   * @return (1) --
   */
  public BuCeyd_01 buUamcView(View view) {
    mUamc = view;
    return this;
  }

  /**
   * @param viewGroup (1) --
   * @return --
   */
  public BuCeyd_01 buAddTo(ViewGroup viewGroup) {
    mParentView = viewGroup;
    return this;
  }

  /**
   * @param p (1) --
   * @return --
   */
  public BuCeyd_01 buPresenter(View.OnClickListener p) {
    mPresenter = p;
    return this;
  }


  //make
  //``````````````````````````````````````````````````````````````````````````````````````````````
  @Override
  public NLinearLayout build() {
    super.build();
    //===
    if (mMzox == null) throw new RuntimeException("(debug) не задан виджет для _области");

    //=== создание и настройка ret
    NLinearLayout ret = new BuLayLinear_01(mContext)
      .build();
    //--- если в билдере задан unoxGetNksc
    if (mParentView != null) {
      mParentView.addView(ret);
    }
    //--- настройка ret
    ViewGroup.MarginLayoutParams lp = lpConfigure(ret);
    ret.setLayoutParams(lp);

    //=== [kurp]
    mKurp = new BuLayLinear_01(mContext)
      .visibility(false)
      .margins(10, 0, 0, 0)
      .build();

    //=== загрушка для [zcnc]
    if (mZcnc == null) {
      mZcnc = new BuilderTV(mContext)
        .addTo(mKurp)
        .text(EStrings._NO_DATA.val())
        .create();
    }

    //=== _btn
    NLinearLayout _btnWrap = new BuLayLinear_01(Bysa_01.appContext)
      .w(24 + 7)
      .h(48)
      .gravityIn(Gravity.CENTER_VERTICAL | Gravity.LEFT)
      .build();
    //--
    mYecaBu = new BuYeca_01(Bysa_01.appContext)
      .buPresenter(new BuYeca_01.Vs19() {
        @Override
        public void n1441_onChangeState(boolean newState) {
          toggleApmxState();
          m55(newState);
        }
      })
      .buWH(18);
    //--
    NTextView _btn = mYecaBu
      .create();
    //--
    _btnWrap.addView(_btn);

    //=== слой на базе RelativeLayout в который входит [mzox], [uamc], _btn
    NRelativeLayout layUp;
    if (mDzahRight) {
      layUp = new BuLayRelative_01(mContext)
        .appendToRight(_btn)
        .appendToRight(mUamc)
        //фейковый виджет
        .appendToRight(new BuilderTV(mContext)
          .w(0)
          .h(48)
          .create()
        )
        .addBaseView(mMzox)
        .build();
    } else {
      layUp = new BuLayRelative_01(mContext)
        .appendToLeft(_btnWrap)
        .appendToRight(mUamc)
        //фейковый виджет
        .appendToRight(new BuilderTV(mContext)
          .w(0)
          .h(48)
          .create()
        )
        .addBaseView(mMzox)
        .build();
    }

    layUp.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mYecaBu._toggle();
        toggleApmxState();
        m55(mApmx == APMX_CLOSE);
      }
    });

    //===
    ret.addView(layUp);
    ret.addView(mKurp);
    //===
    return ret;
  }

  private void toggleApmxState() {
    mApmx = mApmx == APMX_CLOSE ? APMX_OPEN : APMX_CLOSE;
  }

  private void m55(boolean newState) {
    if (!newState) {
      mKurp.setVisibility(View.VISIBLE);
      //--
      if (mPresenter != null) {
        mPresenter.onClick(mKurp);
      }
    } else {
      mKurp.setVisibility(View.GONE);
      mKurp.removeAllViews();      //<=== очистка [kurp]
    }
  }

}

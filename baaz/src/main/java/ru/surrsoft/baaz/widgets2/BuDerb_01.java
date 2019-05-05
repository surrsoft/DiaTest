package ru.surrsoft.baaz.widgets2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.suite.figures.N1208_AbsDrawer;
import ru.surrsoft.baaz.univers.Jmox;
import ru.surrsoft.baaz.widgets2.nviews.NTextView;

/**
 * Бинарный переключатель. Состояние меняется циклически нажатием. Контентом
 * являются drawer-изображения. Основой является {@link NTextView}
 * <p>
 * ИСПОЛЬЗОВАНИЕ:
 * -- задать drawer для TRUE-состояния ({@link #drawerTrue(N1208_AbsDrawer)}) , для
 * FALSE-состояния ({@link #drawerFalse(N1208_AbsDrawer)} )
 * --- указать начальное состояние ({@link #_setState(boolean)})
 * -- для реакции на нажатия передать презентер ({@link #presenter(Presenter)}).
 * -- если нужно позволить переходить в следующее состояние по условию, то следует задать презентер
 * {@link #jmox(Jmox)}
 * -- пример: https://gist.github.com/surrsoft/ed1aa9f6131326a4bd65554ca214fcec </li>
 * <p>
 * ID: [[derb]]
 * <p>
 * КОДЫ: [zipc], [ksao]
 */
public class BuDerb_01 extends AbsBuView_01 {

  private final Context mContext;
  private N1208_AbsDrawer mDrawerTrue;
  private N1208_AbsDrawer mDrawerFalse;
  private boolean mState;
  private ViewGroup mParentLay;
  private Presenter mPresenter;
  private NTextView _tv;
  private boolean mEnable = true;
  private Jmox mJmox;

  //constructors
  //``````````````````````````````````````````````````````````````````````````````````````````````
  public BuDerb_01(Context c) {
    mContext = c;
    _tv = new NTextView(mContext);
  }

  //``````````````````````````````````````````````````````````````````````````````````````````````
  public interface Presenter {
    void n1441_onChangeState(boolean newState);
  }

  //builders
  //``````````````````````````````````````````````````````````````````````````````````````````````
  public BuDerb_01 presenter(Presenter p) {
    mPresenter = p;
    return this;
  }

  public BuDerb_01 drawerTrue(N1208_AbsDrawer drawer) {
    mDrawerTrue = drawer;
    return this;
  }

  public BuDerb_01 drawerFalse(N1208_AbsDrawer drawer) {
    mDrawerFalse = drawer;
    return this;
  }

  public BuDerb_01 startState(boolean state) {
    mState = state;
    return this;
  }

  public BuDerb_01 addTo(ViewGroup parent) {
    mParentLay = parent;
    return this;
  }

  public BuDerb_01 jmox(Jmox jmox) {
    mJmox = jmox;
    return this;
  }

  //commands
  //``````````````````````````````````````````````````````````````````````````````````````````````
  public void _enable(boolean b) {
    _tv.setEnabled(b);
  }

  public void _setState(boolean b) {
    mState = b;
  }

  public void _toggleState() {
    m43();
  }

  public boolean _getState() {
    return mState;
  }

  //make
  //``````````````````````````````````````````````````````````````````````````````````````````````
  public NTextView apply(NTextView ntv) {
    _tv = ntv;
    return build();
  }

  @Override
  public NTextView build() {
    super.build();
    //===
    if (mDrawerTrue == null && mDrawerFalse == null) throw new SomeException("(debug)");
    //===
    if (mParentLay != null) {
      mParentLay.addView(_tv);
    }
    //===
    ViewGroup.MarginLayoutParams lp;
    lp = lpConfigure(_tv);
    //===
    _tv.bu = this;
    //===
    _tv.drawer(mState ? mDrawerTrue : mDrawerFalse);
    //===
    if (mMargins != null) {
      mMargins.setFor(lp);
    }
    //===
    _tv.setLayoutParams(lp);
    //===
    //борьба с проблемой [w130w]
    ViewTreeObserver vto = _tv.getViewTreeObserver();
    if (vto.isAlive()) {
      vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
        @Override
        public boolean onPreDraw() {
          mDrawerTrue
            .w_px(_tv.getWidth())
            .h_px(_tv.getHeight());
          mDrawerFalse
            .w_px(_tv.getWidth())
            .h_px(_tv.getHeight());
          //==
          return true;
        }
      });
    }
    //===
    _tv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        boolean b = true;
        //---
        if (mJmox != null) {
          b = mJmox.fun(null);
        }
        //---
        if (b) {
          m43();
          if (mPresenter != null) mPresenter.n1441_onChangeState(mState);
        }
      }
    });
    //===
    if (mDrawerTrue.isAnimMode()) mDrawerTrue.animView(_tv);
    if (mDrawerFalse.isAnimMode()) mDrawerFalse.animView(_tv);
    //=== enable
    _tv.setEnabled(mEnable);
    //===
    return _tv;
  }

  private void m43() {
    mState = !mState;
    if (mState) {
      mDrawerFalse.animPlay(true);
      _tv.drawer(mDrawerTrue);
    } else {
      mDrawerTrue.animPlay(true);
      _tv.drawer(mDrawerFalse);
    }
    _tv.invalidate();
  }


  public N1208_AbsDrawer getDrawerTrue() {
    return mDrawerTrue;
  }

  public N1208_AbsDrawer getDrawerFalse() {
    return mDrawerFalse;
  }

}

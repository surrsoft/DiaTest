package ru.surrsoft.baaz.univers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;


/**
 * Создает ValueAnimator.
 * This прикрепляется к ЖЦ активити синхронизируя с ним старт/стоп анимации.
 * Для корректной работы этой синхронизации, управлять полученным ValueAnimator следует через методы
 * THIS, а не самого ValueAnimator. Получить ссылку на THIS можно через обратный вызов используя
 * метод {@link #create(IBack)}
 * <p>
 * <p>
 * //
 */
public class BuValueAnimator implements IDebugString {

  private final MFragmentBuValueAnimator mFragment;
  private ValueAnimator mValueAnimator;
  private boolean mIsStarted;
  private boolean byStopActivity;
  private String mDebugString;


  //==============================================================================================

  //constructors
  //``````````````````````````````````````````````````````````````````````````````````````````````
  public BuValueAnimator(AppCompatActivity acty) {
    mFragment = new MFragmentBuValueAnimator();
    acty
      .getSupportFragmentManager()
      .beginTransaction()
      .add(mFragment, "")
      .commit();
  }


  //``````````````````````````````````````````````````````````````````````````````````````````````
  public interface IBack {
    void back(BuValueAnimator bu);
  }

  //make
  //``````````````````````````````````````````````````````````````````````````````````````````````
  public ValueAnimator create(IBack back) {
    ValueAnimator valueAnimator = create();
    if (back != null) {
      back.back(this);
    }
    return valueAnimator;
  }

  public ValueAnimator create() {
    mValueAnimator = new ValueAnimator();
    mValueAnimator.addListener(new AnimatorListenerAdapter() {
      @Override
      public void onAnimationEnd(Animator animation) {
        if (!byStopActivity) {
          mIsStarted = false;
        }
        byStopActivity = false;
      }
    });
    //===
    mFragment.va = mValueAnimator;
    mFragment.buVa = this;
    return mValueAnimator;
  }

  //commands
  //``````````````````````````````````````````````````````````````````````````````````````````````

  public void _start() {
    mIsStarted = true;
    if (!mValueAnimator.isRunning()) {
      mValueAnimator.start();
    }
  }

  public void _cancel() {
    mIsStarted = false;
    mValueAnimator.cancel();
  }

  public void _end() {
    mValueAnimator.end();
  }

  /**
   * @return TRUE если анимация была запущена с помощью {@link #_start()}, но не была остановлена
   * своим порядком либо через {@link #_cancel()} (в случае с INFINITE анимацией)
   */
  public boolean _isStarted() {
    return mIsStarted;
  }

  /**
   * @return TRUE если аниматор в настоящее время работает, генерирует значения
   */
  public boolean _isRunning() {
    return mValueAnimator != null && mValueAnimator.isRunning();
  }

  //фрагмент без UI, для пристыковки к ЖЦ активити
  //``````````````````````````````````````````````````````````````````````````````````````````````
  public static class MFragmentBuValueAnimator extends Fragment {
    public ValueAnimator va;
    public BuValueAnimator buVa;

    //=====================================================================

    @Override
    public void onStart() {
      if (buVa.mIsStarted) {
        va.start();
      }
      super.onStart();
    }

    @Override
    public void onStop() {
      buVa.byStopActivity = true;
      va.cancel();
      super.onStop();
    }
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

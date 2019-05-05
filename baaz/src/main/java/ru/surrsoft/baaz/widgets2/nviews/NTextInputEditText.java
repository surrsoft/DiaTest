package ru.surrsoft.baaz.widgets2.nviews;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.util.AttributeSet;
import android.view.KeyEvent;

import ru.surrsoft.baaz.logs.Vdin_01;
import ru.surrsoft.baaz.univers.UniPresenterEmpty;


/**
 *
 */
public class NTextInputEditText extends TextInputEditText {

  private UniPresenterEmpty mKeyBackSignal;
  public String debugText = "";

  //constructors
  //``````````````````````````````````````````````````````````````````````````````````````````````

  /**
   * @param context       (1) --
   * @param keyBackSignal (2) -- сюда передается сигнал о нажатии аппаратной клавиши "Назад"
   */
  public NTextInputEditText(Context context, UniPresenterEmpty keyBackSignal) {
    super(context);
    mKeyBackSignal = keyBackSignal;
  }

  public NTextInputEditText(Context context) {
    super(context);
  }

  public NTextInputEditText(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public NTextInputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  //``````````````````````````````````````````````````````````````````````````````````````````````
  @Override
  public boolean onKeyPreIme(int keyCode, KeyEvent event) {
    Vdin_01.logStart("onKeyPreIme(keyCode=[" + keyCode + "]; " +
      "event=[" + event + "]) //190413-125602", NTextInputEditText.this);
    //---
    if (!isFocusable()) {
      Vdin_01.logM("виджет не focusable, поэтому ничего не делаем //190413-125601", NTextInputEditText.this);
      Vdin_01.logEnd("", this);
      return false;
    }
    //---
    if (keyCode == KeyEvent.KEYCODE_BACK) {
      Vdin_01.logM("нажатия клавиша это 'аппаратная клавиша Назад' //190413-125600", NTextInputEditText.this);
      if (mKeyBackSignal != null) {
        mKeyBackSignal.fun(); //[[170210083500]]
      }
      Vdin_01.logM("(" + (mKeyBackSignal != null) + ") вызов mKeyBackSignal.fun(); //190413-125700", NTextInputEditText.this);
    } else if (keyCode == KeyEvent.KEYCODE_MENU) {
      Vdin_01.logM("нажатая клавиша это KeyEvent.KEYCODE_MENU; поглощаем это нажатие //190413-125900", NTextInputEditText.this);
      // Поглощение события
      Vdin_01.logEnd("", this);
      return true;
    } else {
      Vdin_01.logM("никаких действий не потребовалось //190413-125901", this);
    }
    Vdin_01.logEnd("", this);
    return false;
  }

  //---

  public void ySetDebugText(String st) {
    debugText = st;
  }

  public void ySetPresenter(UniPresenterEmpty p) {
    mKeyBackSignal = p;
  }
}

package ru.surrsoft.baaz.widgets2.awoh;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import ru.surrsoft.baaz.cls_c.G67G;
import ru.surrsoft.baaz.logs.Vdin_01;
import ru.surrsoft.baaz.suite.lvl0.EEditTextInputTypes;
import ru.surrsoft.baaz.tclasses.TString_01;
import ru.surrsoft.baaz.univers.Color2;
import ru.surrsoft.baaz.univers.UniPresenterEmpty;
import ru.surrsoft.baaz.widgets2.AbsBuView_01;
import ru.surrsoft.baaz.widgets2.nviews.NTextInputEditText;

/**
 * [[awoh]] - это {@link TextInputLayout} с {@link TextInputEditText} внутри; EditText с
 * анимированным hint (при нажатии hint плавно "уходит" в заголовок)
 * <p>
 * По умолчанию не фокусабельный на старте активити (см. {@link #focusableOnStart(boolean)};
 * становится таковым при нажатии
 * <p>
 * Обладает состояниями: АКТИВЕН/НЕ-АКТИВЕН. Активен это когда на данном виджете выполнено нажатие
 * и он перешел в режим ввода текста, показалась клавиатура
 * <p>
 * ПОНЯТИЯ
 * <li> _et - editText </li>
 * <li> _hint - </li>
 * <li> _title - заголовок; в заголовок перемещается _hint при выборе пустого _et </li>
 * <li> _text - текст находящийся в _et (текст введенный пользователем или программно) </li>
 * <li>  </li>
 * <p>
 * ID: [[w468w]]
 * <p>
 * КОДЫ: [zipc], [ksao]
 */
public class BuAwoh extends AbsBuView_01 {

  private final Context mContext;
  private CharSequence mHint;
  private ViewGroup mParent;
  private boolean mFocusableOnStart;
  private boolean mIsActive;
  private Presenter mPresenter;
  private NTextInputEditText _et;
  public String debugText = "";
  private Color2 mColorText = new Color2(Color.BLACK);
  private Color2 mColorHintFocused;
  private Color2 mColorHint;
  private TextInputLayout _ret;
  private boolean mMultiline;
  private ColorStateList mColorTextCSL;
  private boolean mApply2;
  private EEditTextInputTypes eEditTextInputTypes;
  private String text;


  //constructors
  //``````````````````````````````````````````````````````````````````````````````````````````````
  public BuAwoh(Context c) {
    mContext = c;
    mW_px = ViewGroup.LayoutParams.MATCH_PARENT;
    mH_px = ViewGroup.LayoutParams.WRAP_CONTENT;
    //---
    _ret = new TextInputLayout(mContext);

  }

  //``````````````````````````````````````````````````````````````````````````````````````````````
  public static class Presenter {
    /**
     * Вызывается при изменении состояния активности editText
     *
     * @param active (1) -- TRUE если причиной обратного вызова является переход в состояние "в
     *               фокусе". FALSE если причиной является переход в состояние "не в фокусе"
     */
    public void n1468_onChangeActiveState(boolean active) {

    }

    /**
     * Вызывает после каждого изменения текста в _et
     *
     * @param st (1) -- новый текст
     */
    public void n1468_onTextChange(Editable st) {

    }

    /**
     * Вызывается при нажатии "аппаратной кнопки Назад"
     */
    public void n1468_onKeyBackPress(){

    }
  }

  //builders
  //``````````````````````````````````````````````````````````````````````````````````````````````

  //region builders

  public BuAwoh multiline(boolean b) {
    mMultiline = b;
    return this;
  }

  public BuAwoh presenter(Presenter p) {
    mPresenter = p;
    return this;
  }

  public BuAwoh hint(CharSequence chs) {
    mHint = chs;
    return this;
  }

  /**
   * Стартовый текст
   *
   * @param text (1) --
   * @return --
   */
  public BuAwoh buText(String text) {
    this.text = text;
    return this;
  }

  public BuAwoh addTo(ViewGroup vg) {
    mParent = vg;
    return this;
  }

  public BuAwoh debugText(String st) {
    debugText = st;
    return this;
  }

  /**
   * Если (1) TRUE то на старте текущий виджет сразу получает фокус (показывается мигающий курсор),
   * если конечно другой EditText-подобный-виджет уже не перехватил фокус
   *
   * @param b (1) --
   * @return --
   */
  public BuAwoh focusableOnStart(boolean b) {
    mFocusableOnStart = b;
    mtSimple_setIsActive(b);
    return this;
  }

  /**
   * См. _text - текст находящийся в _et (текст введенный пользователем или программно)
   *
   * @param c (1) --
   * @return --
   */
  public BuAwoh colorText(Color2 c) {
    mColorText = c;
    return this;
  }

  public BuAwoh colorText(ColorStateList csl) {
    mColorTextCSL = csl;
    return this;
  }

  /**
   * Цвет _hint когда _et в фокусе; в это время _hint располагается в _title
   *
   * @param c (1) --
   * @return --
   */
  public BuAwoh colorHintFocused(Color2 c) {
    mColorHintFocused = c;
    return this;
  }

  /**
   * Цвет _hint в двух ситуациях: 1) когда _hint отображается вместо отсутствующего в _et текста;
   * 2) когда _hint находится в _title и _et при этом не в фокусе
   *
   * @param c (1) --
   * @return --
   */
  public BuAwoh colorHint(Color2 c) {
    mColorHint = c;
    return this;
  }

  /**
   * Влияет на то какая клавиатура будет показана
   *
   * @param eEditTextInputTypes (1) --
   * @return --
   */
  public BuAwoh buInputType(@Nullable EEditTextInputTypes eEditTextInputTypes) {
    this.eEditTextInputTypes = eEditTextInputTypes;
    return this;
  }

  //endregion

  //create
  //``````````````````````````````````````````````````````````````````````````````````````````````
  public TextInputLayout apply(TextInputLayout til) {
    _ret = til;
    return build();
  }

  /**
   * @param til (1) --
   * @return --
   */
  public TextInputLayout test_apply2(TextInputLayout til) {
    _ret = til;
    mApply2 = true;
    return build();
  }

  /**
   * @return ---
   */
  public TextInputLayout build() {
    super.build();

    //---
    _ret.setOrientation(LinearLayout.HORIZONTAL);

    //ret.setHintTextAppearance(R.style.s25);

    ViewGroup.MarginLayoutParams lp = lpConfigure(_ret);
    _ret.setLayoutParams(lp);

    //--- _et
    UniPresenterEmpty keyBackSignal = () -> {
      //link [170210083500]
      Vdin_01.logM("нажата клавиша НАЗАД; (" + mIsActive + ") при открытой клавиатуре", BuAwoh.this);
      if (mIsActive) {
        int delay = mtGetResolveDelay_x3();
        new Handler().postDelayed(() -> handleChange_x4(false), delay);
      }
      //---
      if (mPresenter != null) {
        mPresenter.n1468_onKeyBackPress();
      }
    };
    if (mApply2) {
      //_et = ((NTextInputEditText) _ret.findViewById(R.id.n1468_3));
      _et.ySetPresenter(keyBackSignal);
    } else {
      _et = new NTextInputEditText(mContext, keyBackSignal);
    }

    //---
    _et.setOnKeyListener(new View.OnKeyListener() {
      @Override
      public boolean onKey(View v, int keyCode, KeyEvent event) {
        Vdin_01.logM("onKey(.., .., event=["+event+"]) //190413-144900", BuAwoh.this);
        return false;
      }
    });

    //---
    if (mColorText != null) {
      _et.setTextColor(mColorText.val);
    }
    if (mColorTextCSL != null) {
      _et.setTextColor(mColorTextCSL);
    }

    //---
    _et.ySetDebugText(debugText);
    //--- удаление полоски внизу
    _et.setBackgroundDrawable(null);
    //--- InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS отключает "автоподчеркивание"
    int inputType = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS;
    if (mMultiline) {
      inputType = inputType | InputType.TYPE_TEXT_FLAG_MULTI_LINE;
    }
    _et.setInputType(inputType);
    //---
    ViewGroup.LayoutParams lp0;
    if (!mApply2) {
      lp0 = new LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
      );
    } else {
      lp0 = new FrameLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
      );
    }
    _et.setLayoutParams(lp0);
    //--- hint
    _et.setHint(mHint);
    //--- text
    if (TString_01.isEmpty(text, false)) {
      _et.setText(text);
    }
    //--- focusable
    _et.setFocusable(mFocusableOnStart);
    //--- //<=== НАЖАТИЕ
    _et.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (!mIsActive) {
          mtToActiveState_x2();
          //=
          //чтобы анимация подсказки запустилась
          _et.requestFocus();
          //=
          mtSimple_setIsActive(true);
        }
        //---
        mtKeyboardShowWithDelay();
      }
    });
    //--- слушатель изменения текста в _et
    _et.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable s) {
        if (mPresenter != null) {
          mPresenter.n1468_onTextChange(s);
        }
      }
    });
    //--- реагирование на кнопку "ГОТОВО" клавиатуры
    _et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        boolean back = false;
        if (mIsActive && actionId == EditorInfo.IME_ACTION_DONE) {
          back = true;
          handleChange_x4(false);
          mtKeyboardShow_maybeDelay(false);
        }
        return back;
      }
    });
    //--- фокус теряется например при переходе к другому editText
    _et.setOnFocusChangeListener((v, hasFocus) -> handleChange_x4(hasFocus));
    //--- //new//
    if (eEditTextInputTypes != null) {
      _et.setInputType(eEditTextInputTypes.inputType);
    }
    //---
    if (!mApply2) {
      _ret.addView(_et);                  //<===
    }
    //--- color _titletextfocus
    if (mColorHintFocused != null) {
      reflectSet(_ret, "mFocusedTextColor", mColorHintFocused.val);
    }
    //---
    if (mColorHint != null) {
      reflectSet(_ret, "mDefaultTextColor", mColorHint.val);
    }
    //---
    if (mParent != null) {
      mParent.addView(_ret);
    }
    //---
    return _ret;
  } //build()

  public BuAwoh build_B() {
    build();
    return this;
  }

  /**
   * Показ клавиатуры с задержкой, чтобы не дергалась анимация "подсказки"
   */
  private void mtKeyboardShowWithDelay() {
    int delay = mtGetResolveDelay_x3();
    new Handler().postDelayed(() -> G67G.Misc.keyboardShow(_et, mContext), delay);
  }

  private void mtToActiveState_x2() {
    _et.setFocusable(true);
    _et.setFocusableInTouchMode(true);
    _et.setEnabled(true);
    //курсор в конец
    _et.setSelection(_et.getText().length());
    //выбрать весь текст
    _et.selectAll();
  }

  private void reflectSet(TextInputLayout ret, String f28, int c28) {
    try {
      Field f = ret.getClass().getDeclaredField(f28);
      f.setAccessible(true);
      f.set(ret, ColorStateList.valueOf(c28));

      Method m = ret.getClass().getDeclaredMethod("updateLabelState", boolean.class);
      m.setAccessible(true);
      m.invoke(ret, true);

    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
  }


  //private
  //``````````````````````````````````````````````````````````````````````````````````````````````
  private void handleChange_x4(boolean newState) {
    mtSimple_setIsActive(newState);
    _et.setFocusable(newState);
    _et.setFocusableInTouchMode(newState);
    if (mPresenter != null) {
      mPresenter.n1468_onChangeActiveState(newState); //[[170210092400]]
    }
  }

  private void mtSimple_setIsActive(boolean b) {
    mIsActive = b;
  }

  //region commands

  public void _enable(boolean b) {
    _et.setEnabled(b);
  }

  public void _setStateActive(boolean show) {
    boolean focused = _et.isFocused();
    if (show && !focused) {
      mtToActiveState_x2();
      //чтобы анимация подсказки запустилась
      _et.requestFocus();
      mtKeyboardShow_maybeDelay(true);
    } else if (!show && focused) {
      _et.setFocusable(false);
      _et.setFocusableInTouchMode(false);
      mtKeyboardShow_maybeDelay(false);
    }
  }

  public NTextInputEditText _getTextInputEditText() {
    return _et;
  }

  //endregion

  //private
  //``````````````````````````````````````````````````````````````````````````````````````````````

  /**
   * @param showKeyboard (1)
   */
  private void mtKeyboardShow_maybeDelay(final boolean showKeyboard) {
    int delay = mtGetResolveDelay_x3();
    new Handler().postDelayed(() -> {
      if (showKeyboard) {
        G67G.Misc.keyboardShow(_et, mContext);
      } else {
        G67G.Misc.keyboardHide(_et, mContext);
      }
    }, delay);
  }

  private int mtGetResolveDelay_x3() {
    int delay = 0;
    if (_et.getText() == null || _et.getText().length() < 1) {
      //выполняем с задержкой, чтобы анимация клавиатуры не заставляла
      //  дергаться анимацию "подсказки"
      delay = 200;
    }
    return delay;
  }


}

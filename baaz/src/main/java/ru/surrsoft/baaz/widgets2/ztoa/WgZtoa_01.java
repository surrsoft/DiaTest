package ru.surrsoft.baaz.widgets2.ztoa;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import ru.surrsoft.baaz.cls_c.G67G;
import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.univers.EDrawables;
import ru.surrsoft.baaz.widgets2.BuSpaf_01;
import ru.surrsoft.baaz.widgets2.buLay.BuLayRelative_01;

/**
 * [[ztoa]] - [nwez] основанный на {@link EditText}
 * <p>
 * ИЗ TROOGET: [nwez] - виджет. Это EditText справа от которого кнопка; кнопка предназначена чтобы
 * показать что поле слева редактируемое, а также для завершения редактирования нажатием на нее
 * <p>
 * Создает обертку над EditText с кнопкой справа. Редактирование начинается по нажатию
 * <p>
 * СТАРЫЕ НАЗВАНИЯ: "BuZtoa_v_"
 * #version 1 08.10.2016  #id [[w367w]]
 */
public class WgZtoa_01 {

  private final Context mContext;
  private String mText;
  private EditText vEt;
  private EModes mCurrMode = EModes.BASE;
  private Presenter mPresenter;
  private BuSpaf_01 buVebf;
  private ImageView vBtn;
  private EInputType mInputType;
  private float cfgBtnSize_dp = 28;
  private ImageView vBtnClose;
  private ViewGroup layParent;

  public WgZtoa_01(Context context) {
    mContext = context;
  }

  public RelativeLayout build() {
    buVebf = new BuSpaf_01(mContext)
      .buAddDrw(EDrawables._PENCIL.val)
      .buAddDrw(EDrawables._CHECK.val)
      .buAddPaddings(2)
      .buAddPaddings(0)
      .buPresenter((stateBeforeClick, stateAfterClick) -> {
        if (stateAfterClick == 1) {
          toModeEdit();
        } else {
          toModeBase();
        }
      });
    vBtn = buVebf.build();
    //---
    vBtnClose = new BuSpaf_01(mContext)
      .buAddDrw(EDrawables._CLOSE.val)
      .buColorPressed(Color.RED)
      .buAddPaddings(2)
      .buPresenter((stateBeforeClick, stateAfterClick) -> vEt.setText(""))
      .build();
    vBtnClose.setVisibility(View.GONE);
    //---
    vEt = new MEditText(mContext);
    vEt.setSelectAllOnFocus(true);
    vEt.setFocusable(false);
    vEt.setBackgroundDrawable(null);
    //==
    if (mInputType == EInputType.TEXT) {
      vEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE
        | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
    }
    if (mInputType == EInputType.NUMBER) {
      vEt.setInputType(InputType.TYPE_CLASS_NUMBER);
    }
    //==
    vEt.setText(mText);
    vEt.setOnClickListener(v -> {
      if (mCurrMode == EModes.BASE) {
        toModeEdit();
      }
    });
    vEt.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable s) {
        if (mPresenter != null) mPresenter.onTextLive(s.toString());
      }
    });
    //реагирование на кнопку "ГОТОВО" клавиатуры
    vEt.setOnEditorActionListener((v, actionId, event) -> {
      boolean handled = false;
      if (actionId == EditorInfo.IME_ACTION_DONE && mCurrMode == EModes.EDIT) {
        toModeBase();
        handled = true;
      }
      return handled;
    });
    vEt.setOnFocusChangeListener((v, hasFocus) -> {
      if (mPresenter != null) {
        mPresenter.onFocusChanged(hasFocus, vEt.getText().toString());
      }
      if (!hasFocus) {
        toModeBase();
      }
    });
    //---
    RelativeLayout ret = new BuLayRelative_01(mContext)
      .appendToRight(vBtn)
      .appendToRight(vBtnClose).lastMarginR(10)
      .addBaseView(vEt)
      .build();
    //--- задание размеров кнопки; если задать сразу, то размер некорректно отбражается до первого нажатия
    RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) vBtn.getLayoutParams();
    lp.width = G67G_Draw.px(cfgBtnSize_dp);
    lp.height = G67G_Draw.px(cfgBtnSize_dp);
    vBtn.setLayoutParams(lp);
    //=
    RelativeLayout.LayoutParams lp1 = (RelativeLayout.LayoutParams) vBtnClose.getLayoutParams();
    lp1.width = G67G_Draw.px(cfgBtnSize_dp);
    lp1.height = G67G_Draw.px(cfgBtnSize_dp);
    vBtnClose.setLayoutParams(lp1);
    //---
    if (layParent != null) {
      layParent.addView(ret);
    }
    //---
    return ret;
  }

  /**
   * Действия при переходе в базовый режим
   */
  private void toModeBase() {
    vEt.setFocusable(false);
    vEt.setFocusableInTouchMode(false);
    vBtnClose.setVisibility(View.GONE);
    mCurrMode = EModes.BASE;
    G67G.Misc.keyboardHide(vEt, mContext);
    //---
    buVebf.buStartStateIndex(0).apply(vBtn);
  }

  /**
   * Действия при переходе в режим редактирования
   */
  private void toModeEdit() {
    vEt.setFocusable(true);
    vEt.setFocusableInTouchMode(true);
    vBtnClose.setVisibility(View.VISIBLE);
    mCurrMode = EModes.EDIT;
    G67G.Misc.keyboardShow(vEt, mContext);
    //---
    buVebf.buStartStateIndex(1).apply(vBtn);
  }

  //region builders
  public WgZtoa_01 buText(String text) {
    mText = text;
    return this;
  }

  public WgZtoa_01 buPresenter(Presenter p) {
    mPresenter = p;
    return this;
  }

  public WgZtoa_01 buInputType(EInputType inputType) {
    mInputType = inputType;
    return this;
  }

  public WgZtoa_01 buAddTo(ViewGroup layParent) {
    this.layParent = layParent;
    return this;
  }

  //endregion

  private enum EModes {
    BASE,
    EDIT
  }

  public enum EInputType {
    TEXT, NUMBER
  }

  public static abstract class Presenter {
    /**
     * Передает Presenter-у текст по мере его набора
     *
     * @param text
     */
    public void onTextLive(String text) {

    }

    /**
     * Вызывается при изменении фокуса
     *
     * @param focused TRUE если _edit перешел в режим редактирования; FALSE - если вышел из режима редактирвоания
     * @param textET  текущий текст EditText
     */
    public void onFocusChanged(boolean focused, String textET) {

    }
  }

  private class MEditText extends android.support.v7.widget.AppCompatEditText {

    public MEditText(Context context) {
      super(context);
    }

    public MEditText(Context context, AttributeSet attrs) {
      super(context, attrs);
    }

    public MEditText(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
    }

    //перехват нажатия hard key back и menu
    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
      if (keyCode == KeyEvent.KEYCODE_BACK) {
        mCurrMode = EModes.BASE;
        toModeBase();
        return true;
      } else if (keyCode == KeyEvent.KEYCODE_MENU) {
        // Поглощение события
        return true;
      }
      return false;
    }
  }
}

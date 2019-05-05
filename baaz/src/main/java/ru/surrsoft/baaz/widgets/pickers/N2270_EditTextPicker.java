package ru.surrsoft.baaz.widgets.pickers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import ru.surrsoft.baaz.cls_c.G67G;

/**
 * Простой EditText, который получает фокус только при нажатии на него
 * <p/>
 * При нажатии происходит переключение с TextView на EditText
 * <p/>
 * <li> ЭЛЕМЕНТЫ </li>
 * <li> -- _edit - </li>
 * <li> -- _text - </li>
 * <p/>
 * #version 1 15.06.2016  #id [[w270w]]
 */
public class N2270_EditTextPicker extends LinearLayout {

  private Context mContext;
  public N2270_Configs _cfg;
  private EditText _edit;
  private TextView _text;
  public N2270_Presenter _presenter;
  private boolean b47;

  public interface N2270_Presenter {
    /**
     * Вызывается по завершении ввода нового текста (1)
     *
     * @param text (1) -- новый текст
     */
    void n2270_onNewText(String text);
  }

  public N2270_EditTextPicker(Context context) {
    super(context);
    init(context);
  }

  public N2270_EditTextPicker(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public N2270_EditTextPicker(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  @SuppressLint("ClickableViewAccessibility")
  private void init(Context context) {
    this.mContext = context;
    _cfg = new N2270_Configs();

    this.setOrientation(LinearLayout.VERTICAL);
    this.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
      ViewGroup.LayoutParams.WRAP_CONTENT));

    _edit = new MEditText(mContext);
    _edit.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
      ViewGroup.LayoutParams.WRAP_CONTENT));
    _edit.setVisibility(View.GONE);
    _edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        boolean handled = false;
        //нажатие кнопки "готово" на клавиатуре
        if (actionId == EditorInfo.IME_ACTION_DONE) {
          b47 = true;
          yEndEdit();
          handled = true;
        }
        return handled;
      }
    });
    _edit.setOnFocusChangeListener(new OnFocusChangeListener() {
      @Override
      public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus && !b47) {
          yEndEdit();
        }
        b47 = false;
      }
    });
    _edit.setOnTouchListener(new OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
        return false;
      }
    });

    _text = new TextView(mContext);
    _text.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
      ViewGroup.LayoutParams.WRAP_CONTENT));
    _text.setClickable(true);
    _text.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        v.setVisibility(View.GONE);
        _edit.setVisibility(View.VISIBLE);
        G67G.Misc.keyboardShow(_edit, mContext);
        _edit.setSelection(_edit.getText().length());
        _edit.selectAll();
      }
    });

    this.addView(_edit);
    this.addView(_text);
  }

  public void yCommit() {
    _edit.setText(_cfg._string);
    _edit.setInputType(_cfg.inputType);
    _text.setText(yNullAdapt(_cfg._string));
  }

  private String yNullAdapt(String s) {
    return (s == null || s.length() == 0) ? "null" : s;
  }

  private void yEndEdit() {
    String s = _edit.getText().toString();
    _cfg._string = s;
    _text.setText(yNullAdapt(s));
    _text.setVisibility(View.VISIBLE);
    _edit.setVisibility(View.GONE);
    G67G.Misc.keyboardHide(_edit, mContext);
    if (_presenter != null) _presenter.n2270_onNewText(s);
  }

  public class N2270_Configs {
    public String _string = "";
    public int inputType = InputType.TYPE_CLASS_TEXT;
  }

  /**
   * Необходим только для того чтобы отлавливать нажатие back button
   */
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

    @Override
    protected void onDetachedFromWindow() {
      b47 = true;
      yEndEdit();
      super.onDetachedFromWindow();
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
      if (keyCode == KeyEvent.KEYCODE_BACK) {
        b47 = true;
        yEndEdit();
      } else if (keyCode == KeyEvent.KEYCODE_MENU) {
        // Поглощение события
        return true;
      }
      return false;
    }
  }
}

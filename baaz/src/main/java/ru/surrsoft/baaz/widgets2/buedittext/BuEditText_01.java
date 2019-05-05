package ru.surrsoft.baaz.widgets2.buedittext;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Простой билдер EditText
 * <p>
 * СТАРЫЕ НАЗВАНИЯ: "BuilderET"
 * #id [[w312w]]
 */
public class BuEditText_01 {
  private String mHint;
  private String mText;
  private ViewGroup mParentLay;
  private Context ctx;

  //--- constructors
  public BuEditText_01(Context ctx) {
    this.ctx = ctx;
  }

  public BuEditText_01 buHint(String str) {
    mHint = str;
    return this;
  }

  public BuEditText_01 buText(String str) {
    mText = str;
    return this;
  }

  public BuEditText_01 buAddTo(ViewGroup lay) {
    mParentLay = lay;
    return this;
  }

  //---
  public EditText build() {
    EditText et = new EditText(ctx);
    //--- при получении фокуса выделяется весь текст
    et.setSelectAllOnFocus(true);
    //--- hint
    if (mHint != null) {
      et.setHint(mHint);
    }
    //--- text
    if (mText != null) {
      et.setText(mText);
    }
    //---
    if (mParentLay != null) {
      mParentLay.addView(et);
    }
    //---
    return et;
  }

  //---

  /**
   * Помещает курсор (1) в конец текста (1)
   *
   * @param et (1) --
   */
  public static void cursorToEnd(EditText et) {
    et.setSelection(et.getText().length());
  }


}

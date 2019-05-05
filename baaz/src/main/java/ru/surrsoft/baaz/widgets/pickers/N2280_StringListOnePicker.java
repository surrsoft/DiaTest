package ru.surrsoft.baaz.widgets.pickers;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;

/**
 * Простой picker с диалогом выбора одного из элементов String[] {@link #_confStrings}
 * <p/>
 * Порядок: создание объекта -- задание массива строк {@link #_confStrings}, задание стартового
 * индекса {@link #_confStartIndex} -- вызов {@link #yCommit()}
 * <p/>
 * ПРИМЕР: https://gist.github.com/surrsoft/e3df3d9dbccc46a27015d765c25b7440
 * <p/>
 * #version 1 19.06.2016  #id [[w280w]]
 */
public class N2280_StringListOnePicker extends android.support.v7.widget.AppCompatTextView {


  private Context context;
  public String[] _confStrings;
  public int _confStartIndex;
  public N2280_Presenter _presenter;

  public interface N2280_Presenter {
    /**
     * Вызывается при выборе элемента списка
     *
     * @param str   (1) -- строка
     * @param index (2) -- индекс
     */
    void n2280_onSelected(String str, int index);
  }

    //constructors
    //``````````````````````````````````````````````````````````````````````````````````````````````
  public N2280_StringListOnePicker(Context context) {
    super(context);
    init(context);
  }

  public N2280_StringListOnePicker(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public N2280_StringListOnePicker(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

    //``````````````````````````````````````````````````````````````````````````````````````````````
  private void init(Context context) {
    this.context = context;
    this.setClickable(true);
  }

  public void yCommit() {
    if (_confStrings == null || _confStrings.length < 1) {
      this.setText("!debug no datas");
      return;
    }

    AlertDialog.Builder b = new AlertDialog.Builder(context);
    b.setSingleChoiceItems(_confStrings, _confStartIndex, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        N2280_StringListOnePicker.this.setText(_confStrings[which]);
        if (_presenter != null) _presenter.n2280_onSelected(_confStrings[which], which);
      }
    });
    b.create().show();
  }
}

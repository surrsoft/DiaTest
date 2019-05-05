package ru.surrsoft.baaz.widgets.pickers;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.commons.lang3.ArrayUtils;

import ru.surrsoft.baaz.R;
import ru.surrsoft.baaz.univers.ETypefaces;

/**
 * Выбор штифта. Выбор осуществляется из класса {@link ETypefaces}
 * <p/>
 * Схема работы: создание -- установка стартового шрифта в {@link #_typefaceName} -- {@link #yCommit()}
 * <p/>
 * #version 1 15.06.2016  #id [[w2267w]]
 */
public class N2268_TypefacePicker extends android.support.v7.widget.AppCompatTextView {

  public N2268_Presenter _presenter;

  private int start;
  public String _typefaceName;
  private AlertDialog adia;

  public interface N2268_Presenter {
    /**
     * Выбранный шрифта
     *
     * @param typeface     (1) -- может быть null
     * @param typefaceName (2) -- имя шрифта (1)
     */
    void n2268_onTypefaceSelected(Typeface typeface, String typefaceName);
  }

  public N2268_TypefacePicker(Context context) {
    super(context);
    init(context);
  }

  public N2268_TypefacePicker(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public N2268_TypefacePicker(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  private void init(final Context context) {
    this.setClickable(true);

    final ETypefaces[] ts = ETypefaces.values();
    String[] arr1 = {};
    Typeface[] arr2 = {};
    for (ETypefaces elem : ts) {
      arr1 = ArrayUtils.add(arr1, elem.name());
      arr2 = ArrayUtils.add(arr2, elem.val);
    }

    start = 0;
    if (_typefaceName != null)
      start = ArrayUtils.indexOf(arr1, _typefaceName);

    final String[] finalArr1 = arr1;
    this.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        AlertDialog.Builder b = new AlertDialog.Builder(context);

        LinearLayout lay = (LinearLayout) LayoutInflater.from(context)
          .inflate(R.layout.n2268_lay, null);
        b.setView(lay);

        ListView lv = (ListView) lay.findViewById(R.id.n2268_listview);
        FontListAdapter a = new FontListAdapter(context, ETypefaces.values());
        lv.setAdapter(a);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            start = position;
            _typefaceName = finalArr1[position];
            yCommit();
            if (_presenter != null)
              _presenter.n2268_onTypefaceSelected(ts[position].val, _typefaceName);
            adia.dismiss();
          }
        });

        adia = b.create();
        adia.show();
      }
    });
  }

  public class FontListAdapter extends ArrayAdapter<ETypefaces> {
    private final Context context;
    private final ETypefaces[] tfs;

    public FontListAdapter(Context context, ETypefaces[] tfs) {
      super(context, 0, tfs);
      this.context = context;
      this.tfs = tfs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      TextView tv = new TextView(context);
      tv.setText(tfs[position].name());
      tv.setTypeface(tfs[position].val);

      return tv;
    }
  }

  public void yCommit() {
    String text;
    if (_typefaceName == null) {
      text = "null";
    } else {
      text = _typefaceName;
    }
    this.setText(text);
    if (_typefaceName != null) {
      ETypefaces tf = ETypefaces.valueOf(_typefaceName);
      this.setTypeface(tf.val);
    }
  }
}

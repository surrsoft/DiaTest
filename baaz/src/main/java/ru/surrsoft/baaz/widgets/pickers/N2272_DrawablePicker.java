package ru.surrsoft.baaz.widgets.pickers;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.commons.lang3.ArrayUtils;

import ru.surrsoft.baaz.R;
import ru.surrsoft.baaz.univers.EDrawables;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;

/**
 * Виджет выбора Drawable
 * <p/>
 * <li>ЭЛЕМЕНТЫ</li>
 * <li> -- _label - </li>
 * <li> -- _img - </li>
 * <p/>
 * #version 1 16.06.2016  #id [[w272w]]
 */
public class N2272_DrawablePicker extends LinearLayout {


  private Context context;
  private AlertDialog adia;
  public String _drawable;
  public N2272_Presenter _presenter;
  private int start;
  private TextView _label;
  private ImageView _img;

  public interface N2272_Presenter {
    void n2272_onDrawableSelected(Drawable drw, String nameDrawable);
  }

  public N2272_DrawablePicker(Context context) {
    super(context);
    init(context);
  }

  public N2272_DrawablePicker(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public N2272_DrawablePicker(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  private void init(final Context context) {
    this.context = context;
    this.setOrientation(LinearLayout.HORIZONTAL);
    this.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
      ViewGroup.LayoutParams.WRAP_CONTENT));

    _img = new ImageView(context);
    _label = new TextView(context);
    this.addView(_img);
    this.addView(_label);

    //===
    final EDrawables[] drw = EDrawables.values();
    String[] arr1 = {};
    Drawable[] arr2 = {};
    for (EDrawables elem : drw) {
      arr1 = ArrayUtils.add(arr1, elem.name());
      arr2 = ArrayUtils.add(arr2, elem.val);
    }

    start = 0;
    if (_drawable != null)
      start = ArrayUtils.indexOf(arr1, _drawable);

    final String[] finalArr1 = arr1;
    this.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        AlertDialog.Builder b = new AlertDialog.Builder(context);

        LinearLayout lay = (LinearLayout) LayoutInflater.from(context)
          .inflate(R.layout.n2268_lay, null);
        b.setView(lay);

        ListView lv = (ListView) lay.findViewById(R.id.n2268_listview);
        DrawableListAdapter a = new DrawableListAdapter(context, EDrawables.values());
        lv.setAdapter(a);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            start = position;
            _drawable = finalArr1[position];
            yCommit();
            if (_presenter != null)
              _presenter.n2272_onDrawableSelected(drw[position].val, _drawable);
            adia.dismiss();
          }
        });

        adia = b.create();
        adia.show();
      }
    });
  }

  public class DrawableListAdapter extends ArrayAdapter<EDrawables> {
    private final Context context;
    private final EDrawables[] tfs;

    public DrawableListAdapter(Context context, EDrawables[] tfs) {
      super(context, 0, tfs);
      this.context = context;
      this.tfs = tfs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      LinearLayout lay = new BuLayLinear_01(context).horizontal().build();
      ImageView iv = new ImageView(context);
      iv.setImageDrawable(tfs[position].val);
      TextView tv = new TextView(context);
      tv.setText(tfs[position].name());
      lay.addView(iv);
      lay.addView(tv);
      return lay;
    }
  }

  public void yCommit() {
    String text;
    if (_drawable == null) {
      text = "null";
    } else {
      text = _drawable;
    }
    _label.setText(text);
    if (_drawable != null) {
      EDrawables tf = EDrawables.valueOf(_drawable);
      _img.setImageDrawable(tf.val);
    }
  }
}

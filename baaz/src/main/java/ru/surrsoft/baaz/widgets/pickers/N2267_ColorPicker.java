package ru.surrsoft.baaz.widgets.pickers;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import es.dmoral.coloromatic.ColorOMaticDialog;
import es.dmoral.coloromatic.IndicatorMode;
import es.dmoral.coloromatic.OnColorSelectedListener;
import es.dmoral.coloromatic.colormode.ColorMode;
import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.R;
import ru.surrsoft.baaz.univers.Color2;
import ru.surrsoft.baaz.univers.EColors;
import ru.surrsoft.baaz.univers.TwoColors;

/**
 * Выбор цвета. Используется библиотека textColor-o-matic
 * <p/>
 * Схема работы: создание -- установка стартового цвета в {@link #_confColor} -- {@link #yCommit()}
 * <p/>
 * #version 1.0.1 15.12.2016  #id [[w2267w]]
 */
public class N2267_ColorPicker extends android.support.v7.widget.AppCompatTextView {


    private static final int MODE_0 = 0;
    private static final int MODE_1 = 1;
    public N2267_Presenter _presenter;

    /**
     * Используется библиотека textColor-o-matic
     */
    public static final int TYPE_PICKER_COLOR_O_MATIC = 1;
    /**
     * Используется {@link EColors}
     */
    public static final int TYPE_PICKER_ECOLORS = 2;

    public Color2 _confColor;
    /**
     * Выбор какой picker использовать
     * <p/>
     * См. {@link #TYPE_PICKER_COLOR_O_MATIC} , {@link #TYPE_PICKER_ECOLORS}
     */
    public int _confTypePicker = TYPE_PICKER_ECOLORS;
    private AlertDialog alertDialog;

    public interface N2267_Presenter {
        /**
         * Выбранный цвет
         *
         * @param color (1) -- может быть null
         */
        void onColorSelected(Color2 color);
    }

    public N2267_ColorPicker(Context context) {
        super(context);
        init(context);
    }

    public N2267_ColorPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public N2267_ColorPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(final Context context) {
        this.setClickable(true);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_confTypePicker == TYPE_PICKER_COLOR_O_MATIC) {
                    yColorOMatic(MODE_0);
                }
                if (_confTypePicker == TYPE_PICKER_ECOLORS) {
                    AlertDialog.Builder b = new AlertDialog.Builder(context);

                    LinearLayout lay = (LinearLayout) LayoutInflater.from(context).
                            inflate(R.layout.n2267_lay, null);
                    b.setView(lay);

                    ListView lv = (ListView) lay.findViewById(R.id.n2267_listview);
                    ColorListAdapter adr = new ColorListAdapter(context, EColors.values());
                    lv.setAdapter(adr);
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            handleColor_overl(EColors.values()[position]);
                            alertDialog.dismiss();
                        }
                    });

                    TextView btn = (TextView) lay.findViewById(R.id.n2267_color_o_matic);
                    new TwoColors(Color.BLUE, null, null, 18).colorPressed(Color.RED)
                            .underline()
                            .apply(btn);
                    btn.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            yColorOMatic(MODE_1);
                        }
                    });

                    alertDialog = b.create();
                    alertDialog.show();
                }
            }
        });
    }

    private void yColorOMatic(final int mode) {
        new ColorOMaticDialog.Builder()
                .initialColor(_confColor == null ? Color.WHITE : _confColor.val)
                .colorMode(ColorMode.ARGB) // RGB, ARGB, HVS
                .indicatorMode(IndicatorMode.HEX) // HEX or DECIMAL; Note that using HSV...
                // ... with IndicatorMode.HEX is not recommended
                .onColorSelected(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(@ColorInt int i) {
                        handleColor_overl(i);
                        if (mode == MODE_1) {
                            if (alertDialog != null) alertDialog.dismiss();
                        }
                    }
                })
                .showColorIndicator(true) // Default false, choose to show text indicator...
                // ... showing the current textColor in HEX or DEC (see images) or not
                .create()
                .show(((AppCompatActivity) getContext()).getSupportFragmentManager(),
                        "ColorOMaticDialog");
    }

    private void handleColor_overl(EColors color) {
        if (color == EColors._NULL) {
            _confColor = null;
        } else {
            if (_confColor == null) {
                _confColor = new Color2(color.val);
            } else {
                _confColor.val = color.val;
            }
        }
        yCommit();
        if (_presenter != null) {
            if (color != EColors._NULL) {
                _presenter.onColorSelected(new Color2(color.val));
            } else {
                _presenter.onColorSelected(null);
            }
        }
    }

    private void handleColor_overl(int color) {
        if (_confColor == null) {
            _confColor = new Color2(color);
        } else {
            _confColor.val = color;
        }
        yCommit();
        if (_presenter != null) {
            _presenter.onColorSelected(new Color2(color));
        }
    }

    public class ColorListAdapter extends ArrayAdapter<EColors> {
        private final Context context;
        private final EColors[] tfs;

        public ColorListAdapter(Context context, EColors[] tfs) {
            super(context, 0, tfs);
            this.context = context;
            this.tfs = tfs;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = new TextView(context);
            String s;
            if (tfs[position] != EColors._NULL) {
                s = tfs[position].name() + " " + G67G_Draw.colorIntToString(tfs[position].val);
            } else {
                s = tfs[position].name();
            }
            tv.setText(s);
            if (tfs[position] != EColors._NULL) {
                tv.setBackgroundColor(tfs[position].val);
            }else{
                tv.setTextColor(Color.WHITE);
                tv.setBackgroundColor(Color.BLACK);
            }
            return tv;
        }
    }

    public void yCommit() {
        this.setText(_confColor == null ? "null" : G67G_Draw.colorIntToString(_confColor.val));
        this.setBackgroundColor(Color.TRANSPARENT);
        if (_confColor != null) {
            this.setBackgroundColor(_confColor.val);
        }
    }
}

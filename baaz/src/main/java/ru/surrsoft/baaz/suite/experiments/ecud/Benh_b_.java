package ru.surrsoft.baaz.suite.experiments.ecud;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import java.lang.reflect.Field;

import es.dmoral.coloromatic.ColorOMaticDialog;
import es.dmoral.coloromatic.IndicatorMode;
import es.dmoral.coloromatic.OnColorSelectedListener;
import es.dmoral.coloromatic.colormode.ColorMode;
import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.cls_c.G67G_Reflect;
import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.univers.EColors;
import ru.surrsoft.baaz.univers.EFcar;
import ru.surrsoft.baaz.univers.EGravity;
import ru.surrsoft.baaz.univers.EStrings;
import ru.surrsoft.baaz.univers.TwoColors;
import ru.surrsoft.baaz.univers.UniPresenter;
import ru.surrsoft.baaz.widgets2.BuPickerChoice;
import ru.surrsoft.baaz.widgets2.BuilderTV;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;

/**
 * Сканирует класс
 * <p>
 *
 */
public class Benh_b_ {
    private Context mContext;
    private Class<?> mScanCls;
    private Object mScanOj;
    private AlertDialog alertDialog;

    //==============================================================================================
    public Benh_b_(Context c) {
        mContext = c;
    }

    /**
     * Сканируемый объект и его класс
     *
     * @param oj
     * @return
     */
    public Benh_b_ scanEntry(Object oj) {
        mScanOj = oj;
        mScanCls = oj.getClass();
        String name = mScanOj.getClass().getName();
        return this;
    }

    public LinearLayout create() {
        if (mScanCls == null || mScanOj == null) throw new SomeException("(debug)");
        //===
        LinearLayout lay = new BuLayLinear_01(mContext)
                .paddings(16)
                .build();
        //===
        recurs15(lay, mScanOj, 0);
        //===
        return lay;
    }

    private void recurs15(LinearLayout lay, Object scanOj, int level) {
        if (scanOj == null) return;
        Class<?> cls = scanOj.getClass();
        //===
        FlexboxLayout vType = getLay("type", level);
        vType.addView(
                new BuilderTV(mContext)
                        .text(cls.getName())
                        .textColor(Color.GRAY)
                        .create()
        );
        lay.addView(vType);
        //===
        for (Field eField : cls.getFields()) {
            if (eField.isAnnotationPresent(AnnEcud.class)) {
                AnnEcud ann = eField.getAnnotation(AnnEcud.class);
                String name = eField.getName();
                //==
                FlexboxLayout lay0 = getLay(name, level);
                lay.addView(lay0);
                View v = null;
                //== color int
                if (ann.type() == EEcudTypes.COLOR_INT) {
                    v = getColorPicker(eField, scanOj);
                }
                //== string
                if (ann.type() == EEcudTypes.STRING) {
                    v = getTextPicker(eField, scanOj);
                }
                //== int
                if (ann.type() == EEcudTypes.INT) {
                    v = getIntPicker(eField, scanOj);
                }
                //== int px
                if (ann.type() == EEcudTypes.INT_PX) {
                    v = getIntPxPicker(eField, scanOj);
                }
                //== gravity
                if (ann.type() == EEcudTypes.GRAVITY) {
                    v = getGravityPicker(eField, scanOj);
                }
                //== typeface
                if (ann.type() == EEcudTypes.TYPEFACE) {
                    v = getTypefacePicker(eField, scanOj);
                }
                //== NODE
                if (ann.type() == EEcudTypes.NODE) {
                    LinearLayout layN = new BuLayLinear_01(mContext)
                            .build();
                    try {
                        Object ojN = eField.get(scanOj);
                        recurs15(layN, ojN, level + 1);                   //<=== RECURSION
                        lay.addView(layN);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                //==
                if (v != null) {
                    lay0.addView(v);
                    //== суффиксы для некоторых параметров
                    if (ann.type() == EEcudTypes.INT_PX) {
                        new BuilderTV(mContext)
                                .addTo(lay0)
                                .text("dp")
                                .textColor(Color.GRAY)
                                .margins(7, 0, 0, 0)
                                .create();
                    }
                    if (ann.type() == EEcudTypes.COLOR_INT) {
                        int intColor = (int) G67G_Reflect.getValueByField(scanOj, eField);
                        new BuilderTV(mContext)
                                .addTo(lay0)
                                .text("(" + G67G_Draw.colorIntToString(intColor) + ")")
                                .textColor(Color.GRAY)
                                .margins(7, 0, 0, 0)
                                .create();
                    }

                }
            }
        }
    }

    private View getTypefacePicker(final Field field, final Object oj) {
        return null;
    }

    private View getGravityPicker(final Field field, final Object oj) {
        int gravityInt = (int) G67G_Reflect.getValueByField(oj, field);
        //===
        final EGravity.Cls[] clss = EGravity.asIGetStrings();
        int ixStart = EGravity.getIxByGravity(gravityInt);
        TextView picker = new BuPickerChoice(mContext)
                .elems(clss, new BuilderTV(mContext).textColor(Color.BLACK))
                .face(new BuilderTV(mContext).textColor(Color.BLACK))
                .onchoceMode(true)
                .startIndex(ixStart)
                .presenter(new BuPickerChoice.Presenter() {
                    @Override
                    public void result(int[] ixs) {
                        if (ixs.length > 0) {
                            int newVal = clss[ixs[0]].gravity;
                            G67G_Reflect.setValueByField(oj, field, newVal);
                        }
                    }
                })
                .create();
        //===
        return picker;
    }

    private View getIntPxPicker(final Field field, final Object oj) {
        int px = (int) G67G_Reflect.getValueByField(oj, field);
        int dp = G67G_Draw.dp(px);
        TextView tv = new BuilderTV(mContext)
                .text(dp + "")
                .textColor(Color.BLACK)
                .isPicker(EFcar.INTEGER, dp + "", "empty", new UniPresenter<String>() {
                            @Override
                            public void fun(String st) {
                                int dpNew = Integer.valueOf(st);
                                G67G_Reflect.setValueByField(oj, field, G67G_Draw.px(dpNew));
                            }
                        }, new TwoColors(Color.RED, null, 12), null, 0,
                        field.getName())
                .create();
        return tv;
    }

    private View getIntPicker(final Field field, final Object oj) {
        int val = (int) G67G_Reflect.getValueByField(oj, field);
        TextView tv = new BuilderTV(mContext)
                .text(val + "")
                .textColor(Color.BLACK)
                .isPicker(EFcar.INTEGER, val + "", "empty", new UniPresenter<String>() {
                            @Override
                            public void fun(String st) {
                                int i = Integer.valueOf(st);
                                G67G_Reflect.setValueByField(oj, field, i);
                            }
                        }, new TwoColors(Color.RED, null, 12), null, 0,
                        field.getName())
                .create();
        return tv;
    }

    private View getTextPicker(final Field field, final Object oj) {
        String val = (String) G67G_Reflect.getValueByField(oj, field);
        TextView tv = new BuilderTV(mContext)
                .text(val)
                .textColor(Color.BLACK)
                .isPicker(EFcar.TEXT,
                        val,
                        "empty",
                        new UniPresenter<String>() {
                            @Override
                            public void fun(String st) {
                                G67G_Reflect.setValueByField(oj, field, st);
                            }
                        },
                        new TwoColors(Color.RED, null, 12),
                        null,
                        0,
                        field.getName())
                .create();
        return tv;
    }

    private View getColorPicker(final Field field, final Object oj) {
        int color = (int) G67G_Reflect.getValueByField(oj, field);
        //===
        TextView vColorPicker = new BuilderTV(mContext)
                .text(color + "")
                .textColor(color)
                .onclick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int c2 = (int) G67G_Reflect.getValueByField(oj, field);
                        m17(c2, oj, field, v);
                    }
                })
                .create();
        //===
        return vColorPicker;
    }

    private void m17(final int color, final Object oj, final Field field, final View vColorPicker) {
        alertDialog = null;
        //===
        LinearLayout baseLay = new BuLayLinear_01(mContext)
                .paddings(16)
                .build();
        //===
        new BuilderTV(mContext)
                .addTo(baseLay)
                .text("Color-o-Matic")
                .textColor(Color.BLUE)
                .underline(true)
                .margins(0, 0, 0, 7)
                .onclick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new ColorOMaticDialog.Builder()
                                .initialColor(color)
                                .colorMode(ColorMode.ARGB)
                                .indicatorMode(IndicatorMode.HEX)
                                .onColorSelected(new OnColorSelectedListener() {
                                    @Override
                                    public void onColorSelected(@ColorInt int i) {
                                    }
                                })
                                .showColorIndicator(true)
                                .create()
                                .show(((AppCompatActivity) mContext).getSupportFragmentManager(), "ColorOMaticDialog");
                    }
                })
                .create();
        //===
        ScrollView view = EColors.getSelectView(mContext, color, new UniPresenter<Integer>() {
            @Override
            public void fun(Integer color) {
                if (alertDialog != null) {
                    ((TextView) vColorPicker).setTextColor(color);
                    ((TextView) vColorPicker).setText(color + "");
                    //===
                    G67G_Reflect.setValueByField(oj, field, color);
                    //===
                    alertDialog.dismiss();
                }
            }
        });
        baseLay.addView(view);
        //===
        AlertDialog.Builder b = new AlertDialog.Builder(mContext);
        b.setView(baseLay);
        b.setPositiveButton(EStrings._OK.val(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        b.setNegativeButton(EStrings._CANCEL.val(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog = b.create();
        alertDialog.show();


    }

    private FlexboxLayout getLay(String name, int level) {
        FlexboxLayout fl = new FlexboxLayout(mContext);
        fl.setFlexWrap(FlexboxLayout.FLEX_WRAP_WRAP);
        fl.setPadding(G67G_Draw.px(level * 16), 0, 0, 0);
        //===
        new BuilderTV(mContext)
                .addTo(fl)
                .text(name + ":")
                .textColor(Color.BLUE)
                .paddings(0, 0, 7, 0)
                .create();
        return fl;
    }

}

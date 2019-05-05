package ru.surrsoft.baaz.widgets2;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import ru.surrsoft.baaz.suite.figures.N1208_RectRound;
import ru.surrsoft.baaz.univers.EGravity;
import ru.surrsoft.baaz.univers.Roih;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;
import ru.surrsoft.baaz.widgets2.nviews.NLinearLayout;

/**
 * Текст в рамке вверху (_wtp), плюс текст под рамкой.
 * <p>
 * Создавать следует через {@link #createKewd()}
 * <p>
 * ЭЛЕМЕНТЫ
 * <li> _wtp - верхний виджет </li>
 * <li> _wdw - нижний виджет </li>
 * <li> _smx - рамка верхнего виджета </li>
 * <p>
 * <p>
 * https://docs.google.com/document/d/1E7Vu433YNYSL99mBBvEcXUWzoF_aoQmjBvQTUzU8UqU/edit?usp=sharing
 * <p>
 * [zipc]
 */
public class BuKewd_v_ extends BuLayLinear_01 {

    private TextView _wtp;
    private TextView _wdw;
    private String _wtp_text;
    private String _wdw_text;
    protected int _smx_color = Color.RED;
    protected int _wtp_textColor = Color.RED;
    protected int _wdw_textColor = Color.RED;
    protected int _wtp_textSize = 12;
    protected int _wdw_textSize = 12;
    protected int _smx_roundPrecent = 50;

    //==============================================================================================
    public BuKewd_v_(Context context) {
        super(context);
    }

    //builders
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public BuKewd_v_ kewd_textTop(String st) {
        _wtp_text = st;
        return this;
    }

    public BuKewd_v_ kewd_textDown(String st) {
        _wdw_text = st;
        return this;
    }

    public BuKewd_v_ kewd_color(int c) {
        _wdw_textColor = c;
        _wtp_textColor = c;
        _smx_color = c;
        return this;
    }


    //create
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public NLinearLayout createKewd() {

        //=== top widget
        _wtp = new BuilderTV(mContext)
                .text(_wtp_text)
                .textSize(_wtp_textSize)
                .textColor(_wtp_textColor)
                .textFont(Roih.get(Roih.TF_CYR_PLAY_BOLD))
                .gravitySelf(Gravity.CENTER_HORIZONTAL)
                .gravityIn(Gravity.CENTER_HORIZONTAL)
                .wMin(60)
                .drawer(new N1208_RectRound()
                        .roundsPercent(_smx_roundPrecent)
                        .colorStroke(ColorStateList.valueOf(_smx_color))
                )
                .paddings(7, 5, 7, 5)
                .create();

        //=== down widget
        _wdw = new BuilderTV(mContext)
                .text(_wdw_text)
                .textColor(_wdw_textColor)
                .textSize(_wdw_textSize)
                .textFont(Roih.get(Roih.TF_CYR_ROBOTO_LIGHT))
                .gravitySelf(EGravity.CH.val)
                .gravityIn(EGravity.CH.val)
                .create();

        //===
        return this
                .addChild(_wtp)
                .addChild(_wdw)
                .wWC()
                .build();
    }

    //commands
    //``````````````````````````````````````````````````````````````````````````````````````````````

    /**
     * Текст для _wtp
     *
     * @param st --
     */
    public void _setTextTop(String st) {
        _wtp.setText(st);
    }

    /**
     * Текст для _wdw
     *
     * @param st --
     */
    public void _setTextDown_kewd(String st) {
        _wdw.setText(st);
    }

}

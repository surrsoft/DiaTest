package ru.surrsoft.baaz.widgets2;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import ru.surrsoft.baaz.suite.figures.N1208_RectRound;
import ru.surrsoft.baaz.univers.Roih;

/**
 * Текст в прямоугольнике
 * <p>
 * https://docs.google.com/document/d/1KdU1sV_6XqAmlHRhNspcRiI-2h8CVI6_bgACpRq58gE/edit?usp=sharing
 * <p>
 * [zipc]
 */
public class BuCyno_v_ extends BuilderTV {
    protected int color_cyno = Color.RED;
    protected int textSize_cyno = 12;
    protected int roundsPrecent_cyno = 50;
    protected int width_cyno = 60;

    //==============================================================================================

    //constructors
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public BuCyno_v_(Context context) {
        super(context);
    }

    //builders
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public BuCyno_v_ cyno_color(int c) {
        color_cyno = c;
        return this;
    }

    //create
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public TextView createCyno() {

        return this
                .textSize(textSize_cyno)
                .textColor(color_cyno)
                .textFont(Roih.get(Roih.TF_CYR_PLAY_BOLD))
                .gravityIn(Gravity.CENTER_HORIZONTAL)
                .drawer(new N1208_RectRound()
                        .roundsPercent(roundsPrecent_cyno)
                        .colorStroke(ColorStateList.valueOf(color_cyno))
                )
                .paddings(7, 5, 7, 5)
                .w(width_cyno)
                .create();

    }
}

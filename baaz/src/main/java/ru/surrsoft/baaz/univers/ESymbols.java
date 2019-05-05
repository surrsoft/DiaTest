package ru.surrsoft.baaz.univers;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import ru.surrsoft.baaz.Bysa_01;

/**
 * #version 2 23-04-2016  #id [[w199w]]
 */
public enum ESymbols {
    /**
     * Символ: "в виде ручки"
     */
    HAND(getTypeface(1), "\uF040"),
    /**
     * Символ: "в виде галочки"
     */
    GALKA(getTypeface(1), "\uF00C"),
    /**
     * В виде крестика (как буква "X")
     */
    KRESTIK(getTypeface(1), "\uF057"),
    KRESTIK_2(getTypeface(1), "\uF00D"),
    KRESTIK_3(getTypeface(2), "\u274C"),
    ARROW_UP(getTypeface(1), "\uF062"),
    ARROW_DOWN(getTypeface(1), "\uF063"),
    SORT_AZ(getTypeface(1), "\uF15D"),
    SORT_ZA(getTypeface(1), "\uF15E"),
    SORT_AZ_2(getTypeface(1), "\uF160"),
    SORT_ZA_2(getTypeface(1), "\uF161"),
    /**
     * В виде знака вопроса
     */
    HELP(getTypeface(1), "\uF059");

    public final String text;
    public final Typeface typeface;

    ESymbols(Typeface typeface, String text) {
        this.text = text;
        this.typeface = typeface;
    }

    public static Typeface getTypeface(int i) {
        AssetManager assets = Bysa_01.appContext.getAssets();
        if (i == 1) return Typeface.createFromAsset(assets, "fonts/ic_fontawesome-webfont.ttf");
        if (i == 2) return Typeface.createFromAsset(assets, "fonts/ic_mfglabsiconset-webfont.ttf");
        return null;
    }

    /**
     * Получение слоя содержащего все символы представленные в текущем объекте
     *
     * @return
     */
    public static LinearLayout getLayAllSymbols() {
        LinearLayout lay = new LinearLayout(Bysa_01.appContext);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lay.setLayoutParams(lp);
        lay.setOrientation(LinearLayout.VERTICAL);

        for (ESymbols elem : ESymbols.values()) {
            TextView tvTitle = new TextView(Bysa_01.appContext);
            tvTitle.setText(elem.name());
            lay.addView(tvTitle);

            TextView tv = new TextView(Bysa_01.appContext);
            tv.setText(elem.text);
            tv.setTypeface(elem.typeface);
            tv.setTextSize(20);
            lay.addView(tv);
        }

        return lay;
    }

}

package ru.surrsoft.baaz.univers;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import ru.surrsoft.baaz.Bysa_01;

/**
 * [[w199w]]
 * <p/>
 * #version 1 30-04-2016  #id [[w203w]]
 */
public enum ETypefaces {
    T1_SEGOEUIL(getTypeface(1)),
    T2_MONSERATT_REGULAR(getTypeface(2)),
    T3_1_COMFORTAA_REGULAR(getTypeface(3)),
    T3_2_COMFORTAA_LIGHT(getTypeface(4)),
    T3_3_COMFORTAA_BOLD(getTypeface(5)),
    T4_ANDIKA(getTypeface(6)),
    T5_1_ARIMO_REGULAR(getTypeface(7)),
    T5_2_ARIMO_ITALIC(getTypeface(8)),
    T5_3_ARIMO_BOLD(getTypeface(9)),
    T5_4_ARIMO_BOLDITALIC(getTypeface(10)),
    T6_DIDACT_GOTHIC(getTypeface(11)),
    T7_FORUM(getTypeface(12)),
    T8_1_ISTOKWEB_REGULAR(getTypeface(14)),
    T8_2_ISTOKWEB_ITALIC(getTypeface(15)),
    T8_3_ISTOKWEB_BOLD(getTypeface(16)),
    T8_4_ISTOKWEB_BOLDITALIC(getTypeface(17)),
    T9_KURALE(getTypeface(18)),
    T10_1_PLAY_REGULAR(getTypeface(19)),
    T10_2_PLAY_BOLD(getTypeface(20)),
    T11_1_ROBOTO_BOLDITALIC(getTypeface(21)),
    T11_2_ROBOTO_CONDENSED(getTypeface(22)),
    T11_3_ROBOTO_LIGHT(getTypeface(23)),
    T11_4_ROBOTO_MEDIUM(getTypeface(24)),
    T11_5_ROBOTO_REGULAR(getTypeface(25)),
    T11_6_ROBOTO_THINITALIC(getTypeface(26)),;

    public final Typeface val;

    ETypefaces(Typeface typeface) {
        this.val = typeface;
    }

    public static Typeface getTypeface(int i) {
        AssetManager assets = Bysa_01.appContext.getAssets();
        if (i == 26) return Typeface.createFromAsset(assets, "fonts/Roboto/Roboto_ThinItalic.ttf");
        if (i == 25) return Typeface.createFromAsset(assets, "fonts/Roboto/Roboto_Regular.ttf");
        if (i == 24) return Typeface.createFromAsset(assets, "fonts/Roboto/Roboto_Medium.ttf");
        if (i == 23) return Typeface.createFromAsset(assets, "fonts/Roboto/Roboto_Light.ttf");
        if (i == 22) return Typeface.createFromAsset(assets, "fonts/Roboto/Roboto_Condensed.ttf");
        if (i == 21) return Typeface.createFromAsset(assets, "fonts/Roboto/Roboto_Bolditalic.ttf");
        if (i == 20) return Typeface.createFromAsset(assets, "fonts/Play/Play-Bold.ttf");
        if (i == 19) return Typeface.createFromAsset(assets, "fonts/Play/Play-Regular.ttf");
        if (i == 18) return Typeface.createFromAsset(assets, "fonts/Kurale/Kurale-Regular.ttf");
        if (i == 17) return Typeface.createFromAsset(assets, "fonts/IstokWeb/IstokWeb-BoldItalic.ttf");
        if (i == 16) return Typeface.createFromAsset(assets, "fonts/IstokWeb/IstokWeb-Bold.ttf");
        if (i == 15) return Typeface.createFromAsset(assets, "fonts/IstokWeb/IstokWeb-Italic.ttf");
        if (i == 14) return Typeface.createFromAsset(assets, "fonts/IstokWeb/IstokWeb-Regular.ttf");
        if (i == 12) return Typeface.createFromAsset(assets, "fonts/Forum/Forum-Regular.ttf");
        if (i == 11) return Typeface.createFromAsset(assets, "fonts/DidactGothic/DidactGothic.ttf");
        if (i == 10) return Typeface.createFromAsset(assets, "fonts/Arimo/Arimo-BoldItalic.ttf");
        if (i == 9) return Typeface.createFromAsset(assets, "fonts/Arimo/Arimo-Bold.ttf");
        if (i == 8) return Typeface.createFromAsset(assets, "fonts/Arimo/Arimo-Italic.ttf");
        if (i == 7) return Typeface.createFromAsset(assets, "fonts/Arimo/Arimo-Regular.ttf");
        if (i == 6) return Typeface.createFromAsset(assets, "fonts/Andika/Andika-R.ttf");
        if (i == 5) return Typeface.createFromAsset(assets, "fonts/Comfortaa/Comfortaa-Bold.ttf");
        if (i == 4) return Typeface.createFromAsset(assets, "fonts/Comfortaa/Comfortaa-Light.ttf");
        if (i == 3) return Typeface.createFromAsset(assets, "fonts/Comfortaa/Comfortaa-Regular.ttf");
        if (i == 2) return Typeface.createFromAsset(assets, "fonts/Montserrat/Montserrat-Regular.ttf");
        if (i == 1) return Typeface.createFromAsset(assets, "fonts/Segoeuil/Segoeuil.ttf");
        return null;
    }

    /**
     * Получение слоя содержащего все символы представленные в текущем объекте
     *
     * @return
     */
    public static LinearLayout getLayAllTypefaces() {
        LinearLayout lay = new LinearLayout(Bysa_01.appContext);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lay.setLayoutParams(lp);
        lay.setOrientation(LinearLayout.VERTICAL);

        for (ETypefaces elem : ETypefaces.values()) {
            TextView tvTitle = new TextView(Bysa_01.appContext);
            tvTitle.setText(elem.name());
            lay.addView(tvTitle);

            TextView tv = new TextView(Bysa_01.appContext);
            tv.setText("Пример текста");
            tv.setTextColor(Color.BLACK);
            tv.setTypeface(elem.val);
            tv.setTextSize(20);
            lay.addView(tv);
        }

        return lay;
    }

    public static int getIxByETypeface(ETypefaces e) {
        int ret = 0;
        ETypefaces[] values = ETypefaces.values();
        for (int i = 0; i < values.length; i++) {
            ETypefaces elem = values[i];
            if (elem == e) {
                ret = i;
                break;
            }
        }
        return ret;
    }

}

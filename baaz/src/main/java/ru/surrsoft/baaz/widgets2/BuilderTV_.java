package ru.surrsoft.baaz.widgets2;

import android.graphics.Color;
import android.widget.TextView;

import ru.surrsoft.baaz.Bysa_01;
import ru.surrsoft.baaz.cls_c.G67G_Draw;

/**
 * #version 1 10.07.2016  #id [[w293w]]
 */
public class BuilderTV_ {

    public static TextView create(String text) {
        return create(text, Color.DKGRAY);
    }

    public static TextView create(String text, int textColor) {
        TextView tv = new TextView(Bysa_01.appContext);
        tv.setText(text);
        tv.setTextColor(textColor);
        return tv;
    }

    public static TextView create(String text, int textColor, int paddingLeftRight_db) {
        TextView tv = BuilderTV_.create(text, textColor);
        int px = G67G_Draw.px(paddingLeftRight_db);
        tv.setPadding(px, 0, px, 0);
        return tv;
    }

}

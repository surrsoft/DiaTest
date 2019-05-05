package ru.surrsoft.baaz.widgets.other;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import ru.surrsoft.baaz.univers.TwoColors;

/**
 * Пара "_name:_val", где "_name" это {@link N2229_ShortTextHint}, "_val" это {@link TextView}
 * <p/>
 * Элементы: _name, _val
 * <p/>
 * Обязателен вызов {@link #yCommit(N2230_Configs)}
 * <p/>
 * #version 1.0.1 15-12-2016  #id [[w230w]]
 */
public class N2230_ShortTextHintPlus extends LinearLayout {
    public N2230_Configs _configs;
    private N2229_ShortTextHint _name;
    private TextView _val;

    public N2230_ShortTextHintPlus(Context context) {
        super(context);
        init(context);
    }

    public N2230_ShortTextHintPlus(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public N2230_ShortTextHintPlus(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.setOrientation(LinearLayout.HORIZONTAL);

        _name = new N2229_ShortTextHint(context);
        _val = new TextView(context);

    }

    public void yCommit(N2230_Configs cnf) {
        if (cnf == null) {
            _configs = new N2230_Configs();
        } else {
            _configs = cnf;
        }
        //===
        N2229_ShortTextHint.N2229_Configs c = new N2229_ShortTextHint.N2229_Configs();
        c._tc_this = _configs._name_tca;
        c._tc_dialogTitle = _configs._name_dialogTitle_tca;
        c._tc_dialogBody = _configs._name_dialogBody_tca;
        c._text_this = _configs.texts[1];
        c._text_dialogBody = _configs.texts[2];
        _name.yCommit(c);
        //===
        _configs._val_tca.apply(_val);
        _val.setText(_configs.texts[0]);
        //===
        this.addView(_name);
        this.addView(_val);
    }

    public static class N2230_Configs {
        public TwoColors _val_tca = new TwoColors(Color.BLUE, null, null, 18);
        public TwoColors _name_tca = new TwoColors(Color.BLUE, null, null, 18)
                .underline();
        public TwoColors _name_dialogTitle_tca = new TwoColors(Color.BLUE, null, null, 18);
        public TwoColors _name_dialogBody_tca = new TwoColors(Color.BLUE, null, null, 18);
        /**
         * Три текста: "текст для _val", "текст для _name в слое", "текст для тела _name в диалоге"
         */
        public String[] texts = new String[]{"_val", "N1", "N2"};
    }

    /**
     * Установка текста _val
     *
     * @param text
     */
    public void ySetValText(String text) {
        _configs.texts[0] = text;
        this.yCommit(_configs);
    }
}

package ru.surrsoft.baaz.widgets.other;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.univers.TwoColors;

/**
 * TextView при нажатии на который появляется диалог с подсказкой
 * <p/>
 * Элементы: _this, _dialogTitle, _dialogBody
 * <p/>
 * Обязателен вызов {@link #yCommit(N2229_Configs)}
 * <p/>
 * #version 1 17-04-2016  #id [[w229w]]
 */
public class N2229_ShortTextHint extends android.support.v7.widget.AppCompatTextView {



    public N2229_Configs _configs;

    //1 //constructors ----------------------------------------------------------------------------
    public N2229_ShortTextHint(Context context) {
        super(context);
        init(context);
    }

    public N2229_ShortTextHint(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public N2229_ShortTextHint(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    //2 //constructors ----------------------------------------------------------------------------

    private void init(final Context ctx) {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                LinearLayout lay = new LinearLayout(ctx);
                lay.setOrientation(LinearLayout.VERTICAL);
                int px = G67G_Draw.px(24);
                lay.setPadding(px, px, px, px);

                TextView dialogTitle = new TextView(ctx);
                TextView dialogBody = new TextView(ctx);
                dialogBody.setPadding(0, G67G_Draw.px(20), 0, 0);


                dialogTitle.setText(_configs._text_this);
                dialogBody.setText(_configs._text_dialogBody);

                if (_configs._tc_dialogTitle != null) {
                    _configs._tc_dialogTitle.apply(dialogTitle);
                }
                if (_configs._tc_dialogBody != null) {
                    _configs._tc_dialogBody.apply(dialogBody);
                }

                lay.addView(dialogTitle);
                lay.addView(dialogBody);

                AlertDialog.Builder b = new AlertDialog.Builder(ctx);
                b.setView(lay);
                b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                b.create().show();
            }
        });
    }

    public void yCommit(N2229_Configs configs) {
        if (configs == null) throw new IllegalStateException("(!!!) необходимо задать configs");
        _configs = configs;

        this.setClickable(true);
        //===
        this.setText(_configs._text_this);
        if (_configs._tc_this != null) {
            _configs._tc_this.apply(this);
        }
    }

    public static class N2229_Configs {
        /**
         * Текст задавать не следует - см. отдельное поле {@link #_text_this}
         */
        public TwoColors _tc_this = new TwoColors(Color.BLUE, null, null, 18);
        /**
         * Текст задавать не следует
         */
        public TwoColors _tc_dialogTitle = new TwoColors(Color.BLUE, null, null, 18);
        /**
         * Текст задавать не следует - см. отдельное поле {@link #_text_dialogBody}
         */
        public TwoColors _tc_dialogBody = new TwoColors(Color.BLUE, null, null, 18);

        public String _text_this = null;
        public String _text_dialogBody = null;
    }
}

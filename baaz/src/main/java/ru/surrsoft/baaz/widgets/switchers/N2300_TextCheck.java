package ru.surrsoft.baaz.widgets.switchers;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import ru.surrsoft.baaz.cls_c.G67G;
import ru.surrsoft.baaz.univers.TwoColors;

/**
 * TextView с двумя состояниями, с переключением между ними при нажатии пользователя.
 * <p>
 * Состояния определяются классом {@link TwoColors} и хранятся в переменных {@link #_tc_state1} и {@link #_tc_state2}  .
 * Объект обратного вызова следует помещать в переменную {@link #_callback} . При программном задании
 * состояния callback не выполняется. Текущее состояние
 * следует задавать через переменную {@link #_currState} константами {@link #STATE_1}, {@link #STATE_2}.
 * Применение параметров инициируется методом {@link #yCommit()}.
 * <p>
 * Архитектура класса - [w148w].
 * <p>
 * [w145w] - описание в Trooget
 */
public abstract class N2300_TextCheck extends android.support.v7.widget.AppCompatTextView {
    public static final int STATE_1 = 1;
    public static final int STATE_2 = 2;


    public int _currState = STATE_1;
    public TwoColors _tc_state1;
    public TwoColors _tc_state2;
    public Callback _callback = null;

    public N2300_TextCheck(Context context) {
        super(context);
        init(context);
    }

    public N2300_TextCheck(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public N2300_TextCheck(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public interface Callback {
        /**
         *
         * @param state (1) -- состояние которое было в момент нажатия на элементе
         * @return объект обратного вызова должен вернуть FALSE если состояние текущего элемента
         * не должно переключиться, иначе TRUE
         */
        boolean call(int state);
    }

    public abstract void initExternal();

    private void init(Context context) {
        this.setClickable(true);
        Typeface r57 = Typeface.createFromAsset(context.getAssets(), G67G.tf1_roboto_light);
        _tc_state1 = new TwoColors(Color.BLUE, null, r57, 6).text("text_state1");
        _tc_state2 = new TwoColors(Color.RED, null, r57, 6).text("text_state2");
        initExternal();
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean call = true;
                if (_callback != null) {
                    call = _callback.call(_currState);
                }
                if (!call) return; //======X не переход в другое состяние если обратный вызов вернул false
                if (_currState == STATE_1) {
                    _currState = STATE_2;
                } else if (_currState == STATE_2) {
                    _currState = STATE_1;
                }
                yCommit();
            }
        });
        yCommit();
    }

    public void yCommit() {
        if (_currState == STATE_1) {
            _tc_state1.apply(this);
        }
        if (_currState == STATE_2) {
            _tc_state2.apply(this);
        }
    }
}

package ru.surrsoft.baaz.widgets.switchers;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import java.util.Observable;
import java.util.Observer;

import ru.surrsoft.baaz.univers.TwoColors;

/**
 * Кнопка на базе TextView с двумя состояниями (state1, state2) между которому происходит переключение
 * <p/>
 * Внешний вид управляется через переменные {@link #_confTCState1} и {@link #_confTCState2}.
 * Можно например делать так: <code>btn._confTCState1.setColor(Color.BLUE)</code> - в этом случае
 * цвет в нормальном состоянии будет синий
 * <p/>
 * #version 1.0.1-2 10-04-2016  #id [[w187w]]
 */
public class N2187_BtnFontTwoState extends android.support.v7.widget.AppCompatTextView implements Observer {


    public static final int STATE_1 = 1;
    public static final int STATE_2 = 2;

    public YAgent _agent;
    public N2187_IPresenter _presenter;
    public int _state;

    public TwoColors _confTCState1;
    public TwoColors _confTCState2;

    public interface Callback {
        void callback(int state);
    }

    public interface N2187_IPresenter {
        /**
         * Выполняется при нажатии на элементе
         *
         * @param state (1) -- состояние после нажатия
         */
        void onClick(int state);
    }

    public interface YAgent {
        /**
         * @return {@link #STATE_1} : если текущее состояние state1;
         * {@link #STATE_2} : если текущее состояние должно быть state2
         */
        int getState_();

        /**
         * Вызывается при нажатии на текущий элемент
         *
         * @return
         */
        Callback getCallback();
    }

    //1 //constructor
    public N2187_BtnFontTwoState(Context context) {
        super(context);
        init(context);
    }

    public N2187_BtnFontTwoState(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public N2187_BtnFontTwoState(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    //2 //constructor

    private void init(Context context) {
        Typeface tf = Typeface.createFromAsset(context.getAssets(), ESymbols48.EDIT.typefacePath1);
        _confTCState1 = new TwoColors(Color.RED, null,
                tf, 16)
                .text(ESymbols48.EDIT.text1);
        _confTCState2 = new TwoColors(Color.RED, null,
                tf, 16)
                .text(ESymbols48.EDIT.text2);
    }

    public void yCommit() {
        int stateX = STATE_1;
        if (_agent == null) {
            if (_presenter == null) {
                return;
            } else {
                stateX = _state;
            }
        } else {
            stateX = _agent.getState_();
        }

        applyAppearance(stateX);
        _state = stateX;
        if (_agent != null && _agent.getCallback() != null) {
            this.setClickable(true);
            this.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    _agent.getCallback().callback(_state);
                }
            });
        }
        if (_presenter != null) {
            this.setClickable(true);
            this.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (_state == STATE_1) _state = STATE_2;
                    else _state = STATE_1;
                    applyAppearance(_state);
                    _presenter.onClick(_state);
                }
            });
        }
    }

    private void applyAppearance(int state) {
        if (state != STATE_1 && state != STATE_2) return;
        if (state == STATE_1 && _confTCState1 != null) {
            _confTCState1.apply(this);
        }
        if (state == STATE_2 && _confTCState2 != null) {
            _confTCState2.apply(this);
        }
    }

    @Override
    public void update(Observable observable, Object data) {
        yCommit();
    }

    public enum ESymbols48 {
        /**
         * Символы: "в виде ручки" / "в виде галочки"
         */
        EDIT("fonts/ic_fontawesome-webfont.ttf", "\uF040", "fonts/ic_fontawesome-webfont.ttf", "\uF00C");

        public final String typefacePath1;
        public final String typefacePath2;
        public final String text1;
        public final String text2;

        ESymbols48(String typefacePath1, String text1, String typefacePath2, String text2) {
            this.typefacePath1 = typefacePath1;
            this.typefacePath2 = typefacePath2;
            this.text1 = text1;
            this.text2 = text2;
        }
    }

    public static class DefaultAgent implements YAgent {
        protected int _state = N2187_BtnFontTwoState.STATE_1;

        @Override
        public int getState_() {
            return _state;
        }

        @Override
        public Callback getCallback() {
            return null;
        }
    }
}

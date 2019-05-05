package ru.surrsoft.baaz.widgets.datainput;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import ru.surrsoft.baaz.Bysa_01;
import ru.surrsoft.baaz.R;
import ru.surrsoft.baaz.univers.TwoColors;
import ru.surrsoft.baaz.widgets.switchers.N2187_BtnFontTwoState;

/**
 * EditText с кнопкой справа, изначально неактивный. Редактируется после нажатия самого EditText или кнопки справа
 * <p/>
 * Приспособлен как к шаблону MVP1, так и к шаблону MAVD
 * <p/>
 * Состоит из 4х элементов: _edit, _btn, _title, _comm. Для отображения _title и _comm необходимо чтобы для них был задан текст
 * <p/>
 * Может находиться в двух состояниях: {@link #STATE_1_EDIT} и {@link #STATE_2_NOEDIT}
 * <p/>
 * "Дергает" _agent.setText() при каждом изменении в строке ввода
 * <p/>
 * Внешний вид следует задавать через вложенный класс {@link N2186_ConfigsApply}
 * <p/>
 * Компоненты: layout/n2186_lay.xml
 * <p/>
 * #version 3 21-05-2016  #id [[w186w]]
 */
public class N2186_EditTextClick extends RelativeLayout implements Observer {


    public static final int STATE_1_EDIT = 1;
    public static final int STATE_2_NOEDIT = 2;

    public N2186_Agent _agent;
    public N2186_IPresenter _presenter;
    public N2188_EditText _edit;
    public N2187_BtnFontTwoState _btn;
    private Context mContext;
    public int _state;
    private N2186_EditTextClick _th;
    public ObservableThis _obs;
    public int _confInputType = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS;
    private N2186_ConfigsApply _configs;
    private TextView _title;
    private TextView _comm;

    @Override
    public void update(Observable observable, Object data) {
        yCommit();
    }

    public interface N2186_IPresenter {
        /**
         * Передает Presenter-у текст по мере его набора
         *
         * @param text
         */
        void onTextLive(String text);

        /**
         * Вызывается при изменении фокуса
         *
         * @param focused TRUE если _edit перешел в режим редактирования; FALSE - если вышел из режима редактирвоания
         */
        void onFocusChanged(boolean focused);
    }

    public interface N2186_Agent {
        /**
         * Текст для _edit
         *
         * @return
         */
        String n2186_getText();

        /**
         * Текст из _edit
         *
         * @param text
         */
        void n2186_setText(String text);
    }

    //1 //constructors
    public N2186_EditTextClick(Context context) {
        super(context);
        init(context);
    }

    public N2186_EditTextClick(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public N2186_EditTextClick(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    //2 //constructors

    private void init(Context context) {
        mContext = context;
        //===
        _configs = new N2186_ConfigsApply();
        yConfigsCommit();
        //===
        _obs = new ObservableThis();
        //===
        LayoutInflater.from(context).inflate(R.layout.n2186_lay, this, true); //layout
        //===
        _edit = (N2188_EditText) findViewById(R.id.n2186_edit);
        _btn = (N2187_BtnFontTwoState) findViewById(R.id.n2186_btn);
        _title = (TextView) findViewById(R.id.n2186_title);
        _comm = (TextView) findViewById(R.id.n2186_comm);
        //===
        _obs.addObserver(_edit);
        _obs.addObserver(_btn);
        //===
        //при старте всегда не в режиме редактирования
        _state = STATE_2_NOEDIT;
        //===
        _edit.setInputType(_confInputType);
        //===
        _edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (_agent != null)
                    _agent.n2186_setText(s.toString());
                if (_presenter != null)
                    _presenter.onTextLive(s.toString());
            }
        });
        //=== реагирование на кнопку "ГОТОВО" клавиатуры
        _edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE && _th._state == STATE_1_EDIT) {
                    _th._state = STATE_2_NOEDIT;
                    yNotify();
                    handled = true;
                }
                return handled;
            }
        });
        //===
        _edit.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (_presenter != null) {
                    _presenter.onFocusChanged(hasFocus);
                }
                if (!hasFocus) {
                    _state = STATE_2_NOEDIT;
                    yCommit();
                }
            }
        });
        //===
    }

    /**
     * Задание текста для элемента _edit
     *
     * @param text
     */
    public void ySetText_Edit(String text) {
        _edit.setText(text);
    }

    private void yConfigsCommit() {
        if (_configs == null) throw new IllegalStateException("(!!!) _configs не должен быть null");

        Typeface tf = Typeface.createFromAsset(Bysa_01.appContext.getAssets(),
                N2187_BtnFontTwoState.ESymbols48.EDIT.typefacePath1);
        _configs._btnState1_tc = new TwoColors(_configs.btn_colorState1, null, tf, 16)
                .text(N2187_BtnFontTwoState.ESymbols48.EDIT.text1);
        _configs._btnState2_tc = new TwoColors(_configs.btn_colorState2, null, tf, 16)
                .text(N2187_BtnFontTwoState.ESymbols48.EDIT.text2);

    }

    public void yCommit() {
        yConfigsCommit();
        //===
        _th = this;
        //===
        if (_configs._edit_tc != null) _configs._edit_tc.apply(_edit);
        if (_configs._comm_tc != null) _configs._comm_tc.apply(_comm);
        if (_configs._btnState1_tc != null) _btn._confTCState1 = _configs._btnState1_tc;
        if (_configs._btnState2_tc != null) _btn._confTCState2 = _configs._btnState2_tc;
        //===
        //===
        _edit._agent1 = new EditAgent();
        _edit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_state == STATE_2_NOEDIT) {
                    _state = STATE_1_EDIT;
                    yNotify();
                }
            }
        });
        //
        _btn._agent = new BtnAgent();
        //
        yNotify();
    }

    private void yNotify() {
        _obs.setChanged();
        _obs.notifyObservers();
    }

    /**
     * Агент _edit
     */
    class EditAgent implements N2188_EditText.YAgent1 {

        @Override
        public int getState_1() {
            return _state;
        }

        @Override
        public String getText_1() {
            if (_agent != null)
                return _agent.n2186_getText();
            return _edit.getText().toString();
        }

        @Override
        public N2188_EditText.N2188CallbackBackpress getN2188CallbackBackpress() {
            return new N2188_EditText.N2188CallbackBackpress() {
                @Override
                public void n2188callbackBackpress() {
                    _th._state = STATE_2_NOEDIT;
                    yNotify();
                }
            };
        }
    }

    /**
     * Агент _btn
     */
    class BtnAgent extends N2187_BtnFontTwoState.DefaultAgent {
        @Override
        public int getState_() {
            int i;
            if (_th._state == N2186_EditTextClick.STATE_1_EDIT) {
                i = N2187_BtnFontTwoState.STATE_2;
            } else {
                i = N2187_BtnFontTwoState.STATE_1;
            }
            return i;
        }

        @Override
        public N2187_BtnFontTwoState.Callback getCallback() {
            return new N2187_BtnFontTwoState.Callback() {
                @Override
                public void callback(int state) {
                    if (state == N2187_BtnFontTwoState.STATE_1) {
                        _th._state = N2186_EditTextClick.STATE_1_EDIT;
                    } else {
                        _th._state = N2186_EditTextClick.STATE_2_NOEDIT;
                    }
                    yNotify();
                }
            };
        }
    }

    class ObservableThis extends Observable {
        @Override
        protected void setChanged() {
            super.setChanged();
        }
    }

    /**
     * Элементы
     * <li>_edit - текстовое поле ввода</li>
     * <li>_btn - кнопка</li>
     */
    public static class N2186_ConfigsApply {

        //=== default values
        private int btn_colorState1 = Color.RED;
        private int btn_colorState2 = Color.BLUE;


        //===
        public N2186_ConfigsApply setBtn_colorState1(int btn_colorState1) {
            this.btn_colorState1 = btn_colorState1;
            return this;
        }

        public N2186_ConfigsApply setBtn_colorState2(int btn_colorState2) {
            this.btn_colorState2 = btn_colorState2;
            return this;
        }

        //=== local used variables
        /**
         * Текст задавать не следует
         */
        public TwoColors _edit_tc = new TwoColors(Color.BLUE, null, null, 20);
        private TwoColors _btnState1_tc;
        private TwoColors _btnState2_tc;
        public TwoColors _title_tc = new TwoColors(Color.BLUE, null, null, 22);
        public TwoColors _comm_tc = new TwoColors(Color.GRAY, null, null, 14);

    }

    public void ySetConfigs(N2186_ConfigsApply cfg) {
        _configs = cfg;
        _title.setVisibility(View.VISIBLE);
        if (cfg._title_tc == null || cfg._title_tc.getText() == null || cfg._title_tc.getText().length() == 0) {
            _title.setVisibility(View.GONE);
        } else {
            cfg._title_tc.apply(_title);
        }
        _comm.setVisibility(View.VISIBLE);
        if (cfg._comm_tc == null || cfg._comm_tc.getText() == null || cfg._comm_tc.getText().length() == 0) {
            _comm.setVisibility(View.GONE);
            cfg._comm_tc.apply(_comm);
        }
    }

    /**
     * Получение текста из _edit
     * @return --
     */
    public String yGetEdit_text(){
        return _edit.getText().toString();
    }

}

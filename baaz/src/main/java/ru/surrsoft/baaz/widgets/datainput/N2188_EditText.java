package ru.surrsoft.baaz.widgets.datainput;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;

import java.util.Observable;
import java.util.Observer;

import ru.surrsoft.baaz.cls_c.G67G;
import ru.surrsoft.baaz.cls_c.Log2_01;

/**
 * Обычный EditText приспособленный для шаблона [MAVD].
 * <p>
 * Также предусмотрено 2 состояния: {@link #STATE_1_EDIT} и {@link #STATE_2_NOEDIT}
 * <p>
 * [[w188w]]
 */
public class N2188_EditText extends android.support.v7.widget.AppCompatEditText implements Observer {
    private static final String TAG = ":N2188_EditText";


    public static final int STATE_1_EDIT = 1;
    public static final int STATE_2_NOEDIT = 2;

    public YAgent1 _agent1;
    private Context mContext;

    public interface N2188CallbackBackpress {
        void n2188callbackBackpress();
    }

    public interface YAgent1 {
        int getState_1();

        String getText_1();

        /**
         * То что вызывается при нажатии
         *
         * @return
         */
        N2188CallbackBackpress getN2188CallbackBackpress();
    }

    //1 //constructors
    public N2188_EditText(Context context) {
        super(context);
        init(context);
    }

    public N2188_EditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public N2188_EditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    //2 //constructors

    private void init(Context context) {
        mContext = context;
    }

    public void yCommit() {
        if (_agent1 != null) {
            this.setText(_agent1.getText_1());
            if (_agent1.getState_1() == STATE_1_EDIT) {
                editEnable(true);
            }
            if (_agent1.getState_1() == STATE_2_NOEDIT) {
                editEnable(false);
            }
        }
    }

    @Override
    public void update(Observable observable, Object data) {
        yCommit();
    }

    private void editEnable(boolean b) {
        if (b) {
            this.setFocusable(true);
            this.setFocusableInTouchMode(true);
            this.setEnabled(true);
            if (!isInEditMode()) {
                G67G.Misc.keyboardShow(this, mContext);
            }
        } else {
            this.setFocusable(false);
            this.setFocusableInTouchMode(false);
            if (!isInEditMode()) {
                G67G.Misc.keyboardHide(this, mContext);
            }
        }
    }

    //перехват нажатия hard key back и menu
    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Log2_01.i(TAG, "--> onKeyPreIme()", Log2_01.CONF_LOG1);
            Log2_01.i(TAG, "-- keyCode=" + keyCode, Log2_01.CONF_LOG1);
            if (_agent1 != null && _agent1.getState_1() == STATE_1_EDIT) {
                _agent1.getN2188CallbackBackpress().n2188callbackBackpress();
            }
        } else if (keyCode == KeyEvent.KEYCODE_MENU) {
            // Поглощение события
            return true;
        }
        return false;
    }
}

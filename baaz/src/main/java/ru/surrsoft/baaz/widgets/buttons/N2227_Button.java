package ru.surrsoft.baaz.widgets.buttons;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Обычная кнопка в части оформления. При нажатии вызывает метод у {@link #_presenter}
 * <p/>
 * #versoin 1 14-04-2016  #id [[u227u]]
 */
public class N2227_Button extends android.support.v7.widget.AppCompatButton {
    //1 //constructors
    public N2227_Button(Context context) {
        super(context);
        init();
    }

    public N2227_Button(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public N2227_Button(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    //2 //constructors

    public N2227_IPresenter _presenter;

    public interface N2227_IPresenter {
        /**
         * Нажатие. Не выполняется если this  в состоянии disable
         */
        void n2227_onClick();
    }


    private void init() {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_presenter != null) {
                    _presenter.n2227_onClick();
                }
            }
        });
    }

}

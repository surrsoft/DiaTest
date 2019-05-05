package ru.surrsoft.baaz.widgets2.nviews;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Расширяет обычный CheckBox для целей:
 * <li> - метод {@link #setCheckedSilent(boolean)} позволяет задать "флаг" без срабатывания
 * МОВ OnCheckedChangeListener </li>
 * <li> - метод {@link #getOnCheckedChangeListener()} позволяет получить назад ссылку на
 * установленный слушатель OnCheckedChangeListener</li>
 * <p>
 * #version 1 03.12.2016  #id [[w405w]]
 */
public class NCheckBox extends android.support.v7.widget.AppCompatCheckBox {
    private OnCheckedChangeListener mOnCheckedChangeListener;
    private Object mTag2;

    public NCheckBox(Context context) {
        super(context);
    }

    public NCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        super.setOnCheckedChangeListener(listener);
        mOnCheckedChangeListener = listener;
    }

    public OnCheckedChangeListener getOnCheckedChangeListener() {
        return mOnCheckedChangeListener;
    }

    /**
     * Позволяет установить/снять "флаг" без срабатывания OnCheckedChangeListener
     *
     * @param checked (1) --
     */
    public void setCheckedSilent(boolean checked) {
        OnCheckedChangeListener lst = mOnCheckedChangeListener;
        setOnCheckedChangeListener(null);
        setChecked(checked);
        setOnCheckedChangeListener(lst);
    }

    public void setTag2(Object oj) {
        mTag2 = oj;
    }

    public Object getTag2() {
        return mTag2;
    }
}

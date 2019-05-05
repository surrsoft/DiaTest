package ru.surrsoft.baaz.widgets.switchers;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import org.apache.commons.lang3.ArrayUtils;

import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.configs.TextConfigs;
import ru.surrsoft.baaz.univers.Color2;
import ru.surrsoft.baaz.univers.EStrings;
import ru.surrsoft.baaz.univers.IApply;
import ru.surrsoft.baaz.univers.StringL;

/**
 * TextView с состояниями, с циклическим переключениме между ними при нажатиях
 * <p/>
 * ТЕРМИНЫ
 * <li> _state (_состояние) - </li>
 * СЦЕНАРИИ
 * <li> инициализация -- настройка {@link #_cfg} -- задание {@link #_prestenter}
 * -- {@link #yCommit()}    </li>
 * <p/>
 * #version 1 13.07.2016  #id [[w302w]]
 */
public class N2302_TextViewSwitcher extends android.support.v7.widget.AppCompatTextView {

    private static final String TAG = ":" + N2302_TextViewSwitcher.class.getSimpleName();


    public N2302_Configs _cfg;
    public N2302_Presenter _prestenter;

    private int yCurrStateIndex;
    private boolean yFirst = true;

    public interface N2302_Presenter {
        /**
         * Вызывается при первом commit ((2) при этом == true) и при последующих переключениях
         * _состояния ((2) при этом == false)
         *
         * @param stateIndex (1) -- индекс нового _состояния
         * @param first      (2) -- TRUE если это вызов при commit, иначе FALSE
         */
        void n2302_on(int stateIndex, boolean first);
    }

    public N2302_TextViewSwitcher(Context context) {
        super(context);
        init(context);
    }

    public N2302_TextViewSwitcher(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public N2302_TextViewSwitcher(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        _cfg = new N2302_Configs();
        yCurrStateIndex = _cfg.startIndex;
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (++yCurrStateIndex > _cfg.stringLs.length - 1) yCurrStateIndex = 0;
                yFirst = false;
                yCommit();
            }
        });
    }

    public void yCommit() {
        if (_cfg.startIndex < 0 || _cfg.startIndex > _cfg.stringLs.length - 1) {
            throw new SomeException("!!! заданный стартовый индекс вне пределов заданного массива " +
                    "локализованных строк");
        }
        this.setText(_cfg.stringLs[yCurrStateIndex].getString());
        if (_cfg.tcf != null) {
            if (_cfg.tcf.textText_L != null) {
                throw new SomeException("!!! текст в TextConfigs задавать не следует");
            }
            _cfg.tcf.apply_w282w(this);
        }
        if (_prestenter != null) _prestenter.n2302_on(_cfg.startIndex, yFirst);
    }

    public static class N2302_Configs implements IApply {
        /**
         * Локализованные строки соответствующие состояниям
         */
        transient public StringL[] stringLs;
        /**
         * Стартовый индекс {@link #stringLs} 
         */
        transient public int startIndex;
        /**
         * Оформление текста для всех состояний; сам текст сюда помещать не следует, для этого
         * предназначен {@link #stringLs}
         */
        @Nullable
        public TextConfigs tcf;

        public N2302_Configs() {
            stringLs = ArrayUtils.add(stringLs, new StringL(EStrings._STATE_1.name()));
            stringLs = ArrayUtils.add(stringLs, new StringL(EStrings._STATE_2.name()));
            stringLs = ArrayUtils.add(stringLs, new StringL(EStrings._STATE_3.name()));

            tcf = new TextConfigs();
            tcf.textColor = new Color2(Color.BLUE);
            tcf.textColorPressed = new Color2(Color.RED);
            tcf.textIsClickable = true;
            tcf.textStyleIsUnderline = true;
        }

        @Override
        public void apply_w282w(Object view) {
            N2302_TextViewSwitcher tvw = (N2302_TextViewSwitcher) view;
            tvw._cfg = this;
            tvw.yCommit();
        }

        @Override
        public void commit_w282w() {

        }
    }

}

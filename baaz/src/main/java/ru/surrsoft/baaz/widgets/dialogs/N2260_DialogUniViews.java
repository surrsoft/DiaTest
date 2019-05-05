package ru.surrsoft.baaz.widgets.dialogs;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.univers.TwoColors;

/**
 * Унивесальный диалог предоставляющий контейнер для виджетов.
 * Клиент передает массив виджетов
 * <p/>
 * Сначала нужно задать параметры с помощью "with..." методов, и затем вызвать {@link #show(View[])}
 * передав _виджеты.
 * Кнопки для которых не задан текст отображаться не будут
 * <p/>
 * <li> ЭЛЕМЕНТЫ</li>
 * <li> -- _container (_контейнер) - контейнер для виджетов </li>
 * <li> -- _widget (_виджет) - отдельный виджет </li>
 * <p/>
 * #version 1 06.06.2016  #id [[w260w]]
 */
public class N2260_DialogUniViews {


    ;
    private final Activity mContext;
    private String mBntOkText;
    private String mBntNegativeText;
    private String mBntNeytralText;
    private TwoColors _title_tc;
    private TwoColors _message_tc;
    public N2260_Presenter _presenter;
    /**
     * FALSE если нужно чтобы диалог закрывался только по нажатию кнопок
     */
    public boolean _confCancelable = true;

    public interface N2260_Presenter {
        /**
         * Нажата кнопка OK
         */
        void n2255_btnOkClick();

        /**
         * Нажата кнопка Negative
         */
        void n2255_btnNegativeClick();

        /**
         * Нажата кнопка Neytral
         */
        void n2255_btnNeytralClick();

        /**
         * Вызывается при dismiss диалога
         */
        void n2255_onDismissDialog();
    }

    public N2260_DialogUniViews(Activity context) {
        this.mContext = context;
    }

    /**
     * Старт показа диалога с передачей текста для _заголовка и _тела
     *
     * @param widgets (1) --
     */
    public void show(View[] widgets) {
        AlertDialog.Builder b = new AlertDialog.Builder(mContext);

        LinearLayout _container = new LinearLayout(mContext);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int px = G67G_Draw.px(16);
        _container.setPadding(px, px, px, px);
        _container.setLayoutParams(lp);
        _container.setOrientation(LinearLayout.VERTICAL);

        for (View elem : widgets) {
            _container.addView(elem);
        }
        ScrollView sv = new ScrollView(mContext);
        sv.setLayoutParams(new LinearLayout.LayoutParams(ScrollView.LayoutParams.MATCH_PARENT, ScrollView.LayoutParams.MATCH_PARENT));
        sv.addView(_container);
        b.setView(sv);

        if (mBntOkText != null && mBntOkText.length() > 0) {
            b.setPositiveButton(mBntOkText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (_presenter != null) {
                        _presenter.n2255_btnOkClick();
                    }
                }
            });
        }
        if (mBntNegativeText != null && mBntNegativeText.length() > 0) {
            b.setNegativeButton(mBntNegativeText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (_presenter != null) {
                        _presenter.n2255_btnNegativeClick();
                    }
                }
            });
        }
        if (mBntNeytralText != null && mBntNeytralText.length() > 0) {
            b.setNeutralButton(mBntNeytralText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (_presenter != null) {
                        _presenter.n2255_btnNeytralClick();
                    }
                }
            });
        }
        AlertDialog adia = b.create();
        adia.setCancelable(_confCancelable);
        adia.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (_presenter != null) _presenter.n2255_onDismissDialog();
            }
        });
        adia.show();
    }

    /**
     * Настройки внешнего вида _заголовка
     *
     * @param tc (1) --
     * @return --
     */
    public N2260_DialogUniViews withTitleTC(TwoColors tc) {
        _title_tc = tc;
        return this;
    }

    /**
     * Настройки внешнего вида _тела
     *
     * @param tc (1) --
     * @return --
     */
    public N2260_DialogUniViews withMessateTC(TwoColors tc) {
        _message_tc = tc;
        return this;
    }

    /**
     * Текст кнопки ОК
     *
     * @param textButton (1) --
     * @return --
     */
    public N2260_DialogUniViews withBtnOkText(String textButton) {
        mBntOkText = textButton;
        return this;
    }

    /**
     * Текст кнопки Negative
     *
     * @param textButton (1) --
     * @return --
     */
    public N2260_DialogUniViews withBtnNegativeText(String textButton) {
        mBntNegativeText = textButton;
        return this;
    }

    /**
     * Текст кнопки Neytral
     *
     * @param textButton (1) --
     * @return --
     */
    public N2260_DialogUniViews withBtnNeytralText(String textButton) {
        mBntNeytralText = textButton;
        return this;
    }

    /**
     * Слушатель
     *
     * @param presenter (1) --
     * @return --
     */
    public N2260_DialogUniViews withPresenter(N2260_Presenter presenter) {
        _presenter = presenter;
        return this;
    }

}

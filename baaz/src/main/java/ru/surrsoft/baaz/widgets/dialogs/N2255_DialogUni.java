package ru.surrsoft.baaz.widgets.dialogs;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;

import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.univers.TwoColors;

/**
 * Универсальный диалог
 * <p/>
 * <li>ЭЛЕМЕНТЫ</li>
 * <li> -- _title (_заголовок) </li>
 * <li> -- _message (_тело) </li>
 * <p/>
 * Сначала нужно задать параметры с помощью "with..." методов, и затем вызвать {@link #show(String, String)}
 * передав текст _заголовка и _тела.
 * Кнопки для которых не задан текст отображаться не будут
 * <p/>
 * #version 1 29.05.2016  #id [[w255w]]
 */
public class N2255_DialogUni {
    private final Activity context;
    private String mBtnOkText;
    private String mBtnNegativeText;
    private String mBtnNeytralText;
    private TwoColors _title_tc;
    private TwoColors _message_tc;
    public N2255_Presenter _presenter;

    public interface N2255_Presenter {
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
    }

    public N2255_DialogUni(Activity context) {
        this.context = context;
    }

    /**
     * Старт показа диалога с передачей текста для _заголовка и _тела
     *
     * @param _titleText   (1) -- текст _заголовка
     * @param _messageText (2) -- текст _тела
     */
    public void show(String _titleText, String _messageText) {
        AlertDialog.Builder b = new AlertDialog.Builder(context);
        if (_titleText != null && _titleText.length() > 0) {
            TextView _title = new TextView(context);
            _title.setText(_titleText);
            int px = G67G_Draw.px(16);
            _title.setPadding(px, px, px, px);
            if (_title_tc != null)
                _title_tc.apply(_title);
            b.setCustomTitle(_title);
        }
        if (_messageText != null && _messageText.length() > 0) {
            TextView _message = new TextView(context);
            int px = G67G_Draw.px(16);
            _message.setPadding(px, px, px, px);
            _message.setText(_messageText);
            if (_message_tc != null)
                _message_tc.apply(_message);
            b.setView(_message);
        }
        if (mBtnOkText != null && mBtnOkText.length() > 0) {
            b.setPositiveButton(mBtnOkText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (_presenter != null) {
                        _presenter.n2255_btnOkClick();
                    }
                }
            });
        }
        if (mBtnNegativeText != null && mBtnNegativeText.length() > 0) {
            b.setNegativeButton(mBtnNegativeText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (_presenter != null) {
                        _presenter.n2255_btnNegativeClick();
                    }
                }
            });
        }
        if (mBtnNeytralText != null && mBtnNeytralText.length() > 0) {
            b.setNegativeButton(mBtnNeytralText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (_presenter != null) {
                        _presenter.n2255_btnNeytralClick();
                    }
                }
            });
        }
        AlertDialog adia = b.create();
        adia.show();
    }

    /**
     * Настройки внешнего вида _заголовка
     *
     * @param tc (1) --
     * @return --
     */
    public N2255_DialogUni withTitleTC(TwoColors tc) {
        _title_tc = tc;
        return this;
    }

    /**
     * Настройки внешнего вида _тела
     *
     * @param tc (1) --
     * @return --
     */
    public N2255_DialogUni withMessateTC(TwoColors tc) {
        _message_tc = tc;
        return this;
    }

    /**
     * Текст кнопки ОК
     *
     * @param par (1) --
     * @return --
     */
    public N2255_DialogUni withBtnOkText(String par) {
        mBtnOkText = par;
        return this;
    }

    /**
     * Текст кнопки Negative
     *
     * @param par (1) --
     * @return --
     */
    public N2255_DialogUni withBtnNegativeText(String par) {
        mBtnNegativeText = par;
        return this;
    }

    /**
     * Текст кнопки Neytral
     *
     * @param par (1) --
     * @return --
     */
    public N2255_DialogUni withBtnNeytralText(String par) {
        mBtnNeytralText = par;
        return this;
    }

    /**
     * Слушатель
     *
     * @param presenter (1) --
     * @return --
     */
    public N2255_DialogUni withPresenter(N2255_Presenter presenter) {
        _presenter = presenter;
        return this;
    }

}

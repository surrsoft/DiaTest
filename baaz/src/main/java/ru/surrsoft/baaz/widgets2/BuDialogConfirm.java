package ru.surrsoft.baaz.widgets2;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.widget.LinearLayout;

import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.univers.EStrings;
import ru.surrsoft.baaz.univers.ETextCase;
import ru.surrsoft.baaz.univers.Roih;
import ru.surrsoft.baaz.univers.UniPresenter_C;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;

/**
 * Диалог подтверждения какого-либо действия
 * <p>
 * ЭЛЕМЕНТЫ
 * <li> _title </li>
 * <li> _message </li>
 * <li> _okBtn </li>
 * <li> _cancelBtn </li>
 * <p>
 * [[w464w]]
 */

public class BuDialogConfirm {
    private final Context mContext;
    private BuilderTV mTitle_btv;
    private BuilderTV mMessage_btv;
    private Presenter mPresenter;
    private String mMessage_st;
    private String mTitle_st;
    private UniPresenter_C<String> mBackline;

    //==============================================================================================
    //constructor
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public BuDialogConfirm(Context c) {
        mContext = c;
    }

    //presenter
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public interface Presenter {
        /**
         * Вызывается в случае подтверждения
         */
        void n1464_ok();
    }

    //``````````````````````````````````````````````````````````````````````````````````````````````
    public BuDialogConfirm title(BuilderTV b) {
        mTitle_btv = b;
        return this;
    }

    /**
     * В этом случае оформление будет по-умолчанию
     *
     * @param s
     * @return
     */
    public BuDialogConfirm title(String s) {
        mTitle_st = s;
        return this;
    }

    public BuDialogConfirm message(BuilderTV b) {
        mMessage_btv = b;
        return this;
    }

    /**
     * В этом случае оформление будет по-умолчанию
     *
     * @param s
     * @return
     */
    public BuDialogConfirm message(String s) {
        mMessage_st = s;
        return this;
    }

    /**
     * Предназначен для вызова только из {@link BuilderTV} и т.п.
     *
     * @param p
     * @return
     */
    public BuDialogConfirm presenter(Presenter p) {
        mPresenter = p;
        return this;
    }

    /**
     * Следует задать если вы хотите в диалог передавать какую-либо актуальную строку при каждом
     * показе диалога. Строка полученная через это будет подставлена в _message вместо "%s"
     *
     * @param up (1) --
     * @return --
     */
    public BuDialogConfirm backline(UniPresenter_C<String> up) {
        mBackline = up;
        return this;
    }

    //create
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public void show() {
        if (mMessage_btv == null && mMessage_st == null) {
            throw new SomeException("(debug) _message обязателен");
        }
        //=== title
        mTitle_btv = new BuilderTV(mContext)
                .textColor(Color.BLACK)
                .textSize(18)
                .textFont(Roih.get(Roih.TF_CYR_ROBOTO_REGULAR))
                .text(mTitle_st);
        //=== message
        String st0 = mMessage_st;
        if (mBackline != null) {
            String st = mBackline.n1451_get();
            if(st != null && st.length() > 0) {
                st0 = String.format(st0, st);
            }
        }
        mMessage_btv = new BuilderTV(mContext)
                .margins(0, 7, 0, 0)
                .textColor(Color.BLACK)
                .textSize(14)
                .textFont(Roih.get(Roih.TF_CYR_ROBOTO_LIGHT))
                .text(st0);
        //===
        //===
        LinearLayout lay = new BuLayLinear_01(mContext)
                .paddings(16)
                .build();
        //=== _title
        if (mTitle_btv != null) {
            mTitle_btv
                    .addTo(lay)
                    .create();
        }
        //=== _message
        if (mMessage_btv != null) {
            mMessage_btv
                    .addTo(lay)
                    .create();
        }
        //===
        AlertDialog.Builder b = new AlertDialog.Builder(mContext);
        b.setView(lay);
        b.setPositiveButton(EStrings._YES.val(ETextCase.UC),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mPresenter != null) {
                            mPresenter.n1464_ok();
                        }
                    }
                });
        b.setNegativeButton(EStrings._CANCEL.val(ETextCase.UC),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        b.create().show();
    }


}

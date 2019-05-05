package ru.surrsoft.baaz.widgets2;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.univers.EStrings;
import ru.surrsoft.baaz.univers.ETextCase;
import ru.surrsoft.baaz.univers.IShak;
import ru.surrsoft.baaz.univers.UniPresenter;

/**
 * Пикер работающий с типом {@link IShak}
 * <p>
 * ПОНЯТИЯ
 * <li> _face - "лицо" пикера </li>
 * <p>
 * #version 1 14.01.2017  #id [[w452w]]
 */

public class BuPickerView {
    private static final String TAG = ":" + BuPickerView.class.getSimpleName();

    private final Context mContext;
    private BuilderTV mBuilderTV;
    private IShak mShak;
    private String mText;
    private TextView _tv;
    private static final String DEF_ST = EStrings._НЕ_ВЫБРАНО.val(ETextCase.UC);
    private String mDefText;
    private AlertDialog mAlertDialog;
    private ScrollView mScroll;
    private UniPresenter<Object> mPresenterPressOk;
    private UniPresenter<Object> mPresenterPressCancel;

    //==============================================================================================
    public BuPickerView(Context context) {
        mContext = context;
    }

    public BuPickerView builderTV(BuilderTV btv) {
        mBuilderTV = btv;
        return this;
    }

    public BuPickerView shak_i_(IShak ishak) {
        mShak = ishak;
        return this;
    }

    public BuPickerView presenterPressOk(UniPresenter<Object> p) {
        mPresenterPressOk = p;
        return this;
    }

    public BuPickerView presenterPressCancel(UniPresenter<Object> p) {
        mPresenterPressCancel = p;
        return this;
    }

    /**
     * Текст для отображения на "лице" пикера для состояния "не выбрано"
     *
     * @param st (1) --
     * @return --
     */
    public BuPickerView defText(@Nullable String st) {
        mDefText = st;
        return this;
    }

    public TextView create() {
        //===
        mDefText = mDefText == null ? DEF_ST : mDefText;
        mText = mShak.n1453_getResultForUI(mDefText);
        //===
        _tv = mBuilderTV
                .text(mText)
                .pressed()
                .onclick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showcreateDialog();
                    }
                })
                .create();
        return _tv;
    }

    private void showcreateDialog() {
        //===
        View mView = mShak.n1453_getView();
        mScroll = new ScrollView(mContext);
        int px = G67G_Draw.px(16);
        mScroll.setPadding(px, px, px, px);
        mScroll.addView(mView);
        //===
        AlertDialog.Builder b = new AlertDialog.Builder(mContext);
        b.setView(mScroll);
        b.setPositiveButton(EStrings._OK.val(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (_tv != null) {
                    String result = mShak.n1453_getResultForUI(mDefText);
                    mBuilderTV
                            .text(result)
                            .apply(_tv);
                }
                //==
                if (mPresenterPressOk != null)
                    mPresenterPressOk.fun(mShak.n1453_getResultSpecial());
                mShak.n1453_applyModelChanges();
            }
        });
        b.setNegativeButton(EStrings._CANCEL.val(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (mPresenterPressCancel != null) {
                    mPresenterPressCancel.fun(null);
                }
                mShak.n1453_resetModelChanges();
            }
        });
        mAlertDialog = b.create();
        mAlertDialog.show();
    }

    public void show() {
        showcreateDialog();
    }
}

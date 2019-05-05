package ru.surrsoft.baaz.widgets2;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import ru.surrsoft.baaz.Bysa_01;
import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.univers.Roih;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;
import ru.surrsoft.baaz.widgets2.nviews.NLinearLayout;

/**
 * [[zdep]]
 * <p>
 * Виджет из заголовка (_title), под которым текст выбора (_body)
 * <p>
 * ПОНЯТИЯ
 * <li> _title - заголовок </li>
 * <li> _body - текст/пикер под заголовком </li>
 * <li> _elems - строки для отображения в _body </li>
 * <li> _mStartIndex - стартовый индекс </li>
 * <p>
 * КОДЫ: [zipc]
 * <p>
 * ВЕРСИЯ 1 1.0 08-12-2018 (stored)
 */
public class BuZdep_01 extends AbsBuView_01 {
    private final Context mContext;
    private BuilderTV _titleBtv;
    private String[] _elems;
    private BuilderTV _bodyBtv;
    private int mStartIndex;

    //==============================================================================================

    //constructors
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public BuZdep_01(Context c) {
        mContext = c;
    }

    //builders
    //``````````````````````````````````````````````````````````````````````````````````````````````

    public BuZdep_01 elems(String[] sts) {

        _elems = sts;
        return this;
    }

    public BuZdep_01 title(BuilderTV btv) {
        _titleBtv = btv;
        return this;
    }

    public BuZdep_01 body(BuilderTV btv) {
        _bodyBtv = btv;
        return this;
    }

    public BuZdep_01 startIndex(int ix) {
        mStartIndex = ix;
        return this;
    }

    //create
    //``````````````````````````````````````````````````````````````````````````````````````````````

    @Override
    public View build() {
        super.build();

        //=== проверки
        if (_elems == null) throw new SomeException("(debug)");
        if (_elems.length < 2) throw new SomeException("(debug)");
        if (mStartIndex < 0) throw new SomeException("(debug)");
        if (mStartIndex > _elems.length - 1) throw new SomeException("(debug)");

        //===
        NLinearLayout lay = new BuLayLinear_01(mContext)
                .wWC()
                .build();

        //=== _title
        if (_titleBtv == null) {
            _titleBtv = _getDefaultTitle();
        }
        _titleBtv
                .addTo(lay)
                .create();

        //=== _body
        if (_bodyBtv == null) {
            _bodyBtv = _getDefaultBody();
        }
        _bodyBtv
                .addTo(lay)
                .create();

        //===
        return lay;
    }

    //default
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public static BuilderTV _getDefaultTitle() {
        return new BuilderTV(Bysa_01.appContext)
                .text("title")
                .textSize(11)
                .textFont(Roih.get(Roih.TF_CYR_ARIMO_REGULAR))
                .textColor(Color.BLUE);
    }

    public static BuilderTV _getDefaultBody() {
        return new BuilderTV(Bysa_01.appContext)
                .text("body")
                .textSize(14)
                .textFont(Roih.get(Roih.TF_CYR_ARIMO_REGULAR))
                .underline(true)
                .textColor(Color.parseColor("#4caf50"));
    }


}

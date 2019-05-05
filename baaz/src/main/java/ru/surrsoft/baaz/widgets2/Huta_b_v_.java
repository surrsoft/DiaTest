package ru.surrsoft.baaz.widgets2;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import ru.surrsoft.baaz.logs.Vdin_01;
import ru.surrsoft.baaz.suite.figures.N1208_Cancel;
import ru.surrsoft.baaz.suite.figures.N1208_DotsVertical;
import ru.surrsoft.baaz.suite.figures.N1208_Trash;
import ru.surrsoft.baaz.univers.EStrings;
import ru.surrsoft.baaz.univers.ETextCase;
import ru.surrsoft.baaz.univers.U;
import ru.surrsoft.baaz.univers.UniPresenter;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;
import ru.surrsoft.baaz.widgets2.nviews.NLinearLayout;
import ru.surrsoft.baaz.widgets2.utils.BuCSL;
import ru.surrsoft.baaz.widgets2.utils.BuPopupMenu;

/**
 * [[huta]] - выезжающая шторка с кнопками
 * <p>
 * [[w465w]]
 */
public class Huta_b_v_ extends NLinearLayout implements ISazr {

    private Context mContext;
    private TextView vCounter;
    private Presenter_x_ mPresenter;
    private int mCount;
    private int mMarginT_dp;

    //==============================================================================================
    public Huta_b_v_(Context context) {
        super(context);
        init(context);
    }

    public Huta_b_v_(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Huta_b_v_(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context c) {
        mContext = c;
        setOrientation(HORIZONTAL);
    }

    public Huta_b_v_ create() {
        //===
        new BuLayLinear_01(mContext)
                .bgColor(Color.WHITE)
                .border(Color.BLACK)
                .horizontal()
                .wWC()
                .margins(0, 7 + mMarginT_dp, 0, 0)
                .classLayParams(FrameLayout.LayoutParams.class)
                .apply(this);

        //=== счетчик количества выбранных
        vCounter = new BuilderTV(mContext)
                .addTo(this)
                .fontSize(14)
                .textColor(Color.parseColor("#47b8e0"))
                .text(mCount + "")
                .wh(48)
                .gravityIn(Gravity.CENTER)
                .create();

        //=== кнопка "Удалить"
        new BuilderTV(mContext)
                .addTo(this)
                .drawer(new N1208_Trash()
                        .margins(12)
                        .colorStroke(new BuCSL()
                                .pressed(U.c2(Color.WHITE))
                                .normal(U.c2(Color.RED))
                                .create())
                )
                .wh(48)
                .onclick(v -> {
                    //event//
                    Vdin_01.logM("нажата кнопка 'Удалить' //190421-074400", Huta_b_v_.this);
                    if (mPresenter != null) {
                        mPresenter.onDelete(); //[[w465w-1555]]
                    }
                })
                .confirmDialog(new BuDialogConfirm(mContext)
                        .title(EStrings._DELETE4.val(ETextCase.FUC))
                        .message(EStrings._N_ITEMS.val(ETextCase.LC))
                        .backline(() -> mCount + "")
                )
                .gravityIn(Gravity.CENTER)
                .gravitySelf(Gravity.CENTER_VERTICAL)
                .create();

        //=== кнопка "..."
        new BuilderTV(mContext)
                .addTo(this)
                .drawer(new N1208_DotsVertical()
                        .margins(11)
                        .colorStroke(new BuCSL()
                                .pressed(U.c2(Color.WHITE))
                                .normal(U.c2(Color.BLACK))
                                .create()
                        )
                        .colorFill(ColorStateList.valueOf(Color.BLACK))
                )
                .wh(48)
                .onclick(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .gravityIn(Gravity.CENTER)
                .gravitySelf(Gravity.CENTER_VERTICAL)
                .popupMenu(new BuPopupMenu(mContext)
                        .addElem(EStrings._SELECT3.val(ETextCase.FUC), new UniPresenter<View>() {
                            @Override
                            public void fun(View v) {
                                //выбрать все
                                if (mPresenter != null) {
                                    mPresenter.onSelectAll();
                                }
                            }
                        }, null)
                        .addElem(EStrings._SELECT_INVERT_2.val(ETextCase.FUC), new UniPresenter<View>() {
                            @Override
                            public void fun(View v) {
                                //инвертировать выбор
                                if (mPresenter != null) {
                                    mPresenter.onSelectInvert();
                                }
                            }
                        }, null)
                )
                .create();

        //=== кнопка "X"
        new BuilderTV(mContext)
                .addTo(this)
                .drawer(new N1208_Cancel()
                        .margins(16)
                        .colorStroke(new BuCSL()
                                .pressed(U.c2(Color.WHITE))
                                .normal(U.c2(Color.BLACK))
                                .create()
                        )
                )
                .wh(48)
                .onclick(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mPresenter != null) {
                            mPresenter.onCancel(); //[[w465w-2201]]
                        }
                    }
                })
                .gravityIn(Gravity.CENTER)
                .gravitySelf(Gravity.CENTER_VERTICAL)
                .create();
        return this;
    }

    public static class Presenter_x_ {
        public void onDelete() {

        }

        public void onCancel() {

        }

        public void onSelectAll() {

        }

        public void onSelectInvert() {

        }
    }

    public Huta_b_v_ presenter(Presenter_x_ p) {
        mPresenter = p;
        return this;
    }

    public Huta_b_v_ marginT(int dp) {
        mMarginT_dp = dp;
        return this;
    }

    //ISazr
    //``````````````````````````````````````````````````````````````````````````````````````````````
    @Override
    public void n1462_countUpdate(int count, boolean updateView) {
        mCount = count;
        if (updateView) {
            vCounter.setText(count + "");
        }
    }

    //``````````````````````````````````````````````````````````````````````````````````````````````

}

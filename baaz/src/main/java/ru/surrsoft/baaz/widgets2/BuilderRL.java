package ru.surrsoft.baaz.widgets2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.flexbox.FlexboxLayout;

import org.apache.commons.lang3.ArrayUtils;

import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.suite.figures.N1208_AbsDrawer;
import ru.surrsoft.baaz.univers.FourSizes_01;
import ru.surrsoft.baaz.widgets2.nviews.NRelativeLayout;

/**
 * Генератор NRelativeLayout
 * <p>
 * #version 1 08.10.2016  #id [[w369w]]
 */
public class BuilderRL {
    private static final String TAG = ":" + BuilderRL.class.getSimpleName();

    private final Context mContext;
    private NRelativeLayout _rl;
    private int mBgColor;
    private View[] mRViews = {};
    private View[] mLViews = {};
    private ESide mLastInvoked = null;
    private int mW_px = RelativeLayout.LayoutParams.MATCH_PARENT;
    private int mH_px = RelativeLayout.LayoutParams.WRAP_CONTENT;
    private FourSizes_01 mMargins;
    private N1208_AbsDrawer mDrawer;
    private FourSizes_01 mPaddings;
    private Class<?> mLayParamsClass;
    private ViewGroup mParentLay;
    private String mEcudComment;

    //constructors
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public BuilderRL(Context context) {
        mContext = context;
        _rl = new NRelativeLayout(mContext);
    }

    //create
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public NRelativeLayout create() {
        if (mParentLay != null) mParentLay.addView(_rl);
        //===
        if (mMargins != null) {
            ViewGroup.MarginLayoutParams lp = ((ViewGroup.MarginLayoutParams) getcreateLP());
            lp.leftMargin = mMargins.l_px;
            lp.topMargin = mMargins.t_px;
            lp.rightMargin = mMargins.r_px;
            lp.bottomMargin = mMargins.b_px;
            _rl.setLayoutParams(lp);
        }
        //===
        if (mPaddings != null) {
            _rl.setPadding(mPaddings.l_px, mPaddings.t_px, mPaddings.r_px, mPaddings.b_px);
        }
        //===
        if (mBgColor != 0) {
            _rl.setBackgroundColor(mBgColor);
        }
        //===
        //=== рисование на фоне
        if (mDrawer != null) {
            _rl.setWillNotDraw(false);
            _rl.drawer(mDrawer);
            ViewTreeObserver vto = _rl.getViewTreeObserver();
            if (vto.isAlive()) {
                vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        //борьба с проблемой [w130w]
                        mDrawer
                                .w_px(_rl.getWidth())
                                .h_px(_rl.getHeight());
                        return true;
                    }
                });
            }
        }

        //===
        //===
        return _rl;
    }

    public void apply(NRelativeLayout rl){
        _rl = rl;
        create();
    }

    //``````````````````````````````````````````````````````````````````````````````````````````````

    public BuilderRL hWC(){
        mH_px = RelativeLayout.LayoutParams.WRAP_CONTENT;
        return this;
    }

    public BuilderRL ecudComment(String st ){
        mEcudComment = st ;
        return this;
    }

    /**
     * @param parent (1) -- контейнер в который будет добавлен созданный текущим билдером виджет
     * @return
     */
    public BuilderRL addTo(ViewGroup parent) {
        mParentLay = parent;
        return this;
    }

    public BuilderRL paddings(FourSizes_01 p) {
        mPaddings = p;
        return this;
    }

    public BuilderRL bgColor(int color) {
        mBgColor = color;
        return this;
    }

    /**
     * Каждый (1) будет прижиматься вправо, слева от предыдущих (1) для которых вызывался текущий метод
     *
     * @param view (1) --
     * @return --
     */
    public BuilderRL appendToRight(View view) {
        if (view == null) return this;
        mLastInvoked = ESide.RIGHT;
        _rl.addView(view);
        view.setId(mRViews.length + 5); //5 - случайная прибавка т.к. 0 и 1 заняты для указания что выравнивать нужно по родителю
        NRelativeLayout.LayoutParams lp = (NRelativeLayout.LayoutParams) view.getLayoutParams();
        if (mRViews.length == 0) {
            lp.addRule(NRelativeLayout.ALIGN_PARENT_RIGHT);
        } else {
            int id = mRViews[mRViews.length - 1].getId();
            lp.addRule(NRelativeLayout.LEFT_OF, id);
        }
        lp.addRule(NRelativeLayout.CENTER_VERTICAL);
        view.setLayoutParams(lp);
        mRViews = ArrayUtils.add(mRViews, view);
        return this;
    }

    /**
     * Дополнительный align по вертикали для последнего добавленного элемента
     * @param align (1) -- например NRelativeLayout.ALIGN_PARENT_BOTTOM
     * @return --
     */
    public BuilderRL lastExtraAlignV(int align){
        if (mLastInvoked != null && mLastInvoked == ESide.LEFT && mLViews.length > 0) {
            NRelativeLayout.LayoutParams lp = (NRelativeLayout.LayoutParams) mLViews[mLViews.length - 1].getLayoutParams();
            verbose_удалитьВыравниваниеCENTER_VERTICAL(lp);
            lp.addRule(align);
        }
        if (mLastInvoked != null && mLastInvoked == ESide.RIGHT && mRViews.length > 0) {
            NRelativeLayout.LayoutParams lp = (NRelativeLayout.LayoutParams) mRViews[mRViews.length - 1].getLayoutParams();
            verbose_удалитьВыравниваниеCENTER_VERTICAL(lp);
            lp.addRule(align);
        }
        return this;
    }

    private void verbose_удалитьВыравниваниеCENTER_VERTICAL(RelativeLayout.LayoutParams lp) {
        NRelativeLayout.removeRule(lp, NRelativeLayout.CENTER_VERTICAL);
    }

    public BuilderRL appendToRight(View view, boolean b) {
        if (b) appendToRight(view);
        return this;
    }

    /**
     * См. {@link #appendToRight(View)}
     *
     * @param view
     * @return
     */
    public BuilderRL appendToRight(View... view) {
        mLastInvoked = ESide.RIGHT;
        for (View elem : view) {
            appendToRight(elem);
        }
        return this;
    }

    /**
     * См. {@link #appendToLeft(View)}
     *
     * @param view
     * @return
     */
    public BuilderRL appendToLeft(View... view) {
        for (View elem : view) {
            appendToLeft(elem);
        }
        return this;
    }

    public BuilderRL wWC() {
        mW_px = RelativeLayout.LayoutParams.WRAP_CONTENT;
        ViewGroup.LayoutParams lp = getcreateLP();
        lp.width = mW_px;
        _rl.setLayoutParams(lp);
        return this;
    }

    private ViewGroup.LayoutParams getcreateLP() {
        if (_rl.getLayoutParams() == null) {
            ViewGroup.MarginLayoutParams lp = null;
            if (mLayParamsClass != null) {
                if (mLayParamsClass.equals(FlexboxLayout.LayoutParams.class)) {
                    lp = new FlexboxLayout.LayoutParams(mW_px, mH_px);
                } else if (mLayParamsClass.equals(FrameLayout.LayoutParams.class)) {
                    lp = new FrameLayout.LayoutParams(mW_px, mH_px);
                } else if (mLayParamsClass.equals(RelativeLayout.LayoutParams.class)) {
                    lp = new RelativeLayout.LayoutParams(mW_px, mH_px);
                }
            }
            if (lp == null) lp = new LinearLayout.LayoutParams(mW_px, mH_px);
            _rl.setLayoutParams(lp);
            return lp;
        }
        return _rl.getLayoutParams();
    }

    public BuilderRL drawer(N1208_AbsDrawer drawer) {
        mDrawer = drawer;
        return this;
    }

    public BuilderRL margins(FourSizes_01 fourSizes) {
        mMargins = fourSizes;
        return this;
    }

    public BuilderRL classLayParams(Class<?> c) {
        mLayParamsClass = c;
        return this;
    }

    public BuilderRL hMP() {
        mH_px = RelativeLayout.LayoutParams.MATCH_PARENT;
        ViewGroup.LayoutParams lp = getcreateLP();
        lp.height = mH_px;
        _rl.setLayoutParams(lp);
        return this;
    }

    private enum ESide {
        LEFT, RIGHT
    }

    /**
     * Каждый (1) будет прижиматься вправо, слева от предыдущих (1) для которых вызывался текущий метод
     *
     * @param view (1) --
     * @return --
     */
    public BuilderRL appendToLeft(View view) {
        mLastInvoked = ESide.LEFT;
        _rl.addView(view);
        view.setId(mLViews.length + 105); //105 - случайная прибавка т.к. 0 и 1 заняты для указания что выравнивать нужно по родителю
        NRelativeLayout.LayoutParams lp = (NRelativeLayout.LayoutParams) view.getLayoutParams();
        if (mLViews.length == 0) {
            lp.addRule(NRelativeLayout.ALIGN_PARENT_LEFT);
        } else {
            int id = mLViews[mLViews.length - 1].getId();
            lp.addRule(NRelativeLayout.RIGHT_OF, id);
        }
        lp.addRule(NRelativeLayout.CENTER_VERTICAL);
        view.setLayoutParams(lp);
        mLViews = ArrayUtils.add(mLViews, view);
        return this;
    }

    public BuilderRL appendToLeft(View view, boolean b) {
        if (b) appendToLeft(view);
        return this;
    }

    /**
     * Добавляет _базовый-виджет - это виджет который будет занимать все оставшееся пространство (
     * пространство которое остается после работы методов {@link #appendToLeft(View)} и
     * {@link #appendToRight(View)})
     * <p>
     * Текущий метод должен вызываться после методов {@link #appendToLeft(View)},
     * {@link #appendToRight(View)}
     *
     * @param view (1) --
     * @return --
     */
    public BuilderRL addBaseView(View view) {
        _rl.addView(view);
        NRelativeLayout.LayoutParams lp = (NRelativeLayout.LayoutParams) view.getLayoutParams();
        if (mLViews.length == 0) {
            lp.addRule(NRelativeLayout.ALIGN_PARENT_LEFT);
        } else {
            int id = mLViews[mLViews.length - 1].getId();
            lp.addRule(NRelativeLayout.RIGHT_OF, id);
        }
        if (mRViews.length == 0) {
            lp.addRule(NRelativeLayout.ALIGN_PARENT_RIGHT);
        } else {
            int id = mRViews[mRViews.length - 1].getId();
            lp.addRule(NRelativeLayout.LEFT_OF, id);
        }
        lp.width = NRelativeLayout.LayoutParams.MATCH_PARENT;
        lp.addRule(NRelativeLayout.CENTER_VERTICAL);
        view.setLayoutParams(lp);
        mLViews = ArrayUtils.add(mLViews, view);
        mLastInvoked = ESide.LEFT;
        return this;
    }

    /**
     * Добавление margin right к view который был последний раз добавлен методом
     * {@link #appendToLeft(View)} или {@link #appendToRight(View)}
     *
     * @param dp (1) --
     * @return --
     */
    public BuilderRL lastMarginR(int dp) {
        if (mLastInvoked != null && mLastInvoked == ESide.LEFT && mLViews.length > 0) {
            NRelativeLayout.LayoutParams lp = (NRelativeLayout.LayoutParams) mLViews[mLViews.length - 1].getLayoutParams();
            lp.rightMargin = G67G_Draw.px(dp);
            mLViews[mLViews.length - 1].setLayoutParams(lp);
        }
        if (mLastInvoked != null && mLastInvoked == ESide.RIGHT && mRViews.length > 0) {
            NRelativeLayout.LayoutParams lp = (NRelativeLayout.LayoutParams) mRViews[mRViews.length - 1].getLayoutParams();
            lp.rightMargin = G67G_Draw.px(dp);
            mRViews[mRViews.length - 1].setLayoutParams(lp);
        }
        return this;
    }

    /**
     * Добавление margin right к view который был последний раз добавлен методом
     * {@link #appendToLeft(View)} или {@link #appendToRight(View)}
     *
     * @param dp (1) --
     * @return --
     */
    public BuilderRL lastMarginL(int dp) {
        if (mLastInvoked != null && mLastInvoked == ESide.LEFT && mLViews.length > 0) {
            NRelativeLayout.LayoutParams lp = (NRelativeLayout.LayoutParams) mLViews[mLViews.length - 1].getLayoutParams();
            lp.leftMargin = G67G_Draw.px(dp);
            mLViews[mLViews.length - 1].setLayoutParams(lp);
        }
        if (mLastInvoked != null && mLastInvoked == ESide.RIGHT && mRViews.length > 0) {
            NRelativeLayout.LayoutParams lp = (NRelativeLayout.LayoutParams) mRViews[mRViews.length - 1].getLayoutParams();
            lp.leftMargin = G67G_Draw.px(dp);
            mRViews[mRViews.length - 1].setLayoutParams(lp);
        }
        return this;
    }

}

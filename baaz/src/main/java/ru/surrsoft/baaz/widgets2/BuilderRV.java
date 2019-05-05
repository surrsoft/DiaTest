package ru.surrsoft.baaz.widgets2;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import ru.surrsoft.baaz.cls_c.G67G_Draw;

/**
 * По умолчанию применяет к rv:
 * <li> LayoutManager == LinearLayoutManager, </li>
 * <li> paddings == 16dp, </li>
 * <li> расстояние между каточками == 5dp </li>
 * <p>
 * #id [[w356w]]
 * <p>
 * ВЕРСИЯ 2 1.1 2019-02-05 (stored)
 */
public class BuilderRV {
    private final Context mContext;
    private int mPaddingL_px;
    private int mPaddingT_px;
    private int mPaddingR_px;
    private int mPaddingB_px;
    private int mGap_dp = 5;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    public BuilderRV(Context c) {
        mContext = c;
        paddings(16);
    }

    public void apply(RecyclerView rv) {
        if (mLayoutManager == null) mLayoutManager = new LinearLayoutManager(mContext);
        //===
        rv.setLayoutManager(mLayoutManager);
        //===
        rv.setPadding(mPaddingL_px, mPaddingT_px, mPaddingR_px, mPaddingB_px);
        //===
        if (mGap_dp > 0) {
            DividerItemDecoration d = new DividerItemDecoration();
            rv.addItemDecoration(d);
        }
        //===
        if (mAdapter != null) {
            rv.setAdapter(mAdapter);
        }
        //===
    }

    public RecyclerView create() {
        RecyclerView rv = new RecyclerView(mContext);
        apply(rv);
        return rv;
    }

    //builders
    //``````````````````````````````````````````````````````````````````````````````````````````````

    public BuilderRV paddings(int dp) {
        int px = G67G_Draw.px(dp);
        mPaddingL_px = px;
        mPaddingT_px = px;
        mPaddingR_px = px;
        mPaddingB_px = px;
        return this;
    }

    /**
     * Зазор между карточками
     *
     * @param dp (1) --
     * @return --
     */
    public BuilderRV gap(int dp) {
        mGap_dp = dp;
        return this;
    }

    public BuilderRV adapter(RecyclerView.Adapter adapter) {
        this.mAdapter = adapter;
        return this;
    }


    //
    //``````````````````````````````````````````````````````````````````````````````````````````````

    public class DividerItemDecoration extends RecyclerView.ItemDecoration {
        public DividerItemDecoration() {
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            if (parent.getChildAdapterPosition(view) == 0) return;
            outRect.top = G67G_Draw.px(mGap_dp);
        }
    }


}

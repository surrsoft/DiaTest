package ru.surrsoft.baaz.widgets.recyclerview;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apache.commons.lang3.ArrayUtils;

import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.configs.LinearLayoutConfigs;
import ru.surrsoft.baaz.tclasses.TArray_01;
import ru.surrsoft.baaz.univers.Color2;
import ru.surrsoft.baaz.univers.Size2;
import ru.surrsoft.baaz.widgets2.BuilderTV_;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;

/**
 * Created by 0000 on 20.07.2016.
 */
class N2304B_RVAdapter extends RecyclerView.Adapter<N2304B_RVAdapter.Holder> {
    private static final String TAG = ":" + N2304B_RVAdapter.class.getSimpleName();


    private String[] elems = new String[]{"aaa", "bbb", "ccc", "ddd"};

    private Context context;
    private final ItemTouchHelper mItemTouchHelper;

    /**
     * @param context (1) --
     * @param rv      (2) -- передается сюда только для пристыковки ItemTouchHelper к этому RV
     */
    public N2304B_RVAdapter(Context context, RecyclerView rv) {
        this.context = context;
        mItemTouchHelper = new ItemTouchHelper(new SimpleCallback(this));
        mItemTouchHelper.attachToRecyclerView(rv);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = new CardView(context);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        cardView.setLayoutParams(lp);

        Holder holder = new Holder(cardView);

        LinearLayout lay = new BuLayLinear_01(context).horizontal()
                .build();
        LinearLayoutConfigs c = new LinearLayoutConfigs();
        c.sizeWidth = new Size2().matchParent();
        c.sizeHeight = new Size2().wrapContent();
        c.border = true;
        c.borderColor = new Color2(Color.RED);
        c.borderTh_dp = 1;
        c.apply_w282w(lay);

        holder._handle = BuilderTV_.create(null);
        holder._handle.setLayoutParams(new ViewGroup.LayoutParams(G67G_Draw.px(48),
                G67G_Draw.px(48)));
        holder._handle.setBackgroundColor(Color.BLUE);

        holder.tv = BuilderTV_.create(null, Color.BLACK, 5);

        lay.addView(holder._handle);
        lay.addView(holder.tv);
        cardView.addView(lay);

        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        holder.tv.setText(elems[position]);
        //подключение к _handle слушателя касаний для драгинга
        holder._handle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mItemTouchHelper.startDrag(holder);
                }
                return false;
            }
        });
    }

    class Holder extends RecyclerView.ViewHolder {
        public TextView _handle;
        public TextView tv;

        public Holder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemCount() {
        return elems.length;
    }

    public void swap(int firstPosition, int secondPosition) {
        TArray_01.swap(elems, firstPosition, secondPosition);
        notifyItemMoved(firstPosition, secondPosition);
    }

    public void remove(int position) {
        elems = ArrayUtils.remove(elems, position);
        notifyItemRemoved(position);
    }

    /**
     * Получает обратные вызовы при drag&drop и swipe.
     * Также предоставляет конфигурационные данные для RV
     */
    class SimpleCallback extends ItemTouchHelper.SimpleCallback {

        private final N2304B_RVAdapter mAdapter;

        //==========================================================================================
        public SimpleCallback(N2304B_RVAdapter adapt) {
            super(
                    ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                    ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
            );
            mAdapter = adapt;
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                              RecyclerView.ViewHolder target) {
            mAdapter.swap(viewHolder.getAdapterPosition(), target.getAdapterPosition());
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            mAdapter.remove(viewHolder.getAdapterPosition());
        }

        //отключаем начало драга при long press
        @Override
        public boolean isLongPressDragEnabled() {
            return false;
        }

        //отключаем удаление свайпом
        @Override
        public boolean isItemViewSwipeEnabled() {
            return false;
        }
    }
}

package ru.surrsoft.baaz.widgets.recyclerview;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.R;

/**
 * Класс-демонстратор.
 * Активити с простейшим RecyclerView, с drag&drop за handle ([w305w_handleDragDrop]), с отключенным swipe
 * Начальный массив задается через {@link N2304B_RVAdapter#elems}
 * <p/>
 * ЭЛЕМЕНТЫ
 * <li> -- _elem (_элемент) - элемент списка </li>
 * <li> -- _handle - элемент _элемента за который _элемент драгуется</li>
 * <p/>
 * #version 1 20-07-2016  #id [w304w]
 */
public class N2304_DEM_ARVDragDropHandle extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.n2304_learn_ardragdrop);
        RecyclerView rv = (RecyclerView) findViewById(R.id.learn_rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        int px = G67G_Draw.px(16);
        rv.setPadding(px, px, px, px);

        //зазор между карточками
        rv.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                if (parent.getChildAdapterPosition(view) == 0) return;
                outRect.top = G67G_Draw.px(5);
            }
        });

        N2304B_RVAdapter rvAdapter = new N2304B_RVAdapter(this, rv);
        rv.setAdapter(rvAdapter);

    }

}

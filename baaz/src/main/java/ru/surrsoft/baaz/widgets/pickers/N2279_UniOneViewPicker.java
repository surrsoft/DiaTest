package ru.surrsoft.baaz.widgets.pickers;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import ru.surrsoft.baaz.cls_c.G67G_Draw;

/**
 * Универсальный picker одиночного выбора из коллекции View
 * <p/>
 * см. ниже описание конструктора
 * <p/>
 * #version 1 19.06.2016  #id [[w279w]]
 */
public class N2279_UniOneViewPicker {



    private final AlertDialog dg;
    private final Adapt adapter;
    private Context context;
    private final View[] views;
    private final int startIndex;

    public interface N2279_presenter {
        /**
         * Вызывается при первом создании объекта ((3) при этом == true) и в последствии при выборе
         * пользователя
         *
         * @param elem_  (1) -- элемент из начального массива
         * @param index (2) -- индекс элемента (1)
         * @param first (3) -- TRUE если это вызов при создании объекта, иначе FALSE
         */
        void n2279_onSelected(View elem_, int index, boolean first);
    }

    /**
     * @param context    (1) -- контекст
     * @param views      (2) -- массив виджетов которые будут оторажены в виде списка для выбора
     * @param baseView   (3) -- _базовый-виджет; виджет по нажатию на который будет отображен список выбора
     * @param startIndex (4) -- стартовый индекс; используется чтобы задать начальное значение _базовому-виджету
     * @param _presetner (5) -- презентер который будет получать обратные вызовы
     * @param title      (6) -- текст для заголовка;
     */
    public N2279_UniOneViewPicker(Context context, View[] views, View baseView, int startIndex,
                                  final N2279_presenter _presetner, @Nullable String title) {
        this.context = context;
        this.views = views;
        this.startIndex = startIndex;

        baseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dg.show();
            }
        });

        if (_presetner != null) _presetner.n2279_onSelected(views[startIndex], startIndex, true);

        AlertDialog.Builder b = new AlertDialog.Builder(context);
        ListView lv = new ListView(context);
        int px = G67G_Draw.px(16);
        lv.setPadding(px, px, px, px);
        b.setView(lv);
        adapter = new Adapt(context, views);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (_presetner != null) _presetner.n2279_onSelected(view, position, false);
                dg.dismiss();
            }
        });
        if(title != null) b.setTitle(title);
        dg = b.create();
    }

    private class Adapt extends ArrayAdapter<View> {
        private final Context context;
        private final View[] views;

        public Adapt(Context context, View[] views) {
            super(context, 0, views);
            this.context = context;
            this.views = views;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return views[position];
        }
    }
}

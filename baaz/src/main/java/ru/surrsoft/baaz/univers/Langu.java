package ru.surrsoft.baaz.univers;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.Locale;

import ru.surrsoft.baaz.cls_c.G67G;
import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.cls_c.G67G_Reflect;
import ru.surrsoft.baaz.widgets.dialogs.DialogTextInput;
import ru.surrsoft.baaz.widgets2.BuilderTV_;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;

/**
 * Класс представляющий одну локализованную строку
 * #version 1 10.07.2016  #id [[w290w]]
 */
public class Langu {
    /**
     * Ключ для "привязки" к отдельным записям
     */
    public String _key;
    /**
     * Просто поясняющий комментарий
     */
    public String _comment;
    public String en;
    public String ru;

    public Langu() {
    }

    /**
     * Получение строки в зависимости от текущей языковой локали
     * @return --
     */
    @Nullable
    public String getString(){
        String lang = Locale.getDefault().getLanguage();
        if(lang.equals("en")) return this.en;
        if(lang.equals("ru")) return this.ru;
        G67G.assert_(false, "класс не поддерживает текущую языковую локаль "+lang);
        return null;
    }

    /**
     * Виджет с диалогом. По результату работы диалога текущий объект меняет сам себя
     * @param languages
     * @param context
     * @param presenter
     * @return
     */
    public LinearLayout getWidget_06(String[] languages,
                                     final Context context, LanguPresenter_06 presenter) {
        LinearLayout layBase = new BuLayLinear_01(context).build();
        LinearLayout lay0 = m38_06("_key:", this._key, context,
                G67G_Reflect.getFieldByName(this, "_key"), presenter);
        LinearLayout lay1 = m38_06("_comm:", this._comment, context,
                G67G_Reflect.getFieldByName(this, "_comment"), presenter);
        layBase.addView(lay0);
        layBase.addView(lay1);

        for (String elem : languages) {
            String val = (String) G67G_Reflect.getValueByFieldName(this, elem);
            LinearLayout layX = m38_06(elem, val, context, G67G_Reflect.getFieldByName(this, elem),
                    presenter);
            layBase.addView(layX);
        }
        return layBase;
    }

    public interface LanguPresenter_06 {
        /**
         * Вызывается по окончании редактирования
         * @param langu
         */
        void languOn(Langu langu);
    }

    /**
     *
     * @param title_
     * @param value
     * @param context
     * @param field
     * @param presenter
     * @return пара ключ-значение, с пикером значения
     */
    private LinearLayout m38_06(String title_, final String value, final Context context,
                                final Field field,
                                final LanguPresenter_06 presenter) {
        LinearLayout lay = new BuLayLinear_01().horizontal().build();

        TextView title = BuilderTV_.create(title_);
        TwoColors tc = new TwoColors(Color.BLUE, null, null, 12).colorPressed(Color.CYAN);
        tc.apply(title);
        title.setPadding(0, 0, G67G_Draw.px(10), 0);
        lay.addView(title);

        final TextView val = BuilderTV_.create(value);
        TwoColors tc2 = new TwoColors(Color.BLACK, null, null, 12).colorPressed(Color.CYAN);
        tc2.apply(val);
        View.OnClickListener lst = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogTextInput d = new DialogTextInput(context);
                d._cfg._edit_text = value;
                d._presenter = new DialogTextInput.N2287_Presenter() {
                    @Override
                    public void n2287_ok(String text) {
                        G67G_Reflect.setValueByField(Langu.this, field, text);
                        val.setText(text);
                        if (presenter != null) presenter.languOn(Langu.this);
                    }
                };
                d.create().show();
            }
        };
        title.setOnClickListener(lst);
        val.setOnClickListener(lst);
        lay.addView(val);
        return lay;
    }
}

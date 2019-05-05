package ru.surrsoft.baaz.widgets.tags;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Collections;

import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.R;
import ru.surrsoft.baaz.configs.LinearLayoutConfigs;
import ru.surrsoft.baaz.configs.TextConfigs;
import ru.surrsoft.baaz.univers.Color2;
import ru.surrsoft.baaz.univers.TwoColors;
import ru.surrsoft.baaz.widgets.buttons.N2249_BtnPNG;
import ru.surrsoft.baaz.widgets.dialogs.N2260_DialogUniViews;

/**
 * Виджет предназначенный для отображения тегов
 * <p/>
 * <li> ТЕРМИНЫ, ЭЛЕМЕНТЫ </li>
 * <li> -- _tag (_тег) - </li>
 * <li> -- _layTag - слой представляющий _тег </li>
 * <li> ---- _text1 - </li>
 * <li> ---- _text2 - </li>
 * <li> ---- _text3 - </li>
 * <li> ---- _btndel - кнопка удаления отдельного тега </li>
 * <li> -- _dialog (_диалог) - диалог отображающий при по нажатию на _тег </li>
 * <p/>
 * ЗАВИСИМОСТЬ: compile 'com.google.android:flexbox:0.2.1' //https://github.com/google/flexbox-layout
 * <p/>
 * #version 1 05.06.2016  #id [[w259w]]
 */
public class N2259_TagsView extends FlexboxLayout {



    private Context yContext;
    public N2259_Config _config;
    public N2259_Presenter _presenter;
    /**
     * _Тег на котором было выполнено нажатие последний раз. Используется для скрытия _поискового-тега при нажатии "clean"
     */
    public View _clickedTag_r;

    public interface N2259_Presenter {
        /**
         * Вызывается при нажатии "отвязать" тег
         *
         * @param idTag (1) -- первичный ключ тега
         */
        void onDeleteTag(long idTag);

        /**
         * Нажатие на _btnDel _тега
         *
         * @param idTag (1) -- первичный ключ _тега
         */
        void onClickBtnDel(long idTag);

        /**
         * Через это this получает _text1 для _диалога
         *
         * @param id (1) -- первичный ключ _тега
         * @return --
         */
        String getDialogText1(long id);

        /**
         * Через это this получает _text2 для _диалога
         *
         * @param id (1) -- первичный ключ _тега
         * @return --
         */
        String getDialogText2(long id);

        /**
         * Через это this получает _text3 для _диалога
         *
         * @param id (1) -- первичный ключ _тега
         * @return --
         */
        String getDialogText3(long id);
    }

    //2 //constructors
    public N2259_TagsView(Context context) {
        super(context);
        init(context);
    }

    public N2259_TagsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public N2259_TagsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    //2 //constructors

    private void init(Context context) {
        yContext = context;
        _config = new N2259_Config();
        this.setFlexWrap(FLEX_WRAP_WRAP);
        int px = G67G_Draw.px(10);
        this.setPadding(px, 0, px, px);
        ySetTags(new N2259_Tag[]{});
    }

    /**
     * @param tags (1) -- теги которые нужно отображить
     */
    public void ySetTags(N2259_Tag[] tags) {
        if (tags == null || tags.length < 1) return;
        Collections.sort(Arrays.asList(tags));
        this.removeAllViews();
        for (N2259_Tag elem : tags) {
            LinearLayout tagWidget = yCreateTagWidget(elem);
            addView(tagWidget);
        }
    }

    /**
     * Вариант виджета _тега без "крестика" справа
     *
     * @param tag (1) -- модель _тега
     * @return --
     */
    private TextView yCreateTagWidget_B(final N2259_Tag tag) {
        TextView tv = new TextView(yContext);
        int px = G67G_Draw.px(5);
        tv.setPadding(px, px, px, px);
        tv.setText(tag.text1);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yOnClickTag(v, tag);
            }
        });
        return tv;
    }

    /**
     * Создание виджета _тега (1)
     *
     * @param tag (1) -- _тег
     * @return виджет _тега (1)
     */
    private LinearLayout yCreateTagWidget(final N2259_Tag tag) {
        LayoutInflater infl = (LayoutInflater) yContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final LinearLayout lay = ((LinearLayout) infl.inflate(_config._laytag, null)); //layout
        if(_config._laytag_llConfigs != null) _config._laytag_llConfigs.apply_w282w(lay);
        TextView _text1 = (TextView) lay.findViewById(R.id.n2259_text1);

        _text1.setText(tag.text1);
        if (tag.text1_tc != null) tag.text1_tc.apply(_text1);
        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yOnClickTag(lay, tag);
            }
        };
        _text1.setOnClickListener(click);

        N2249_BtnPNG _btndel = (N2249_BtnPNG) lay.findViewById(R.id.n2259_btndel);
        if (_config._btndel_isVisible) {
            _btndel.ySetParams(_config._btndel_drw, _config._btndel_colorNormal,
                    _config._btndel_colorPressed, _config._btndel_colorDisabled);
            _btndel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (_presenter != null) {
                        _clickedTag_r = lay;
                        _presenter.onClickBtnDel(tag.id);
                    }
                }
            });
        } else {
            _btndel.setVisibility(View.GONE);
        }
        //===
        return lay;
    }

    /**
     * Действие при нажатии на _тег
     *
     * @param viewTag (1) -- нажатый _тег
     * @param tag     (2) -- модель нажатого _тега
     */
    private void yOnClickTag(View viewTag, N2259_Tag tag) {
        _clickedTag_r = viewTag;
        yShowDialog(tag.id);
    }

    /**
     * Удаление _тега на котором было последнее нажатие
     */
    public void yRemoveClickedTag() {
        if (_clickedTag_r != null) {
            removeView(_clickedTag_r);
        }
    }

    /**
     * Показ диалога по нажатию на _тег
     *
     * @param idTag (1) -- первичный ключ _тега
     */
    public void yShowDialog(final long idTag) {
        if (_presenter == null) return;
        N2260_DialogUniViews d = new N2260_DialogUniViews(((Activity) yContext));

        TextView[] widgets = {};

        TextView tv1 = new TextView(yContext);
        String text1 = _presenter.getDialogText1(idTag);
        if (text1 != null && text1.length() > 0) {
            tv1.setText(text1);
            if (_config._dialog_text1_tcf != null) _config._dialog_text1_tcf.apply_w282w(tv1);
            widgets = ArrayUtils.add(widgets, tv1);
        }

        TextView tv2 = new TextView(yContext);
        String text2 = _presenter.getDialogText2(idTag);
        if (text2 != null && text2.length() > 0) {
            tv2.setText(text2);
            if (_config._dialog_text2_tcf != null) _config._dialog_text2_tcf.apply_w282w(tv2);
            widgets = ArrayUtils.add(widgets, tv2);
        }

        TextView tv3 = new TextView(yContext);
        String text3 = _presenter.getDialogText3(idTag);
        if (text3 != null && text3.length() > 0) {
            tv3.setText(text3);
            if (_config._dialog_text3_tcf != null) _config._dialog_text3_tcf.apply_w282w(tv3);
            widgets = ArrayUtils.add(widgets, tv3);
        }

        d.withBtnNegativeText(_config._dialogBtnDelText);
        d.withPresenter(new N2260_DialogUniViews.N2260_Presenter() {
            @Override
            public void n2255_btnOkClick() {

            }

            @Override
            public void n2255_btnNegativeClick() {
                if (_presenter != null) _presenter.onDeleteTag(idTag);
            }

            @Override
            public void n2255_btnNeytralClick() {

            }

            @Override
            public void n2255_onDismissDialog() {

            }
        });

        d.show(widgets);
    }

    /**
     * Представление _тега
     */
    public static class N2259_Tag implements Comparable<N2259_Tag> {
        public long id = -1L;
        public String text1;
        public String text2;
        public TwoColors text1_tc;
        public Color2 bgColor;

        @Override
        public int compareTo(N2259_Tag another) {
            if (another != null)
                return text1.compareTo(another.text1);
            return 0;
        }
    }

    public static class N2259_Config {
        public int _laytag = R.layout.n2259_laytag;
        public LinearLayoutConfigs _laytag_llConfigs;
        public Drawable _btndel_drw;
        public boolean _btndel_isVisible = true;
        public Color2 _btndel_colorNormal = new Color2(Color.BLUE);
        public Color2 _btndel_colorPressed = new Color2(Color.RED);
        public Color2 _btndel_colorDisabled = new Color2(Color.GRAY);
        public TextConfigs _dialog_text1_tcf;
        public TextConfigs _dialog_text2_tcf;
        public TextConfigs _dialog_text3_tcf;
        public String _dialogBtnDelText = "Delete";

        public N2259_Config() {
        }
    }
}

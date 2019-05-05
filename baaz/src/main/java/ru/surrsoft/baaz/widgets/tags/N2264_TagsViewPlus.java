package ru.surrsoft.baaz.widgets.tags;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import ru.surrsoft.baaz.R;
import ru.surrsoft.baaz.univers.Color2;
import ru.surrsoft.baaz.univers.EDrawables;
import ru.surrsoft.baaz.widgets.buttons.N2249_BtnPNG;

/**
 * Развитие {@link N2259_TagsView} - добавляет кнопку "+" для "дозированного" добавления новых _тегов
 * <p/>
 * После создания и конфигурирования необходим {@link #yCommit()}
 * <p/>
 * <li>ЭЛЕМЕНТЫ</li>
 * <li> -- _btnPlus - кнопка добавления новых тегов</li>
 * <li> -- _tagsView - элемент {@link N2259_TagsView}; принимает на вход _теги, сообщает о желании удалить тег </li>
 * <p/>
 * #version 1 08.06.2016  #id [[w264w]]
 */
public class N2264_TagsViewPlus extends FrameLayout {


    private N2249_BtnPNG _btnPlus;
    public N2259_TagsView _tagsView;
    public N2264_Config _config;
    public N2264_Presenter _presenter;

    public interface N2264_Presenter {
        /**
         * Нажатие на кнопку _btnPlus
         */
        void n2264_onClickBtnPlus();

        /**
         * Запрос на удаление _тега
         *
         * @param idTag (1) --
         * @return TRUE если можно удалить _тег из контейнера
         */
        boolean n2264_onDeleteTag(long idTag);

        String getDialogText1(long id);

        String getDialogText2(long id);

        String getDialogText3(long id);
    }

    //1 //constructors
    public N2264_TagsViewPlus(Context context) {
        super(context);
        init(context);
    }

    public N2264_TagsViewPlus(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public N2264_TagsViewPlus(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    //2 //constructors

    private void init(Context context) {
        _config = new N2264_Config();
        LayoutInflater.from(context).inflate(R.layout.n2264_lay, this, true); //layout
        _btnPlus = ((N2249_BtnPNG) findViewById(R.id.n2264_btnPlus));
        _tagsView = ((N2259_TagsView) findViewById(R.id.n2264_tagsView));

    }

    public void yCommit() {
        //===
//        _btnPlus.ySetParams(_config._btnPlus_drw, _config._btnPlus_colorNormal,
//                _config._btnPlus_colorPressed, _config._btnPlus_colorDisabled);
//        int px = G67G_Draw.px(_config._btnPlus_drw_paddingDp);
//        _btnPlus.setPadding(px, px, px, px);
        _config._btnPlus_cfg.apply_w282w(_btnPlus);
        _btnPlus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_presenter != null) _presenter.n2264_onClickBtnPlus();
            }
        });

        //===
        if (_config._tagsView_config != null) {
            _tagsView._config = _config._tagsView_config;
        }
        _tagsView._presenter = new N2259_TagsView.N2259_Presenter() {
            @Override
            public void onDeleteTag(long idTag) {
                if (_presenter != null) {
                    boolean b = _presenter.n2264_onDeleteTag(idTag);
                    if (b) {
                        _tagsView.yRemoveClickedTag();
                    }
                }
            }

            @Override
            public void onClickBtnDel(long idTag) {
                _tagsView.yShowDialog(idTag);
            }

            @Override
            public String getDialogText1(long id) {
                if (_presenter != null) return _presenter.getDialogText1(id);
                return null;
            }

            @Override
            public String getDialogText2(long id) {
                if (_presenter != null) return _presenter.getDialogText2(id);
                return null;
            }

            @Override
            public String getDialogText3(long id) {
                if (_presenter != null) return _presenter.getDialogText3(id);
                return null;
            }
        };
    }

    public void ySetTags(N2259_TagsView.N2259_Tag[] tags) {
        _tagsView.ySetTags(tags);
    }

    public static class N2264_Config {
        public N2249_BtnPNG.N2249_Configs _btnPlus_cfg;
        public N2259_TagsView.N2259_Config _tagsView_config;

        public N2264_Config() {
            _btnPlus_cfg = new N2249_BtnPNG.N2249_Configs();
            _btnPlus_cfg.drwName = EDrawables._LAUNCHER.name();
            _btnPlus_cfg.normalColor = new Color2(Color.BLUE);

            _tagsView_config = new N2259_TagsView.N2259_Config();
        }

    }
}

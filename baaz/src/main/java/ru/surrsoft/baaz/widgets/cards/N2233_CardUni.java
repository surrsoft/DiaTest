package ru.surrsoft.baaz.widgets.cards;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.R;
import ru.surrsoft.baaz.univers.Color2;
import ru.surrsoft.baaz.widgets.appearances.N2215_DrwIcon;
import ru.surrsoft.baaz.widgets.buttons.N2249_BtnPNG;

/**
 * Универсальная карточка
 * <p/>
 * Элементы:
 * <li> _laycontent - контейнер для элементов, </li>
 * <li> _btnopt - по нажатию открывается popup-список действий </li>
 * <li> _icon - иконка слева </li>
 * <p/>
 * Чтобы _btnopt не отображался, нужно установить _config._btnopt_menuResId = 0
 * <p/>
 * Использование: подготовить массив из виджетов и передать его в _config._views;
 * границы можно установить например так { G67G.Misc.setMargins(card, 8, "t", true, false); };
 * для получения обратных ответов следует использовать {@link #_presenter}
 * <p/>
 * #version 2 03-06-2016  #id [[w233w]]
 */
public class N2233_CardUni extends FrameLayout {


  private LinearLayout _laycontent;
  public View[] views;
  public String[] btnopt_texts;
  public OnClickListener[] btnopt_listeners;
  public N2233_Presenter _presenter;
  private Context mContext;
  private N2249_BtnPNG _btnopt;
  private PopupMenu popMenu;
  public N2233_Config _config;
  private ImageView _icon;

  public interface N2233_Presenter {
    /**
     * Нажатие на this
     */
    void n2233_onClickLay();

    /**
     * Нажатие на элементе popup-меню
     *
     * @param menuItem (1) -- нажатый элемент
     */
    void n2233_btnopt_onClick(MenuItem menuItem);

  }

  //1 //constructor -----------------------------------------------------------------------------
  public N2233_CardUni(Context context) {
    super(context);
    init(context);
  }

  public N2233_CardUni(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public N2233_CardUni(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }
  //2 //constructor -----------------------------------------------------------------------------

  private void init(Context context) {
    _config = new N2233_Config();
    mContext = context;
    this.setClickable(true);
    this.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if (_presenter != null) {
          _presenter.n2233_onClickLay();
        }
      }
    });
    LayoutInflater.from(context).inflate(R.layout.n2233_lay, this); //layout
    _laycontent = (LinearLayout) findViewById(R.id.n2233_laycontent);
    _btnopt = (N2249_BtnPNG) findViewById(R.id.n2233_btnopt);
    _icon = ((ImageView) findViewById(R.id.n2233_icon));

    //===
    _btnopt.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        popMenu.show();
      }
    });
  }

  /**
   * Применяет параметры заданные в {@link #_config}
   */
  public void yCommit() {
    //===
    this.views = _config._views;
    for (View elem : views) {
      _laycontent.addView(elem);
    }
    //===
    _btnopt.ySetParams(
      _config._btnopt_drw,
      _config._btnopt_drwColorNormal,
      _config._btnopt_drwColorPressed,
      _config._btnopt_drwColorDisabled
    );
    int px = G67G_Draw.px(7);
    _btnopt.setPadding(px, px, px, px);
    //===
    if (_config._btnopt_menuResId == 0) {
      _btnopt.setVisibility(View.GONE);
    } else {
      //
      popMenu = new PopupMenu(mContext, _btnopt);
      popMenu.inflate(_config._btnopt_menuResId);
      popMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
          _presenter.n2233_btnopt_onClick(item);
          return true;
        }
      });
    }
    //=== _icon
    if (_config._icon_drw == null) {
      _icon.setVisibility(View.GONE);
    } else {
      if (_config._icon_tint != null) {
        DrawableCompat.setTint(_config._icon_drw, _config._icon_tint.val);
      }
      _icon.setImageDrawable(_config._icon_drw);
    }
    //===
    if (_config._background != null) _config._background.setDrawedView(this);
  }

  public static class N2233_Config {
    /**
     * {опционально} Объект N2215_DrwIcon для фона
     */
    public N2215_DrwIcon _background;
    /**
     * Виджеты для _контейнера
     */
    public View[] _views = {};
    /**
     * Drawable для _btnopt
     */
    public Drawable _btnopt_drw;
    public Color2 _btnopt_drwColorNormal;
    public Color2 _btnopt_drwColorPressed;
    public Color2 _btnopt_drwColorDisabled;
    /**
     * resId меню для _btnopt; если == 0 то меню не показывается
     */
    public int _btnopt_menuResId;
    /**
     * Drawable для _icon
     */
    public Drawable _icon_drw;
    /**
     * Тонировка для _icon
     */
    public Color2 _icon_tint;
  }
}

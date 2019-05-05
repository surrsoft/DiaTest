package ru.surrsoft.baaz.widgets.recyclerview;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;

import ru.surrsoft.baaz.Bysa_01;
import ru.surrsoft.baaz.cls_c.G67G_Dates;
import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.R;
import ru.surrsoft.baaz.configs.LinearLayoutConfigs;
import ru.surrsoft.baaz.configs.TextConfigs;
import ru.surrsoft.baaz.cls_d.N2256_DragAndSwipe;
import ru.surrsoft.baaz.univers.Color2;
import ru.surrsoft.baaz.univers.EDrawables;
import ru.surrsoft.baaz.univers.EStrings;
import ru.surrsoft.baaz.univers.Langu;
import ru.surrsoft.baaz.univers.Size2;
import ru.surrsoft.baaz.widgets.buttons.N2249_BtnPNG;
import ru.surrsoft.baaz.widgets2.BuilderTV_;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;

/**
 * Универсальный RecyclerView получающий на вход String[]. Обладает Toolbar с поиском (фильтром);
 * позволяет drag&drop, копирование, удаление, добавление, редактирование элементов
 * <p/>
 * СЦЕНАРИИ
 * <li> инстанцирование -- задание String[] в переменную {@link #_ojs} -- задание
 * {@link #_presenter} -- вызов {@link #yCommit()} </li>
 * <p/>
 * ЭЛЕМЕНТЫ
 * <li> -- _elem (_элемент) - элемент списка </li>
 * <li> -- _select (_выбор) -  </li>
 * <li> -- _layBase - this объект </li>
 * <li> -- _rv - RecyclerView </li>
 * <li> -- _toolbar - тулбар </li>
 * <li> -- _checkbox </li>
 * <li> -- _btnMenu </li>
 * <li> -- _modeSwitcher (_переключатель-режимов) -- </li>
 * <p/>
 * ПОМЕТКИ
 * <li> //drag// - drag&drop</li>
 * <p/>
 * #version 1 05.07.2016  #id [[w286w]]
 */
public class N2286_2_RVLangu extends LinearLayout implements N2256_DragAndSwipe.OnStartDragListener {

  private static final String TAG = ":" + N2286_2_RVLangu.class.getSimpleName();


  public Langu[] _ojs;
  public N2286_Configs _cfg;
  public N2286_Presenter _presenter;
  /**
   * Выбранные элементы (их позиции)
   */
  public int[] _selectedPos = {};

  private Context context;
  private Toolbar _toolbar;
  private RecyclerView _rv;
  private RAdapter adapter;
  private ItemTouchHelper mItemTouchHelper;
  private boolean yDragStart; //drag//
  private int yDragPositionFrom; //drag//
  private int yDragPositionTo; //drag//
  /**
   * Изначальный клон {@link #_ojs}. Используется для поиска
   */
  private Langu[] _ojsInitial;
  private String searchText;
  private _01_Modes _01_currMode;

  public interface N2286_Presenter {
    /**
     * Вызывается при любом изменении {@link #_ojs}
     *
     * @param ojs
     */
    void n2286_on(Langu[] ojs);
  }

  public N2286_2_RVLangu(Context context) {
    super(context);
    init(context);
  }

  public N2286_2_RVLangu(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public N2286_2_RVLangu(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  private void init(final Context ctx) {
    this.context = ctx;
    this.setOrientation(VERTICAL);
    LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
      ViewGroup.LayoutParams.WRAP_CONTENT);
    this.setLayoutParams(lp);

    //===
    _cfg = new N2286_Configs(ctx);

    //=== toolbar
    _toolbar = new Toolbar(ctx);
    Menu menu = _toolbar.getMenu();

    MenuItem search = menu.add(EStrings._SEARCH.val());
    MenuItem add = menu.add(EStrings._ADD.val());
    MenuItem dublicate = menu.add(EStrings._DUBLICATE.val());
    MenuItem delete = menu.add(EStrings._DELETE.val());
    final SubMenu select = menu.addSubMenu(EStrings._SELECT2.val() + "...");
    MenuItem selectAll = select.add(EStrings._SELECT_ALL.val());
    MenuItem selectInvert = select.add(EStrings._SELECT_INVERT.val());
    MenuItem selectDeselectAll = select.add(EStrings._SELECT_DESELECT_ALL.val());

    selectAll.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem item) {
        ySelectReset();
        for (int i = 0; i < _ojs.length; i++) {
          _selectedPos = ArrayUtils.add(_selectedPos, i);
        }
        yUpdateAdapter();
        yUpdateWidgetSelectedCount();
        return true;
      }
    });
    selectInvert.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem item) {
        int[] arr = {};
        for (int i = 0; i < _ojs.length; i++) {
          if (ArrayUtils.indexOf(_selectedPos, i) == -1) {
            arr = ArrayUtils.add(arr, i);
          }
        }
        _selectedPos = arr;
        yUpdateAdapter();
        yUpdateWidgetSelectedCount();
        return true;
      }
    });
    selectDeselectAll.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem item) {
        ySelectReset();
        yUpdateAdapter();
        yUpdateWidgetSelectedCount();
        return true;
      }
    });

    add.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    add.setIcon(R.mipmap.n2286_b_plus);
    add.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem item) {
        Langu langu = new Langu();
        langu._key = G67G_Dates.getTimestamp() + "";
        _ojs = ArrayUtils.add(_ojs, 0, langu);
        adapter.notifyItemInserted(0);
        yUpdateWidgetCountElems();
        return true;
      }
    });
    dublicate.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem item) {
        if (_selectedPos.length > 0) {
          for (int elem : _selectedPos) {
            _ojs = ArrayUtils.add(_ojs, _ojs[elem]);
          }
          ySelectReset();
          yUpdateWidgetSelectedCount();
          yUpdateAdapter();
          if (_presenter != null) _presenter.n2286_on(_ojs);
        }
        return true;
      }
    });
    delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem item) {
        _ojs = ArrayUtils.removeAll(_ojs, _selectedPos);
        ySelectReset();
        yUpdateWidgetSelectedCount();
        yUpdateAdapter();
        if (_presenter != null) _presenter.n2286_on(_ojs);
        return true;
      }
    });

    SearchView sv = new SearchView(ctx);
    sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        return false;
      }

      @Override
      public boolean onQueryTextChange(String newText) {

//                searchText = newText;
//                if (newText.length() > 0) {
//                    String[] arr = new String[]{};
//                    for (String elem : _ojsInitial) {
//                        if (elem.indexOf(newText) != -1) {
//                            arr = ArrayUtils.add(arr, elem);
//                        }
//                    }
//                    _ojs = arr;
//                } else {
//                    _ojs = _ojsInitial;
//                }
//                ySelectReset();
//                yUpdateWidgetSelectedCount();
//                yUpdateAdapter();
        return true;
      }
    });
    sv.setOnCloseListener(new SearchView.OnCloseListener() {
      @Override
      public boolean onClose() {
        searchText = null;
        return false;
      }
    });
    search.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    search.setActionView(sv);

    this.addView(_toolbar);

//        //=== _modeSwitcher
//        N2302_TextViewSwitcher _modeSwitcher = new N2302_TextViewSwitcher(context);
//        _modeSwitcher._prestenter = new N2302_TextViewSwitcher.N2302_Presenter() {
//            @Override
//            public void n2302_on(int stateIndex, boolean first) {
//                Log2_01.i(TAG, "--> n2302_on()", LOG2);
//                if (!first) {
//                    _selectedPos = new int[]{};
//                    yUpdateAdapter();
//                    yUpdateWidgetCountElems();
//                    yUpdateWidgetSelectedCount();
//                }
//            }
//        };
//        _modeSwitcher._cfg.stringLs = new StringL[]{
//                new StringL(EStrings.N2286_2_1.name()),
//                new StringL(EStrings.N2286_2_2.name())};
//        _modeSwitcher._cfg.tcf.textColor = new Color2(Color.BLUE);
//        _modeSwitcher._cfg.tcf.textColorPressed = new Color2(Color.RED);
//        _modeSwitcher.yCommit();
//        this.addView(_modeSwitcher);

    //=== RecyclerView
    _rv = new RecyclerView(ctx);
    _rv.setScrollbarFadingEnabled(true);
    LinearLayoutManager llm = new LinearLayoutManager(ctx);
    _rv.setLayoutManager(llm);
    _rv.addItemDecoration(new DividerItemDecoration());

    this.addView(_rv);
  }

  private void yUpdateAdapter() {
    adapter.notifyDataSetChanged();
    yUpdateWidgetCountElems();
  }

  private void yUpdateWidgetCountElems() {
    if (_toolbar != null) {
      if (_ojs.length > 0) {
        _toolbar.setSubtitle(EStrings._COUNT.val() + ": " + _ojs.length + "");
      } else {
        _toolbar.setSubtitle("");
      }
    }
  }

  public void yCommit() {
    _01_currMode = _cfg._01_startMode;
    _cfg._layBase_cfg.apply_w282w(this);
    adapter = new RAdapter();

    _ojsInitial = ArrayUtils.clone(_ojs);

    mItemTouchHelper = new ItemTouchHelper(new N2256_DragAndSwipe(adapter)); //drag//
    mItemTouchHelper.attachToRecyclerView(_rv);

    _rv.setAdapter(adapter);
    yUpdateWidgetCountElems();
  }

  //drag//
  @Override
  public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
    mItemTouchHelper.startDrag(viewHolder);
  }

  private class RAdapter extends RecyclerView.Adapter implements N2256_DragAndSwipe.ItemTouchHelperAdapter {
    public RAdapter() {
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      CardView cardView = new CardView(context);
      RelativeLayout wraplay = (RelativeLayout) LayoutInflater.from(context).inflate(
        R.layout.n2286_wraplay, null);
      cardView.addView(wraplay);
      return new Holder(cardView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
      Holder h = (Holder) holder;

      h._layContent.removeAllViews();
      Langu langu12 = _ojs[position];

      LinearLayout widget = langu12.getWidget_06(Bysa_01.confLanguagesList, context, null);
      h._layContent.addView(widget);

      h._pos = position;
      h._checkbox.setOnCheckedChangeListener(null);
      h._checkbox.setChecked(ArrayUtils.indexOf(_selectedPos, position) != -1);
      h._checkbox.setOnCheckedChangeListener(h._checkboxLst);
    }

    @Override
    public int getItemCount() {
      return _ojs.length;
    }

    //1 //drag//
    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
      if (yDragStart) {
        yDragPositionFrom = fromPosition;
        yDragStart = false;
      }
      yDragPositionTo = toPosition;
      notifyItemMoved(fromPosition, toPosition);
      return true;

    }

    //вызывается по окончании swipe
    @Override
    public void onItemDismiss(int position) {
      yDragStart = false;
    }
    //2 drag

    public class Holder extends RecyclerView.ViewHolder implements N2256_DragAndSwipe.ItemTouchHelperViewHolder {
      public final CheckBox _checkbox;
      public final CompoundButton.OnCheckedChangeListener _checkboxLst;
      private final LinearLayout _layContent;
      public int _pos;

      public Holder(CardView cardView) {
        super(cardView);

        _layContent = (LinearLayout) cardView.findViewById(R.id.n2286_lay);
        _layContent.setPadding(G67G_Draw.px(5), 0, 0, 0);
        final N2249_BtnPNG btnMenu = (N2249_BtnPNG) cardView.findViewById(R.id.n2286_btnMenu);
        _checkbox = (CheckBox) cardView.findViewById(R.id.n2286_checkbox);

        _cfg._btnMenu_cfg.apply_w282w(btnMenu);
        btnMenu.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View v) {
            PopupMenu pm = new PopupMenu(context, btnMenu);
            Menu menu = pm.getMenu();
            MenuItem pmEdit = menu.add(EStrings._EDIT.val());
            MenuItem pmDelete = menu.add(EStrings._DELETE.val());
            pmEdit.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
              @Override
              public boolean onMenuItemClick(MenuItem item) {

//                                N2287_DialogTextInput d = new N2287_DialogTextInput(context);
//                                d._cfg._edit_text = _ojs[_pos];
//                                d._presenter = new N2287_DialogTextInput.N2287_Presenter() {
//                                    @Override
//                                    public void n2287_ok(String text) {
//                                        Log2_01.i(TAG, "--> n2287_ok()", LOG2);
//                                        yElemEdit(text, _pos);
//                                    }
//                                };
//                                d.create().show();
                return true;
              }
            });
            pmDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
              @Override
              public boolean onMenuItemClick(MenuItem item) {
                yElemDelete(_pos);
                return true;
              }
            });
            pm.show();
          }
        });

        _checkboxLst = new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
              _selectedPos = ArrayUtils.add(_selectedPos, _pos);
            } else {
              _selectedPos = ArrayUtils.removeElement(_selectedPos, _pos);
            }
            yUpdateWidgetSelectedCount();
          }
        };
        _checkbox.setOnCheckedChangeListener(_checkboxLst);

      }


      //1 //drag//
      @Override
      public void onItemSelected() {
        yDragStart = true;
      }

      /**
       * Вызывается по окончании move или swipe
       */
      @Override
      public void onItemClear() {
        //изменить порядок
        Langu from = _ojs[yDragPositionFrom];
        Langu to = _ojs[yDragPositionTo];
        _ojs[yDragPositionFrom] = to;
        _ojs[yDragPositionTo] = from;
        if (_presenter != null) _presenter.n2286_on(_ojs);
        adapter.notifyDataSetChanged();
      }
      //2 drag
    }

  }

  private void yUpdateWidgetSelectedCount() {
    if (_selectedPos.length == 0) {
      _toolbar.setTitle("");
    } else {
      _toolbar.setTitle("(" + _selectedPos.length + ")");
    }
  }

  /**
   * Класс обеспечивающий промежутки между элементами списка
   * <p/>
   * Задается для RecyclerView с помощью метода RecyclerView.addItemDecoration
   * <p/>
   * См. https://www.bignerdranch.com/blog/a-view-divided-adding-dividers-to-your-recyclerview-with-itemdecoration/
   */
  public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    public DividerItemDecoration() {
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
      super.getItemOffsets(outRect, view, parent, state);
      if (parent.getChildAdapterPosition(view) == 0) return;
      outRect.top = G67G_Draw.px(_cfg._gapBetweenCards_db);
    }
  }

  private SpannableString getSpannableString(String text) { //1search1
    SpannableString textA_span = new SpannableString(text);
    if (searchText != null) {
      int i = text.toLowerCase().indexOf(searchText.toLowerCase());
      if (i != -1) {
        textA_span.setSpan(new BackgroundColorSpan(_cfg._searchColorHighlight.val),
          i, i + searchText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      }
    }
    return textA_span;
  }


  /**
   * Добавление нового _элемента. Вызывается по нажатию ОК в диалге добавления нового _элемента
   *
   * @param oj (1) --
   */
  private void yElemAdd(Langu oj) {
    _ojs = ArrayUtils.add(_ojs, oj);
    yUpdateAdapter();
    if (_presenter != null) _presenter.n2286_on(_ojs);
  }

  /**
   * Действие по применению редактирования _элемента. Вызывается по нажатию ОК в диалоге редактирования
   *
   * @param oj  (1) -- новый текст
   * @param pos (2) -- позиция отредактированного _элемента
   */
  private void yElemEdit(Langu oj, int pos) {
    yUpdateAdapter();
    if (_presenter != null) _presenter.n2286_on(_ojs);
  }

  private void yElemDelete(int pos) {
    _ojs = ArrayUtils.remove(_ojs, pos);
    if (_selectedPos.length > 0) {
      _selectedPos = ArrayUtils.removeElement(_selectedPos, pos);
    }
    yUpdateWidgetSelectedCount();
    adapter.notifyItemRemoved(pos);
    yUpdateWidgetCountElems();
    if (_presenter != null) _presenter.n2286_on(_ojs);
  }

  /**
   * Сброс _выбора
   */
  private void ySelectReset() {
    _selectedPos = new int[]{};
    yUpdateWidgetSelectedCount();
  }

  private class DialogLangu {
    public DialogLangu(Langu langu) {
      LinearLayout lay = new BuLayLinear_01(context).build();

      AlertDialog.Builder b = new AlertDialog.Builder(context);
      Field[] fields = langu.getClass().getFields();
      for (Field elem : fields) {
        LinearLayout layIn = new BuLayLinear_01(context).build();
        TextView tvTitle = BuilderTV_.create(elem.getType().getSimpleName(), Color.BLUE);
        layIn.addView(tvTitle);

        if (elem.getType().equals(String.class)) {
          try {
            String value = (String) elem.get(langu);
            TextView tvValue = BuilderTV_.create(value, Color.RED);
            layIn.addView(tvValue);
          } catch (IllegalAccessException e) {
            e.printStackTrace();
          }
        }
        lay.addView(layIn);
      }
      b.setView(lay);
      b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
        }
      });
      b.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

        }
      });
      b.create().show();
    }
  }

  /**
   * Режимы работы this
   */
  public enum _01_Modes {
    /**
     * Режим в котором можно выбрать только один элемент
     */
    _01_MODE_ONE_SELECT,
    /**
     * Режим в котором можно выбрать несколько элементов
     */
    _01_MODE_MULT_SELECT,
    /**
     * Режим в котором возможно редактирование элементов (редактирование, добавление, удаление)
     */
    _01_MODE_EDIT
  }

  public static class N2286_Configs {
    public LinearLayoutConfigs _layBase_cfg;
    public N2249_BtnPNG.N2249_Configs _btnMenu_cfg;
    public final Color2 _searchColorHighlight;
    /**
     * Расстояние между карточками
     */
    public int _gapBetweenCards_db = 2;
    public TextConfigs _oj_cfg;
    /**
     * Режим на старте this
     */
    public _01_Modes _01_startMode = _01_Modes._01_MODE_EDIT;

    public N2286_Configs(Context ctx) {
      _layBase_cfg = new LinearLayoutConfigs();
      _layBase_cfg.sizeWidth = new Size2(ViewGroup.LayoutParams.MATCH_PARENT);
      _layBase_cfg.sizeHeight = new Size2(ViewGroup.LayoutParams.MATCH_PARENT);
      _layBase_cfg.paddingBottom_dp = 16;
      _layBase_cfg.paddingLeft_dp = 16;
      _layBase_cfg.paddingRight_dp = 16;
      _layBase_cfg.paddingBottom_dp = 16;

      _btnMenu_cfg = new N2249_BtnPNG.N2249_Configs();
      _btnMenu_cfg.drwName = EDrawables.N2241_B_DOTS.name();
      _btnMenu_cfg.normalColor = new Color2(Color.DKGRAY);
      _btnMenu_cfg.pressedColor = new Color2(Color.RED);
      _btnMenu_cfg.drwPaddings_dp = 7;

      _searchColorHighlight = new Color2(Color.YELLOW);

      _oj_cfg = new TextConfigs();
      _oj_cfg.paddingLeft_dp = 5;
      _oj_cfg.paddingRight_dp = 5;
      _oj_cfg.textColor = new Color2(Color.BLACK);

    }
  }


}

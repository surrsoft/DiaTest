package ru.surrsoft.baaz.widgets2.pkor_b;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import ru.surrsoft.baaz.R;
import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.logs.Vdin_01;
import ru.surrsoft.baaz.suite.figures.DrawerBank_01;
import ru.surrsoft.baaz.suite.figures.N1208_Check;
import ru.surrsoft.baaz.suite.figures.N1208_DragVertical;
import ru.surrsoft.baaz.tclasses.TArrayList_01;
import ru.surrsoft.baaz.univers.Avgb;
import ru.surrsoft.baaz.univers.Color2;
import ru.surrsoft.baaz.univers.EStrings;
import ru.surrsoft.baaz.univers.ETextCase;
import ru.surrsoft.baaz.univers.Trans;
import ru.surrsoft.baaz.univers.U;
import ru.surrsoft.baaz.widgets2.BuDialogConfirm;
import ru.surrsoft.baaz.widgets2.BuFAB;
import ru.surrsoft.baaz.widgets2.BuProgressDialog;
import ru.surrsoft.baaz.widgets2.BuilderTV;
import ru.surrsoft.baaz.widgets2.Huta_b_v_;
import ru.surrsoft.baaz.widgets2.WgCbav;
import ru.surrsoft.baaz.widgets2.WgOdba;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;
import ru.surrsoft.baaz.widgets2.buLay.BuLayRelative_01;
import ru.surrsoft.baaz.widgets2.forwg.Margins_01;
import ru.surrsoft.baaz.widgets2.nviews.NCheckBox;
import ru.surrsoft.baaz.widgets2.nviews.NLinearLayout;
import ru.surrsoft.baaz.widgets2.nviews.NRelativeLayout;
import ru.surrsoft.baaz.widgets2.pkor_b.a.CfgVertScrollbar;
import ru.surrsoft.baaz.widgets2.pkor_b.a.Dwzr;
import ru.surrsoft.baaz.widgets2.pkor_b.a.EDragMode;
import ru.surrsoft.baaz.widgets2.pkor_b.a.Presenter;
import ru.surrsoft.baaz.widgets2.pkor_b.a.PresenterLocal;
import ru.surrsoft.baaz.widgets2.pkor_b.b.MenuElem;
import ru.surrsoft.baaz.widgets2.utils.BuCSL;
import ru.surrsoft.baaz.widgets2.utils.BuPopupMenu;

/**
 * {есть демо - см. ABaazDemo}
 * <p>
 * [[pkor]] - это RecyclerView обернутый в [odba] (выездающая панелька с кнопкой удаления и
 * другими). Элементы (_рв-элементы)
 * выбираются их слайдом, при этом появляется слайдер редактирования.
 * Работает с наборами бесконечной длины. Набор представлен классом имплементирующим {@link Dwzr};
 * этот класс должен быть передан сюда посредством метода {@link #buDwzr(Dwzr)}.
 * <p>
 * //--- ОПЦИИ
 * -- dragAndDrop задаётся методом {@link #buDragAndDrop(EDragMode)}
 * -- чтобы работали сдвиги карточек, задействовать {@link #buPresenter(Presenter)}
 * -- для обновления содержимого предназначен метод {@link #refresh(Dwzr)}
 * -- [[pvfa]]:: свои пункты меню можно задавать через {@link #buMenuElemAdd(MenuElem)}. По умолчанию всегда
 * присутствует пункт меню "Удалить". Из View, который будет передаваться в презентер при нажатии ан
 * пункт меню, можно извлеать {@link TagView}
 * <p>
 * //--- ПО УМОЛЧАНИЮ СОЗДАЁТСЯ RecyclerView СО СЛЕДУЮЩИМИ ХАРАКТЕРИСТИКАМИ:
 * -- LayoutManager == LinearLayoutManager
 * -- paddings == 16dp
 * -- расстояние между карточками == 5dp
 * <p>
 * //--- СЛОИСТОСТЬ (снизу вверх):
 * -- FrameLayout - [odba]
 * -- RecyclerView - _rv
 * -- FrameLayout - [cbav]  //link to [170204171000]
 * -- LinearLayout - _layback - разметка которая показывается когда пользователь сдвигает _laycont
 * //link to [170204171100]
 * -- NRelativeLayout - [[_wrapLay]], [[_laycont]], [[_laycontainer]] - разметка-обёртка в которую
 * вставляется
 * разметка кастомная  //link to [170204171101]
 * -- NLinearLayout - слой-вставка для элемента  //[[170204171102]]
 * <p>
 * //СДВИГАНИЕ НА МЕСТО (ЗАКРЫТИЕ) ЭЛЕМЕНТОВ ПРИ ВЫХОДЕ ИЗ РЕДАКТИРОВАНИЯ:
 * //````````````````````````````````````````````
 * используется коллекция mVisibleViews {[[point09]]}. Она всегда содержит ссылки на видимые
 * элементы списка. Это
 * достигается за счет добавления в нее видимых элементов в методе onChildViewAttachedToWindow, и
 * удаление в методе onChildViewDetachedFromWindow. При необходимости "закрыть" элементы,выполняется
 * пробежка по данной коллекции и "закрытие" с плавной анимацией тех элементов которые "открыты"
 * <p>
 * //ТЕХНИЧЕСКИЕ ПОДРОБНОСТИ
 * //````````````````````````````````````````````
 * -- внутренний RecyclerView использует адаптер {@link Adapter}. В этом адаптере генерируется
 * разметка-контейнер (_laycontainer) в которую встраивается кастомная-разметка (_laycustom).
 * -- и разметка-контейнер, и кастомная-разметка, имеют каждая свой ViewHolder; при этом ViewHolder
 * разметки-контейнера держит ссылку на ViewHolder кастомной-разметки.
 * -- ViewHolder кастомной разметки генерируется в {@link #mPresenterOuter}
 * -- корнем отдельного элемента является тип {@link WgCbav}
 * -- список {@link #mVisibleViews} всегда содержит список всех видимых в текущий момент карточек
 * <p>
 * //ПРИМЕЧАНИЯ
 * //````````````````````````````````````````````
 * (*1) - терминология w459w
 * <p>
 * //ПОНЯТИЯ
 * //````````````````````````````````````````````
 * -- [[dyap]], _набор, _collection - исходный набор данных. Представлен сущностью типа {@link Dwzr}
 * -- _rv - вложенный RecyclerView
 * -- _slideBar или _huta - выезжающая панель с количеством выбранных элементов и пр.
 * -- _корневой-виджет - это виджет который передается в конструктор ViewHolder
 * -- _laycustom - разметка которая вставляется в стандартную разметку элементов RecyclerView
 * -- [[iwrb]], _индекс, _index - индекс хранящийся в теге виджетов. См. {описание-1737}
 * -- _м-элемент, _элемент - это объект типа E из исходного набора данных. Смотри так же {описание-1737}
 * -- _рв-элемент - элемент RecyclerView отражающий _м-элемент
 * -- _handle, _ручка - виджет за который пользователь делает drag&drop элементов
 * -- {{описание-1737}}: при биндинге, в tag некоторых виджетов записывается объект TagView
 * инкапсулирующий _индекс и _м-элемент
 * -- [[sdva]] - объект типа {@link TagView} . Ссылка на него хранится в {@link LaycontainerViewHolder} и в
 * некоторых виджетах на которые держит ссылку {@link LaycontainerViewHolder}
 * <p>
 * //МЕСТА
 * //````````````````````````````````````````````
 * -- [170219101600] - цвет фона при нажатии
 * <p>
 * //````````````````````````````````````````````
 * <p>
 * ID: [[w356w]]
 * <p>
 * [zipc]
 *
 * @param <VH> - _тип-токен ViewHolder виджета _laycustom
 * @param <E>  - _тип-токен _м-элементов
 */
public class WgPkor_B<VH extends RecyclerView.ViewHolder, E> {

  private final Context mContext;
  //---
  private Dwzr<VH, E> mDwzr;
  //---
  private Presenter mPresenterOuter;
  //--- paddings
  private int mPaddingL_px;
  private int mPaddingT_px;
  private int mPaddingR_px;
  private int mPaddingB_px;
  //---
  private PresenterLocal mPresenterLocal;
  private int mGap_dp = 5;
  private RecyclerView.LayoutManager mLayoutManager;
  private boolean mIsDraged;
  private Adapter mAdapter;
  private EDragMode mDragMode = EDragMode.LONG_PRESS;
  private ItemTouchHelper mItemTouchHelper;
  private boolean mShowCheckboxes;
  private boolean mShowMenuElem;
  private int mSelectCount;
  private Huta_b_v_ mHuta;
  private WgOdba mOdbaBu;
  /**
   * См. [point09]
   */
  private Set<View> mVisibleViews = new HashSet<>();
  private int mPadding_slideBar_dp = 0;
  private int mSpaceBeforeFirstElem_dp;
  private int mSpaceAfterLastElem;
  private boolean mShowFAB = true;
  private Trans<RecyclerView.Adapter> mTransAdapter;
  private CfgVertScrollbar mCfgVertScrollbar;
  private Avgb mAvgb;
  private ViewGroup layParent;
  RecyclerView _rv;
  //если TRUE то RecyclerView будет иметь высоту MATCH_PARENT
  private boolean bMatchParent = true;
  //цвет фона (применяется к layOdba)
  private Color2 bgColor;
  /**
   * Активити в котором работает текущий WgPkor
   */
  private AppCompatActivity mActivity;
  /**
   * Пункты меню
   */
  private ArrayList<MenuElem> menuElems = new ArrayList<>();

  //--- constructors
  public WgPkor_B(Context ctx) {
    Vdin_01.log("создание [pkor] //180317-084600", Vdin_01.E.MIDDLE, this);
    this.mContext = ctx;
    //---
    buPaddings_rv(16);
  }

  //---

  //builders
  //``````````````````````````````````````````````````````````````````````````````````````````````

  //region builders

  //region general

  /**
   * [dwzr]
   *
   * @param dwzr (1) -- сюда подходят: DwzrListSharpref ([gvhf])
   * @return --
   */
  public WgPkor_B buDwzr(Dwzr dwzr) {
    U.se(dwzr == null, "");
    //---
    this.mDwzr = dwzr;
    //--- [avgb]
    this.mAvgb = new Avgb(dwzr.getCount());
    //---
    return this;
  }

  public WgPkor_B buPresenter(Presenter lst) {
    mPresenterOuter = lst;
    return this;
  }

  //endregion general

  //region appearance
  public WgPkor_B buScrollbarVertCfg(CfgVertScrollbar bu) {
    mCfgVertScrollbar = bu;
    return this;
  }

  public WgPkor_B buBgColor(Color2 color) {
    bgColor = color;
    return this;
  }

  //endregion appearance

  public WgPkor_B buAddTo(ViewGroup layParent) {
    this.layParent = layParent;
    return this;
  }

  public WgPkor_B buDragAndDrop(EDragMode edm) {
    mIsDraged = true;
    mDragMode = edm;
    return this;
  }

  //region paddings

  /**
   * paddings вложенного _rv
   *
   * @param dp (1) --
   * @return --
   */
  public WgPkor_B buPaddings_rv(int dp) {
    int px = G67G_Draw.px(dp);
    mPaddingL_px = px;
    mPaddingT_px = px;
    mPaddingR_px = px;
    mPaddingB_px = px;
    return this;
  }

  /**
   * paddings вложенного _rv
   *
   * @return --
   */
  public WgPkor_B buPaddings_rv(int l_dp, int t_dp, int r_dp, int b_dp) {
    mPaddingL_px = G67G_Draw.px(l_dp);
    mPaddingT_px = G67G_Draw.px(t_dp);
    mPaddingR_px = G67G_Draw.px(r_dp);
    mPaddingB_px = G67G_Draw.px(b_dp);
    return this;
  }

  //endregion paddings

  public WgPkor_B buSlideBarPadding(int dp) {
    mPadding_slideBar_dp = dp;
    return this;
  }


  /**
   * @param b (1) --
   * @return --
   */
  public WgPkor_B buHeightMP(boolean b) {
    bMatchParent = b;
    return this;
  }


  /**
   * Зазор между карточками
   *
   * @param dp (1) --
   * @return --
   */
  public WgPkor_B buGap(int dp) {
    mGap_dp = dp;
    return this;
  }


  public WgPkor_B buCheckboxesShow(boolean b) {
    mShowCheckboxes = b;
    return this;
  }

  //region menu

  public WgPkor_B buMenuElemShow(boolean b) {
    mShowMenuElem = b;
    return this;
  }

  /**
   * {возможен многоразовый вызов}
   * <p>
   * См. [pvfa]::
   * <p>
   * Через этот метод добавляем пукты в меню элементов (три точки справа)
   *
   * @param menuElem (1) --
   * @return --
   */
  public WgPkor_B buMenuElemAdd(MenuElem menuElem) {
    U.se(menuElem == null, "");
    //---
    this.menuElems.add(menuElem);
    mShowMenuElem = true;
    return this;
  }

  //endregion menu

  /**
   * Отступ от верха первого элемента
   *
   * @param dp --
   * @return --
   */
  public WgPkor_B buSpaceBeforeFirstElem(int dp) {
    mSpaceBeforeFirstElem_dp = dp;
    return this;
  }

  /**
   * Дополнительное пространство прокрутки после последнего элемента
   *
   * @param dp --
   * @return --
   */
  public WgPkor_B buSpaceAfterLastElem(int dp) {
    mSpaceAfterLastElem = dp;
    return this;
  }

  public WgPkor_B buFABShow(boolean b_defTrue) {
    mShowFAB = b_defTrue;
    return this;
  }

  public WgPkor_B buLinkAdapter(Trans<RecyclerView.Adapter> trans) {
    mTransAdapter = trans;
    return this;
  }

  public WgPkor_B buActivity(AppCompatActivity activity) {
    this.mActivity = activity;
    return this;
  }


  //endregion builders

  //---
  public FrameLayout build() {
    //---
    _rv = U.inflate(R.layout.g67g_rv01, mContext);

    //--- scrollbar
    mtSettScrollbar(_rv);

    //--- [huta] - "выезжающий" виджет с кнопками
    mHuta = mtHutaCreate();

    //--- [odba] - интегратор для _rv и _huta
    mOdbaBu = new WgOdba(mContext)
      .baseView(_rv)
      .slideView(mHuta)
      .padding_slideView(mPadding_slideBar_dp);
    FrameLayout layOdba = mOdbaBu.build();

    //--- FAB
    mtFAB(layOdba);

    //---
    mPresenterLocal = new PresenterLocal() {
      @Override
      public void onChangeSelectedCount(int count) {
        mHuta.n1462_countUpdate(count, count > 0);
        mOdbaBu.showSlideView(count > 0);
      }
    };

    //--- layout manager
    if (mLayoutManager == null) {
      mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
    }
    _rv.setLayoutManager(mLayoutManager);

    //--- paddings
    _rv.setPadding(mPaddingL_px, mPaddingT_px, mPaddingR_px, mPaddingB_px);

    //--- gaps
    if (mGap_dp > 0) {
      DividerItemDecoration d = new DividerItemDecoration();
      _rv.addItemDecoration(d);
    }

    //--- drag&drop
    if (mIsDraged) {
      int iDragDirs = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
      int iSwipeDirs = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
      //---
      MSimpleCallback callback = new MSimpleCallback(iDragDirs, iSwipeDirs);
      mItemTouchHelper = new ItemTouchHelper(callback);
      //---
      mItemTouchHelper.attachToRecyclerView(_rv);
    }

    //---
    mAdapter = new Adapter();
    _rv.setAdapter(mAdapter);

    //---
    if (mTransAdapter != null) {
      mTransAdapter.val = mAdapter;
    }

    //--- решение проблемы [w461w]
    _rv.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
      @Override
      public void onChildViewAttachedToWindow(@NonNull View view) {
        //--- см. [point09]
        mtVisibleViewsAdd(view);
        //---
        WgCbav cbav = (WgCbav) view;
        int index = mtGetIndex_x7(cbav);
        boolean isCheck = mAvgb.checklistIsChecked(index);
        cbav.openState(isCheck);
      }

      @Override
      public void onChildViewDetachedFromWindow(@NonNull View view) {
        mtVisibleViewsRemove(view);
      }
    });

    //---
    if (bMatchParent) {
      layOdba.setLayoutParams(new FrameLayout.LayoutParams(U.MP, U.MP));
    }

    //---
    if (bgColor != null) {
      layOdba.setBackgroundColor(bgColor.val);
    }

    //---
    if (layParent != null) {
      layParent.addView(layOdba);
    }

    //---
    return layOdba;
  }

  /**
   * Настройки FAB
   *
   * @param layOdba (1) -- [odba]
   */
  private void mtFAB(FrameLayout layOdba) {
    if (mShowFAB) {
      FloatingActionButton fab = ((BuFAB) new BuFAB(mContext)
        .margins(new Margins_01(0, 0, 32, 32))
        .layParamClass(FrameLayout.LayoutParams.class)
        .bgColors(new BuCSL()
          .pressed(U.c2(Color.CYAN))
          .normal(U.c2(Color.WHITE))
        )
      ).build();
      //---
      fab.setOnClickListener(v -> {
        //event//
        Vdin_01.logStart("EVENT-> нажата FAB; //21.04.2019-18:04-504", WgPkor_B.this);
        U.se(mActivity == null, "");
        //---
        mDwzr.add(mActivity);
        //---
        Vdin_01.logEnd("", this);
      });
      //---
      layOdba.addView(fab);
    }
  }

  /**
   * Установки скроллбара
   *
   * @param rv (1) --
   */
  private void mtSettScrollbar(RecyclerView rv) {
    rv.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
    if (mCfgVertScrollbar != null) {
      if (mCfgVertScrollbar.isDisable()) {
        rv.setVerticalScrollBarEnabled(false);
      }
      if (mCfgVertScrollbar.isStyleInsideOverlay()) {
        rv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
      }
    }
  }

  private void mtVisibleViewsAdd(View view) {
    mVisibleViews.add(view);
  }

  private void mtVisibleViewsRemove(View view) {
    mVisibleViews.remove(view);
  }

  /**
   * Получение _huta
   * <p>
   * [huta] - выезжающий виджет с кнопками
   *
   * @return --
   */
  private Huta_b_v_ mtHutaCreate() {
    Huta_b_v_ huta_a = new Huta_b_v_(mContext)
      .marginT(mPadding_slideBar_dp)
      .presenter(new Huta_b_v_.Presenter_x_() {
        @Override
        public void onCancel() { //link to [w465w-2201]
          //event//
          Vdin_01.logStart("EVENT-> Huta: onCancel(...); //21.04.2019-9:24-587", WgPkor_B.this);
          //--- плавное закрытие сдвигов
          for (View elem : mVisibleViews) {
            WgCbav cbav = (WgCbav) elem;
            cbav.openSmooth(false);
          }
          //---
          Vdin_01.logM("дечекание всех элементов; //21.04.2019-9:26-599", WgPkor_B.this);
          mAvgb.checklistCheckOrDecheckAll(false);
          //---
          mtDoneEdit_x2();
          Vdin_01.logEnd("", this);
        }

        @Override
        public void onDelete() { //link to [w465w-1555]
          Vdin_01.logStart("EVENT-> Huta: onDelete(...); //21.04.2019-9:24-608", WgPkor_B.this);
          //--- возможность клиенту подправить пометки подлежащих к удалению
          if (mPresenterOuter != null) {
            mPresenterOuter.beforeDelete();
          }
          //---
          final int[] min = {-1};
          //--- удаление элементов
          //--- сначала только обновляем интерфейс
          //_чеклист в формате [novd]
          Map<Long, Long> checkListNovd = mAvgb.checkListGet_B();
          //организуем обратную итерацию
          Iterator<Long> it = new LinkedList<>(checkListNovd.keySet()).descendingIterator();
          while (it.hasNext()) { //LOOP
            Long gKey = it.next();
            Long gVal = checkListNovd.get(gKey);
            //размер нужно менять иначе в интерфейсе будут лишние элементы отображены
            mAvgb.sizeSet(mAvgb.sizeGet() - gVal);
            //---
            mAdapter.notifyItemRangeRemoved(gKey.intValue(), gVal.intValue());
          } //LOOP

          //--- затем в фоне удаляем из источника данных
          new BuProgressDialog(mContext)
            .message(EStrings._DELETE4.val() + "...")
            .task(() -> {
              Set<Long> checklistMvrc = mAvgb.checklistGet();
              //обратная итерация, иначе будет удалена только половина записей
              Iterator<Long> it53 = new LinkedList<>(checklistMvrc).descendingIterator();
              while (it53.hasNext()) { //LOOP
                Long gIndex = it53.next();
                mDwzr.remove(gIndex.intValue());
                min[0] = gIndex.intValue();
              } //LOOP
              mAvgb.checklistClear();
            })
            .createAndShow(() -> mtDeleteAbout_x2(min));
          //---

          Vdin_01.logEnd("", this);
        }

        private void mtDeleteAbout_x2(int[] min) {
          mtDoneEdit_x2();
          //--- нужно для обновления _индексов ([iwrb]) виджетов
          mAdapter.notifyItemRangeChanged(min[0], ((int) mAvgb.sizeGet()) - min[0]);
          //---
          mtChangeToPresenterOuter_x4();
        }

        @Override
        public void onSelectAll() {
          //--- плавное открытие сдвигов
          for (View elem : mVisibleViews) {
            WgCbav cbav = (WgCbav) elem;
            cbav.openSmooth(true);
          }
          //---
          mAvgb.checklistCheckOrDecheckAll(true);
          //---
          mSelectCount = ((int) mAvgb.sizeGet());
          //---
          mHuta.n1462_countUpdate(mSelectCount, true);
        }

        @Override
        public void onSelectInvert() {
          //--- плавное открытие/закрытие сдвигов
          for (View elem : mVisibleViews) {
            WgCbav cbav = (WgCbav) elem;
            cbav.openSmooth(!cbav.isOpen());
          }
          //---
          mAvgb.checklistInvert();
          //---
          mSelectCount = (int) mAvgb.checklistSize();
          //---
          mHuta.n1462_countUpdate(mSelectCount, mSelectCount > 0);
          if (mSelectCount == 0) {
            mOdbaBu.showSlideView(false);
          }
        }
      });
    return huta_a.create();
  }

  private void mtDoneEdit_x2() {
    mSelectCount = 0;
    mHuta.n1462_countUpdate(mSelectCount, false);
    mOdbaBu.showSlideView(false);
  }


  //---

  /**
   * "Дёргалка" которая вызывается Drag&Drop-системой
   */
  private class MSimpleCallback extends ItemTouchHelper.SimpleCallback {

    MSimpleCallback(int dragDirs, int swipeDirs) {
      super(dragDirs, swipeDirs);
    }

    /**
     * Вызывается при перемещении элемента относительно других элементов.
     * Когда делается drag, то (2) может пересекать несколько других элементов; текущий метод будет
     * вызван при каждом таком пересечении
     *
     * @param recyclerView (1) --
     * @param from         (2) -- ViewHolder который переместился
     * @param to           (3) -- ViewHolder на место которого переместился (2)
     * @return --
     */
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView,
                          @NonNull RecyclerView.ViewHolder from,
                          @NonNull RecyclerView.ViewHolder to) {
      mAdapter.swap(from.getAdapterPosition(), to.getAdapterPosition(), from, to);
      return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

    }

    //управление swipe enable
    @Override
    public boolean isItemViewSwipeEnabled() {
      return false;
    }

    //управлением способом старта драга

    /**
     * Если вернём здесь FALSE то запуск drag&drop длинным нажатием не будет запущен
     *
     * @return --
     */
    @Override
    public boolean isLongPressDragEnabled() {
      return mDragMode == EDragMode.LONG_PRESS;
    }
  }


  //---
  private class DividerItemDecoration extends RecyclerView.ItemDecoration {

    //--- constructors
    DividerItemDecoration() {
    }

    //---
    @Override
    public void getItemOffsets(@NonNull Rect outRect,
                               @NonNull View view,
                               @NonNull RecyclerView rv,
                               @NonNull RecyclerView.State state) {
      super.getItemOffsets(outRect, view, rv, state);
      //---
      int pos = mtGetIndex_x7(view);
      if (rv.getChildAdapterPosition(view) == 0) {
        if (mSpaceBeforeFirstElem_dp > 0) {
          outRect.top = G67G_Draw.px(mSpaceBeforeFirstElem_dp);
        }
      } else {
        int size;
        size = ((int) mAvgb.sizeGet());
        if (pos == size - 1) {
          outRect.top = G67G_Draw.px(mGap_dp);
          if (mSpaceAfterLastElem > 0) {
            outRect.bottom = G67G_Draw.px(mSpaceAfterLastElem);
          }
        } else {
          outRect.top = G67G_Draw.px(mGap_dp);
        }
      }
    }
  }

  /**
   * Адаптер
   */
  private class Adapter extends RecyclerView.Adapter<LaycontainerViewHolder> {

    //--- constructors
    Adapter() {
    }

    //--- create ViewHolder

    /**
     * Создает и возвращает ViewHolder ("А") уровня _laycontainer. "А" имеет поле
     * {@link LaycontainerViewHolder#laycustomViewHolder} в котором хранится ссылка на ViewHolder "Б".
     * "Б" это ViewHolder уровня _laycustom, взятый либо из nanvs, либо из dwzr
     *
     * @param parent   (1) --
     * @param viewType (2) --
     * @return --
     */
    @NonNull
    @Override
    public LaycontainerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      //--- оборачиваемый ViewHolder
      VH laycustomHolder;
      laycustomHolder = mDwzr.viewHolderCreate(mContext);
      //оборачиваемый view
      View laycustom = laycustomHolder.itemView;

      //--- оборачивающий view - билдер
      WgLaycontainer wgLaycontainer = new WgLaycontainer(laycustom).build();
      //--- оборачивающий view
      WgCbav layCbav = wgLaycontainer.getCbav();
      //--- оборачивающий ViewHolder
      LaycontainerViewHolder vh = new LaycontainerViewHolder(layCbav);

      //--- присвоения ссылок
      //- laycustom
      vh.laycustom = laycustom;
      vh.laycustomViewHolder = laycustomHolder;
      //- laycontainer
      vh.laycontainer = wgLaycontainer.getLaycontainer();
      vh.laycontainerHandle = wgLaycontainer.getHandle();
      vh.laycontainerCheckbox = wgLaycontainer.getChb();
      vh.laycontainerMenuElemPicker = wgLaycontainer.getMenuElemPicker();

      //---
      mtTagViewSets_x1(vh);    //<=== TagView

      //---
      return vh;
    }

    /**
     * Создание и присвоение объекта TagView ([sdva]) для ViewHolder (1) и для некоторых виджетов на которые
     * держит ссылку ViewHolder (1)
     *
     * @param vh (1) --
     */
    private void mtTagViewSets_x1(LaycontainerViewHolder vh) {
      TagView tagView = new TagView();
      //---
      vh.tagView = tagView;
      //---
      if (mShowCheckboxes) {
        vh.laycontainerCheckbox.setTag(tagView);
      }
      //---
      if (mShowMenuElem) {
        vh.laycontainerMenuElemPicker.setTag(tagView);
      }
      //--- [cbav]; layCbav
      vh.itemView.setTag(tagView);
      //---
      vh.laycontainer.setTag(tagView);
    }


    //--- bind
    @Override
    public void onBindViewHolder(@NonNull LaycontainerViewHolder vh, int index) {
      //Vdin_01.logStart("onBindViewHolder(...) //190420-164700", Adapter.this);
      //---
      if (mDragMode == EDragMode.HANDLE) {
        vh.laycontainerHandle.setOnTouchListener((v, event) -> {
          if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
            mItemTouchHelper.startDrag(vh);
          }
          return false;
        });
      }
      //--- обновление TagView
      mtTagViewUpdate(vh, index, mDwzr.elemGetByIndex(index));
      //---
      if (mShowCheckboxes) {
        //--- чекаем чекбокс если необходимо
        //присваиваем
        vh.laycontainerCheckbox.setCheckedSilent(mAvgb.checklistIsChecked(index));
      }
      //---
      WgCbav cbav = (WgCbav) vh.itemView;
      //---
      cbav.openState(mAvgb.checklistIsChecked(index));
      //---
      mDwzr.viewHolderBind(vh.laycustomViewHolder, index);          //<=== BIND custom уровня
      //---
      //Vdin_01.logEnd("", this);
    }

    /**
     * Запись в TagView ([sdva]), хранящийся во ViewHolder (1), индекса (2) и ссылки на _элемент (3) которому
     * этот индекс
     * соответствует
     *
     * @param vh      (1) --
     * @param index   (2) --
     * @param element (3) --
     */
    private void mtTagViewUpdate(@NonNull LaycontainerViewHolder vh, int index, E element) {
      vh.tagView.index = index;
      vh.tagView.element = element;
    }

    @Override
    public int getItemCount() {
      return (int) mAvgb.sizeGet();
    }

    /**
     * @param iFromPos   (1) --
     * @param iToPos     (2) --
     * @param vhFrom_out (3) --
     * @param vhTo_out   (4) --
     */
    public void swap(int iFromPos, int iToPos, RecyclerView.ViewHolder vhFrom_out, RecyclerView.ViewHolder vhTo_out) {
      Vdin_01.logStart("swap(iFromPos [" + iFromPos + "]; iToPos [" + iToPos + "])", Adapter.this);
      //---
      mDwzr.swap(iFromPos, iToPos);

      //--- обновление индексов в [avgb]
      mAvgb.checklistSwap(iFromPos, iToPos);

      //--- изменяем индексы у объекта TagView виджетов (меняем индексы местами)
      ((LaycontainerViewHolder) vhFrom_out).tagView.index = iToPos;
      ((LaycontainerViewHolder) vhTo_out).tagView.index = iFromPos;

      //---
      notifyItemMoved(iFromPos, iToPos);
      mtChangeToPresenterOuter_x4();
      //---
      Vdin_01.logEnd("", this);
    }

    /**
     * Вспомогательный класс для создания оборачивающего view
     * <p>
     * ИСПОЛЬЗОВАНИЕ: инстанцировать, вызвать {@link #build()}
     */
    private class WgLaycontainer {
      private View laycustom;
      private TextView handle;
      private NCheckBox chb;
      private TextView menuElemPicker;
      private NRelativeLayout laycontainer;
      private WgCbav cbav;

      /**
       * @param laycustom (1) -- оборачиваемый виджет
       */
      WgLaycontainer(View laycustom) {
        this.laycustom = laycustom;
      }


      public TextView getHandle() {
        return handle;
      }

      NCheckBox getChb() {
        return chb;
      }

      TextView getMenuElemPicker() {
        return menuElemPicker;
      }

      NRelativeLayout getLaycontainer() {
        return laycontainer;
      }

      WgCbav getCbav() {
        return cbav;
      }


      /**
       * @return --
       */
      public WgLaycontainer build() {
        //--- laycontainer
        BuLayRelative_01 buLaycontainer = mtBuLaycontainerCreate();
        //--- _ручка
        handle = mtHandleCreateIf(buLaycontainer);
        //--- checkbox
        chb = mtCheckbox(buLaycontainer);
        //--- menu
        menuElemPicker = mtMenuCreateIf(buLaycontainer);
        //--- добавление
        buLaycontainer.addBaseView(laycustom);
        //--- [[170204171101]]
        laycontainer = mtLaycontainerBuild(buLaycontainer);
        //--- cbav  //[[170204171000]]
        cbav = mtCbav(laycontainer);
        //---
        mtOnclick_x1(laycontainer);
        //---
        return this;
      }

      /**
       * Создание [cbav]
       *
       * @param layover (1) -- "сдвигаемая" разметка
       * @return --
       */
      @NonNull
      private WgCbav mtCbav(NRelativeLayout layover) {
        WgCbav cbav = new WgCbav(mContext)
          //[[170204171100]]
          .setLayBack(mtLayBackCreate())
          .setLayOver(layover)
          .presenter(new WgCbav.Presenter_x_() {
            @Override
            public void onOpened(WgCbav self) {
              //event//
              Vdin_01.logStart("EVENT-> onOpened(...)", WgLaycontainer.this);
              //---
              int iPos = mtGetIndex_x7(self);
              mAvgb.checklistNumberAdd(iPos);
              Vdin_01.logM("в mAvgb добавлен индекс [" + iPos + "]; //21.04.2019-9:15-1093", WgLaycontainer.this);
              Vdin_01.logM("mAvgb [" + mAvgb + "]; //21.04.2019-9:16-1094", WgLaycontainer.this);
              //---
              mSelectCount++;
              if (mPresenterLocal != null) {
                mPresenterLocal.onChangeSelectedCount(mSelectCount);
              }
              //---
              Vdin_01.logEnd("", WgLaycontainer.this);
            }

            @Override
            public void onClosed(WgCbav self) {
              Vdin_01.logStart("onClosed(...)", WgLaycontainer.this);
              int pos = mtGetIndex_x7(self);
              mAvgb.checklistNumberRemove(pos);
              Vdin_01.logM("из mAvgb удалён индекс [" + pos + "]; //21.04.2019-9:15-1112", WgLaycontainer.this);
              Vdin_01.logM("mAvgb [" + mAvgb + "]; //21.04.2019-9:16-1114", WgLaycontainer.this);
              //---
              mSelectCount--;
              if (mPresenterLocal != null) {
                mPresenterLocal.onChangeSelectedCount(mSelectCount);
              }
              Vdin_01.logEnd("", this);
            }
          })
          .commit();
        //---
        cbav.debugTagString = "[cbav] self -1730";
        //---
        return cbav;
      }

      @Nullable
      private NCheckBox mtCheckbox(BuLayRelative_01 buLayOver) {
        NCheckBox chb = null;
        if (mShowCheckboxes) {
          chb = new NCheckBox(mContext);
          chb.setOnCheckedChangeListener((buttonView, isChecked) -> {
            //event//
            Vdin_01.logM("EVENT-> isChecked [" + isChecked + "]; //21.04.2019-8:30-1120", WgLaycontainer.this);
            int index = mtGetIndex_x7(buttonView);
            mAvgb.checklistNumberAddOrRemove(index, isChecked);
          });
          buLayOver.appendToLeft(chb);
        }
        return chb;
      }

      /**
       * Создание виджета _ручки (если режим drag&drop == HANDLE) и добавление её к (1) слева
       *
       * @param buLayOver (1) --
       * @return -- ссылка на созданную _ручку или null
       */
      @Nullable
      private TextView mtHandleCreateIf(BuLayRelative_01 buLayOver) {
        TextView _handle = null;
        if (mDragMode == EDragMode.HANDLE) {
          _handle = new BuilderTV(mContext)
            .w(24)
            .hMP()
            .drawer(new N1208_DragVertical()
              .colorStroke(ColorStateList.valueOf(Color.WHITE))
            )
            .create();
          buLayOver.appendToLeft(_handle);
        }
        return _handle;
      }

      /**
       * Создание билдера RelativeLayout
       *
       * @return --
       */
      private BuLayRelative_01 mtBuLaycontainerCreate() {
        return new BuLayRelative_01(mContext)
          .classLayParams(FrameLayout.LayoutParams.class)
          .bgColor(Color.WHITE);
      }

      private NLinearLayout mtLayBackCreate() {
        return new BuLayLinear_01(mContext)
          .horizontal()
          .wWC()
          //.hMP()
          .bgColor(Color.CYAN)
          .addChild(new BuilderTV(mContext)
            .wh(48)
            .gravitySelf(Gravity.CENTER)
            .drawer(new N1208_Check()
              .colorStroke(ColorStateList.valueOf(Color.BLACK))
              .margins(12)
            )
            .create()
          )
          .build();
      }

      /**
       * Задаём действия выполняемые при нажатии на _laycontainer
       *
       * @param laycontainer (1) --
       */
      private void mtOnclick_x1(NRelativeLayout laycontainer) {
        //---
        laycontainer.setClickable(true);
        //---
        if (mPresenterOuter != null) {
          laycontainer.setOnClickListener(v -> {
            //event//
            Vdin_01.logStart("EVENT-> нажатие на _рв-элемент; //21.04.2019-17:43-1212", WgLaycontainer.this);
            v.invalidate();
            //---
            if (mPresenterOuter != null) {
              int iIndex = mtGetIndex_x7(v);
              //---
              TagView tagView = (TagView) v.getTag();
              //---
              boolean bNeedInvalidate = mPresenterOuter.onListItemClick(v, iIndex, tagView.element);
              if (bNeedInvalidate) {
                mAdapter.notifyItemChanged(iIndex);
              }
            }
            Vdin_01.logEnd("", this);
          });
        }
        //---
      }

      @NonNull
      private NRelativeLayout mtLaycontainerBuild(BuLayRelative_01 buLaycont) {
        NRelativeLayout laycont = buLaycont.build();
        //---
        laycont.setMinimumHeight(G67G_Draw.px(48));    //<=== MIN HEIGHT
        ViewCompat.setBackgroundTintList( //техника [ddrx]
          laycont,
          new BuCSL()
            //[[170219101600]]
            .pressed(U.c2(Color.parseColor("#F5F5F5")))
            .normal(U.c2(Color.WHITE))
            .create()
        );
        //---
        laycont.debugText = "[cbav] _layOver -1725";
        return laycont;
      }

      private MenuElem mtMenuElemDelete() {
        return new MenuElem(EStrings._DELETE.val(ETextCase.FUC), v -> {
          //---
          int i = 0;
          //клиент может запретить удаление
          if (mPresenterOuter != null) {
            i = mPresenterOuter.beforeDelete();
          }
          //---
          if (i == 0) {
            int iIndex = mtGetIndex_x7(v);
            mDwzr.remove(iIndex);
            mAvgb.sizeSet(mAvgb.sizeGet() - 1);
            //---
            mAdapter.notifyItemRemoved(iIndex);
            //нужно для обновления _индексов ([iwrb])
            mAdapter.notifyItemRangeChanged(iIndex, ((int) mAvgb.sizeGet()) - iIndex);
          }
          mtChangeToPresenterOuter_x4();
        },
          new BuDialogConfirm(mContext)
            .title(EStrings._DELETE4.val(ETextCase.FUC))
            .message(EStrings._N_ITEMS.val(ETextCase.LC))
            .backline(() -> "1")
        );
      }

      /**
       * Создание меню (если mShowMenuElem == true) и добавление виджета этого меню в правую часть (1)
       *
       * @param buLayOver (1) --
       * @return --
       */
      @Nullable
      private TextView mtMenuCreateIf(final BuLayRelative_01 buLayOver) {
        TextView _menuElemPicker = null;
        if (mShowMenuElem) {
          //---
          //- пункт "Удалить присутствует по умолчанию"
          BuPopupMenu buPopupMenu = new BuPopupMenu(mContext)
            //--- пункт "Удалить"
            .addElem(mtMenuElemDelete());
          //-
          if (TArrayList_01.isFill_B(menuElems)) {
            for (MenuElem menuElem : menuElems) {
              buPopupMenu.addElem(menuElem);
            }
          }
          //---
          _menuElemPicker = new BuilderTV(mContext)
            .wh(24)
            .drawer(DrawerBank_01.create(DrawerBank_01.E.VERTICAL_DOTS_A))
            .popupMenu(buPopupMenu)
            .create();
          //---
          buLayOver.appendToRight(_menuElemPicker);
        }
        return _menuElemPicker;
      } // mtMenu(...)


    } // class WrapViewCreate


  }


  /**
   * Сообщение клиенту класса что коллекция изменилась
   */
  private void mtChangeToPresenterOuter_x4() {
    if (mPresenterOuter != null) {
      mPresenterOuter.onElemsChanged();
    }
  }

  /**
   * ViewHolder для уровня _laycontainer
   */
  private class LaycontainerViewHolder extends RecyclerView.ViewHolder {
    /**
     * Оборачиваемый view
     */
    View laycustom;
    /**
     * ViewHolder обоачиваемого view (уровня _laycustom)
     */
    VH laycustomViewHolder;
    TextView laycontainerHandle;
    NCheckBox laycontainerCheckbox;
    TextView laycontainerMenuElemPicker;
    NRelativeLayout laycontainer;
    //--- этот же тег сидит в tag некоторых виджетов на которые текущий ViewHolder держит ссылку
    TagView tagView;

    //--- constructors
    LaycontainerViewHolder(View itemView) {
      super(itemView);
    }

  }


  //``````````````````````````````````````````````````````````````````````````````````````````````

  /**
   * Извлечение _индекса из TagView ([sdva]) виджета (1)
   *
   * @param v (1) --
   * @return --
   */
  private int mtGetIndex_x7(View v) {
    return ((TagView) v.getTag()).index;
  }


  /**
   * {ВЫНОСИТЬ В ОТДЕЛЬНЫЙ КЛАСС НЕ СЛЕДУЕТ (см. тип поля element)}
   * <p>
   * Представляет _индекс и _элемент.
   * Используется как tag для виджетов
   */
  public class TagView {
    public int index;
    public E element;

    public TagView() {
    }

    TagView(int index, E element) {
      this.index = index;
      this.element = element;
    }

    @Override
    public String toString() {
      return "TagView{" +
        "index=" + index +
        ", element=" + element +
        '}';
    }
  }

  //--- commands

  //region commands

  /**
   * Получение ссылки на внутренни RecyclerView
   *
   * @return --
   */
  public RecyclerView getRecyclerView() {
    return _rv;
  }

  /**
   * Заставляет перерисоваться RecyclerView с новыми данными (1)
   *
   * @param dwzr (1) --
   */
  public void refresh(Dwzr dwzr) {
    buDwzr(dwzr);
    _rv.getAdapter().notifyDataSetChanged();
  }

  //endregion commands


}

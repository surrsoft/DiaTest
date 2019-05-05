package ru.surrsoft.baaz.widgets2.pkor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import ru.surrsoft.baaz.R;
import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.logs.Vdin_01;
import ru.surrsoft.baaz.suite.figures.DrawerBank_01;
import ru.surrsoft.baaz.suite.figures.N1208_Check;
import ru.surrsoft.baaz.suite.figures.N1208_DragVertical;
import ru.surrsoft.baaz.tclasses.TArray_01;
import ru.surrsoft.baaz.univers.EStrings;
import ru.surrsoft.baaz.univers.ETextCase;
import ru.surrsoft.baaz.univers.Trans;
import ru.surrsoft.baaz.univers.U;
import ru.surrsoft.baaz.univers.UniPresenter_C;
import ru.surrsoft.baaz.widgets2.BuDialogConfirm;
import ru.surrsoft.baaz.widgets2.BuFAB;
import ru.surrsoft.baaz.widgets2.BuilderTV;
import ru.surrsoft.baaz.widgets2.Huta_b_v_;
import ru.surrsoft.baaz.widgets2.INanv;
import ru.surrsoft.baaz.widgets2.INanvUtils;
import ru.surrsoft.baaz.widgets2.WgCbav;
import ru.surrsoft.baaz.widgets2.WgOdba;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;
import ru.surrsoft.baaz.widgets2.buLay.BuLayRelative_01;
import ru.surrsoft.baaz.widgets2.forwg.Margins_01;
import ru.surrsoft.baaz.widgets2.nviews.NCheckBox;
import ru.surrsoft.baaz.widgets2.nviews.NRelativeLayout;
import ru.surrsoft.baaz.widgets2.utils.BuCSL;
import ru.surrsoft.baaz.widgets2.utils.BuPopupMenu;

/**
 * {есть демо - см. ABaazDemo}
 * <p>
 * Тип [[pkor]].
 * <p>
 * Это RecyclerView обернутый в [odba]. Элементы выбираются их слайдом, при этом появляется слайдер
 * редактирования.
 * <p>
 * Элементы "Б" передаваемые сюда (методом {@link #nanvs(ArrayList)}  должны реализовывать
 * интерфейс
 * {@link INanv}
 * генерализованный типом расширяющимся от элемента "А" расширяющего {@link
 * android.support.v7.widget.RecyclerView.ViewHolder}.
 * Класс этого элемента "А" как правило делается вложенным в класс элемента "Б".
 * Класс [pkor] в свою очередь как раз генерализуется типом "А".
 * Здесь "Б" это сущность которая генерирует виджеты, а "А" это holder соответственно предназначенный для
 * многократного переиспользования объекта виджета сгенерированного посредством "Б".
 * <p>
 * //--- ОПЦИИ
 * -- drag&drop задаётся методом {@link #dragAndDrop(EDragMode)}. БАГ: плохо работает если не
 * задействован {@link #presenter(Presenter_x_)}
 * -- чтобы работали сдвиги карточек, задействовать {@link #presenter(Presenter_x_)}
 * -- можно настраивать вертикальный скроллбар, см. {@link #scrollbarVertCfg(BuVertScrollbarCfg)}
 * <p>
 * //--- СЛОИСТОСТЬ (снизу вверх):
 * <li> FrameLayout - [odba] </li>
 * <li> RecyclerView - _rv </li>
 * <li> FrameLayout - [cbav]  //link to [170204171000] </li>
 * <li> LinearLayout - _layBack  //link to [170204171100] </li>
 * <li> NRelativeLayout - wrapLay  //link to [170204171101] </li>
 * <li> NLinearLayout - слой-вставка для элемента  //[[170204171102]] </li>
 * <p>
 * //---
 * По умолчанию применяет к rv:
 * <li> LayoutManager == LinearLayoutManager, </li>
 * <li> paddings == 16dp, </li>
 * <li> расстояние между карточками == 5dp </li>
 * <p>
 * <p>
 * СИСТЕМА ОПРЕДЕЛЕНИЯ ПОЗИЦИЙ VIEW ПРЕДСТАВЛЯЮЩИХ ЭЛЕМЕНТЫ СПИСКА:
 * в тег views (cbav и пр.) записывается объект элемента из mNanvs, которому этот view
 * соответствует.
 * Затем, когда нужно определить позицию, ссылка из тега извлекатеся, и по этой ссылке ищется
 * позиция в mNanvs, это и будет позицией элемента
 * <p>
 * СДВИГАНИЕ НА МЕСТО (ЗАКРЫТИЕ) ЭЛЕМЕНТОВ ПРИ ВЫХОДЕ ИЗ РЕДАКТИРОВАНИЯ:
 * используется коллекция mVisibleViews. Она всегда содержит ссылки на видимые элементы списка. Это
 * достигается за счет добавления в нее видимых элементов в методе onChildViewAttachedToWindow,
 * и удаление в методе onChildViewDetachedFromWindow. При необходимости "закрыть" элементы,
 * выполняется пробежка по данной коллекции и "закрытие" с плавной анимацией тех элементов
 * которые "открыты"
 * <p>
 * ПРИМЕЧАНИЯ:
 * (*1) - терминология w459w
 * <p>
 * ДАЛЕЕ
 * <li> есть "оборачивающий holder" </li>
 * <li> корнем отдельного элемента является тип {@link WgCbav} </li>
 * <p>
 * ПОНЯТИЯ:
 * <li> _rv - вложенный RecyclerView </li>
 * <li> _slideBar или _huta - выезжающая панель с количеством выбранных элементов и пр. </li>
 * <p>
 * МЕСТА:
 * <li> [170219101600] - цвет фона при нажатии </li>
 * <p>
 * #version 1 17.09.2016  #id [[w356w]]
 * <p>
 * [zipc]
 *
 * @param <VH> - _тип-токен _холдера; это тип элемента "А" (см. выше)
 */
public class WgPkor<VH extends RecyclerView.ViewHolder> {

  private final Context mContext;
  private int mPaddingL_px;
  private int mPaddingT_px;
  private int mPaddingR_px;
  private int mPaddingB_px;
  private int mGap_dp = 5;
  private RecyclerView.LayoutManager mLayoutManager;
  private ArrayList<INanv> mNanvs;
  private boolean mIsDraged;
  private Adapter mAdapter;
  private boolean mIsSwiped;
  private EDragMode mDragMode = EDragMode.LONG_PRESS;
  private ItemTouchHelper mItemTouchHelper;
  private boolean mShowCheckboxes;
  private boolean mShowMenuElem;
  private PresenterLocal_x_ mPresenterLocal;
  private Presenter_x_ mPresenterOuter;
  private int mSelectCount;
  private Huta_b_v_ mHuta;
  private WgOdba mOdbaBu;
  private Set<View> mVisibleViews = new HashSet<>();
  private int mPadding_slideBar_dp = 0;
  private int mSpaceBeforeFirstElem_dp;
  private int mSpaceAfterLastElem;
  private boolean mShowFAB = true;
  private Trans<RecyclerView.Adapter> mTransAdapter;
  private BuVertScrollbarCfg mBuVertScrollbarCfg;

  //--- constructor
  public WgPkor(Context c) {
    mContext = c;
    paddings_rv(16);
  }

  //---
  public enum EDragMode {
    /**
     * Драг стартует при длительном нажатии на _эс-виджет(*1)
     */
    LONG_PRESS,
    /**
     * Драг стартует если тянуть за _handle(*1)
     */
    HANDLE
  }

  //---
  public static class Presenter_x_ {

    /**
     * Вызывается при любом изменении коллекции переданной сюда через {@link #nanvs(ArrayList)}.
     * Реализующий как правило должен выполнить действия по сохранению изменний в постоянное
     * хранилище
     */
    public void n1356_onElemsChanged() {

    }

    /**
     * Вызывается при нажатии на элемент списка
     *
     * @param v   (1) --
     * @param pos (2) --
     */
    public void n1356_onListItemClick(View v, int pos) {

    }

    /**
     * Реализующий должен создать и вернуть новый элемент
     */
    public INanv n1356_createNewElem() {
      return null;
    }

    /**
     * Вызывается перед операцией удаления элементов.
     * ЦЕЛЬ: Реализующий может подправить то какие элементы будут удалены (путям
     * снятия/постановки
     * "чеков")
     *
     * @return реализующий должен вернуть количество элементов данные которых он "подправил"
     */
    public int n1356_beforeDelete() {
      return 0;
    }

  }

  //make
  //``````````````````````````````````````````````````````````````````````````````````````````````
  public FrameLayout create() {
    Vdin_01.log("-wg- создание [pkor] (RecyclerView обернутый в [odba])", Vdin_01.E.MIDDLE, this);
    //--- ===
    final RecyclerView _rv = U.inflate(R.layout.g67g_rv01, mContext);
    //--- scrollbar
    _rv.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
    if (mBuVertScrollbarCfg != null) {
      if (mBuVertScrollbarCfg.mVertScrollbarDisable) {
        _rv.setVerticalScrollBarEnabled(false);
      }
      if (mBuVertScrollbarCfg.mStyleInsideOverlay) {
        _rv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
      }
    }

    //--- "выезжающий" виджет с кнопками
    mHuta = getHuta();
    //--- интегратор для _rv и _huta
    mOdbaBu = new WgOdba(mContext)
      .baseView(_rv)
      .slideView(mHuta)
      .padding_slideView(mPadding_slideBar_dp);
    FrameLayout mOdba = mOdbaBu.build();
    //---
    //--- FAB
    if (mShowFAB) {
      FloatingActionButton fab = ((BuFAB) new BuFAB(mContext)
        .margins(new Margins_01(0, 0, 32, 32))
        .layParamClass(FrameLayout.LayoutParams.class)
        .bgColors(new BuCSL()
          .pressed(U.c2(Color.CYAN))
          .normal(U.c2(Color.WHITE))
        )
      ).build();
      fab.setOnClickListener(v -> {
        if (mPresenterOuter != null) {
          //add new elem
          INanv inanv = mPresenterOuter.n1356_createNewElem();
          mNanvs.add(0, inanv);
          mAdapter.notifyItemInserted(0);
          _rv.scrollToPosition(0);
          changeToPresenterOuter_x4();
        }
      });
      mOdba.addView(fab);
    }
    //---
    mPresenterLocal = new PresenterLocal_x_() {
      @Override
      public void onChangeSelectedCount(int count) {
        mHuta.n1462_countUpdate(count, count > 0);
        mOdbaBu.showSlideView(count > 0);
      }
    };
    //--- ---


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
      int dragDirs = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
      int swipeDirs = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
      mItemTouchHelper = new ItemTouchHelper(
        new MSimpleCallback(dragDirs, swipeDirs)
      );
      mItemTouchHelper.attachToRecyclerView(_rv);
    }
    //---
    mAdapter = new Adapter();
    _rv.setAdapter(mAdapter);
    //==
    if (mTransAdapter != null) {
      mTransAdapter.val = mAdapter;
    }
    //--- решение проблемы [w461w]
    _rv.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
      @Override
      public void onChildViewAttachedToWindow(View view) {
        mVisibleViews.add(view);
        //---
        WgCbav cbav = (WgCbav) view;
        int pos = getIx_x8(cbav);
        cbav.openState(mNanvs.get(pos).n1457_isChecked());
      }

      @Override
      public void onChildViewDetachedFromWindow(View view) {
        mVisibleViews.remove(view);
      }
    });
    //---
    return mOdba;
  }

  /**
   * Получение _huta
   * <p>
   * [huta] - выезжающий виджет с кнопками
   *
   * @return --
   */
  private Huta_b_v_ getHuta() {
    Huta_b_v_ huta_a = new Huta_b_v_(mContext)
      .marginT(mPadding_slideBar_dp)
      .presenter(new Huta_b_v_.Presenter_x_() {
        @Override
        public void onCancel() { //link to [w465w-2201]
          //--- плавное закрытие сдвигов
          for (View elem : mVisibleViews) {
            WgCbav cbav = (WgCbav) elem;
            cbav.openSmooth(false);
          }
          //---
          for (INanv<VH> mNanv : mNanvs) {
            mNanv.n1457_setChecked(false);
          }
          //---
          doneEdit_x2();
        }

        @Override
        public void onDelete() { //link to [w465w-1555]
          //--- возможность клиенту подправить пометки подлежащих к удалению
          if (mPresenterOuter != null) {
            mPresenterOuter.n1356_beforeDelete();
          }
          //---
          int min = -1;
          //удаление элементов
          for (int i = mNanvs.size() - 1; i >= 0; i--) {
            if (mNanvs.get(i).n1457_isChecked()) {
              mNanvs.remove(i);
              mAdapter.notifyItemRangeRemoved(i, 1);
              min = i;
            }
          }
          doneEdit_x2();
          mAdapter.notifyItemRangeChanged(min, mNanvs.size() - min);
          changeToPresenterOuter_x4();
        }

        @Override
        public void onSelectAll() {
          //--- плавное открытие сдвигов
          for (View elem : mVisibleViews) {
            WgCbav cbav = (WgCbav) elem;
            cbav.openSmooth(true);
          }
          //---
          for (INanv<VH> mNanv : mNanvs) {
            mNanv.n1457_setChecked(true);
          }
          //---
          mSelectCount = mNanvs.size();
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
          for (INanv<VH> eNanv : mNanvs) {
            eNanv.n1457_setChecked(!eNanv.n1457_isChecked());
          }
          //---
          mSelectCount = INanvUtils.countChecked(mNanvs);
          mHuta.n1462_countUpdate(mSelectCount, mSelectCount > 0);
          if (mSelectCount == 0) {
            mOdbaBu.showSlideView(false);
          }
        }
      });
    return huta_a.create();
  }

  private void doneEdit_x2() {
    mSelectCount = 0;
    mHuta.n1462_countUpdate(mSelectCount, false);
    mOdbaBu.showSlideView(false);
  }

  //simple callback
  //``````````````````````````````````````````````````````````````````````````````````````````````
  private class MSimpleCallback extends ItemTouchHelper.SimpleCallback {

    /**
     *
     */
    MSimpleCallback(int dragDirs, int swipeDirs) {
      super(dragDirs, swipeDirs);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView,
                          RecyclerView.ViewHolder from,
                          RecyclerView.ViewHolder to) {
      mAdapter.swap(from.getAdapterPosition(), to.getAdapterPosition());
      return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }

    //управление swipe enable
    @Override
    public boolean isItemViewSwipeEnabled() {
      return mIsSwiped;
    }

    //управлением способом старта драга
    @Override
    public boolean isLongPressDragEnabled() {
      return mDragMode == EDragMode.LONG_PRESS;
    }
  }

  //builders
  //``````````````````````````````````````````````````````````````````````````````````````````````

  public WgPkor scrollbarVertCfg(BuVertScrollbarCfg bu) {
    mBuVertScrollbarCfg = bu;
    return this;
  }

  public WgPkor dragAndDrop(EDragMode edm) {
    mIsDraged = true;
    mDragMode = edm;
    return this;
  }

  /**
   * paddings вложенного _rv
   *
   * @param dp (1) --
   * @return --
   */
  public WgPkor paddings_rv(int dp) {
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
  public WgPkor paddings_rv(int l_dp, int t_dp, int r_dp, int b_dp) {
    mPaddingL_px = G67G_Draw.px(l_dp);
    mPaddingT_px = G67G_Draw.px(t_dp);
    mPaddingR_px = G67G_Draw.px(r_dp);
    mPaddingB_px = G67G_Draw.px(b_dp);
    return this;
  }

  public WgPkor padding_slideBar(int dp) {
    mPadding_slideBar_dp = dp;
    return this;
  }

  public WgPkor presenter(Presenter_x_ lst) {
    mPresenterOuter = lst;
    return this;
  }

  /**
   * Зазор между карточками
   *
   * @param dp (1) --
   * @return --
   */
  public WgPkor gap(int dp) {
    mGap_dp = dp;
    return this;
  }

  public WgPkor nanvs(ArrayList<INanv> nanvs) {
    mtNanvsVerifyEx(nanvs);
    mNanvs = nanvs;
    return this;
  }

  /**
   * Проверка (1) на валидность
   *
   * @param nanvs (1) --
   */
  private void mtNanvsVerifyEx(ArrayList<INanv> nanvs) {
    U.se(nanvs == null, "");
    //---
    for (INanv eNanv : nanvs) {
      U.se(eNanv == null, "");
      U.se(eNanv.n1457_getViewHolder(mContext) == null, "метод getViewHolder не должен возвращать null");
    }
  }

  public WgPkor showCheckboxes(boolean b) {
    mShowCheckboxes = b;
    return this;
  }

  public WgPkor showMenuElem(boolean b) {
    mShowMenuElem = b;
    return this;
  }

  /**
   * Отступ от верха первого элемента
   *
   * @param dp --
   * @return --
   */
  public WgPkor spaceBeforeFirstElem(int dp) {
    mSpaceBeforeFirstElem_dp = dp;
    return this;
  }

  /**
   * Дополнительное пространство прокрутки после последнего элемента
   *
   * @param dp --
   * @return --
   */
  public WgPkor spaceAfterLastElem(int dp) {
    mSpaceAfterLastElem = dp;
    return this;
  }

  public WgPkor showFAB(boolean b_defTrue) {
    mShowFAB = b_defTrue;
    return this;
  }

  private WgPkor presenter(PresenterLocal_x_ p) {
    mPresenterLocal = p;
    return this;
  }

  public WgPkor linkAdapter(Trans<RecyclerView.Adapter> trans) {
    mTransAdapter = trans;
    return this;
  }

  //class presenter
  //``````````````````````````````````````````````````````````````````````````````````````````````
  public static class PresenterLocal_x_ {
    /**
     * Вызывается когда изменяется количество _выбранных элементов
     *
     * @param count (1) -- количество элементов _выбранных в текущий момент
     */
    public void onChangeSelectedCount(int count) {

    }
  }

  //decoration
  //``````````````````````````````````````````````````````````````````````````````````````````````
  public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    public DividerItemDecoration() {
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView rv, RecyclerView.State state) {
      super.getItemOffsets(outRect, view, rv, state);
      int pos = getIx_x8(view);
      if (rv.getChildAdapterPosition(view) == 0) {
        if (mSpaceBeforeFirstElem_dp > 0) {
          outRect.top = G67G_Draw.px(mSpaceBeforeFirstElem_dp);
        }
      } else if (pos == mNanvs.size() - 1) {
        outRect.top = G67G_Draw.px(mGap_dp);
        if (mSpaceAfterLastElem > 0) {
          outRect.bottom = G67G_Draw.px(mSpaceAfterLastElem);
        }
      } else {
        outRect.top = G67G_Draw.px(mGap_dp);
      }
    }
  }

  //adapter class
  //``````````````````````````````````````````````````````````````````````````````````````````````
  class Adapter extends RecyclerView.Adapter<WrapHolder> {

    //CREATE ------------------------------------------
    @Override
    public WrapHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      VH wrapedHolder = (VH) mNanvs.get(0).n1457_getViewHolder(mContext);
      View wrapedView = wrapedHolder.itemView;

      //--- оборачивающий view >>>
      //region
      //--- rv
      BuLayRelative_01 buLayOver = new BuLayRelative_01(mContext)
        .classLayParams(FrameLayout.LayoutParams.class)
        .bgColor(Color.WHITE);
      //--- handle
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
      //---
      NCheckBox chb = null;
      if (mShowCheckboxes) {
        chb = new NCheckBox(mContext);
        chb.setOnCheckedChangeListener((buttonView, isChecked) -> {
          int pos = getIx_x8(buttonView);
          mNanvs.get(pos).n1457_setChecked(isChecked);
        });
        buLayOver.appendToLeft(chb);
      }
      //= menu elem
      TextView _menuElemPicker = null;
      if (mShowMenuElem) {
        _menuElemPicker = new BuilderTV(mContext)
          .wh(24)
          .drawer(DrawerBank_01.create(DrawerBank_01.E.VERTICAL_DOTS_A))
          .popupMenu(new BuPopupMenu(mContext)
            .addElem(EStrings._DELETE.val(ETextCase.FUC), v -> {
                //---
                int i = 0;
                //клиент может запретить удаление
                if (mPresenterOuter != null) {
                  i = mPresenterOuter.n1356_beforeDelete();
                }
                //---
                if (i == 0) {
                  int pos = getIx_x8(v);
                  mNanvs.remove(pos);
                  mAdapter.notifyItemRemoved(pos);
                }
                changeToPresenterOuter_x4();
              }, new BuDialogConfirm(mContext)
                .title(EStrings._DELETE4.val(ETextCase.FUC))
                .message(EStrings._N_ITEMS.val(ETextCase.LC))
                .backline(new UniPresenter_C<String>() {
                  @Override
                  public String n1451_get() {
                    return "1";
                  }
                })
            )
          )
          .create();
        buLayOver.appendToRight(_menuElemPicker);
      }
      buLayOver.addBaseView(wrapedView);
      //= [[170204171101]]
      NRelativeLayout vLayOver = buLayOver.build();
      vLayOver.setMinimumHeight(G67G_Draw.px(48));    //<=== MIN HEIGHT
      ViewCompat.setBackgroundTintList( //техника [ddrx]
        vLayOver,
        new BuCSL()
          //[[170219101600]]
          .pressed(U.c2(Color.parseColor("#F5F5F5")))
          .normal(U.c2(Color.WHITE))
          .create()
      );
      vLayOver.debugText = "[cbav] _layOver -1725";
      if (mPresenterOuter != null) {
        vLayOver.setClickable(true);
        vLayOver.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            //event//
            Vdin_01.logM("EVENT-> WgPkor{нажатие на элемент}: onClick(...); //21.04.2019-11:49-710", Adapter.this);
            v.invalidate();
            if (mPresenterOuter != null) {
              int pos = getIx_x8(((WgCbav) v.getParent()));
              mPresenterOuter.n1356_onListItemClick(v, pos);  //<=== ПЕРЕДАЧА КЛИКА
            }
          }
        });
      }
      //== cbav  //[[170204171000]]
      WgCbav cbav = new WgCbav(mContext)
        //[[170204171100]]
        .setLayBack(new BuLayLinear_01(mContext)
          .horizontal()
          .wWC()
          .bgColor(Color.CYAN)
          .addChild(new BuilderTV(mContext)
            .wh(48)
            .drawer(new N1208_Check()
              .colorStroke(ColorStateList.valueOf(Color.BLACK))
              .margins(12)
            )
            .create()
          )
          .build()
        )
        .setLayOver(vLayOver)
        .presenter(new WgCbav.Presenter_x_() {
          @Override
          public void onOpened(WgCbav self) {
            int pos = getIx_x8(self);
            mNanvs.get(pos).n1457_setChecked(true);
            //---
            mSelectCount++;
            if (mPresenterLocal != null) {
              mPresenterLocal.onChangeSelectedCount(mSelectCount);
            }
          }

          @Override
          public void onClosed(WgCbav self) {
            int pos = getIx_x8(self);
            mNanvs.get(pos).n1457_setChecked(false);
            mSelectCount--;
            if (mPresenterLocal != null) {
              mPresenterLocal.onChangeSelectedCount(mSelectCount);
            }
          }
        })
        .commit();
      cbav.debugTagString = "[cbav] self -1730";
      //endregion

      //--- оборачивающий holder
      WrapHolder wh = new WrapHolder(cbav);
      wh.wrapedView = wrapedView;
      wh.wrapedHolder = wrapedHolder;
      wh._handle = _handle;
      wh._chb = chb;
      wh._menuElemPicker = _menuElemPicker;
      wh.layOver = vLayOver;

      //---
      return wh;
    }

    //BIND --------------------------------------------
    @Override
    public void onBindViewHolder(final WrapHolder wrapHolder, int pos) {
      //Log2_01.i(TAG, "==51--17> [atcu]# d# onBindViewHolder()" + "; pos=" + pos, LOG2);
      if (mDragMode == EDragMode.HANDLE) {
        wrapHolder._handle.setOnTouchListener(new View.OnTouchListener() {
          @SuppressLint("ClickableViewAccessibility")
          @Override
          public boolean onTouch(View v, MotionEvent event) {
            if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
              mItemTouchHelper.startDrag(wrapHolder);
            }
            return false;
          }
        });
      }
      //---
      if (mShowCheckboxes) {
        wrapHolder._chb.setCheckedSilent(mNanvs.get(pos).n1457_isChecked());
        wrapHolder._chb.setTag(mNanvs.get(pos));
      }
      if (mShowMenuElem) {
        wrapHolder._menuElemPicker.setTag(mNanvs.get(pos));
      }
      //---
      wrapHolder.itemView.setTag(mNanvs.get(pos));
      //---
      WgCbav cbav = (WgCbav) wrapHolder.itemView;
      cbav.setTag(mNanvs.get(pos));
      cbav.openState(mNanvs.get(pos).n1457_isChecked());
      //---
      mNanvs.get(pos).n1457_bindViewHolder(wrapHolder.wrapedHolder);   //<=== BIND
      //---
    }

    @Override
    public int getItemCount() {
      return mNanvs.size();
    }

    public void swap(int firstPosition, int secondPosition) {
      TArray_01.swap(mNanvs, firstPosition, secondPosition);
      notifyItemMoved(firstPosition, secondPosition);
      changeToPresenterOuter_x4();
    }

  }

  /**
   * Сообщение клиенту класса что коллекция изменилась
   */
  private void changeToPresenterOuter_x4() {
    if (mPresenterOuter != null) {
      mPresenterOuter.n1356_onElemsChanged();
    }
  }

  //wrap holder
  //``````````````````````````````````````````````````````````````````````````````````````````````
  class WrapHolder extends RecyclerView.ViewHolder {
    /**
     * Оборачиваемый view
     */
    public View wrapedView;
    /**
     * Оборачиваемый _холдер
     */
    public VH wrapedHolder;

    public TextView _handle;

    public NCheckBox _chb;
    public TextView _menuElemPicker;
    public NRelativeLayout layOver;

    public WrapHolder(View itemView) {
      super(itemView);
    }
  }


  //``````````````````````````````````````````````````````````````````````````````````````````````
  //http://stackoverflow.com/questions/24471109/recyclerview-onclick
  public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
    private Pkor_onItemClickListener mListener;
    GestureDetector mGestureDetector;

    public RecyclerItemClickListener(Context context,
                                     final RecyclerView rv,
                                     Pkor_onItemClickListener listener) {
      mListener = listener;
      mGestureDetector = new GestureDetector(context,
        new GestureDetector.SimpleOnGestureListener() {
          @Override
          public boolean onSingleTapUp(MotionEvent e) {
            return true;
          }

          @Override
          public void onLongPress(MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && mListener != null) {
              mListener.onLongItemClick(child, rv.getChildAdapterPosition(child));
            }
          }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
      View childView = rv.findChildViewUnder(e.getX(), e.getY());
      if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
        mListener.onItemClick(childView, rv.getChildAdapterPosition(childView));
        return true;
      }
      return false;
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }
  }

  public interface Pkor_onItemClickListener {
    void onItemClick(View view, int position);

    void onLongItemClick(View view, int position);

  }

  //``````````````````````````````````````````````````````````````````````````````````````````````

  private int getIx_x8(View v) {
    INanv<VH> nanv = (INanv<VH>) v.getTag();
    return mNanvs.indexOf(nanv);
  }

  public static class BuVertScrollbarCfg {
    private boolean mVertScrollbarDisable;
    private boolean mStyleInsideOverlay;

    public BuVertScrollbarCfg disable() {
      mVertScrollbarDisable = true;
      return this;
    }

    public BuVertScrollbarCfg styleInsideOverlay() {
      mStyleInsideOverlay = true;
      return this;
    }
  }

}

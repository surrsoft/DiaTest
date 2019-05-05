package ru.surrsoft.baaz.widgets2.uasa;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import ru.surrsoft.baaz.R;
import ru.surrsoft.baaz.logs.Vdin_01;
import ru.surrsoft.baaz.suite.figures.N1208_Check;
import ru.surrsoft.baaz.suite.figures.N1208_Pensil;
import ru.surrsoft.baaz.suite.lvl0.EEditTextInputTypes;
import ru.surrsoft.baaz.univers.Color2;
import ru.surrsoft.baaz.univers.EC;
import ru.surrsoft.baaz.univers.Roih;
import ru.surrsoft.baaz.univers.U;
import ru.surrsoft.baaz.widgets2.AbsBuView_01;
import ru.surrsoft.baaz.widgets2.awoh.BuAwoh;
import ru.surrsoft.baaz.widgets2.BuDerb_01;
import ru.surrsoft.baaz.widgets2.BuilderTV;
import ru.surrsoft.baaz.widgets2.events.Ev1;
import ru.surrsoft.baaz.widgets2.events.EvBoolean;
import ru.surrsoft.baaz.widgets2.events.EvString;
import ru.surrsoft.baaz.widgets2.forwg.Margins_01;
import ru.surrsoft.baaz.widgets2.nviews.NLinearLayout;
import ru.surrsoft.baaz.widgets2.nviews.NTextInputEditText;
import ru.surrsoft.baaz.widgets2.nviews.NTextView;
import ru.surrsoft.baaz.widgets2.utils.BuCSL;
import ru.surrsoft.baaz.widgets2.utils.BuTextStyle;

/**
 * [[uasa]] -  это [nwez] основанный на (см. {@link BuAwoh} [awoh])
 * <p>
 * ИЗ TROOGET: [nwez] - виджет. Это EditText справа от которого кнопка, она предназначена чтобы
 * показать что поле слева редактируемое, а также для завершения редактирования нажатием на нее
 * <p>
 * ПОНЯТИЯ
 * <li> _uxo - корневой layout (RelativeLayout); его детьми являются _til, _btn и _tad </li>
 * <li> LL _til - TextInputLayout. Представлен классом {@link TextInputLayout}, выходом класса
 * {@link BuAwoh}   </li>
 * <li>   LL _ety - editText который внутри _til. Представлен там классом {@link NTextInputEditText}
 * </li>
 * <li>     LL _erm - всплывающая метка в _ety справа обозначающая ошибку  </li>
 * <li> LL _btn - кнопка справа. Представлена выходом класса {@link BuDerb_01}   </li>
 * <li> LL _tad - layout расположенный под _til и _btn и предназначенный для отображения сообщения
 * об ошибке</li>
 * <li>   LL _erd - сообщение об ошибке нижнее. Всё что с этим связано </li>
 * <p>
 * ТЕХНИКА [moru]
 * <li> излучает ({@link EvBoolean} при получении/потере фокуса </li>
 * <li> излучает {@link EvString} при изменении текста пользователем  </li>
 * <p>
 * СТАРЫЕ НАЗВАНИЯ: "BuUasa_v_"
 * //[[w469w]]
 */
public class WgUasa_01 extends AbsBuView_01 {

  private final Context mContext;
  private CharSequence mHint;
  private ViewGroup mParent;
  private CharSequence mText;
  private BuDerb_01 mBuPzub;
  private TextInputLayout _til;
  public String debugText = "";
  private BuAwoh _tilBu;
  private Color2 mColorText = new Color2(Color.BLACK);
  private Color2 mColorHintFocused;
  private Color2 mColorHint;
  private Presenter mPresenter;
  private boolean mMultiline;
  private ColorStateList mColorTextCSL;
  private Ev1[] mEnable_ev1s;
  private boolean mEnable = true;
  private boolean mErrorEnabled;
  @StyleRes
  private int mTextErrorStyle = R.style.n1469_errorText;
  private FrameLayout _tad;
  private BuTextStyle mErdBuTextStyle;
  /**
   * Техника [moru]
   */
  public String gefp;
  /**
   *
   */
  private EEditTextInputTypes eEditTextInputTypes;

  //constructors
  //``````````````````````````````````````````````````````````````````````````````````````````````
  public WgUasa_01(Context c) {
    mContext = c;
    mErdBuTextStyle = new BuTextStyle()
      .buTextColor(Color.RED)
      .buTextSize(12)
      .buTextFace(Roih.get(Roih.TF_CYR_ROBOTO_LIGHT));
  }

  //presenters
  //``````````````````````````````````````````````````````````````````````````````````````````````
  public static class Presenter {
    /**
     * Вызывается при каждом изменении текста в _et
     *
     * @param st (1) -- новый текст
     */
    public void onTextChange(Editable st) {

    }

    /**
     * @param focus (1) -- TRUE если виджет получил фокус; FALSE иначе
     */
    public void onFocusChange(boolean focus) {

    }
  }

  //builders
  //``````````````````````````````````````````````````````````````````````````````````````````````
  //region builders >>>

  /**
   * Техника [moru]
   *
   * @param gefp (1) --
   * @return --
   */
  public WgUasa_01 buGefp(String gefp) {
    this.gefp = gefp;
    return this;
  }

  /**
   * Стиль для _erd
   *
   * @param bts (1) --
   * @return --
   */
  public WgUasa_01 buErdTextStyle(BuTextStyle bts) {
    mErdBuTextStyle = bts;
    return this;
  }

  public WgUasa_01 buErrorEnabled(boolean b) {
    mErrorEnabled = b;
    return this;
  }

  public WgUasa_01 buErrorTextStyle(@StyleRes int styleRes) {
    mTextErrorStyle = styleRes;
    return this;
  }

  public WgUasa_01 buMultiline(boolean b) {
    mMultiline = b;
    return this;
  }

  public WgUasa_01 buHintText(CharSequence chs) {
    mHint = chs;
    return this;
  }

  public WgUasa_01 buText(CharSequence chs) {
    mText = chs;
    return this;
  }

  public WgUasa_01 buAddTo(ViewGroup vg) {
    mParent = vg;
    return this;
  }

  public WgUasa_01 buDebugText(String st) {
    debugText = st;
    return this;
  }

  /**
   * См. _text в {@link BuAwoh}
   *
   * @param c (1) --
   * @return --
   */
  public WgUasa_01 buTextColor(Color2 c) {
    mColorText = c;
    return this;
  }

  /**
   * См. _text в {@link BuAwoh}
   *
   * @param csl (1) --
   * @return --
   */
  public WgUasa_01 buTextColor(ColorStateList csl) {
    mColorTextCSL = csl;
    return this;
  }

  /**
   * См. [cgeg] в {@link BuAwoh}
   *
   * @param c (1) --
   * @return --
   */
  public WgUasa_01 buHintColorFocused(Color2 c) {
    mColorHintFocused = c;
    return this;
  }

  /**
   * См. {@link BuAwoh#colorHint(Color2)}
   *
   * @param c (1) --
   * @return --
   */
  public WgUasa_01 buHintTextColor(Color2 c) {
    mColorHint = c;
    return this;
  }

  public WgUasa_01 buPresenter(Presenter p) {
    mPresenter = p;
    return this;
  }

  public WgUasa_01 buEnable(boolean b) {
    mEnable = b;
    return this;
  }

  /**
   * (1) влияет на то какая будет показана клавиатура
   *
   * @param eEditTextInputTypes (1) --
   * @return --
   */
  public WgUasa_01 buInputType(@Nullable EEditTextInputTypes eEditTextInputTypes) {
    this.eEditTextInputTypes = eEditTextInputTypes;
    return this;
  }

  //endregion //builders


  //commands
  //``````````````````````````````````````````````````````````````````````````````````````````````
  //region commands >>>

  /**
   * Установка текста для _ety
   *
   * @param st (1) --
   */
  public void _setText(String st) {
    _til.getEditText().setText(st);
  }

  public void _setFocusable(boolean b) {
    _til.setFocusable(b);
    _til.setFocusableInTouchMode(b);
  }

  public String _getText() {
    return _til.getEditText().getText().toString();
  }

  public void _enable(boolean b) {
    _til.setEnabled(b);
    mBuPzub._enable(b);
  }

  /**
   * Получение _til
   *
   * @return --
   */
  public TextInputLayout _getTextInputLayout() {
    return _til;
  }

  /**
   * Если (2) == TRUE то отображает _erd с текстом (1)
   *
   * @param st   (1) -- текст для отображения
   * @param show (2) -- TRUE чтобы отобразить _erd, FALSE чтобы скрыть
   */
  public void _erdShow(String st, boolean show) {
    String text = st == null ? "null" : st;
    if (show) {
      _tad.setVisibility(View.VISIBLE);
      if (_tad.getChildCount() == 0) {
        //^ если виджет отображающий _erd еще ни разу не создавался
        new BuilderTV(mContext)
          .addTo(_tad)
          .textStyle(mErdBuTextStyle)
          .text(text)
          .create();
      } else {
        TextView tv = (TextView) _tad.getChildAt(0);
        tv.setText(text);
      }
    } else {
      _tad.setVisibility(View.GONE);
    }
  }

  /**
   * Если (2) == TRUE то отображает _erm с текстом (1). Если (2) == FALSE то скрывает _erm
   *
   * @param st   (1) --
   * @param show (2) --
   */
  public void _ermShow(String st, boolean show) {
    _getTextInputLayout()
      .getEditText()
      .setError(show ? st : null);
  }
  //endregion


  //create
  //``````````````````````````````````````````````````````````````````````````````````````````````
  @Override
  public NLinearLayout build() {
    Vdin_01.logM("создание [uasa]-виджета", this);
    //--- //inflate
    NLinearLayout _uxo = U.inflate(R.layout.n1468_uasa_lay, mContext);
    final TextInputLayout _tilSrc = _uxo.findViewById(R.id.n1468_tilSrc);
    NTextView _btn = _uxo.findViewById(R.id.n1468_btn);
    _tad = _uxo.findViewById(R.id.n1468_tad);
    //--- _til
    _tilBu = new BuAwoh(mContext)
      .hint(mHint)
      .debugText(debugText)
      .colorText(mColorTextCSL)
      .colorHintFocused(mColorHintFocused)
      .colorHint(mColorHint)
      .multiline(mMultiline)
      .buInputType(eEditTextInputTypes)
      .presenter(new BuAwoh.Presenter() {
        @Override
        public void n1468_onChangeActiveState(boolean active) {
          //link [170210092400]
          if (active == mBuPzub._getState()) {
            //переключение состояния кнопки
            mBuPzub._toggleState();
          }
          if (mPresenter != null) {
            mPresenter.onFocusChange(active);
          }
          if (active) {
            //убираем _erm
            _tilSrc
              .getEditText()
              .setError(null);
          }
          //--- техника [moru]
          if (gefp != null) {
            EventBus.getDefault().post(new EvBoolean(gefp, active));
          }
        }

        @Override
        public void n1468_onTextChange(Editable st) {
          if (mPresenter != null) {
            mPresenter.onTextChange(st);
          }
          if (gefp != null) {
            EventBus.getDefault().post(new EvString(gefp, st.toString()));
          }
        }
      });
    _til = _tilBu.apply(_tilSrc);

    //--- _btn
    Margins_01 m = new Margins_01(12 + 2, 12 + 2, 12 + 2, 12 + 2);
    ColorStateList csl = new BuCSL()
      .disable(EC.MD_TRANSPARENT)
      .pressed(EC.MD_RED_A700)
      .normal(EC.MD_BLACK_1000)
      .create();

    mBuPzub = ((BuDerb_01) new BuDerb_01(mContext)
      .wh(48))
      .drawerFalse(new N1208_Check()
        .margins(m)
        .colorStroke(csl)
      )
      .drawerTrue(new N1208_Pensil()
        .margins(m)
        .colorStroke(csl)
      )
      .presenter(newState -> _tilBu._setStateActive(!newState))
      .startState(true);
    mBuPzub.apply(_btn);

    //---
    EditText _et0 = _til.getEditText();
    _et0.setText(mText);
    //---
    if (mParent != null) {
      mParent.addView(_uxo);
    }
    //--- enable
    _til.setEnabled(mEnable);
    //---
    return _uxo;
  }


}

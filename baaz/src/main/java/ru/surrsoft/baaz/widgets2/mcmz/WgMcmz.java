package ru.surrsoft.baaz.widgets2.mcmz;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
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
import ru.surrsoft.baaz.new_02.p02_mmvd.MmvdValue;
import ru.surrsoft.baaz.new_02.p02_mmvd.b.CorrectionResult;
import ru.surrsoft.baaz.new_02.p02_mmvd.b.ValidateResult;
import ru.surrsoft.baaz.suite.figures.N1208_Check;
import ru.surrsoft.baaz.suite.figures.N1208_Pensil;
import ru.surrsoft.baaz.univers.Color2;
import ru.surrsoft.baaz.univers.EC;
import ru.surrsoft.baaz.univers.Roih;
import ru.surrsoft.baaz.univers.U;
import ru.surrsoft.baaz.widgets2.AbsBuView_01;
import ru.surrsoft.baaz.widgets2.BuDerb_01;
import ru.surrsoft.baaz.widgets2.BuilderTV;
import ru.surrsoft.baaz.widgets2.awoh.BuAwoh;
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
 * [[mcmz]] -  это [nwez] основанный на (см. {@link BuAwoh} [awoh])
 * <p>
 * Это переделка [uasa]-виджета под работу с {@link MmvdValue}
 *
 * <p>
 * ИЗ TROOGET: [nwez] - виджет. Это EditText справа от которого кнопка, она предназначена чтобы
 * показать что поле слева редактируемое, а также для завершения редактирования нажатием на нее
 * <p>
 * ПОНЯТИЯ:
 * -- _txt - текущий текст _edittext
 * -- _uxo, _rootlay - корневой layout (RelativeLayout); его детьми являются _til, _btn и _tad
 * -- -- _til, _layinput - TextInputLayout. Представлен классом {@link TextInputLayout}, выходом класса
 * {@link BuAwoh}
 * -- -- _ety, _edittext - editText который внутри _til. Представлен там классом {@link NTextInputEditText}
 * -- -- -- _erm, _errlabel - всплывающая метка в _ety справа обозначающая ошибку
 * -- -- _btn - кнопка справа. Представлена выходом класса {@link BuDerb_01}
 * -- -- _tad, _errmessagelay - layout расположенный под _til и _btn и предназначенный для отображения сообщения
 * об ошибке
 * -- -- -- _erd, _errmessage - сообщение об ошибке нижнее. Всё что с этим связано
 * -- _errstate - это состояние виджета когда отображается _errmessage
 * <p>
 * ТЕХНИКА [moru]
 * <li> излучает ({@link EvBoolean} при получении/потере фокуса </li>
 * <li> излучает {@link EvString} при изменении текста пользователем  </li>
 * <p>
 * КОДЫ: [ksao]
 * //new//
 */
public class WgMcmz extends AbsBuView_01 {

  private final Context mContext;
  private CharSequence mHint;
  private ViewGroup mParent;
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
  private EErrState eErrState = EErrState.NO_ERR;
  /**
   * Техника [moru]
   */
  public String gefp;
  /**
   *
   */
  private MmvdValue mmvdValue;
  /**
   * [[enhw]]
   * <p>
   * Эта переменная переводится в TRUE в методе {@link #deactivate(boolean, boolean)}, когда нужно
   * отменить выполненный ввод. В этом случае, в момент потери фокуса не анализируется введённый
   * текст и не меняется состояние объекта переменной {@link #mmvdValue}
   */
  private boolean bEnhwMode;

  /**
   * Если TRUE то после создания виджет получит фокус и перейдёт в режим редактирования
   */
  private boolean bActiveOnStart;

  //constructors
  //``````````````````````````````````````````````````````````````````````````````````````````````
  public WgMcmz(Context c) {
    mContext = c;
    mErdBuTextStyle = new BuTextStyle()
      .buTextColor(Color.RED)
      .buTextSize(12)
      .buTextFace(Roih.get(Roih.TF_CYR_ROBOTO_LIGHT));
  }

  /**
   * ([[yabw]]::) Используется для передачи информации о том находится ли текущий виджет в состоянии _errstate
   */
  public enum EErrState {
    /**
     * Находится в состоянии _errstate
     */
    ERR,
    /**
     * Не находится в состоянии _errstate
     */
    NO_ERR
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
     * @param focus     (1) -- TRUE если виджет получил фокус; FALSE иначе
     * @param eErrState (2) -- см. [yabw]::
     */
    public void onFocusChange(boolean focus, EErrState eErrState) {

    }

    /**
     * Вызывается при нажатии "аппаратной кнопки Назад"
     */
    public void onKeyBackPress() {

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
  public WgMcmz buGefp(String gefp) {
    this.gefp = gefp;
    return this;
  }

  /**
   * Стиль для _erd
   *
   * @param bts (1) --
   * @return --
   */
  public WgMcmz buErdTextStyle(BuTextStyle bts) {
    mErdBuTextStyle = bts;
    return this;
  }

  public WgMcmz buErrorEnabled(boolean b) {
    mErrorEnabled = b;
    return this;
  }

  public WgMcmz buErrorTextStyle(@StyleRes int styleRes) {
    mTextErrorStyle = styleRes;
    return this;
  }

  public WgMcmz buHintText(CharSequence chs) {
    mHint = chs;
    return this;
  }

  public WgMcmz buValue(MmvdValue mmvdValue) {
    U.se(mmvdValue == null, "");
    //---
    this.mmvdValue = mmvdValue;
    //---
    mMultiline = false;
    //---
    return this;
  }

  public WgMcmz buAddTo(ViewGroup vg) {
    mParent = vg;
    return this;
  }

  public WgMcmz buDebugText(String st) {
    debugText = st;
    return this;
  }

  /**
   * См. _text в {@link BuAwoh}
   *
   * @param c (1) --
   * @return --
   */
  public WgMcmz buTextColor(Color2 c) {
    mColorText = c;
    return this;
  }

  /**
   * См. _text в {@link BuAwoh}
   *
   * @param csl (1) --
   * @return --
   */
  public WgMcmz buTextColor(ColorStateList csl) {
    mColorTextCSL = csl;
    return this;
  }

  /**
   * См. [cgeg] в {@link BuAwoh}
   *
   * @param c (1) --
   * @return --
   */
  public WgMcmz buHintColorFocused(Color2 c) {
    mColorHintFocused = c;
    return this;
  }

  /**
   * См. {@link BuAwoh#colorHint(Color2)}
   *
   * @param c (1) --
   * @return --
   */
  public WgMcmz buHintTextColor(Color2 c) {
    mColorHint = c;
    return this;
  }

  public WgMcmz buPresenter(Presenter p) {
    mPresenter = p;
    return this;
  }

  public WgMcmz buEnable(boolean b) {
    mEnable = b;
    return this;
  }

  public WgMcmz buActiveOnStart(boolean b) {
    this.bActiveOnStart = b;
    return this;
  }

  //endregion //builders


  //commands
  //``````````````````````````````````````````````````````````````````````````````````````````````
  //region commands >>>

  public void setFocusable(boolean b) {
    _til.setFocusable(b);
    _til.setFocusableInTouchMode(b);
  }

  /**
   * см. [yabw]::
   *
   * @return --
   */
  public EErrState getErrState() {
    return eErrState;
  }

  /**
   * Получение текущего текста (_txt)
   *
   * @return (1) --
   */
  public String getText() {
    return mmvdValue.textGet();
  }

  public void enable(boolean b) {
    _til.setEnabled(b);
    mBuPzub._enable(b);
  }

  /**
   * Если (1) TRUE то выводит виджет из активного режима (т.е. он теряет фокус и выходит из режима
   * редактирования).
   * <p>
   * Если (2) FALSE - ничего не делается
   *
   * @param b         (1) --
   * @param bEnhwMode (2) -- см. [enhw]
   */
  public void deactivate(boolean b, boolean bEnhwMode) {
    if (b) {
      //--- эта инструция должна быть перед инструкцией {1552}
      this.bEnhwMode = bEnhwMode;
      //--- {{1552}}
      _tilBu._setStateActive(false);
    }
  }

  public void activate(boolean b) {
    if (b) {
      _tilBu._setStateActive(true);
    }
  }

  /**
   * Получение _til
   *
   * @return --
   */
  public TextInputLayout getTextInputLayout() {
    return _til;
  }

  /**
   * Если (2) == TRUE то отображает _erd с текстом (1)
   *
   * @param st   (1) -- текст для отображения
   * @param show (2) -- TRUE чтобы отобразить _erd, FALSE чтобы скрыть
   */
  public void errmessageShow(String st, boolean show) {
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
  public void errlabelShow(String st, boolean show) {
    getTextInputLayout()
      .getEditText()
      .setError(show ? st : null);
  }
  //endregion


  //build
  //``````````````````````````````````````````````````````````````````````````````````````````````
  @Override
  public NLinearLayout build() {
    Vdin_01.logM("создание [uasa]-виджета", this);
    //---
    U.se(mmvdValue == null, "");

    //--- //inflate
    NLinearLayout _uxo = U.inflate(R.layout.n1468_uasa_lay, mContext);
    final TextInputLayout _tilSrc = _uxo.findViewById(R.id.n1468_tilSrc);
    NTextView _btn = _uxo.findViewById(R.id.n1468_btn);
    _tad = _uxo.findViewById(R.id.n1468_tad);

    //--- _til
    mtTil(_tilSrc);
    _til = _tilBu.apply(_tilSrc);

    //--- _btn
    mtBtn(_btn);

    //---
    EditText _et0 = _til.getEditText();
    _et0.setText(mmvdValue.textGet());
    //---
    if (mParent != null) {
      mParent.addView(_uxo);
    }
    //--- enable
    _til.setEnabled(mEnable);

    //---
    _tilBu._setStateActive(bActiveOnStart);

    //---
    return _uxo;
  }

  private void mtBtn(NTextView _btn) {
    ColorStateList csl = new BuCSL()
      .disable(EC.MD_TRANSPARENT)
      .pressed(EC.MD_RED_A700)
      .normal(EC.MD_BLACK_1000)
      .create();

    Margins_01 margins = new Margins_01(14, 14, 14, 14);
    mBuPzub = ((BuDerb_01) new BuDerb_01(mContext)
      .wh(48))
      .drawerFalse(new N1208_Check()
        .margins(margins)
        .colorStroke(csl)
      )
      .drawerTrue(new N1208_Pensil()
        .margins(margins)
        .colorStroke(csl)
      )
      .presenter(newState -> _tilBu._setStateActive(!newState))
      .startState(true);
    mBuPzub.apply(_btn);
  }

  private void mtTil(TextInputLayout _tilSrc) {
    _tilBu = new BuAwoh(mContext)
      .hint(mHint)
      .debugText(debugText)
      .colorText(mColorTextCSL)
      .colorHintFocused(mColorHintFocused)
      .colorHint(mColorHint)
      .multiline(mMultiline)
      .buInputType(Utils.inputTypeGet(mmvdValue.getClass()))
      .presenter(new BuAwoh.Presenter() {
        @Override
        public void n1468_onChangeActiveState(boolean active) {
          Vdin_01.logStart("n1468_onChangeActiveState(active=[" + active + "]) //190406-060500", WgMcmz.this);
          Vdin_01.logM("(" + active + ") получен фокус //190407-160400", WgMcmz.this);
          //---
          //link [170210092400]
          if (active == mBuPzub._getState()) {
            //переключение состояния кнопки
            mBuPzub._toggleState();
          }

          //---
          //если в фокусе
          if (active) {
            //убираем _errlabel
            _tilSrc
              .getEditText()
              .setError(null);
          }

          //--- техника [moru]
          if (gefp != null) {
            EventBus.getDefault().post(new EvBoolean(gefp, active));
          }

          //--- {{0911}} : текущий блок должен быть перед блоком {0912}
          if (!bEnhwMode) {
            if (!active) {
              //--- _txt
              String stText = _til.getEditText().getText().toString();
              CorrectionResult cr = mmvdValue.correct(stText);
              String stC = cr.getText();
              if (cr.isCorrected) {
                _til.getEditText().setText(stC);
              }
              //---
              ValidateResult vr = mmvdValue.textSetIf(stC);
              if (!vr.isValid) {
                errmessageShow(vr.stComment, true);
                eErrState = EErrState.ERR;
              } else {
                //навязываем скрытие текста с ошибкой
                errmessageShow("", false);
                eErrState = EErrState.NO_ERR;
              }
            }
          }
          bEnhwMode = false;

          //--- {{0912}} : текущий блок должен идти после блока {0911}
          if (mPresenter != null) {
            mPresenter.onFocusChange(active, eErrState);
          }

          //---
          Vdin_01.logEnd("", this);
        }

        @Override
        public void n1468_onTextChange(Editable st) {
          //---
          if (mPresenter != null) {
            mPresenter.onTextChange(st);
          }
          //---
          if (gefp != null) {
            EventBus.getDefault().post(new EvString(gefp, st.toString()));
          }
        }

        @Override
        public void n1468_onKeyBackPress() {
          Vdin_01.logM("нажата 'аппаратная кнопка Назад'", WgMcmz.this);

        }
      });
  } //mtTil

  public WgMcmz build_B() {
    build();
    return this;
  }

}

package ru.surrsoft.baaz.widgets2;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.AnimRes;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Calendar;

import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.TypeConsts;
import ru.surrsoft.baaz.cls_a.AndrAttrs_01;
import ru.surrsoft.baaz.cls_c.G67G_Dates;
import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.cls_c.G67G_Strings;
import ru.surrsoft.baaz.new_.Zvah;
import ru.surrsoft.baaz.suite.experiments.ecud.AnnEcud;
import ru.surrsoft.baaz.suite.experiments.ecud.EEcudTypes;
import ru.surrsoft.baaz.suite.figures.N1208_AbsDrawer;
import ru.surrsoft.baaz.suite.terms.widgets.Tgwgchoicesingpick;
import ru.surrsoft.baaz.univers.EFcar;
import ru.surrsoft.baaz.univers.EStrings;
import ru.surrsoft.baaz.univers.Ret2_j_bo;
import ru.surrsoft.baaz.univers.Trans;
import ru.surrsoft.baaz.univers.TwoColors;
import ru.surrsoft.baaz.univers.U;
import ru.surrsoft.baaz.univers.UniPresenter;
import ru.surrsoft.baaz.univers.UniPresenter_B;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;
import ru.surrsoft.baaz.widgets2.buedittext.BuEditText_01;
import ru.surrsoft.baaz.widgets2.nviews.NCheckBox;
import ru.surrsoft.baaz.widgets2.nviews.NTextView;
import ru.surrsoft.baaz.widgets2.utils.BuPopupMenu;
import ru.surrsoft.baaz.widgets2.utils.BuTextStyle;

/**
 * Метод {@link #create()} фактически возвращает тип {@link NTextView}
 * <p>
 * === ОПЦИИ ===
 * -- {@link #confirmDialog(BuDialogConfirm)}  - при нажатии, отображается диалог подтверждения
 * действия; при согласии, будет выполнен код заданный через {@link #onclick(View.OnClickListener)}
 * -- singleChoice - при нажатии на TextView отображается список одиночного выбора
 * -- если нужна рамка, то можно задействовать метод {@link #drawer(N1208_AbsDrawer)}, передав туда
 * например N1208_RectRound
 * <p>
 * #version 2.1 01.10.2016 #id [[w333w]]
 */
public class BuilderTV {

  protected final Context mContext;
  private TextView _tv;

  @AnnEcud(type = EEcudTypes.STRING)
  public CharSequence mText;

  @AnnEcud(type = EEcudTypes.COLOR_INT)
  public int mTextColor = Color.BLACK;

  @AnnEcud(type = EEcudTypes.INT)
  public int mFontSize_sp;

  @AnnEcud(type = EEcudTypes.GRAVITY)
  public int mGravitySelf;

  @AnnEcud(type = EEcudTypes.GRAVITY)
  public int mGravityIn;

  @AnnEcud(type = EEcudTypes.COLOR_INT)
  public int mColorPressed = Color.RED;

  //`````````````````````````````````````````````````````
  private int mPaddingL_px;
  private int mPaddingT_px;
  private int mPaddingR_px;
  private int mPaddingB_px;

  //`````````````````````````````````````````````````````
  @AnnEcud(type = EEcudTypes.INT)
  public int mMarginL_dp;
  @AnnEcud(type = EEcudTypes.INT)
  public int mMarginT_dp;
  @AnnEcud(type = EEcudTypes.INT)
  public int mMarginR_dp;
  @AnnEcud(type = EEcudTypes.INT)
  public int mMarginB_dp;

  //`````````````````````````````````````````````````````
  private boolean mIsPressed;
  private boolean mIsUnderline;
  private boolean mIsSingleChoice;
  private String[] mSingleChoiceElems;
  private int mSingleChoiceIndex;
  private int mBgColor;

  private TwoColors mTC_;
  private boolean bTextColor;
  private ViewGroup mLayParent;

  //wh
  //`````````````````````````````````````````````````````
  @AnnEcud(type = EEcudTypes.INT_PX)
  public int mW_px = ViewGroup.LayoutParams.WRAP_CONTENT;

  @AnnEcud(type = EEcudTypes.INT_PX)
  public int mH_px = ViewGroup.LayoutParams.WRAP_CONTENT;

  //`````````````````````````````````````````````````````
  @AnnEcud(type = EEcudTypes.NODE)
  public N1208_AbsDrawer mDrawer;

  private boolean mPickerIs;
  private EFcar mPickerETypedata;
  private String mPickerValue;
  private String mPickerValueIfEmpty;
  private UniPresenter_B mSCPresenter;
  private UniPresenter<String> mPickerPresenter;
  private boolean mChecked;
  private UniPresenter<Ret2_j_bo> mCheckedPresenter;
  private boolean mCheckedIs;
  private Object mTag;
  private Object mTag2;
  private View.OnClickListener mOnClick;
  private Class<?> mLayParamClass;
  private TwoColors mPickerTCNoValueState;
  private boolean mIsBold;
  private boolean mIsItalic;
  private BuTextStyle mTxtStyle;
  private Animation mPressViewAnimation;
  private View[] mLinkViews;
  private String mOwet; //[ecud]

  public String mEcudComment;
  private String[] mPickerOnechoiceStrings;
  private int mPickerOnechoiceStartIndex;
  private String mPickerTitle;
  private View[] mLinkViews2;
  private Trans<TextView> mTrans;
  private BuilderTV[] mBus;
  private BuDialogConfirm mConfirmDialog;
  private BuPopupMenu mPopupMenu;
  private boolean mEnable = true;
  /**
   * Техника [moru]
   */
  protected String mGefp;
  private int mWMin_px;
  private Typeface mTypeface;
  private String mSCKeySharPref;
  private boolean mIsSingleChoice2;
  private String mSCKeySharPref2;
  private UniPresenter<String> mSCPresenter2;
  private Zvah mSCZvah;
  private boolean b50;
  private ColorStateList mTextColorCSL;
  private boolean bTextColorCSL;

  //==============================================================================================
  public BuilderTV(Context context) {
    mContext = context;
    _tv = new NTextView(context);
  }

  public interface Get {
    void f(TextView tv, BuilderTV bu);
  }

  //create
  //``````````````````````````````````````````````````````````````````````````````````````````````

  /**
   * Чтобы получить ссылку на созданный виджет прямо в create()
   *
   * @param get (1) --
   * @return --
   */
  public TextView create(Get get) {
    TextView tv = create();
    get.f(tv, this);
    return tv;
  }

  /**
   * Метод-синоним для {@link #create()}
   *
   * @return --
   */
  public TextView build() {
    return create();
  }

  public TextView create() {
    //---
    if (mTxtStyle != null) {
      mFontSize_sp = mTxtStyle.getFontSize();
      mTypeface = mTxtStyle.getTypeface();
      mTextColor = mTxtStyle.getTextColor();
      bTextColor = true;
    }
    //--- ширина, высота
    if (mW_px != LinearLayout.LayoutParams.WRAP_CONTENT ||
      mH_px != LinearLayout.LayoutParams.WRAP_CONTENT) {
      ViewGroup.LayoutParams lp05 = getLP_x2();
      lp05.width = mW_px;
      lp05.height = mH_px;
      _tv.setLayoutParams(lp05);
    }
    _tv.setMinWidth(mWMin_px);
    //--- checkbox
    if (mCheckedIs) {
      NCheckBox cb = new NCheckBox(mContext);
      cb.setTag2(mTag2);
      cb.setChecked(mChecked);
      cb.setClickable(true);
      cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
          Ret2_j_bo r = new Ret2_j_bo(isChecked, buttonView);
          if (mCheckedPresenter != null) mCheckedPresenter.fun(r);
        }
      });
      _tv = cb;
    }
    //---
    if (mOnClick != null) {
      mIsPressed = true;
    }
    if (mPressViewAnimation != null || mPopupMenu != null || mOnClick != null) {
      _tv.setClickable(true);
      _tv.setOnClickListener(v -> {
        if (mPressViewAnimation != null) {
          mPressViewAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
              m11_x2(v);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
          });
          _tv.startAnimation(mPressViewAnimation);
        } else {
          m11_x2(v);
        }
      });
    }

    //---
    if (mTag != null) {
      _tv.setTag(mTag);
    }
    //---
    if (mPickerIs) {
      mIsPressed = true;
    }
    //---
    if (mTC_ != null) {
      mTC_.apply(_tv);
    }
    //---
    if (mTypeface != null) {
      _tv.setTypeface(mTypeface);
    }
    //---
    if (mFontSize_sp > 0) {
      _tv.setTextSize(mFontSize_sp);
    }
    mtTextSet_x5();
    //---
    if (bTextColor) {
      _tv.setTextColor(mTextColor);
    }
    if (bTextColorCSL) {
      _tv.setTextColor(mTextColorCSL);
    }
    //---
    _tv.setPadding(mPaddingL_px, mPaddingT_px, mPaddingR_px, mPaddingB_px);
    //--- single choice
    if (mIsSingleChoice) {
      if (mSingleChoiceElems == null) throw new SomeException("(debug)");
      if (mSingleChoiceElems.length < 2) throw new SomeException("(debug)");
      if (mSingleChoiceIndex < 0) throw new SomeException("(debug)");
      //==
      if (mSCKeySharPref != null) {
        SharedPreferences shp = mContext.getSharedPreferences(TypeConsts.SHARPREF_COMMON_NAME, 0);
        if (shp.contains(mSCKeySharPref)) {
          mSingleChoiceIndex = shp.getInt(mSCKeySharPref, mSingleChoiceIndex);
        } else {
          shp.edit().putInt(mSCKeySharPref, mSingleChoiceIndex).apply();
        }
      }
      //==
      mText = mSingleChoiceElems[mSingleChoiceIndex];
      mtTextSet_x5();
      //==
      mIsPressed = true;
      _tv.setClickable(true);
      _tv.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          AlertDialog.Builder b = new AlertDialog.Builder(mContext);
          b.setSingleChoiceItems(mSingleChoiceElems, mSingleChoiceIndex, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              mSingleChoiceIndex = which;
              //=
              if (mSCKeySharPref != null) {

                mContext
                  .getSharedPreferences(TypeConsts.SHARPREF_COMMON_NAME, 0)
                  .edit().putInt(mSCKeySharPref, mSingleChoiceIndex)
                  .apply();
              }
              //=
              _tv.setBackgroundDrawable(null);
              mtTextSet_x5();
              dialog.cancel();
            }
          });
          AlertDialog a = b.create();
          a.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
              if (mSCPresenter != null) {
                mSCPresenter.n1400_uniPresenter(mSingleChoiceIndex);
              }
            }
          });
          a.show();
        }
      });
    }

    if (mIsSingleChoice2) {
      String[] uxirs = mSCZvah._getUxirs();
      String currUxir = mSCZvah._getCurrUxir();
      //==
      mSingleChoiceElems = uxirs;
      //==
      if (mSCKeySharPref2 != null) {
        SharedPreferences shp = mContext.getSharedPreferences(TypeConsts.SHARPREF_COMMON_NAME, 0);
        if (shp.contains(mSCKeySharPref2)) {
          String currIevz = shp.getString(mSCKeySharPref2, "");
          mSCZvah._setCurrIevz(currIevz);
          mSingleChoiceIndex = mSCZvah._getCurrIndex();
        } else {
          shp.edit().putString(mSCKeySharPref2, mSCZvah._getCurrIevz()).apply();
        }
      }
      //==
      mSingleChoiceIndex = mSCZvah._getCurrIndex();
      //==
      mText = currUxir;
      mtTextSet_x5();

      //==
      mIsPressed = true;
      _tv.setClickable(true);
      _tv.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          AlertDialog.Builder b = new AlertDialog.Builder(mContext);
          b.setSingleChoiceItems(mSingleChoiceElems, mSingleChoiceIndex, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              //=
              int ix = mSCZvah._getCurrIndex();
              //
              if (ix != which) {
                mSingleChoiceIndex = which;
                b50 = true;
                //=
                mSCZvah._setCurrIndex(mSingleChoiceIndex);
                //=
                if (mSCKeySharPref2 != null) {
                  mContext
                    .getSharedPreferences(TypeConsts.SHARPREF_COMMON_NAME, 0)
                    .edit()
                    .putString(mSCKeySharPref2, mSCZvah._getCurrIevz())
                    .apply();
                }
                //=
                _tv.setBackgroundDrawable(null);
                mtTextSet_x5();
              }
              dialog.cancel();
            }
          });
          AlertDialog a = b.create();
          a.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
              if (mSCPresenter2 != null && b50) {
                b50 = false;
                mSCPresenter2.fun(mSCZvah._getCurrIevz());
              }
            }
          });
          a.show();
        }
      });
    }

    //---
    if (mIsPressed && bTextColor) {
      _tv.setClickable(true);
      int[][] states = new int[][]{new int[]{AndrAttrs_01.state_pressed}, // pressed
        new int[]{}}; // по умолчанию
      int val = mTextColor != 0 ? mTextColor : _tv.getCurrentTextColor();
      int[] statesColors = new int[]{mColorPressed, val};
      ColorStateList csl = new ColorStateList(states, statesColors);
      _tv.setTextColor(csl);
    }

    //---
    if (mBgColor != 0) {
      _tv.setBackgroundColor(mBgColor);
    }
    //---
    if (mLayParent != null && _tv.getParent() == null) {
      mLayParent.addView(_tv);
    }
    //---
    ViewGroup.LayoutParams lp = getLP_x2();
    lp.width = mW_px;
    lp.height = mH_px;
    _tv.setLayoutParams(lp);
    //--- margins
    if (mMarginL_dp != 0 || mMarginT_dp != 0 || mMarginR_dp != 0 || mMarginB_dp != 0) {
      ViewGroup.MarginLayoutParams lp1 = (ViewGroup.MarginLayoutParams) lp;
      lp1.leftMargin = G67G_Draw.px(mMarginL_dp);
      lp1.topMargin = G67G_Draw.px(mMarginT_dp);
      lp1.rightMargin = G67G_Draw.px(mMarginR_dp);
      lp1.bottomMargin = G67G_Draw.px(mMarginB_dp);
      _tv.setLayoutParams(lp1);
    }
    //--- gravitySelf
    if (mGravitySelf != 0) {
      //gravity есть только у LinearLayout
      if (lp.getClass().equals(LinearLayout.LayoutParams.class)) {
        ((LinearLayout.LayoutParams) lp).gravity = mGravitySelf;
        _tv.setLayoutParams(lp);
      } else if (lp.getClass().equals(FrameLayout.LayoutParams.class)) {
        ((FrameLayout.LayoutParams) lp).gravity = mGravitySelf;
        _tv.setLayoutParams(lp);
      }
    }
    //---
    if (mGravityIn != 0) {
      _tv.setGravity(mGravityIn);
    }
    //---
    if (mDrawer != null) {
      if (_tv instanceof NTextView) {
        ((NTextView) _tv).drawer(mDrawer);
      }
      ViewTreeObserver vto = _tv.getViewTreeObserver();
      if (vto.isAlive()) {
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
          @Override
          public boolean onPreDraw() {
            //борьба с проблемой [w130w]
            mDrawer
              .w_px(_tv.getWidth())
              .h_px(_tv.getHeight());
            return true;
          }
        });
      }
    }
    //--- picker
    if (mPickerIs) {
      if (G67G_Strings.isValid(mPickerValue)) {
        String xText = mPickerValue;
        if (mPickerIs && G67G_Strings.isValid(xText)
          && (mPickerETypedata == EFcar.FLOAT || mPickerETypedata == EFcar.INTEGER)) {
          xText = G67G_Strings.addDividersBigNumbers(xText, " ", 3);
        }
        _tv.setText(xText);
        textAppearances_x3();
      } else {
        _tv.setText(mPickerValueIfEmpty);
        mPickerTCNoValueState.apply(_tv);
      }
      _tv.setClickable(true);
      _tv.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          switch (mPickerETypedata) {
            case DATE:
              dateDialog();
              break;
            case ONECHOICE:
              onechoiceDialog();
              break;
            default:
              editTextDialog();
              break;
          }
        }
      });
    }
    //---
    _tv.setTag(this);
    //---
    if (mLinkViews != null) {
      if (mLinkViews.length == 1) {
        mLinkViews[0] = _tv;
      } else {
        throw new SomeException("(debug) массив должен быть длиной ровно 1");
      }
    }
    if (mLinkViews2 != null) {
      mLinkViews2 = ArrayUtils.add(mLinkViews2, _tv);
    }
    //=
    if (mTrans != null) {
      mTrans.val = _tv;
    }
    //=
    if (mBus != null) {
      mBus = ArrayUtils.add(mBus, this);
    }
    //---
    if (mDrawer != null) {
      //на случай когда drawer с анимацией
      mDrawer.animView(_tv);

    }
    //---
    _tv.setEnabled(mEnable);
    //---
    return _tv;
  }

  private void onechoiceDialog() {
    //T O D O
  }

  //``````````````````````````````````````````````````````````````````````````````````````````````
  public NTextView createN() {
    return ((NTextView) create());
  }


  public TextView apply(TextView tv) {
    _tv = tv;
    create();
    return _tv;
  }

  //commands
  //``````````````````````````````````````````````````````````````````````````````````````````````

  /**
   * Следует использовать данный метод для установки текста если задействуется отрисовка на фоне
   * этого TextView либо задействуются стили текста
   *
   * @param s (1) --
   */
  public void _setText(CharSequence s) {
    mText = s;
    mtTextSet_x5();
  }

  private void mtTextSet_x5() {
    //---
    if (mIsSingleChoice || mIsSingleChoice2) {
      mSingleChoiceIndex = mSingleChoiceIndex < mSingleChoiceElems.length ? mSingleChoiceIndex : 0;
      mText = mSingleChoiceElems[mSingleChoiceIndex];
    }
    if (mText != null && mText.length() > 0) {
      if (mText instanceof Spannable) {
        Spannable spText = (Spannable) this.mText;
        _tv.setText(spText);
      } else {
        if (mIsUnderline || mIsBold || mIsItalic) {
          SpannableString sps = new SpannableString(mText);
          if (mIsUnderline) sps.setSpan(new UnderlineSpan(), 0, sps.length(), 0);
          if (mIsBold) sps.setSpan(new StyleSpan(Typeface.BOLD), 0, sps.length(), 0);
          if (mIsItalic) sps.setSpan(new StyleSpan(Typeface.ITALIC), 0, sps.length(), 0);
          _tv.setText(sps);
        } else {
          _tv.setText(mText);
        }
      }
    }
  }

  public void _enable(boolean b) {
    _tv.setEnabled(b);
  }

  public void _enable(boolean b, String from) {
    _enable(b);
  }

  //builders
  //``````````````````````````````````````````````````````````````````````````````````````````````

  /**
   * Техника [moru]
   *
   * @param gefp (1) -- [gefp]
   * @return --
   */
  public BuilderTV gefp(String gefp) {
    mGefp = gefp;
    return this;
  }

  public BuilderTV enable(boolean b) {
    mEnable = b;
    return this;
  }

  public BuilderTV marginR(int dp) {
    mMarginR_dp = dp;
    return this;
  }

  //[ecud]
  public BuilderTV ecudComment(String st) {
    mEcudComment = st;
    return this;
  }

  public BuilderTV text(CharSequence text) {
    mText = text;
    return this;
  }

  public BuilderTV textColor(int color) {
    bTextColor = true;
    mTextColor = color;
    return this;
  }

  public BuilderTV textColor(ColorStateList csl) {
    bTextColorCSL = true;
    mTextColorCSL = csl;
    return this;
  }

  /**
   * Синоним для {@link #fontSize(int)}
   *
   * @param sp
   * @return
   */
  public BuilderTV textSize(int sp) {
    mFontSize_sp = sp;
    return this;
  }

  public BuilderTV paddingL(int dp) {
    mPaddingL_px = G67G_Draw.px(dp);
    return this;
  }

  public BuilderTV paddingT(int dp) {
    mPaddingT_px = G67G_Draw.px(dp);
    return this;
  }

  public BuilderTV paddingR(int dp) {
    mPaddingR_px = G67G_Draw.px(dp);
    return this;
  }

  public BuilderTV paddingB(int dp) {
    mPaddingB_px = G67G_Draw.px(dp);
    return this;
  }

  public BuilderTV paddings(int dp) {
    int px = G67G_Draw.px(dp);
    mPaddingL_px = px;
    mPaddingT_px = px;
    mPaddingR_px = px;
    mPaddingB_px = px;
    return this;
  }

  public BuilderTV paddings(int l_dp, int t_dp, int r_dp, int b_dp) {
    mPaddingL_px = G67G_Draw.px(l_dp);
    mPaddingT_px = G67G_Draw.px(t_dp);
    mPaddingR_px = G67G_Draw.px(r_dp);
    mPaddingB_px = G67G_Draw.px(b_dp);
    return this;
  }

  public BuilderTV margins(int l_dp, int t_dp, int r_dp, int b_dp) {
    mMarginL_dp = l_dp;
    mMarginT_dp = t_dp;
    mMarginR_dp = r_dp;
    mMarginB_dp = b_dp;
    return this;
  }

  public BuilderTV pressed() {
    mIsPressed = true;
    return this;
  }

  /**
   * Чтобы это сработало текст уже должен быть задан предварительно методом {@link #_setText(CharSequence)}
   *
   * @param b --
   * @return --
   */
  public BuilderTV underline(boolean b) {
    mIsUnderline = b;
    return this;
  }

  public BuilderTV bold(boolean b) {
    mIsBold = b;
    return this;
  }

  public BuilderTV italic(boolean b) {
    mIsItalic = b;
    return this;
  }

  public BuilderTV textColorPressed(int c) {
    mColorPressed = c;
    return this;
  }

  public BuilderTV pressViewAnimation(Animation anim) {
    mPressViewAnimation = anim;
    return this;
  }

  public BuilderTV pressViewAnimation(@AnimRes int resAnim) {
    if (mPressViewAnimation != null) throw new SomeException("(debug) анимация уже задана");
    mPressViewAnimation = AnimationUtils.loadAnimation(mContext, resAnim);
    return this;
  }

  /**
   * Превращает TextView в {@link Tgwgchoicesingpick picker-списка-одиночного-выбора}
   *
   * @param elems             (1) --
   * @param textNoSelect      (2) -- текст для элемента "ничего не выбрано"; null если он не нужен
   * @param defaultStartIndex (3) -- начальный индекс выбранного элемента [0...]
   * @param keySharPref       (4) -- ключ хранения индекса в TypeConsts#SHARPREF_COMMON_NAME; если NULL
   *                          то используется индекс (3)
   * @param uniPresenter      (5) -- сюда передается выбранный индекс
   * @return --
   */
  public BuilderTV singleChoice(String[] elems,
                                String textNoSelect,
                                int defaultStartIndex,
                                String keySharPref,
                                UniPresenter_B uniPresenter) {
    mIsSingleChoice = true;
    //---
    if (defaultStartIndex > elems.length - 1) defaultStartIndex = -1;
    mSingleChoiceElems = elems;
    if (textNoSelect != null) {
      mSingleChoiceElems = ArrayUtils.add(mSingleChoiceElems, 0, textNoSelect);
    }
    //---
    mSingleChoiceIndex = defaultStartIndex;
    //---
    mSCPresenter = uniPresenter;
    //---
    mSCKeySharPref = keySharPref;
    //---
    return this;
  }

  /**
   * Альтернативный вариант превращения this в список одиночного выбора
   *
   * @param zvah         (1) -- тип [zvah]
   * @param keySharPref  (2) --
   * @param uniPresenter (3) -- сюда возвращается [ievz] выбранного элемента. Если был выбран и так
   *                     уже выбранный элементо то обратный вызов не выполняется
   * @return
   */
  public BuilderTV singleChoice2(Zvah zvah, String keySharPref, UniPresenter<String> uniPresenter) {
    mSingleChoiceElems = zvah._getUxirs();
    //---
    mIsSingleChoice2 = true;
    //---
    mSCZvah = zvah;
    //---
    mSCKeySharPref2 = keySharPref;
    //---
    mSCPresenter2 = uniPresenter;
    //---
    return this;
  }

  public BuilderTV bgColor(int color) {
    mBgColor = color;
    return this;
  }

  public BuilderTV textFont(Typeface tf) {
    mTypeface = tf;
    return this;
  }

  /**
   * Синоним для {@link #textSize(int)}
   *
   * @param sp
   * @return
   */
  public BuilderTV fontSize(int sp) {
    mFontSize_sp = sp;
    return this;
  }

  public BuilderTV twocolors(TwoColors tc) {
    mTC_ = tc;
    return this;
  }

  public BuilderTV addTo(ViewGroup lay) {
    mLayParent = lay;
    return this;
  }

  /**
   * Позиционирование себя относительно контейнера
   *
   * @param g (1) -- например {@link Gravity#CENTER_VERTICAL}
   * @return --
   */
  public BuilderTV gravitySelf(int g) {
    mGravitySelf = g;
    return this;
  }

  /**
   * Позиционирование текста
   *
   * @param g (1) --
   * @return --
   */
  public BuilderTV gravityIn(int g) {
    mGravityIn = g;
    return this;
  }

  /**
   * В основе должен быть NTextView чтобы отрисовка состоялась
   *
   * @param drawer
   * @return
   */
  public BuilderTV drawer(N1208_AbsDrawer drawer) {
    mDrawer = drawer;
    //--- чтобы не вызывать apply() когда drawer с анимацией
    if (_tv != null && mDrawer != null) {
      mDrawer.animView(_tv);
      ((NTextView) _tv).drawer(mDrawer);
    }
    return this;
  }

  public BuilderTV wMP() {
    mW_px = ViewGroup.LayoutParams.MATCH_PARENT;
    return this;
  }

  public BuilderTV hMP() {
    mH_px = ViewGroup.LayoutParams.MATCH_PARENT;
    return this;
  }

  /**
   * Позволяет превратить this в picker
   *
   * @param etypedata          (1) --
   * @param startValue         (2) -- стартовое значение
   * @param valueIfEmpty       (3) -- значение на случай когда (2) пустой
   * @param uniPresenter       (4) -- возвращает строку полученную в результате работы пикера; для
   *                           типа EFcar.DATE это будет дата в формате w319w
   * @param tcNoValueState     (5) --
   * @param onchoiceStings     (6) -- используется только когда (1) == EFcar.ONECHOICE
   * @param onchoiceStartIndex (7) -- используется только когда (1) == EFcar.ONECHOICE
   * @param title              (8) -- текст для заголовка
   * @return --
   */
  public BuilderTV isPicker(EFcar etypedata,
                            String startValue,
                            String valueIfEmpty,
                            UniPresenter<String> uniPresenter,
                            TwoColors tcNoValueState,
                            String[] onchoiceStings,
                            int onchoiceStartIndex,
                            @Nullable String title) {
    mPickerIs = true;
    mPickerETypedata = etypedata;
    mPickerValue = startValue;
    mPickerValueIfEmpty = valueIfEmpty;
    mPickerPresenter = uniPresenter;
    mPickerTCNoValueState = tcNoValueState;
    mPickerOnechoiceStrings = onchoiceStings;
    mPickerOnechoiceStartIndex = onchoiceStartIndex;
    mPickerTitle = title;
    return this;
  }

  /**
   * @param checked
   * @param presenter (2) -- в "o" объекта {@link Ret2_j_bo} возвращается CompoundButton (т.е. ссылка на нажатый чекбокс)
   * @return
   */
  public BuilderTV isCheckbox(boolean checked, UniPresenter<Ret2_j_bo> presenter) {
    mCheckedIs = true;
    mChecked = checked;
    mCheckedPresenter = presenter;
    return this;
  }

  public BuilderTV tag(Object oj) {
    mTag = oj;
    return this;
  }

  public BuilderTV onclick(View.OnClickListener lst) {
    mOnClick = lst;
    return this;
  }

  /**
   * Отличается от А только тем что если (1) FALSE то присвоение слушателя (2) не происходит
   *
   * @param condition (1) --
   * @param lst       (2) --
   * @return --
   */
  public BuilderTV onclick_B(boolean condition, View.OnClickListener lst) {
    if (condition) {
      onclick(lst);
    }
    return this;
  }


  public BuilderTV layParamClass(Class<?> c) {
    mLayParamClass = c;
    return this;
  }

  public BuilderTV textStyle(BuTextStyle bu) {
    mTxtStyle = bu;
    return this;
  }

  public BuilderTV wh(int wh_dp) {
    mW_px = G67G_Draw.px(wh_dp);
    mH_px = G67G_Draw.px(wh_dp);
    return this;
  }

  public BuilderTV linkView(View[] views) {
    mLinkViews = views;
    return this;
  }

  public BuilderTV linkView2(View[] views) {
    mLinkViews2 = views;
    return this;
  }

  public BuilderTV linkView3(Trans<TextView> trans) {
    mTrans = trans;
    return this;
  }

  public BuilderTV linkBu(BuilderTV[] bus) {
    mBus = bus;
    return this;
  }


  public BuilderTV tag2(Object oj) {
    mTag2 = oj;
    return this;
  }

  public Object getTag2() {
    return mTag2;
  }

  public BuilderTV w(int dp) {
    mW_px = G67G_Draw.px(dp);
    return this;
  }

  public BuilderTV h(int dp) {
    mH_px = G67G_Draw.px(dp);
    return this;
  }

  public BuilderTV wMin(int dp) {
    mWMin_px = U.px(dp);
    return this;
  }

  /**
   * Диалог подверждения перед тем как будет вызвано переданное в {@link #onclick(View.OnClickListener)}
   *
   * @param bdc (1) --
   * @return --
   */
  public BuilderTV confirmDialog(BuDialogConfirm bdc) {
    mConfirmDialog = bdc;
    return this;
  }

  public BuilderTV popupMenu(BuPopupMenu bpm) {
    mPopupMenu = bpm;
    return this;
  }

  public interface BTVPresenter {
    /**
     * Результат выбора. Вызывается при каждом check элемента списка
     *
     * @param index (1) -- индекс выбранного элемента; если -1 то значит ничего не выбрано
     */
    void onSelected(int index);

    /**
     * Вызывается по окончании диалога
     */
    void onCancel();
  }

  public int getW_px() {
    return mW_px;
  }

  public int getH_px() {
    return mH_px;
  }

  public BuilderTV owet(String owet) { //[ecud]
    mOwet = owet;
    return this;
  }

  //private
  //``````````````````````````````````````````````````````````````````````````````````````````````

  private void m11_x2(final View v) {
    if (mPopupMenu != null) {
      mPopupMenu
        .show(_tv);
    } else if (mOnClick != null) {
      if (mConfirmDialog != null) {
        mConfirmDialog
          .presenter(new BuDialogConfirm.Presenter() {
            @Override
            public void n1464_ok() {
              mOnClick.onClick(v);
            }
          })
          .show();
      } else {
        mOnClick.onClick(v);
      }
    }
  }

  private void textAppearances_x3() {
    if (mTC_ != null) {
      mTC_.apply(_tv);
    } else {
      _tv.setTextColor(mTextColor);
    }
  }

  private void dateDialog() {
    //--- текущая дата
    Calendar c1 = G67G_Dates.bgitToCalendar(mPickerValue);
    if (c1 == null) c1 = Calendar.getInstance(); //текущая дата
    //---
    int year = c1.get(Calendar.YEAR);
    int month = c1.get(Calendar.MONTH);
    int day = c1.get(Calendar.DAY_OF_MONTH);
    //---
    DatePickerDialog dpd = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
      @Override
      public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        String date_w319w = G67G_Dates.bgitCreate(year, monthOfYear, dayOfMonth);
        mPickerValue = date_w319w;
        if (G67G_Strings.isValid(date_w319w)) {
          _tv.setText(date_w319w);
          textAppearances_x3();
        } else {
          _tv.setText(mPickerValueIfEmpty);
          mPickerTCNoValueState.apply(_tv);
        }
        _tv.setText(date_w319w);
        if (mPickerPresenter != null) mPickerPresenter.fun(date_w319w);
      }
    }, year, month, day);
    DatePicker dp = dpd.getDatePicker();
    dp.setCalendarViewShown(false);
    dp.setSpinnersShown(true);
    dpd.show();
  }


  private void editTextDialog() {
    //==
    AlertDialog.Builder b = new AlertDialog.Builder(mContext);
    LinearLayout lay = new BuLayLinear_01(mContext)
      .paddings(16)
      .build();
    View v1;
    EditText et = new BuEditText_01(mContext)
      .buAddTo(lay)
      .buText(mPickerValue)
      .build();
    et.setInputType(mPickerETypedata.iFend);
    v1 = et;
    b.setView(lay);
    //==
    final View finalV = v1;
    b.setTitle(mPickerTitle);
    b.setPositiveButton(EStrings._OK.val(), new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        String s = ((EditText) finalV).getText().toString();
        mPickerValue = s;
        if (G67G_Strings.isValid(s)) {
          if (mPickerETypedata == EFcar.FLOAT || mPickerETypedata == EFcar.INTEGER) {
            //разделители для больших чисел
            String s2 = G67G_Strings.addDividersBigNumbers(s, " ", 3);
            _tv.setText(s2);
          } else {
            _tv.setText(s);
          }
          textAppearances_x3();
        } else {
          _tv.setText(mPickerValueIfEmpty);
          mPickerTCNoValueState.apply(_tv);
        }
        if (mPickerPresenter != null) mPickerPresenter.fun(s);
      }
    });
    b.setNegativeButton(EStrings._CANCEL.val(), new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {

      }
    });
    AlertDialog a = b.create();
    //автоматический показ клавиатуры
    a.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    a.show();
  }

  private ViewGroup.LayoutParams getLP_x2() {
    ViewGroup.LayoutParams lp = _tv.getLayoutParams();
    if (lp != null) return lp;
    //---
    if (mLayParamClass != null) {
      if (mLayParamClass.equals(RelativeLayout.LayoutParams.class)) {
        lp = new RelativeLayout.LayoutParams(mW_px, mH_px);
      } else if (mLayParamClass.equals(FlexboxLayout.LayoutParams.class)) {
        lp = new FlexboxLayout.LayoutParams(mW_px, mH_px);
      } else if (mLayParamClass.equals(FrameLayout.LayoutParams.class)) {
        lp = new FrameLayout.LayoutParams(mW_px, mH_px);
      }
    }
    if (lp == null)
      lp = new LinearLayout.LayoutParams(mW_px, mH_px);
    return lp;
  }

}

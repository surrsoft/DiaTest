package ru.surrsoft.baaz.widgets.switchers;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;

import ru.surrsoft.baaz.cls_c.G67G_Draw;
import ru.surrsoft.baaz.univers.Color2;
import ru.surrsoft.baaz.widgets.buttons.N2249_BtnPNG;

/**
 * Кнопка для которой задаются параметры в виде Drawable's и цветов для состояний normal, pressed, disable.
 * Текущий класс самостоятельно "перекрашивает" заданный Drawable для вышеуказанных цветов.
 * От {@link N2249_BtnPNG} отличается тем что имеет несколько _состояний (несколько Drawable) между
 * которыми происходит
 * последовательное циклическое переключение при нажатиях
 * <p/>
 * Параметры задаются через ySetParams. Для обратных вызовов предназначен {@link #_presenter}
 * <p/>
 * Для изменения размеров Drawable внутри контейнера можно задавать padding
 * <p/>
 * <li> ТЕРМИНЫ </li>
 * <li> -- _state (_состояние) </li>
 * <p/>
 * #version 1 04.06.2016  #id [[w258w]]
 */
public class N2258_BtnPNGMulti extends AppCompatImageView {


  private Context mContext;
  private N2258_State[] _states = {};
  private int _currStateIndex;
  public N2258_Presenter _presenter;

  public interface N2258_Presenter {
    void n2258_onClick(int stateBeforeClick, int stateAfterClick);
  }

  //1 //constructors
  public N2258_BtnPNGMulti(Context context) {
    super(context);
    init(context);
  }

  public N2258_BtnPNGMulti(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public N2258_BtnPNGMulti(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }
  //2 //constructors

  private void init(Context context) {
    mContext = context;
    this.setClickable(true);
    this.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        int stateBefore = _currStateIndex;
        _currStateIndex++;
        if (_currStateIndex >= _states.length) {
          _currStateIndex = 0;
        }
        setState(_states[_currStateIndex]);
        if (_presenter != null) _presenter.n2258_onClick(stateBefore, _currStateIndex);
      }
    });
  }

  /**
   * Задание параметров - drawables и цветов
   *
   * @param states     (1) -- состояния
   * @param startState (2) -- отсчет с 0
   */
  public void ySetParams(N2258_State[] states, int startState) {
    _states = states;
    _currStateIndex = startState;
    setState(states[startState]);
  }

  /**
   * Установка _состояния
   *
   * @param state (1) -- _состояние
   */
  private void setState(N2258_State state) {
    StateListDrawable drw2 = G67G_Draw.drawableToStateListDrawable_D(
      state.drw,
      state.normalColor,
      state.pressedColor,
      state.disabledColor
    );
    this.setImageDrawable(drw2);
  }

  /**
   * Установка _состояиня через индекс
   *
   * @param stateIndex (1) -- индекс состояние, отсчет с 0
   * @param reaction   (2) -- TRUE если нужно чтобы сработал обратный вызов {@link #_presenter}
   */
  public void setState(int stateIndex, boolean reaction) {
    int beforeStateIndex = _currStateIndex;
    _currStateIndex = stateIndex;
    setState(_states[stateIndex]);
    if (_presenter != null && reaction) {
      _presenter.n2258_onClick(beforeStateIndex, stateIndex);
    }
  }

  /**
   * Представление для _состояния
   */
  public static class N2258_State {
    private final Drawable drw;
    private final Color2 normalColor;
    private final Color2 pressedColor;
    private final Color2 disabledColor;

    public N2258_State(Drawable drw, Color2 normalColor, Color2 pressedColor, Color2 disabledColor) {
      this.drw = drw;
      this.normalColor = normalColor;
      this.pressedColor = pressedColor;
      this.disabledColor = disabledColor;
    }
  }
}

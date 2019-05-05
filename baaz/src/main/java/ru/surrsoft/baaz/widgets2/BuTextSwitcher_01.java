package ru.surrsoft.baaz.widgets2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import ru.surrsoft.baaz.R;
import ru.surrsoft.baaz.univers.Color2;
import ru.surrsoft.baaz.univers.U;

/**
 * Билдер анимированного переключателя 2-х (не более) TextView - TextSwitcher
 * <p>
 * КАК РАБОТАТЬ:
 * -- либо указать фабрику создающую текстовый элемент (см. {@link #buViewFactory(ViewSwitcher.ViewFactory)} ).
 * То что будет возвращать эта фабрика будет использоваться как виджет при переключении.
 * Второй вариант, это задать виджеты с помощью методов {@link #buViewFirst(TextView)} и
 * {@link #buViewSecond(TextView)}
 * -- текст меняется с помощью метода textSwitcher.setText(...), либо buTextSwitcher_01.textSet(...)
 * <p>
 * ДАЛЕЕ:
 * По умолчанию создаётся TextSwitcher с дефолтной анимацией появления и исчезновения текста.
 * Если TextView передаваемые в this, если они обладают LayoutParams, должны обладать именно
 * FrameLayout.LayoutParams, иначе будет ошибка
 * <p>
 * ДЕМО: ABuTextSwitcherDemo_01
 */
public class BuTextSwitcher_01 {
  private final Context ctx;
  private ViewGroup layParent;
  private TextView tv1;
  private TextView tv2;
  private ViewSwitcher.ViewFactory viewFactory;
  private String stTextStart;
  private Animation animIn;
  private Animation animOut;
  private View.OnClickListener onclick;
  private TextSwitcher ret;
  private Color2 bgColor;

  public BuTextSwitcher_01(Context ctx) {
    this.ctx = ctx;
    //---
    animIn = AnimationUtils.loadAnimation(ctx, R.anim.g67g_slide_in_left);
    animOut = AnimationUtils.loadAnimation(ctx, R.anim.g67g_slide_out_right);
  }

  public BuTextSwitcher_01 buAddTo(ViewGroup layParent) {
    this.layParent = layParent;
    return this;
  }

  public BuTextSwitcher_01 buViewFirst(TextView tv1) {
    this.tv1 = tv1;
    return this;
  }

  public BuTextSwitcher_01 buViewSecond(TextView tv2) {
    this.tv2 = tv2;
    return this;
  }

  public BuTextSwitcher_01 buTextStart(String stText) {
    this.stTextStart = stText;
    return this;
  }

  /**
   * Анимация появления текста
   *
   * @param animIn (1) --
   * @return --
   */
  public BuTextSwitcher_01 buAnimationIn(Animation animIn) {
    this.animIn = animIn;
    return this;
  }

  /**
   * Анимация исцезновения текста
   *
   * @param animOut (1) --
   * @return --
   */
  public BuTextSwitcher_01 buAnimationOut(Animation animOut) {
    this.animOut = animOut;
    return this;
  }

  /**
   * Фабрика (1) должна возвращть текстовый виджет, который будет использоваться для вставки в него
   * текста.
   * Если используется BuilderTV, то нужно чтобы он создавался с
   * .layParamClass(FrameLaout.LayoutParams.class)
   *
   * @param factory (1) --
   * @return --
   */
  public BuTextSwitcher_01 buViewFactory(ViewSwitcher.ViewFactory factory) {
    this.viewFactory = factory;
    return this;
  }

  public BuTextSwitcher_01 buOnclick(View.OnClickListener onclick) {
    this.onclick = onclick;
    return this;
  }

  /**
   * Задание фона TextSwitcher (он ам по себе является по сути обычным FrameLayout)
   *
   * @param bgColor (1) --
   * @return --
   */
  public BuTextSwitcher_01 buBgColor(Color2 bgColor) {
    this.bgColor = bgColor;
    return this;
  }

  //--- build
  public TextSwitcher build() {
    U.se((viewFactory != null) && (tv1 != null || tv2 != null),
      "нужно задать одно из двух: 1) viewFactory; 2) отдельные 2 view методами buAddView...");
    U.se(viewFactory == null && tv1 == null && tv2 == null,
      "нужно задать одно из двух: 1) viewFactory; 2) отдельные 2 view методами buAddView...");
    //---
    ret = new TextSwitcher(ctx);
    //--- чтобы контейнер подстраивался под новый текст, если этот новый текст сильно короче текущего текста
    ret.setMeasureAllChildren(false);
    //---
    if (viewFactory != null) {
      ret.setFactory(viewFactory);
    }
    //---
    if (tv1 != null) {
      ret.addView(tv1);
    }
    //---
    if (tv1 != null) {
      ret.addView(tv2);
    }
    //---
    if (animIn != null) {
      ret.setInAnimation(animIn);
    }
    //---
    if (animOut != null) {
      ret.setOutAnimation(animOut);
    }
    //---
    if (onclick != null) {
      ret.setOnClickListener(onclick);
    }
    //---
    if (stTextStart != null) {
      ret.setText(stTextStart);
    }
    //---
    if (bgColor != null) {
      ret.setBackgroundColor(bgColor.val);
    }
    //---
    if (layParent != null) {
      layParent.addView(ret);
    }
    //---
    return ret;
  }

  /**
   * Берёт следующий виджет, вставляет в него текст (1) и заменяет этим новым виджетом текущий виджет
   *
   * @param stText (1) --
   */
  public void textSet(String stText) {
    ret.setText(stText);
  }

}

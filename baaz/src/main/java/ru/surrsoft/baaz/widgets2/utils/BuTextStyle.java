package ru.surrsoft.baaz.widgets2.utils;

import android.graphics.Color;
import android.graphics.Typeface;

/**
 * Оформление текста (цвет, шрифт, размер шрифта)
 * <p>
 * ОПЦИИ:
 * -- есть метод клонирования {@link #clone()}
 * <p>
 * ID: [[w427w]]
 */
public class BuTextStyle implements Cloneable {


  private Typeface mTypeface;
  private int mFontSize = 14;
  private int mTextColor = Color.BLACK;

  //--- construstors
  public BuTextStyle() {
  }

  //---
  public Typeface getTypeface() {
    return mTypeface;
  }

  public int getFontSize() {
    return mFontSize;
  }

  public int getTextColor() {
    return mTextColor;
  }

  //builders
  //``````````````````````````````````````````````````````````````````````````````````````````````
  public BuTextStyle buTextFace(Typeface etf) {
    mTypeface = etf;
    return this;
  }

  public BuTextStyle buTextSize(int sp) {
    mFontSize = sp;
    return this;
  }

  public BuTextStyle buTextColor(int c) {
    mTextColor = c;
    return this;
  }

  //``````````````````````````````````````````````````````````````````````````````````````````````

  /**
   * @return --
   */
  @Override
  public BuTextStyle clone() {
    try {
      super.clone();
      return new BuTextStyle()
        .buTextFace(this.getTypeface())
        .buTextColor(this.getTextColor())
        .buTextSize(this.getFontSize());
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
      throw new RuntimeException("(debug)");
    }
  }

}

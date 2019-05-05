package ru.surrsoft.baaz.widgets2.forwg;

import android.graphics.Color;
import android.graphics.drawable.Drawable;

/**
 * POJO
 * <p>
 * ID [[pyux]]
 * <p>
 * ВЕРСИЯ 1 1.0 2018-11-18 (stored)
 */
public class Pyux {

  private Drawable drw;
  private TxPaddings paddings = new TxPaddings(0);
  private int colorNormal = Color.TRANSPARENT;

  //constructors
  //```````````````````````````````````````````````````````````````````````````````````````````````
  public Pyux() {
  }

  //builders
  //```````````````````````````````````````````````````````````````````````````````````````````````
  public Pyux buDrw(Drawable drw) {
    this.drw = drw;
    return this;
  }

  public Pyux buPaddings(TxPaddings p) {
    paddings = p;
    return this;
  }

  public Pyux buColorNormal(int colorNormal) {
    this.colorNormal = colorNormal;
    return this;
  }

  //build
  //```````````````````````````````````````````````````````````````````````````````````````````````
  public Pyux build() {
    return this;
  }

  //getters
  //```````````````````````````````````````````````````````````````````````````````````````````````
  public Drawable drwGet() {
    return this.drw;
  }

  public TxPaddings paddingsGet() {
    return this.paddings;
  }

  public int colorNormalGet() {
    return this.colorNormal;
  }


}

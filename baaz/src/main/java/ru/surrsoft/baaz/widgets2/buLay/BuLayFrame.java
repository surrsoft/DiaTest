package ru.surrsoft.baaz.widgets2.buLay;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;

import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.tclasses.TArray_01;
import ru.surrsoft.baaz.univers.U;

/**
 * ВЕРСИЯ 1 1.0 2019-02-05 (stored)
 */
public class BuLayFrame {
  private final Context mContext;
  private final ArrayList<View> mChilds = new ArrayList<>();
  private ViewGroup mParent;
  private boolean bAsConentView;

  //constructors
  //``````````````````````````````````````````````````````````````````````````````````````````````


  public BuLayFrame(Context ctx) {
    mContext = ctx;
  }

  //builders
  //``````````````````````````````````````````````````````````````````````````````````````````````

  public BuLayFrame addChild(View view) {
    mChilds.add(view);
    return this;
  }

  public BuLayFrame addChild(View view, Back back) {
    if (view == null) {
      throw new SomeException("(debug)");
    }
    if (back == null) {
      throw new SomeException("(debug)");
    }
    //---
    mChilds.add(view);
    //---
    back.fun(view);
    //---
    return this;
  }

  public BuLayFrame addTo(ViewGroup vg) {
    this.mParent = vg;
    return this;
  }

  public BuLayFrame buAsContentView(boolean bAsConentView) {
    this.bAsConentView = bAsConentView;
    return this;
  }


  //create
  //``````````````````````````````````````````````````````````````````````````````````````````````
  public FrameLayout create() {
    U.se(bAsConentView && mParent != null, "что-нибудь одно");
    //---
    FrameLayout ret = new FrameLayout(mContext);
    //---
    if (TArray_01.isFill(mChilds)) {
      for (View eView : mChilds) {
        ret.addView(eView);
      }
    }
    //---
    if (mParent != null) {
      mParent.addView(ret);
    }
    //---
    if (bAsConentView) {
      ((AppCompatActivity) mContext).setContentView(ret);
    }
    //---
    return ret;
  }

  public interface Back<T> {
    void fun(View view);
  }
}

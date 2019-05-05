package ru.surrsoft.baaz.widgets2;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * //needdesc
 * <p>
 * //new//
 */
public class BuImageView extends AbsBuView_01 {

  private final Context mContext;
  private ViewGroup layParent;

  public BuImageView(Context ctx) {
    mContext = ctx;
  }

  public BuImageView buAddTo(ViewGroup layParent) {
    this.layParent = layParent;
    return this;
  }

  public ImageView build() {
    ImageView iv = new ImageView(mContext);
    //---
    ViewGroup.MarginLayoutParams lp = lpConfigure(iv);
    iv.setLayoutParams(lp);
    //---
    if (layParent != null) {
      layParent.addView(iv);
    }
    //---
    return iv;
  }

}

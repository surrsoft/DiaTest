package ru.surrsoft.baaz.widgets2.nviews;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

import org.apache.commons.lang3.ArrayUtils;

import ru.surrsoft.baaz.cls_a.AndrAttrs_01;

/**
 * СТАРЫЕ НАЗВАНИЯ: [NCardView]
 */
public class NCardView_01 extends CardView {

  //constructors
  //``````````````````````````````````````````````````````````````````````````````````````````````
  public NCardView_01(Context context) {
    super(context);
    init(context);
  }

  public NCardView_01(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public NCardView_01(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  //``````````````````````````````````````````````````````````````````````````````````````````````

  private void init(Context context) {
    //this.setWillNotDraw(false);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    //--- техника [etaf]
    int[] drawableState = this.getDrawableState();
    if (ArrayUtils.contains(drawableState, AndrAttrs_01.state_pressed)) {
      this.invalidate();
    }

    //---
  }

  @Override
  protected void drawableStateChanged() {
    //--- техника [etaf]
    this.invalidate();

    //---
    super.drawableStateChanged();
  }
}

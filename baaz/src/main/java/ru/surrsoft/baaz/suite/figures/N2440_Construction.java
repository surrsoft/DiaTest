package ru.surrsoft.baaz.suite.figures;

import android.graphics.Color;
import android.graphics.Paint;

import ru.surrsoft.baaz.widgets2.utils.BuPaint;

/**
 * Сооружение
 */
public class N2440_Construction extends N1208_AbsDrawer {

  private final Paint mPaint_02;

  public N2440_Construction() {
    mPaint_02 = new BuPaint().fill().color(Color.WHITE).create();
  }

  @Override
  public void draw() {
    super.draw();
  }

  @Override
  public void _draw() {
    float c1 = 0.2f;
    float c2 = 0.2f;
    float c3 = 0.6f;
    float c4 = 0.2f;
    float r1 = mH_px * c1;
    float r2 = mH_px * c2;
    float r3 = mW_px * c3;
    float r4 = mW_px * c4;
    //---
    mCanvas.drawRect(l(0), t(0), r(mW_px), b(mH_px), mPaintFill);
    mCanvas.drawRect(l(0), t(r1), l(r3), t(r1 + r2), mPaint_02);
    mCanvas.drawRect(l(r3 + r4), t(r1), r(mW_px), t(r1 + r2), mPaint_02);
  }

  @Override
  public void animToEnd() {

  }

  @Override
  public void animStart() {

  }

  @Override
  public void animCancel() {

  }

  @Override
  public void animPause() {

  }

  @Override
  public void animResume() {

  }

  @Override
  public boolean animIsRunning() {
    return true;
  }

}

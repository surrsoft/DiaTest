package ru.surrsoft.baaz.demo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.FrameLayout;

import ru.surrsoft.baaz.Bysa_01;
import ru.surrsoft.baaz.R;
import ru.surrsoft.baaz.widgets2.BuFAB;
import ru.surrsoft.baaz.widgets2.buLay.BuLayFrame;

/**
 * //needdesc
 * <p>
 * //new//
 */
public class A_FAB_Demo extends AppCompatActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    //---
    FrameLayout layMain = new BuLayFrame(this)
      .buAsContentView(true)
      .create();

    //---
    //mt1(layMain);

    mt2(layMain);
  }

  private void mt2(FrameLayout layMain) {
    new BuFAB(this)
      .buAddTo(layMain)
      .build();
  }

  private void mt1(FrameLayout layMain) {
    FloatingActionButton fab = new FloatingActionButton(this);

    Drawable drw = Bysa_01.res.getDrawable(R.drawable.ic_plus, null);
    fab.setImageDrawable(drw);

    layMain.addView(fab);

    ((FrameLayout.LayoutParams) fab.getLayoutParams()).gravity = Gravity.RIGHT | Gravity.BOTTOM;

  }
}

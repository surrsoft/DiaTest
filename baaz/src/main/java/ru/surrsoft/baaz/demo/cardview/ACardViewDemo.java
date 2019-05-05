package ru.surrsoft.baaz.demo.cardview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.widget.Toast;

import ru.surrsoft.baaz.univers.EC;
import ru.surrsoft.baaz.univers.Gravity_01;
import ru.surrsoft.baaz.widgets.other.BuSeekBar_01;
import ru.surrsoft.baaz.widgets2.BuButton_01;
import ru.surrsoft.baaz.widgets2.BuilderTV;
import ru.surrsoft.baaz.widgets2.buLay.BuLayCardView_02;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;
import ru.surrsoft.baaz.widgets2.buLay.BuLayScroll_01;
import ru.surrsoft.baaz.widgets2.forwg.Margins_01;
import ru.surrsoft.baaz.widgets2.forwg.Paddings_01;
import ru.surrsoft.baaz.widgets2.nviews.NLinearLayout;
import ru.surrsoft.baaz.widgets2.nviews.NScrollView;
import ru.surrsoft.baaz.widgets2.utils.BuCSL;

/**
 * Демонстрация работы с CardView
 */
public class ACardViewDemo extends AppCompatActivity {
  private NLinearLayout layForCardView;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //---
    NScrollView layScroll = new BuLayScroll_01(this)
      .buAsContentView(true)
      .build();

    NLinearLayout layMain = new BuLayLinear_01(this)
      .addTo(layScroll)
      .build();

    //---
    NLinearLayout lay0 = new BuLayLinear_01(this)
      .addTo(layMain)
      .bgColor(Color.GREEN)
      .build();

    //---
    AppCompatSeekBar seekBarElevation = new BuSeekBar_01(this)
      .buAddTo(lay0)
      .buProgressStart(6)
      .buProgressMax(40)
      .buLabel(new BuilderTV(this)
        .text("evaluate: %d dp"))
      .build();

    AppCompatSeekBar seekBarRadius = new BuSeekBar_01(this)
      .buAddTo(lay0)
      .buProgressStart(6)
      .buProgressMax(40)
      .buLabel(new BuilderTV(this)
        .text("radius: %d dp"))
      .build();

    AppCompatSeekBar seekBarCardViewMargins = new BuSeekBar_01(this)
      .buAddTo(lay0)
      .buProgressStart(10)
      .buProgressMax(100)
      .buLabel(new BuilderTV(this)
        .text("card view margins: %d dp"))
      .build();

    AppCompatSeekBar seekBarCardViewPaddings = new BuSeekBar_01(this)
      .buAddTo(lay0)
      .buProgressStart(6)
      .buProgressMax(100)
      .buLabel(new BuilderTV(this)
        .text("card view paddings: %d dp"))
      .build();

    //---
    new BuButton_01(this)
      .buAddTo(lay0)
      .buText("apply")
      .buGravityOut(new Gravity_01(Gravity.END))
      .buListener(v -> {
        mtCardCreateAndAdd(
          seekBarElevation.getProgress(),
          seekBarRadius.getProgress(),
          seekBarCardViewMargins.getProgress(),
          seekBarCardViewPaddings.getProgress()
        );
      })
      .build();

    //--- lay для вставки CardView-s
    layForCardView = new BuLayLinear_01(this)
      .addTo(layMain)
      .bgColor(EC.MD_BLUE_200.val)
      .paddings(0, 0, 0, 10)
      .build();

    //---
    mtCardCreateAndAdd(
      seekBarElevation.getProgress(),
      seekBarRadius.getProgress(),
      seekBarCardViewMargins.getProgress(),
      seekBarCardViewPaddings.getProgress()
    );

  }

  private void mtCardCreateAndAdd(int iElevation, int iRadius, int iMargins_dp, int iPaddings_dp) {
    layForCardView.removeAllViews();
    mtAddCardView(iElevation, iRadius, iMargins_dp, iPaddings_dp);
    mtAddCardView(iElevation, iRadius, iMargins_dp, iPaddings_dp);
  }

  private void mtAddCardView(int iElevation, int iRadius, int iMargins_dp, int iPaddings_dp) {
    //--- card view
    CardView cv = new BuLayCardView_02(this)
      .buAddTo(layForCardView)
      .buPaddings(new Paddings_01(iPaddings_dp))
      .buElevation(iElevation)
      .buCornerRadius(iRadius)
      .buMargins(new Margins_01(iMargins_dp, iMargins_dp, iMargins_dp, 0))
      .buBgColor(new BuCSL()
        .pressed(EC.MD_AMBER_500)
        .normal(EC.MD_BLUE_GREY_100)
        .create()
      )
      .buOnclick(v -> Toast.makeText(this, "react on click", Toast.LENGTH_SHORT).show())
      .build();

    NLinearLayout layCard = new BuLayLinear_01(this)
      .addTo(cv)
      .bgColor(Color.CYAN)
      .build();

    //---
    new BuilderTV(this)
      .addTo(layCard)
      .text("1000\n2000\n300\n400")
      .bgColor(Color.YELLOW)
      .gravitySelf(Gravity.CENTER)
      .create();
  }
}

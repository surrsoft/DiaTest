package ru.surrsoft.baaz.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.temporal.ChronoField;

import java.util.TimeZone;

import ru.surrsoft.baaz.cls_c.G67G_Dates;
import ru.surrsoft.baaz.widgets2.BuilderTV_02;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;
import ru.surrsoft.baaz.widgets2.buLay.BuLayScroll_01;
import ru.surrsoft.baaz.widgets2.nviews.NLinearLayout;
import ru.surrsoft.baaz.widgets2.nviews.NScrollView;

/**
 * Демонстратор по части времени
 * <p>
 * //new//
 */
public class ATimeDemo extends AppCompatActivity {
  private NLinearLayout layMain;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    //---
    NScrollView layScroll = new BuLayScroll_01(this)
      .buAsContentView(true)
      .build();

    layMain = new BuLayLinear_01(this)
      .addTo(layScroll)
      .paddings(10)
      .build();

    //---
    long time = System.currentTimeMillis();
    //-
    mt("System.currentTimeMillis() ", time);
    mt("G67G_Dates.eavvToFormat(G67G_Dates.yataToEavv(time), \"dd.MM.yyyy kk:mm:ss\")", G67G_Dates.eavvToFormat(G67G_Dates.yataToEavv(time), "dd.MM.yyyy kk:mm:ss"));
    mt("System.nanoTime() ", System.nanoTime());
    mt("java.util.TimeZone.getDefault() ", TimeZone.getDefault());
    mt("G67G_Dates.eavvCurrentGet() ", G67G_Dates.eavvCurrentGet());
    mt("//--- ThreeTenABP", "");
    mt("Instant.now() ", Instant.now());
    mt("Instant.now().getEpochSecond() ", Instant.now().getEpochSecond());
    mt("Instant.now().getLong(ChronoField.INSTANT_SECONDS) ", Instant.now().getLong(ChronoField.INSTANT_SECONDS));
    mt("Instant.now().get(ChronoField.MILLI_OF_SECOND) ", Instant.now().getLong(ChronoField.MILLI_OF_SECOND));
    mt("Instant.now().getNano() ", Instant.now().getNano());
    mt("LocalDateTime.now() ", LocalDateTime.now());
    mt("LocalDateTime.ofEpochSecond(1556431817L, 0, ZoneOffset.UTC) ", LocalDateTime.ofEpochSecond(1556431817L, 0, ZoneOffset.UTC));
    mt("LocalDateTime.ofEpochSecond(-8000000000L, 0, ZoneOffset.UTC) ", LocalDateTime.ofEpochSecond(-8000000000L, 0, ZoneOffset.UTC));
    mt("LocalDateTime.ofEpochSecond(-365243219162L, 0, ZoneOffset.UTC) ", LocalDateTime.ofEpochSecond(-365243219162L, 0, ZoneOffset.UTC));
    mt("LocalDateTime.ofEpochSecond(365241780471L, 0, ZoneOffset.UTC) ", LocalDateTime.ofEpochSecond(365241780471L, 0, ZoneOffset.UTC));
    mt("Instant.ofEpochSecond(-31557014167219200L, 0L) ", Instant.ofEpochSecond(-31557014167219200L, 0L));
  }

  private void mt(String desc, Object value) {
    int confTextSize = 18;
    //---
    new BuilderTV_02(this)
      .addTo(layMain)
      .text(desc)
      .textColor(Color.BLUE)
      .textSize(confTextSize)
      .margins(0, 5, 0, 0)
      .build();
    //---
    new BuilderTV_02(this)
      .addTo(layMain)
      .text(value.toString())
      .textColor(Color.RED)
      .textSize(confTextSize)
      .build();
    //---
  }
}

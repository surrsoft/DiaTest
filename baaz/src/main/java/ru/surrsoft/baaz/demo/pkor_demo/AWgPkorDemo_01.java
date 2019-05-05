package ru.surrsoft.baaz.demo.pkor_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import java.util.ArrayList;

import ru.surrsoft.baaz.demo.pkor_demo.a.A_ViewHolder;
import ru.surrsoft.baaz.demo.pkor_demo.a.C_Utils;
import ru.surrsoft.baaz.widgets2.INanv;
import ru.surrsoft.baaz.widgets2.pkor.WgPkor;

/**
 * Демонстратор работы с {@link WgPkor}
 */
public class AWgPkorDemo_01 extends AppCompatActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    //---
    ArrayList<INanv> nanvs = C_Utils.sampleNanvsGenerate();

    //---
    FrameLayout layPkor = new WgPkor<A_ViewHolder>(this)
      .nanvs(nanvs)
      .dragAndDrop(WgPkor.EDragMode.HANDLE)
      .presenter(new WgPkor.Presenter_x_()) //чтобы работали сдвиги карточек
      .create();

    //---
    setContentView(layPkor);

  }


}

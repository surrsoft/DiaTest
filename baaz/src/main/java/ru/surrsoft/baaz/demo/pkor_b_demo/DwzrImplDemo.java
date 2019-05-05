package ru.surrsoft.baaz.demo.pkor_b_demo;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import ru.surrsoft.baaz.Bysa_01;
import ru.surrsoft.baaz.tclasses.TArray_01;
import ru.surrsoft.baaz.tclasses.TSharprefCommon_01;
import ru.surrsoft.baaz.widgets2.BuilderTV;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;
import ru.surrsoft.baaz.widgets2.nviews.NLinearLayout;
import ru.surrsoft.baaz.widgets2.pkor_b.a.Dwzr;

/**
 * Источник данных и виджетов для [pkor] (для RecyclerView), а так же манипулятор этими данными в
 * зависимости от сигналов из RecyclerView
 */
public class DwzrImplDemo implements Dwzr<VHolder, PkorDemoElem> {
  private static final String KEY_ELEMS = "key_wgPkor_B_demo_190323174500";
  private ArrayList<PkorDemoElem> elems;

  enum Mode {
    /**
     * Обычный режим
     */
    MODE_INITIAL,
    /**
     * В этом режиме содержимое RecyclerView сбрасывается к дефолтному
     */
    MODE_RESET
  }

  //--- constructors

  /**
   * В конструкторе генерируем/получаем-из-хранилища данные (список)
   */
  public DwzrImplDemo(Mode mode) {
    if (mode == Mode.MODE_RESET) {
      Bysa_01.sharprefCommon.edit().remove(KEY_ELEMS).apply();
    }
    //--- используем в качестве хранилища простой SharedPreferences
    elems = TSharprefCommon_01.listInit(KEY_ELEMS, new TypeToken<ArrayList<PkorDemoElem>>() {
    }, () -> {
      ArrayList<PkorDemoElem> list = new ArrayList<>();
      list.add(new PkorDemoElem().buText1("Иванов").buText2("Владимир Тест Тест Тест " +
        "Тест Тест Тест Тест Тест Тест Тест Тест Тест Тест" +
        " Тест"));
      list.add(new PkorDemoElem().buText1("Сидоров").buText2("Степан"));
      list.add(new PkorDemoElem().buText1("Большаков").buText2("Константин"));
      list.add(new PkorDemoElem().buText1("Афинагенов").buText2("Пётр"));
      return list;
    });
    //---
  }

  @Override
  public PkorDemoElem elemGetByIndex(int index) {
    return elems.get(index);
  }

  //--- здесь мы должны создавать виджет, связывать его с ViewHolder и возвращать этот ViewHolder
  @Override
  public VHolder viewHolderCreate(Context ctx) {
    NLinearLayout lay = new BuLayLinear_01(ctx)
      .paddings(5)
      .build();
    //---
    TextView tv1 = new BuilderTV(ctx)
      .addTo(lay)
      .textColor(Color.BLACK)
      .text("")
      .create();
    //---
    TextView tv2 = new BuilderTV(ctx)
      .addTo(lay)
      .text("")
      .create();
    //---
    VHolder ret = new VHolder(lay);
    ret.tv1 = tv1;
    ret.tv2 = tv2;
    //---
    return ret;
  }

  //--- здесь мы должны подставить данные в виджеты, ссылки на которые хранит ViewHolder
  @Override
  public void viewHolderBind(VHolder vh, int index) {
    vh.tv1.setText(elems.get(index).getSt1());
    vh.tv2.setText(elems.get(index).getSt2());
  }

  //---
  @Override
  public void remove(int index) {
    elems.remove(index);
    mtUpdateStore();
  }

  @Override
  public void add(AppCompatActivity activity) {
    elems.add(new PkorDemoElem());
    mtUpdateStore();
  }

  @Override
  public void swap(int iFirstIndex, int iSecondIndex) {
    TArray_01.swap(elems, iFirstIndex, iSecondIndex);
    mtUpdateStore();
  }

  @Override
  public long getCount() {
    return this.elems.size();
  }

  /**
   * Обновление данных в хранилище
   */
  private void mtUpdateStore() {
    TSharprefCommon_01.listPut(KEY_ELEMS, elems);
  }
}

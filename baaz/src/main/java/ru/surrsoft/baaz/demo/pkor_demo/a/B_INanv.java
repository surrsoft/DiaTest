package ru.surrsoft.baaz.demo.pkor_demo.a;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import ru.surrsoft.baaz.widgets2.BuilderTV;
import ru.surrsoft.baaz.widgets2.INanv;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;
import ru.surrsoft.baaz.widgets2.nviews.NLinearLayout;

/**
 * Реализация INanv
 */
public class B_INanv implements INanv<A_ViewHolder> {
  //--- "модельные" поля
  private String stFirstName = "";
  private String stLastName = "";

  //--- служебное поле чтобы хранить "чекнут" ли элемент
  private boolean isChecked = false;

  //---

  /**
   * Здесь, для RecyclerView, генерируем виджет и создаём ViewHolder. В конструктор ViewHodler
   * передаём ссылку на корень виджета и также пишем в его поля ссылки на
   * элементы виджета.
   */
  @Override
  public A_ViewHolder n1457_getViewHolder(Context ctx) {
    //---
    NLinearLayout layRoot = new BuLayLinear_01(ctx)
      .paddings(5)
      .build();
    //---
    TextView tvFirstName = new BuilderTV(ctx)
      .addTo(layRoot)
      .text("")
      .create();
    //---
    TextView tvLastName = new BuilderTV(ctx)
      .addTo(layRoot)
      .text("")
      .textColor(Color.BLACK)
      .create();
    //--- в конструктор "холдера" передаём ссылку на корневую разметку
    A_ViewHolder vh = new A_ViewHolder(layRoot);
    //--- в поля "холдера" пишем ссылки на элементы корневой разметки
    vh.tvFirstName = tvFirstName;
    vh.tvLastName = tvLastName;
    //---
    return vh;
  }

  @Override
  public void n1457_bindViewHolder(A_ViewHolder vh) {
    vh.tvFirstName.setText(stFirstName);
    vh.tvLastName.setText(stLastName);
  }

  @Override
  public boolean n1457_isChecked() {
    return isChecked;
  }

  @Override
  public void n1457_setChecked(boolean b) {
    isChecked = b;
  }

  public B_INanv buFirstName(String stFirstName) {
    this.stFirstName = stFirstName;
    return this;
  }

  public B_INanv buLastName(String stLastName) {
    this.stLastName = stLastName;
    return this;
  }

}

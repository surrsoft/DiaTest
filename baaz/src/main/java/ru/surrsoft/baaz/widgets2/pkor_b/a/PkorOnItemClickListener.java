package ru.surrsoft.baaz.widgets2.pkor_b.a;

import android.view.View;

/**
 * "Дёргалка" нажатий на элемент RecyclerView
 */
public interface PkorOnItemClickListener {
  public void onItemClick(View view, int position);

  public void onLongItemClick(View view, int position);

}

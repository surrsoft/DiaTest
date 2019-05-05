package ru.surrsoft.baaz.widgets2;

import android.content.Context;

/**
 * [[nanv]]
 * <p>
 * Используется в WgPkor для реализации классами генерирующими виджеты для RecyclerView
 * <p>
 * КОДЫ: [[w457w]]
 */
public interface INanv<T> {
  T n1457_getViewHolder(Context ctx);

  void n1457_bindViewHolder(T viewHolder);

  boolean n1457_isChecked();

  void n1457_setChecked(boolean b);

}

package ru.surrsoft.baaz.widgets2.pkor_b.a;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

/**
 * [[dwzr]]
 * <p>
 * Представляет набор данных. Предназначен для "скармливания" в WgPkor_B.java.
 * WgPkor_B.java:
 * -- передаёт обращается сюда чтобы получить ViewHolder
 * -- передаёт сюда ViewHolder для наполнения его (bind)
 * -- получает отсюда [avgb]-объект, по которому в том числе определяет длину набора данных
 * <p>
 * -- _nabor, _набор - набор данных
 * -- _listChecked - список чекнутых индексов
 * -- _elem, _элемент - элемент набора
 * -- _viewHolder -
 * -- _index, _индекс -
 *
 * @param <VH> -- тип холдера
 * @param <E> -- тип _элемента
 */
public interface Dwzr<VH extends RecyclerView.ViewHolder, E> {

  /**
   * Получение _элемента по _индексу (1)
   *
   * @param index (1) --
   * @return --
   */
  E elemGetByIndex(int index);

  /**
   * Реализующий должен создать и вернуть _viewHolder
   *
   * @param ctx (1) --
   * @return --
   */
  VH viewHolderCreate(Context ctx);

  /**
   * Реализующий должен наполнить _viewHolder (1) данными ассоциированными с индексом (2)
   *
   * @param wrapedHolder (1) --
   * @param index        (2) --
   */
  void viewHolderBind(VH wrapedHolder, int index);

  /**
   * Реализующий должен выполнить удаление _элемента ассоциированного с _индексом
   * (1)
   *
   * @param index --
   */
  void remove(int index);


  /**
   * Реализующий должен создать новый элемент, добавить его в [dwzr], передать этот обновлённый
   * [dwzr] в [pkor] одновременно вызвав его перерисовку с помощью WgPkor_B.refresh(Dwzr)
   */
  void add(AppCompatActivity acty);

  /**
   * Реализующий должен поменять местами элементы (1) и (2) в наборе
   *
   * @param iFirstIndex  (1) -- индекс элемента до его перемещения
   * @param iSecondIndex (2) -- индекс элемента после его перемещения
   */
  void swap(int iFirstIndex, int iSecondIndex);

  /**
   * Реализующий должен вернуть сколько всего элементов в _наборе ([dyap])
   *
   * @return --
   */
  long getCount();

  //
  //``````````````````````````````````````````````````````````````````````````````````````````````

}

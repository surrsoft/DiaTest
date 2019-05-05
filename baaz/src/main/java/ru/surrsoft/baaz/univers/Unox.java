package ru.surrsoft.baaz.univers;

import android.support.annotation.Nullable;


/**
 * [[unox]] - интерфейс для элемента(ов) имеющих в качестве родителя (^[nksc]) другой элемент.
 * Подразумевается
 * что оба элемента принадлежат одному множеству
 * <p>
 * СМ ТАКЖЕ:
 * _Etof - тип множества с элементами вида Unox;
 * Vcnv - утилитные методы для работы с _Etof
 * <p>
 * ВЕРСИЯ 2 1.1 2019-02-05 (stored)
 */
public interface Unox {
  /**
   * Получить ссылку на родителя (^[nksc]) текущего элемента
   *
   * @return --
   */
  @Nullable
  Unox unoxGetNksc();

}

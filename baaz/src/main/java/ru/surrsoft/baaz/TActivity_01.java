package ru.surrsoft.baaz;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

/**
 * Утилитные статические методы для работы с Activity
 * <p>
 * ВЕРСИЯ 1 1.0 2019-01-19 (stored)
 */
public class TActivity_01 {

  /**
   * Создает объект-инструкцию запускающую активити (2) из активити (1)
   *
   * @param actyFrom (1) -- активити из которой выполняется запуск
   * @param actyTo   (2) -- класс той активити которую нужно запускать
   * @return --
   */
  public static View.OnClickListener starter(final Activity actyFrom, final Class actyTo) {
    if (actyFrom == null) throw new RuntimeException();
    if (actyTo == null) throw new RuntimeException();
    //---
    return new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        actyFrom.startActivity(new Intent(actyFrom, actyTo));
      }
    };
  }

  /**
   * Создает объект-инструкцию запускающую активити (2) из активити (1) и применяет её в качестве
   * слушателя для виджета (3)
   *
   * @param actyFrom (1) -- активити из которой выполняется запуск
   * @param actyTo   (2) -- класс той активити которую нужно запуска
   * @param viewTo   (3) -- виджет которому нужно "повесить" действие запуска активити (2) на клик
   */
  public static void starterCreateAndSet(Activity actyFrom, Class actyTo, View viewTo) {
    viewTo.setOnClickListener(TActivity_01.starter(actyFrom, actyTo));
  }

  /**
   * Запускает активити (2)
   *
   * @param actyFrom (1) -- ссылка на активити из которого выполняется запуск
   * @param actyTo   (2) -- класс активити которое нужно запустить
   */
  public static void start(Activity actyFrom, Class actyTo) {
    actyFrom.startActivity(new Intent(actyFrom, actyTo));
  }

}

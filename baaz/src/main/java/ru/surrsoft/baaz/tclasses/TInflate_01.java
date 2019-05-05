package ru.surrsoft.baaz.tclasses;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import ru.surrsoft.baaz.Bysa_01;
import ru.surrsoft.baaz.univers.U;

/**
 * Утилитные методы для работы с инфлейтом
 */
public class TInflate_01 {

  /**
   * Инфлейтит разметку (1) в разметку (2).
   * Аналогично вызову (2).addView(x), где x - это как бы инстанцированная разметка (1)
   * <p>
   * #errors если (1) == 0; если (2) == null
   *
   * @param iResIdLayout (1) --
   * @param layRoot      (2) --
   */
  public static void inflate(@LayoutRes int iResIdLayout, ViewGroup layRoot) {
    U.se(iResIdLayout == 0, "");
    U.se(layRoot == null, "");
    //---
    LayoutInflater.from(Bysa_01.appContext).inflate(iResIdLayout, layRoot, true);
  }

  /**
   * Инфлейтит разметку (1) и возвращает её.
   * При этом LayoutParams корня разметки (1) после инфлейта будет == null (вне зависимости что
   * было указано в XML)
   * <p>
   * #errors если (1) == 0
   *
   * @param iResIdLayout (1) --
   * @return --
   */
  public static ViewGroup inflate_B(@LayoutRes int iResIdLayout) {
    U.se(iResIdLayout == 0, "");
    //---
    return (ViewGroup) LayoutInflater.from(Bysa_01.appContext).inflate(iResIdLayout, null, false);
  }

  /**
   * Инфлейтит разметку (1) и возвращает её.
   * Отличается от В тем что берёт ОБЪЕКТ LayoutParams из (2) и уставливает его для (1). Берётся только
   * ОБЪЕКТ, само содержимое копируется из исходного LayoutParams разметки (1)
   * <p>
   * #errors если (1) == 0 или (2) == null
   *
   * @param iResIdLayout (1) --
   * @param layRoot      (2) --
   * @return --
   */
  public static ViewGroup inflate_C(@LayoutRes int iResIdLayout, ViewGroup layRoot) {
    U.se(iResIdLayout == 0, "");
    U.se(layRoot == null, "");
    //---
    return (ViewGroup) LayoutInflater.from(Bysa_01.appContext).inflate(iResIdLayout, layRoot, false);
  }

}

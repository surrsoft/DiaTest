package ru.surrsoft.baaz.univers;

/**
 * Специальный презентер используемый для подтверждения какого-либо действия. Например,
 * при нажатии на кнопку-бинарный-переключатель, сначала вызывается текущий
 * презентер, и если он возвращает TRUE, то кнопка переводится в следующее состояние, если же
 * FALSE, то кнопка в другое состояние не переводится
 * <p>
 * КОДЫ: [[jmox]]; [eaib]; $[msfe]-(презентер)
 */
public interface Jmox {
  /**
   * @param oj (1) -- опциональный элемент
   * @return --
   */
  boolean fun(Object oj);
}
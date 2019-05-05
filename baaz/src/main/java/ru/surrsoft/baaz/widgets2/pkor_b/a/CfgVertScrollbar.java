package ru.surrsoft.baaz.widgets2.pkor_b.a;

/**
 * Настройки вертикального ScrollBar для WgPkor_B
 * <p>
 * [sbso]-вид: _класс-хранитель ([toio])
 */
public class CfgVertScrollbar {
  private boolean mVertScrollbarDisable;
  private boolean mStyleInsideOverlay;

  public CfgVertScrollbar() {
  }

  public CfgVertScrollbar buDisable() {
    mVertScrollbarDisable = true;
    return this;
  }

  public CfgVertScrollbar buStyleInsideOverlay() {
    mStyleInsideOverlay = true;
    return this;
  }

  public boolean isDisable() {
    return mVertScrollbarDisable;
  }

  public boolean isStyleInsideOverlay() {
    return mStyleInsideOverlay;
  }
}

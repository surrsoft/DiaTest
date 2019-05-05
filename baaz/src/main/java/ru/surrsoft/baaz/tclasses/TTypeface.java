package ru.surrsoft.baaz.tclasses;

import android.graphics.Typeface;

import ru.surrsoft.baaz.Bysa_01;
import ru.surrsoft.baaz.univers.U;

/**
 * Утилитные методы для работы со шрифтами, Typeface
 * <p>
 * ПОНЯТИЯ:
 * -- _pathfile, _путьфайл - путьфайл штрифта, например "fonts/Roboto/Roboto_Regular.ttf"
 */
public class TTypeface {

  /**
   * Получение Typeface по его _путьфайлу (1)
   * <p>
   * #errors исключение если шрифта (2) не существует
   *
   * @param _stPathfile (1) --
   * @return --
   */
  public static Typeface get(String _stPathfile) {
    U.se(TString_01.isEmpty(_stPathfile), "");
    return Typeface.createFromAsset(Bysa_01.assets, _stPathfile);
  }

}

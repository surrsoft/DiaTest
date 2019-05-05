package ru.surrsoft.baaz.suite.lvl0;

import android.text.InputType;

/**
 * Константы типа ввода для EditText
 * <p/>
 * ID: [[w196w]]
 * <p>
 * СТАРЫЕ НАЗВАНИЯ: "N2196_EInputTypes"
 */
public enum EEditTextInputTypes {
  /**
   * Текст однострочный
   */
  TEXT_ONELINE(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS),
  /**
   * Текст многострочный
   */
  TEXT_MULTILINE(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS),
  /**
   * Число целое положительное
   */
  INTEGER_POSITIVE(InputType.TYPE_CLASS_NUMBER),
  /**
   * Число целое знаковое
   */
  INTEGER_SIGNED(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED),
  /**
   * Число дробное положительное
   */
  FRACT_POSITIVE(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL),
  /**
   * Число дробное знаковое
   */
  FRACT_SIGNED(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);

  public final int inputType;

  EEditTextInputTypes(int inputType) {
    this.inputType = inputType;
  }
}

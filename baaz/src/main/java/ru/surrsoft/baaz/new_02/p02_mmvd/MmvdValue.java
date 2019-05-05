package ru.surrsoft.baaz.new_02.p02_mmvd;

import ru.surrsoft.baaz.SomeException;
import ru.surrsoft.baaz.new_02.p02_mmvd.b.CorrectionResult;
import ru.surrsoft.baaz.new_02.p02_mmvd.b.ValidateResult;
import ru.surrsoft.baaz.univers.U;

/**
 * [[mmvd]] - инкарнация некого значения выраженного строкой. Может представлять обычный текст,
 * числа, даты и т.д.
 * <p>
 * ПОНЯТИЯ:
 * -- [[npbd]], _коррекция, _correct - операция корректирования текста. Часто, мы хотим подкорректировать ввод
 * пользователя перед _валидацией, например убрать пробелы в начале и конце и т.п.
 * -- _валидация, _valid - валидирование ввода пользователя. Результатом валидации является
 * объект {@link ValidateResult}. Валидация выполняется исходя из того что на вход может прийти как
 * строка прошедшая _коррекцию, так и не прощедшая её
 * -- _значение, _text - хранится в поле {@link #text}. По умолчанию = "", не может быть == NULL
 * -- _предзначение, _pretext - текст который гипотетически может стать _значением если пройдёт _валидацию
 * <p>
 * <p>
 * КОДЫ: [ksao]
 * <p>
 * //new//new//
 */
public abstract class MmvdValue {

  private String text = "";

  public MmvdValue() {
  }

  /**
   * ВНИМАНИЕ: Будет брошено непроверяемое исключение если _предтекст (1) не пройдёт _валидацию или
   * будет ==
   * null
   *
   * @param pretext (1) --
   */
  public MmvdValue(String pretext) {
    ValidateResult vr = textSetIf(pretext);
    if (!vr.isValid) {
      throw new SomeException("(debug)");
    }
  }

  /**
   * Реализующий должен сделать _коррекцию _предзначения (1). Результат пишется в поле text
   * выходного объекта.
   * Если в ходе коррекции, _предзначение (1) подверглось любому изменению, то в поле isCorrected
   * выходного обхекта должно быть записано значение TRUE
   *
   * @param st (1) --
   * @return --
   */
  public abstract CorrectionResult correct(String st);

  /**
   * Реализующий должен сделать _валидацию (1) и если она пройдена успешна, обновить поле {@link
   * #text}.
   * Реализующий не должен допускать запись NULL в поле {@link #text}
   *
   * @param pretext (1) -- _предзначение
   * @return --
   */
  public abstract ValidateResult validate(String pretext);

  /**
   * Выполняет _валидацию _предзначения (1). Если она успешна, то обновляет _предначением (1) поле
   * {@link #text}
   *
   * @param text (1) -- _предзначение
   * @return --
   */
  public ValidateResult textSetIf(String text) {
    U.se(text == null, "");
    //---
    ValidateResult vr = validate(text);
    //---
    if (!vr.isValid) {
      return vr;
    }
    //---
    this.text = text;
    return vr;
  }

  /**
   * Получение текущего _значения
   *
   * @return --
   */
  public String textGet() {
    //защита на случай если по какой-либо причине реализующий записал null в поле text
    U.se(text == null, "");
    //---
    return text;
  }

  /**
   * Если (1) TRUE то возвращет TRUE если _значение пустое.
   * Если (1) FALSE то возвращае TRUE если _значение НЕ пустое.
   *
   * @param b (1) --
   * @return --
   */
  public boolean isEmpty(boolean b) {
    if (b) {
      return textGet().length() == 0;
    }
    return textGet().length() > 0;
  }
}

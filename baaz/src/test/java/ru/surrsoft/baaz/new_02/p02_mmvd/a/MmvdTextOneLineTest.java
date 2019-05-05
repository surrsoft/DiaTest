package ru.surrsoft.baaz.new_02.p02_mmvd.a;

import org.junit.Test;

import ru.surrsoft.baaz.new_02.p02_mmvd.b.CorrectionResult;
import ru.surrsoft.baaz.new_02.p02_mmvd.b.ValidateResult;
import ru.surrsoft.baaz.univers.EStrings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * //needdesc
 * <p>
 * //new//
 */
public class MmvdTextOneLineTest {

  @Test
  public void init() {
    MmvdTextOneLine tol = new MmvdTextOneLine();
    assertEquals("", tol.textGet());

    tol = new MmvdTextOneLine("-");
    assertEquals("-", tol.textGet());

  }

  @Test
  public void correct() {
    MmvdTextOneLine tol = new MmvdTextOneLine();

    CorrectionResult c;

    c = tol.correct("");
    assertEquals("", c.getText());
    assertFalse(c.isCorrected);

    c = tol.correct("1");
    assertEquals("1", c.getText());
    assertFalse(c.isCorrected);

    //--- удаление пробелов в начале/конце и замена повторений пробелов одним пробелом
    c = tol.correct(" 1  1 ");
    assertEquals("1 1", c.getText());
    assertTrue(c.isCorrected);

    //--- удаление всех переносов строк
    c = tol.correct(" \n 1 \n 1 ");
    assertEquals("1 1", c.getText());
    assertTrue(c.isCorrected);

  }

  @Test
  public void validate() {
    MmvdTextOneLine tol = new MmvdTextOneLine();

    ValidateResult vr;

    vr = tol.validate("");
    assertTrue(vr.isValid);
    assertEquals("", vr.stComment);

    vr = tol.validate("1");
    assertTrue(vr.isValid);
    assertEquals("", vr.stComment);

    //--- переносы строк недопустимы
    vr = tol.validate("1" + ((char) 10) + "1");
    assertFalse(vr.isValid);
    assertEquals(EStrings._TEXT_NEED_NO_LINE_BREAKS.val(), vr.stComment);

    vr = tol.validate("1" + ((char) 12) + "1");
    assertFalse(vr.isValid);
    assertEquals(EStrings._TEXT_NEED_NO_LINE_BREAKS.val(), vr.stComment);


  }

}
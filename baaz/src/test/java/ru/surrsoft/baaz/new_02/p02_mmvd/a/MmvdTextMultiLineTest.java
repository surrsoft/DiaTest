package ru.surrsoft.baaz.new_02.p02_mmvd.a;

import org.junit.Test;

import ru.surrsoft.baaz.new_02.p02_mmvd.b.CorrectionResult;
import ru.surrsoft.baaz.new_02.p02_mmvd.b.ValidateResult;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * //needdesc
 * <p>
 * //new//
 */
public class MmvdTextMultiLineTest {

  @Test
  public void init() {
    MmvdTextMultiLine tml = new MmvdTextMultiLine();
    assertEquals("", tml.textGet());
  }


  @Test
  public void correct() {
    MmvdTextMultiLine tml = new MmvdTextMultiLine();

    CorrectionResult cr;

    cr = tml.correct("");
    assertFalse(cr.isCorrected);
    assertEquals("", cr.getText());

    cr = tml.correct("1");
    assertFalse(cr.isCorrected);
    assertEquals("1", cr.getText());

    cr = tml.correct(" \n1 \n 1\n ");
    assertTrue(cr.isCorrected);
    assertEquals("1 \n 1", cr.getText());


  }

  @Test
  public void validate() {
    MmvdTextMultiLine tml = new MmvdTextMultiLine();

    ValidateResult vr;

    vr = tml.validate("");
    assertTrue(vr.isValid);
    assertEquals("", vr.stComment);


    vr = tml.validate("1");
    assertTrue(vr.isValid);
    assertEquals("", vr.stComment);


    vr = tml.validate("1\n1");
    assertTrue(vr.isValid);
    assertEquals("", vr.stComment);

  }
}
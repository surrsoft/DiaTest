package ru.surrsoft.baaz.new_02.p02_mmvd.a;

import org.junit.Test;

import ru.surrsoft.baaz.new_02.p02_mmvd.b.CorrectionResult;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * //needdesc
 * <p>
 * //new//
 */
public class MmvdNumberIntegerNilPlusTest {

  /**
   * Проверка начального значения
   */
  @Test
  public void init() {
    MmvdNumberIntegerNilPlus correctorInteger = new MmvdNumberIntegerNilPlus();

    assertEquals("0", correctorInteger.textGet());
  }

  @Test
  public void correct() {
    CorrectionResult res;

    //---
    MmvdNumberIntegerNilPlus correctorInteger = new MmvdNumberIntegerNilPlus();

    //---
    res = correctorInteger.correct("");
    assertEquals(res.getText(), "");
    assertFalse(res.isCorrected);

    res = correctorInteger.correct(null);
    assertEquals(res.getText(), "");
    assertTrue(res.isCorrected);

    res = correctorInteger.correct("1");
    assertEquals(res.getText(), "1");
    assertFalse(res.isCorrected);

    res = correctorInteger.correct("0");
    assertEquals(res.getText(), "0");
    assertFalse(res.isCorrected);

    res = correctorInteger.correct("000");
    assertEquals(res.getText(), "0");
    assertTrue(res.isCorrected);

    res = correctorInteger.correct("000000000000000000000000000000000000000000000");
    assertEquals(res.getText(), "0");
    assertTrue(res.isCorrected);


    res = correctorInteger.correct("999999999999999999999999999999999999999999");
    assertEquals(res.getText(), "999999999999999999999999999999999999999999");
    assertFalse(res.isCorrected);


    res = correctorInteger.correct("000012");
    assertEquals(res.getText(), "12");
    assertTrue(res.isCorrected);


    res = correctorInteger.correct("  00001200  ");
    assertEquals(res.getText(), "1200");
    assertTrue(res.isCorrected);


    res = correctorInteger.correct("  000  012  ");
    assertEquals(res.getText(), "000 012");
    assertTrue(res.isCorrected);


    res = correctorInteger.correct(" \n 000  012  ");
    assertEquals(res.getText(), "000 012");
    assertTrue(res.isCorrected);


    res = correctorInteger.correct("-1");
    assertEquals(res.getText(), "-1");
    assertFalse(res.isCorrected);


    res = correctorInteger.correct("+1");
    assertEquals(res.getText(), "+1");
    assertFalse(res.isCorrected);


    res = correctorInteger.correct("1.2");
    assertEquals(res.getText(), "1.2");
    assertFalse(res.isCorrected);

    res = correctorInteger.correct("1,2");
    assertEquals(res.getText(), "1,2");
    assertFalse(res.isCorrected);


    res = correctorInteger.correct("001.2");
    assertEquals(res.getText(), "001.2");
    assertFalse(res.isCorrected);


  }

}
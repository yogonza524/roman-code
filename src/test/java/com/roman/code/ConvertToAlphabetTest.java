package com.roman.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.roman.code.domain.Alphabet;
import javax.naming.OperationNotSupportedException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public class ConvertToAlphabetTest {
  private Alphabet roman = Alphabet.roman();
  private Alphabet facundo =
      Alphabet.builder()
          .One("F")
          .Five("A")
          .Ten("C")
          .Fifty("U")
          .OneHundred("N")
          .FiveHundred("D")
          .Thousand("O")
          .build();

  @Test
  public void shouldFailIfNumberIsNull() {
    assertNotNull(
        assertThrows(
            NullPointerException.class, () -> ConvertToAlphabet.fromArabic(null, roman), ""));
  }

  @Test
  public void shouldFailIfAlphabetIsNull() {
    assertNotNull(
        assertThrows(
            NullPointerException.class, () -> ConvertToAlphabet.fromArabic(1200, null), ""));
  }

  @Test
  public void shouldFailIfNumberIsNegative() {
    assertNotNull(
        assertThrows(
            OperationNotSupportedException.class,
            () -> ConvertToAlphabet.fromArabic(-19, roman),
            ""));
  }

  @Test
  public void shouldFailIfNumberIsZero() {
    assertNotNull(
        assertThrows(
            OperationNotSupportedException.class,
            () -> ConvertToAlphabet.fromArabic(0, roman),
            ""));
  }

  @Test
  public void shouldPassWhenRomanConversionIsOK() throws OperationNotSupportedException {
    assertEquals("MCC", ConvertToAlphabet.fromArabic(1200, roman));
    assertEquals("MCMXIV", ConvertToAlphabet.fromArabic(1914, roman));
    assertEquals("XIV", ConvertToAlphabet.fromArabic(14, roman));
    assertEquals("X", ConvertToAlphabet.fromArabic(10, roman));
    assertEquals("IV", ConvertToAlphabet.fromArabic(4, roman));
    assertEquals("DLIV", ConvertToAlphabet.fromArabic(554, roman));
    assertEquals("CCCIII", ConvertToAlphabet.fromArabic(303, roman));
    assertEquals("CMIX", ConvertToAlphabet.fromArabic(909, roman));
    assertEquals("MMM", ConvertToAlphabet.fromArabic(3000, roman));
    assertEquals("MMMCMXCIX", ConvertToAlphabet.fromArabic(3999, roman));
  }

  @Test
  public void shouldPassWhenFacundoConversionIsOK() throws OperationNotSupportedException {
    assertEquals("ONN", ConvertToAlphabet.fromArabic(1200, facundo));
    assertEquals("ONOCFA", ConvertToAlphabet.fromArabic(1914, facundo));
    assertEquals("CFA", ConvertToAlphabet.fromArabic(14, facundo));
    assertEquals("C", ConvertToAlphabet.fromArabic(10, facundo));
    assertEquals("FA", ConvertToAlphabet.fromArabic(4, facundo));
    assertEquals("DUFA", ConvertToAlphabet.fromArabic(554, facundo));
    assertEquals("NNNFFF", ConvertToAlphabet.fromArabic(303, facundo));
    assertEquals("NOFC", ConvertToAlphabet.fromArabic(909, facundo));
    assertEquals("OOO", ConvertToAlphabet.fromArabic(3000, facundo));
    assertEquals("OOONOCNFC", ConvertToAlphabet.fromArabic(3999, facundo));
  }

  @Test
  public void shouldPassWhenAllNumbersAreCorrect() throws OperationNotSupportedException {
    for (int i = 1; i < 4000; i++) {
      // Check all roman numbers
      String rNumber = ConvertToAlphabet.fromArabic(i, roman);
      Integer aNumber = ConvertToArabic.fromRoman(rNumber);
      assertEquals(i, aNumber);

      // Check all custom alphabet numbers
      rNumber = ConvertToAlphabet.fromArabic(i, facundo);
      aNumber = ConvertToArabic.fromAlphabet(rNumber, facundo);
      assertEquals(i, aNumber);
    }
  }

  @Test
  public void shouldPassWhenConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, InvocationTargetException {
    Constructor<ConvertToArabic> constructor = ConvertToArabic.class.getDeclaredConstructor();
    assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    constructor.setAccessible(true);
    constructor.newInstance();
  }
}

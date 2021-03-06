package com.roman.code;

import static org.junit.jupiter.api.Assertions.*;

import com.roman.code.domain.Alphabet;
import com.roman.code.exception.ConversionException;
import com.roman.code.exception.IncompleteAlphabetException;
import org.junit.jupiter.api.Test;

class ConvertToArabicTest {

  @Test
  void shouldPassWhenRomanToArabicValidationFails() {

    assertTrue(
        assertThrows(ConversionException.class, () -> ConvertToArabic.fromRoman("VX"), "")
            .getMessage()
            .contains("Sintaxis violation"));

    assertTrue(
        assertThrows(ConversionException.class, () -> ConvertToArabic.fromRoman("XVU"), "")
            .getMessage()
            .contains("Alphabet violation"));

    assertTrue(
        assertThrows(ConversionException.class, () -> ConvertToArabic.fromRoman("XIIII"), "")
            .getMessage()
            .contains("Concecutives ocurrence violation"));

    assertTrue(
        assertThrows(ConversionException.class, () -> ConvertToArabic.fromRoman("IIX"), "")
            .getMessage()
            .contains("Sustraction violation"));
  }

  @Test
  public void shouldConvertRomanNumbers() {
    assertEquals(1914, ConvertToArabic.fromRoman("MCMXIV"));
    assertEquals(14, ConvertToArabic.fromRoman("XIV"));
    assertEquals(10, ConvertToArabic.fromRoman("X"));
    assertEquals(4, ConvertToArabic.fromRoman("IV"));
    assertEquals(554, ConvertToArabic.fromRoman("DLIV"));
    assertEquals(303, ConvertToArabic.fromRoman("CCCIII"));
    assertEquals(909, ConvertToArabic.fromRoman("CMIX"));
    assertEquals(3000, ConvertToArabic.fromRoman("MMM"));
  }

  @Test
  public void shouldConvertAllNumbersOfAlphabet() {
    Alphabet alphabet =
        Alphabet.builder()
            .One("F")
            .Five("A")
            .Ten("C")
            .Fifty("U")
            .OneHundred("N")
            .FiveHundred("D")
            .Thousand("O")
            .build();

    assertEquals(1914, ConvertToArabic.fromAlphabet("ONOCFA", alphabet));
    assertEquals(14, ConvertToArabic.fromAlphabet("CFA", alphabet));
    assertEquals(10, ConvertToArabic.fromAlphabet("C", alphabet));
    assertEquals(4, ConvertToArabic.fromAlphabet("FA", alphabet));
    assertEquals(554, ConvertToArabic.fromAlphabet("DUFA", alphabet));
    assertEquals(303, ConvertToArabic.fromAlphabet("NNNFFF", alphabet));
    assertEquals(909, ConvertToArabic.fromAlphabet("NOFC", alphabet));
    assertEquals(3000, ConvertToArabic.fromAlphabet("OOO", alphabet));
  }

  @Test
  public void shouldFailIfAlphabetIsNotCompletelyDefined() {
    Alphabet.Builder alphabet =
        Alphabet.builder().One("G").Five("O").Ten("N").Fifty("Z").OneHundred("A");
    assertTrue(
        assertThrows(IncompleteAlphabetException.class, () -> alphabet.build(), "")
            .getMessage()
            .contains("Alphabet must have 7 defined pair key-values"));
  }

  @Test
  public void shouldFailIfAlphabetWantsToBeModified() {
    Alphabet alphabet =
        Alphabet.builder()
            .One("G")
            .Five("O")
            .Ten("N")
            .Fifty("Z")
            .OneHundred("A")
            .FiveHundred("L")
            .Thousand("0")
            .build();

    assertNotNull(
        assertThrows(
            UnsupportedOperationException.class, () -> alphabet.getAlphabet().put("G", 19), ""));
  }
}

package com.roman.code;

import static org.junit.jupiter.api.Assertions.*;

import com.roman.code.exception.ConversionException;
import java.util.HashMap;
import java.util.Map;
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
    Map<Character, Integer> alphabet = new HashMap<>();

    alphabet.put('F', 1);
    alphabet.put('A', 5);
    alphabet.put('C', 10);
    alphabet.put('U', 50);
    alphabet.put('N', 100);
    alphabet.put('D', 500);
    alphabet.put('O', 1000);

    assertEquals(1914, ConvertToArabic.fromAlphabet("ONOCFA", alphabet));
    assertEquals(14, ConvertToArabic.fromAlphabet("CFA", alphabet));
    assertEquals(10, ConvertToArabic.fromAlphabet("C", alphabet));
    assertEquals(4, ConvertToArabic.fromAlphabet("FA", alphabet));
    assertEquals(554, ConvertToArabic.fromAlphabet("DUFA", alphabet));
    assertEquals(303, ConvertToArabic.fromAlphabet("NNNFFF", alphabet));
    assertEquals(909, ConvertToArabic.fromAlphabet("NOFC", alphabet));
    assertEquals(3000, ConvertToArabic.fromAlphabet("OOO", alphabet));
  }
}

package com.roman.code;

import static org.junit.jupiter.api.Assertions.*;

import javax.naming.OperationNotSupportedException;
import org.junit.jupiter.api.Test;

class ConvertToRomanTest {
  @Test
  public void shouldPassWhenConversionIsOK() throws OperationNotSupportedException {
    assertEquals("MCMXIV", ConvertToRoman.fromArabic(1914));
  }
}

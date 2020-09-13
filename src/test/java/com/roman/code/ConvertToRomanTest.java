package com.roman.code;

import static org.junit.jupiter.api.Assertions.*;

import javax.naming.OperationNotSupportedException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

class ConvertToRomanTest {
  @Test
  public void shouldPassWhenConversionIsOK() throws OperationNotSupportedException {
    assertEquals("MCMXIV", ConvertToRoman.fromArabic(1914));
  }

  @Test
  public void shouldPassWhenConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, InvocationTargetException {
    Constructor<ConvertToRoman> constructor = ConvertToRoman.class.getDeclaredConstructor();
    assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    constructor.setAccessible(true);
    constructor.newInstance();
  }
}

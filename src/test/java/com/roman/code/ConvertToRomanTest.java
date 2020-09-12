package com.roman.code;

import org.junit.jupiter.api.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.jupiter.api.Assertions.*;

class ConvertToRomanTest {
    @Test
    public void shouldPassWhenConversionIsOK() throws OperationNotSupportedException {
        assertEquals("MCMXIV", ConvertToRoman.fromArabic(1914));
    }
}
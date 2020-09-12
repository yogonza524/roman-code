package com.roman.code;

import com.roman.code.domain.Alphabet;

import javax.naming.OperationNotSupportedException;

public class ConvertToRoman {
    public static String fromArabic(Integer number) throws OperationNotSupportedException {
        return ConvertToAlphabet.fromArabic(number, Alphabet.roman());
    }
}

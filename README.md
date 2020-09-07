# Roman code
Translate any roman number to arabic or create your own alphabet to translate that symbols to arabic 

## Features
- Validators
- Translator roman-2-arabic
- Translator custom-2-arabic

## Sample
```java
// Validation test
assertTrue(
    assertThrows(ConversionException.class, () -> ConvertToArabic.fromRoman("VX"), "")
        .getMessage()
        .contains("Sintaxis violation"));

// Conversion test
assertEquals(1914, ConvertToArabic.fromRoman("MCMXIV"));
```

## Try yourself
Check [Test class for more examples](./src/test/java/com/roman/code/ConvertToArabicTest.java)
 
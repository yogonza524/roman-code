![Test all](https://github.com/yogonza524/roman-code/workflows/test-all/badge.svg)
# Roman code
Translate any roman number to arabic or create your own alphabet to translate that symbols to arabic 

## Features
- Translator roman-2-arabic
- Translator alphabet-2-arabic
- Translator arabic-2-roman (New) :heavy_check_mark:
- Translator arabic-2-alphabet (New) :heavy_check_mark:

## Stack
- Openjdk 11.0.8 2020-07-14
- Gradle 6.5

## Samples
- Validation test for roman translation
```java
assertTrue(
    assertThrows(ConversionException.class, () -> ConvertToArabic.fromRoman("VX"), "")
        .getMessage()
        .contains("Sintaxis violation"));
```

- Convert from **roman** to **arabic**
```java
assertEquals(1914, ConvertToArabic.fromRoman("MCMXIV"));
```

- Convert from **arabic** to **roman**
```java
assertEquals("MCMXIV", ConvertToRoman.fromArabic(1914));
```

- Create your own alphabet and convert to arabic
```java
Alphabet alphabet =
    Alphabet.builder()
        .One('F')
        .Five('A')
        .Ten('C')
        .Fifty('U')
        .OneHundred('N')
        .FiveHundred('D')
        .Thousand('O')
        .build();

assertEquals(1914, ConvertToArabic.fromAlphabet("ONOCFA", alphabet));
```

- If your alphabet is incomplete then you will receive an IncompleteAlphabetException
```java
Alphabet.Builder alphabet =
    Alphabet.builder().One('G').Five('O').Ten('N').Fifty('Z').OneHundred('A');

assertTrue(
    assertThrows(
        IncompleteAlphabetException.class, () -> alphabet.build(), "")
            .getMessage()
            .contains("Alphabet must have 7 defined pair key-values")
    );
```

- Remember: the alphabet created is **immutable**
```java
assertNotNull(
    assertThrows(
        UnsupportedOperationException.class, () -> alphabet.getAlphabet().put('G', 19), ""));
```

## Test all
```java
./gradlew test
```

## Try yourself
Check [Test class for more examples](./src/test/java/com/roman/code/ConvertToArabicTest.java)
 
## Issues
Please contact to me to [my email](mailto:yogonza524@gmail.com)
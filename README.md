# Roman code
Translate any roman number to arabic or create your own alphabet to translate that symbols to arabic 

## Features
- Translator roman-2-arabic
- Translator custom-2-arabic

## Sample
- Validation test for romanic translation
```java
assertTrue(
    assertThrows(ConversionException.class, () -> ConvertToArabic.fromRoman("VX"), "")
        .getMessage()
        .contains("Sintaxis violation"));
```

- Convert from roman to arabic test
```java
assertEquals(1914, ConvertToArabic.fromRoman("MCMXIV"));
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
Alphabet.AlphabetBuilder alphabet =
    Alphabet.builder().One('G').Five('O').Ten('N').Fifty('Z').OneHundred('A');

assertTrue(
    assertThrows(
        IncompleteAlphabetException.class, () -> alphabet.build(), "")
            .getMessage()
            .contains("Alphabet must have 7 defined pair key-values")
    );
```

## Test all
```java
./gradlew test
```

## Try yourself
Check [Test class for more examples](./src/test/java/com/roman/code/ConvertToArabicTest.java)
 
## Issues
please contact to me to [my email](mailto:yogonza524@gmail.com)
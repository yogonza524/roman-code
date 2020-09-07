package com.roman.code.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Alphabet {
  private Map<Character, Integer> alphabet;

  private Alphabet(Map<Character, Integer> alphabet) {
    this.alphabet = alphabet;
  }

  public Map<Character, Integer> getAlphabet() {
    return Collections.unmodifiableMap(alphabet);
  }

  public static AlphabetBuilder builder() {
    return new AlphabetBuilder();
  }

  public static final class AlphabetBuilder {
    private Map<Character, Integer> alphabet;

    private AlphabetBuilder() {
      this.alphabet = new HashMap<>();
    }

    public AlphabetBuilder One(Character key) {
      this.alphabet.put(key, 1);
      return this;
    }

    public AlphabetBuilder Five(Character key) {
      this.alphabet.put(key, 5);
      return this;
    }

    public AlphabetBuilder Ten(Character key) {
      this.alphabet.put(key, 10);
      return this;
    }

    public AlphabetBuilder Fifty(Character key) {
      this.alphabet.put(key, 50);
      return this;
    }

    public AlphabetBuilder OneHundred(Character key) {
      this.alphabet.put(key, 100);
      return this;
    }

    public AlphabetBuilder FiveHundred(Character key) {
      this.alphabet.put(key, 500);
      return this;
    }

    public AlphabetBuilder Thousand(Character key) {
      this.alphabet.put(key, 1000);
      return this;
    }

    public Alphabet build() {
      Integer[] values = new Integer[] {1, 5, 10, 50, 100, 500, 1000};
      Set<Integer> v = Set.of(values);
      long definedDigits =
          alphabet.entrySet().stream().filter(digit -> v.contains(digit.getValue())).count();
      if (definedDigits != 7)
        throw new RuntimeException(
            "Alphabet must have 7 defined pair key-values. Defined: " + definedDigits + "/7 digits");
      Alphabet result = new Alphabet(alphabet);
      return result;
    }
  }
}

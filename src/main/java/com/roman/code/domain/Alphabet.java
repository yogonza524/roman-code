package com.roman.code.domain;

import com.roman.code.exception.IncompleteAlphabetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Alphabet {
  private Map<String, Integer> alphabet;

  private Alphabet(Map<String, Integer> alphabet) {
    this.alphabet = alphabet;
  }

  public Map<String, Integer> getAlphabet() {
    return this.alphabet;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static Alphabet roman() {
    return new Builder()
        .One("I")
        .Five("V")
        .Ten("X")
        .Fifty("L")
        .OneHundred("C")
        .FiveHundred("D")
        .Thousand("M")
        .build();
  }

  public static final class Builder {
    private Map<String, Integer> alphabet;

    private Builder() {
      this.alphabet = new HashMap<>();
    }

    public Builder One(String key) {
      this.alphabet.put(key, 1);
      return this;
    }

    public Builder Five(String key) {
      this.alphabet.put(key, 5);
      return this;
    }

    public Builder Ten(String key) {
      this.alphabet.put(key, 10);
      return this;
    }

    public Builder Fifty(String key) {
      this.alphabet.put(key, 50);
      return this;
    }

    public Builder OneHundred(String key) {
      this.alphabet.put(key, 100);
      return this;
    }

    public Builder FiveHundred(String key) {
      this.alphabet.put(key, 500);
      return this;
    }

    public Builder Thousand(String key) {
      this.alphabet.put(key, 1000);
      return this;
    }

    private long countDefinedDigitsOfAlphabet() {
      Integer[] values = new Integer[] {1, 5, 10, 50, 100, 500, 1000};
      Set<Integer> v = Set.of(values);
      return alphabet.entrySet().stream().filter(digit -> v.contains(digit.getValue())).count();
    }

    public Alphabet build() throws IncompleteAlphabetException {
      checkDefinedAlphabet();
      var immutableAlphabet = Collections.unmodifiableMap(alphabet);
      Alphabet result = new Alphabet(immutableAlphabet);
      return result;
    }

    private void checkDefinedAlphabet() {
      long definedDigits = this.countDefinedDigitsOfAlphabet();
      if (definedDigits != 7)
        throw new IncompleteAlphabetException(
            "Alphabet must have 7 defined pair key-values. Defined: "
                + definedDigits
                + "/7 digits");
    }
  }
}

package com.roman.code.domain;

import com.roman.code.exception.IncompleteAlphabetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Alphabet {
  private Map<Character, Integer> alphabet;

  private Alphabet(Map<Character, Integer> alphabet) {
    this.alphabet = alphabet;
  }

  public Map<Character, Integer> getAlphabet() {
    return this.alphabet;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private Map<Character, Integer> alphabet;
    private Set<Integer> values = Set.of(new Integer[] {1, 5, 10, 50, 100, 500, 1000});

    private Builder() {
      this.alphabet = new HashMap<>();
    }

    public Builder One(Character key) {
      this.alphabet.put(key, 1);
      return this;
    }

    public Builder Five(Character key) {
      this.alphabet.put(key, 5);
      return this;
    }

    public Builder Ten(Character key) {
      this.alphabet.put(key, 10);
      return this;
    }

    public Builder Fifty(Character key) {
      this.alphabet.put(key, 50);
      return this;
    }

    public Builder OneHundred(Character key) {
      this.alphabet.put(key, 100);
      return this;
    }

    public Builder FiveHundred(Character key) {
      this.alphabet.put(key, 500);
      return this;
    }

    public Builder Thousand(Character key) {
      this.alphabet.put(key, 1000);
      return this;
    }

    public Alphabet build() throws IncompleteAlphabetException {
      long definedDigits =
          alphabet.entrySet().stream().filter(digit -> values.contains(digit.getValue())).count();
      if (definedDigits != 7)
        throw new IncompleteAlphabetException(
            "Alphabet must have 7 defined pair key-values. Defined: "
                + definedDigits
                + "/7 digits. Please set keys " + undefinedDigits());
      return new Alphabet(Collections.unmodifiableMap(alphabet));
    }

    private String undefinedDigits() {
      Set<Integer> valuesCopy = new HashSet<>();
      valuesCopy.addAll(values);
      valuesCopy.removeAll(alphabet.values());
      return valuesCopy.stream().map(digit -> "for " + digit).collect(Collectors.joining(", "));
    }
  }
}

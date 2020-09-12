package com.roman.code;

import com.roman.code.domain.Alphabet;
import com.roman.code.exception.IncompleteAlphabetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;
import javax.naming.OperationNotSupportedException;

public class ConvertToAlphabet {

  public static String fromArabic(Integer number, Alphabet alphabet)
      throws OperationNotSupportedException {
    Objects.requireNonNull(number, "Number must be not null");
    Objects.requireNonNull(alphabet, "Alphabet must be not null");

    if (number <= 0) throw new OperationNotSupportedException("Number must be positive");

    // Fill all values required to operate before
    var treeAlphabet = fillOuterValues(alphabet.getAlphabet());

    return convertToAlphabet(number, treeAlphabet);
  }

  private static String convertToAlphabet(Integer n, TreeMap<Integer, String> alphabet) {
    int l = alphabet.floorKey(n);
    if (n == l) {
      return alphabet.get(n);
    }
    return alphabet.get(l) + convertToAlphabet(n - l, alphabet);
  }

  private static TreeMap<Integer, String> fillOuterValues(Map<String, Integer> alphabet)
      throws IncompleteAlphabetException {
    Objects.requireNonNull(alphabet);

    Map<String, Integer> copy = new HashMap<>();
    copy.putAll(alphabet);
    // Complete the alphabet
    copy.put(getKeyByValue(alphabet, 100) + getKeyByValue(alphabet, 1000), 900);
    copy.put(getKeyByValue(alphabet, 100) + getKeyByValue(alphabet, 500), 400);
    copy.put(getKeyByValue(alphabet, 10) + getKeyByValue(alphabet, 100), 90);
    copy.put(getKeyByValue(alphabet, 10) + getKeyByValue(alphabet, 50), 40);
    copy.put(getKeyByValue(alphabet, 1) + getKeyByValue(alphabet, 10), 9);
    copy.put(getKeyByValue(alphabet, 1) + getKeyByValue(alphabet, 5), 4);

    TreeMap<Integer, String> tree = new TreeMap<>();

    Iterator<Map.Entry<String, Integer>> i = copy.entrySet().iterator();
    while (i.hasNext()) {
      Map.Entry<String, Integer> entry = i.next();
      tree.put(entry.getValue(), entry.getKey());
    }
    return tree;
  }

  private static String getKeyByValue(Map<String, Integer> map, Integer value) {
    var result =
        map.entrySet()
            .stream()
            .filter(entry -> Objects.equals(entry.getValue(), value))
            .map(Map.Entry::getKey)
            .collect(Collectors.toSet());
    if (result.isEmpty()) throw new RuntimeException("Key for value=" + value + " not found");
    return result.iterator().next();
  }
}

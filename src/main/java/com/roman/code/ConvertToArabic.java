package com.roman.code;

import com.roman.code.domain.Alphabet;
import com.roman.code.exception.ConversionException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.apache.commons.lang3.StringUtils;

public class ConvertToArabic {
  private static Map<Character, Integer> numbers = new HashMap<>();

  static {
    numbers.put('I', 1);
    numbers.put('V', 5);
    numbers.put('X', 10);
    numbers.put('L', 50);
    numbers.put('C', 100);
    numbers.put('D', 500);
    numbers.put('M', 1000);
  }

  public static Integer fromRoman(String romanNumber) throws ConversionException {
    validate(romanNumber, numbers);
    return transform.apply(romanNumber, numbers);
  }

  public static Integer fromAlphabet(String number, Alphabet alphabet) {
    validate(number, alphabet.getAlphabet());
    return transform.apply(number, alphabet.getAlphabet());
  }

  private static BiFunction<String, Map<Character, Integer>, Integer> transform =
      (input, map) ->
          validate(input, map)
              ? input.length() == 1
                  ? map.get(input.charAt(0))
                  : sum(input.charAt(0), input.substring(1), 0, LastNumber.NO, map)
              : -1;

  private static BiFunction<Character, Map<Character, Integer>, Integer> convert =
      (digit, map) -> map.get(digit);

  private static Function<String, Integer> calcOffset = (string) -> string.length() > 1 ? 1 : 0;
  private static Function<Integer, LastNumber> isLast =
      (offset) -> offset > 0 ? LastNumber.YES : LastNumber.NO;

  private static int sum(
      char head, String tail, int acum, LastNumber lastNumber, Map<Character, Integer> map) {
    if (StringUtils.isBlank(tail) && lastNumber.equals(LastNumber.YES))
      return acum + convert.apply(head, map);
    if (StringUtils.isBlank(tail)) return acum;

    char nextDigit = tail.charAt(0);
    int first = convert.apply(head, map);
    int second = convert.apply(nextDigit, map);

    if (first >= second) {
      LastNumber flag = first == second ? LastNumber.YES : LastNumber.NO;
      return sum(nextDigit, tail.substring(1), acum + first, flag, map);
    }
    int offset = calcOffset.apply(tail);
    return sum(
        tail.charAt(offset),
        tail.substring(offset + 1),
        acum + (second - first),
        isLast.apply(offset),
        map);
  }

  private static Boolean validateSintax(String number, Map<Character, Integer> map) {
    if (StringUtils.isBlank(number)) return true;
    if (number.length() < 2) return true;

    int first = convert.apply(number.charAt(0), map);
    int second = convert.apply(number.charAt(1), map);

    if (first < second && (map.containsValue(second - first))) return false;

    return validateSintax(number.substring(1), map);
  }

  private static boolean hasConcecutives(char head, String input, int count) {
    if (StringUtils.isBlank(input)) return false;
    if (count > 3) return true;
    if (input.length() == 1 && head == input.charAt(0) && count == 3) return true;

    return hasConcecutives(
        input.charAt(0), input.substring(1), head == input.charAt(0) ? ++count : 1);
  }

  private static boolean validateConvecutives(String input) {
    return !hasConcecutives(input.charAt(0), input.substring(1), 1);
  }

  private static boolean hasTwoMinorSymbols(char head, String input, Map<Character, Integer> map) {
    if (StringUtils.isBlank(input)) return false;
    if (input.length() < 2) return false;

    int first = convert.apply(head, map);
    int second = convert.apply(input.charAt(0), map);
    int third = convert.apply(input.charAt(1), map);

    if (first < third && second < third) return true;

    return hasTwoMinorSymbols(input.charAt(0), input.substring(1), map);
  }

  private static boolean validateSustractViolation(String input, Map<Character, Integer> map) {
    return !hasTwoMinorSymbols(input.charAt(0), input.substring(1), map);
  }

  private static boolean validateAlphabet(String number, Map<Character, Integer> map) {
    long count =
        number.chars().mapToObj(c -> (char) c).filter(digit -> map.containsKey(digit)).count();
    return count == number.length();
  }

  private static boolean validate(String number, Map<Character, Integer> map)
      throws ConversionException {
    // Validate if number is valid at alphabet
    boolean validAtAlphabet = validateAlphabet(number, map);
    if (!validAtAlphabet)
      throw new ConversionException(
          "Alphabet violation for "
              + number
              + ". Cause: Exists a digit at number that doesn't exists at alphabet");
    // Validate if difference between two digits doesn't match with exist digit
    boolean hasValidSintax = validateSintax(number, map);
    if (!hasValidSintax)
      throw new ConversionException(
          "Sintaxis violation for "
              + number
              + ". Cause: The number has two concecutives digits whose difference is another digit valid at alphabet");

    // Validate concecutives
    boolean notConcecutives = validateConvecutives(number);
    if (!notConcecutives)
      throw new ConversionException(
          "Concecutives ocurrence violation for "
              + number
              + ". Cause: The number has more than 3 concecutives digits");

    // Validate sustraction operations
    boolean hasValidSustraction = validateSustractViolation(number, map);
    if (!hasValidSustraction)
      throw new ConversionException(
          "Sustraction violation for "
              + number
              + ". Cause: For sustraction operation only can exist one digit left to greater one");

    return validAtAlphabet && hasValidSintax && notConcecutives && hasValidSustraction;
  }
}

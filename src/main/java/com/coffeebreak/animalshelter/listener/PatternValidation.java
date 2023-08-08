package com.coffeebreak.animalshelter.listener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternValidation {
      private static final String REGEX_MESSAGE = """
            (Рацион:)(\\s)(\\W+)(;)\s
            (Самочувствие:)(\\s)(\\W+)(;)
            (Поведение:)(\\s)(\\W+)(;)""";

      public static boolean validationPatternReport(String textRegEx) {
            Pattern reportPattern = Pattern.compile(REGEX_MESSAGE);
            Matcher reportMatcher = reportPattern.matcher(textRegEx);
            return reportMatcher.matches();
      }

}

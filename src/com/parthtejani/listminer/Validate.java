package com.parthtejani.listminer;


public class Validate {

    /**
     * Parses input string as an int if it exists, otherwise returns a default value
     * 
     * @param inputValue Input string
     * @param defaultValue Default value to return if input string is null
     * @return Either the integer contained in the input string or the default value
     */
    public static int parseInt(String inputValue, int defaultValue) {
        if (inputValue != null) {
            return Integer.parseInt(inputValue);
        } else {
            return defaultValue;
        }
    }

    /**
     * This function is only used when parsing an Option that is a flag. If the flag is
     * present (inputValue != null), then the opposite of the default value is used.
     * 
     * @param inputValue String that represents if the flag is present
     * @param defaultValue Default value to return when flag not present
     * @return defaultValue if inputValue is null, otherwise !defaultValue
     */
    public static boolean checkFlag(String inputValue, boolean defaultValue) {
        if (inputValue != null) {
            return !defaultValue;
        } else {
            return defaultValue;
        }
    }

    public static void checkNull(Object value, String message) {
        if (value == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

}

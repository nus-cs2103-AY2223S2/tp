package wingman.logic.toplevel.syntax;

import wingman.logic.core.exceptions.ParseException;

/**
 * The parent class of all Syntax classes.
 * It provides a method that checks the non-negativeness of integers, which is shared
 * across all children classes.
 */
public abstract class ModelSyntax {
    static final String NON_NEGATIVE_ERROR_MESSAGE = "Numerical values cannot be negative.";
    static final String NON_ALPHANUMERIC_SPACE_ERROR_MESSAGE = "The strings should only consist of "
            + "alphabets, numbers, and/or spaces.";

    /**
     * Checks all input integers are non-negative, otherwise an exception is thrown.
     *
     * @param integers integers to check, expected user-input integers
     * @throws ParseException when any of the integers is negative
     */
    public static void requireAllNonNegative(int... integers) throws ParseException {
        for (int integer: integers) {
            if (integer < 0) {
                throw new ParseException(NON_NEGATIVE_ERROR_MESSAGE);
            }
        }
    }

    private static boolean isAlphanumericOrSpace(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (!Character.isLetterOrDigit(c) && c != ' ') {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks all input strings are alphanumeric.
     *
     * @param strings the input strings from the user
     * @throws ParseException if any input string is not alphanumeric
     */
    protected static void requireAllAlphanumericOrSpace(String... strings) throws ParseException {
        for (String string: strings) {
            if (!isAlphanumericOrSpace(string)) {
                throw new ParseException(NON_ALPHANUMERIC_SPACE_ERROR_MESSAGE);
            }
        }
    }
}

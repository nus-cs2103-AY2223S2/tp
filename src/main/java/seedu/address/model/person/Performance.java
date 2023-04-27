package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's performance in the address book.
 * Guarantees: immutable; is always valid
 */
public class Performance {

    public static final String NULL_PERFORMANCE = "None yet!";
    public static final String MESSAGE_CONSTRAINTS = "Performance (Score) Must be an integer between 0 to 100";


    public final String value;

    public Performance() {
        value = NULL_PERFORMANCE;
    }

    /**
     * Accepts remark that is not empty
     * @param performance String of a performance
     */
    public Performance(String performance) {
        requireNonNull(performance);
        checkArgument(isValidPerformance(performance), Performance.MESSAGE_CONSTRAINTS);
        value = performance;
    }

    /**
     * Checks if input {@code String performance} is valid
     * @param performance Input string to check upon
     * @return Whether the performance is a validated integer
     */
    public static boolean isValidPerformance(String performance) {
        boolean validInteger = true;
        for (int i = 0; i < performance.length(); i++) {
            if (!Character.isDigit(performance.charAt(i))) {
                validInteger = false;
            }
        }
        int convertedNumber = 0;
        try {
            convertedNumber = Integer.parseInt(performance);
        } catch (NumberFormatException nfe) {
            return false;
        }
        if (validInteger) {
            if (convertedNumber < 0 || convertedNumber > 100) {
                validInteger = false;
            }
        }
        return validInteger;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Performance // instanceof handles nulls
                && value.equals(((Performance) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public int calculateUrgency() {
        return 100 - Integer.parseInt(value);
    }

    public int getPerformanceValue() {
        return Integer.parseInt(value);
    }
}

package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's performance in the address book.
 * Guarantees: immutable; is always valid
 */
public class Performance {

    public static final String NULL_PERFORMANCE = "None yet!";
    public static final String MESSAGE_CONSTRAINTS = "Must be an integer between 0 to 100";


    public final String value;

    /**
     * Accepts remark that is not empty
     * @param performance String of a performance
     */
    public Performance(String performance) {
        requireNonNull(performance);
        checkArgument(isValidPerformance(performance), Performance.MESSAGE_CONSTRAINTS);
        value = performance;
    }

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
                && value == ((Performance) other).value); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

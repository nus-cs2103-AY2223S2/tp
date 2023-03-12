package seedu.careflow.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.careflow.commons.util.AppUtil.checkArgument;

/**
 * Represents a patient's IC in the patient record
 */
public class Ic {
    public static final String MESSAGE_CONSTRAINTS =
            "IC number should only contain alphanumeric characters,"
                    + " and it should be exactly 8 digits and characters long";

    private static final String VALIDATION_REGEX = "[a-zA-Z]\\d{7}[a-zA-Z]";
    public final String value;

    /**
     * Constructs a {@code Ic}.
     *
     * @param icNumber A valid ic number.
     */
    public Ic(String icNumber) {
        requireNonNull(icNumber);
        checkArgument(isValidIc(icNumber), MESSAGE_CONSTRAINTS);
        value = icNumber;
    }

    /**
     * Returns true if a given string is a valid ic number.
     */
    public static boolean isValidIc(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Ic // instanceof handles nulls
                && value.equals(((Ic) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

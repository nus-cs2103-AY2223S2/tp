package seedu.address.model.person.information;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class NRIC {
    public static final String MESSAGE_CONSTRAINTS =
            "NRIC should follow the valid format, and should be 9 characters long";
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9]{9}$";
    public final String value;

    public NRIC(String nric) {
        requireNonNull(nric);
        checkArgument(isValidNRIC(nric), MESSAGE_CONSTRAINTS);
        value = nric;
    }

    public static boolean isValidNRIC(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NRIC // instanceof handles nulls
                && value.equals(((NRIC) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

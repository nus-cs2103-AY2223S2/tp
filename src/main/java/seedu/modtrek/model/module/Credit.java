package seedu.modtrek.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.modtrek.commons.util.AppUtil.checkArgument;

/**
 * Credit denotes the Modular Credit (MC) associated with each module.
 */
public class Credit {

    public static final String MESSAGE_CONSTRAINTS =
            "Credit should only be a positive integer and only 1-2 digits long.";

    public static final String MESSAGE_MISSING_DETAIL = "Missing credit after /c.";

    private static final String VALIDATION_REGEX = "\\d{1,2}";

    protected final String value;

    /**
     * Instantiates a new Credit. Credit cannot be null and must be valid.
     *
     * @param value the value
     */
    public Credit(String value) {
        requireNonNull(value);
        checkArgument(isValidCredit(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Checks if the Credit is valid.
     *
     * @param test the test
     * @return the boolean
     */
    public static boolean isValidCredit(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj
                || (obj instanceof Credit
                && value.equals(((Credit) obj).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

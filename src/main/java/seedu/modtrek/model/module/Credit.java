package seedu.modtrek.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.modtrek.commons.util.AppUtil.checkArgument;

/**
 * The type Credit.
 */
public class Credit {

    /**
     * The constant MESSAGE_CONSTRAINTS.
     */
    public static final String MESSAGE_CONSTRAINTS =
            "Credit should only be a number and only 1-2 digits long";

    private static final String VALIDATION_REGEX = "\\d{1,2}";

    /**
     * The Value.
     */
    protected final String value;

    /**
     * Instantiates a new Credit.
     *
     * @param value the value
     */
    public Credit(String value) {
        requireNonNull(value);
        checkArgument(isValidCredit(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Is valid credit boolean.
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

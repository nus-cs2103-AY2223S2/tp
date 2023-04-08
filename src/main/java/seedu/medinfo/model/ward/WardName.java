package seedu.medinfo.model.ward;

import static java.util.Objects.requireNonNull;
import static seedu.medinfo.commons.util.AppUtil.checkArgument;

/**
 * Represents a Ward's name in MedInfo.
 * Guarantees: immutable; is valid as declared in {@link #isValidWardName(String)}
 */
public class WardName {

    public static final String MESSAGE_CONSTRAINTS = "Ward names should be 40 characters or less,"
            + "should only contain alphanumeric characters"
            + "and spaces, and it should not be blank";

    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]{0,39}";

    public final String wardName;

    /**
     * Constructs a {@code WardName}.
     *
     * @param name A valid ward name.
     */
    public WardName(String name) {
        requireNonNull(name);
        checkArgument(isValidWardName(name), MESSAGE_CONSTRAINTS);
        wardName = name;
    }

    /**
     * Returns true if a given string is a valid ward name.
     */
    public static boolean isValidWardName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return wardName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof WardName // instanceof handles nulls
                && wardName.equalsIgnoreCase(((WardName) other).wardName)); // state check
    }

    @Override
    public int hashCode() {
        return wardName.hashCode();
    }

}

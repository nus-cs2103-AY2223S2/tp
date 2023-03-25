package teambuilder.model.team;

import teambuilder.model.person.Address;

import static java.util.Objects.requireNonNull;
import static teambuilder.commons.util.AppUtil.checkArgument;

/**
 * Represents a Team's description.
 * Guarantees: immutable; is valid as declared in {@link #isValidTeamDesc(String)}
 */
public class Desc {

    public static final String MESSAGE_CONSTRAINTS = "Descriptions can take any values, and it should not be blank";

    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Desc}.
     *
     * @param desc A valid address.
     */
    public Desc(String desc) {
        requireNonNull(desc);
        checkArgument(isValidTeamDesc(desc), MESSAGE_CONSTRAINTS);
        value = desc;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidTeamDesc(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Desc // instanceof handles nulls
                && value.equals(((Desc) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

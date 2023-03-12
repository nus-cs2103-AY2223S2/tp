package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
public class Role {

    public static final String MESSAGE_CONSTRAINTS = "Role should only contain alphanumeric characters and spaces, and it should not be blank";

    public static final String VALIDATION_REGEX = "[\\p{Alpha}\\p{Digit}]*";

    public final String value;

    public Role(String personRole) {
        requireNonNull(personRole);
        checkArgument(isValidRole(personRole), MESSAGE_CONSTRAINTS);
        value = personRole;
    }

    public static boolean isValidRole(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Role // instanceof handles nulls
                && value.equals(((Role) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

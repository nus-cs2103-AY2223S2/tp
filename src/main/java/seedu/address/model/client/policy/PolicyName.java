package seedu.address.model.client.policy;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the name of a policy.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class PolicyName {

    public static final String MESSAGE_CONSTRAINTS =
            "Policy names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String policyName;

    /**
     * Constructs a {@code Policy Name}.
     *
     * @param name A valid policy name.
     */
    public PolicyName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        policyName = name;
    }

    /**
     * Returns true if a given string is a valid policy name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return policyName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PolicyName // instanceof handles nulls
                && policyName.equals(((PolicyName) other).policyName)); // state check
    }

    @Override
    public int hashCode() {
        return policyName.hashCode();
    }

}

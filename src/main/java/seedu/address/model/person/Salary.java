package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an internship's salary in the tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidSalary(String)}
 */
public class Salary extends InternshipApplicationAttribute {

    public static final String MESSAGE_CONSTRAINTS = "Salaries must be in the form [number] [CURRENCY] or blank";

    public static final String VALIDATION_REGEX = "([0-9]+ [A-Z]+|)";

    public final String value;

    /**
     * Constructs an {@code Salary}.
     *
     * @param x A valid Salary.
     */
    public Salary(String x) {
        checkArgument(isValidSalary(x), MESSAGE_CONSTRAINTS);
        value = x;
    }

    /**
     * Returns true if a given string is a valid salary.
     */
    public static boolean isValidSalary(String test) {
        if (test == null) {
            return true;
        }
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Salary// instanceof handles nulls
                && value.equals(((Salary) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}


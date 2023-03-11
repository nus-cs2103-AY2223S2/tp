package seedu.address.model.job;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Salary in the Tech track.
 * Guarantees: immutable; is valid as declared in {@link #isValidSalary(String)}
 */
public class Salary {
    public static final String MESSAGE_CONSTRAINTS =
            "Salaries should be more than 0!";
    public static final String VALIDATION_REGEX = "\\d{1,}";
    public final String salary;

    /**
     * Constructs a {@code salary}.
     *
     * @param salary A valid salary.
     */
    public Salary(String salary) {
        requireNonNull(salary);
        checkArgument(isValidSalary(salary), MESSAGE_CONSTRAINTS);
        this.salary = salary;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidSalary(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return salary;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Salary // instanceof handles nulls
                && salary.equals(((Salary) other).salary)); // state check
    }

    @Override
    public int hashCode() {
        return salary.hashCode();
    }
}

package seedu.address.model.employee;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Class represents an employee's employee ID in the database.
 */
public class EmployeeId {

    public static final String MESSAGE_CONSTRAINTS =
            "Employee ID is entirely managed by ExecutivePro. It is a fixed number. Please do not tamper.";

    /*
     * The first character of the position must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^\\d+";

    private static int count = 1;

    public final String value;

    /**
     * Constructs a {@code EmployeeId}.
     */
    public EmployeeId() {
        this.value = String.format("%d", count++);
    }

    /**
     * Constructs a {@code EmployeeId} with a given ID.
     * Uses the raw integer value of the ID (no leading zeroes).
     * Called by factory method {@code addEmployeeId}.
     * Also used primarily in testing.
     */
    public EmployeeId(String id) {
        requireNonNull(id);
        checkArgument(isValidNumber(id));
        this.value = String.valueOf(Integer.parseInt(id));
    }


    /**
     * Returns true if a given string is a valid number, and can possibly be an employee ID.
     */
    public static boolean isValidNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidEmployeeId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Gets the current employee ID as an integer.
     */
    public int getEmployeeId() {
        return Integer.parseInt(value);
    }

    /**
     * Sets the current employee count and thus next employee ID.
     */
    public static void setCount(int count) {
        EmployeeId.count = count;
    }

    /**
     * Gets the current employee count.
     */
    public static int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof EmployeeId
                && Integer.valueOf(this.value).equals(Integer.valueOf(((EmployeeId) other).value)));
    }
}


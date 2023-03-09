package seedu.address.model.person;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Set;

/**
 * Class represents a Person's employee ID in the database.
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

    private static Set<Integer> allIds = new HashSet<Integer>();

    public final String value;

    /**
     * Constructs a {@code EmployeeId}.
     */
    public EmployeeId() {
        allIds.add(count);
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
     * Resets the static count to a given input.
     * Also clears the set for new entries.
     */
    public static void restart(int count) {
        allIds.clear();
        EmployeeId.count = count;
    }

    /**
     * Factory method for creating a new {@code EmployeeId}.
     * Calls the constructor with a given string, but performs validation checks.
     */
    public static EmployeeId addEmployeeId(String id) {
        requireNonNull(id);
        checkArgument(isValidEmployeeId(id));
        int rawValue = Integer.parseInt(id);
        allIds.add(rawValue);
        return new EmployeeId(String.valueOf(rawValue));
    }

    /**
     * Returns true if a given string is a valid number, and can possibly be an employee ID.
     */
    public static boolean isValidNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid new Employee ID.
     */
    public static boolean isValidEmployeeId(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }
        int testValue = Integer.parseInt(test);
        return testValue > 0 && testValue < count && !allIds.contains(testValue);
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

    /**
     * Gets the size of the hash set containing all Ids.
     */
    public static int getCurrentIdsCount() {
        return allIds.size();
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


package seedu.address.model.person;

/**
 * Represents the Priority level that the client is currently at.
 * Guarantees: is valid as declared in {@link #isValidPriority(String)}
 */
public class Priority implements Comparable<Priority> {


    public static final String MESSAGE_CONSTRAINTS =
            "Priority must be marked as either HIGH, LOW, OR MEDIUM";
    public static final String[] VALIDATION_ARRAY = {"HIGH", "LOW", "MEDIUM"};
    public final String value;

    /**
     * Constructs an {@code Priority}.
     *
     * @param priority Priority level of the client.
     */
    public Priority(String priority) {
        this.value = String.valueOf(priority);
    }

    /**
     * Checks whether a given string is a priority.
     * @param test The priority to be checked.
     */
    public static boolean isValidPriority(String test) {
        for (String str : VALIDATION_ARRAY) {
            if (test.equals(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Maps the priority of the client to an integer value.
     *
     * @return The integer value of the client's priority.
     */
    public Integer getPriorityValue() {
        if (value.equals("HIGH")) {
            return 3;
        } else if (value.equals("MEDIUM")) {
            return 2;
        } else if (value.equals("LOW")) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return value;
    }


    @Override
    public int compareTo(Priority otherPriority) {
        return this.getPriorityValue() - otherPriority.getPriorityValue();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Priority // instanceof handles nulls
                && value.equals(((Priority) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

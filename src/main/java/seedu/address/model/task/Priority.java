package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the priority of a Task.
 * Guarantees: Non-null priority in valid form.
 */

public class Priority {
    enum PriorityLevel { LOW, MEDIUM, HIGH }
    public static final String MESSAGE_CONSTRAINTS =
            "Priority should be of the form: 'low' / 'medium' / 'high', and it should not be blank";

    public final PriorityLevel priority;

    /**
     * Constructs a {@code Priority}.
     *
     * @param priority priority level of a Task.
     */
    public Priority(String priority) {
        requireNonNull(priority);
        checkArgument(isValidPriority(priority), MESSAGE_CONSTRAINTS);
        //this.priority = priority;
        switch(priority) {
        case "low":
            this.priority = PriorityLevel.LOW;
            break;
        case "medium":
            this.priority = PriorityLevel.MEDIUM;
            break;
        case "high":
            this.priority = PriorityLevel.HIGH;
            break;
        default:
            throw new IllegalArgumentException("Unexpected priority level");
        }
    }

    /**
     * Returns true if a given string is a valid priority.
     */
    public static boolean isValidPriority(String test) {
        return test.equals("low") || test.equals("medium") || test.equals("high");
    }

    /**
     * Inverts natural order and returns comparison.
     * @param p priority to compare against.
     * @return int value of ordering.
     */
    public int compare(Priority p) {
        return -1 * this.priority.compareTo(p.priority);
    }

    @Override
    public String toString() {
        switch(this.priority) {
        case LOW:
            return "low";
        case MEDIUM:
            return "medium";
        case HIGH:
            return "high";
        default:
            throw new IllegalArgumentException("Unexpected priority level");
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Priority// instanceof handles nulls
                && priority.equals(((Priority) other).priority)); // state check
    }

    @Override
    public int hashCode() {
        return priority.hashCode();
    }
}

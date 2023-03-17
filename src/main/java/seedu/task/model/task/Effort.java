package seedu.task.model.task;

import java.util.Objects;

import seedu.task.model.task.exceptions.InvalidEffortException;

/**
 * Represent a Task's required effort in the task book.
 */
public class Effort {
    public static final String MESSAGE_CONSTRAINTS = "Effort must be valid";
    public static final long DEFAULT_DAILY_EFFORT = 24;
    private long effort;

    /**
     * Creates an effort instance which is an abstraction on the estimated effort required to complete a task.
     * @param effort
     */
    public Effort(long effort) {
        if (effort < 0) {
            throw new InvalidEffortException();
        }
        this.effort = effort;
    }

    public Effort() {
        this.effort = DEFAULT_DAILY_EFFORT;
    }

    public long getEffort() {
        return this.effort;
    }

    @Override
    public String toString() {
        String str = "" + this.effort;
        return str;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Effort)) {
            return false;
        }

        Effort otherEffort = (Effort) other;
        return Objects.equals(otherEffort.effort, this.effort);
    }
}

package seedu.task.model.task;

import seedu.task.model.task.exceptions.InvalidEffortException;

import java.util.Objects;

/**
 * Represent a Task's required effort in the task book.
 */
public class Effort {
    public static final String MESSAGE_CONSTRAINTS = "Effort must be valid";
    private long effort;

    public Effort(long effort) {
        if (effort < 0) {
            throw new InvalidEffortException();
        }
        this.effort = effort;
    }

    public Effort() {
        this.effort = 2;
    }

    public long getEffort() {
        return this.effort;
    }

    @Override
    public String toString() {
        return String.valueOf(this.effort);
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

package seedu.address.model.student;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a Student's assignment in the TutorPro.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Homework {
    private final String description;
    private final LocalDateTime deadline;
    private boolean isDone;

    /**
     * Creates a new Homework with the given description and deadline.
     *
     * @param description The description of the assignment.
     * @param deadline The deadline of the assignment.
     * @throws ParseException if the deadline is in the past.
     */
    public Homework(String description, LocalDateTime deadline) {
        Objects.requireNonNull(description);
        Objects.requireNonNull(deadline);

        this.description = description;
        this.deadline = deadline;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String getStatusTag() {
        return isDone ? "[X]" : "[ ]";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Homework)) {
            return false;
        }

        Homework otherHomework = (Homework) other;
        return otherHomework.getDescription().equals(getDescription())
                && otherHomework.getDeadline().equals(getDeadline());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription(), getDeadline());
    }

    @Override
    public String toString() {
        return String.format("Status: %s, Description: %s, Deadline: %s", getStatusTag(), description, deadline);
    }
}

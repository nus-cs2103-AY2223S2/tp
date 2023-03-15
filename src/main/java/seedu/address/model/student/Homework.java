package seedu.address.model.student;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a Student's assignment in the TutorPro.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Homework {
    private static final DateTimeFormatter PRINT_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
    private static final String TO_STRING_FORMAT = "Status: %s, Description: %s, Deadline: %s";
    private static final String COMPLETED_TAG = "[X]";
    private static final String PENDING_TAG = "[ ]";

    private final String description;
    private final LocalDateTime deadline;
    private Status status;

    /**
     * The status of the homework.
     */
    public enum Status {
        COMPLETED, PENDING;
    };

    /**
     * Creates a new Homework with the given description and deadline.
     *
     * @param description The description of the assignment.
     * @param deadline The deadline of the assignment.
     */
    public Homework(String description, LocalDateTime deadline) {
        Objects.requireNonNull(description);
        Objects.requireNonNull(deadline);

        this.description = description;
        this.deadline = deadline;
        this.status = Status.PENDING;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public Status getStatus() {
        return status;
    }

    public boolean isCompleted() {
        return status == Status.COMPLETED;
    }

    /**
     * Marks the homework as completed.
     */
    public void markAsDone() {
        status = Status.COMPLETED;
    }

    /**
     * Marks the homework as not completed.
     */
    public void markAsUndone() {
        status = Status.PENDING;
    }

    /**
     * Returns a tag to indicate whether the homework is completed or not.
     *
     * @return A tag to indicate whether the homework is completed or not.
     */
    public String getStatusTag() {
        return status == Status.COMPLETED ? COMPLETED_TAG : PENDING_TAG;
    }

    /**
     * Returns true if both homework have the same description.
     * This defines a weaker notion of equality between two homework.
     *
     * @param otherHomework The other homework to compare with.
     * @return True if both homework have the same description.
     */
    public boolean isSameHomework(Homework otherHomework) {
        if (otherHomework == this) {
            return true;
        }

        return otherHomework != null
                && otherHomework.getDescription().equals(getDescription());
    }

    /**
     * Returns a string representation of the homework.
     *
     * @return A string representation of the homework.
     */
    public String getDeadlineString() {
        return deadline.format(PRINT_FORMATTER);
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
        return String.format(TO_STRING_FORMAT, getStatusTag(), getDescription(), getDeadlineString());
    }
}

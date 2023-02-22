package seedu.address.model.student;

import java.time.LocalDateTime;
import java.util.Objects;

public class Assignment {

    private final String description;
    private final LocalDateTime deadline;

    /**
     * Creates a new Assignment with the given description and deadline.
     *
     * @param description The description of the assignment.
     * @param deadline The deadline of the assignment.
     */
    public Assignment(String description, LocalDateTime deadline) {
        Objects.requireNonNull(description);
        Objects.requireNonNull(deadline);
        this.description = description;
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Assignment)) return false;
        Assignment that = (Assignment) o;
        return getDescription().equals(that.getDescription()) && getDeadline().equals(that.getDeadline());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription(), getDeadline());
    }

    @Override
    public String toString() {
        return String.format("Assignment{description='%s', deadline=%s}", description, deadline);
    }
}

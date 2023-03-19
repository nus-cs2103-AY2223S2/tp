package vimification.model.task;

import java.time.LocalDateTime;
import static java.util.Objects.requireNonNull;

public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String description, boolean isDone, LocalDateTime deadline) {
        super(description, isDone, TaskType.DEADLINE);
        requireNonNull(deadline);
        this.deadline = deadline;
    }

    public Deadline(String description, LocalDateTime deadline) {
        this(description, false, deadline);
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    @Override
    public Deadline clone() {
        return new Deadline(getDescription(), isDone(), deadline);
    }

    @Override
    public boolean isSameTask(Task task) {
        if (task == this) {
            return true;
        }

        if (!(task instanceof Deadline)) {
            return false;
        }

        Deadline otherDeadline = (Deadline) task;
        return isSameTask(otherDeadline)
                && otherDeadline.getDeadline().equals(deadline);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Deadline)) {
            return false;
        }

        Deadline otherDeadline = (Deadline) other;
        return super.equals(otherDeadline)
                && otherDeadline.getDeadline().equals(deadline);
    }

    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getType())
                .append(" ")
                .append(super.toString())
                .append("; by: ")
                .append(getDeadline());
        return builder.toString();
    }
}

package vimification.model.oldcode;

import vimification.model.task.Priority;
import vimification.model.task.Status;
import vimification.model.task.Task;

import java.time.LocalDateTime;
import static java.util.Objects.requireNonNull;

public class Deadline {

    private LocalDateTime deadline;
/**
    public Deadline(String description, Status status, Priority priority, LocalDateTime deadline) {
        super(description, status, priority);
        requireNonNull(deadline);
        this.deadline = deadline;
    }

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        requireNonNull(deadline);
        this.deadline = deadline;
    }
*/
    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public boolean isDeadline() {
        return true;
    }

    public boolean isDateBefore(LocalDateTime date) {
        return deadline.isBefore(date) || deadline.isEqual(date);
    }

    public boolean isDateAfter(LocalDateTime date) {
        return deadline.isAfter(date) || deadline.isEqual(date);
    }
/**
    @Override
    public Deadline clone() {
        return new Deadline(getDescription(), getStatus(), getPriority(), deadline);
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
        return super.isSameTask(otherDeadline) && otherDeadline.deadline.equals(deadline);
    }
*/
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Deadline)) {
            return false;
        }
        Deadline otherDeadline = (Deadline) other;
        return super.equals(otherDeadline) && otherDeadline.deadline.equals(deadline);
    }

    public String toString() {
        final StringBuilder builder = new StringBuilder(super.toString());
        builder.append("; by: ")
                .append(getDeadline());
        return builder.toString();
    }
}

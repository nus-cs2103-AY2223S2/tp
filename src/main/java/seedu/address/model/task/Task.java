package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.model.person.Name;

/**
 * Represents a Task in the address book.
 * Guarantees: immutable; fields are validated; details are present and not null;
 */
public class Task {

    public static final String MESSAGE_CONSTRAINTS =
            "Name of tasks should only contain alphanumeric characters and spaces, and it should not be blank";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";


    // Identity field(s)
    public final Name taskName;

    // Data field(s)
    private boolean isDone;

    /**
     * Constructs a {@code Task}.
     *
     * @param taskName A valid Task name.
     */
    public Task(Name taskName) {
        requireNonNull(taskName);
        this.taskName = taskName;
        isDone = false;
    }

    /**
     * Marks the task if its done.
     */
    public void markedTask() {
        isDone = true;
    }

    /**
     * Unmarks the task if its not done.
     */
    public void unMarkedTask() {
        isDone = false;
    }

    public Name getName() {
        return taskName;
    }

    public boolean getDone() {
        return isDone;
    }

    /**
     * Returns true if both tasks have the same name.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getName().equals(getName());
    }

    /**
     * Returns true if a given string is a valid task name.
     */
    public static boolean isValidTaskName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Task // instanceof handles nulls
                        && taskName.equals(((Task) other).taskName)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskName, isDone);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Done: ")
                .append(getDone() ? "[X]" : "[]");

        return builder.toString();
    }

}

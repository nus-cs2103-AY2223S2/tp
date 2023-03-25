package seedu.task.model.task;

import static seedu.task.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;


/**
 * Represents a subtask in the task book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Subtask {

    // Identity fields
    protected Name name;
    protected Description description;

    /**
     * Name, Description and Tag must be present and not null.
     * Effort may or may not be null. If null, default value will be used.
     * alertWindow is generated with default values.
     * plannedDate will always be initialised with a placeholder value, indicating that value is not ready.
     */
    public Subtask(Name name, Description description) {
        requireAllNonNull(name, description);
        this.name = name;
        this.description = description;
    }

    public Name getName() {
        return name;
    }

    public Description getDescription() {
        return description;
    }


    /**
     * Returns true if both tasks have the same name.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Subtask otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
            && otherTask.getName().equals(getName());
    }

    public boolean hasDescription() {
        return description.getHasDescription();
    }


    /**
     * Returns true if both tasks have the same identity and data fields.
     * This defines a stronger notion of equality between two tasks.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Subtask)) {
            return false;
        }

        Subtask otherTask = (Subtask) other;
        return otherTask.getName().equals(getName())
            && otherTask.getDescription().equals(getDescription());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, description);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
            .append("; Description: ")
            .append(getDescription());
        return builder.toString();
    }


}

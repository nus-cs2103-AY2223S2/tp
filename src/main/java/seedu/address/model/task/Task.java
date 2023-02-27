package seedu.address.model.task;

import java.util.Objects;

public class Task {
    private final Description description;

    public Task(Description description) {
        this.description = description;
    }

    public Description getDescription() {
        return this.description;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getDescription().equals(description);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDescription());

        return builder.toString();
    }
}

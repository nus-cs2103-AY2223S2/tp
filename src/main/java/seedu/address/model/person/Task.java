package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's remark in the address book.
 * Guarantees: immutable; is always valid
 */
public class Task {
    public final String value;

    public Task(String task) {
        requireNonNull(task);
        value = task;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Task // instanceof handles nulls
                && value.equals(((Task) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

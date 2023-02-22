package seedu.address.model.task;

import java.util.Objects;

/**
 * Represents a Task's status in the address book.
 * Guarantees: immutable;
 */
public class Status {
    public final boolean value;

    /**
     * Constructs a {@code Status}.
     *
     * @param value status of a task;
     */
    public Status(boolean value) {
        this.value = value;
    }


    @Override
    public String toString() {

        return value ? "Done" : "Undone";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Status // instanceof handles nulls
            && value == ((Status) other).value); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

}

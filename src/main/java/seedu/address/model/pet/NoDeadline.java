package seedu.address.model.pet;

import java.time.LocalDateTime;

/**
 * A specialized {@link Deadline} class representing the absence of a deadline.
 * This class extends the {@code Deadline} class and sets the description to "N/A" and the deadline to {@code null}.
 * The {@code toString} method is overridden to return the description only, without the deadline.
 */
public class NoDeadline extends Deadline {
    public NoDeadline() {
        super("N/A", null);
    }

    @Override
    public LocalDateTime getDate() {
        return null;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NoDeadline); // instanceof handles nulls
    }

    @Override
    public String toString() {
        return description;
    }
}

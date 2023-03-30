package seedu.address.model.pet;

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
    public String toString() {
        return description;
    }
}

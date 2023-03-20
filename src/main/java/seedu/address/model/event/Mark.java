package seedu.address.model.event;

/**
 * Represents the completeness of an Event in Paidlancers.
 */
public class Mark {
    private boolean isDone;

    /**
     * Constructs a {@code Mark}.
     */
    public Mark() {
        this.isDone = false;
    }

    /**
     * Returns true if an event is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the event as done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Marks the event as undone.
     */
    public void setUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "]";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Mark // instanceof handles nulls
                && isDone == ((Mark) other).isDone); // state check
    }

    @Override
    public int hashCode() {
        return Double.hashCode(isDone ? 1 : 0);
    }
}

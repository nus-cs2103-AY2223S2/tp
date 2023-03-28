package arb.model.project;

/**
 * Represents a Project's completion status in the address book.
 */
public class Status {

    private boolean isDone;

    /**
     * Constructs a {@code Status}: False by default.
     */
    public Status() {
        isDone = false;
    }

    /**
     * Constructs a {@code Status} with the given value.
     * @param status The value.
     */
    public Status(boolean status) {
        isDone = status;
    }

    /**
     * Returns the completion status as a boolean.
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * Sets the completion status to True.
     */
    public void setTrue() {
        isDone = true;
    }

    /**
     * Sets the completion status to False.
     */
    public void setFalse() {
        isDone = false;
    }


    @Override
    public String toString() {
        return isDone ? "DONE" : "NOT DONE";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // Short-circuit if same object
                || (other instanceof Status) // Handles null
                && isDone == ((Status) other).isDone; // Check isDone
    }

    @Override
    public int hashCode() {
        return Boolean.hashCode(isDone);
    }

}

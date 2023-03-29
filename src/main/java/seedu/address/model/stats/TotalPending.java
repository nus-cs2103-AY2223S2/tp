package seedu.address.model.stats;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents Total number of Pending jobs in Statistics Window.
 * Guarantees: details are present and not null.
 */
public class TotalPending {
    public static final String MESSAGE_CONSTRAINTS = "TotalPending must be a non-negative integer";
    private static final String OUTPUT_MESSAGE = "Total number of Pending Jobs: ";
    private int numPending;

    /**
     * Constructor to create a TotalPending object.
     * @param pending Total number of pending jobs in the job list. Cannot be negative and cannot be null.
     */
    public TotalPending(int pending) {
        requireNonNull(pending);
        checkArgument(isValidPending(pending), MESSAGE_CONSTRAINTS);
        this.numPending = pending;
    }

    public int getPending() {
        return numPending;
    }

    /**
     * Returns true if a given int is non-negative
     */
    public static boolean isValidPending(int test) {
        return test >= 0;
    }

    @Override
    public String toString() {
        return OUTPUT_MESSAGE + numPending + "\n";
    }
}

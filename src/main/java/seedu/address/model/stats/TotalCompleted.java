package seedu.address.model.stats;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents Total number of Completed jobs in Statistics Window.
 * Guarantees: details are present and not null.
 */
public class TotalCompleted implements Statistic {
    public static final String MESSAGE_CONSTRAINTS = "TotalCompleted must be a non-negative integer";
    private static final String OUTPUT_MESSAGE = "Total number of Completed Jobs: ";
    private int numCompleted;

    /**
     * Constructor to create a TotalCompleted object.
     * @param completed Total number of completed jobs in the job list. Cannot be negative and cannot be null.
     */
    public TotalCompleted(int completed) {
        requireNonNull(completed);
        checkArgument(isValidCompleted(completed), MESSAGE_CONSTRAINTS);
        this.numCompleted = completed;
    }

    public double getCompleted() {
        return numCompleted;
    }

    /**
     * Returns true if a given int is non-negative
     */
    public static boolean isValidCompleted(int test) {
        return test >= 0;
    }

    @Override
    public String toString() {
        return OUTPUT_MESSAGE + numCompleted + "\n";
    }
}

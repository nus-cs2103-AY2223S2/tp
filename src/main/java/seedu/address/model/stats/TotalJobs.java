package seedu.address.model.stats;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents TotalJobs in Statistics Window.
 * Guarantees: details are present and not null.
 */
public class TotalJobs {

    public static final String MESSAGE_CONSTRAINTS = "TotalJobs must be a non-negative integer";
    private static final String OUTPUT_MESSAGE = "Total number of jobs: ";
    private int numJobs;

    /**
     * Constructor to create a Reminder object.
     * @param numJobs Number of jobs in job list. Cannot be negative and cannot be null.
     */
    public TotalJobs(int numJobs) {
        requireNonNull(numJobs);
        checkArgument(isValidTotalJobs(numJobs), MESSAGE_CONSTRAINTS);
        this.numJobs = numJobs;
    }

    public int getNumJobs() {
        return numJobs;
    }

    /**
     * Returns true if a given int is non-negative
     */
    public static boolean isValidTotalJobs(int test) {
        return test >= 0;
    }

    @Override
    public String toString() {
        return OUTPUT_MESSAGE + Integer.toString(numJobs) + "\n";
    }
}

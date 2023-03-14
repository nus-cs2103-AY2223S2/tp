package seedu.address.model.stats;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class TotalJobs {

    public static final String MESSAGE_CONSTRAINTS = "TotalJobs must be a non-negative integer";
    public int numJobs;
    private final String OUTPUT_MESSAGE = "Total number of jobs: ";

    public TotalJobs(int numJobs) {
        requireNonNull(numJobs);
        checkArgument(isValidTotalJobs(numJobs), MESSAGE_CONSTRAINTS);
        this.numJobs = numJobs;
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

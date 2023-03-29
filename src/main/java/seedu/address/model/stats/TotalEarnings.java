package seedu.address.model.stats;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents TotalEarnings in Statistics Window.
 * Guarantees: details are present and not null.
 */
public class TotalEarnings implements Statistic {
    public static final String MESSAGE_CONSTRAINTS = "TotalEarnings must be a non-negative number";
    private static final String OUTPUT_MESSAGE = "Total Earnings: $";
    private double earnings;

    /**
     * Constructor to create a TotalEarnings object.
     * @param earnings Total earnings from all jobs in the joblist. Cannot be negative and cannot be null.
     */
    public TotalEarnings(double earnings) {
        requireNonNull(earnings);
        checkArgument(isValidEarnings(earnings), MESSAGE_CONSTRAINTS);
        this.earnings = earnings;
    }

    public double getEarnings() {
        return earnings;
    }

    /**
     * Returns true if a given int is non-negative
     */
    public static boolean isValidEarnings(double test) {
        return test >= 0;
    }

    @Override
    public String toString() {
        return OUTPUT_MESSAGE + earnings + "\n";
    }

}

package seedu.address.logic;

import seedu.address.logic.aggregatefunction.AggregateFunction;

/**
 * A Summary of statistics of the entities in {@code FriendlyLink}.
 */
public class Summary {
    public static final String TITLE = "Statistics:";
    public static final String MESSAGE_NO_DATA = TITLE + "\nNo data available";
    public static final String STATISTICS_DESCRIPTION = "\n %1$s: %2$s";
    private StringBuilder summary = new StringBuilder(TITLE);

    /**
     * Adds a description of a statistic calculated using an aggregate function.
     *
     * @param function Function used to calculate the statistics.
     */
    public void describe(AggregateFunction function) {
        summary.append(String.format(STATISTICS_DESCRIPTION, function.getDescription(), function.getResult()));
    }

    @Override
    public String toString() {
        if (summary.toString().equals(TITLE)) {
            return MESSAGE_NO_DATA;
        }
        return summary.toString();
    }
}

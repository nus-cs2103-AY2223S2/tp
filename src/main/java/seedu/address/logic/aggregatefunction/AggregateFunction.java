package seedu.address.logic.aggregatefunction;

/**
 * A function to calculate a particular statistic of {@code FriendlyLink}.
 */
public abstract class AggregateFunction {

    /**
     * Retrieves the description of the statistic.
     *
     * @return Description.
     */
    public abstract String getDescription();

    /**
     * Retrieves the result of the calculations of the statistic.
     *
     * @return Result.
     */
    public abstract String getResult();
}

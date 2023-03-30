package seedu.address.model.statstics;

/**
 * Encapsulates statistics information of InternEase.
 */
public abstract class StatsInformation {
    public abstract void updateStatsInformation();

    public abstract Number getStatsInformation();

    public abstract String getDescription();
}

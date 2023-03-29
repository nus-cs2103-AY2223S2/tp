package seedu.address.model.statstics;

import javafx.beans.Observable;

public abstract class StatsInformation {
    public abstract void updateStatsInformation();

    public abstract Number getStatsInformation();

    public abstract String getDescription();
}

package seedu.address.model.statistic;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;

import javafx.collections.ObservableList;
import seedu.address.model.application.Application;
import seedu.address.model.application.Status;

/**
 * A class representing the logic for the statistics pie chart.
 */
public class Statistic {
    private final HashMap<Status, Integer> statsMap = new HashMap<>();
    private Integer totalNum = 0;

    /**
     * Constructs a Statistic instance corresponding to the current list of applications.
     *
     * @param applications an Observable List that contains current list of applications.
     */
    public Statistic(ObservableList<Application> applications) {
        requireNonNull(applications);

        statsMap.put(new Status("interested"), 0);
        statsMap.put(new Status("applied"), 0);
        statsMap.put(new Status("offered"), 0);
        statsMap.put(new Status("rejected"), 0);

        for (Application app : applications) {
            totalNum++;
            Status status = app.getStatus();
            int count = statsMap.get(status);
            count++;
            statsMap.put(status, count);
        }
    }

    public HashMap<Status, Integer> getStatsMap() {
        return statsMap;
    }

    public Integer getTotalNum() {
        return totalNum;
    }
}

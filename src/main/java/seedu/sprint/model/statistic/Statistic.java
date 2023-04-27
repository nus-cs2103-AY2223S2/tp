package seedu.sprint.model.statistic;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;

import javafx.collections.ObservableList;
import seedu.sprint.model.application.Application;
import seedu.sprint.model.application.Status;

/**
 * A class representing the logic for the application statistics to be displayed on the GUI.
 */
public class Statistic {
    private final HashMap<Status, Integer> statsMap = new HashMap<>();
    private Integer totalNum = 0;

    private final Status interestedStat = new Status("interested");
    private final Status appliedStat = new Status("applied");
    private final Status offeredStat = new Status("offered");
    private final Status rejectedStat = new Status("rejected");

    /**
     * Constructs a Statistic instance corresponding to the current list of applications.
     *
     * @param applications an Observable List that contains current list of applications.
     */
    public Statistic(ObservableList<Application> applications) {
        requireNonNull(applications);

        statsMap.put(interestedStat, 0);
        statsMap.put(appliedStat, 0);
        statsMap.put(offeredStat, 0);
        statsMap.put(rejectedStat, 0);

        for (Application app : applications) {
            totalNum++;
            Status status = app.getStatus();
            int count = statsMap.get(status);
            count++;
            statsMap.put(status, count);
        }

        assert statsMap.size() == 4;
    }

    public HashMap<Status, Integer> getStatsMap() {
        return statsMap;
    }

    public Integer getTotalNum() {
        return totalNum;
    }
}

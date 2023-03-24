package seedu.address.model.recommender;

import seedu.address.model.Model;
import seedu.address.model.location.Location;
import seedu.address.model.location.LocationRecommender;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.scheduler.Scheduler;
import seedu.address.model.scheduler.Timetable;
import seedu.address.model.scheduler.time.Day;
import seedu.address.model.scheduler.time.HourBlock;
import seedu.address.model.scheduler.time.TimePeriod;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Recommender {
    private static final int RECOMMENDATION_LIMIT = 20;
    private final LocationRecommender lr;
    private final Scheduler sc;
    private final Model model;
    private final HashMap<ContactIndex, HashMap<Day, HashMap<Integer, Location>>> personLocations;

    public Recommender(Model model) {
        this.model = model;
        lr = new LocationRecommender();
        sc = new Scheduler(model);
        personLocations = new HashMap<>();
    }

    public Recommendation recommend(Collection<ContactIndex> contactIndices) {
        sc.initialise(contactIndices);
        List<TimePeriod> availableTimePeriods = getAvailableTimePeriods(contactIndices);
    }

    private List<TimePeriod> getAvailableTimePeriods(Collection<ContactIndex> contactIndices) {
        return sc.giveLongestTimingRecommendations(RECOMMENDATION_LIMIT);
    }
}

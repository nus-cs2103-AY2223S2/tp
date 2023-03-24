package seedu.address.model.recommender;

import seedu.address.model.Model;
import seedu.address.model.location.LocationRecommender;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.scheduler.Scheduler;
import seedu.address.model.scheduler.time.TimePeriod;

import java.util.Collection;
import java.util.List;

public class Recommender {
    private static final int RECOMMENDATION_LIMIT = 10;
    private final LocationRecommender lr;
    private final Scheduler sc;
    private final Model model;

    public Recommender(Model model) {
        this.model = model;
        lr = new LocationRecommender();
        sc = new Scheduler(model);
    }

    public Recommendation recommend(Collection<ContactIndex> contactIndices) {
        List<TimePeriod> availableTimePeriods = getAvailableTimePeriods(contactIndices);
        return null;
    }

    private List<TimePeriod> getAvailableTimePeriods(Collection<ContactIndex> contactIndices) {
        sc.initialise(contactIndices);
        return sc.giveLongestTimingRecommendations(RECOMMENDATION_LIMIT);
    }
}

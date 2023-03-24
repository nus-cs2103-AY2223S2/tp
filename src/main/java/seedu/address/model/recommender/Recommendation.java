package seedu.address.model.recommender;

import seedu.address.model.location.Location;
import seedu.address.model.scheduler.time.TimePeriod;

public class Recommendation {
    private final Location location;
    private final TimePeriod timePeriod;

    public Recommendation(Location location, TimePeriod timePeriod) {
        this.location = location;
        this.timePeriod = timePeriod;
    }
}

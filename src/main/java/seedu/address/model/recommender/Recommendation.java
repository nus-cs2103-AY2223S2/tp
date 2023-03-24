package seedu.address.model.recommender;

import seedu.address.model.location.Location;
import seedu.address.model.scheduler.time.TimePeriod;

public class Recommendation implements Comparable<Recommendation> {
    private final Location location;
    private final TimePeriod timePeriod;

    public Recommendation(Location location, TimePeriod timePeriod) {
        this.location = location;
        this.timePeriod = timePeriod;
    }

    public Location getLocation() {
        return location;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    private int getClosenessToNoon() {
        return Math.abs(12 - timePeriod.getStartTime().getHourOfDay());
    }

    @Override
    public String toString() {
        return String.format("[%s, %s]", location, timePeriod);
    }

    @Override
    public int compareTo(Recommendation other) {
        return Integer.compare(getClosenessToNoon(), other.getClosenessToNoon());
    }
}

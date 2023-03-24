package seedu.address.model.recommender;

import seedu.address.model.location.Location;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.scheduler.time.TimePeriod;

public class Recommendation implements Comparable<Recommendation> {
    private final Location location;
    private final TimePeriod timePeriod;
    private final ContactIndex contactIndex;
    private final boolean isSaved;

    public Recommendation(
            Location location, TimePeriod timePeriod,
            ContactIndex contactIndex, boolean isSaved) {
        this.location = location;
        this.timePeriod = timePeriod;
        this.contactIndex = contactIndex;
        this.isSaved = isSaved;
    }

    public Recommendation(Location location, TimePeriod timePeriod, ContactIndex contactIndex) {
        this(location, timePeriod, contactIndex, false);
    }

    public Recommendation(Location location, TimePeriod timePeriod, boolean isSaved) {
        this(location, timePeriod, new ContactIndex(0), isSaved);
    }

    public Recommendation(Location location, TimePeriod timePeriod) {
        this(location, timePeriod, false);
    }

    public Location getLocation() {
        return location;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public boolean getIsSaved() {
        return isSaved;
    }

    private int getClosenessToNoon() {
        return Math.abs(12 - timePeriod.getStartTime().getHourOfDay());
    }

    public boolean isSameRecommendation(Recommendation other) {
        return timePeriod.equals(other.timePeriod) && location.equals(other.location);
    }

    public ContactIndex getContactIndex() {
        return contactIndex;
    }

    public Recommendation saveRecommendation() {
        return new Recommendation(location, timePeriod, contactIndex, true);
    }

    public Recommendation unsaveRecommendation() {
        return new Recommendation(location, timePeriod, contactIndex, false);
    }

    /**
     * Creates a new person and sets the contact index.
     */
    public Recommendation setContactIndex(ContactIndex contactIndex) {
        return new Recommendation(location, timePeriod, contactIndex);
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

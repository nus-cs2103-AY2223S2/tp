package seedu.address.model.recommendation;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.location.Location;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.time.TimePeriod;

/**
 * A meetup suggestion consisting of a location and time period.
 */
public class Recommendation implements Comparable<Recommendation> {
    private final Location location;
    private final TimePeriod timePeriod;
    private final ContactIndex contactIndex;
    private final boolean isSaved;

    /**
     * Constructor for a {@code Recommendation} object.
     */
    public Recommendation(
            Location location, TimePeriod timePeriod,
            ContactIndex contactIndex, boolean isSaved) {
        requireAllNonNull(location, timePeriod, contactIndex, isSaved);
        this.location = location;
        this.timePeriod = timePeriod;
        this.contactIndex = contactIndex;
        this.isSaved = isSaved;
    }

    /**
     * Overloaded constructor for a {@code Recommendation} object without save status.
     */
    public Recommendation(Location location, TimePeriod timePeriod, ContactIndex contactIndex) {
        this(location, timePeriod, contactIndex, false);
    }

    /**
     * Overloaded constructor for a {@code Recommendation} object without contact index.
     */
    public Recommendation(Location location, TimePeriod timePeriod, boolean isSaved) {
        this(location, timePeriod, new ContactIndex(0), isSaved);
    }

    /**
     * Overloaded constructor for a {@code Recommendation} object without
     * time contact index and save status.
     */
    public Recommendation(Location location, TimePeriod timePeriod) {
        this(location, timePeriod, false);
    }

    /**
     * Gets location.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Gets time period.
     */
    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    /**
     * Checks whether the Recommendation has been saved into Edumate.
     */
    public boolean getIsSaved() {
        return isSaved;
    }

    /**
     * Gets how close the timing is to the middle of the day.
     * Generally speaking, meeting nearer to noon is better.
     */
    private int getClosenessToNoon() {
        return Math.abs(12 - timePeriod.getStartTime().getHourOfDay());
    }

    /**
     * Compares two recommendations to check whether the time and location are the same.
     * This is a parallel to the code in {@code Person}.
     */
    public boolean isSameRecommendation(Recommendation other) {
        return timePeriod.equals(other.timePeriod) && location.equals(other.location);
    }

    /**
     * Gets the contact index of the Recommendation.
     */
    public ContactIndex getContactIndex() {
        return contactIndex;
    }

    /**
     * Returns a new Recommendation with an updated flag.
     */
    public Recommendation saveRecommendation() {
        return new Recommendation(location, timePeriod, contactIndex, true);
    }

    /**
     * Returns a new Recommendation with an updated flag.
     */
    public Recommendation unsaveRecommendation() {
        return new Recommendation(location, timePeriod, contactIndex, false);
    }

    /**
     * Creates a new recommendation and sets the contact index.
     */
    public Recommendation setContactIndex(ContactIndex contactIndex) {
        return new Recommendation(location, timePeriod, contactIndex, isSaved);
    }

    @Override
    public String toString() {
        return String.format("[%s, %s, %s]", contactIndex, location, timePeriod);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Recommendation)) {
            return false;
        }

        Recommendation otherRecommendation = (Recommendation) other;
        return otherRecommendation.location.equals(location)
                && otherRecommendation.timePeriod.equals(timePeriod)
                && otherRecommendation.isSaved == isSaved;
    }

    @Override
    public int compareTo(Recommendation other) {
        return Integer.compare(getClosenessToNoon(), other.getClosenessToNoon());
    }
}

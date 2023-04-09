package seedu.address.model.meetup;

import seedu.address.model.location.Location;
import seedu.address.model.recommendation.Recommendation;
import seedu.address.model.time.TimePeriod;

/**
 * A scheduled meet up consisting of location, time period and participants' indexes.
 */
public class MeetUp implements Comparable<MeetUp> {
    private final TimePeriod timePeriod;
    private final Location location;
    private Participants participants;
    private final MeetUpIndex meetUpIndex;

    /**
     * Constructor for a {@code MeetUp} object.
     */
    public MeetUp(Recommendation recommendation, Participants participants, MeetUpIndex meetUpIndex) {
        this.timePeriod = recommendation.getTimePeriod();
        this.location = recommendation.getLocation();
        this.participants = participants;
        this.meetUpIndex = meetUpIndex;
    }

    /**
     * Overloaded constructor for a {@code MeetUp} object not from meet recommendations.
     */
    public MeetUp(TimePeriod timePeriod, Location location, Participants participants, MeetUpIndex meetUpIndex) {
        this.timePeriod = timePeriod;
        this.location = location;
        this.participants = participants;
        this.meetUpIndex = meetUpIndex;
    }

    /**
     * Gets index of meetup.
     */
    public MeetUpIndex getMeetUpIndex() {
        return this.meetUpIndex;
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
     * Gets location.
     */
    public Participants getParticipants() {
        return participants;
    }

    public void setParticipants(Participants newParticipants) {
        this.participants = newParticipants;
    }

    @Override
    public int compareTo(MeetUp other) {
        return meetUpIndex.compareTo(other.meetUpIndex);
    }

    /**
     * Compares two meet ups to check whether the time and location and participants are the same.
     * @param other The other meet up.
     * @return True if both meet ups are the same.
     */
    public boolean isSameMeetUp(MeetUp other) {
        return this.timePeriod.equals(other.timePeriod)
                && this.location.equals(other.location)
                && this.participants.isSameParticipants(other.participants);
    }
}

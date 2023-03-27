package seedu.address.model.meetup;

import seedu.address.model.Participants;
import seedu.address.model.location.Location;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.recommendation.Recommendation;
import seedu.address.model.time.TimePeriod;

import java.util.Optional;
import java.util.Set;

public class MeetUp implements Comparable<MeetUp> {
    private final TimePeriod timePeriod; //todo comment later: this is an abstract class, could be hour block or timeblock
    private final Location location;
    private final Participants participants;
    private final ContactIndex contactIndex;

    //todo constructor for choice form recommendations
    public MeetUp(Recommendation recommendation, Participants participants, ContactIndex contactIndex) {
        this.timePeriod = recommendation.getTimePeriod();
        this.location = recommendation.getLocation();
        this.participants = participants;
        this.contactIndex = contactIndex;
    }

    //todo constructor for customised meet
    public MeetUp(TimePeriod timePeriod, Location location, Participants participants, ContactIndex contactIndex) {
        this.timePeriod = timePeriod;
        this.location = location;
        this.participants = participants;
        this.contactIndex = contactIndex;
    }

    public ContactIndex getContactIndex() {
        return this.contactIndex;
    }

    public int getContactIndexValue() {
        return this.contactIndex.getContactIndex();
    }

    public Location getLocation() {
        return location;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public Participants getParticipants() {
        return participants;
    }

    @Override
    public int compareTo(MeetUp o) {
        return 0;
    }
    //todo implement this

    public boolean isSameMeetUp(MeetUp other) {
        return this.timePeriod.equals(other.timePeriod) && this.location.equals(other.location) && this.participants.equals(other.participants);
    }
}

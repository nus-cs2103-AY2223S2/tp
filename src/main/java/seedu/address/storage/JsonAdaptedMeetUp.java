package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.model.location.Location;
import seedu.address.model.meetup.MeetUp;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.scheduler.time.TimePeriod;

import java.util.Set;

public class JsonAdaptedMeetUp {

    protected final Location location;
    protected final TimePeriod timePeriod;
    protected final Set<ContactIndex> participants;
    protected final ContactIndex contactIndex;

    @JsonCreator
    public JsonAdaptedMeetUp(@JsonProperty("location") Location location, @JsonProperty("timePeriod") TimePeriod timePeriod, @JsonProperty("participants") Set<ContactIndex> participants, @JsonProperty("contactIndex") ContactIndex contactIndex) {
        this.location = location;
        this.participants = participants;
        this.timePeriod = timePeriod;
        this.contactIndex = contactIndex;
    }

    public JsonAdaptedMeetUp(MeetUp meetUp) {
        this.location = meetUp.getLocation();
        this.participants = meetUp.getParticipants();
        this.timePeriod = meetUp.getTimePeriod();
        this.contactIndex = meetUp.getContactIndex();
    }

    public MeetUp toModelType() {
        return new MeetUp(timePeriod, location, participants,contactIndex);
    }
}

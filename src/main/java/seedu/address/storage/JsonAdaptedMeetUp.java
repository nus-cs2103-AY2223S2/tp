package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.model.meetup.MeetUp;

public class JsonAdaptedMeetUp {

    protected final JsonAdaptedLocation location;
    protected final JsonAdaptedTimePeriod timePeriod;
    protected final JsonAdaptedParticipants participants;
    protected final int contactIndex;

    @JsonCreator
    public JsonAdaptedMeetUp(
            @JsonProperty("location") JsonAdaptedLocation location,
            @JsonProperty("timePeriod")JsonAdaptedTimePeriod timePeriod,
            @JsonProperty("participants") JsonAdaptedParticipants participants,
            @JsonProperty("contactIndex") int contactIndex) {
        this.location = location;
        this.participants = participants;
        this.timePeriod = timePeriod;
        this.contactIndex = contactIndex;
    }

    public JsonAdaptedMeetUp(MeetUp meetUp) {
        this.location = new JsonAdaptedLocation(meetUp.getLocation());
        this.participants = new JsonAdaptedParticipants(meetUp.getParticipants());
        this.timePeriod = new JsonAdaptedTimePeriod(meetUp.getTimePeriod());
        this.contactIndex = meetUp.getContactIndexValue();
    }

    public MeetUp toModelType() {
        //todo to implement later
    }
}

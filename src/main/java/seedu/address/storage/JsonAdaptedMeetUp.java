package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Participants;
import seedu.address.model.location.Location;
import seedu.address.model.meetup.MeetUp;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.time.TimePeriod;

public class JsonAdaptedMeetUp {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "MeetUp's %s field is missing!";

    protected final JsonAdaptedLocation location;
    protected final JsonAdaptedTimePeriod timePeriod;
    protected final JsonAdaptedParticipants participants;
    protected final Integer contactIndex;

    @JsonCreator
    public JsonAdaptedMeetUp(
            @JsonProperty("location") JsonAdaptedLocation location,
            @JsonProperty("timePeriod")JsonAdaptedTimePeriod timePeriod,
            @JsonProperty("participants") JsonAdaptedParticipants participants,
            @JsonProperty("contactIndex") Integer contactIndex) {
        this.location = location;
        this.participants = participants;
        this.timePeriod = timePeriod;
        this.contactIndex = contactIndex;
    }

    public JsonAdaptedMeetUp(MeetUp meetUp) {
        location = new JsonAdaptedLocation(meetUp.getLocation());
        assert (meetUp.getParticipants() != null);
        participants = new JsonAdaptedParticipants(meetUp.getParticipants());
        timePeriod = new JsonAdaptedTimePeriod(meetUp.getTimePeriod());
        contactIndex = meetUp.getContactIndex().getContactIndex();
    }

    public MeetUp toModelType() throws IllegalValueException {
        //todo to implement later
        if (location == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Location.class.getSimpleName()));
        }
        final Location modelLocation = location.toModelType();

        if (timePeriod == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, TimePeriod.class.getSimpleName()));
        }
        final TimePeriod modelTimePeriod = timePeriod.toModelType();

        final ContactIndex modelContactIndex = new ContactIndex(contactIndex);

        final Participants modelParticipants = participants.toModelType();

        return new MeetUp(modelTimePeriod, modelLocation, modelParticipants,modelContactIndex);
    }
}

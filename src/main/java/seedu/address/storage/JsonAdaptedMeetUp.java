package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.location.Location;
import seedu.address.model.meetup.MeetUp;
import seedu.address.model.meetup.MeetUpIndex;
import seedu.address.model.meetup.Participants;
import seedu.address.model.time.TimePeriod;

/**
 * Jackson-friendly version of {@link MeetUp}.
 */
public class JsonAdaptedMeetUp {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Meet ups %s field is missing!";

    protected final JsonAdaptedLocation location;
    protected final JsonAdaptedTimePeriod timePeriod;
    protected final JsonAdaptedParticipants participants;
    protected final Integer meetUpIndex;

    /**
     * Constructs a {@code JsonAdaptedMeetUp} with the given meet up details.
     */
    @JsonCreator
    public JsonAdaptedMeetUp(
            @JsonProperty("location") JsonAdaptedLocation location,
            @JsonProperty("timePeriod")JsonAdaptedTimePeriod timePeriod,
            @JsonProperty("participants") JsonAdaptedParticipants participants,
            @JsonProperty("meetUpIndex") Integer meetUpIndex) {
        this.location = location;
        this.participants = participants;
        this.timePeriod = timePeriod;
        this.meetUpIndex = meetUpIndex;
    }

    /**
     * Converts a given {@code MeetUp} into this class for Jackson use.
     */
    public JsonAdaptedMeetUp(MeetUp meetUp) {
        location = new JsonAdaptedLocation(meetUp.getLocation());
        assert (meetUp.getParticipants() != null);
        participants = new JsonAdaptedParticipants(meetUp.getParticipants());
        timePeriod = new JsonAdaptedTimePeriod(meetUp.getTimePeriod());
        meetUpIndex = meetUp.getMeetUpIndex().getMeetUpIndex();
    }

    /**
     * Converts this Jackson-friendly adapted meet up object into the model's {@code MeetUp} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted meet up.
     */
    public MeetUp toModelType() throws IllegalValueException {
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
        final MeetUpIndex modelMeetUpIndex = new MeetUpIndex(meetUpIndex);
        final Participants modelParticipants = participants.toModelType();
        return new MeetUp(modelTimePeriod, modelLocation, modelParticipants, modelMeetUpIndex);
    }
}

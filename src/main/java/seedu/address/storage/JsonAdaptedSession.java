package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.session.SessionName;
import seedu.address.model.session.Location;
import seedu.address.model.session.Session;

public class JsonAdaptedSession {
    static final String MISSING_FIELD_MESSAGE_FORMAT = "Session's %s field is missing!";
    private final String startDateTime;
    private final String endDateTime;
    private final String name;
    private final String id;
    private final String location;
    //private final HashMap<Integer, Boolean> attendanceMap = new HashMap<>();

    @JsonCreator
    public JsonAdaptedSession(@JsonProperty("name") String name,
                             @JsonProperty("startTime") String startDateTime,
                             @JsonProperty("endTime") String endDateTime,
                             @JsonProperty("location") String location,
                             @JsonProperty("id") String id) {
        this.name = name;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.location = location;
        this.id = id;
    }

    public JsonAdaptedSession(Session source) {
        name = source.getName();
        startDateTime = source.getStartDateTime();
        endDateTime = source.getEndDateTime();
        location = source.getLocation();
        id = source.getId();
    }

    public Session toModelType() throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, SessionName.class.getSimpleName()));
        }
        if (!SessionName.isValidName(name)) {
            throw new IllegalValueException(SessionName.MESSAGE_CONSTRAINTS);
        }
        final SessionName modelName = new SessionName(name);

        if (startDateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Start Date Time"));
        }

        final String modelStartDateTime = startDateTime;

        if (endDateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "End Date Time"));
        }

        final String modelEndDateTime = endDateTime;

        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Location.class.getSimpleName()));
        }

        final Location modelLocation = new Location(location);

        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Id"));
        }

        final int modelId = Integer.parseInt(id);

        return new Session(modelStartDateTime, modelEndDateTime, modelName, modelLocation, modelId);
    }
}

package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.session.Location;
import seedu.address.model.session.NameBooleanPair;
import seedu.address.model.session.NamePayRatePair;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionName;


/**
 * Jackson-friendly version of {@link seedu.address.model.session.Session}.
 */
public class JsonAdaptedSession {
    static final String MISSING_FIELD_MESSAGE_FORMAT = "Session's %s field is missing!";
    private final String startDateTime;
    private final String endDateTime;
    private final String name;
    private final String id;
    private final String location;
    private final List<JsonAdaptedNameBooleanPair> attendanceMap = new ArrayList<>();
    private final List<JsonAdaptedNamePayRatePair> payRateMap = new ArrayList<>();
    /**
     * Constructs a {@code JsonAdaptedSession} with the given session details.
     */
    @JsonCreator
    public JsonAdaptedSession(@JsonProperty("name") String name,
                             @JsonProperty("startTime") String startDateTime,
                             @JsonProperty("endTime") String endDateTime,
                             @JsonProperty("location") String location,
                             @JsonProperty("id") String id,
                              @JsonProperty("attendanceMap")
                              List<JsonAdaptedNameBooleanPair> attendanceMap) {
        this.name = name;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.location = location;
        this.id = id;
        if (attendanceMap != null) {
            this.attendanceMap.addAll(attendanceMap);
        }
    }
    /**
     * Constructs a {@code JsonAdaptedSession} with the given session.
     */
    public JsonAdaptedSession(Session source) {
        name = source.getName();
        startDateTime = source.getStartDateTime();
        endDateTime = source.getEndDateTime();
        location = source.getLocation().toString();
        id = source.getId();
        attendanceMap.addAll(source.getNameBooleanMap().stream()
                .map(JsonAdaptedNameBooleanPair::new)
                .collect(Collectors.toList()));
        payRateMap.addAll(source.getNamePayRateMap().stream()
                .map(JsonAdaptedNamePayRatePair::new)
                .collect(Collectors.toList()));
    }
    /**
     * Converts this Jackson-friendly adapted session object into the model's {@code Session} object.
     * @throws IllegalValueException if there were any data constraints violated in the adapted session.
     */
    public Session toModelType() throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    SessionName.class.getSimpleName()));
        }
        if (!SessionName.isValidName(name)) {
            throw new IllegalValueException(SessionName.MESSAGE_CONSTRAINTS);
        }
        final SessionName modelName = new SessionName(name);

        if (startDateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    "Start Date Time"));
        }

        final String modelStartDateTime = startDateTime;

        if (endDateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    "End Date Time"));
        }

        final String modelEndDateTime = endDateTime;

        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Location.class.getSimpleName()));
        }

        final Location modelLocation = new Location(location);

        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Id"));
        }

        final int modelId = Integer.parseInt(id);

        ArrayList<NameBooleanPair> modelAttendanceList = new ArrayList<>();
        for (JsonAdaptedNameBooleanPair jsonAdaptedPair : attendanceMap) {
            NameBooleanPair pair = jsonAdaptedPair.toModelType();
            modelAttendanceList.add(pair);
        }

        ArrayList<NamePayRatePair> modelPayRateList = new ArrayList<>();
        for (JsonAdaptedNamePayRatePair jsonAdaptedPair : payRateMap) {
            NamePayRatePair pair = jsonAdaptedPair.toModelType();
            modelPayRateList.add(pair);
        }

        return new Session(modelStartDateTime, modelEndDateTime,
                modelName, modelLocation, modelId, modelAttendanceList, modelPayRateList);
    }
}

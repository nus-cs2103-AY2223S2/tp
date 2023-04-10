package seedu.address.storage;

import org.joda.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.time.Day;
import seedu.address.model.time.TimeBlock;
import seedu.address.model.time.TimePeriod;
import seedu.address.model.time.util.TimeUtil;

/**
 * Jackson-friendly version of {@link TimePeriod}.
 */
public class JsonAdaptedTimePeriod {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "TimePeriod's %s field is missing!";
    protected final int startHour;
    protected final int endHour;
    protected final String day;

    /**
     * Constructs a {@code JsonAdaptedTimePeriod} with the given location details.
     */
    @JsonCreator
    public JsonAdaptedTimePeriod(
            @JsonProperty("startHour") int startHour,
            @JsonProperty("endHour") int endHour,
            @JsonProperty("day") String day) {
        this.startHour = startHour;
        this.endHour = endHour;
        this.day = day;
    }

    /**
     * Converts a given {@code TimePeriod} into this class for Jackson use.
     */
    public JsonAdaptedTimePeriod(TimePeriod timePeriod) {
        startHour = timePeriod.getStartTime().getHourOfDay();
        endHour = timePeriod.getEndTime().getHourOfDay();
        day = timePeriod.getSchoolDay().name();
    }

    /**
     * Converts this Jackson-friendly adapted TimePeriod object into
     * the model's {@code TimePeriod} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TimePeriod toModelType() throws IllegalValueException {
        if (!TimeUtil.isValidStartHour(startHour)) {
            throw new IllegalValueException(
                    String.format(ParserUtil.MESSAGE_INVALID_START_HOUR, LocalTime.class.getSimpleName()));
        }

        final LocalTime modelStartTime = new LocalTime(startHour, 0);

        if (!TimeUtil.isValidEndHour(endHour)) {
            throw new IllegalValueException(
                    String.format(ParserUtil.MESSAGE_INVALID_END_HOUR, LocalTime.class.getSimpleName()));
        }

        final LocalTime modelEndTime = new LocalTime(endHour, 0);

        final Day modelSchoolDay = ParserUtil.parseDay(day);

        return new TimeBlock(modelStartTime, modelEndTime, modelSchoolDay);
    }
}

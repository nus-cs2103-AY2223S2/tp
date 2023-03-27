package seedu.address.storage;

import java.util.Arrays;

import org.joda.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.time.Day;
import seedu.address.model.time.TimeBlock;
import seedu.address.model.time.TimePeriod;
import seedu.address.model.timetable.Timetable;

/**
 * Jackson-friendly version of {@link TimePeriod}.
 */
public class JsonAdaptedTimePeriod {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "TimePeriod's %s field is missing!";
    private static final Integer[] START_TIMINGS = Timetable.START_TIMINGS;
    private static final int EARLIEST_TIMING = START_TIMINGS[0];
    private static final int LATEST_TIMING = START_TIMINGS[START_TIMINGS.length - 1];
    protected final int startTime;
    protected final int endTime;
    protected final String schoolDay;

    /**
     * Constructs a {@code JsonAdaptedTimePeriod} with the given location details.
     */
    @JsonCreator
    public JsonAdaptedTimePeriod(
            @JsonProperty("startTime") int startTime,
            @JsonProperty("endTime") int endTime,
            @JsonProperty("schoolDay") String schoolDay) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.schoolDay = schoolDay;
    }

    /**
     * Converts a given {@code TimePeriod} into this class for Jackson use.
     */
    public JsonAdaptedTimePeriod(TimePeriod timePeriod) {
        startTime = timePeriod.getStartTime().getHourOfDay();
        endTime = timePeriod.getEndTime().getHourOfDay();
        schoolDay = timePeriod.getSchoolDay().name();
    }

    /**
     * Converts this Jackson-friendly adapted TimePeriod object into
     * the model's {@code TimePeriod} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TimePeriod toModelType() throws IllegalValueException {
        if (startTime < EARLIEST_TIMING || startTime > LATEST_TIMING) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalTime.class.getSimpleName()));
        }

        final LocalTime modelStartTime = new LocalTime(startTime);

        if (endTime < EARLIEST_TIMING || endTime > LATEST_TIMING) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalTime.class.getSimpleName()));
        }

        final LocalTime modelEndTime = new LocalTime(endTime);

        if (Arrays.stream(Day.values()).map(Day::name).noneMatch(schoolDay::equals)) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Day.class.getSimpleName()));
        }

        final Day modelSchoolDay = Day.valueOf(schoolDay);

        return new TimeBlock(modelStartTime, modelEndTime, modelSchoolDay);
    }
}

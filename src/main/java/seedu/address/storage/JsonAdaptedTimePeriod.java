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
        if (startHour < 0 || startHour > 23) {
            System.out.println(startHour);
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalTime.class.getSimpleName()));
        }

        final LocalTime modelStartTime = new LocalTime(startHour);

        if (endHour < 0 || endHour > 23) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalTime.class.getSimpleName()));
        }

        final LocalTime modelEndTime = new LocalTime(endHour);

        if (Arrays.stream(Day.values()).map(Day::name).noneMatch(day::equals)) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Day.class.getSimpleName()));
        }

        final Day modelSchoolDay = Day.valueOf(day);

        return new TimeBlock(modelStartTime, modelEndTime, modelSchoolDay);
    }
}

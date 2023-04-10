package seedu.address.storage;

import org.joda.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.commitment.Lesson;
import seedu.address.model.time.Day;
import seedu.address.model.time.TimeBlock;
import seedu.address.model.time.util.TimeUtil;

/**
 * Json object for converting Lessons.
 */
public class JsonAdaptedLesson {
    private final int startHour;
    private final int endHour;
    private final String day;

    /**
     * Constructs a {@code JsonAdaptedLesson} with the given
     * {@code startHour}, {@code endHour}, {@code day}.
     */
    @JsonCreator
    public JsonAdaptedLesson(
            @JsonProperty("startHour") int startHour,
            @JsonProperty("endHour") int endHour,
            @JsonProperty("day") String day) {
        this.startHour = startHour;
        this.endHour = endHour;
        this.day = day;
    }

    /**
     * Converts a given {@code Lesson} into this class for Jackson use.
     */
    public JsonAdaptedLesson(Lesson lesson) {
        startHour = lesson.getStartTime().getHourOfDay();
        endHour = lesson.getEndTime().getHourOfDay();
        day = lesson.getDay().toString();
    }

    /**
     * Converts this Jackson-friendly adapted lesson object into the model's {@code Lesson} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted lesson.
     */
    public Lesson toModelType() throws IllegalValueException {
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

        Day parsedDay = ParserUtil.parseDay(day);
        TimeBlock timeBlock = new TimeBlock(modelStartTime, modelEndTime, parsedDay);
        return new Lesson(timeBlock);
    }
}

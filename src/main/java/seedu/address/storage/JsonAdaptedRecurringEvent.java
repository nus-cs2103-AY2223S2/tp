package seedu.address.storage;

import java.time.DayOfWeek;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.event.Event;
import seedu.address.model.event.RecurringEvent;

/**
 * Jackson-friendly version of {@link RecurringEvent}.
 */
public class JsonAdaptedRecurringEvent {
    private final String eventName;
    private final String dayOfWeek;
    private final String startTime;
    private final String endTime;

    /**
     * Constructs a {@code JsonAdaptedRecurringEvent} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedRecurringEvent(@JsonProperty("eventName") String eventName,
                                     @JsonProperty("dayOfWeek") String dayOfWeek,
                                     @JsonProperty("startTime") String startTime,
                                     @JsonProperty("endTime") String endTime) {
        this.eventName = eventName;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     *  Converts a given {@code RecurringEvent} into this class for Jackson use.
     */
    public JsonAdaptedRecurringEvent(RecurringEvent source) {
        eventName = source.getEventName();
        dayOfWeek = source.getDayOfWeek().name();
        startTime = source.getStartTime().toString();
        endTime = source.getEndTime().toString();
    }

    /**
     * Converts this Jackson-friendly adapted recurring event object into the model's {@code RecurringEvent} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted recurring event.
     */
    public RecurringEvent toModelType() throws IllegalValueException {
        if (!RecurringEvent.isValidEventName(eventName)) {
            throw new IllegalValueException(Event.MESSAGE_CONSTRAINTS_EVENTNAME);
        }

        DayOfWeek day = ParserUtil.parseDayOfWeek(dayOfWeek);
        LocalTime start = ParserUtil.parseTime(startTime);
        LocalTime end = ParserUtil.parseTime(endTime);
        ParserUtil.parsePeriod(start, end);

        return new RecurringEvent(eventName, day, start, end);
    }
}

package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.DateTime;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;


/**
 * Jackson-friendly version of {@link Event}.
 */
class JsonAdaptedEvent {

    private final String eventName;
    private final String startDateTime;
    private final String endDateTime;

    /**
     * Constructs a {@code JsonAdaptedEvent} with the given {@code eventName}, {@code startDateTime}
     * and {@code endDateTime}.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("eventName") String eventName,
                            @JsonProperty("startDateTime") String startDateTime,
                            @JsonProperty("endDateTime") String endDateTime) {
        this.eventName = eventName;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Converts a given {@code Event} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {

        eventName = source.getName().toString();
        startDateTime = source.getStartDateTime().toString();
        endDateTime = source.getEndDateTime().toString();

    }

    /**
     * Converts this Jackson-friendly adapted event object into the model's {@code Event} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted event.
     */
    public Event toModelType() throws IllegalValueException {
        if (!EventName.isValidName(eventName)) {
            throw new IllegalValueException(EventName.MESSAGE_CONSTRAINTS);
        }
        if (!DateTime.isValidDateTime(startDateTime) || !DateTime.isValidDateTime(endDateTime)) {
            throw new IllegalValueException(DateTime.MESSAGE_CONSTRAINTS);
        }
        if (!DateTime.isValidDateRange(startDateTime, endDateTime)) {
            throw new IllegalValueException(DateTime.MESSAGE_CONSTRAINTS);
        }
        EventName nameOfEvent = new EventName(eventName);
        DateTime dateOfStart = new DateTime(startDateTime);
        DateTime dateOfEnd = new DateTime(endDateTime);
        return new Event(nameOfEvent, dateOfStart, dateOfEnd);
    }

}

package seedu.address.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.IsolatedEvent;

/**
 * Jackson-friendly version of {@link IsolatedEvent}.
 */
public class JsonAdaptedIsolatedEvent {
    private final String eventName;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    /**
     * Costructs a {@code JsonAdaptedIsolatedEvent} with the given details.
     */
    @JsonCreator
    public JsonAdaptedIsolatedEvent(@JsonProperty("eventName") String eventName,
                                    @JsonProperty("startDate") LocalDateTime startDate,
                                    @JsonProperty("endDate") LocalDateTime endDate) {
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    /**
     * Converts a given {@code IsolatedEvent} into this class for Jackson use.
     */
    public JsonAdaptedIsolatedEvent(IsolatedEvent isolatedEvent) {
        this.eventName = isolatedEvent.getEventName();
        this.startDate = isolatedEvent.getStartDate();
        this.endDate = isolatedEvent.getEndDate();
    }

    public IsolatedEvent toModelType() throws IllegalValueException {
        return new IsolatedEvent(eventName, startDate, endDate);
    }
}

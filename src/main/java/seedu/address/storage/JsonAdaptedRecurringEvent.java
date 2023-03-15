package seedu.address.storage;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.event.RecurringEvent;

public class JsonAdaptedRecurringEvent {
    private final String eventName;
    private final DayOfWeek dayOfWeek;
    private final LocalTime startTime;
    private final LocalTime endTime;

    @JsonCreator
    public JsonAdaptedRecurringEvent(@JsonProperty("eventName") String eventName,
                                    @JsonProperty("dayOfWeek") DayOfWeek dayOfWeek,
                                    @JsonProperty("startDate") LocalTime startTime,
                                    @JsonProperty("endDate") LocalTime endTime) {
        this.eventName = eventName;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public JsonAdaptedRecurringEvent(RecurringEvent recurringEvent) {
        this.eventName = recurringEvent.getEventName();
        this.dayOfWeek = recurringEvent.getDayOfWeek();
        this.startTime = recurringEvent.getStartTime();
        this.endTime = recurringEvent.getEndTime();

    }

    public RecurringEvent toModelType() {
        return new RecurringEvent(eventName, dayOfWeek, startTime, endTime);
    }
}

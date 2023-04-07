package seedu.internship.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.internship.commons.exceptions.IllegalValueException;
import seedu.internship.model.EventCatalogue;
import seedu.internship.model.ReadOnlyEventCatalogue;
import seedu.internship.model.event.Event;



/**
 * An Immutable EventCatalogue that is serializable to JSON format.
 */
@JsonRootName(value = "TinS")
public class JsonSerializableEventCatalogue {
    public static final String MESSAGE_DUPLICATE_EVENT = "Event list contains duplicate Event(s).";

    private final List<JsonAdaptedEvent> events = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableEventCatalogue} with the given events.
     */
    @JsonCreator
    public JsonSerializableEventCatalogue(@JsonProperty("events") List<JsonAdaptedEvent> events) {
        this.events.addAll(events);
    }

    /**
     * Converts a given {@code ReadOnlyEventCatalogue} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableEventCatalogue}.
     */
    public JsonSerializableEventCatalogue(ReadOnlyEventCatalogue source) {
        this.events.addAll(source.getEventList().stream().map(JsonAdaptedEvent::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this event catalogue into the model's {@code EventCatalogue} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public EventCatalogue toModelType() throws IllegalValueException {
        EventCatalogue eventCatalogue = new EventCatalogue();
        for (JsonAdaptedEvent jsonAdaptedEvent : events) {
            Event event = jsonAdaptedEvent.toModelType();
            if (eventCatalogue.hasEvent(event)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_EVENT);
            }
            eventCatalogue.addEvent(event);
        }
        return eventCatalogue;
    }


}

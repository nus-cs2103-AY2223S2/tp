package seedu.internship.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.internship.commons.exceptions.IllegalValueException;
import seedu.internship.model.event.End;
import seedu.internship.model.event.Event;
import seedu.internship.model.event.EventDescription;
import seedu.internship.model.event.Name;
import seedu.internship.model.event.Start;
import seedu.internship.model.internship.Internship;

/**
 * Jackson-friendly version of {@link Event}.
 */
public class JsonAdaptedEvent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Event's %s field is missing!";

    private final String name;
    private final String start;
    private final String end;
    private final String description;
    private final JsonAdaptedInternship internship;

    /**
     * Constructs a {@code JsonAdaptedEvent} with the given event details.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("name") String name,
                            @JsonProperty("start") String start,
                            @JsonProperty("end") String end,
                            @JsonProperty("description") String description,
                            @JsonProperty("internship") JsonAdaptedInternship internship) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.description = description;
        this.internship = internship;
    }

    /**
     * Converts a given {@code Event} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        this.name = source.getName().name;
        this.start = source.getStart().startDateTime;
        this.end = source.getEnd().endDateTime;
        this.description = source.getDescription().descriptionMessage;
        this.internship = new JsonAdaptedInternship(source.getInternship());
    }

    /**
     * Converts this Jackson-friendly adapted event object into the model's {@code Event} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted internship.
     */
    public Event toModelType() throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (start == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Start.class.getSimpleName()));
        }
        final Start modelStart = new Start(start);
        if (!modelStart.isValidStart()) {
            throw new IllegalValueException(Start.MESSAGE_CONSTRAINTS);
        }

        if (end == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, End.class.getSimpleName()));
        }
        final End modelEnd = new End(end);
        if (!modelEnd.isValidEnd()) {
            throw new IllegalValueException(End.MESSAGE_CONSTRAINTS);
        }

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EventDescription.class.getSimpleName()));
        }
        if (!EventDescription.isValidDescription(description)) {
            throw new IllegalValueException(EventDescription.MESSAGE_CONSTRAINTS);
        }
        final EventDescription modelMessageEventDescription = new EventDescription(description);

        final Internship modelInternship = internship.toModelType();

        return new Event(modelName, modelStart, modelEnd, modelMessageEventDescription, modelInternship);
    }

}

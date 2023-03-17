package seedu.address.storage.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.Event;
import seedu.address.model.event.OneTimeEvent;
import seedu.address.model.event.RecurringEvent;
import seedu.address.model.event.fields.DateTime;
import seedu.address.model.event.fields.Description;
import seedu.address.model.event.fields.Recurrence;
import seedu.address.model.person.Person;

/**
 * Jackson-friendly version of {@link Person}.
 */
public class JsonAdaptedEvent {

    private final String description;
    private final String startDateTime;
    private final String endDateTime;
    private final String recurrence;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("description") String description,
                            @JsonProperty("start") String startDateTime,
                            @JsonProperty("end") String endDateTime,
                            @JsonProperty("recurrence") String recurrence) {
        this.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.recurrence = recurrence;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        this.description = source.getDescription().description;
        this.startDateTime = source.getStartDateTime().dateTime.toString();
        this.endDateTime = source.getEndDateTime().dateTime.toString();
        this.recurrence = source.getRecurrence().interval.getValue();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Event toModelType() throws IllegalValueException {

        if (!Description.isValidDescription(this.description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        Description modelDescription = new Description(this.description);

        if (!DateTime.isValidDateTime(this.startDateTime)) {
            throw new IllegalValueException(DateTime.MESSAGE_CONSTRAINTS);
        }
        DateTime modelStartDateTime = new DateTime(this.startDateTime);

        if (!DateTime.isValidDateTime(this.endDateTime)) {
            throw new IllegalValueException(DateTime.MESSAGE_CONSTRAINTS);
        }
        DateTime modelEndDateTime = new DateTime(this.endDateTime);


        if (!Recurrence.isValidRecurrence(this.recurrence)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        Recurrence modelRecurrence = new Recurrence(this.recurrence);

        if (this.recurrence.equals(Recurrence.NONE_CASE)) {
            return new OneTimeEvent(modelDescription, modelStartDateTime, modelEndDateTime);
        } else {
            return new RecurringEvent(modelDescription, modelStartDateTime, modelEndDateTime, modelRecurrence);
        }
    }

}

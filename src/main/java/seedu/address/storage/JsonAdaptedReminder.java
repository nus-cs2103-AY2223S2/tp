package seedu.address.storage;

import static seedu.address.commons.util.DateTimeUtil.toDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.reminder.Reminder;

/**
 * Jackson-friendly version of {@link Reminder}.
 */
public class JsonAdaptedReminder {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Reminder's %s field is missing!";

    private final String description;
    private final String time;

    /**
     * Constructs a {@code JsonAdaptedReminder} with the given reminder details.
     */
    @JsonCreator
    public JsonAdaptedReminder(@JsonProperty("description") String description, @JsonProperty("time") String time) {
        this.description = description;
        this.time = time;
    }

    /**
     * Converts a given {@code Reminder} into this class for Jackson use.
     */
    public JsonAdaptedReminder(Reminder source) {
        description = source.getDescription();
        time = source.reminderDateTimeToString();
    }

    /**
     * Converts this Jackson-friendly adapted reminder object into the model's {@code Reminder} object.
     */
    public Reminder toModelType() {
        return new Reminder(description, toDateTime(time));
    }
}

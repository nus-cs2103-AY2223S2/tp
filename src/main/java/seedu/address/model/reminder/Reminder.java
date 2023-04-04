package seedu.address.model.reminder;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.commons.util.DateTimeUtil.dateTimeToString;

import java.time.LocalDateTime;

/**
 * Represents a Reminder in the Reminders.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Reminder {
    private final String description;
    private final LocalDateTime reminderDateTime;
    private boolean hasShown;

    /**
     * Constructor to create a Reminder object.
     * @param description Description of the reminder. Description can be left blank, but not null (ie. "").
     * @param reminderDateTime When the reminder will be activated. Cannot be null.
     */
    public Reminder(String description, LocalDateTime reminderDateTime) {
        requireAllNonNull(description, reminderDateTime);
        this.description = description;
        this.reminderDateTime = reminderDateTime;
        this.hasShown = false;
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDateTime getReminderDateTime() {
        return this.reminderDateTime;
    }

    public String reminderDateTimeToString() {
        return dateTimeToString(reminderDateTime);
    }

    public Boolean getHasShown() {
        return this.hasShown;
    }

    public void setHasShown(Boolean b) {
        this.hasShown = b;
    }

    /**
     * Returns Description of reminder
     * @return Description
     */
    public String toString() {
        return getDescription();
    }

}

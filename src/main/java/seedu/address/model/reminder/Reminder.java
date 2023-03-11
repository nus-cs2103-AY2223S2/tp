package seedu.address.model.reminder;

import java.time.LocalDateTime;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.commons.util.DateTimeUtil.dateTimeToString;

/**
 * Represents a Reminder in the Reminders.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Reminder {
    private final String description;
    private final LocalDateTime reminderDateTime;


    public Reminder(String description, LocalDateTime reminderDateTime) {
        requireAllNonNull(description, reminderDateTime);
        this.description = description;
        this.reminderDateTime = reminderDateTime;
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

    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDescription());
        return builder.toString();
    }

}

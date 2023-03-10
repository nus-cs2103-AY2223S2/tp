package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.reminder.Reminder;

/**
 * Unmodifiable view of the reminders
 */
public interface ReadOnlyReminders {

    /**
     * Returns an unmodifiable view of the list of reminders.
     */
    ObservableList<Reminder> getReminders();
}

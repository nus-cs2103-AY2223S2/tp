package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderList;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class Reminders implements ReadOnlyReminders {

    private final ReminderList reminderList;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        reminderList = new ReminderList();
    }

    public Reminders() {}

    public Reminders(ReadOnlyReminders toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    public void setReminders(List<Reminder> reminders) {
        this.reminderList.setReminders(reminders);
    }

    public void resetData(ReadOnlyReminders newData) {
        requireNonNull(newData);
        setReminders(newData.getReminders());
    }

    public void addReminder(Reminder r) {
        reminderList.add(r);
    }

    public void removeReminder(int i) {
        reminderList.remove(i);
    }

    public String toString() {
        return reminderList.asUnmodifiableObservableList().size() + " reminders";
    }

    @Override
    public ObservableList<Reminder> getReminders() {
        return reminderList.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Reminders // instanceof handles nulls
                && reminderList.equals(((Reminders) other).reminderList));
    }
}

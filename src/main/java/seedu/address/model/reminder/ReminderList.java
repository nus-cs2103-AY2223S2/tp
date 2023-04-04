package seedu.address.model.reminder;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of reminders that does not allow nulls.
 * <p>
 * Supports a minimal set of list operations.
 */

public class ReminderList implements Iterable<Reminder> {
    private final ObservableList<Reminder> internalList = FXCollections.observableArrayList();
    private final ObservableList<Reminder> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Adds a reminder to the list.
     */
    public void add(Reminder toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Removes the reminder from the list.
     * The reminder must exist in the list.
     */
    public void remove(int i) {
        requireNonNull(internalList.get(i));
        internalList.remove(i);
    }

    public void setReminderList(ReminderList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code reminderList}.
     */
    public void setReminderList(List<Reminder> reminderList) {
        requireAllNonNull(reminderList);
        internalList.setAll(reminderList);
    }

    public void sortByOldest() {
        internalList.sort(new ReminderListComparator());
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Reminder> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Reminder> iterator() {
        return internalList.iterator();
    }


}

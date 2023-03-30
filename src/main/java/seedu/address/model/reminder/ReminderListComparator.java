package seedu.address.model.reminder;

import java.util.Comparator;

public class ReminderListComparator implements Comparator<Reminder> {
    @Override
    public int compare(Reminder o1, Reminder o2) {
        return o1.getReminderDateTime().compareTo(o2.getReminderDateTime());
    }
}

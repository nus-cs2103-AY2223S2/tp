package seedu.address.model.person;

import java.util.Comparator;

/**
 * A comparator to rank patients by scheduled time
 */
public class TimeComparator implements Comparator<Person> {
    @Override
    public int compare(Person person1, Person person2) {
        if (person1.hasAppointment() && person2.hasAppointment()) {
            return person1.getAppointment().getStartTime().compareTo(person2.getAppointment().getStartTime());
        } else {
            return 0;
        }
    }
}

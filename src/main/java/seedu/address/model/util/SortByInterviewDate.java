package seedu.address.model.util;

import seedu.address.model.person.Person;
import seedu.address.model.person.Status;

import java.util.Comparator;

public class SortByInterviewDate implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        if (p1.getStatus() != Status.SHORTLISTED || p2.getStatus() != Status.SHORTLISTED) {
            return 0;
        } else {
            return p1.getInterviewDateTime().getDateTime().compareTo(p2.getInterviewDateTime().getDateTime());
        }
    }
}

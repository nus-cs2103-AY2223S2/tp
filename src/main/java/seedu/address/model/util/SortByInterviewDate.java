package seedu.address.model.util;

import java.util.Comparator;

import seedu.address.model.person.Person;
import seedu.address.model.person.Status;

/**
 * Comparator that compares the interview date between shortlisted applicants.
 * Does not change the ordering between non-shortlisted applicants.
 */
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

package seedu.address.logic.comparators;

import java.util.Comparator;

import seedu.address.model.person.Person;

/**
 * Email comparator that compares the emails in alphabetical order of two Person instances.
 */
public class EmailComparator implements Comparator<Person> {
    private final int increasingOrder;

    /**
     * Constructor for EmailComparator
     * @param isIncreasing a boolean depending on whether the sorting order is increasing (true) or decreasing (false)
     */
    public EmailComparator(boolean isIncreasing) {
        this.increasingOrder = (isIncreasing) ? 1 : -1;
    }
    public int compare(Person p1, Person p2) {
        return p1.getEmail().toString().compareTo(p2.getEmail().toString()) * increasingOrder;
    }
}

package seedu.address.logic.comparators;

import java.util.Comparator;

import seedu.address.model.person.Person;

/**
 * Name comparator that compares the names in alphabetical order of two Person instances.
 */
public class NameComparator implements Comparator<Person> {
    private final int increasingOrder;

    /**
     * Constructor for NameComparator
     * @param isIncreasing a boolean depending on whether the sorting order is increasing (true) or decreasing (false)
     */
    public NameComparator(boolean isIncreasing) {
        this.increasingOrder = (isIncreasing) ? 1 : -1;
    }
    public int compare(Person p1, Person p2) {
        return p1.getName().toString().compareTo(p2.getName().toString()) * increasingOrder;
    }
}

package seedu.address.logic.comparators;

import java.util.Comparator;

import seedu.address.model.person.Person;

/**
 * Address comparator that compares the address in alphabetical order of two Person instances.
 */
public class AddressComparator implements Comparator<Person> {
    private final int increasingOrder;

    /**
     * Constructor for AddressComparator
     * @param isIncreasing a boolean depending on whether the sorting order is increasing (true) or decreasing (false)
     */
    public AddressComparator(boolean isIncreasing) {
        this.increasingOrder = (isIncreasing) ? 1 : -1;
    }
    public int compare(Person p1, Person p2) {
        return p1.getAddress().toString().compareTo(p2.getAddress().toString()) * increasingOrder;
    }
}

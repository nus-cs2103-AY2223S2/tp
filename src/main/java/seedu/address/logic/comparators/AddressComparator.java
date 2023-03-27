package seedu.address.logic.comparators;

import seedu.address.model.person.Person;

import java.util.Comparator;

public class AddressComparator implements Comparator<Person> {
    private final int increasingOrder;
    public AddressComparator(boolean increasingOrder) {
        this.increasingOrder = (increasingOrder) ? 1 : -1;
    }
    public int compare(Person p1, Person p2) {
        return p1.getAddress().toString().compareTo(p2.getAddress().toString()) * increasingOrder;
    }
}

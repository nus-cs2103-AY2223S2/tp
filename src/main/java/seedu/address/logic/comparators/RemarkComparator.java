package seedu.address.logic.comparators;

import java.util.Comparator;

import seedu.address.model.person.Person;

/**
 * Remark comparator that compares the remarks in alphabetical order of two Person instances.
 */
public class RemarkComparator implements Comparator<Person> {
    private final int increasingOrder;

    /**
     * Constructor for RemarkComparator
     * @param isIncreasing a boolean depending on whether the sorting order is increasing (true) or decreasing (false)
     */
    public RemarkComparator(boolean isIncreasing) {
        this.increasingOrder = (isIncreasing) ? 1 : -1;
    }
    public int compare(Person p1, Person p2) {
        return p1.getRemark().toString().compareTo(p2.getRemark().toString()) * increasingOrder;
    }
}

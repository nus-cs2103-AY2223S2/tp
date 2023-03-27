package seedu.address.logic.comparators;

import java.util.Comparator;

import seedu.address.model.person.Person;


/**
 * Performance comparator that compares the performances of two Person instances.
 */
public class PerformanceComparator implements Comparator<Person> {
    private final int increasingOrder;

    /**
     * Constructor for PerformanceComparator
     * @param isIncreasing a boolean depending on whether the sorting order is increasing (true) or decreasing (false)
     */
    public PerformanceComparator(boolean isIncreasing) {
        this.increasingOrder = (isIncreasing) ? 1 : -1;
    }

    /**
     * Compare between two Person instances to check priority.
     * @param p1 the first object to be compared.
     * @param p2 the second object to be compared.
     * @return 1 if p2's priority is higher, -1 if p1's priority is higher, or 0 otherwise.
     */
    public int compare(Person p1, Person p2) {
        int first = p1.getPerformance().calculateUrgency();
        int second = p2.getPerformance().calculateUrgency();
        if (second > first) {
            return 1 * increasingOrder;
        } else if (second < first) {
            return -1 * increasingOrder;
        } else {
            return 0;
        }
    }
}

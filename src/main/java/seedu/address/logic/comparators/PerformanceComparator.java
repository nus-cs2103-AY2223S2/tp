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

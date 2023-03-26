package seedu.address.logic.comparators;

import seedu.address.model.person.Person;

import java.util.Comparator;

public class PerformanceComparator implements Comparator<Person> {
    private final int increasingOrder;

    public PerformanceComparator(boolean increasingOrder) {
        this.increasingOrder = (increasingOrder) ? 1 : -1;
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
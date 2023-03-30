package seedu.dengue.logic.comparators;

import java.util.Comparator;

import seedu.dengue.model.person.Postal;

/**
 * Comparator for the class {@code Postal}
 */
public class PostalComparator implements Comparator<Postal> {

    /**
     * Compares two {@code Postal} objects.
     * @param p1 the first object to be compared.
     * @param p2 the second object to be compared.
     * @return A negative integer, zero, or a positive integer if the first argument is
     *         less than, equal to, or greater than the second.
     */
    @Override
    public int compare(Postal p1, Postal p2) {
        return p1.value.compareTo(p2.value);
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }
}

package seedu.dengue.logic.comparators;

import java.util.Comparator;

import seedu.dengue.model.person.Age;

/**
 * Comparator for the class {@code Age}
 */
public class AgeComparator implements Comparator<Age> {

    /**
     * Compares two {@code Age} objects.
     * @param a1 the first object to be compared.
     * @param a2 the second object to be compared.
     * @return A negative integer, zero, or a positive integer if the first argument is
     *         less than, equal to, or greater than the second.
     */
    @Override
    public int compare(Age a1, Age a2) {
        int firstAge = Integer.parseInt(a1.value);
        int secondAge = Integer.parseInt(a2.value);
        return firstAge - secondAge;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }
}

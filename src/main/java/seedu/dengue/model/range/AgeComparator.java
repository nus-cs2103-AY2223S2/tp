package seedu.dengue.model.range;

import seedu.dengue.model.person.Age;

/**
 * Comparator for the class {@code Age}
 */
public class AgeComparator extends GeneralComparator<Age> {

    /**
     * Compares two {@code Age} objects.
     * @param a1 the first object to be compared.
     * @param a2 the second object to be compared.
     * @return A negative integer, zero, or a positive integer if the first argument is
     *         less than, equal to, or greater than the second.
     */
    public int compare(Age a1, Age a2) {
        int firstAge = Integer.valueOf(a1.value);
        int secondAge = Integer.valueOf(a2.value);
        return firstAge - secondAge;
    }
}

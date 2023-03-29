package seedu.dengue.logic.comparators;

import java.util.Comparator;

import seedu.dengue.model.person.Age;

/**
 * A Comparator that compares between the ages of two persons.
 */
public class AgeComparator implements Comparator<Age> {

    /**
     * Compares two ages.
     * @param a1 the first object to be compared.
     * @param a2 the second object to be compared.
     * @return A negative integer if a1 < a2, positive integer if a1 > a2, else 0.
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

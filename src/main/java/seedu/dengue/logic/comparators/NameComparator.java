package seedu.dengue.logic.comparators;

import java.util.Comparator;

import seedu.dengue.model.person.Name;

/**
 * Comparator for the class {@Date}
 */
public class NameComparator implements Comparator<Name> {
    /**
     * Compares two {@Name} objects.
     * @param n1 the first object to be compared.
     * @param n2 the second object to be compared.
     * @return An integer, which is positive if n2 > n1, zero if n1 == n2 and negative if n1 < n2.
     */
    public int compare(Name n1, Name n2) {
        return n1.fullName.compareTo(n2.fullName);
    }

}

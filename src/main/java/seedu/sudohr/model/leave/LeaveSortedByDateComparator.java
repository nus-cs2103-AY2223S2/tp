package seedu.sudohr.model.leave;

import java.util.Comparator;

/**
 * Compares a Leave{@code l1}'s with another Leave{@code l2} to leaves sort by date.
 */
public class LeaveSortedByDateComparator implements Comparator<Leave> {
    @Override
    public int compare(Leave l1, Leave l2) {
        return l1.compareTo(l2);
    }
}

package seedu.address.model.jobs.sorters;

import seedu.address.model.jobs.DeliveryJob;

/**
 * Helper class implementing Comparator
 * Sort by job's earning
 */
public class SortbyEarning extends OrderedSorter {

    public SortbyEarning(boolean asc) {
        super(asc);
    }

    /**
     * Method sort by Delivered (increasing)
     * @param a the first job to be compared.
     * @param b the second job to be compared.
     * @return
     */
    public int compare(DeliveryJob a, DeliveryJob b) {
        if (a.getEarning().isPresent() && b.getEarning().isPresent()) {
            return isAsc() ? a.getEarning().get().compareTo(b.getEarning().get())
                    : b.getEarning().get().compareTo(a.getEarning().get());
        } else if (a.getEarning().isPresent() && b.getEarning().isEmpty()) {
            return isAsc() ? 1 : -1;
        } else {
            return isAsc() ? -1 : 1;
        }
    }
}

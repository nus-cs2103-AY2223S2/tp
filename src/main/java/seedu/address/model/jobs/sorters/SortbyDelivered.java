package seedu.address.model.jobs.sorters;

import seedu.address.model.jobs.DeliveryJob;

/**
 * Helper class implementing Comparator
 * Sort by job's delivered status
 */
public class SortbyDelivered extends OrderedSorter {

    public SortbyDelivered(boolean asc) {
        super(asc);
    }

    /**
     * Method sort by Delivered (increasing)
     * @param a the first job to be compared.
     * @param b the second job to be compared.
     * @return
     */
    public int compare(DeliveryJob a, DeliveryJob b) {
        if (a.getDeliveredStatus() == b.getDeliveredStatus()) {
            return 0;
        } else {
            if (a.getDeliveredStatus()) {
                return isAsc() ? 1 : -1;
            } else {
                return isAsc() ? -1 : 1;
            }
        }
    }
}

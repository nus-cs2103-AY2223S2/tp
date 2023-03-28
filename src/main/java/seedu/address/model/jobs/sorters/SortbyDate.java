package seedu.address.model.jobs.sorters;

import seedu.address.model.jobs.DeliveryJob;

/**
 * Helper class implementing Comparator
 * Sort by job's scheduled date
 */
public class SortbyDate extends OrderedSorter {

    public SortbyDate(boolean asc) {
        super(asc);
    }

    /**
     * Method sort by Delivered (increasing)
     *
     * @param a the first job to be compared.
     * @param b the second job to be compared.
     * @return
     */
    public int compare(DeliveryJob a, DeliveryJob b) {
        if (a.getDeliveryDate().isPresent() && b.getDeliveryDate().isPresent()) {
            return isAsc() ? compareByDate(a, b)
                    : compareByDate(b, a);
        } else if (a.getDeliveryDate().isPresent() && b.getDeliveryDate().isEmpty()) {
            return isAsc() ? 1 : -1;
        } else {
            return isAsc() ? -1 : 1;
        }
    }

    private int compareByDate(DeliveryJob a, DeliveryJob b) {
        if (a.getDeliveryDate().get().getDate().compareTo(
                b.getDeliveryDate().get().getDate()) == 0) {
            return a.getSlot() - b.getSlot();
        } else {
            return a.getDeliveryDate().get().getDate().compareTo(
                    b.getDeliveryDate().get().getDate());
        }
    }
}

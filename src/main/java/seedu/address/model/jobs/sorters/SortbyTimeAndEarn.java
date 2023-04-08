package seedu.address.model.jobs.sorters;

import java.util.Comparator;
import java.util.NoSuchElementException;

import seedu.address.model.jobs.DeliveryJob;

/**
 * Helper class implementing Comparator
 * Sort by job's scheduled timing
 */
public class SortbyTimeAndEarn implements Comparator<DeliveryJob> {

    /**
     * Method sort by time (increasing)
     * If time is the same, sort by earning (decreasing)
     * @param a the first job to be compared.
     * @param b the second job to be compared.
     * @return difference between jobs' timing/earning
     */
    public int compare(DeliveryJob a, DeliveryJob b) {
        try {
            if (compareByDate(a, b) != 0) {
                return compareByDate(a, b);
            } else {
                return a.getEarning().get().compareTo(b.getEarning().get());
            }
        } catch (NoSuchElementException e) {
            if (b.isScheduled()) {
                return 1;
            } else if (a.isScheduled()) {
                return -1;
            } else if ((a.hasDateOrSlot()) && (!b.hasDateOrSlot())) {
                return -1;
            } else if ((b.hasDateOrSlot()) && (!a.hasDateOrSlot())) {
                return 1;
            } else if (a.hasEarning() && b.hasEarning()) {
                return a.getEarning().get().compareTo(b.getEarning().get());
            } else if (b.hasEarning()) {
                return 1;
            } else if (a.hasEarning()) {
                return -1;
            }
            return 0;
        }
    }

    /**
     * Sorts by date
     */
    private int compareByDate(DeliveryJob a, DeliveryJob b) {
        if (a.getDeliveryDate().isPresent() && b.getDeliveryDate().isPresent()) {
            if (a.getDate().compareTo(b.getDate()) == 0) {
                return a.getSlot() - b.getSlot();
            } else {
                return a.getDate().compareTo(b.getDate());
            }
        } else {
            return 0;
        }
    }
}

package seedu.address.model.jobs.sorters;

import java.util.Comparator;

import seedu.address.model.jobs.DeliveryJob;

abstract class OrderedSorter implements Comparator<DeliveryJob> {
    private boolean isAsc;

    public OrderedSorter(boolean isAsc) {
        this.isAsc = isAsc;
    }

    public boolean isAsc() {
        return isAsc;
    }
}

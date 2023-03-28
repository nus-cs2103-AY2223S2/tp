package seedu.address.model.jobs.sorters;

import java.util.Comparator;

import seedu.address.model.jobs.DeliveryJob;

abstract class OrderedSorter implements Comparator<DeliveryJob> {
    private boolean asc;

    public OrderedSorter(boolean asc) {
        this.asc = asc;
    }

    public boolean isAsc() {
        return asc;
    }
}

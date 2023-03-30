package seedu.address.model.jobs.predicate;

import java.util.function.Predicate;

import seedu.address.model.jobs.DeliveryJob;

/**
 * Predicate for sender id.
 */
public class DeliveryJobContainsSenderIdPredicate implements Predicate<DeliveryJob> {
    private final String toFind;

    public DeliveryJobContainsSenderIdPredicate(String toFind) {
        this.toFind = toFind;
    }

    @Override
    public boolean test(DeliveryJob job) {
        if (job.getSenderId().toUpperCase().contains(toFind.toUpperCase())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return toFind;
    }
}

package seedu.address.model.jobs.predicate;

import java.util.function.Predicate;

import seedu.address.model.jobs.DeliveryJob;

/**
 * Predicate for delivery status.
 */
public class DeliveryJobContainsStatusPredicate implements Predicate<DeliveryJob> {
    private final boolean toFind;

    public DeliveryJobContainsStatusPredicate(boolean toFind) {
        this.toFind = toFind;
    }

    @Override
    public boolean test(DeliveryJob job) {
        if (job.getDeliveredStatus() == toFind) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return Boolean.toString(toFind);
    }
}

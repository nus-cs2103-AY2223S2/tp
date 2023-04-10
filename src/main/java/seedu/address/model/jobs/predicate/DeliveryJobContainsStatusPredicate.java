package seedu.address.model.jobs.predicate;

import java.util.function.Predicate;

import seedu.address.model.jobs.DeliveryJob;

/**
 * Predicate for delivery status.
 */
public class DeliveryJobContainsStatusPredicate implements Predicate<DeliveryJob> {
    private final boolean isDelivered;

    public DeliveryJobContainsStatusPredicate(boolean isDelivered) {
        this.isDelivered = isDelivered;
    }

    @Override
    public boolean test(DeliveryJob job) {
        if (job.getDeliveredStatus() == isDelivered) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return Boolean.toString(isDelivered);
    }
}

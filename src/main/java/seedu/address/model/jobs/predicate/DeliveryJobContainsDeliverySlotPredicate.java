package seedu.address.model.jobs.predicate;

import java.util.function.Predicate;

import seedu.address.model.jobs.DeliveryJob;
import seedu.address.model.jobs.DeliverySlot;

/**
 * Predicate for delivery slot.
 */
public class DeliveryJobContainsDeliverySlotPredicate implements Predicate<DeliveryJob> {
    private final DeliverySlot toFind;

    public DeliveryJobContainsDeliverySlotPredicate(DeliverySlot toFind) {
        this.toFind = toFind;
    }

    @Override
    public boolean test(DeliveryJob job) {
        if (job.getDeliverySlot().isPresent()) {
            if (job.getDeliverySlot().get().equals(toFind)) {
                return true;
            }
        } else {
            if (toFind.equals(DeliverySlot.placeholder())) {
                if (job.getDeliverySlot().isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return toFind.toString();
    }
}

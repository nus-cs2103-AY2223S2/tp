package seedu.address.model.jobs.predicate;

import java.util.function.Predicate;

import seedu.address.model.jobs.DeliveryDate;
import seedu.address.model.jobs.DeliveryJob;

/**
 * Predicate for delivery date.
 */
public class DeliveryJobContainsDeliveryDatePredicate implements Predicate<DeliveryJob> {
    private final DeliveryDate toFind;

    public DeliveryJobContainsDeliveryDatePredicate(DeliveryDate toFind) {
        this.toFind = toFind;
    }

    @Override
    public boolean test(DeliveryJob job) {
        if (job.getDeliveryDate().isPresent()) {
            if (job.getDeliveryDate().get().equals(toFind)) {
                return true;
            }
        } else {
            if (toFind.equals(DeliveryDate.placeholder())) {
                if (job.getDeliveryDate().isEmpty()) {
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

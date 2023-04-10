package seedu.address.model.jobs.predicate;

import java.util.function.Predicate;

import seedu.address.model.jobs.DeliveryJob;

/**
 * Predicate for recipient id.
 */
public class DeliveryJobContainsRecipientIdPredicate implements Predicate<DeliveryJob> {
    private final String toFind;

    public DeliveryJobContainsRecipientIdPredicate(String toFind) {
        this.toFind = toFind;
    }

    @Override
    public boolean test(DeliveryJob job) {
        if (job.getRecipientId().toUpperCase().contains(toFind.toUpperCase())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return toFind;
    }
}

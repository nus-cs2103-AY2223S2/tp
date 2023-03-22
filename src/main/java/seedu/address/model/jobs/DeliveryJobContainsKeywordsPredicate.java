package seedu.address.model.jobs;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class DeliveryJobContainsKeywordsPredicate implements Predicate<DeliveryJob> {
    private final DeliveryJob toFind;

    public DeliveryJobContainsKeywordsPredicate(DeliveryJob toFind) {
        this.toFind = toFind;
    }

    @Override
    public boolean test(DeliveryJob job) {
        if (job.getJobId().equals(toFind.getJobId())) {
            return true;
        }

        if (job.getRecipientId().equals(toFind.getRecipientId())) {
            return true;
        }

        if (job.getSenderId().equals(toFind.getSenderId())) {
            return true;
        }

        if (job.getEarning().equals(toFind.getEarning())) {
            return true;
        }

        if (toFind.getDeliveryDate().isPresent()) {
            if (job.getDeliveryDate().get().equals(toFind.getDeliveryDate().get())) {
                return true;
            }
        }

        if (toFind.getDeliverySlot().isPresent()) {
            if (job.getDeliverySlot().get().equals(toFind.getDeliverySlot().get())) {
                return true;
            }
        }

        if (toFind.getDeliveredStatus() != null) {
            if (job.getDeliveredStatus() == toFind.getDeliveredStatus()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeliveryJobContainsKeywordsPredicate // instanceof handles nulls
                && toFind.equals(((DeliveryJobContainsKeywordsPredicate) other).toFind)); // state check
    }
}

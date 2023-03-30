package seedu.address.model.jobs;

import java.util.function.Predicate;

/**
 * Tests that a {@code DeliveryJob}'s attributes matches any of the keywords given.
 */
public class DeliveryJobContainsKeywordsPredicate implements Predicate<DeliveryJob> {
    private final DeliveryJob toFind;

    public DeliveryJobContainsKeywordsPredicate(DeliveryJob toFind) {
        this.toFind = toFind;
    }

    @Override
    public boolean test(DeliveryJob job) {
        if (toFind.getJobId() != null) {
            if (job.getJobId().toUpperCase().contains(toFind.getJobId().toUpperCase())) {
                return true;
            }
        }

        if (toFind.getRecipientId() != null) {
            if (job.getRecipientId().contains(toFind.getRecipientId())) {
                return true;
            }
        }

        if (toFind.getSenderId() != null) {
            if (job.getSenderId().contains(toFind.getSenderId())) {
                return true;
            }
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

        if (toFind.getEarning().isPresent()) {
            if (Double.compare(job.getEarning().get().getEarning(), toFind.getEarning().get().getEarning()) == 0) {
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

    @Override
    public String toString() {
        return toFind.toString();
    }
}

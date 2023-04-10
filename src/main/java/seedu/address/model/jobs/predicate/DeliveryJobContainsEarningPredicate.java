package seedu.address.model.jobs.predicate;

import java.util.function.Predicate;

import seedu.address.model.jobs.DeliveryJob;
import seedu.address.model.jobs.Earning;

/**
 * Predicate for earning.
 */
public class DeliveryJobContainsEarningPredicate implements Predicate<DeliveryJob> {
    private final Earning toFind;

    public DeliveryJobContainsEarningPredicate(Earning toFind) {
        this.toFind = toFind;
    }

    @Override
    public boolean test(DeliveryJob job) {
        if (Double.compare(job.getEarning().get().getEarning(), toFind.getEarning()) == 0) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return toFind.toString();
    }
}

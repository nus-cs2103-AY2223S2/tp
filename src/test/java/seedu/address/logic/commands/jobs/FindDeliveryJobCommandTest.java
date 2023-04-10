package seedu.address.logic.commands.jobs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.jobs.DeliveryJob;
import seedu.address.model.jobs.predicate.DeliveryJobContainsDeliveryDatePredicate;
import seedu.address.model.jobs.predicate.DeliveryJobContainsDeliverySlotPredicate;
import seedu.address.model.jobs.predicate.DeliveryJobContainsEarningPredicate;
import seedu.address.model.jobs.predicate.DeliveryJobContainsJobIdPredicate;
import seedu.address.model.jobs.predicate.DeliveryJobContainsRecipientIdPredicate;
import seedu.address.model.jobs.predicate.DeliveryJobContainsSenderIdPredicate;
import seedu.address.model.jobs.predicate.DeliveryJobContainsStatusPredicate;
import seedu.address.testutil.TypicalDeliveryJobs;

public class FindDeliveryJobCommandTest {

    @Test
    public void equals() {
        DeliveryJob toFind = TypicalDeliveryJobs.JOBA;
        List<Predicate<DeliveryJob>> preds = Arrays.asList(
                new DeliveryJobContainsDeliveryDatePredicate(toFind.getDeliveryDate().get()),
                new DeliveryJobContainsDeliverySlotPredicate(toFind.getDeliverySlot().get()),
                new DeliveryJobContainsEarningPredicate(toFind.getEarning().get()),
                new DeliveryJobContainsJobIdPredicate(toFind.getJobId()),
                new DeliveryJobContainsRecipientIdPredicate(toFind.getRecipientId()),
                new DeliveryJobContainsSenderIdPredicate(toFind.getSenderId()),
                new DeliveryJobContainsStatusPredicate(toFind.getDeliveredStatus()));

        List<FindDeliveryJobCommand> finds = new ArrayList<>();
        preds.forEach(pred -> {
            finds.add(new FindDeliveryJobCommand(pred));
        });

        // same object -> returns true
        finds.forEach(find -> {
            assertTrue(find.equals(find));
        });

        // same values -> returns true
        for (int i = 0; i < finds.size(); i++) {
            FindDeliveryJobCommand copy = new FindDeliveryJobCommand(preds.get(i));
            assertTrue(finds.get(i).equals(copy));
        }

        // different types -> returns false
        finds.forEach(find -> {
            assertFalse(find.equals(1));
        });

        // null -> returns false
        finds.forEach(find -> {
            assertFalse(find.equals(null));
        });

        // different job -> returns false
        for (int i = 1; i < finds.size(); i++) {
            FindDeliveryJobCommand prev = new FindDeliveryJobCommand(preds.get(i - 1));
            assertFalse(finds.get(i).equals(prev));
        }
    }

}

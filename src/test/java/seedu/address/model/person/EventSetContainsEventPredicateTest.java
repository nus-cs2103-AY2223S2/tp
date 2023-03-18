package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalEvents.CARNIVAL;
import static seedu.address.testutil.TypicalEvents.WEDDING_DINNER;

import org.junit.jupiter.api.Test;

import seedu.address.model.event.Event;
import seedu.address.testutil.PersonBuilder;

public class EventSetContainsEventPredicateTest {
    @Test
    public void equals() {
        Event firstPredicateTargetEvent = WEDDING_DINNER;
        Event secondPredicateTargetEvent = CARNIVAL;

        EventSetContainsEventPredicate firstPredicate = new EventSetContainsEventPredicate(firstPredicateTargetEvent);
        EventSetContainsEventPredicate secondPredicate = new EventSetContainsEventPredicate(secondPredicateTargetEvent);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EventSetContainsEventPredicate firstPredicateCopy =
                new EventSetContainsEventPredicate(firstPredicateTargetEvent);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different event -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_personEventSetContainsEvent_returnsTrue() {
        EventSetContainsEventPredicate predicate = new EventSetContainsEventPredicate(WEDDING_DINNER);
        assertTrue(predicate.test(new PersonBuilder().withEventSet(WEDDING_DINNER).build()));

    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero event
        EventSetContainsEventPredicate predicate = new EventSetContainsEventPredicate(null);
        assertFalse(predicate.test(new PersonBuilder().withEventSet(WEDDING_DINNER).build()));

        // Non-matching event set
        predicate = new EventSetContainsEventPredicate(CARNIVAL);
        assertFalse(predicate.test(new PersonBuilder().withEventSet(WEDDING_DINNER).build()));
    }
}

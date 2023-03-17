package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.ElderlyBuilder;
import seedu.address.testutil.VolunteerBuilder;

class NricContainsKeywordPredicateTest {
    @Test
    public void equals() {
        NricContainsKeywordPredicate<Person> firstPredicate = new NricContainsKeywordPredicate<>("S7651974G");
        NricContainsKeywordPredicate<Person> secondPredicate = new NricContainsKeywordPredicate<>("123A");

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NricContainsKeywordPredicate<Person> firstPredicateCopy = new NricContainsKeywordPredicate<>("S7651974G");
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different nric -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nricContainsKeyword_returnsTrue() {
        // exact match
        NricContainsKeywordPredicate<Person> predicate = new NricContainsKeywordPredicate<>("S7651974G");
        assertTrue(predicate.test(new ElderlyBuilder().withNric("S7651974G").build()));
        assertTrue(predicate.test(new VolunteerBuilder().withNric("S7651974G").build()));

        // case insensitive
        assertTrue(predicate.test(new ElderlyBuilder().withNric("s7651974g").build()));
        assertTrue(predicate.test(new VolunteerBuilder().withNric("s7651974g").build()));

        // substring match
        predicate = new NricContainsKeywordPredicate<>("123A");
        assertTrue(predicate.test(new ElderlyBuilder().withNric("T1234123A").build()));
        assertTrue(predicate.test(new VolunteerBuilder().withNric("T1234123A").build()));
    }

    @Test
    public void test_nricDoesNotContainKeyword_returnsFalse() {
        NricContainsKeywordPredicate<Person> predicate = new NricContainsKeywordPredicate<>("S7651974G");
        assertFalse(predicate.test(new ElderlyBuilder().withNric("T1651974G").build()));
        assertFalse(predicate.test(new VolunteerBuilder().withNric("T1651974G").build()));
    }
}

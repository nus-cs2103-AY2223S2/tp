package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.ElderlyBuilder;
import seedu.address.testutil.VolunteerBuilder;

public class NameContainsKeywordPredicateTest {
    @Test
    public void equals() {
        NameContainsKeywordPredicate<Person> firstPredicate = new NameContainsKeywordPredicate<>("John");
        NameContainsKeywordPredicate<Person> secondPredicate = new NameContainsKeywordPredicate<>("Sally");

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordPredicate<Person> firstPredicateCopy = new NameContainsKeywordPredicate<>("John");
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different name -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeyword_returnsTrue() {
        // exact match
        NameContainsKeywordPredicate<Person> predicate = new NameContainsKeywordPredicate<>("John");
        assertTrue(predicate.test(new ElderlyBuilder().withName("John").build()));
        assertTrue(predicate.test(new VolunteerBuilder().withName("John").build()));

        // substring match
        assertTrue(predicate.test(new ElderlyBuilder().withName("John Doe").build()));
        assertTrue(predicate.test(new VolunteerBuilder().withName("John Doe").build()));

        // case insensitive
        assertTrue(predicate.test(new ElderlyBuilder().withName("jOHn").build()));
        assertTrue(predicate.test(new VolunteerBuilder().withName("jOHn").build()));
    }

    @Test
    public void test_nameDoesNotContainKeyword_returnsFalse() {
        NameContainsKeywordPredicate<Person> predicate = new NameContainsKeywordPredicate<>("John");
        assertFalse(predicate.test(new ElderlyBuilder().withName("Sally").build()));
        assertFalse(predicate.test(new VolunteerBuilder().withName("Sally").build()));
    }
}

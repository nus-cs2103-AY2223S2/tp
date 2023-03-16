package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.ElderlyBuilder;
import seedu.address.testutil.VolunteerBuilder;

class EmailContainsKeywordPredicateTest {
    @Test
    public void equals() {
        EmailContainsKeywordPredicate<Person> firstPredicate = new EmailContainsKeywordPredicate<>("Test@gamil.com");
        EmailContainsKeywordPredicate<Person> secondPredicate = new EmailContainsKeywordPredicate<>("e@hotmail");

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EmailContainsKeywordPredicate<Person> firstPredicateCopy =
                new EmailContainsKeywordPredicate<>("Test@gamil.com");
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different email -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_emailContainsKeyword_returnsTrue() {
        // exact match
        EmailContainsKeywordPredicate<Person> predicate = new EmailContainsKeywordPredicate<>("e@hotmail");
        assertTrue(predicate.test(new ElderlyBuilder().withEmail("e@hotmail").build()));
        assertTrue(predicate.test(new VolunteerBuilder().withEmail("e@hotmail").build()));

        // substring match
        assertTrue(predicate.test(new ElderlyBuilder().withEmail("JohnDoe@hotmail.com").build()));
        assertTrue(predicate.test(new VolunteerBuilder().withEmail("JohnDoe@hotmail.com").build()));

        // case insensitive
        assertTrue(predicate.test(new ElderlyBuilder().withEmail("E@hoTMail").build()));
        assertTrue(predicate.test(new VolunteerBuilder().withEmail("E@hoTMail").build()));
    }

    @Test
    public void test_emailDoesNotContainKeyword_returnsFalse() {
        EmailContainsKeywordPredicate<Person> predicate = new EmailContainsKeywordPredicate<>("Test@gamil.com");
        assertFalse(predicate.test(new ElderlyBuilder().withEmail("tree@gamil.com").build()));
        assertFalse(predicate.test(new VolunteerBuilder().withEmail("tree@gamil.com").build()));
    }
}

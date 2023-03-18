package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.ElderlyBuilder;
import seedu.address.testutil.VolunteerBuilder;

class PhoneContainsDigitsPredicateTest {
    @Test
    public void equals() {
        PhoneContainsDigitsPredicate<Person> firstPredicate = new PhoneContainsDigitsPredicate<>("81234567");
        PhoneContainsDigitsPredicate<Person> secondPredicate = new PhoneContainsDigitsPredicate<>("7896");

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PhoneContainsDigitsPredicate<Person> firstPredicateCopy = new PhoneContainsDigitsPredicate<>("81234567");
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different phone number -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_phoneContainsDigits_returnsTrue() {
        // exact match
        PhoneContainsDigitsPredicate<Person> predicate = new PhoneContainsDigitsPredicate<>("7896");
        assertTrue(predicate.test(new ElderlyBuilder().withPhone("7896").build()));
        assertTrue(predicate.test(new VolunteerBuilder().withPhone("7896").build()));

        // substring match
        assertTrue(predicate.test(new ElderlyBuilder().withPhone("98987896").build()));
        assertTrue(predicate.test(new VolunteerBuilder().withPhone("98987896").build()));
    }

    @Test
    public void test_phoneDoesNotContainDigits_returnsFalse() {
        PhoneContainsDigitsPredicate<Person> predicate = new PhoneContainsDigitsPredicate<>("81234567");
        assertFalse(predicate.test(new ElderlyBuilder().withPhone("89586567").build()));
        assertFalse(predicate.test(new VolunteerBuilder().withPhone("89586567").build()));
    }
}

package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.ElderlyBuilder;
import seedu.address.testutil.VolunteerBuilder;

class AgeIsEqualPredicateTest {
    @Test
    public void equals() {
        AgeIsEqualPredicate<Person> firstPredicate = new AgeIsEqualPredicate<>("65");
        AgeIsEqualPredicate<Person> secondPredicate = new AgeIsEqualPredicate<>("37");

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AgeIsEqualPredicate<Person> firstPredicateCopy = new AgeIsEqualPredicate<>("65");
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different age -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_ageIsEqual_returnsTrue() {
        AgeIsEqualPredicate<Person> predicate = new AgeIsEqualPredicate<>("37");
        assertTrue(predicate.test(new ElderlyBuilder().withAge("37").build()));
        assertTrue(predicate.test(new VolunteerBuilder().withAge("37").build()));
    }

    @Test
    public void test_ageIsNotEqual_returnsFalse() {
        // Non-matching age
        AgeIsEqualPredicate<Person> predicate = new AgeIsEqualPredicate<>("37");
        assertFalse(predicate.test(new ElderlyBuilder().withAge("45").build()));
        assertFalse(predicate.test(new VolunteerBuilder().withAge("45").build()));

        // Partially matching age
        assertFalse(predicate.test(new ElderlyBuilder().withAge("32").build()));
        assertFalse(predicate.test(new ElderlyBuilder().withAge("27").build()));
        assertFalse(predicate.test(new VolunteerBuilder().withAge("32").build()));
        assertFalse(predicate.test(new VolunteerBuilder().withAge("27").build()));
    }
}

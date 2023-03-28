package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.ElderlyBuilder;
import seedu.address.testutil.VolunteerBuilder;


class BirthDateEqualPredicateTest {
    @Test
    public void equals() {
        BirthDateEqualPredicate<Person> first = new BirthDateEqualPredicate<>("2000-01-01");
        BirthDateEqualPredicate<Person> second = new BirthDateEqualPredicate<>("1990-01-01");

        assertTrue(first.equals(first));

        BirthDateEqualPredicate<Person> firstCopy = new BirthDateEqualPredicate<>("2000-01-01");
        assertTrue(first.equals(firstCopy));

        // different types -> returns false
        assertFalse(first.equals(1));

        // null -> returns false
        assertFalse(first.equals(null));

        // different address -> returns false
        assertFalse(first.equals(second));

    }

    @Test
    public void test_birthDateEquals_returnsTrue() {
        // exact match
        BirthDateEqualPredicate<Person> predicate = new BirthDateEqualPredicate<>("1989-02-01");
        assertTrue(predicate.test(new ElderlyBuilder().withBirthDate("1989-02-01").build()));
        assertTrue(predicate.test(new VolunteerBuilder().withBirthDate("1989-02-01").build()));
    }

    @Test
    public void test_birthDateEquals_returnsFalse() {
        BirthDateEqualPredicate<Person> predicate = new BirthDateEqualPredicate<>("1989-02-01");
        assertFalse(predicate.test(new ElderlyBuilder().withBirthDate("1989-03-01").build()));
        assertFalse(predicate.test(new VolunteerBuilder().withBirthDate("1989-03-01").build()));

    }

}

package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.ElderlyBuilder;
import seedu.address.testutil.VolunteerBuilder;

class AddressContainsKeywordPredicateTest {
    @Test
    public void equals() {
        AddressContainsKeywordPredicate<Person> firstPredicate = new AddressContainsKeywordPredicate<>("Bishan");
        AddressContainsKeywordPredicate<Person> secondPredicate = new AddressContainsKeywordPredicate<>("BLK 543");

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AddressContainsKeywordPredicate<Person> firstPredicateCopy = new AddressContainsKeywordPredicate<>("Bishan");
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different address -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_addressContainsKeyword_returnsTrue() {
        // exact match
        AddressContainsKeywordPredicate<Person> predicate = new AddressContainsKeywordPredicate<>("Bishan");
        assertTrue(predicate.test(new ElderlyBuilder().withAddress("Bishan").build()));
        assertTrue(predicate.test(new VolunteerBuilder().withAddress("Bishan").build()));

        // substring match
        assertTrue(predicate.test(new ElderlyBuilder().withAddress("Bishan Drive").build()));
        assertTrue(predicate.test(new VolunteerBuilder().withAddress("Bishan Drive").build()));

        // case insensitive
        assertTrue(predicate.test(new ElderlyBuilder().withAddress("BIsHaN").build()));
        assertTrue(predicate.test(new VolunteerBuilder().withAddress("BIsHaN").build()));
    }

    @Test
    public void test_addressDoesNotContainKeyword_returnsFalse() {
        AddressContainsKeywordPredicate<Person> predicate = new AddressContainsKeywordPredicate<>("BLK 543");
        assertFalse(predicate.test(new ElderlyBuilder().withAddress("Bishan").build()));
        assertFalse(predicate.test(new VolunteerBuilder().withAddress("Bishan").build()));
    }
}

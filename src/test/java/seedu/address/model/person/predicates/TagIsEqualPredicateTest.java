package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.ElderlyBuilder;
import seedu.address.testutil.VolunteerBuilder;

public class TagIsEqualPredicateTest {
    @Test
    public void equals() {
        TagIsEqualPredicate<Person> firstPredicate = new TagIsEqualPredicate<>("Eager");
        TagIsEqualPredicate<Person> secondPredicate = new TagIsEqualPredicate<>("Active");

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagIsEqualPredicate<Person> firstPredicateCopy = new TagIsEqualPredicate<>("Eager");
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different tag -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagIsEqual_returnsTrue() {
        // matching tag
        TagIsEqualPredicate<Person> predicate = new TagIsEqualPredicate<>("Eager");
        assertTrue(predicate.test(new ElderlyBuilder().withTags("Eager").build()));
        assertTrue(predicate.test(new VolunteerBuilder().withTags("Eager").build()));

        // matching tag in multiple tags
        assertTrue(predicate.test(new ElderlyBuilder().withTags("Single").withTags("Eager").build()));
        assertTrue(predicate.test(new VolunteerBuilder().withTags("Single").withTags("Eager").build()));

        // case insensitive
        assertTrue(predicate.test(new ElderlyBuilder().withTags("EaGEr").build()));
        assertTrue(predicate.test(new VolunteerBuilder().withTags("EaGEr").build()));
    }

    @Test
    public void test_tagIsNotEqual_returnsFalse() {
        // non-matching tag
        TagIsEqualPredicate<Person> predicate = new TagIsEqualPredicate<>("Eager");
        assertFalse(predicate.test(new ElderlyBuilder().withTags("45").build()));
        assertFalse(predicate.test(new VolunteerBuilder().withTags("45").build()));

        // no matching tag in multiple tags
        assertFalse(predicate.test(new ElderlyBuilder().withTags("Single").withTags("Strong").build()));
        assertFalse(predicate.test(new VolunteerBuilder().withTags("Single").withTags("Strong").build()));
    }
}

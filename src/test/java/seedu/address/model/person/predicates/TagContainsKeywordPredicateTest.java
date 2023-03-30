package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.ElderlyBuilder;
import seedu.address.testutil.VolunteerBuilder;

class TagContainsKeywordPredicateTest {
    @Test
    public void equals() {
        TagContainsKeywordPredicate<Person> firstPredicate = new TagContainsKeywordPredicate<>("Eager");
        TagContainsKeywordPredicate<Person> secondPredicate = new TagContainsKeywordPredicate<>("Active");

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagContainsKeywordPredicate<Person> firstPredicateCopy = new TagContainsKeywordPredicate<>("Eager");
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different tag -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagContainsKeyword_returnsTrue() {
        // exact match
        TagContainsKeywordPredicate<Person> predicate = new TagContainsKeywordPredicate<>("Eag");
        assertTrue(predicate.test(new ElderlyBuilder().withTags("Eag").build()));
        assertTrue(predicate.test(new VolunteerBuilder().withTags("Eag").build()));

        // matching tag
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
    public void test_tagDoesNotContainKeyword_returnsFalse() {
        // non-matching tag
        TagContainsKeywordPredicate<Person> predicate = new TagContainsKeywordPredicate<>("Eager");
        assertFalse(predicate.test(new ElderlyBuilder().withTags("45").build()));
        assertFalse(predicate.test(new VolunteerBuilder().withTags("45").build()));

        // no matching tag in multiple tags
        assertFalse(predicate.test(new ElderlyBuilder().withTags("Single").withTags("Strong").build()));
        assertFalse(predicate.test(new VolunteerBuilder().withTags("Single").withTags("Strong").build()));
    }

    @Test
    public void test_emptyTag_throwsIllegalArgumentException() {
        String invalidTag = "";
        assertThrows(IllegalArgumentException.class, () -> new TagContainsKeywordPredicate<>(invalidTag));
    }
}
